public class TestCase {
    private String instruction;
    private int rd;
    private int rs1;
    private int operand;
    private int expected;

public TestCase(String instruction, int rd, int rs1,
                int operand, int expected) {
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