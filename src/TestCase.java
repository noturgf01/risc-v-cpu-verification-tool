public class TestCase {
    private final String instruction;
    private final int rd;
    private final int rs1;
    private final int operand;
    private final int expected;

    public TestCase(String instruction, int rd, int rs1, int operand, int expected) {
        this.instruction = instruction;
        this.rd = rd;
        this.rs1 = rs1;
        this.operand = operand;
        this.expected = expected;
    }

    public String getInstruction() {
        return instruction;
    }

    public int getRd() {
        return rd;
    }

    public int getRs1() {
        return rs1;
    }

    public int getOperand() {
        return operand;
    }

    public int getExpected() {
        return expected;
    }
}
