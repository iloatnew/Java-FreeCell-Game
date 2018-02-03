package aicontroller;

import exceptions.IllegalGameArgumentException;
import exceptions.NoMovesPossibleException;
import exceptions.NotEnoughCardsOnPlaystacksException;
import model.Game;
import model.Move;

public abstract class AIController {	
	public abstract void setGame(Game game) throws IllegalGameArgumentException;
	
//	public ArrayList<Move> getMoves(){ // nur in ZANK
//		return null;
//	}
	
	public abstract Move getNextMove() throws NotEnoughCardsOnPlaystacksException, NoMovesPossibleException;

	public abstract void manuelMoved();
				
}
