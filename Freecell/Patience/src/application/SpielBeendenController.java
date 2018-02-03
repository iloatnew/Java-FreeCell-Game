package application;

import java.io.File;
import java.time.LocalDateTime;

import controller.AcesUpController;
import controller.FreeCellController;
import controller.PatienceController;
import controller.ZankController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.AcesUp;
import model.FreeCell;
import model.Game;
import model.Zank;

public class SpielBeendenController {	

	@FXML
	private AnchorPane pane;
	
	 @FXML
    private Button savingGameButton;

    @FXML
    private Button cancelGameButton;

    @FXML
    private Button cancelButton;
    
    private PatienceController pCtrl;
    
    private WindowEvent windowEvent;
    
    private Stage parentStage;
    
    private HauptfensterController hauptfensterController;

    @FXML
	public void initialize() {
	}
    
    @FXML
    void cancelButtonClicked(ActionEvent event) {
    	this.windowEvent.consume();
    	Stage stage = (Stage) pane.getScene().getWindow();
    	stage.close();
    	this.hauptfensterController.weiterButtonClicked(null);
    }

    @FXML
    void cancelGameButtonClicked(ActionEvent event) {
    	Game game = this.pCtrl.getPatience().getGame();
    	
    	if(game.getClass().equals(AcesUp.class)){
    		AcesUpController acesUpController = this.pCtrl.getAcesUpController();
    		acesUpController.cancelGame();    		
    	}
    	if(game.getClass().equals(FreeCell.class)){
    		FreeCellController freeCellController = this.pCtrl.getFreeCellController();
    		freeCellController.cancelGame();   	  		
    	}
    	if(game.getClass().equals(Zank.class)){
    		ZankController zankController = this.pCtrl.getZankController();
    		zankController.cancelGame();
    	}
    	Stage stage = (Stage) pane.getScene().getWindow();
    	stage.close();  
    	parentStage.close();
    }

    @FXML
    void savingGameButtonClicked(ActionEvent event) {
		Stage stage = (Stage) pane.getScene().getWindow();
		FileChooser fileChooser = new FileChooser();
		
		String currentGame;
		if(this.pCtrl.getPatience().getGame().getClass().equals(AcesUp.class)){
			currentGame="AcesUp";
			
		}else{ 
			if(this.pCtrl.getPatience().getGame().getClass().equals(FreeCell.class)){			
				currentGame="FreeCell";
			}
			else{
				currentGame = "Zank";
			}
		}
		
		fileChooser.getExtensionFilters().add(new ExtensionFilter(currentGame + "Spielstand", "*.patience"));
		fileChooser.setInitialFileName(currentGame+ LocalDateTime.now().toString() + ".patience");
		fileChooser.setTitle("Spielstand speichern");
		File file = fileChooser.showSaveDialog(stage);
		if (file != null) {
			Game game = this.pCtrl.getPatience().getGame();
			this.pCtrl.getiOController().saveGame(game, file.getAbsolutePath());
			stage.close();			
		}
		parentStage.close();
    }
    
    public void setPatienceController(PatienceController patienceController){
    	this.pCtrl = patienceController;
    }
    
    public void setEvent(WindowEvent event){
    	this.windowEvent = event;
    }
    
    public void setWindow(Stage stage){
    	this.parentStage = stage;
    }
    
    public void setHauptfensterController(HauptfensterController hauptfensterController){
    	this.hauptfensterController = hauptfensterController;
    }
}
