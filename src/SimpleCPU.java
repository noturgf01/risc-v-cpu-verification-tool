public class SimpleCPU {
    private final int[] registers = new int[32];

    public int readRegister(int index) {
        validateRegisterIndex("Register", index);
        return registers[index];
    }

    public void writeRegister(int index, int value) {
        validateRegisterIndex("Register", index);
        storeRegisterValue(index, value);
    }

    private void storeRegisterValue(int index, int value) {
        // RISC-V hardwires x0 to zero even when an instruction targets it.
        if (index != 0) {
            registers[index] = value;
        }
    }

    public void add(int rd, int rs1, int rs2) {
        validateOperand("ADD", "rd", rd);
        validateOperand("ADD", "rs1", rs1);
        validateOperand("ADD", "rs2", rs2);
        storeRegisterValue(rd, registers[rs1] + registers[rs2]);
    }

    public void sub(int rd, int rs1, int rs2) {
        validateOperand("SUB", "rd", rd);
        validateOperand("SUB", "rs1", rs1);
        validateOperand("SUB", "rs2", rs2);
        storeRegisterValue(rd, registers[rs1] - registers[rs2]);
    }

    public void addi(int rd, int rs1, int immediate) {
        validateOperand("ADDI", "rd", rd);
        validateOperand("ADDI", "rs1", rs1);
        if (immediate < -2048 || immediate > 2047) {
            throw new IllegalArgumentException(
                    "ADDI immediate " + immediate + " is out of range [-2048, 2047]");
        }
        storeRegisterValue(rd, registers[rs1] + immediate);
    }

    private void validateOperand(String instruction, String operand, int index) {
        validateRegisterIndex(instruction + " register " + operand, index);
    }

    private void validateRegisterIndex(String operand, int index) {
        if (index < 0 || index >= registers.length) {
            throw new IllegalArgumentException(operand
                    + " index " + index + " is out of range [0, " + (registers.length - 1) + "]");
        }
    }
}

