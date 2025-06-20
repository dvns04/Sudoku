public class Sudoku {
    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        int[][] board = {
                {7, 0, 2, 0, 5, 0, 6, 0, 0},
                {0, 0, 0, 0, 0, 3, 0, 0, 0},
                {1, 0, 0, 0, 0, 9, 5, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 9, 0},
                {0, 4, 3, 0, 0, 0, 7, 5, 0},
                {0, 9, 0, 0, 0, 0, 0, 0, 8},
                {0, 0, 9, 7, 0, 0, 0, 0, 5},
                {0, 0, 0, 2, 0, 0, 0, 0, 0},
                {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };
        displayBoard(board);
        boolean solved = solve(board);
        System.out.println();
        if (solved) {
            System.out.println("Puzzle solved!");
        } else {
            System.out.println("Oops! Try Again");
        }
        displayBoard(board);
    }

    private static void displayBoard(int[][] board) {
        for (int r = 0; r < GRID_SIZE; r++) {
            if (r > 0 && r % 3 == 0) {
                System.out.println("-----------");
            }
            for (int c = 0; c < GRID_SIZE; c++) {
                if (c > 0 && c % 3 == 0) {
                    System.out.print("|");
                }
                System.out.print(board[r][c]);
            }
            System.out.println();
        }
    }
    private static boolean solve(int[][] grid) {
        for (int rowIdx = 0; rowIdx < GRID_SIZE; rowIdx++) {
            for (int colIdx = 0; colIdx < GRID_SIZE; colIdx++) {

                if (grid[rowIdx][colIdx] == 0) {
                    for (int attempt = 1; attempt <= GRID_SIZE; attempt++) {
                        if (isOkay(grid, attempt, rowIdx, colIdx)) {
                            grid[rowIdx][colIdx] = attempt;
                            if (solve(grid)) {
                                return true;
                            }
                            grid[rowIdx][colIdx] = 0;
                        }
                    }return false;
                }
            }
        } return true;
    }
    private static boolean isOkay(int[][] board, int digit, int r, int c) {
        return !existsInRow(board, digit, r) &&
                !existsInCol(board, digit, c) &&
                !existsInBox(board, digit, r, c);
    }
    private static boolean existsInRow(int[][] board, int num, int rowToCheck) {
        for (int j = 0; j < GRID_SIZE; j++) {
            if (board[rowToCheck][j] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean existsInCol(int[][] board, int num, int colToCheck) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][colToCheck] == num) {
                return true;
            }
        }
        return false;
    }
    private static boolean existsInBox(int[][] board, int num, int r, int c) {
        int boxRow = r - r % 3;
        int boxCol = c - c % 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j] == num) {
                    return true;
                }
            }
        }return false;
    }
}