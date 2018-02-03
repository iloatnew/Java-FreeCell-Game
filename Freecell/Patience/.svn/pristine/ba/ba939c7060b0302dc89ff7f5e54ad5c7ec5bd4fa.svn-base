package aicontroller;

import controller.AutoMoveController;
import exceptions.IllegalGameArgumentException;
import exceptions.NoMovesPossibleException;
import exceptions.NotEnoughCardsOnPlaystacksException;
import model.Game;
import model.Move;
import model.Zank;


/**
 * @author Steven, Luise
 * The ZankAIController is the AI For Zank.
 */

public class ZankAIController extends AIController {

	private Zank zank;

	private ZankAI startAI;

	public ZankAIController(){
		
	}	
	
	@Override
	public void setGame(Game game) throws IllegalGameArgumentException {
		if(game.getClass() == Zank.class){
			zank = (Zank) game;
			startAI.setGame(zank);
		}
		else{
			throw new IllegalGameArgumentException();
		}
	}
	
	public void initAI(AutoMoveController autoMoveController){
		startAI = new ZankAI(this);
		startAI.setAutoMoveController(autoMoveController);
	}

	public void setAutoMoveController(AutoMoveController autoMoveController){
		startAI.setAutoMoveController(autoMoveController);
	}
	public AutoMoveController getAutoMoveController(){
		return startAI.getAutoMoveController();
	}
	
	@Override
	public Move getNextMove() throws NotEnoughCardsOnPlaystacksException, NoMovesPossibleException {
		return startAI.getNextMove();
	}
	
	public void setActivePlayer(int activePlayer){
		startAI.setActivePlayer(activePlayer);
	}

	@Override
	public void manuelMoved() {
		startAI.manuelMoved();		
	}
	public void setLastMove(Move lastMove){
		this.startAI.setLastMove(lastMove);
	}
}
