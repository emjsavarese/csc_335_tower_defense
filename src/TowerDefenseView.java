import java.util.ArrayList;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Creates and handles the GUI for the Tower Defense Game
 * 
 * @author Reagan O'Grady, Emily Savarese, Tatiana Kapos, Nancy Tran
 */
public class TowerDefenseView extends Application{
	private TowerDefenseModel model = new TowerDefenseModel();

	private TowerDefenseController controller = new TowerDefenseController(model);
	private int canBuy = 0;
	private boolean sellTower = false;
	private boolean checkTower = false;
	private int stage = 1;
	private boolean fastforward = false;
	//change this to be dynamic when stages connected to 
	//controller
	private int waveNum = 0;
	private List<Tower> towers = new ArrayList<Tower>();
	private List<Enemy> enemies = new ArrayList<Enemy>();
	private List<Enemy> activeEnemies = new ArrayList<Enemy>();
	//needed for pause/play
	Timeline t;
	private List<Timeline> timelines = new ArrayList<Timeline>();
	private List<Animation> animations = new ArrayList<Animation>();
	private List<PathTransition> paths = new ArrayList<PathTransition>();

	private BorderPane window = new BorderPane();
	private GridPane board = new GridPane();
	private Scene scene;
	private Text money = new Text(Integer.toString(controller.getMoney()));
	private Text health = new Text(Integer.toString(controller.getHealth()));
	private Text wave = new Text(Integer.toString(waveNum));

	private Stage3 stage3;
	private Stage1 stage1;
	private Stage2 stage2;
	private Stage4 stage4; 
	
	/**
	 * Starts up the GUI - creates all necessary buttons as well as 
	 * listeners to handle when they are pressed.
	 */
	@Override
	public void start(Stage arg0) throws Exception {
		//right side
		final ToggleGroup group = new ToggleGroup();
		RadioButton rb1 = new RadioButton("Stage 1");
		RadioButton rb2 = new RadioButton("Stage 2");
		final RadioButton rb3 = new RadioButton("Stage 3");
		RadioButton rb4 = new RadioButton("Stage 4");
		rb1.setToggleGroup(group);
		rb2.setToggleGroup(group);
		rb3.setToggleGroup(group);
		rb4.setToggleGroup(group);
		rb1.selectedProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(javafx.beans.Observable arg0) {
				if (rb1.isSelected()) {
					stopGame();
					model = new TowerDefenseModel();
					controller = new TowerDefenseController(model);
					stage1 = new Stage1();
					controller.setBoard(stage1.getBoard());
					board = stage1.getGrid();
					window.setCenter(board);
					board.setGridLinesVisible(true);
					stage = 1;
					setBoard(board, arg0);
				}
			}
		});
		rb1.setSelected(true);
		rb2.selectedProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(javafx.beans.Observable arg0) {
				if (rb2.isSelected()) {
					stopGame();
					model = new TowerDefenseModel();
					controller = new TowerDefenseController(model);
					stage2 = new Stage2();
					controller.setBoard(stage2.getBoard());
					board = stage2.getGrid();
					window.setCenter(board);
					board.setGridLinesVisible(true);
					stage = 2;
					setBoard(board, arg0);
					
				}
			}
		});
		rb3.selectedProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(javafx.beans.Observable arg0) {
				if (rb3.isSelected()) {
					stopGame();
					model = new TowerDefenseModel();
					controller = new TowerDefenseController(model);
					stage3 = new Stage3();
					controller.setBoard(stage3.getBoard());
					board = stage3.getGrid();
					window.setCenter(board);
					board.setGridLinesVisible(true);
					stage = 3;
					setBoard(board, arg0);
					
				}
			}

		});
		rb4.selectedProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(javafx.beans.Observable arg0) {
				if (rb4.isSelected()) {
					stopGame();
					model = new TowerDefenseModel();
					controller = new TowerDefenseController(model);
					stage4 = new Stage4();
					controller.setBoard(stage4.getBoard());
					board = stage4.getGrid();
					window.setCenter(board);
					board.setGridLinesVisible(true);
					stage = 4;
					setBoard(board, arg0);
					
				}
			}
		});
		HBox stages = new HBox(rb1,rb2,rb3,rb4);
		stages.setSpacing(10);
		window.setBottom(stages);
		scene = new Scene(window);
		arg0.setTitle("Tower Defense Game!");
		arg0.setScene(scene);
		arg0.show();

	}	
	
	/**
	 * Creates the Help dialogue box.
	 * Shows the user all available towers, their 
	 * stats, and a little information on how to 
	 * use the tower itself.
	 */
	public void createHelp() {
		Image tower1 = new Image("file:tower2.png");
		ImageView tower1View = new ImageView(tower1);
		tower1View.setFitHeight(30);
		tower1View.setFitWidth(30);
		Label tow1 = new Label(": Default Tower. 15 health, 10 damage. Good to start.");
		HBox t1 = new HBox(tower1View, tow1);
		
		Image tower2 = new Image("file:tower6.png");
		ImageView tower2View = new ImageView(tower2);
		tower2View.setFitHeight(30);
		tower2View.setFitWidth(30);
		Label tow2 = new Label(": Tank Tower. 100 health. 10 damage. Great to defend with.");
		HBox t2 = new HBox(tower2View, tow2);
		
		Image tower3 = new Image("file:tower3.png");
		ImageView tower3View = new ImageView(tower3);
		tower3View.setFitHeight(30);
		tower3View.setFitWidth(30);
		Label tow3 = new Label(": Money Tower. 50 health. 0 damage. Creates 15 more money every tick.");
		HBox t3 = new HBox(tower3View, tow3);
		
		Image tower4 = new Image("file:tower5.png");
		ImageView tower4View = new ImageView(tower4);
		tower4View.setFitHeight(30);
		tower4View.setFitWidth(30);
		Label tow4 = new Label(": Speed Tower. 15 health. 10 damage. Shoots faster than the average tower.");
		HBox t4 = new HBox(tower4View, tow4);
		
		Image tower5 = new Image("file:tower1.png");
		ImageView tower5View = new ImageView(tower5);
		tower5View.setFitHeight(30);
		tower5View.setFitWidth(30);
		Label tow5 = new Label(": Barrier Tower. 50 health. 0 damage. A quick defence against intruders.");
		HBox t5 = new HBox(tower5View, tow5);
		
		Image tower6 = new Image("file:tower4.png");
		ImageView tower6View = new ImageView(tower6);
		tower6View.setFitHeight(30);
		tower6View.setFitWidth(30);
		Label tow6 = new Label(": Power Tower. 20 health. 20 damage. A better Speed Tower.");
		HBox t6 = new HBox(tower6View, tow6);
		
		Image tower7 = new Image("file:waterTower.png");
		ImageView tower7View = new ImageView(tower7);
		tower7View.setFitHeight(30);
		tower7View.setFitWidth(30);
		Label tow7 = new Label(": Water Tower. 30 health. 10 damage. A tower that can be placed on water.");
		HBox t7 = new HBox(tower7View, tow7);
		
		Image tower8= new Image("file:gate.png");
		ImageView tower8view = new ImageView(tower8);
		tower8view.setFitHeight(30);
		tower8view.setFitWidth(30);
		Label tow8 = new Label(": Gate. 50 health. 0 damage. To defend against water enemies.");
		HBox t8 = new HBox(tower8view, tow8);
		
		
		VBox fullGraphic = new VBox(t1, t2, t3, t4, t5, t6, t7,t8);
		Alert help = new Alert(AlertType.NONE, "", ButtonType.OK);
		help.setGraphic(fullGraphic);
		
		help.show();
	}	
	
	/**
	 * Sets up the game's board and handles when towers are being bought.
	 * Also handles all GUI Buttons
	 * @param board (GridPane) = the GUI board
	 * @param arg0 (Observable)
	 */
	public void setBoard(GridPane board,javafx.beans.Observable arg0) {
		board.setOnMouseClicked((e) -> {
			int[] position = getRec(e.getX(), e.getY());
			if(canBuy > 0) { // buys a tower
				if (controller.validTile(position[1], position[0])) {
					Tower tower;
					int row = position[0];
					int col = position[1];

					if(canBuy == 1) {
						//Default Tower
						tower = new Tower(15, "file:tower2.png", 10, row, col, "file:fireball1.png",25 );
					}else if(canBuy == 2){
						// Tank Tower
						tower = new Tower(100, "file:tower6.png", 10, row, col, "file:fireball3.png",30);
					}else if(canBuy == 3) {
						// Money Tower
						tower = new Tower(50, "file:tower3.png", 0, row, col, "file:coin.png",20);
						tower.setMoneyTow();
						tower.setSpeed(5000);
					}else if(canBuy == 4) {
						// Speed Tower
						tower = new Tower(15, "file:tower5.png", 10, row, col, "file:fireball5.png", 50);
						tower.setSpeed(500);
					}else if(canBuy == 5){
						// Barrier Tower
						tower =  new Tower(50, "file:tower1.png", 0, row, col, "file:fireball.png",5);
					}else if(canBuy == 6){
						// Power Tower
						tower =  new Tower(20, "file:tower4.png", 20, row, col, "file:fireball4.png", 100);
						tower.setSpeed(2000);
					}else if(canBuy == 7){
						// water Tower
						tower =  new Tower(30, "file:waterTower.png", 10, row, col, "file:fireball2.png", 25);
						tower.setWater();
					}else{
						// gate Tower
						tower =  new Tower(50, "file:gate.png", 0, row, col, "file:fireball.png", 5);
						tower.setWater();
					}

					boolean type = false;
					if((tower.getWater() && controller.getPos(col,row) == 4) || (!tower.getWater() && controller.getPos(col,row) == 0)) {
						type = true;
					}

					if(type) {
						towers.add(tower);
						controller.placeTower(tower.getRow(), tower.getCol());
						board.add(tower.getImageView(), position[0], position[1]);
						controller.buyTower(tower.getCost());
						money.setText(Integer.toString(controller.getMoney()));
						canBuy = 0;
						scene.setCursor(Cursor.DEFAULT);
					}else {
						Alert error = new Alert(AlertType.ERROR);
						error.setContentText("Can't place this type of tower here!");
						error.showAndWait();
					}
				}
				else { //error if board pos is not free
					Alert error = new Alert(AlertType.ERROR);
					error.setContentText("Board position is not free!");
					error.showAndWait();
				}
			}else if(checkTower) { //checks status of tower
				Tower towerA = null;
				for(Tower a: towers) {
					if(a.getRow() == position[1] && a.getCol() ==  position[0]) {
						checkTower = false;
						scene.setCursor(Cursor.DEFAULT);
						towerA = a;
					}
				}
				if(towerA == null) {
					System.out.println("No tower to check!");
				}else {
					Alert info = new Alert(AlertType.INFORMATION);
					info.setContentText("Health = " + towerA.getHealth() 
					+ "\nDamage = " + towerA.getDamage());
					info.showAndWait();
				}
			}else if(sellTower) { //sells tower
				Tower remove = null;
				for(Tower a: towers) {

					if(a.getRow() == position[1] && a.getCol() ==  position[0]) {
						a.hideImage();
						a.stopTower();
						sellTower = false;
						controller.addMoney(a.getCost()/2);
						money.setText(Integer.toString(controller.getMoney()));
						scene.setCursor(Cursor.DEFAULT);
						remove = a;
						controller.setPos(0, a.getRow(), a.getCol());
					}
				}
				if(remove == null) {
					System.out.println("No tower to remove!");
				}else {
					towers.remove(remove);
				}
			}else {
				System.out.println("first click tower");
			}
		});

		//left side
		Label moneyl = new Label("Coins: ");
		HBox moneyView = new HBox(moneyl,money);
		Label healthl = new Label("Health: ");
		HBox healthView = new HBox(healthl,health);
		Label wave1 = new Label("Wave: ");
		HBox waveView = new HBox(wave1, wave);
		VBox stats = new VBox(moneyView,healthView,waveView);
		VBox settings = new VBox(stats,controlSetting(), towers());
		settings.setSpacing(20);
		window.setLeft(settings);
		controlSetting();
	}

	/**
	 * Handles button clicks on towers
	 * Figures out if the player can purchase the tower selected
	 * @return Vbox
	 */
	private VBox towers() {
		EventHandler<ActionEvent> button = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{
				if(controller.canBuyTower(25)) {
					canBuy = 1;
					scene.setCursor(Cursor.HAND);
				}else {
					System.out.println("Not enough money!");
				}
			}
		};
		EventHandler<ActionEvent> tower2 = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{
				if(controller.canBuyTower(30)) {
					canBuy = 2;
					scene.setCursor(Cursor.HAND);
				}else {
					System.out.println("Not enough money!");
				}
			}
		};
		EventHandler<ActionEvent> tower3 = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{
				if(controller.canBuyTower(20)) {
					canBuy = 3;
					scene.setCursor(Cursor.HAND);
				}else {
					System.out.println("Not enough money!");
				}
			}
		};
		EventHandler<ActionEvent> tower4 = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{
				if(controller.canBuyTower(50)) {
					canBuy = 4;
					scene.setCursor(Cursor.HAND);
				}else {
					System.out.println("Not enough money!");
				}
			}
		};
		EventHandler<ActionEvent> tower5 = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{
				if(controller.canBuyTower(5)) {
					canBuy = 5;
					scene.setCursor(Cursor.HAND);
				}else {
					System.out.println("Not enough money!");
				}
			}
		};
		EventHandler<ActionEvent> tower6 = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{
				if(controller.canBuyTower(100)) {
					canBuy = 6;
					scene.setCursor(Cursor.HAND);
				}else {
					System.out.println("Not enough money!");
				}
			}
		};
		EventHandler<ActionEvent> tower7 = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{
				if(controller.canBuyTower(25)) {
					canBuy = 7;
					scene.setCursor(Cursor.HAND);
				}else {
					System.out.println("Not enough money!");
				}
			}
		};
		EventHandler<ActionEvent> tower8 = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{
				if(controller.canBuyTower(5)) {
					canBuy = 8;
					scene.setCursor(Cursor.HAND);
				}else {
					System.out.println("Not enough money!");
				}
			}
		};
		EventHandler<ActionEvent> remove = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{
				if(!towers.isEmpty()) {
					sellTower = true;
					scene.setCursor(Cursor.HAND);
				}else {
					System.out.println("No Towers to sell!");
				}
			}
		};
		EventHandler<ActionEvent> status = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{
				if(!towers.isEmpty()) {
					checkTower = true;
					scene.setCursor(Cursor.HAND);
				}else {
					System.out.println("No Towers to check!");
				}
			}
		};
		Image tower1 = new Image("file:tower2.png");
		ImageView tower1View = new ImageView(tower1);
		tower1View.setFitHeight(30);
		tower1View.setFitWidth(30);
		Button buyTower1 = new Button("(25)",tower1View);
		buyTower1.setOnAction(button);
		Image tower2img = new Image("file:tower6.png");
		ImageView tower2View = new ImageView(tower2img);
		tower2View.setFitHeight(30);
		tower2View.setFitWidth(30);
		Button buyTower2 = new Button("(30)", tower2View);
		buyTower2.setOnAction(tower2);
		Image tower3img = new Image("file:tower3.png");
		ImageView tower3view = new ImageView(tower3img);
		tower3view.setFitHeight(30);
		tower3view.setFitWidth(30);
		Button buyTower3 = new Button("(20)",tower3view);
		buyTower3.setOnAction(tower3);
		Image tower4img = new Image("file:tower5.png");
		ImageView tower4view = new ImageView(tower4img);
		tower4view.setFitHeight(30);
		tower4view.setFitWidth(30);
		Button buyTower4 = new Button("(50)",tower4view);
		buyTower4.setOnAction(tower4);
		Image tower5img = new Image("file:tower1.png");
		ImageView tower5view = new ImageView(tower5img);
		tower5view.setFitHeight(30);
		tower5view.setFitWidth(30);
		Button buyTower5 = new Button("(5)  ",tower5view);
		buyTower5.setOnAction(tower5);
		Image tower6img = new Image("file:tower4.png");
		ImageView tower6view = new ImageView(tower6img);
		tower6view.setFitHeight(30);
		tower6view.setFitWidth(30);
		Button buyTower6 = new Button("(100)", tower6view);
		buyTower6.setOnAction(tower6);

		Image tower7img = new Image("file:waterTower.png");
		ImageView tower7view = new ImageView(tower7img);
		tower7view.setFitHeight(30);
		tower7view.setFitWidth(30);
		Button buyTower7 = new Button("(25)",tower7view);
		buyTower7.setOnAction(tower7);
		Image tower8img = new Image("file:gate.png");
		ImageView tower8view = new ImageView(tower8img);
		tower8view.setFitHeight(30);
		tower8view.setFitWidth(30);
		Button buyTower8 = new Button("(5)  ",tower8view);
		buyTower8.setOnAction(tower8);



		Button sellTower = new Button("Sell Tower");
		sellTower.setOnAction(remove);
		Button checkTower = new Button("See Status");
		checkTower.setOnAction(status);
		Button help = new Button("Help");
		help.setOnAction((event)-> {
			createHelp();
		});
		return new VBox(buyTower1,buyTower2,buyTower3,buyTower4,buyTower5,buyTower6,buyTower7,buyTower8,sellTower,checkTower,help);
	}

	/**
	 * Returns the space on the board the mouse is at
	 * @param x (double) = the x position the mouse clicked
	 * @param y (double) = the y position the mouse clicked
	 * @return position
	 */
	private int[] getRec(double x, double y) {
		// Returns the tile where the user clicked
		int[] position = new int[2];
		position[0] = (int) (x/50); // Col Num
		position[1] = (int) (y/50); // Row Num
		return position;
	}

	/**
	 * Stops all game timelines, animations, and paths
	 */
	private void stopGame() {
		
		if(t != null) {
			t.stop();
		}
		for(Timeline t : timelines) {
			t.stop();
		}
		for(Animation a : animations) {
			a.stop();
		}
		for(PathTransition p : paths) {
			p.stop();
		}

		timelines = new ArrayList<Timeline>();
		animations = new ArrayList<Animation>();
		paths = new ArrayList<PathTransition>();
		canBuy = 0;
		sellTower = false;
		checkTower = false;
		fastforward = false;
		waveNum = 0;
		towers = new ArrayList<Tower>();
		enemies = new ArrayList<Enemy>();
		activeEnemies = new ArrayList<Enemy>();	
		money = new Text(Integer.toString(controller.getMoney()));
		health = new Text(Integer.toString(controller.getHealth()));
		wave = new Text(Integer.toString(waveNum));
	}

	/**
	 *  Handles start button and which wave should be played, 
	 *  as well as the pause, play, and fast forward buttons
	 * @return VBox
	 */
	private VBox controlSetting() {
		EventHandler<ActionEvent> startButton = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{
				//if there are no enemies left
				if(!checkEnemiesLeft()) {
					waveNum++;
					enemies = new ArrayList<Enemy>();
					activeEnemies = new ArrayList<Enemy>();

					if(stage == 1) {
						enemies = stage1.getCurWave();
					}else if(stage == 2){
						enemies = stage2.getCurWave();
					}else if(stage == 3) {
						enemies = stage3.getCurWave();
					}else if(stage == 4) {
						enemies = stage4.getCurWave();
					} 
					enemyWave();
					wave.setText(Integer.toString(waveNum));
				}

			}
		};

		EventHandler<ActionEvent> playButton = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{
				
				if(t != null) {
					t.play();
				}
				for(Timeline t : timelines) {
					t.play();
				}
				for(Animation a : animations) {
					a.play();
				}
				for(PathTransition p : paths) {
					p.play();
				}
			}
		};

		EventHandler<ActionEvent> pauseButton = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{
				System.out.println("pause");
				if(t != null) {
					t.pause();
				}
				for(Timeline t : timelines) {
					t.pause();
				}
				for(Animation a : animations) {
					a.pause();
				}
				for(PathTransition p : paths) {
					p.pause();
				}
			}
		};

		EventHandler<ActionEvent> fastForwardButton = new EventHandler<ActionEvent>() { 
			public void handle(ActionEvent e) 
			{
				if (!fastforward) {
					fastforward = true;
					for (Timeline tl: timelines) {
						tl.setRate(tl.getCycleDuration().toSeconds()*2);
					}
					for(Animation a : animations) {
						a.setRate(a.getCycleDuration().toSeconds()*2);
					}

					for(PathTransition p : paths) {
						p.setRate(p.getCycleDuration().toSeconds()*2);
					}

				}else{
					fastforward = false;
					for (Timeline tl: timelines) {
						tl.setRate(tl.getCycleDuration().toSeconds()/2);
					}
					for(Animation a : animations) {
						a.setRate(a.getCycleDuration().toSeconds()/2);
					}

					for(PathTransition p : paths) {
						p.setRate(p.getCycleDuration().toSeconds()/2);
					}


				}
			}
		};
		Button start = new Button("START");
		start.setPrefWidth(72);
		start.setOnAction(startButton);
		Image imgPlay = new Image("file:play.png");
		ImageView imgview = new ImageView(imgPlay);
		imgview.setFitHeight(17);
		imgview.setFitWidth(18);
		Button play = new Button("", imgview);
		play.setPrefWidth(36);
		play.setOnAction(playButton);
		Button pause = new Button(" || ");
		pause.setPrefWidth(36);
		pause.setOnAction(pauseButton);
		Button fastForward = new Button(">>");
		fastForward.setPrefWidth(72);
		fastForward.setOnAction(fastForwardButton);
		return new VBox(start, new HBox(play, pause), fastForward);
	}

	private static final int COLUMNS  =   4;
	private static final int COUNT    =  8;
	private static final int OFFSET_X =  512;
	private static final int OFFSET_Y =  512;
	private static final int WIDTH    = 512;
	private static final int HEIGHT   = 512;

	/**
	 * Creates a sprite animation
	 */
	public class SpriteAnimation extends Transition {

		private final ImageView imageView;
		private final int count;
		private final int columns;
		private final int offsetX;
		private final int offsetY;
		private final int height;
		private final int width;
		private final Enemy enemy;

		private int lastIndex;

		/**
		 * Constructor for sprite animation
		 * @param imageView (ImageView) = ImageView of the sprite
		 * @param duration (Duration) = duration of the sprite animation
		 * @param count (int) = how many images in the animation
		 * @param columns (int) = number of columns
		 * @param offsetX (int) = distance between each image (X)
		 * @param offsetY (int) = distance between each image (Y)
		 * @param width (int) = width of each image
		 * @param height (int) = height of each image
		 * @param enemy (Enemy) = enemy associatged w the image
		 */
		public SpriteAnimation(
				ImageView imageView, 
				Duration duration, 
				int count,   int columns,
				int offsetX, int offsetY,
				int width,   int height, Enemy enemy) {
			this.imageView = imageView;
			this.count     = count;
			this.columns   = columns;
			this.offsetX   = offsetX;
			this.offsetY   = offsetY;
			this.height    = height;
			this.width     = width;
			this.enemy     = enemy;
			setCycleDuration(duration);
			setInterpolator(Interpolator.LINEAR);
		}

		/**
		 * Moves through the sprite animation images
		 * @param k (double)
		 */
		protected void interpolate(double k) {
			final int index = Math.min((int) Math.floor(k * count), count - 1);
			if (index != lastIndex) {
				if(enemy.getFight()) {
					final int x = (index % columns) * offsetX;
					final int y = offsetY;
					imageView.setViewport(new Rectangle2D(x, y, width, height));
				} else {
					final int x = (index % columns) * offsetX;
					final int y = 0;
					imageView.setViewport(new Rectangle2D(x, y, width, height));
				}
				lastIndex = index;
			}
		}
	}

	/**
	 * Creates an animation for the given enemy
	 * @param imageView (ImageView) = imageView of the enemy
	 * @param enemy (Enemy) = enemy of the given animation
	 * @return animation
	 */
	private Animation createAnimation(ImageView imageView, Enemy enemy) {
		//sprite animation
		final Animation animation = new SpriteAnimation(
				imageView,
				Duration.millis(1500),
				COUNT, COLUMNS,
				OFFSET_X, OFFSET_Y,
				WIDTH, HEIGHT, enemy
				);
		animation.setCycleCount(animation.INDEFINITE);
		animations.add(animation);
		return animation;	

	}

	/**
	 * Creates a path transition
	 * @param imageView (ImageView) = imageView being moved
	 * @param oldCol (int) = x-coord being moved from
	 * @param newCol (int) = x-coord being moved to
	 * @param speed (int) = duration speed
	 * @return pathTransition
	 */
	private PathTransition createPath(ImageView imageView, int oldCol, int newCol, int speed) {
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(speed));
		pathTransition.setNode(imageView);
		pathTransition.setPath(newPath(25, oldCol, newCol));
		paths.add(pathTransition);
		return pathTransition;
	}

	/**
	 * Creates a new Path
	 * @param row (int) = y position
	 * @param oldCol (int) = x position moving from
	 * @param newCol (int) = x position moving to
	 * @return path
	 */
	private Path newPath(int row, int oldCol, int newCol) {
		Path path = new Path();
		path.getElements().add(new MoveTo(oldCol, row));
		path.getElements().add(new LineTo(newCol, row));
		return path;
	}

	/**
	 * Moves the enemies across the board from the rightmost side towards
	 * the left to attack the player
	 * @param enemy (Enemy) = Enemy instance being moved
	 */
	private void enemyMove(Enemy enemy) {
		final ImageView imageView = enemy.getImageView();
		imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, OFFSET_X, HEIGHT));

		board.add(imageView, 9, enemy.getRow());
		activeEnemies.add(enemy);

		final Animation animation = createAnimation(imageView, enemy);
		PathTransition pathTransition = createPath(imageView, 75, 25, enemy.getSpeed());
		paths.add(pathTransition);
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timelines.add(timeline);

		EventHandler<ActionEvent> movement = new EventHandler<ActionEvent>() {
			int oldSpot = 25;
			int newSpot = 25;

			@Override
			public void handle(ActionEvent t) {
				health.setText(Integer.toString(controller.getHealth()));
				if(enemy.getHealth() == 0 || !checkEnemiesLeft()) {
					activeEnemies.remove(enemy);
					enemy.hideImage();
					pathTransition.stop();
					animation.stop();
					timeline.stop();
				} else if(controller.getHealth() <= 0) {
					pathTransition.stop();
					animation.stop();
					timeline.stop();
				}else if(enemy.getCol()-1 == 0) {
					if(controller.getHealth() > 0) {
						enemy.startFight();
						controller.damageHealth(enemy.getAttack());
						health.setText(Integer.toString(controller.getHealth()));
					}
					if(controller.getHealth() <= 0) {
						pathTransition.stop();
						animation.stop();
						timeline.stop();
					}

				} else {
					if(!controller.validTile(enemy.getRow(), enemy.getCol()-1)){
						for(Tower tow: towers) {
							if(tow.getCol() == enemy.getCol()-1 && tow.getRow() == enemy.getRow()) {
								enemy.startFight();
								tow.attacked(enemy.getAttack());
								if(tow.getHealth() <= 0) {
									enemy.endFight();
									if(tow.getWater()) {
										controller.setPos(4, enemy.getRow(), enemy.getCol()-1);
									}else {
										controller.setPos(0, enemy.getRow(), enemy.getCol()-1);
									}
									tow.hideImage();
								}
								break;
							}
						}
					} 
					if(controller.validTile(enemy.getRow(), enemy.getCol()-1)){
						oldSpot = newSpot;
						newSpot -= 50;

						enemy.move();

						pathTransition.setPath(newPath(25, oldSpot, newSpot));
						pathTransition.play();
					}   
				}
			}
		};
		final KeyFrame kf = new KeyFrame(Duration.millis(enemy.getSpeed() + 30), movement);
		timeline.getKeyFrames().add(kf);
		animation.play();
		pathTransition.play();
		timeline.play();
	}

	/**
	 * Loops through the towers on the board to make them attack when 
	 * an enemy is in their lane
	 * @param tow (Tower) = tower instance on the board that is attacking
	 */
	private void towerAttack(Tower tow) {
		Image img = new Image(tow.getAttackImg());

		final ImageView imgView = new ImageView(img);
		imgView.setFitHeight(50);
		imgView.setFitWidth(50);
		imgView.setVisible(false);

		PathTransition pathTransition = createPath(imgView, 25,75,tow.getSpeed());
		Timeline timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		timelines.add(timeline);
		board.add(imgView, tow.getCol(), tow.getRow());
		EventHandler<ActionEvent> towerAttack = new EventHandler<ActionEvent>() {
			int oldSpot = 75;
			int newSpot = 75;
			Boolean startMoney = false;

			@Override
			public void handle(ActionEvent e) {
				if (tow.getHealth() <=0 || controller.getHealth() <= 0 || !checkEnemiesLeft()) {
					imgView.setVisible(false);
					pathTransition.stop();
					timeline.stop();
				}
				if(tow.getDamage() > 0) {
					for (Enemy enemy: activeEnemies) {
						if (enemy.getRow() == tow.getRow()) {
							// If there is an enemy in the tower's lane
							imgView.setVisible(true);

							if (tow.getAttack().returnCol() <= 9){		
								if(enemy.getCol() == tow.getAttack().returnCol() || (enemy.getCol() == tow.getAttack().returnCol() - 1)) {
									// If the tower's attack hits an enemy
									enemy.hurtHealth(tow.getDamage());
									tow.restartAttack();
									oldSpot = 25;
									newSpot = 25;

									if (enemy.getHealth() <= 0) {
										imgView.setVisible(false);
										enemy.hideImage();
									}
									pathTransition.setPath(newPath(25, oldSpot, newSpot));
									pathTransition.play();	
								}
								else {
									oldSpot = newSpot;
									newSpot += 50;
									if (enemy.getHealth() <= 0) {
										imgView.setVisible(false);
										enemy.hideImage();
									}

									pathTransition.setPath(newPath(25, oldSpot, newSpot));
									pathTransition.play();
									tow.getAttack().move();
								}
							}
							else {
								tow.restartAttack();
								oldSpot = 25;
								newSpot = 25;
								pathTransition.setPath(newPath(25, oldSpot, newSpot));
								pathTransition.play();
							}
						}

					}	
				}else if (tow.isMoneyTow()){
					// If money tower, add money to balance
					if (startMoney) {
						controller.addMoney(tow.generateMoney());
						money.setText(Integer.toString(controller.getMoney()));
					} else {
						startMoney = true;
					}
				}
			}
		};

		final KeyFrame kf = new KeyFrame(Duration.millis(tow.getSpeed() + 40), towerAttack);
		timeline.getKeyFrames().add(kf);

		pathTransition.play();
		timeline.play();
	}

	
	/**
	 * Starts the ActionEvents for the enemy movements and the 
	 * tower's attacks. Sets them to a timeline.
	 */
	private void enemyWave() {
		t = new Timeline();
		t.setCycleCount(Timeline.INDEFINITE);

		EventHandler<ActionEvent> movement = new EventHandler<ActionEvent>() {
			int stop = 0;
			int tick = 1;
			int enemyNum = 0;

			@Override
			public void handle(ActionEvent t) {
				if(controller.getHealth() <= 0) {
					stop++;
				}
				if(stop == 1) {
					Alert win = new Alert(AlertType.INFORMATION);
					win.setContentText("YOU LOSE! Better luck next time!");
					win.show();
					endWave(stop);
				}
				if(tick%2 ==0) {
					controller.addMoney(5);
					money.setText(Integer.toString(controller.getMoney()));
				}
				if(tick == 4) {
					if(enemyNum >= enemies.size() || controller.getHealth() <= 0) {
					}else {
						enemyMove(enemies.get(enemyNum));
						enemyNum++;
					}
				} else {
					tick++;
				}
				
				if (!checkEnemiesLeft()) {
					endWave(stop);
				}
			}
		};
		// change to KeyFrame(Duration.millis(5000)
		final KeyFrame kf = new KeyFrame(Duration.millis(1000), movement);
		EventHandler<ActionEvent> towerAttack = new EventHandler<ActionEvent>() {
			int towerIndex = 0;
			@Override
			public void handle(ActionEvent e) {
				if(towers.size() != 0 && towerIndex < towers.size()) {
					towerAttack(towers.get(towerIndex));
					towerIndex++;
				}
			}
		};
		// change to KeyFrame(Duration.millis(4000)
		final KeyFrame towKey = new KeyFrame(Duration.millis(1000), towerAttack);

		t.getKeyFrames().add(kf);
		t.getKeyFrames().add(towKey);
		t.play();
	}

	/**
	 * Stops all timelines when all enemies have been defeated or user loses.
	 * It stops the current t Timeline, as well as all timelines, paths,
	 * and animations. Also shows alerts when user has won or when user has
	 * completed a wave.
	 *
	 * @param stop, an int that if more than/equal to 0, hides all enemies on screen.
	 */
	private void endWave(int stop) {
		if (stop <= 0) {
			for (Enemy enemy: enemies) {
				enemy.hideImage();
			}
			if(waveNum ==3) {
				Alert win = new Alert(AlertType.INFORMATION);
				win.setContentText("Well done! You completed the final wave! You win!");
				win.show();
			}else {
				Alert win = new Alert(AlertType.INFORMATION);
				win.setContentText("Well done! You completed Wave " + (waveNum) + "! Click start for next wave!");
				win.show();
			}

		}
		t.stop();
		for(Timeline t : timelines) {
			t.stop();
		}
		for(Animation a : animations) {
			a.stop();
		}
		for(PathTransition p : paths) {
			p.stop();
		}
	}

	/**
	 * checks to see if there are enemies left alive.
	 * If an enemy is found with more than zero health,
	 * returns true.
	 * @return boolean value of enemies alive.
	 */
	private boolean checkEnemiesLeft() {
		for(Enemy enemy: enemies) {
			if (enemy.getHealth() > 0) {
				return true;
			}
		}
		return false;

	}

}