import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Emily Savarese, Tatiana Kapos, Nancy Tran, Reagan O'Grady
 * Tower's attack
 */

public class TowerAttack {
	private int damage;
	private int row;
	private int col;
	private String img;
	
	/**
	 * Constructor for a tower's attack instance.
	 * @param image (String) = image name in file
	 * @param damage (int) = how much damage this attack does
	 * @param row (int) = the row the tower is located at
	 * @param col (int) = the column the tower is located at
	 */
	public TowerAttack(String image, int damage, int row, int col) {
		this.damage = damage;
		this.row = row;
		this.col = col;
		this.img = image;
	}
	
	/**
	 * Returns the Tower Attack's image
	 * @return img
	 */
	public String getImage() {
		return img;
	}
	
	/**
	 * Returns the row the attack is currently at
	 * @return row 
	 */
	public int returnRow() {
		return row;
	}
	
	/**
	 * Returns the column the attack is currently at
	 * @return col
	 */
	public int returnCol() {
		return col;
	}
	
	/**
	 * Move the attack forward by one column
	 */
	public void move() {
		col ++;
	}
	
	/**
	 * Reset the attack to the tower's current column
	 * @param oldCol (int) = col the tower is placed at
	 */
	public void setCol(int oldCol) {
		col = oldCol;
	}
}
