package movecontroller;

import controller.AcesUpController;
import controller.FreeCellController;
import controller.GameController;
import controller.ZankController;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import model.AcesUp;
import model.Cardstack;
import model.FreeCell;
import model.Game;
import model.Move;
import model.Type;
import model.Zank;

/**
 * @author sopr047 legt gezogene Karten auf den Stapel, falls möglich
 *
 */
public class DragDropController implements EventHandler<DragEvent> {

	private GameController controller;

	public DragDropController(GameController controller) {
		this.controller = controller;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.event.EventHandler#handle(javafx.event.Event)
	 */
	public void handle(DragEvent event) {
		event.acceptTransferModes(TransferMode.MOVE);

		if (!(event.getGestureTarget() instanceof Pane)
				|| !controller.getTargetStack().getType().equals(Type.PLAYINGSTACK)
				|| controller.getTargetStack().size() == 0) {
			if (controller.isDroppable(controller.getTargetStack())) {
				controller.getTargetStack().addAll(controller.getStackToDrag());
				controller.getSourceStack().removeAllBelow(controller.getStackToDrag().get(0));
				
				Cardstack tmpfrom = controller.getSourceStack();
				Cardstack tmpto = controller.getTargetStack();
				int tmpsize = controller.getStackToDrag().size();
				Move tmp = new Move(tmpfrom, tmpto, tmpsize);
				controller.getPatienceController().getPatience().getGame().getHistory().push(tmp);
				Game game = controller.getPatienceController().getPatience().getGame();
				if(game instanceof Zank){
					((ZankController)controller).getAutoMoveController().autoMoveSuccess();
					if (tmpto.equals(((Zank) game).getWastePiles()[((ZankController)controller).getActivePlayer()])){
						((ZankController) controller).switchPlayer();
					}
					if(((ZankController) controller).isFinished()){
						((ZankController)controller).getZankAUI().openFinishedWindow("Zank", ( (ZankController) controller).getActivePlayer());
					}
				}
				else if (game instanceof FreeCell){
					((FreeCell) game).getSingleplayerStatistics().incrementNumberMoves();
					if(((FreeCellController) controller).isFinished()){
						((FreeCellController)controller).getFreeCellAUI().openFinishedWindow("FreeCell", 0);
					}
				}
				else{
					((AcesUp) game).getStatistics().incrementNumberMoves();
					if(((AcesUpController) controller).isFinished()){
						((AcesUpController) controller).getAcesUpAUI().openFinishedWindow("AcesUp", 0);
					}
					
				}
				event.consume();
			} 
			else {
				controller.getSourceStack().removeAllBelow(controller.getStackToDrag().get(0));
				controller.getSourceStack().addAll(controller.getStackToDrag());
				event.consume();
			}
		}

		
		// }

		if (controller instanceof FreeCellController) {
			while(((FreeCellController) controller).getAutoMoveController().autoMoveSuccess());
			
		}
		if (controller instanceof ZankController) {
			((ZankController) controller).getAutoMoveController().autoMoveSuccess();
		}
		
		controller.getAIController().manuelMoved();
		// controller.setStackToDrag(new Cardstack(Type.DEFAULT));

		event.consume();
	}

}
