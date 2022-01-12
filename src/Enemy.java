import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Enemy class - creates and holds an enemy's attributes
 * @author Reagan O'Grady, Emily Savarese, Tatiana Kapos, Nancy Tran
 */
public class Enemy {
	private int attack;
	private int speed;
	private int health;
	private ImageView imgView;
	
	private int row;
	private int col;
	
	private boolean fight;
	
	/** 
	 * Constructor: Initializes the attributes of a specific enemy
	 * @param attack (int) = The amount of damage the enemy does
	 * @param speed (int) = The speed at which the enemy moves
	 * @param health (int) = How much health the enemy has
	 * @param imageName (String) = name of the enemy's image
	 */
	public Enemy(int attack, int speed, int health, String imageName) {
		this.attack = attack;
		this.speed = speed;
		this.health = health;
		this.col = 9;
		this.fight = false;
		Image img = new Image(imageName);
		this.imgView = new ImageView(img);
		imgView.setFitHeight(50);
		imgView.setFitWidth(50);
	}
	
	/**
	 * Sets the enemy to attacking
	 */
	public void startFight() {
		fight = true;
	}
	
	/**
	 * Sets the enemy to stop attacking
	 */
	public void endFight() {
		fight = false;
	}
	
	/**
	 * Returns whether the enemy is attacking or not
	 * @return fight
	 */
	public boolean getFight() {
		return fight;
	}
	
	/**
	 * Returns the speed the enemy travels at
	 * @return speed (int) = Enemy's speed
	 */
	public int getSpeed() {
		return speed;	
	}
	
	/**
	 * Returns the enemy's damage
	 * @return attack (int) = Amount of damage the enemy does
	 */
	public int getAttack() {
		return attack;
	}
	
	/**
	 * Returns the enemy's current health
	 * @return health (int) = The amount of health the enemy currently has
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Returns the enemy's image/animation
	 * @return imgView
	 */
	public ImageView getImageView() {
		return imgView;
	}
	
	/**
	 * Makes the enemy's image disappear
	 */
	public void hideImage() {
		imgView.setVisible(false);
	}
	
	/**
	 * Does damage to the enemy's health
	 * @param damageDone (int) = How much health is subtracted from the enemy
	 */
	public void hurtHealth(int damageDone) {
		health -= damageDone;
		if(health < 0) {
			health = 0;
		}
	}

	/**
	 * Sets the enemy's row position
	 * @param row (int) = Row number
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * Returns the row the enemy is situated at
	 * @return row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Decreases the enemy's column number as it moves towards where it
	 *  attacks (column 1)
	 */
	public void move() {
		this.col --;
	}
	
	/**
	 * Returns the column the enemy is situated at
	 * @return col
	 */
	public int getCol() {
		return col;
	}
}
