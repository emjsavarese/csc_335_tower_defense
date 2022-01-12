import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class TDTestSuite {
	@Test
	public void testNotValidTile() {
		TowerDefenseModel model = new TowerDefenseModel();
		TowerDefenseController controller = new TowerDefenseController(model);
		controller.placeTower(controller.returnRows() - 1, controller.returnCols() - 1);
		assertFalse(controller.validTile(controller.returnRows() - 1, controller.returnCols() - 1));
	}
	
	@Test
	public void testCustomBoard() {
		TowerDefenseModel model = new TowerDefenseModel("map1.txt");
		TowerDefenseController controller = new TowerDefenseController(model);
		assertTrue(controller.validTile(1,1));
	}
	
	@Test
	public void testWaterBoard() {
		TowerDefenseModel model = new TowerDefenseModel("map3.txt");
		TowerDefenseController controller = new TowerDefenseController(model);
		assertTrue(controller.returnBoard().checkForWater(0));
		assertFalse(controller.returnBoard().checkForWater(2));
	}
	
	@Test
	public void buyTow() {
		TowerDefenseModel model = new TowerDefenseModel();
		TowerDefenseController controller = new TowerDefenseController(model);
		int money = controller.getMoney();
		assertTrue(controller.canBuyTower(money - 10));
	}
	
	
	@Test
	public void cantBuyTow() {
		TowerDefenseModel model = new TowerDefenseModel();
		TowerDefenseController controller = new TowerDefenseController(model);
		assertFalse(controller.canBuyTower(100000));
	}
	
	@Test
	public void moneyDepletes() {
		TowerDefenseModel model = new TowerDefenseModel();
		TowerDefenseController controller = new TowerDefenseController(model);
		controller.setMoney();
		int money = controller.getMoney();
		controller.buyTower(1);
		controller.addMoney(10); 
		assertEquals(controller.getMoney(), money + 9);
	}
	
	@Test
	public void setBoard() {
		TowerDefenseModel model = new TowerDefenseModel();
		TowerDefenseController controller = new TowerDefenseController(model);
		Board newBoard = new Board();
		controller.setBoard(newBoard);
		assertTrue(controller.returnBoard().equals(newBoard));
	}
	
	@Test
	public void damageHealth() {
		TowerDefenseModel model = new TowerDefenseModel();
		TowerDefenseController controller = new TowerDefenseController(model);
		controller.setHealth(100);
		controller.damageHealth(50);
		assertEquals(50, controller.getHealth());
	}
	
	@Test
	public void setAndGetPos() {
		TowerDefenseModel model = new TowerDefenseModel();
		TowerDefenseController controller = new TowerDefenseController(model);
		controller.setPos(10, 1, 1);
		assertEquals(controller.getPos(1, 1), 10);
	}
	
	@Test
	public void testTowerAttack() {
		TowerAttack tak = new TowerAttack("file:fireball1.png", 10, 0, 1);
		assertEquals(tak.getImage(), "file:fireball1.png");
		assertEquals(tak.returnRow(), 0);
		tak.move();
		assertEquals(tak.returnCol(), 2);
		tak.setCol(5);
		assertEquals(tak.returnCol(), 5);
	}
	
}
