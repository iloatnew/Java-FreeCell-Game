package controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import application.ZankGUIController;
import controller.DeckController;
import controller.PatienceController;
import controller.ZankController;
import javafx.fxml.FXMLLoader;
import model.Cardstack;
import model.Move;
import model.Player;
import model.Zank;

public class ZankControllerTest {

	PatienceController pCtrl;
	ZankController zCtrl;
	Zank game;

	@Before
	public void setUp() throws Exception {
		pCtrl = new PatienceController();
		pCtrl.initController();
		zCtrl = pCtrl.getZankController();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Zank.fxml"));
		fxmlLoader.load();
		ZankGUIController gui = fxmlLoader.getController();
		gui.setPatienceController(pCtrl);
		gui.init();
		game = new Zank(new DeckController().randomDeck(), new DeckController().randomDeck(), Player.PLAYER,
				Player.PLAYER, true);
		pCtrl.getPatience().setGame(game);
	}

	@Test
	public void initGameTest() {
		zCtrl.initGame();

		for (Cardstack stack : game.getPlayingStacks()) {
			assertEquals("Stack must have size 1", stack.size(), 1);
		}
		assertEquals("Stack must have size 13", game.getReserves()[0].size(), 13);
		assertEquals("Stack must have size 13", game.getReserves()[1].size(), 13);
		assertTrue("Stack must be empty", game.getWastePiles()[0].isEmpty());
		assertTrue("Stack must be empty", game.getWastePiles()[1].isEmpty());
		assertEquals("Stack must have size 35", game.getHands()[0].size(), 35);
		assertEquals("Stack must have size 35", game.getHands()[1].size(), 35);
	}

	@Test
	public void isFinishedTest() {
		zCtrl.initGame();
		assertTrue("Cant be finished", !zCtrl.isFinished());

		game.getHands()[0].removeAllElements();
		game.getReserves()[0].removeAllElements();
		game.getWastePiles()[0].removeAllElements();

		assertTrue("Must be finished", zCtrl.isFinished());
	}

	@Test
	public void undoMoveTest() {
		zCtrl.initGame();
		Move testmove = new Move(game.getPlayingStacks()[0], game.getPlayingStacks()[0], 1);
		zCtrl.executeMove(testmove);
		int before = game.getHistory().size();
		zCtrl.undoMove();
		int after = game.getHistory().size();
		assertEquals("before must be 1", before, 1);
		assertEquals("after must be 0", after, 0);
	}

	@Test
	public void switchPlayerTest() {
		zCtrl.initGame();
		int before = zCtrl.getActivePlayer();
		zCtrl.switchPlayer();
		int after = zCtrl.getActivePlayer();
		assertEquals("before must be 0", before, 0);
		assertEquals("after must be 2", after, 1);
	}

	@Test
	public void watePileToHandTest() {
		zCtrl.initGame();
		game.getWastePiles()[0].addAll(game.getHands()[0].pop(game.getHands()[0].size()));
		zCtrl.wastePileToHand();
		assertEquals("hand must be full", game.getHands()[0].size(), 35);
	}

}
