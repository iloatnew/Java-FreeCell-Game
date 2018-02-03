package model;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

public class AcesUpTest {

	private AcesUp ausgangsAcesUp;
	private Stack<Move> ausgangsHistory; //testCloneHistory
	private Cardstack[] ausgangsPlayingstacks; //testCloneCardstacks
	private Cardstack playingStack, eliminationStack; //testCloneCardstacks
	private Cardstack ausgangsStack; //testCloneCardstack
	private SingleplayerStatistics ausgangsStatistics;//testCloneSingleplayerStatistics
	
	@Before
	public void setUp() throws Exception {
		ausgangsStack = new Cardstack(Type.TALON);
		ausgangsStack.add(new Card(Rank.ACE, Suit.CLUB));
		ausgangsStack.add(new Card(Rank.EIGHT, Suit.CLUB));
		ausgangsStatistics = new SingleplayerStatistics(1, 2, 3);
		
		ausgangsAcesUp = new AcesUp(ausgangsStack);
		ausgangsPlayingstacks = new Cardstack[4];
		for(int i = 0; i < ausgangsPlayingstacks.length; i++){
			ausgangsPlayingstacks[i] = new Cardstack(Type.PLAYINGSTACK);
		}
		ausgangsPlayingstacks[0].add(new Card(Rank.ACE, Suit.CLUB));
		ausgangsPlayingstacks[1].add(new Card(Rank.EIGHT, Suit.CLUB));
		ausgangsPlayingstacks[1].add(new Card(Rank.FIVE, Suit.CLUB));
		
		playingStack = new Cardstack(Type.PLAYINGSTACK);
		eliminationStack = new Cardstack(Type.ELIMINATIONSTACK);
		ausgangsHistory = new Stack<Move>();
		ausgangsHistory.add(new Move(playingStack, eliminationStack, 1));
		ausgangsHistory.add(new Move(eliminationStack, playingStack, 1));
		
		ausgangsAcesUp.setEliminationStack(new Cardstack(Type.ELIMINATIONSTACK));
		ausgangsAcesUp.setHistory(ausgangsHistory);
		ausgangsAcesUp.setPlayingStacks(ausgangsPlayingstacks);
		ausgangsAcesUp.setSpielzug(0);
		ausgangsAcesUp.setStartStack(ausgangsStack);
		ausgangsAcesUp.setStatistics(ausgangsStatistics);
		ausgangsAcesUp.setTalon(ausgangsStack);
		ausgangsAcesUp.setTime(9823498L);
	}


	@Test
	public void testClone() {
		AcesUp cloneUp = (AcesUp) ausgangsAcesUp.clone();
		//ints
		assertEquals("", ausgangsAcesUp.getCardsOnBoard(), cloneUp.getCardsOnBoard());
		assertEquals("", ausgangsAcesUp.getSpielzug(), cloneUp.getSpielzug());
		assertEquals("", ausgangsAcesUp.getTime(), cloneUp.getTime());
		//Objecte
		//System.out.println(System.identityHashCode(ausgangsAcesUp.getEliminationStack())+";"+System.identityHashCode(cloneUp.getEliminationStack()));
		assertNotEquals("", System.identityHashCode(ausgangsAcesUp.getEliminationStack()), System.identityHashCode(cloneUp.getEliminationStack()));
		for(int i = 0; i < ausgangsAcesUp.getEliminationStack().size(); i++){
			assertEquals("", ausgangsAcesUp.getEliminationStack().get(i), cloneUp.getEliminationStack().get(i));
		}
		assertNotEquals("", System.identityHashCode(ausgangsAcesUp.getHistory()), System.identityHashCode(cloneUp.getHistory()));
		for(int i = 0; i < ausgangsAcesUp.getHistory().size(); i++){
			assertEquals("", ausgangsAcesUp.getHistory().get(i), cloneUp.getHistory().get(i));
		}
		assertNotEquals("", System.identityHashCode(ausgangsAcesUp.getPlayingStacks()), System.identityHashCode(cloneUp.getHistory()));
		for(int i = 0; i < ausgangsAcesUp.getPlayingStacks().length; i++){
			for(int j = 0; j < ausgangsAcesUp.getPlayingStacks()[i].size(); j++){
				assertEquals("", ausgangsAcesUp.getPlayingStacks()[i].get(j), cloneUp.getPlayingStacks()[i].get(j));
			}
		}
		assertNotEquals("", System.identityHashCode(ausgangsAcesUp.getStartStack()), System.identityHashCode(cloneUp.getStartStack()));
		for(int i = 0; i < ausgangsAcesUp.getStartStack().size(); i++){
			assertEquals("", ausgangsAcesUp.getStartStack().get(i), cloneUp.getStartStack().get(i));
		}
		assertNotEquals("", System.identityHashCode(ausgangsAcesUp.getStatistics()), System.identityHashCode(cloneUp.getStatistics()));
		assertEquals("", ausgangsStatistics.getNeededTime(), cloneUp.getStatistics().getNeededTime());
		assertEquals("", ausgangsStatistics.getNumberEliminatedCards(), cloneUp.getStatistics().getNumberEliminatedCards());
		assertEquals("", ausgangsStatistics.getNumberMoves(), cloneUp.getStatistics().getNumberMoves());
		assertNotEquals("", System.identityHashCode(ausgangsAcesUp.getTalon()), System.identityHashCode(cloneUp.getTalon()));		
		for(int i = 0; i < ausgangsAcesUp.getTalon().size(); i++){
			assertEquals("", ausgangsAcesUp.getTalon().get(i), cloneUp.getTalon().get(i));
		}
	}

}
