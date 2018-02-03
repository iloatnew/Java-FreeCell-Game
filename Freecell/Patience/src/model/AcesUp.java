package model;

import java.util.Stack;

import controller.CloneController;

public class AcesUp extends Game {

	private Cardstack[] playingStacks;

	private SingleplayerStatistics statistics;

	private Cardstack talon;

	private Cardstack eliminationStack;
	
	private int spielzug;
	
	public AcesUp(Cardstack startDeck){
		this.isNewGame = true;
		this.startStack = startDeck;
		this.history=new Stack<Move>();
		this.statistics = new SingleplayerStatistics(0,0,0);
		this.talon = new Cardstack(Type.TALON);
		this.talon.addAll(startDeck);
		this.eliminationStack = new Cardstack(Type.ELIMINATIONSTACK);
		this.spielzug = 0;
		this.fraction = (long) 0;
		this.time = (long) 0;
		this.playingStacks = new Cardstack[4];
		this.fraction = (long) 0;
		for(int i=0; i<4; i++){
			this.playingStacks[i] = new Cardstack(Type.PLAYINGSTACK);
		}
	}
	
	public int getSpielzug() {
		return spielzug;
	}

	public void setSpielzug(int spielzug) {
		this.spielzug = spielzug;
	}

	public Cardstack[] getPlayingStacks() {
		return this.playingStacks;
	}

	public void setPlayingStacks(Cardstack[] playingStacks) {
		this.playingStacks = playingStacks;
	}

	public SingleplayerStatistics getStatistics() {
		return statistics;
	}

	public void setStatistics(SingleplayerStatistics statistics) {
		this.statistics = statistics;
	}

	public Cardstack getTalon() {
		return talon;
	}

	public void setTalon(Cardstack talon) {
		this.talon = talon;
	}

	public Cardstack getEliminationStack() {
		return eliminationStack;
	}

	public void setEliminationStack(Cardstack eliminationStack) {
		this.eliminationStack = eliminationStack;
	}

	@Override
	public int getCardsOnBoard() {
		int cardsOnBoard = 0;
		for(Cardstack stack : playingStacks){
			cardsOnBoard += stack.size();
		}
		return cardsOnBoard;
	}
	
	@Override
	public Game clone(){
		AcesUp cloneUp = new AcesUp(startStack);
		cloneUp.setEliminationStack(CloneController.cloneCardstack(eliminationStack)); //Tested
		cloneUp.setHistory((Stack<Move>) history.clone()); //Tested
		cloneUp.setPlayingStacks(CloneController.cloneCardstacks(playingStacks)); //Tested
		cloneUp.setSpielzug(spielzug);
		cloneUp.setStartStack(CloneController.cloneCardstack(startStack)); //Tested
		cloneUp.setStatistics(CloneController.cloneSingleplayerStatistics(statistics)); //Tested
		cloneUp.setTalon(CloneController.cloneCardstack(talon)); //Tested
		cloneUp.setTime(time);
		cloneUp.setNewGame(isNewGame);
		return cloneUp;
	}

}
