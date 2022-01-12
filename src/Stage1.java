import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
/**
 * @author Emily Savarese, Tatiana Kapos, Nancy Tran, Reagan O'Grady
 * Stage1 on Tower Defense, create enemies wave and map
 */

/**
 * Stage1 represents the 1st playable stage.
 * With unique enemy waves and a unique map.
 * 
 * @author Reagan O'Grady, Emily Savarese, Tatiana Kapos, Nancy Tran
 */
public class Stage1 {
	private Board board;
	private GridPane grid;
	private List<Enemy> wave1;
	private List<Enemy> wave2;
	private List<Enemy> wave3;
	private int curWave;

	/**
	 * Stage1 Constructor - creates a new board with the given map and sets up the waves.
	 */
	public Stage1() {
		this.board = new Board("map1.txt");
		this.curWave = 0;
		setGrid();
		setWave1();
		setWave2();
		setWave3();
	}

	/**
	 * Sets up the GUI view of the board
	 *  1 = wall
	 *  other = grass
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
				} else {
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
	 * 10 default enemies
	 */
	private void setWave1() {
		this.wave1 = new ArrayList<Enemy>();
		//ten basic enemies!
		//currently 5 for testing purposes
		for(int i=0; i<10; i++) {
			Random rand = new Random();
			int randRow = rand.nextInt(10);
			// Default Enemy
			Enemy currEnemy = new Enemy(5, 5000, 40, "file:Wizard.png");
			currEnemy.setRow(randRow);
			wave1.add(currEnemy);
		}
		Collections.shuffle(wave1);
	}

	/**
	 * Adds enemies to wave 2: 
	 * 6 default enemies
	 * 4 speed enemies
	 */
	private void setWave2() {
		this.wave2 = new ArrayList<Enemy>();
		for(int i = 0; i<6; i++) {
			Random rand = new Random();
			int randRow = rand.nextInt(10);
			// Default Enemy
			Enemy currEnemy = new Enemy(5, 5000, 40, "file:Wizard.png");
			currEnemy.setRow(randRow);
			wave2.add(currEnemy);
		}
		
		for(int j = 0; j<4; j++) {
			Random rand = new Random();
			int randRow = rand.nextInt(10);
			// Speed Enemy
			Enemy currEnemy = new Enemy(5, 2000, 40, "file:Ninja.png");
			currEnemy.setRow(randRow);
			wave2.add(currEnemy);
		}
		Collections.shuffle(wave2);
	}

	/**
	 * Adds enemies to wave 3: 
	 * 5 default enemies
	 * 3 speed enemies
	 * 2 tank
	 */
	private void setWave3() {
		this.wave3 = new ArrayList<Enemy>();
		for(int i = 0; i<5; i++) {
			Random rand = new Random();
			int randRow = rand.nextInt(10);
			// Default Enemy
			Enemy currEnemy = new Enemy(5, 5000, 40, "file:Wizard.png");
			currEnemy.setRow(randRow);
			wave3.add(currEnemy);
		}
		
		for(int j = 0; j<3; j++) {
			Random rand = new Random();
			int randRow = rand.nextInt(10);
			// Speed Enemy
			Enemy currEnemy = new Enemy(5, 2000, 40, "file:Ninja.png");
			currEnemy.setRow(randRow);
			wave3.add(currEnemy);
		}
		
		for(int k = 0; k<2; k++) {
			Random rand = new Random();
			int randRow = rand.nextInt(10);
			// Tank Enemy
			 Enemy currEnemy = new Enemy(10,3000,80,"file:Knight.png");
			currEnemy.setRow(randRow);
			wave3.add(currEnemy);
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
