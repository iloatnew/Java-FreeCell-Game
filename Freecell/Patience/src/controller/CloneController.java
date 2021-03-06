package controller;

import model.Cardstack;
import model.MultiplayerStatistics;
import model.SingleplayerStatistics;

public class CloneController {
	
	public static Cardstack[] cloneCardstacks(Cardstack[] originalStack){
		Cardstack[] cloneStack = new Cardstack[originalStack.length];
		for(int i = 0; i < cloneStack.length; i++){
			cloneStack[i] = cloneCardstack(originalStack[i]);
		}
		return cloneStack;
	}
	
	public static Cardstack cloneCardstack(Cardstack originalStack){
		Cardstack cloneStack = new Cardstack(originalStack.getType());
		cloneStack.addAll(originalStack.cloneAllBelow(originalStack.size()));
		return cloneStack;
	}
	
	public static SingleplayerStatistics cloneSingleplayerStatistics(SingleplayerStatistics originalStatistics){
		return new SingleplayerStatistics(originalStatistics.getNumberMoves(), originalStatistics.getNumberEliminatedCards(), originalStatistics.getNeededTime());
	}

	public static MultiplayerStatistics cloneMultiplayerStatistics(MultiplayerStatistics multiplayerStatistics) {
		return new MultiplayerStatistics(multiplayerStatistics.getCardsPlayer1(), multiplayerStatistics.getCardsPlayer2(), multiplayerStatistics.getPlayer1(), multiplayerStatistics.getPlayer2(), multiplayerStatistics.getNeededTime());
	}
	
}
