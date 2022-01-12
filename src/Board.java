import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
/**
 * @author Emily Savarese, Tatiana Kapos, Nancy Tran, Reagan O'Grady
 * Hold Board of Tower Defense games
 */
public class Board {
	
	private int BOARD_COLS = 10;
	private int BOARD_ROWS = 10;
	
	private int[][] BOARD = new int[BOARD_ROWS][BOARD_COLS]; //[col][row]

	
	//board[row][col] = 0  -> can place tower
	//board[row][col] = 1  -> Area protecting(can't place tower)
	//board[row][col] = 2  -> Area full with tower already(can't place tower)
	//board[row][col] = 3 -> A spawn area for the enemies(can't place tower)
	//board[row][col] = 4 -> Area for water (can place tower)
	
	//creates a basic board!
	public Board() {
		
		for(int i = 0; i < BOARD_COLS ; i++) {
			//tower to protect
			BOARD[i][0] = 1;
			//enemies spawn
			BOARD[i][9] = 3;
		}
		
		//grass, free to place stuff
		for(int j = 0 ; j < BOARD_ROWS; j++) {
			for (int k = 1; k < (BOARD_COLS -1); k++) {
				BOARD[j][k] =0;
			}
		}
	}
	
	/**
	 * Sets the Grid of integers based on the file passed in.
	 * Scans in a file with rows of integers, and sets each 
	 * integer at a row, column position to be in that same
	 * row, column position in the Board.
	 * 
	 * @param filename, a string of the filename 
	 */
	public Board(String filename) {
		Scanner fileScan = null;
		try {
			fileScan = new Scanner(new File (filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int row = 0;
		int col= 0;
		while(fileScan.hasNextLine()) {
			String a = fileScan.next();
			BOARD[row][col] = Integer.parseInt(a);
			col++;
			if(col == 10) {
				col = 0;
				row++;
			}
		}
	}
	
	/**
	 * Places a tower in the Grid.
	 * Given a row and column position, places a 2 
	 * in that position, 2 representing a now 
	 * inoccupiable place within the grid.
	 * 
	 * @param row
	 * @param col
	 */
	public void placeTower(int row, int col) {
		BOARD[row][col] = 2;
	}

	/**
	 * Returns whether or not theres a free space on the board.
	 * Returns true or false depending on whether or not the 
	 * passed in board position is a 0 (indicating a free 
	 * position).
	 * @param row
	 * @param col
	 * @return a boolean
	 */
	public boolean free(int row, int col) {
		return (BOARD[row][col] != 2 && BOARD[row][col] != 1);
	}
	
	/**
	 * get the row length
	 * @return the row's length
	 */
	public int returnRows() {
		return BOARD_ROWS;
	}
	
	/**
	 * get the column length
	 * @return the column's length
	 */
	public int returnCols() {
		return BOARD_COLS;
	}
	
	/**
	 * get the in at board of [row][col]
	 * @param row row to set
	 * @param col column to set
	 * @return an int at board of [row][col]
	 */
	public int grabPosition(int row, int col) {
		return BOARD[row][col];
	}
	
	/**
	 * sets position on Board
	 * @param x int to set
	 * @param row row to set
	 * @param col column to set
	 */
	public void setPosition(int x, int row, int col) {
		BOARD[row][col] = x;
	}
	
	
	/**
	 * Checks if a row contains 4, the indicator for water.
	 * @param int row, indicating which row to check
	 * @return a boolean value indicating if a single value of water
	 * was found
	 */
	public boolean checkForWater(int row) {
		for(int i=0; i< BOARD_COLS; i++) {
			if(BOARD[row][i] == 4) {
				return true;
			}
		}
		return false;
	}
}

