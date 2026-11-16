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
}
