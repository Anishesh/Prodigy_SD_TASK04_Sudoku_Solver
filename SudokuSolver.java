public class SudokuSolver {
    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if (solveSudoku(board)) {
            System.out.println("Sudoku solved successfully:");
            printBoard(board);
        } else {
            System.out.println("No solution exists");
        }
    }

    private static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {  // Empty cell
                    for (int num = 1; num <= GRID_SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;

                            if (solveSudoku(board)) {
                                return true;
                            }

                            board[row][col] = 0;  // Backtrack
                        }
                    }
                    return false;  // No valid number found, trigger backtracking
                }
            }
        }
        return true;  // Puzzle solved
    }

    private static boolean isValid(int[][] board, int row, int col, int num) {
        // Check if 'num' is not in the given row
        for (int x = 0; x < GRID_SIZE; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }

        // Check if 'num' is not in the given column
        for (int x = 0; x < GRID_SIZE; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }

        // Check if 'num' is not in the 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("-----------");
            }

            for (int col = 0; col < GRID_SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("|");
                }

                System.out.print(board[row][col] == 0 ? "." : board[row][col]);
                if (col != GRID_SIZE - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
    