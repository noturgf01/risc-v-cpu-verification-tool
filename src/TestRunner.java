public class TestRunner {
    private final SimpleCPU cpu;

    public TestRunner(SimpleCPU cpu) {
        this.cpu = cpu;
    }

    public boolean runTest(TestCase test) {
        String instruction = test.getInstruction();
        int rd = test.getRd();
        int rs1 = test.getRs1();
        int operand = test.getOperand();
        int expected = test.getExpected();

        if ("ADD".equals(instruction)) {
            cpu.add(rd, rs1, operand);
        } else if ("SUB".equals(instruction)) {
            cpu.sub(rd, rs1, operand);
        } else if ("ADDI".equals(instruction)) {
            cpu.addi(rd, rs1, operand);
        } else {
            throw new IllegalArgumentException(
                    "Unknown instruction '" + instruction + "'. Supported instructions: ADD, SUB, ADDI");
        }

        int actual = cpu.readRegister(rd);
        boolean passed = actual == expected;

        System.out.println("Instruction: " + instruction);
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);
        if (passed) {
            System.out.println("Result: PASS");
        } else {
            String source = "ADDI".equals(instruction) ? "immediate=" + operand : "rs2=x" + operand;
            System.out.printf("Result: FAIL (rd=x%d, rs1=x%d, %s)%n", rd, rs1, source);
        }
        return passed;
    }
}
