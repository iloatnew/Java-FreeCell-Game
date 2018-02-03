package aicontroller;

import controller.AutoMoveController;
import exceptions.IllegalGameArgumentException;
import exceptions.NoMovesPossibleException;
import exceptions.NotEnoughCardsOnPlaystacksException;
import model.AcesUp;
import model.Game;
import model.Move;


/**
 * @author Steven, Luise
 * The AcesUpAIController is the AI for AcesUp.
 */

public class AcesUpAIController extends AIController {
	
	private AcesUp acesUp;

	//private AcesUpController acesUpController;
	private AcesUpAI startAI;

	public AcesUpAIController(){//AcesUpController acesUpController){
		//this.acesUpController = acesUpController;
	}	
	
	@Override
	public void setGame(Game game) throws IllegalGameArgumentException {
		if(game.getClass() == AcesUp.class){
			acesUp = (AcesUp) game;
			startAI.setGame(acesUp);
		}
		else{ 
			throw new IllegalGameArgumentException();
		}
	}
	
	public void initAI(AutoMoveController autoMoveController){
		startAI = new AcesUpAI(this);
	}
	
	@Override
	public Move getNextMove() throws NotEnoughCardsOnPlaystacksException, NoMovesPossibleException {
		return startAI.getNextMove();
	}

	@Override
	public void manuelMoved() {
		startAI.manuelMoved();		
	}
	
}
