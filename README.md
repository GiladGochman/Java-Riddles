# Java Riddles

Three GUI puzzle solvers built in Java (Swing). Each is packaged as a runnable JAR.

---

## Requirements

- Java 8 or later installed (`java -version` to check)

---

## How to Run Each JAR

Open a terminal in the folder containing the JAR, then run:

### Sudoku

```
java -jar sudoku.jar
```

JAR location: `Sudoku/sudoku.jar`

### Griddlers (Nonogram solver)

```
java -jar "griddlers v1.08 (final).jar"
```

JAR location: `Griddlers/griddlers v1.08 (final).jar`

### Hidato

```
java -jar "Hidato v1.03.jar"
```

JAR location: `Hidato/Hidato v1.03.jar`

---

## How to Use

### Sudoku

1. A 9×9 grid opens.
2. Enter the known digits (1–9) into the pre-filled cells, or leave them blank for unknown cells.
3. Click **Solve** — the solver fills in the remaining cells using constraint propagation:
   - Each filled cell removes its value from the candidate lists of every cell in the same row, column, and 3×3 box.
   - When a 3×3 box has only one cell where a digit can go, that digit is placed there.
   - If a digit in a box is confined to a single row or column, it is eliminated from the rest of that row/column outside the box.

### Griddlers (Nonogram)

1. A dimensions dialog opens — enter the number of rows and columns.
2. A board opens. Enter the clue numbers for each row (left side) and each column (top).
   - Cells can be black (filled), white (empty/`x`), or unknown.
3. Click **Solve** — the solver iteratively applies logical deduction techniques:
   - **Overlap**: where the leftmost and rightmost possible positions of a block overlap, those cells must be filled.
   - **Mark borders**: if a completed block is at the edge of an unknown region, the cell just outside it must be empty.
   - **All done**: if the total filled cells already equal the sum of clues, all remaining unknowns become empty.
   - **Block away**: marks a gap as empty if it is too narrow for any remaining block.
   - **Black or X edge of blank**: extends fills adjacent to known blocks at the board boundaries.
   - **Mark border of biggest**: places empty markers on both sides of a found block whose size matches the largest clue.
4. The solver repeats these passes until no further progress is made.

### Hidato

1. A hexagonal board opens (diamond/hex shape, 61 cells numbered 1–61).
2. Enter the pre-given numbers into their correct cells (leave unknown cells blank). Cell `1` must always be filled so the solver knows where to start.
3. Click **Start!** — the solver uses backtracking:
   - Starting from cell `1`, it tries to place each consecutive number (`n+1`) in an adjacent cell (6 neighbors: left, right, upper-left, upper-right, lower-left, lower-right).
   - If the next number is already a given (pre-filled), the solver verifies it is in an adjacent cell and continues.
   - If the next number is unknown, the solver tries each empty neighbor, recursing until it reaches `61` (the goal) or backtracks.

---

## Source Files

| Puzzle     | Key source files |
|------------|-----------------|
| Sudoku     | `Sudoku/Square.java`, `Sudoku/overlap.java`, `Sudoku/Advanced.java`, `Sudoku/Frame.java` |
| Griddlers  | `Griddlers/Solve.java`, `Griddlers/BoardFrame.java`, `Griddlers/DimentionsFrame.java` |
| Hidato     | `Hidato/BoardFrame.java`, `Hidato/Block.java` |
