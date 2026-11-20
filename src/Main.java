
public class Main {

    public static void main(String[] args) {
        if (args.length == 1 && "--random".equals(args[0])) {
            if (!RandomTests.run()) {
                System.exit(1);
            }
            return;
        }

        SimpleCPU cpu = new SimpleCPU();
        System.out.println("RISC-V CPU Verification Tool");
        cpu.writeRegister(1, 10);
        cpu.writeRegister(2, 20);
        cpu.writeRegister(0, 100);
        System.out.println(cpu.readRegister(1));
        System.out.println(cpu.readRegister(2));
        System.out.println(cpu.readRegister(0));
        try {
            cpu.readRegister(-1);
            System.out.println("FAIL: Negative index accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Negative index rejected");
        }

        try {
            cpu.writeRegister(32, 100);
            System.out.println("FAIL: Index 32 accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Index 32 rejected");
        }
        cpu.add(3, 1, 2);
        System.out.println("ADD result: " + cpu.readRegister(3));
        cpu.add(0, 1, 2);
        System.out.println("ADD to x0: " + cpu.readRegister(0));

        cpu.add(1, 1, 2);
        System.out.println("ADD with same destination: " + cpu.readRegister(1));
        cpu.writeRegister(1, 30);
        cpu.writeRegister(2, 10);

        cpu.sub(3, 1, 2);
        System.out.println("SUB result: " + cpu.readRegister(3));

        cpu.sub(0, 1, 2);
        System.out.println("SUB to x0: " + cpu.readRegister(0));

        cpu.sub(1, 1, 2);
        System.out.println("SUB same destination: " + cpu.readRegister(1));
        cpu.writeRegister(1, 20);

        cpu.addi(3, 1, 15);
        System.out.println("ADDI result: " + cpu.readRegister(3));

        cpu.addi(4, 1, -5);
        System.out.println("ADDI negative: " + cpu.readRegister(4));

        cpu.addi(0, 1, 100);
        System.out.println("ADDI to x0: " + cpu.readRegister(0));

        cpu.writeRegister(3, 100);

        try {
            cpu.addi(3, 1, 5000);
            System.out.println("FAIL: Invalid immediate accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: " + e.getMessage());
        }

        System.out.println("x3 after invalid ADDI: " + cpu.readRegister(3));
        runPredefinedTests();
    }

    private static void runPredefinedTests() {
        TestCase[] tests = {
            new TestCase("ADD", 3, 1, 2, 30),
            new TestCase("SUB", 3, 1, 2, -10),
            new TestCase("ADDI", 3, 2, -5, 15),
            new TestCase("SUB", 3, 1, 1, 0),
            new TestCase("ADD", 0, 1, 2, 0)
        };

        System.out.println("\nPredefined tests");
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
    }
}
