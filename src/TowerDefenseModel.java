import java.util.Observable;
/**
 * @author Emily Savarese, Tatiana Kapos, Nancy Tran, Reagan O'Grady
 * Model for Tower Defense Game, holds Board class
 */
public class TowerDefenseModel{
	private Board BOARD;
	private int money = 100; //starts off with 100
	private int health = 100; //starts off with 100
	
	/**
	 * Constructor that creates a default board
	 */
	public TowerDefenseModel() {		
		BOARD = new Board();
	}

	/**
	 * Constructor that creates a board based on the given file
	 * @param filename (String) = file name of a map
	 */
	public TowerDefenseModel(String filename) {
		BOARD = new Board(filename);	
	}
	
	/**
	 * Sets the player's health to the amount specified
	 * @param health (int) = amount to set health to
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * Sets the player's money balance to the specified amount
	 * @param m (int) = amount of money
	 */
	public void setMoney(int m) {
		this.money = m;
	}
	
	/**
	 * Returns the player's current health
	 * @return int of the player's health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Returns the how much money the player has
	 * @return int of amount of money player has
	 */
	public int getMoney() {
		return money;
	}
	
	/**
	 * Enemies damage user's health
	 * @param hit (int) = amount of damage the player's health is taking
	 */
	public void damageHealth(int hit) {
		health -= hit;
	}
	
	/**
	 * Buys a tower
	 * @param cost (int) = amount of money the tower costs
	 */
	public void buyTower(int cost) {
		money = money - cost;
	}
	
	/**
	 * Sets the board to the one passed in
	 * @param b (Board) = Board instance
	 */
	public void setBoard(Board b) {
		BOARD = b;
	}
	
	/**
	 * Returns the current Board
	 * @return BOARD
	 */
	public Board returnBoard() {
		return BOARD;
	}
	
	/**
	 * Places a tower at the given position
	 * @param row (int) = row position
	 * @param col (int) = col position
	 */
	public void placeTower(int row, int col) {
		BOARD.placeTower(row, col);
	}

	/**
	 * checks if row/col is valid
	 * @param row row to check
	 * @param col column to check
	 * @return true if valid, false otherwise
	 */
	public boolean valid(int row, int col) {
		if (BOARD.free(row, col)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the number of rows in board
	 * @return an int of number of rows in board
	 */
	public int returnRows() {
		return BOARD.returnRows();
	}
	
	/**
	 * Gets the number of columns in board
	 * @return int of number of columns in board
	 */
	public int returnCols() {
		return BOARD.returnCols();
	}
	
	/**
	 * sets position on Board
	 * @param x int to set
	 * @param row row to set
	 * @param col column to set
	 */
	public void setPos(int x, int row, int col) {
		BOARD.setPosition(x, row, col);
	}
	
	/**
	 * gets position on Board
	 * @param row row to set
	 * @param col column to set
	 * @return x int of board at [row][col]
	 */
	public int getPos(int row, int col) {
		return BOARD.grabPosition(row, col);
	}
}
