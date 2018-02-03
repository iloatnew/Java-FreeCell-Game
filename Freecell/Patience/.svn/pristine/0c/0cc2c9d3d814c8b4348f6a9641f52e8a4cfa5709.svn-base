package movecontroller;

import controller.GameController;
import javafx.event.EventHandler;
import javafx.scene.input.DragEvent;

public class DragDoneController implements EventHandler<DragEvent> {
	GameController controller;

	public DragDoneController(GameController controller) {
		this.controller = controller;
	}

	@Override
	public void handle(DragEvent event) {

		controller.refreshKarte();
	}

}
