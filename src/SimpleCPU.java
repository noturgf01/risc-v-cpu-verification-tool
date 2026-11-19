public class SimpleCPU {
    private int[] registers;

    public SimpleCPU() {
        registers = new int[32];
    }
    public int readRegister(int index) {
        if (index < 0 || index >= registers.length) {
            throw new IllegalArgumentException("Invalid register index: " + index);
        }
        return registers[index];
    }
    public void writeRegister(int index, int value) {
        if (index < 0 || index >= registers.length) {
            throw new IllegalArgumentException("Invalid register index: " + index);
        }
        if(index !=0) {
            registers[index] = value;
        }
    }
    public void add(int rd, int rs1, int rs2) {
        if (rd < 0 || rd >= registers.length || rs1 < 0 || rs1 >= registers.length || rs2 < 0 || rs2 >= registers.length) {
            throw new IllegalArgumentException("Invalid register index");
        }
        if(rd != 0) {
            registers[rd] = registers[rs1] + registers[rs2];
        }
    }
    public void sub(int rd, int rs1, int rs2) {
        if (rd < 0 || rd >= registers.length || rs1 < 0 || rs1 >= registers.length || rs2 < 0 || rs2 >= registers.length) {
            throw new IllegalArgumentException("Invalid register index");
        }
        if(rd != 0) {
            registers[rd] = registers[rs1] - registers[rs2];
        }
    }
    public void addi(int rd, int rs1, int immediate) {
        if (rd < 0 || rd >= registers.length || rs1 < 0 || rs1 >= registers.length) {
            throw new IllegalArgumentException("Invalid register index");
        }
        if (immediate < -2048 || immediate > 2047) {
            throw new IllegalArgumentException("Invalid immediate value");
        }
        if(rd != 0) {
            registers[rd] = registers[rs1] + immediate;
        }
        
    }
}

