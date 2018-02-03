package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import model.AcesUp;
import model.Card;
import model.Cardstack;
import model.Move;
import model.Rank;
import model.Suit;
import model.Type;


/**
 * @author Luise
 * this class tests the functionality of the AcesUpController
 * NOTE: the tests were executed successfully under the condition that in AcesUpController all lines are out-commented which communicate with the GUI or IO Controller 
 */
public class AcesUpControllerTest {
	
	private AcesUp acesUp;
	private AcesUpController acesUpController;
	private PatienceController patienceController;
	private Move moveFromTalon;
	private Move moveToEliminiationStack;

	@Before
	public void setUp() throws Exception {
		this.patienceController = new PatienceController(); //setzt patience und erzeugt leere Controller
		this.patienceController.initController();
		this.acesUpController = this.patienceController.getAcesUpController();		
		
		Cardstack startStack = new Cardstack(Type.TALON);
		Collection<Card> cards = (new DeckController()).randomDeck(); // needs to be tested successfully
		startStack.addAll(cards);
		this.acesUp = new AcesUp(startStack);
		this.patienceController.getPatience().setGame(acesUp);
		
		this.moveFromTalon = new Move(acesUp.getTalon(), acesUp.getPlayingStacks()[1], 1);
		
		this.moveToEliminiationStack = new Move(acesUp.getPlayingStacks()[2], acesUp.getEliminationStack(),1);		
	}

	@Test
	public void testInitGame() {
		this.acesUpController.initGame();
		assertEquals("Durch das Initialisieren des Spiels muss das private Attribut game gesetzt sein", acesUp, this.acesUpController.getGame() );		
		assertEquals("Durch das Initialisieren des Spiels muss der Talon der ganze startStack sein, bei dem die obersten vier Karten entfernt wurden",48 , this.acesUpController.getGame().getTalon().size());
		assertEquals("Die Startzeit muss gemerkt worden sein",new Date().getTime() , this.acesUpController.getStartTime());
	}
	
	
	@Test
	public void testIsFinished() {
		Cardstack talon = new Cardstack(Type.TALON);
		Cardstack playing1 = new Cardstack(Type.PLAYINGSTACK);
		playing1.add(new Card(Rank.ACE, Suit.CLUB));
		Cardstack playing2 = new Cardstack(Type.PLAYINGSTACK);
		playing2.add(new Card(Rank.ACE, Suit.DIAMOND));
		Cardstack playing3 = new Cardstack(Type.PLAYINGSTACK);
		playing3.add(new Card(Rank.ACE, Suit.HEART));
		Cardstack playing4 = new Cardstack(Type.PLAYINGSTACK);
		playing4.add(new Card(Rank.ACE, Suit.SPADE));
		this.acesUpController.setGame((AcesUp)this.patienceController.getPatience().getGame());
		this.acesUpController.getGame().setTalon(talon);
		this.acesUpController.getGame().getPlayingStacks()[0] = playing1;
		this.acesUpController.getGame().getPlayingStacks()[1] = playing2;
		this.acesUpController.getGame().getPlayingStacks()[2] = playing3;
		this.acesUpController.getGame().getPlayingStacks()[3] = playing4;
		
		assertEquals(true,this.acesUpController.isFinished());
	}
	
	@Test
	public void testIsNotFinished() {
		this.acesUpController.setGame((AcesUp)this.patienceController.getPatience().getGame());
		this.acesUpController.drawCards();
		
		assertEquals(false,this.acesUpController.isFinished());
	}


	@Test
	public void testUndoMoveFromTalon() {
		this.acesUpController.setGame((AcesUp)this.patienceController.getPatience().getGame());
		this.acesUpController.drawCards();
		
		int talonSize = this.acesUpController.getGame().getTalon().size();
		Card onFirst = this.acesUpController.getGame().getPlayingStacks()[0].peek();
		Card onSecond = this.acesUpController.getGame().getPlayingStacks()[1].peek();
		Card onThird = this.acesUpController.getGame().getPlayingStacks()[2].peek();
		Card onFourth = this.acesUpController.getGame().getPlayingStacks()[3].peek();

		Cardstack talon = this.acesUpController.getGame().getTalon();
		
		this.acesUpController.undoMove();
		assertEquals("The Talon needs to have 4 cards more than before the undo",talonSize+4, this.acesUpController.getGame().getTalon().size());
		assertEquals("The Talon needs to have as topLevel card the card what was on the first plaing stack ([0]) before",
						this.acesUpController.getGame().getTalon().peek(), onFirst);
		assertEquals("The Talon needs to have as scnd topLevel card the card what was on the first plaing stack ([1]) before",
								this.acesUpController.getGame().getTalon().get(talon.size()-2), onSecond);
		assertEquals("The Talon needs to have as thrd topLevel card the card what was on the first plaing stack ([2]) before",
				this.acesUpController.getGame().getTalon().get(talon.size()-3), onThird);
		assertEquals("The Talon needs to have as frth topLevel card the card what was on the first plaing stack ([3]) before",
				this.acesUpController.getGame().getTalon().get(talon.size()-4), onFourth);
	}
	
	@Test
	public void testUndoMove() {
		this.acesUpController.setGame((AcesUp)this.patienceController.getPatience().getGame());
		this.acesUpController.drawCards();
		int elimSize = this.acesUpController.getGame().getEliminationStack().size();
		int stackSize = this.moveToEliminiationStack.getFrom().size();
		this.acesUpController.executeMove(moveToEliminiationStack);
		this.acesUpController.undoMove();
		assertEquals("after undo the size of elim should be like it was before.",elimSize, this.acesUpController.getGame().getEliminationStack().size());
		assertEquals("after undo the size of the playingstack should be like it was before.",stackSize, this.moveToEliminiationStack.getFrom().size());
	}

	
	/**
	 * tests if restartGame() resets all stacks 
	 */
	@Test
	public void testRestartGame() {
		this.acesUpController.setGame((AcesUp)this.patienceController.getPatience().getGame());
		this.acesUpController.drawCards();
		this.acesUpController.drawCards();
		this.acesUpController.executeMove(moveToEliminiationStack);
		
		int prevElimSize = this.acesUpController.getGame().getEliminationStack().size();
		
		this.acesUpController.restartGame();
		
		assertEquals("after restart the elim stack needs to be cleared.",0, this.acesUpController.getGame().getEliminationStack().size());
		for(Cardstack playingstack : this.acesUpController.getGame().getPlayingStacks()){
			assertEquals("after restart all playingstacks needs to be cleared and hold one card  which came from the initGame.",1, playingstack.size());
		}
		assertEquals("after restart the talon needs to be fully recreated and the first cards are drawn.", 48, this.acesUpController.getGame().getTalon().size());
	}
	
	/**
	 * tests if executeMove adjusts the affected stacks correctly and 
	 * the card-object actually moves from source to target
	 */
	@Test
	public void testExecuteMove() {
		this.acesUpController.setGame((AcesUp)this.patienceController.getPatience().getGame());
		this.acesUpController.drawCards();
		
		Cardstack prevTalon = this.acesUp.getTalon();
		int prevTalonSize = prevTalon.size();
		Card cardToMove = prevTalon.peek();
		Cardstack targetStack = moveFromTalon.getTo();
		int prevTargetSize = targetStack.size();		
		
		this.acesUpController.executeMove(moveFromTalon);

		assertEquals("The card which previous was the top of the Talon needs to be now the top of the TargetStack",cardToMove, acesUp.getPlayingStacks()[1].peek());
		assertEquals(prevTalonSize-1, this.acesUp.getTalon().size());
		assertEquals(prevTargetSize+1, this.acesUp.getPlayingStacks()[1].size());
	}	

	
	/**
	 * tests if drawCards() draws the correct number of cards from the talon and discards them correctly on the playingstacks
	 */
	@Test
	public void testDrawCards() {	
		// only tests for empty playing stacks and full talon if cards are discard
		this.acesUpController.setGame((AcesUp)this.patienceController.getPatience().getGame());
		this.acesUpController.drawCards();
		assertEquals("Draw cards needs to delete four cards from the talon and put them on top of the playing stacks", this.acesUpController.getGame().getStartStack().size()-4, this.acesUpController.getGame().getTalon().size());
		for(Cardstack playingStack: this.acesUpController.getGame().getPlayingStacks()){ 
			assertEquals(1,playingStack.size());
			}
	}

}
