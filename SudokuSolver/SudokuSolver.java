import java.util.Scanner;

class Sudoku {
	static final int GRID_SIZE = 9;

	static void takeUserInput(int[][] board) {
		Scanner sc = new Scanner(System.in);
		Boolean flag = true;
		System.out.println("Enter the Sudoku grid (0 for empty cells):");

		for (int row = 0; row < GRID_SIZE; row++) {
			for (int column = 0; column < GRID_SIZE; column++) {
				while (flag) {
					System.out.print("Enter element at position (" + (row + 1) + "," + (column + 1) + "): ");
					String x = sc.next();
					if (x.matches("[0-9]")) {
						board[row][column] = Integer.parseInt(x);
						flag = false;
					} else {
						System.out.println("Please Enter A Valid Number e.g.(0 to 9)");
					}

				}
				flag = true;
			}
		}
	}

	static void printBoard(int[][] board) {
		for (int row = 0; row <= GRID_SIZE - 1; row++) {

			if (row % 3 == 0 && row != 0) {
				System.out.println("----------------------");
			}
			for (int column = 0; column <= GRID_SIZE - 1; column++) {
				if (column % 3 == 0 && column != 0) {
					System.out.print("| ");
				}
				System.out.print(board[row][column] + " ");
			}
			System.out.println();
		}
	}

	static boolean isNumberInRow(int[][] board, int number, int row) {
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[row][i] == number) {
				return true;
			}
		}
		return false;
	}

	static boolean isNumberInColumn(int[][] board, int number, int column) {
		for (int i = 0; i < GRID_SIZE; i++) {
			if (board[i][column] == number) {
				return true;
			}
		}
		return false;
	}

	static boolean isNumberInBox(int[][] board, int number, int row, int column) {
		int localBoxRow = row - row % 3;
		int localBoxColumn = column - column % 3;

		for (int i = localBoxRow; i < localBoxRow + 3; i++) {
			for (int j = localBoxColumn; j < localBoxColumn + 3; j++) {
				if (board[i][j] == number) {
					return true;
				}
			}
		}
		return false;
	}

	static boolean isValidPlacement(int[][] board, int number, int row, int column) {
		return !isNumberInRow(board, number, row) && !isNumberInColumn(board, number, column)
				&& !isNumberInBox(board, number, row, column);
	}

	static boolean solveBoard(int[][] board) {
		for (int row = 0; row < GRID_SIZE; row++) {
			for (int column = 0; column < GRID_SIZE; column++) {
				if (board[row][column] == 0) {
					for (int numberToTry = 1; numberToTry <= GRID_SIZE; numberToTry++) {
						if (isValidPlacement(board, numberToTry, row, column)) {
							board[row][column] = numberToTry;

							if (solveBoard(board)) {
								return true;
							} else {
								board[row][column] = 0;
							}
						}
					}
					// If no valid number was found, backtrack
					return false;
				}
			}
		}
		// If the entire board is filled, return true
		return true;
	}
}

class SudokuSolver extends Sudoku

{
	public static void main(String[] args) {
		int[][] board = new int[GRID_SIZE][GRID_SIZE];
		Scanner sc = new Scanner(System.in);

		System.out.println("=============================");
		System.out.println("||WELCOME                  ||");
		System.out.println("||        TO               ||");
		System.out.println("||           SUDOKU_SOLVER ||");
		System.out.println("=============================");

		// Take user input for the Sudoku grid
		takeUserInput(board);

		System.out.println("Entered Sudoku Board:");
		printBoard(board);
		boolean flag = true;
		while (flag) {
			System.out.println("ARE YOU WANT TO CHANGE YOUR GRID??");
			System.out.println("PRESS 1: FOR CHANGE YOUR GRID. ");
			System.out.println("PRESS 2: FOR CONTINUE WITH THIS GRID. ");
			int choice = sc.nextInt();
			switch (choice) {
				case 1: {
					System.out.println("x---x---x---x---x----x----x---x---x---x");
					System.out.println("||  SO YOU WANT TO CHANGE THE GRID.  ||");
					System.out.println("||        REWRITE THE GRID.          ||");
					System.out.println("x---x---x---x---x----x----x---x---x---x");
					takeUserInput(board);
					printBoard(board);
					break;

				}
				case 2: {
					System.out.println("x---x---x---x---x----x----x---x---x---x");
					System.out.println("||     LET'S TRY WITH THIS BOARD.    ||");
					System.out.println("x---x---x---x---x----x----x---x---x---x");
					flag = false;
					break;

				}
				default: {
					System.out.println("x---x---x---x---x----x----x---x---x---x---x---x---x");
					System.out.println("|| PLEASE ENTER THE CORRECT VALUE ONLY 1 AND 2.  ||");
					System.out.println("x---x---x---x---x----x----x---x---x---x---x---x---x");
					break;
				}
			}
		}

		if (solveBoard(board)) {
			System.out.println();
			System.out.println("===============================");
			System.out.println("||  SOLVED SUCCESFULLY!! :)  ||");
			System.out.println("===============================");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("=============================");
			System.out.println("||  UNSOLVABLE BOARD!! :(  ||");
			System.out.println("=============================");
			System.out.println();
		}
		System.out.println();
		System.out.println("========================");
		System.out.println("|| Final Sudoku Board ||");
		System.out.println("========================");
		System.out.println();
		printBoard(board);
	}
}
