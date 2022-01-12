/**
 * @author Emily Savarese, Tatiana Kapos, Nancy Tran, Reagan O'Grady
 * Controller for Tower Defense Game, implements game logic
 */
public class TowerDefenseController {
	TowerDefenseModel model;
	
	/**
	 * Constructor for Tower Defense Controller
	 * @param model Tower Defense Model to base controller off of
	 */
	public TowerDefenseController(TowerDefenseModel model) {	
		this.model = model;
	}
	
	/**
	 * Board setter
	 * @param b board of Board class
	 */
	public void setBoard(Board b) {
		model.setBoard(b);
	}

	/**
	 * Board getter
	 * @return the board of game
	 */
	public Board returnBoard() {
		return model.returnBoard();
	}
	
	/**
	 * Checks if a specific row/col is valid
	 * @param row row to check
	 * @param col column to check
	 * @return true if row/col is valid false otherwise
	 */
	public boolean validTile(int row, int col) {
		if (model.valid(row, col)) {
			return true;
		}
		return false;
	}	
	
	/**
	 * Places a tower onto the board
	 * @param row row to place
	 * @param col column to place
	 */
	public void placeTower(int row, int col) {
		model.placeTower(row, col);
	}	
	
	/**
	 * Gets the rows of the board
	 * @return the number of rows in the board
	 */
	public int returnRows() {
		return model.returnRows();
	}
	
	/**
	 * Gets the columns of the board
	 * @return the number of columns in the board
	 */
	public int returnCols() {	
		return model.returnCols();
	}
	
	/**
	 * Buys a Tower
	 */
	public void buyTower(int cost) {
		model.buyTower(cost);
	}
	
	/**
	 * Checks if player has enough money to buy a tower1
	 * @param cost is a tower's cost
	 * @return true if enough money, false otherwise
	 */
	public boolean canBuyTower(int cost) {
		if(model.getMoney() - cost >= 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the amount of money the user has
	 * @return int amount of user money
	 */
	public int getMoney() {
		return model.getMoney();
	}
	
	/**
	 * Adds money to the player's current balance
	 * @param x (int) = amount of money being collected
	 */
	public void addMoney(int x) {
		model.setMoney(model.getMoney() + x);
	}
	
	/**
	 * Sets the player's money balance to 100
	 */
	public void setMoney() {
		model.setMoney(100);
	}
	
	/**
	 * Returns the amount o health the user has
	 * @return int amount of user's health
	 */
	public int getHealth() {
		return model.getHealth();
	}
	
	/**
	 * Damage user's health
	 * @param hit (int) = amount of damage being subtracted from the player's health
	 */
	public void damageHealth(int hit) {
		model.damageHealth(hit);
	}
	
	/** 
	 * Sets the player's health to the specified amount
	 * @param health (int) = an amount of health
	 */
	public void setHealth(int health) {
		model.setHealth(100);
	}
	
	/**
	 * Sets position on Board
	 * @param x int to set
	 * @param row row to set
	 * @param col column to set
	 */
	public void setPos(int x, int row, int col) {
		model.setPos(x, row, col);
	}
	
	/**
	 * Returns the position at the given row and col
	 * @param row (int) = row number
	 * @param col (int) = col number
	 * @return model.getPos(row,col)S
	 */
	public int getPos(int row, int col) {
		return model.getPos(row, col);
	}
}
