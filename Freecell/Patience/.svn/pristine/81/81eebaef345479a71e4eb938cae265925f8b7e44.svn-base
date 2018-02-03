package aicontroller;

import java.util.ArrayList;

import controller.AutoMoveController;
import exceptions.NotEnoughCardsOnPlaystacksException;
import exceptions.TimeoutException;
import model.Card;
import model.Cardstack;
import model.Move;
import model.Zank;

public class ZankAI{

	private Zank zank;
	private ArrayList<Move> moves;
	private ArrayList<Move> backtrackingMoves;
	//private AcesUpController acesUpController;
	private ZankAIController zankAIController;
	private AutoMoveController autoMoveController;
	private int activePlayer;
	private int enemyPlayer;
	private final int EMPTY = 0;
	//private final int MAX_BACKTRACKING_TIME_IN_MILLIS = 15000;
	private final int TOO_MANY_CARDS_ON_PLAYINGSTACKS_FOR_BACKTRACKING = 6;
	private ArrayList<Card> receivedReverse; //TODO muss geworfen werden vom ZankController, sobald ein spieler
	//erreichte minimale anzahl an Karten vom Ersatzkartenstapel; 
	//ist mRC = 5 und zank.getReserver()[AP].size() > 5 wissen wir, uns wurden karten drauf gelegt; 
	//benötigt für backtracking
	private Move lastMove;
	private long startingTime; //backtracking
	
	private final int empty = 0;
	
	@SuppressWarnings("unused")
	private ZankAI motherAI;

	public ZankAI(ZankAIController zankAIController){
		this.zankAIController = zankAIController;	
		moves = new ArrayList<Move>();
		backtrackingMoves = new ArrayList<Move>();
		startingTime = System.currentTimeMillis();
		motherAI = null;
	}	
	
	public void setGame(Zank zank){
		this.zank = zank;
		for(int i = 0; i < zank.getPlayingStacks().length; i++){
			zank.getPlayingStacks()[i].setCardstackID(i);
		}
		for(int i = 8; i-8 < zank.getReserves().length; i++){
			zank.getReserves()[i-8].setCardstackID(i);
		}
		for(int i = 10; i-10 < zank.getWastePiles().length; i++){
			zank.getWastePiles()[i-10].setCardstackID(i);
		}
	}
	public Zank getGame(){
		return zank;
	}
	public void setAutoMoveController(AutoMoveController autoMoveController){
		this.autoMoveController = autoMoveController;
	}
	public AutoMoveController getAutoMoveController(){
		return autoMoveController;
	}
	private void setMotherAI(ZankAI artificialIntelligence){
		this.motherAI = artificialIntelligence;
	}
	public void setLastMove(Move lastMove){
		this.lastMove = lastMove;
	}

	////////////////////
	//getNextMoveBlock
	////////////////////
	public Move getNextMove() throws NotEnoughCardsOnPlaystacksException{
		//TODO zankController muss jedesmal zuerst setAktivPlayer aufrufen!
		if(moves.size()>empty){
			return moves.remove(0);
		}
		else{
			return this.gnmTryShiftCardsBeforeBacktracking();
		}
	}
	private Move gnmTryShiftCardsBeforeBacktracking(){
		if(isMoveableReserveToEnemyReserve()){
			return this.createMove("reserve", "enemyReserve");
		}
		else if(isMoveableReserveToEnemyWaste()){
			return this.createMove("reserve", "enemyWaste");			
		}
		else{
			if(isMoveableHandToEnemyReserve()){
				return this.createMove("hand", "enemyReserve");
			}
			else if(isMoveableHandToEnemyWaste()){
				return this.createMove("hand", "enemyWaste");
			}
			else{
				return this.gnmStartBacktracking();
			}
		}
	}
	private Move gnmStartBacktracking(){
		createFreeStacks();//Backtracking;
		if(!moves.isEmpty()){
			System.out.println(moves.get(0).getFrom().getCardstackID());
			return moves.remove(0);
		}
		else{
			if(isPossibleMoveAllKnownReserve()){
				//Backtrackartig
				//mit try-catch (SystemInterruptException) ->möglich paar Karten loszuwerden
				//ODER: möglich, Teilstapel loszuwerden ohne dass Gegner Karten zurück gibt;
				//5,6,7,8 -> 5 aufs feld, 6 gegner, 7 vom Feld auf gegner, kann 5 nicht zurückgeben
				return moves.remove(0);
			}
			else{
				return this.gnmTryShiftCardsAfterBacktracking();
			}						
		}
	}
	private Move gnmTryShiftCardsAfterBacktracking(){
		if(isMoveableHandOrWasteToEnemyWasteOrReserve()){
			return moves.remove(0);
		}
		else{
			if(isMoveableHandOrWasteToField()){
				return moves.remove(0);
			}
			else{
				if(isMoveableFieldToEnemy()){
					return moves.remove(0);
				}
				else{
					return this.gnmFinishTurn();
				}
			}
		}
	}
	private Move gnmFinishTurn(){
		return this.createMove("hand", "waste");
	}
	///////////////////
	//Ende gnm-Block
	///////////////////
	
	///////////////////
	//is-Booleanmethoden, die Moves aktualisieren
	///////////////////
	private boolean isMoveableFieldToEnemy() {
		Cardstack enemyWasteStack = zank.getWastePiles()[enemyPlayer];
		Cardstack enemyReserveStack = zank.getReserves()[enemyPlayer];
		Card enemyWaste = this.getToplevelCard(enemyWasteStack);
		Card enemyReserve = this.getToplevelCard(enemyReserveStack);
		for(Cardstack playingstack : zank.getPlayingStacks()){
			if(playingstack.isEmpty()){continue;}
			if(isMoveable(playingstack.peek(), enemyWaste, false)){
				moves.add(new Move(playingstack, enemyWasteStack, 1));
				return true;
			}
			if(isMoveable(playingstack.peek(), enemyReserve, false)){
				moves.add(new Move(playingstack, enemyReserveStack, 1));
				return true;
			}
		}
		return false;
	}

	private boolean isMoveableHandOrWasteToField() {
		Cardstack ourHandStack = zank.getHands()[activePlayer];
		Cardstack ourWasteStack = zank.getWastePiles()[activePlayer];
		Card ourHand = this.getToplevelCard(ourHandStack);
		Card ourWaste = this.getToplevelCard(ourWasteStack);
		Card ourReserve = this.getToplevelCard(zank.getReserves()[activePlayer]);
		//Kann die Karte im nächsten Zug auf unseren Reserve gelegt werden?
		boolean checkHand = !isMoveable(ourHand, ourReserve, false);
		boolean checkWaste = !isMoveable(ourWaste, ourReserve, false);
		
		for(Cardstack playingstack : zank.getPlayingStacks()){
			if(playingstack.isEmpty()){continue;}
			if(checkHand){
				if(isMoveable(ourHand, playingstack.peek(), true)){
						moves.add(new Move(ourHandStack,playingstack, 1));
						return true;
				}
			}
			if(checkWaste){ 
				if(isMoveable(ourWaste, playingstack.peek(), true)){
					moves.add(new Move(ourWasteStack, playingstack, 1));
					return true;
				}
			}
		}
		return false;
	}
		
	private boolean isMoveableHandOrWasteToEnemyWasteOrReserve() {
		if(isMoveableHandToEnemyReserve()){
			moves.add(this.createMove("hand", "enemyReserve"));
			return true;
		}
		if(isMoveableHandToEnemyWaste()){
			moves.add(this.createMove("hand", "enemyWaste"));
			return true;
		}
		if(isMoveableWasteToEnemyReserve()){
			moves.add(this.createMove("waste", "enemyReserve"));
			return true;
		}
		if(isMoveableWasteToEnemyWaste()){
			moves.add(this.createMove("waste", "enemyWaste"));
			return true;
		}
		return false;
	}
	///////////////////
	//reine is-Booleanmethoden
	///////////////////
	private boolean isMoveableWasteToEnemyWaste(){
		Card ourWaste = this.getToplevelCard(zank.getWastePiles()[activePlayer]);
		Card enemyWaste = this.getToplevelCard(zank.getWastePiles()[enemyPlayer]);
		return isMoveable(ourWaste, enemyWaste, false);
	}
	
	private boolean isMoveableWasteToEnemyReserve(){
		Card ourWaste = this.getToplevelCard(zank.getWastePiles()[activePlayer]);
		Card enemyReserve = this.getToplevelCard(zank.getReserves()[enemyPlayer]);
		return isMoveable(ourWaste, enemyReserve, false);
	}
	
	private boolean isMoveableHandToEnemyWaste() {
		Card ourHand = this.getToplevelCard(zank.getHands()[activePlayer]);
		Card enemyWaste = this.getToplevelCard(zank.getWastePiles()[enemyPlayer]);
		return isMoveable(ourHand, enemyWaste, false);
	}
	
	private boolean isMoveableHandToEnemyReserve() {
		Card ourHand = this.getToplevelCard(zank.getHands()[activePlayer]);
		Card enemyReserve = this.getToplevelCard(zank.getReserves()[enemyPlayer]);
		return isMoveable(ourHand, enemyReserve, false);
	}

	private boolean isMoveableReserveToEnemyWaste() {
		Card ourReserve = this.getToplevelCard(zank.getReserves()[activePlayer]);
		Card enemyWaste = this.getToplevelCard(zank.getWastePiles()[enemyPlayer]);
		return isMoveable(ourReserve, enemyWaste, false);
	}

	private boolean isMoveableReserveToEnemyReserve() {
		Card ourReserve = this.getToplevelCard(zank.getReserves()[activePlayer]);
		Card enemyReserve = this.getToplevelCard(zank.getReserves()[enemyPlayer]);
		return isMoveable(ourReserve, enemyReserve, false);
	}

	private boolean isMoveable(Card from, Card target, boolean isTargetPlayingstack){
		if(from == null){return false;}
		if(!isTargetPlayingstack && target == null){return false;}//Target ist KartenstapelSpieler
		if(isTargetPlayingstack){//auf Playingstack
			if(!from.getSuit().color().equals(target.getSuit().color())){//Unterschiedliche Farbe
				if(from.getRank().ordinal()+1 == target.getRank().ordinal()){//kleinere an größere legen
					return true;
				}
			}
			return false;			
		}
		else{ 
			if(from.getSuit().name().equals(target.getSuit().name())){
				//(from.getSuit().name().equals(target.getSuit().name())){
				if(from.getRank().ordinal() == target.getRank().ordinal()+1 
						|| from.getRank().ordinal() == target.getRank().ordinal()-1){
					return true;
				}
			}
			return false;
		}
	}
	///////////////////
	//Hilsfmethoden
	///////////////////
	private Card getToplevelCard(Cardstack cardstack){
		if(cardstack.isEmpty()){ return null; }
		return cardstack.peek();
	}
	
	private Move createMove(String from, String target){
		return new Move(this.stringToStack(from), this.stringToStack(target), 1);
	}
	
	private Cardstack stringToStack(String input){
		switch(input){
		case "hand": return zank.getHands()[activePlayer]; 
		case "waste": return zank.getWastePiles()[activePlayer];
		case "reserve": return zank.getReserves()[activePlayer];
		case "enemyHand": return zank.getHands()[enemyPlayer]; 
		case "enemyWaste": return zank.getWastePiles()[enemyPlayer];
		case "enemyReserve": return zank.getReserves()[enemyPlayer];
		default :System.out.println("ERROR in aiController.ZankAI.stringToStack->switch(input); input: "+input);return null;		
		}
	}
	
	public void setActivePlayer(int playerID){
		this.activePlayer = playerID;
		this.enemyPlayer = (playerID+1)%2;
	}
	
	//Muss jedes mal vom GameController geworfen werden, wenn der Gegner eine Karte auf den Ersatzkartenstapel legt
	public void addReceivedReverse(Card card){
		receivedReverse.add(card);
	}
	
	/////////////////////////
	//Backtracking
	/////////////////////////
	
	private boolean isPossibleMoveAllKnownReserve(){
		//TODO
		//erstmal nur eine
		Card topCardReserve = zank.getReservesToplevelCard(activePlayer);
		for(Cardstack cardStack : zank.getPlayingStacks()){
			if(!cardStack.isEmpty()){
				if(isMoveable(topCardReserve, cardStack.peek(), true)){
					moves.add(new Move(zank.getReserves()[activePlayer], cardStack, 1));
					return true;
				}
			}
		}
		return false;
	}
	
	private void createFreeStacks(){
		this.setStartingTime(System.currentTimeMillis());
		try{
			ZankAI backtrackingAI;
			for(Cardstack currentStack : zank.getPlayingStacks()){
				//überprüfe nach jedem Stack ob Zeitüberschreitung vorliegt
				/*if(System.currentTimeMillis() - startingTime > MAX_BACKTRACKING_TIME_IN_MILLIS){
					throw new TimeoutException();
				}*/ //TODO TIME
				if(currentStack.size() >= TOO_MANY_CARDS_ON_PLAYINGSTACKS_FOR_BACKTRACKING){ continue;} //TODO feinschliff
				if(currentStack.size() == EMPTY){ continue; }
				backtrackingAI = new ZankAI(this.zankAIController);
				backtrackingAI.setGame((Zank) zank.clone());
				backtrackingAI.setAutoMoveController(autoMoveController);
				backtrackingAI.setMotherAI(this);
				backtrackingAI.setActivePlayer(activePlayer);
				backtrackingAI.setLastMove(lastMove);
				//TODO clone muss noch geschrieben werden
				backtrackingAI.setStartingTime(startingTime);
				int tempID = this.getIDOriginalToBacktrackingstack(currentStack);
				if(backtrackingAI.tryEmptyCardstackBacktracking(tempID)){
					this.getMovesFromBacktrackingAI(backtrackingAI);
					return;
				}//passe Movelist an
			}
		}
		catch(TimeoutException e){
			return; //Falls kein Ergebnis erzielt werden konnte in der maximalen Zeit, brich ab ohne moves zu verändern (?)
		}
	}
	private int getIDOriginalToBacktrackingstack(Cardstack stack){
		for(int i = 0; i < zank.getPlayingStacks().length; i++){
			if(stack.getCardstackID()==zank.getPlayingStacks()[i].getCardstackID()){
				return i;
			}
		}
		System.out.println(stack.getType());
		System.out.println(stack);
		System.out.println("FATAL ERROR in ZankAI.getIDOriginalTobacktrackingstack");
		return 0;
	}
	private void getMovesFromBacktrackingAI(ZankAI backtrackingAI){//auf startAI
		int[][] tempIDs = backtrackingAI.createIDArrayFromBacktrackingMoves();
		for(int j = 0; j < backtrackingAI.getBacktrackingMoves().size(); j++){
			Cardstack from, target;
			//IDs: 0-7 Playingstacks, 8 EnemyReserve, 9 EnemyWaste
			switch(tempIDs[j][0]){
			case 8: from = zank.getReserves()[enemyPlayer]; break;
			case 9: from = zank.getWastePiles()[enemyPlayer]; break;
			case 10: from = zank.getHands()[activePlayer]; break;
			case 11: from = zank.getReserves()[activePlayer]; break;
			case 12: from = zank.getWastePiles()[activePlayer]; break;
			default: from = zank.getPlayingStacks()[tempIDs[j][0]]; break;			
			}
			switch(tempIDs[j][1]){
			case 8: target = zank.getReserves()[enemyPlayer]; break;
			case 9: target = zank.getWastePiles()[enemyPlayer]; break;
			case 10: target = zank.getHands()[activePlayer]; break;
			case 11: target = zank.getReserves()[activePlayer]; break;
			case 12: target = zank.getWastePiles()[activePlayer]; break;
			default: target = zank.getPlayingStacks()[tempIDs[j][1]]; break;
			}
			Move tempMove = new Move(from, target, 1);
			moves.add(tempMove);
		}
	}
	private int[][] createIDArrayFromBacktrackingMoves(){//auf backtrackingAI
		int[][] tempID = new int[backtrackingMoves.size()][2];
		for(int i = 0;  i < backtrackingMoves.size(); i++){
			tempID[i][0] = this.getIDFromBacktrackingMove(backtrackingMoves.get(i).getFrom());
			tempID[i][1] = this.getIDFromBacktrackingMove(backtrackingMoves.get(i).getTo());
		}
		return tempID;
	}
	private int getIDFromBacktrackingMove(Cardstack stack){
		for(int i = 0; i < zank.getPlayingStacks().length; i++){
			if(stack.getCardstackID()==zank.getPlayingStacks()[i].getCardstackID()){ return i;}
		}
		if(stack.getCardstackID()==zank.getReserves()[enemyPlayer].getCardstackID()){ return 8; }
		if(stack.getCardstackID()==zank.getWastePiles()[enemyPlayer].getCardstackID()){ return 9; }
		if(stack.getCardstackID()==zank.getHands()[activePlayer].getCardstackID()){ return 10; }
		if(stack.getCardstackID()==zank.getReserves()[activePlayer].getCardstackID()){ return 11; }
		if(stack.getCardstackID()==zank.getWastePiles()[activePlayer].getCardstackID()){ return 12; }
		System.out.println("FATAL ERROR in ZankAI.getIDFromBacktrackingMove, Cardstack: "+stack);
		return 0;
	}
	
	private boolean tryEmptyCardstackBacktracking(int iDWorkstack) throws TimeoutException{
		Card topCard;
		Cardstack workingStack = zank.getPlayingStacks()[iDWorkstack];
		while(!workingStack.isEmpty()){
			topCard = workingStack.pop();
			//überprüfe nach jeder Kartenbewegung ob Zeitüberschreitung vorliegt
			/*if(System.currentTimeMillis() - startingTime > MAX_BACKTRACKING_TIME_IN_MILLIS){
				throw new TimeoutException();
			}*///TODO TIME
			//TODO: 
			int stackSizeBeforeAutomove = workingStack.size();
			while(autoMoveController.autoMoveZank(zank)){
				int stackSizeAfterAutomove = workingStack.size();
				int difference = stackSizeBeforeAutomove - stackSizeAfterAutomove;
				while(difference > 0){
					topCard = workingStack.pop(); //nächste Karte
				}
				if(topCard == null){ return true; } //Stapel leer
			}
			if(!this.tryShiftCardToOtherStack(workingStack, topCard)){ //Versuche, Karte zu verschieben
				return false;
			}
		}
		return true;
	}
	
	//Bewege Zielkarte vom Zielstapel zu irgendeinem anderen stapel. 
	//return: ist mölgich -> falls false: abbruch für backtracking
	private boolean tryShiftCardToOtherStack(Cardstack workingStack, Card topCard){
		//Teste jeden Playingstack, ob topCard dort angelegt werden kann
		for(Cardstack playingstack : zank.getPlayingStacks()){
			if(playingstack.getCardstackID()==workingStack.getCardstackID()){ continue; }
			if(playingstack.size() == EMPTY || isMoveable(topCard, playingstack.peek(), true)){
				Move tempMove = new Move(workingStack, playingstack, 1);
				if(lastMove != null){
					lastMove = lastMove.revertMove();
					if(tempMove.getFrom().getCardstackID()==lastMove.getFrom().getCardstackID()
						&& tempMove.getTo().getCardstackID()==lastMove.getTo().getCardstackID()){
						return false;
					}
				}
				backtrackingMoves.add(new Move(workingStack, playingstack, 1));
				playingstack.add(topCard);
				return true;
			}
		}
		//Teste enemyStacks; falls dort ebenfalls nicht ablegbar, gibt es keine ablagemöglichkeit
		return wasMoveCardToEnemySuccsesfull(workingStack, topCard);
	}
	private boolean wasMoveCardToEnemySuccsesfull(Cardstack workingStack, Card topCard){
		if(isMoveable(topCard, zank.getHandsToplevelCard(enemyPlayer), false)){
			adjustBacktrackingMovesAndRemoveToplevelcard(workingStack, zank.getHands()[enemyPlayer]);
			return true;
		}
		if(isMoveable(topCard, zank.getReservesToplevelCard(enemyPlayer), false)){
			adjustBacktrackingMovesAndRemoveToplevelcard(workingStack, zank.getReserves()[enemyPlayer]);
			return true;
		}
		if(isMoveable(topCard, zank.getWastesToplevelCard(enemyPlayer), false)){
			adjustBacktrackingMovesAndRemoveToplevelcard(workingStack, zank.getWastePiles()[enemyPlayer]);
			return true;
		}
		return false;
	}
	private void adjustBacktrackingMovesAndRemoveToplevelcard(Cardstack workingStack, Cardstack targetStack){
		this.backtrackingMoves.add(new Move(workingStack, targetStack, 1));
		this.removeToplevelCard(workingStack);
	}
	private void removeToplevelCard(Cardstack cardstack){
		if(!cardstack.isEmpty()){
			cardstack.pop();
		}
	}
	private void setStartingTime(long startingTime){
		this.startingTime = startingTime;
	}

	private ArrayList<Move> getBacktrackingMoves(){
		return this.backtrackingMoves;
	}

	public void manuelMoved() {
		moves = new ArrayList<Move>();
	}
}
