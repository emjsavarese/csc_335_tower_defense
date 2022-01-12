import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Emily Savarese, Tatiana Kapos, Nancy Tran, Reagan O'Grady
 * Simple Tower class
 */
public class Tower {
	private int health;
	private int damage;
	private int speed;
	private int row;
	private int col;
	private int moneyProduced = 15;
	private boolean water;

	private ImageView imgView;

	private TowerAttack attack;
	private boolean moneyTow = false;

	private int cost;
	
	/**
	 * constructor of tower
	 * @param health int of health of tower
	 * @param image String of image path
	 * @param damage int of damage tower gives
	 * @param row row of tower
	 * @param col column of tower
	 */
	public Tower(int health, String image, int damage, int col, int row, String attackImg, int cost) {
		this.health = health;
		this.damage = damage;
		this.row = row;
		this.col = col;
		this.attack = new TowerAttack(attackImg, damage, row, col);
		this.cost = cost;
		this.speed = 1000;
		this.water = false;
		Image img = new Image(image);
		this.imgView = new ImageView(img);
		imgView.setFitHeight(50);
		imgView.setFitWidth(50);
	}
	
	/**
	 * If the tower can be placed on water
	 */
	public void setWater() {
		water = true;
	}
	
	/**
	 * Returns if the tower can be placed on water
	 * @return water
	 */
	public boolean getWater() {
		return water;
	}

	/**
	 * Returns how much money it costs to place the specified tower
	 * @return cost
	 */
	public int getCost() {
		return cost;
	}	
	
	/**
	 * Sets the tower's speed
	 * @param speedup (int) = tower's speed 
	 */
	public void setSpeed(int speedup) {
		speed = speedup;
	}
	
	/**
	 * Returns the tower's speed
	 * @return speed
	 */
	public int getSpeed() {
		return speed;
	}
	
	/**
	 * Returns the health of the tower
	 * @return int of health of tower
	 */
	public int getHealth() {
		return health;
	}
	

	/**
	 * Returns the damage tower gives
	 * @return damage
	 */
	public int getDamage() {
		return damage;
	}
	
	/**
	 * Returns the row of tower
	 * @return row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Returns the column of tower
	 * @return col
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * Subtracts the damage the tower is taking from its health
	 * @param hit (int) = damage the tower is taking
	 */
	public void attacked(int hit) {
		health -= hit;
	}
	
	/**
	 * Returns the imageView of the tower
	 * @return imgView
	 */
	public ImageView getImageView() {
		return imgView;
	}
	
	/**
	 * Makes the tower's image disappear
	 */
	public void hideImage() {
		imgView.setVisible(false);
	}
	
	/**
	 * Helper for tower attack(if tower is sold)
	 */
	public void stopTower() {
		health = 0;
	}
	
	/**
	 * Returns bullet image for the specific towers
	 * @return attack.getImageView()
	 */
	public String getAttackImg() {
		//return attack.getImage(); <- This will be the return stmt when/if we have different attack imgs
		return attack.getImage();
	}
	
	/**
	 * Returns the tower's attack instance
	 * @return attack
	 */
	public TowerAttack getAttack() {
		return attack;
	}
	
	/**
	 * Restarts the tower's attack position to where the tower is located
	 */
	public void restartAttack() {
		attack.setCol(col);
	}
	
	/**
	 * If money tower, returns an amount of money to be added to the player
	 * @return moneyProduced
	 */
	public int generateMoney() {
		return moneyProduced;
	}
	
	/**
	 * Sets this tower to be a money tower
	 */
	public void setMoneyTow() {
		moneyTow = true;
	}
	
	/**
	 * Returns true if tower is a money tower
	 * @return moneyTow
	 */
	public boolean isMoneyTow() {
		return moneyTow;
	}

}
