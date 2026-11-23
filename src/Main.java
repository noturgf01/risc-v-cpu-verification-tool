public class Main {
    public static void main(String[] args) {
        if (args.length == 1 && "--random".equals(args[0])) {
            if (!RandomTests.run()) {
                System.exit(1);
            }
            return;
        }

        if (args.length != 0) {
            System.err.println("Usage: java -cp out Main [--random]");
            System.exit(2);
        }

        System.out.println("RISC-V CPU Verification Tool");
        boolean predefinedPassed = runPredefinedTests();
        boolean validationPassed = runValidationTests();
        if (!predefinedPassed || !validationPassed) {
            System.exit(1);
        }
    }

    private static boolean runValidationTests() {
        SimpleCPU cpu = new SimpleCPU();
        cpu.writeRegister(3, 100);
        boolean negativeIndexRejected = expectInvalidInput(
                "Negative read index", () -> cpu.readRegister(-1));
        boolean highIndexRejected = expectInvalidInput(
                "Out-of-range write index", () -> cpu.writeRegister(32, 100));
        boolean immediateRejected = expectInvalidInput(
                "Out-of-range ADDI immediate", () -> cpu.addi(3, 1, 5000));

        // Invalid instructions must leave the destination register intact.
        boolean destinationPreserved = cpu.readRegister(3) == 100;
        if (!destinationPreserved) {
            System.err.println("FAIL: Invalid ADDI changed x3");
        }
        return negativeIndexRejected && highIndexRejected && immediateRejected && destinationPreserved;
    }

    private static boolean expectInvalidInput(String caseName, Runnable operation) {
        try {
            operation.run();
        } catch (IllegalArgumentException error) {
            System.out.printf("PASS: %s (%s)%n", caseName, error.getMessage());
            return true;
        }
        System.err.printf("FAIL: %s was accepted%n", caseName);
        return false;
    }


    private static boolean runPredefinedTests() {
        TestCase[] tests = {
            new TestCase("ADD", 3, 1, 2, 30),
            new TestCase("SUB", 3, 1, 2, -10),
            new TestCase("ADDI", 3, 2, -5, 15),
            new TestCase("SUB", 3, 1, 1, 0),
            new TestCase("ADD", 0, 1, 2, 0)
        };

        System.out.println("Predefined tests");
        int passed = 0;
        for (TestCase test : tests) {
            SimpleCPU cpu = new SimpleCPU();
            cpu.writeRegister(1, 10);
            cpu.writeRegister(2, 20);

            TestRunner runner = new TestRunner(cpu);
            if (runner.runTest(test)) {
                passed++;
            }
            System.out.println();
        }
        System.out.printf("Predefined tests: %d passed, %d failed%n", passed, tests.length - passed);
        return passed == tests.length;
    }
}
