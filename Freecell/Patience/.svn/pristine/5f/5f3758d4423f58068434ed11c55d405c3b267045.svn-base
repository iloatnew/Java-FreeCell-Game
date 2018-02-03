package aicontroller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import aui.FreeCellAUI;
import controller.AutoMoveController;
import controller.DeckController;
import controller.FreeCellController;
import controller.PatienceController;
import model.Card;
import model.Cardstack;
import model.FreeCell;
import model.Move;
import model.Rank;
import model.Suit;

public class FreeCellAITest {

	FreeCell game, game2;

	PatienceController patienceController;
	FreeCellController freeCellController;
	FreeCellAI freeCellAI;
	
	@Before
	public void setUp() throws Exception {
		
		game = new FreeCell(new DeckController().randomDeck());
		game2 = game;
		patienceController = new PatienceController();
		patienceController.initController();
		patienceController.getPatience().setGame(game);
		setupAI();
		patienceController.getFreeCellController().initGame();
		freeCellController	= patienceController.getFreeCellController();
		freeCellAI = new FreeCellAI(null);
		freeCellAI.setGame(game);
		

		// leere Spielfeld
		for (Cardstack stack : game.getPlayingStacks()) {
			stack.clear();

		}
		 
		
	}

	private void setupAI() {
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
	}

	@Test
	public void testMoveAces() {
	
		Card tmpCard = new Card(Rank.ACE, Suit.CLUB);
		game.getPlayingStacks()[0].add(tmpCard);
		
		assertEquals(0, freeCellAI.getMoves().size());
		
		freeCellAI.moveAces();
		
		assertEquals(1, freeCellAI.getMoves().size());
		
	}
	
	 
	@Test
	public void testDifferentSuitCardAll()
	{
		Card tmpCard = new Card(Rank.ACE, Suit.CLUB);
		game.getPlayingStacks()[0].add(tmpCard);
		tmpCard = new Card(Rank.TWO, Suit.DIAMOND);
		game.getPlayingStacks()[1].add(tmpCard);
		
		assertTrue(freeCellAI.differentColorAndRank());
		
	}

	
	@Test
	public void testIsMovePossible()
	{
		Card tmpCard;
		setupStack();
		
		
		assertTrue(freeCellAI.isMovePossible());
		
		
		for(Cardstack stack: game.getPlayingStacks())
		{
			stack.clear();
		}
		
		setupStackSecondTime();
		
		
		tmpCard = new Card(Rank.NINE, Suit.DIAMOND);
		game.getFreeCells()[0].add(tmpCard);
		tmpCard = new Card(Rank.TEN, Suit.DIAMOND);
		game.getFreeCells()[1].add(tmpCard);
		tmpCard = new Card(Rank.KING, Suit.DIAMOND);
		game.getFreeCells()[2].add(tmpCard);
		tmpCard = new Card(Rank.QUEEN, Suit.DIAMOND);
		game.getFreeCells()[3].add(tmpCard);
		
		
		assertFalse(freeCellAI.isMovePossible());
		
		
	}

	private void setupStackSecondTime() {
		Card tmpCard;
		tmpCard = new Card(Rank.JACK, Suit.DIAMOND);
		game.getPlayingStacks()[0].add(tmpCard);
		tmpCard = new Card(Rank.TWO, Suit.DIAMOND);
		game.getPlayingStacks()[1].add(tmpCard);
		tmpCard = new Card(Rank.THREE, Suit.DIAMOND);
		game.getPlayingStacks()[2].add(tmpCard);
		tmpCard = new Card(Rank.FOUR, Suit.DIAMOND);
		game.getPlayingStacks()[3].add(tmpCard);
		tmpCard = new Card(Rank.FIVE, Suit.DIAMOND);
		game.getPlayingStacks()[4].add(tmpCard);
		tmpCard = new Card(Rank.SIX, Suit.DIAMOND);
		game.getPlayingStacks()[5].add(tmpCard);
		tmpCard = new Card(Rank.SEVEN, Suit.DIAMOND);
		game.getPlayingStacks()[6].add(tmpCard);
		tmpCard = new Card(Rank.EIGHT, Suit.DIAMOND);
		game.getPlayingStacks()[7].add(tmpCard);
	}

	private void setupStack() {
		Card tmpCard = new Card(Rank.ACE, Suit.CLUB);
		game.getPlayingStacks()[0].add(tmpCard);
		tmpCard = new Card(Rank.TWO, Suit.DIAMOND);
		game.getPlayingStacks()[1].add(tmpCard);
		tmpCard = new Card(Rank.THREE, Suit.CLUB);
		game.getPlayingStacks()[2].add(tmpCard);
		tmpCard = new Card(Rank.FOUR, Suit.DIAMOND);
		game.getPlayingStacks()[3].add(tmpCard);
		tmpCard = new Card(Rank.FIVE, Suit.CLUB);
		game.getPlayingStacks()[4].add(tmpCard);
		tmpCard = new Card(Rank.SIX, Suit.DIAMOND);
		game.getPlayingStacks()[5].add(tmpCard);
		tmpCard = new Card(Rank.SEVEN, Suit.CLUB);
		game.getPlayingStacks()[6].add(tmpCard);
		tmpCard = new Card(Rank.EIGHT, Suit.DIAMOND);
		game.getPlayingStacks()[7].add(tmpCard);
	}
	
}
