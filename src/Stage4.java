import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Stage4 represents the 4th playable stage.
 * With unique enemy waves and a unique map.
 * 
 * @author Reagan O'Grady, Emily Savarese, Tatiana Kapos, Nancy Tran
 */
public class Stage4 extends Stage{
	private Board board;
	private GridPane grid;
	private List<Enemy> wave1;
	private List<Enemy> wave2;
	private List<Enemy> wave3;
	private int curWave;

	/**
	 * Stage4 Constructor - creates a new board with the given map and sets up the waves.
	 */
	public Stage4() {
		this.board = new Board("map4.txt");
		this.curWave = 0;
		setGrid();
		setWave1();
		setWave2();
		setWave3();
	}

	/**
	 * Sets up the GUI view of the board
	 *  1 = wall
	 *  4 = water
	 *  0 = grass
	 */
	private void setGrid() {
		this.grid = new GridPane();
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
				else if (board.grabPosition(row, col) == 0) {
					// Grass
					Image imgGrass = new Image("file:grass.png");
					ImageView imgview = new ImageView(imgGrass);
					imgview.setFitHeight(50);
					imgview.setFitWidth(50);
					grid.add(imgview, col, row);
				}
			}
		}
	}

	/**
	 * Adds enemies to wave 1: 
	 * 3 default enemies
	 * 3 flying enemies
	 * 4 speed/water enemies
	 * 1 tank
	 */
	private void setWave1() { 
		this.wave1 = new ArrayList<Enemy>();
		Random rand = new Random();
		int randRow = 0;
		int count = 0;
		while(count < 4) {
			randRow = rand.nextInt(10);
			if(!board.checkForWater(randRow)) {
				// Default Enemy
				Enemy currEnemy = new Enemy(5, 5000, 40, "file:Wizard.png");
				if(count == 0) {
					// Tank Enemy
					currEnemy = new Enemy(10,3000,80,"file:Knight.png");
				}
				currEnemy.setRow(randRow);
				wave1.add(currEnemy);
				count++;
			}
		}
		while(count < 7) {
			randRow = rand.nextInt(10);
			Enemy currEnemy = new Enemy(5,3000,60,"file:Ghost.png");
			currEnemy.setRow(randRow);
			wave1.add(currEnemy);
			count++;

		}
		while(count < 11) {
			randRow = rand.nextInt(10);
			Enemy currEnemy = new Enemy(5, 2000, 40, "file:Ninja.png");
			if(board.checkForWater(randRow)) {
				// Water Enemy
				currEnemy = new Enemy(5,3000,40,"file:Mermaid.png");
			}
			currEnemy.setRow(randRow);
			wave1.add(currEnemy);
			count++;
		}

		Collections.shuffle(wave1);
	}

	/** 
	 * Adds enemies to wave 2:
	 * 3 default enemies
	 * 2 speed enemies
	 * 2 tank enemies
	 * 2 boss enemies
	 */
	private void setWave2() {
		this.wave2 = new ArrayList<Enemy>();
		Random rand = new Random();
		int randRow = 0;
		int count = 0;
		while(count < 7) {
			randRow = rand.nextInt(10);
			if(!board.checkForWater(randRow)) {
				// Default Enemy
				Enemy currEnemy = new Enemy(5, 5000, 40, "file:Wizard.png");
				if(count < 2) {
					// Tank Enemy
					currEnemy = new Enemy(10,3000,80,"file:Knight.png");
				}else if(count < 4) {
					// Boss Enemy
					currEnemy = new Enemy(20,5000,200,"file:Boss.png");
				}
				currEnemy.setRow(randRow);
				wave2.add(currEnemy);
				count++;
			}
		}
		while(count < 9) {
			randRow = rand.nextInt(10);
			Enemy currEnemy = new Enemy(5, 2000, 40, "file:Ninja.png");
			currEnemy.setRow(randRow);
			wave2.add(currEnemy);
			count++;

		}
		Collections.shuffle(wave2);
	}

	/**
	 * Adds enemies to wave 3:
	 * 5 speed/water enemies
	 * 3 tank enemies
	 * 2 boss enemies
	 */
	private void setWave3() {
		this.wave3 = new ArrayList<Enemy>();
		Random rand = new Random();
		int randRow = 0;
		int count = 0;
		while(count < 5) {
			randRow = rand.nextInt(10);
			Enemy currEnemy = new Enemy(5, 2000, 40, "file:Ninja.png");
			if(board.checkForWater(randRow)) {
				// Water Enemy
				currEnemy = new Enemy(5,3000,40,"file:Mermaid.png");
			}
			currEnemy.setRow(randRow);
			wave3.add(currEnemy);
			count++;
		}
		while(count < 10) {
			randRow = rand.nextInt(10);
			if(!board.checkForWater(randRow)) {
				// Boss Enemy
				Enemy currEnemy = new Enemy(20,5000,200,"file:Boss.png");
				if(count < 8) {
					// Tank Enemy
					currEnemy = new Enemy(10,3000,80,"file:Knight.png");
				}
				currEnemy.setRow(randRow);
				wave3.add(currEnemy);
				count++;
			}
		}

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