package aicontroller;

import controller.AutoMoveController;
import exceptions.IllegalGameArgumentException;
import exceptions.NoMovesPossibleException;
import exceptions.NotEnoughCardsOnPlaystacksException;
import model.FreeCell;
import model.Game;
import model.Move;

public class FreeCellAIController extends AIController {





	private FreeCell freeCell;



	private FreeCellAI freeCellAI;

	@Override
	public void setGame(Game game) throws IllegalGameArgumentException {
		if (game.getClass() == FreeCell.class) {
			freeCell = (FreeCell) game;
			freeCellAI.setGame(freeCell);

		} else {
			throw new IllegalGameArgumentException();
		}
	}

	

	@Override
	public Move getNextMove() throws NotEnoughCardsOnPlaystacksException, NoMovesPossibleException {

		return freeCellAI.getNextMove();
	}



	@Override
	public void manuelMoved() {
		// TODO Auto-generated method stub
		
	}

}
