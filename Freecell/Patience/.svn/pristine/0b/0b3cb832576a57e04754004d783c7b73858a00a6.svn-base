package controller;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


import aui.FreeCellAUI;
import model.Card;
import model.Cardstack;
import model.FreeCell;
import model.Move;
import model.Rank;
import model.Suit;
import model.Type;

public class FreeCellControllerTest {

	FreeCell game, game2;

	PatienceController patienceController;

	@Before
	public void setUp() throws Exception {
		game = new FreeCell(new DeckController().randomDeck());
		game2 = game;
		patienceController = new PatienceController();
		patienceController.initController();
		patienceController.getPatience().setGame(game);
		patienceController.getFreeCellController().setFreeCellAUI(new FreeCellAUI() {

			@Override
			public void refreshAll() {
				// TODO Auto-generated method stub

			}

			@Override
			public void initializeStacks() {
				// TODO Auto-generated method stub

			}

			@Override
			public void animateMove(Move move) {
				// TODO Auto-generated method stub

			}

			@Override
			public void openFinishedWindow(String string, int i) {
				// TODO Auto-generated method stub
				
			}
		});
		patienceController.getFreeCellController().initGame();

		// leere Spielfeld
		for (Cardstack stack : game.getPlayingStacks()) {
			stack.clear();

		}

	}

	@Test
	public void testIsDroppable() {
		Cardstack stack = new Cardstack(Type.DEFAULT);
		stack.add(new Card(Rank.THREE, Suit.CLUB));
		stack.add(new Card(Rank.TWO, Suit.DIAMOND));
		stack.add(new Card(Rank.ACE, Suit.CLUB));

		patienceController.getFreeCellController().setStackToDrag(stack);

		game.getPlayingStacks()[0].add(new Card(Rank.FOUR, Suit.DIAMOND));
		game.getPlayingStacks()[1].add(new Card(Rank.FOUR, Suit.CLUB));

		assertTrue(patienceController.getFreeCellController().isDroppable(game.getPlayingStacks()[0]));

		assertFalse(patienceController.getFreeCellController().isDroppable(game.getPlayingStacks()[1]));

		assertTrue(patienceController.getFreeCellController().isDroppable(game.getPlayingStacks()[2]));

	}

	@Test
	public void testIsDraggable() {
		// patienceController.getFreeCellController().initGame();
		game.getPlayingStacks()[0].add(new Card(Rank.ACE, Suit.SPADE));
		game.getPlayingStacks()[0].add(new Card(Rank.TWO, Suit.SPADE));
		game.getPlayingStacks()[0].add(new Card(Rank.ACE, Suit.HEART));

		System.out.println(game.getFreeCells()[0].toString());
		System.out.println(game.getFreeCells()[1].toString());
		System.out.println(game.getFreeCells()[2].toString());
		System.out.println(game.getFreeCells()[3].toString());

		boolean shouldBeRight = patienceController.getFreeCellController().isDraggable(game.getPlayingStacks()[0],
				game.getPlayingStacks()[0].get(1));
		assert shouldBeRight;

		shouldBeRight = patienceController.getFreeCellController().isDraggable(game.getPlayingStacks()[0],
				game.getPlayingStacks()[0].get(2));
		assert shouldBeRight;

		boolean shouldBeWrong = patienceController.getFreeCellController().isDraggable(game.getPlayingStacks()[0],
				game.getPlayingStacks()[0].get(0));
		assert !shouldBeWrong;
	}

	@Test
	public void testRestartGame() {
		patienceController.getFreeCellController().restartGame();

		for (int i = 0; i < game.getPlayingStacks().length; i++) {
			assertEquals(game.getPlayingStacks()[i], game2.getPlayingStacks()[i]);
		}

	}

	@Test
	public void testExecuteMove() {
		game.getPlayingStacks()[0].add(new Card(Rank.ACE, Suit.SPADE));
		game.getPlayingStacks()[0].add(new Card(Rank.TWO, Suit.SPADE));
		game.getPlayingStacks()[0].add(new Card(Rank.ACE, Suit.HEART));

		Card cardToCompare = game.getPlayingStacks()[0].peek();

		// to Playingstack
		
		Move tmpMove = new Move(game.getPlayingStacks()[0], game.getPlayingStacks()[1], 1);
		assertEquals(0, game.getHistory().size());
		patienceController.getFreeCellController().executeMove(tmpMove);
		assertEquals(cardToCompare, game.getPlayingStacks()[1].peek());
		assertEquals(1, game.getHistory().size());
		patienceController.getFreeCellController().undoMove();
		
		// to Freeceel

		tmpMove = new Move(game.getPlayingStacks()[0], game.getFreeCells()[1], 1);
		assertEquals(0, game.getHistory().size());
		patienceController.getFreeCellController().executeMove(tmpMove);
		assertEquals(cardToCompare, game.getFreeCells()[1].peek());
		assertEquals(1, game.getHistory().size());
		patienceController.getFreeCellController().undoMove();
		
		// to Homeceel

		tmpMove = new Move(game.getPlayingStacks()[0], game.getHomeCells()[1], 1);
		assertEquals(0, game.getHistory().size());
		patienceController.getFreeCellController().executeMove(tmpMove);
		assertEquals(cardToCompare, game.getHomeCells()[1].peek());
		assertEquals(1, game.getHistory().size());

	}

	@Test
	public void testUndoMove() {
		game.getPlayingStacks()[0].add(new Card(Rank.ACE, Suit.SPADE));
		game.getPlayingStacks()[0].add(new Card(Rank.TWO, Suit.SPADE));
		game.getPlayingStacks()[0].add(new Card(Rank.ACE, Suit.HEART));
		Card cardToCompare = game.getPlayingStacks()[0].peek();

		// to Playingstacks
		Move tmpMove = new Move(game.getPlayingStacks()[0], game.getPlayingStacks()[1], 1);
		patienceController.getFreeCellController().executeMove(tmpMove);
		assertNotEquals(game.getPlayingStacks()[0].peek(), cardToCompare);
		patienceController.getFreeCellController().undoMove();
		assertEquals(game.getPlayingStacks()[0].peek(), cardToCompare);
		assertEquals(0, game.getHistory().size());

		// to Freecells
		tmpMove = new Move(game.getPlayingStacks()[0], game.getFreeCells()[1], 1);
		patienceController.getFreeCellController().executeMove(tmpMove);
		assertEquals(game.getFreeCells()[1].peek(), cardToCompare);
		patienceController.getFreeCellController().undoMove();
		assertEquals(game.getFreeCells()[1], new Cardstack(Type.FREECELL));
		assertEquals(0, game.getHistory().size());

		// to Homecells
		tmpMove = new Move(game.getPlayingStacks()[0], game.getHomeCells()[1], 1);
		patienceController.getFreeCellController().executeMove(tmpMove);
		assertEquals(game.getHomeCells()[1].peek(), cardToCompare);
		patienceController.getFreeCellController().undoMove();
		assertEquals(game.getHomeCells()[1], new Cardstack(Type.FREECELL));
		assertEquals(0, game.getHistory().size());

	}
}
