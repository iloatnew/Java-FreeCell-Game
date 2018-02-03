package movecontroller;

import controller.GameController;
import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import model.Cardstack;

public class DragDropEnteredController implements EventHandler<DragEvent> {
	GameController controller;
	Cardstack stapel;
	Pane pane;

	public DragDropEnteredController(GameController controller, Cardstack stapel, Pane pane) {
		this.controller = controller;
		this.stapel = stapel;
		this.pane = pane;
	}

	@Override
	public void handle(DragEvent event) {
		event.acceptTransferModes(TransferMode.MOVE);

		controller.setTargetStack(stapel);
		if (controller.isDroppable(stapel)) {
			
			//falls feld nicht leer braucht keine imageview aufzuleuchten
			if (controller.getTargetStack().size() > 0) {
				
				ImageView view = (ImageView) pane.getChildren().get(pane.getChildren().size() - 1);
				view.setEffect(new Glow(0.8));
			}
		}

	}

}
