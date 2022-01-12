import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
/**
 * @author Emily Savarese, Tatiana Kapos, Nancy Tran, Reagan O'Grady
 * Stage2 on Tower Defense, create enemies wave and map
 */
public class Stage2 {
	private Board board;
	private GridPane grid;
	private List<Enemy> wave1;
	private List<Enemy> wave2;
	private List<Enemy> wave3;
	private int curWave;

	/**
	 * constructor of Stage2
	 */
	public Stage2() {
		this.board = new Board("map2");
		this.curWave = 0;
		setGrid();
		setWave1();
		setWave2();
		setWave3();
	}

	/**
	 * sets the grid of Stage2
	 */
	void setGrid() {
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
				else {
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
	 * Sets wave1 of Stage2
	 */
	private void setWave1() {
		this.wave1 = new ArrayList<Enemy>();
		Random rand = new Random();
		int randRow = 0;
		int count = 0;
		while(count < 5) {
			randRow = rand.nextInt(10);
			if(!board.checkForWater(randRow)) {
				// Default Enemy
				Enemy currEnemy = new Enemy(5, 5000, 40, "file:Wizard.png");
				currEnemy.setRow(randRow);
				wave1.add(currEnemy);
				count++;
			}
		}
		Collections.shuffle(wave1);
	}

	/**
	 * Sets wave2 of Stage2
	 */
	private void setWave2() {
		this.wave2 = new ArrayList<Enemy>();
		Random rand = new Random();
		int randRow = 0;
		int count = 0;
		while(count < 6) {
			randRow = rand.nextInt(10);
			if(!board.checkForWater(randRow)) {
				// Default Enemy
				Enemy currEnemy = new Enemy(5, 5000, 40, "file:Wizard.png");
				currEnemy.setRow(randRow);
				wave2.add(currEnemy);
				count++;
			}
		}
		while(count < 10) {
			randRow = rand.nextInt(10);
			// Speed Enemy
			Enemy currEnemy = new Enemy(5, 2000, 40, "file:Ninja.png");
			currEnemy.setRow(randRow);
			wave2.add(currEnemy);
			count++;
		}
		Collections.shuffle(wave2);
	}

	/**
	 * Sets wave3 of Stage2
	 */
	private void setWave3() {
		this.wave3 = new ArrayList<Enemy>();
		Random rand = new Random();
		int randRow = 0;
		int count = 0;
		while(count < 7) {
			randRow = rand.nextInt(10);
			if(!board.checkForWater(randRow)) {
				// Default Enemy
				Enemy currEnemy = new Enemy(5, 5000, 40, "file:Wizard.png");
				if(count < 2) {
					currEnemy = new Enemy(10,3000,80,"file:Knight.png");
				}
				currEnemy.setRow(randRow);
				wave3.add(currEnemy);
				count++;
			}
		}
		while(count < 10) {
			randRow = rand.nextInt(10);
			// Speed Enemy
			Enemy currEnemy = new Enemy(5, 2000, 40, "file:Ninja.png");
			currEnemy.setRow(randRow);
			wave3.add(currEnemy);
			count++;
		}

		Collections.shuffle(wave3);
	}

	/**
	 * returns the grid
	 * @return gridpane of grid
	 */
	public GridPane getGrid() {
		return grid;
	}

	/**
	 * returns the board
	 * @return board of game
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * gets the current enemy wave
	 * @return return of list of enemies of current wave
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
