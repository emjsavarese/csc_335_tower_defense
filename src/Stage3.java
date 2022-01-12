import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author Emily Savarese, Tatiana Kapos, Nancy Tran, Reagan O'Grady
 * Stage3 on Tower Defense, create enemies wave and map
*/
public class Stage3{
	private Board board;
	private GridPane grid;
	private List<Enemy> wave1;
	private List<Enemy> wave2;
	private List<Enemy> wave3;
	private int curWave;

	/**
	 * Stage3 Constructor - creates a new board with the given map and sets up the waves.
	 */
	public Stage3() {
		this.board = new Board("map3.txt");
		this.curWave = 0;
		setGrid();
		setWave1();
		setWave2();
		setWave3();
	}

	/**
	 * sets grid of stage3
	 * Sets up the GUI view of the board
	 *  1 = wall
	 *  4 = water
	 *  other = bridge
	 */
	void setGrid() {
		this.grid = new GridPane();
		boolean topHalf = true;
		boolean free = false;
		for(int row = 0; row < board.returnRows(); row++) {
			for(int col = 0; col < board.returnCols(); col++) {
				if(board.grabPosition(row, col) == 1) {
					//Area we are protecting!
					Image imgWall = new Image("file:wall.png");
					ImageView imgview = new ImageView(imgWall);
					imgview.setFitHeight(50);
					imgview.setFitWidth(50);
					grid.add(imgview, col, row);
				}else if(board.grabPosition(row, col) == 4) {
					///water area
					Image imgWater = new Image("file:water.png");
					ImageView imgview = new ImageView(imgWater);
					imgview.setFitHeight(50);
					imgview.setFitWidth(50);
					grid.add(imgview, col, row);
				}
				else {
					free = true;
					ImageView imgview = new ImageView();
					//bridge
					if(topHalf) {
						Image imgGrass = new Image("file:bridge1.png");
						imgview.setImage(imgGrass);
					}else {
						Image imgGrass = new Image("file:bridge2.png");
						imgview.setImage(imgGrass);
					}
					imgview.setFitHeight(50);
					imgview.setFitWidth(50);
					grid.add(imgview, col, row);
				}
			}
			if(free && topHalf) {
				topHalf = false;
			}else if(free && !topHalf) {
				topHalf = true;
			}
			free = false;
		}
	}
	
	/**
	 * Adds enemies to wave 1: 
	 * 4 default enemies
	 * 4 water enemies
	 * 2 speed enemies
	 */
	private void setWave1() {
		this.wave1 = new ArrayList<Enemy>();
		Random rand = new Random();
		int randRow = 0;
		int count = 0;
		//4 basic enemies
		while(count < 4) {
			randRow = rand.nextInt(10);
			if(!board.checkForWater(randRow)) {
				// Default enemy
				Enemy currEnemy = new Enemy(5, 5000, 40, "file:Wizard.png");
				currEnemy.setRow(randRow);
				wave1.add(currEnemy);
				count++;
			}
		}
		//4 water enemies
		while(count < 8) {
			randRow = rand.nextInt(10);
			if(board.checkForWater(randRow)) {
				// Water Enemy
				Enemy currEnemy = new Enemy(5,3000,40,"file:Mermaid.png");
				currEnemy.setRow(randRow);
				wave1.add(currEnemy);
				count++;
			}
		}
		//2 speed enemies
		while(count < 10) {
			randRow = rand.nextInt(10);
			// Speed Enemy
			Enemy currEnemy = new Enemy(5, 2000, 40, "file:Ninja.png");
			currEnemy.setRow(randRow);
			wave1.add(currEnemy);
			count++;
		}
		Collections.shuffle(wave1);
	}


	/**
	 * Adds enemies to wave 2: 
	 * 2 default enemies
	 * 4 water enemies
	 * 1 speed enemy
	 * 2 flying enemies
	 * 1 tank enemy
	 */
	private void setWave2() {
		this.wave2 = new ArrayList<Enemy>();
		Random rand = new Random();
		int randRow = 0;
		int count = 0;
		//2 basic, 1 tank
		while(count < 3) {
			randRow = rand.nextInt(10);
			if(!board.checkForWater(randRow)) {
				// Default Enemy
				Enemy currEnemy = new Enemy(5, 5000, 40, "file:Wizard.png");
				if(count == 2) {
					// Tank Enemy
					currEnemy = new Enemy(10,3000,80,"file:Knight.png");
				}
				currEnemy.setRow(randRow);
				wave2.add(currEnemy);
				count++;
			}
		}
		//2 water
		while(count < 7) {
			randRow = rand.nextInt(10);
			if(board.checkForWater(randRow)) {
				// Water Enemy
				Enemy currEnemy = new Enemy(5,3000,40,"file:Mermaid.png");
				currEnemy.setRow(randRow);
				wave2.add(currEnemy);
				count++;
			}
		}
		//2 fly, 1 speed
		while(count < 10) {
			randRow = rand.nextInt(10);
			// Flying Enemy
			Enemy currEnemy = new Enemy(5,3000,60,"file:Ghost.png");
			if(count == 7) {
				// Speed Enemy
				currEnemy = new Enemy(5, 2000, 40, "file:Ninja.png");
			}

			currEnemy.setRow(randRow);
			wave2.add(currEnemy);
			count++;
		}
		Collections.shuffle(wave2);
	}
	
	/**
	 * Adds enemies to wave 3: 
	 * 3 speed enemies
	 * 2 water enemies
	 * 2 flying enemies
	 * 2 tank enemies
	 * 1 boss enemy
	 */
	private void setWave3() {
		this.wave3 = new ArrayList<Enemy>();
		Random rand = new Random();
		int randRow = 0;
		int count = 0;
		//2 water
		while(count < 2) {
			randRow = rand.nextInt(10);
			if(board.checkForWater(randRow)) {
				// Water Enemy
				Enemy currEnemy = new Enemy(5,3000,40,"file:Mermaid.png");
				currEnemy.setRow(randRow);
				wave3.add(currEnemy);
				count++;
			}
		}
		// 2 flying, 3 speed
		while(count < 7) {
			randRow = rand.nextInt(10);
			// Speed Enemy
			Enemy currEnemy = new Enemy(5, 2000, 40, "file:Ninja.png");
			if(count < 4) {
				// Flying Enemy
				currEnemy = new Enemy(5,3000,60,"file:Ghost.png");
			}
			currEnemy.setRow(randRow);
			wave3.add(currEnemy);
			count++;

		}
		//2 Tank
		while(count < 9) {
			randRow = rand.nextInt(10);
			if(!board.checkForWater(randRow)) {
				// Tank Enemy
				Enemy currEnemy = new Enemy(10,3000,80,"file:Knight.png");
				currEnemy.setRow(randRow);
				wave3.add(currEnemy);
				count++;
			}
		}
		//1 Boss
		while(count < 10) {
			randRow = rand.nextInt(10);
			if(!board.checkForWater(randRow)) {
				// Boss Enemy
				Enemy currEnemy = new Enemy(20,5000,200,"file:Boss.png");
				currEnemy.setRow(randRow);
				wave3.add(currEnemy);
				count++;
			}
		}
		Collections.shuffle(wave3);
	}

	/**
	 * Returns the GridPane instance of this wave
	 * @return grid
	 */
	public GridPane getGrid() {
		return grid;
	}

	/**
	 * Returns the wave's board
	 * @return board
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * Returns the current wave number's enemy list 
	 * @return List<Enemy> : list of enemies for the wave
	 */
	public List<Enemy> getCurWave() {
		curWave++;
		if(curWave == 1) {
			return wave1;
		}else if(curWave == 2) {
			return wave2;
		}else if(curWave == 3) {
			return wave3;
		}
		return new ArrayList<Enemy>();
	}
}
