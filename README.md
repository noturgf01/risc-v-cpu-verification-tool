# RISC-V CPU Verification Tool

## Goal
A Java tool that tests a simplified RISC-V CPU model and reports errors.

## MVP
- Implement ADD, SUB, and ADDI instructions.
- Run predefined test cases.
- Run seeded random test cases with `java -cp out Main --random`.
- Compare actual and expected results.
- Report passed and failed tests.
- Report invalid instruction operands with their values and allowed ranges.

## Tech Stack
- Java (JDK 21). Compile with `javac -d out src/*.java` and run with `java -cp out Main`.
- VS Code
- Git and GitHub

## Future Plans
- Add memory and branch instructions.
- Track instruction coverage.
- Explore SystemVerilog integration.

## Documentation
- [Eisenhower Matrix](docs/eisenhower-matrix.png)
- [System Diagram](docs/system-diagram.png)
