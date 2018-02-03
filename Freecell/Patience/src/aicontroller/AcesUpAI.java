package aicontroller;

import java.util.ArrayList;

import exceptions.NoMovesPossibleException;
import exceptions.NotEnoughCardsOnPlaystacksException;
import model.AcesUp;
import model.Card;
import model.Cardstack;
import model.Move;


/**
 * @author Steven, Luise
 * The AcesUpAIController is the AI for AcesUp.
 */

/**
 * @author sopr045
 *
 */
/**
 * @author sopr045
 *
 */
/**
 * @author sopr045
 *
 */
public class AcesUpAI {
	
	private AcesUp acesUp;
	private Card[] topLevelCards;
	private ArrayList<Move> moves;
	private ArrayList<Move> backtrackingMoves;
	//private AcesUpController acesUpController;
	private AcesUpAIController acesUpAIController;
	
	@SuppressWarnings("unused")
	private AcesUpAI motherAI;
	
	private final int EMPTY = 0;
	
	public AcesUpAI(AcesUpAIController acesUpAIController){//AcesUpController acesUpController){
		//this.acesUpController = acesUpController;
		this.acesUpAIController = acesUpAIController;
		topLevelCards = new Card[4];		
		moves = new ArrayList<Move>();
		backtrackingMoves = new ArrayList<Move>();
	}	

	public void setGame(AcesUp acesUp){
		this.acesUp = acesUp;
	}
	public AcesUp getGame(){
		return acesUp;
	}
	
	public Move getNextMove() throws NotEnoughCardsOnPlaystacksException, NoMovesPossibleException {
		if(moves.size()>EMPTY){
			return moves.remove(0);
		}
		else{
			this.refreshToplevelCards();
			if(existsSameSuit()){
				return createMoveSameSymbol();
			}
			else{
				return this.gnmNoSameSuitsOnBoard();
			}
		}
	}
	
	private Move gnmNoSameSuitsOnBoard() throws NoMovesPossibleException, NotEnoughCardsOnPlaystacksException{
		if(countEmptyStacks() > EMPTY){
			if(existsStackedCards()){
				this.backtrackMoves();
				return moves.remove(0);
			}
			else{
				return this.throwExceptions();
			}
		}
		else{
			return this.throwExceptions();			
		}
	}
	private Move throwExceptions() throws NoMovesPossibleException, NotEnoughCardsOnPlaystacksException{
		if(this.isTalonEmpty()){
			throw new NoMovesPossibleException();
			//Controller : Spiel ist nicht mehr lösbar
		}
		else{
			throw new NotEnoughCardsOnPlaystacksException();
			//TODO: acesUpController muss drawCards() ausführen und danahc wieder die AI anschmeißen
		}	
	}
	private void refreshToplevelCards(){
		for(int i = 0; i < topLevelCards.length; i++){
			if(acesUp.getPlayingStacks()[i].isEmpty()){
				topLevelCards[i] = null;
				continue;
			}
			Cardstack currentStack = acesUp.getPlayingStacks()[i];
			topLevelCards[i] = currentStack.peek();
		}
	}
	
	/**	 
	 * @return True, if there is a stack of the PlayingStacks, that holds more than one Card.
	 * Otherwise it returns false.
	 */
	private boolean existsStackedCards(){
		return acesUp.getPlayingStacks()[0].size()>(EMPTY+1)||
				acesUp.getPlayingStacks()[1].size()>(EMPTY+1)||
					acesUp.getPlayingStacks()[2].size()>(EMPTY+1)||
						acesUp.getPlayingStacks()[3].size()>(EMPTY+1);					
	}
	

	/**
	 * @return a new legal Move, from a PlayingStack to the EliminationStack.
	 * Null, if there are not two Cards with the same suit.
	 */
	private Move createMoveSameSymbol(){ //eliminate smaller Rank card 
		boolean isSecondGreater;
		for(int i = 0; i < topLevelCards.length; i++){
			if(topLevelCards[i]== null){ continue; }
			for(int j = i+1; j < topLevelCards.length; j++){
				if(topLevelCards[j]== null){ continue; }
				if(this.isSameSuit(topLevelCards[i], topLevelCards[j])){
					int rankOfFirst = topLevelCards[i].getRank().ordinal();					
					int cardId1 =  isAce(rankOfFirst) ?	14 : rankOfFirst;
					int rankOfSecond = topLevelCards[j].getRank().ordinal();
					int cardId2 =  isAce(rankOfSecond) ?14 : rankOfSecond;
					isSecondGreater = cardId1 < cardId2 ? true : false; 
					Cardstack firstStack = acesUp.getPlayingStacks()[i];
					Cardstack secondStack = acesUp.getPlayingStacks()[j];
					return new Move(isSecondGreater ? firstStack:secondStack,
									acesUp.getEliminationStack(),
									1);
				}
			}
		}
		return null;
	}
	
	/**
	 * @param rank ordinal Value of the Rank [0,..,12]
	 * @return True, if rank = 0, then the Card is an Ace
	 * False, if the rank has any other value.
	 * 
	 */
	private boolean isAce(int rank){
		return rank == EMPTY;
	}
	
	/**
	 * @param card1 first Card 
	 * @param card2 second Card
	 * @return True, if the two Cards have the same suit.
	 * Otherwise it returns false.
	 */
	private boolean isSameSuit(Card card1, Card card2){
		return card1.getSuit().equals(card2.getSuit());
	}

	/**
	 * @return True, if there are Cards with the same suit on the top of the PlayingStacks.
	 * Otherwise it returns false.
	 */
	private boolean existsSameSuit(){
		int kreuz=0;
		int pik= 0;
		int karo = 0;
		int herz=0;
		int unique = 1;
		for(Card card : topLevelCards){
			if(card == null){continue;}
			switch(card.getSuit()){
			case DIAMOND: karo++; break;
			case SPADE: pik++; break;
			case CLUB: kreuz++; break;
			case HEART: herz++; break;
			}
		}
		if(kreuz>unique){ return true;}
		if(pik>unique){ return true;}
		if(karo>unique){ return true;}
		if(herz>unique){ return true;}
		return false;
	}
	
	/**
	 * @return Amount of empty PlayingStacks
	 */
	private int countEmptyStacks(){
		int emptyStacks = 0;
		for(Cardstack stack : acesUp.getPlayingStacks()){
			if(stack.size() == EMPTY){
				emptyStacks++;
			}
		}
		return emptyStacks;
	}
	
	/**
	 * 
	 * @return True, if the Talon is empty.
	 * Otherwise it returns false.
	 */
	private boolean isTalonEmpty(){
		return acesUp.getTalon().size() == EMPTY;
	}
	
	private int getIdOfFirstEmptyStack(){
		for(int i = 0; i < acesUp.getPlayingStacks().length; i++){
			if(acesUp.getPlayingStacks()[i].size() == EMPTY){ return i;}
		}
		return -1;
	}
	
	/**
	 * Calculates the best strategy for the actual Playground and sets intern variable 'moves' with the order of calculated moves. 
	 */
	//TODO move hinten an moves schreiben -> vorne ist erster Zug den die AI macht
	private void backtrackMoves() {
		//init Variablen
		int cardsOnBoard = acesUp.getCardsOnBoard();
		int firstEmptyStack = this.getIdOfFirstEmptyStack();
		
		//////////
		//TODO 1. backtracking bei mind. 2 x mind. 2 kartenstapel -> endlosbacktarckingschleife
		//		2. frei, frei, zwei, frei -> zweiGESPIEGELT, frei, frei, frei. 1. warum 2? 2. endlosschleife <<...
		//////////
		//start backtracking
		for(int i = 0; i < acesUp.getPlayingStacks().length; i++){
			if(acesUp.getPlayingStacks()[i].size() < EMPTY+2){ continue; }
			else{				
				AcesUpAI backtrackingAI = new AcesUpAI(acesUpAIController);
				backtrackingAI.setGame((AcesUp)acesUp.clone());
				System.out.println("TEST: "+acesUp);
				System.out.println("TEST2: "+backtrackingAI.getGame());
				backtrackingAI.move(i, firstEmptyStack);
				backtrackingAI.setMotherAI(this);
				
				//starte Backtracking mit diesem setting
				int cardsOnBoardNewSetting = backtrackingAI.backtracking();
				if(cardsOnBoard == cardsOnBoardNewSetting){
					if(moves.isEmpty()){
						int[][] tempIDs = backtrackingAI.createIDArrayFromBacktrackingMoves();
						for(int j = 0; j < backtrackingAI.getBacktrackingMoves().size(); j++){
							//brauch keine abfrage auf 5, da 5 für EliminationStack ist, und damit die Anzahl karten
							// bei einer leeren Moveliste(erste iteration) sonst kleiner wäre.
							Move tempMove = new Move(acesUp.getPlayingStacks()[tempIDs[j][0]], acesUp.getPlayingStacks()[tempIDs[j][1]], 1);
							moves.add(tempMove);
						}
						//moves.addAll(backtrackingAI.getBacktrackingMoves());
					}
				}
				else if(cardsOnBoard > cardsOnBoardNewSetting){ 
					cardsOnBoard = cardsOnBoardNewSetting;
					moves.clear();
					int[][] tempIDs = backtrackingAI.createIDArrayFromBacktrackingMoves();
					for(int j = 0; j < backtrackingAI.getBacktrackingMoves().size(); j++){
						Cardstack from = acesUp.getPlayingStacks()[tempIDs[j][0]];
						Cardstack target;
						if(tempIDs[j][1] == EMPTY+5){//Falls eliminationstack wurde 5 übergeben
							target = acesUp.getEliminationStack();
						}
						else{
							target = acesUp.getPlayingStacks()[tempIDs[j][1]];
						}
						Move tempMove = new Move(from, target, 1);
						moves.add(tempMove);
					}
					//moves.addAll(backtrackingAI.getBacktrackingMoves());
					//while(backtrackingAI.getBacktrackingMoves().size() > EMPTY){
						//moves.add(this.swapMoveCloneToOriginal(backtrackingAI.getBacktrackingMoves().get(0)));
						//moves.add(acesUp.getPlayingStacks()[backtrackingAI.getBacktrackingMoves().get(0).getNumberOfCards()])
					//}	//acesUp.getPlayingStacks[backtrakcingAI.getBacktrackingMoves(0).getFrom.getID], 
				}
			}
		}	
	}
	/*
	private Move swapMoveCloneToOriginal(Move cloneMove){
		int cardstackIDFrom = cloneMove.getFrom().getID();
		int cardstackIDTo = cloneMove.getTo().getID();
		Cardstack cardstackFrom = acesUp.getPlayingStacks()[cardstackIDFrom];
		Cardstack cardstackTo = acesUp.getPlayingStacks()[cardstackIDTo];
		Move originalMove = new Move(cardstackFrom, cardstackTo, 1);
		return originalMove;
	}*/
	//TESTMETHODE
	private void setMotherAI(AcesUpAI artificialIntelligence){
		this.motherAI = artificialIntelligence;
	}
	
	//nur für backtracking gedacht
	private void move(int source, int target){
		System.out.println("move: "+acesUp);
		Cardstack[] movedStacks = acesUp.getPlayingStacks();
		Cardstack sourceStack = movedStacks[source];
		Card topLevelCard = sourceStack.pop();
		movedStacks[target].add(topLevelCard);
		Move backtrackingMove = new Move(movedStacks[source], movedStacks[target],1);
		backtrackingMoves.add(backtrackingMove);
		acesUp.setPlayingStacks(movedStacks);		
	}
	
	private ArrayList<Move> getBacktrackingMoves(){
		return this.backtrackingMoves;
	}
		
	
	private int backtracking(){
		try{
			///// EIGENTLICH: this.getNextMove, aber mit BACKTRACKINGStacks und nicht AcesUp.getPlayingStacks()
			//Idee: Copy of AcesUp + neue KI mit dieser Copy
			Move nextMove = this.getNextMove();
			while(nextMove!=null){
				backtrackingMoves.add(nextMove);
				nextMove.getTo().push(nextMove.getFrom().pop());
				nextMove = this.getNextMove();
				//if(nextMove.getTo().getType().equals(Type.ELIMINATIONSTACK)){break;}
			}			
		} catch(NoMovesPossibleException e){
			return this.countCardsVirtualBacktrackingField();
		} catch (NotEnoughCardsOnPlaystacksException e) {
			return this.countCardsVirtualBacktrackingField();
		} 
		return 0; //wird theoretisch nie erreicht
	}
	
	//wird auf backtracking aufgerufen
	private int[][] createIDArrayFromBacktrackingMoves(){
		int[][] tempID = new int[backtrackingMoves.size()][2];
		for(int i = 0; i < backtrackingMoves.size(); i++){
			tempID[i][0] = this.getIDFromBacktrackingMove(backtrackingMoves.get(i).getFrom());
			tempID[i][1] = this.getIDFromBacktrackingMove(backtrackingMoves.get(i).getTo());
		}
		return tempID;
	}//Vergleicht abgespeicherten Playingstack mit playingstack vom clone
	private int getIDFromBacktrackingMove(Cardstack stack){
		for(int i = 0; i < acesUp.getPlayingStacks().length; i++){
			if(stack.equals(acesUp.getPlayingStacks()[i])){ return i;}
		}
		if(stack.equals(acesUp.getEliminationStack())){
			return 5;
		}
		System.out.println("FATAL ERROR getIDFromBacktrackingMove");
		return 0;
	}
	
	private int countCardsVirtualBacktrackingField(){
		int tempCount = 0;
		int stackSize = acesUp.getPlayingStacks().length;
		for(int i = 0; i < stackSize; i++){
			tempCount += acesUp.getPlayingStacks()[i].size();
		}
		return tempCount;
	}

	public void manuelMoved() {
		moves = new ArrayList<Move>();		
	}
}
