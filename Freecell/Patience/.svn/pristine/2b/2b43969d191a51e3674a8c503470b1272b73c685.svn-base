package movecontroller;

import controller.GameController;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import model.Cardstack;
import model.Game;
import model.Type;

public class DragDropExitController implements EventHandler<DragEvent> {

	Pane pane;
	GameController controller;

	public DragDropExitController(GameController controller, Pane pane) {
		// TODO Auto-generated constructor stub
		this.controller = controller;
		this.pane = pane;
	}

	@Override
	public void handle(DragEvent event) {
		event.acceptTransferModes(TransferMode.MOVE);

		if (controller.getTargetStack().size() > 1) {
			ImageView view = (ImageView) pane.getChildren().get(pane.getChildren().size()-1);
			view.setEffect(null);
		}
			
		controller.setTargetStack(new Cardstack(Type.DEFAULT));
	}

}
