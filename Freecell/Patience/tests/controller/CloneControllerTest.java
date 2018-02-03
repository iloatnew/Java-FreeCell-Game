package controller;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.AcesUp;
import model.Card;
import model.Cardstack;
import model.Move;
import model.Rank;
import model.SingleplayerStatistics;
import model.Suit;
import model.Type;

public class CloneControllerTest {
	
	
	private AcesUp ausgangsAcesUp;
	private Stack<Move> ausgangsHistory; //testCloneHistory
	private Cardstack[] ausgangsPlayingstacks; //testCloneCardstacks
	private Cardstack playingStack, eliminationStack; //testCloneCardstacks
	private Cardstack ausgangsStack; //testCloneCardstack
	private SingleplayerStatistics ausgangsStatistics;//testCloneSingleplayerStatistics
	
	@Before
	public void setUp() throws Exception {
		ausgangsStack = new Cardstack(Type.DEFAULT);
		ausgangsStack.add(new Card(Rank.ACE, Suit.CLUB));
		ausgangsStack.add(new Card(Rank.EIGHT, Suit.CLUB));
		ausgangsStatistics = new SingleplayerStatistics(1, 2, 3);
		
		ausgangsAcesUp = new AcesUp(ausgangsStack);
		ausgangsPlayingstacks = new Cardstack[2];
		ausgangsPlayingstacks[0] = new Cardstack(Type.PLAYINGSTACK);
		ausgangsPlayingstacks[1] = new Cardstack((Type.PLAYINGSTACK));
		ausgangsPlayingstacks[0].add(new Card(Rank.ACE, Suit.CLUB));
		ausgangsPlayingstacks[1].add(new Card(Rank.EIGHT, Suit.CLUB));
		ausgangsPlayingstacks[1].add(new Card(Rank.FIVE, Suit.CLUB));
		
		playingStack = new Cardstack(Type.PLAYINGSTACK);
		eliminationStack = new Cardstack(Type.ELIMINATIONSTACK);
		ausgangsHistory = new Stack<Move>();
		ausgangsHistory.add(new Move(playingStack, eliminationStack, 1));
		ausgangsHistory.add(new Move(eliminationStack, playingStack, 1));
	}

	@Test
	public void testCloneCardstacks() {
		Cardstack[] testStack = CloneController.cloneCardstacks(ausgangsPlayingstacks);
		for(int i = 0; i < testStack.length; i++){
			while(ausgangsPlayingstacks[i].size() != 0){
				Card ausgangsCard = ausgangsPlayingstacks[i].pop();
				assertEquals("",ausgangsCard, testStack[i].pop());
			}
		}
	}
	
	@Test
	public void testCloneHistory() {
		Stack<Move> cloneStack = (Stack<Move>) ausgangsHistory.clone();
		assertEquals("", ausgangsHistory.pop(), cloneStack.pop());
		assertEquals("", ausgangsHistory.pop(), cloneStack.pop());
	}
	
	@Test
	public void testCloneCardstack() {
		Cardstack testStack = CloneController.cloneCardstack(ausgangsStack);
		assertEquals("", ausgangsStack.pop(), testStack.pop());
		assertEquals("", ausgangsStack.pop(), testStack.pop());
	}

	@Test
	public void testCloneSingleplayerStatistics(){
		SingleplayerStatistics testStatistics = CloneController.cloneSingleplayerStatistics(ausgangsStatistics);
		assertEquals("", ausgangsStatistics.getNeededTime(), testStatistics.getNeededTime());
		assertEquals("", ausgangsStatistics.getNumberEliminatedCards(), testStatistics.getNumberEliminatedCards());
		assertEquals("", ausgangsStatistics.getNumberMoves(), testStatistics.getNumberMoves());
	}
}
