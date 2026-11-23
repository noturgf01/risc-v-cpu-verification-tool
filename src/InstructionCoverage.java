final class InstructionCoverage {
    static final String[] INSTRUCTIONS = {"ADD", "SUB", "ADDI"};
    private final int[] testCounts = new int[INSTRUCTIONS.length];

    void record(String instruction) {
        for (int index = 0; index < INSTRUCTIONS.length; index++) {
            if (INSTRUCTIONS[index].equals(instruction)) {
                testCounts[index]++;
                return;
            }
        }
    }

    void printReport() {
        int covered = 0;
        for (int index = 0; index < INSTRUCTIONS.length; index++) {
            int count = testCounts[index];
            String label;
            if (count == 1) {
                label = "test";
            } else {
                label = "tests";
            }
            System.out.printf("%s: %d %s%n", INSTRUCTIONS[index], count, label);
            if (count > 0) {
                covered++;
            }
        }
        System.out.printf("Instruction coverage: %d/%d%n", covered, INSTRUCTIONS.length);
    }
}
