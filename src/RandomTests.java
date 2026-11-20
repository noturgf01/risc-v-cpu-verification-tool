import java.util.Random;

final class RandomTests {
    private static final long SEED = 193L;
    private static final int TESTS_PER_INSTRUCTION = 100;

    static boolean run() {
        Random random = new Random(SEED);
        String[] instructions = {"ADD", "SUB", "ADDI"};
        int failed = 0;

        System.out.printf("Random tests (seed %d)%n", SEED);
        for (String instruction : instructions) {
            for (int index = 0; index < TESTS_PER_INSTRUCTION; index++) {
                SimpleCPU cpu = new SimpleCPU();
                int[] initialRegisters = new int[32];
                for (int register = 1; register < initialRegisters.length; register++) {
                    initialRegisters[register] = random.nextInt();
                    cpu.writeRegister(register, initialRegisters[register]);
                }

                int rd = random.nextInt(initialRegisters.length);
                int rs1 = random.nextInt(initialRegisters.length);
                int operand = "ADDI".equals(instruction)
                        ? random.nextInt(4096) - 2048
                        : random.nextInt(initialRegisters.length);
                long left = initialRegisters[rs1];
                long result = switch (instruction) {
                    case "ADD" -> left + initialRegisters[operand];
                    case "SUB" -> left - initialRegisters[operand];
                    case "ADDI" -> left + operand;
                    default -> throw new IllegalStateException("Unknown instruction: " + instruction);
                };
                // RV32 keeps the low 32 bits and discards all writes to x0.
                int expected = rd == 0 ? 0 : (int) result;
                TestCase test = new TestCase(instruction, rd, rs1, operand, expected);
                if (!new TestRunner(cpu).runTest(test)) {
                    failed++;
                    System.out.printf("Case %d: rd=x%d, rs1=x%d, operand=%d%n",
                            index + 1, rd, rs1, operand);
                }
            }
        }

        int total = TESTS_PER_INSTRUCTION * instructions.length;
        System.out.printf("Random tests: %d passed, %d failed%n", total - failed, failed);
        return failed == 0;
    }
}
