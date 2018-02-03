package movecontroller;


import controller.GameController;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import model.Card;
import model.Cardstack;

//wird gebraucht damit ein drop stattfinden kann
public class DragOverController implements EventHandler<DragEvent> {


	private Cardstack stapel;
	private GameController controller;

	public DragOverController(GameController controller, Cardstack stapel) {
		this.stapel = stapel;
		this.controller = controller;
	}
	
	
	public DragOverController(){}

	public void handle(DragEvent event) {
		Dragboard db = event.getDragboard();
		
		event.acceptTransferModes(TransferMode.MOVE);
		
		event.consume();

	}

}
