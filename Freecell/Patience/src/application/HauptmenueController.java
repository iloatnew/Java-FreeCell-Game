package application;


import java.io.File;
import java.io.IOException;

import controller.PatienceController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import model.AcesUp;
import model.FreeCell;
import model.Game;
import model.Zank;

public class HauptmenueController {	

	@FXML
	private AnchorPane hauptmenuePane;
	
	@FXML
	private ImageView idiotenImage;
	
	@FXML
	private ImageView freeCellImage;
	
	@FXML
	private ImageView zankImage;
	

    @FXML // fx:id="spielImportieren"
    private Button spielImportieren; // Value injected by FXMLLoader
	
	private String freeCell = "FreeCell";
	
	private String idioten = "Idioten";
	
	private String zank = "Zank";
	
	private PatienceController patienceCtr;
	
	@FXML
    void openFreeCell(MouseEvent event) throws IOException {
		spielLadenFensterOeffnen(freeCell);
    }

    @FXML
    void openIdioten(MouseEvent event) throws IOException {
    	
    	spielLadenFensterOeffnen(idioten);
    }

    public String getFreeCell() {
		return freeCell;
	}

	public void setFreeCell(String freeCell) {
		this.freeCell = freeCell;
	}

	public String getIdioten() {
		return idioten;
	}

	public void setIdioten(String idioten) {
		this.idioten = idioten;
	}

	public String getZank() {
		return zank;
	}

	public void setZank(String zank) {
		this.zank = zank;
	}

	@FXML
    void openZank(MouseEvent event) throws IOException {
    	spielLadenFensterOeffnen(zank);
    }
    private void spielLadenFensterOeffnen(String spiel) throws IOException {	
    	if(spiel.equals("Idioten") || spiel.equals("FreeCell")){
    		oeffneFenster(spiel);
    	}
    	else{
    		oeffneZankFenster();
    	}
		
    }
    private void oeffneFenster(String spiel) throws IOException {
    	FXMLLoader singlePlayerFxmlLoader = new FXMLLoader(getClass().getResource("SpielLaden.fxml"));
    	Parent root1 = (Parent) singlePlayerFxmlLoader.load();
    	Stage stage = new Stage();    	
    	SpielLadenController spielLadenCtr = (SpielLadenController) singlePlayerFxmlLoader.getController();
    	spielLadenCtr.setpCtrl(patienceCtr);
    	spielLadenCtr.setSpielTyp(spiel);
    	System.out.println(spiel);
		stage.setResizable(false);
		if(spielLadenCtr.getSpielTyp() == idioten || spielLadenCtr.getSpielTyp() == freeCell){    		
    		stage.setTitle("Spieloptionen");
    		if(spielLadenCtr.getSpielTyp() == idioten){
    			spielLadenCtr.setSpielVarianteLabel("Idioten Patience spielen");
    		}
    		if(spielLadenCtr.getSpielTyp() == freeCell){
    			spielLadenCtr.setSpielVarianteLabel("FreeCell Patience spielen");
    		}
    	} 
		stage.setScene(new Scene(root1));
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(hauptmenuePane.getScene().getWindow());
		stage.showAndWait();		
    }
    
    public void oeffneZankFenster() throws IOException{
    	FXMLLoader multiPlayerFxmlLoader = new FXMLLoader(getClass().getResource("ZankLaden.fxml"));
    	Parent root = (Parent) multiPlayerFxmlLoader.load();
    	Stage multiStage = new Stage();    	
    	ZankLadenController zankLadenCtr = (ZankLadenController) multiPlayerFxmlLoader.getController();
    	zankLadenCtr.setPatienceCtrl(patienceCtr);
    	multiStage.setResizable(false);  
    	multiStage.setTitle("Spieloptionen");
    	multiStage.setScene(new Scene(root));
    	multiStage.initModality(Modality.APPLICATION_MODAL);
    	multiStage.initOwner(hauptmenuePane.getScene().getWindow());
    	multiStage.showAndWait();
    }
    
    
    @FXML
    void hoverFreeCellImage(MouseEvent event) {

    }

    @FXML
    void hoverIdiotenImage(MouseEvent event) {

    }

    @FXML
    void hoverZankImage(MouseEvent event) {

    }
    
    public void initialize(){
    	
    }

	public void setPatienceController(PatienceController patienceController) {
		System.out.println("set aufgerufen hptfst");
		this.patienceCtr = patienceController;
	}
	
	@FXML
	void spielImport(ActionEvent event) {
		Stage stage = (Stage) hauptmenuePane.getScene().getWindow();
		FileChooser chooser = new FileChooser();
		String type = "";

		chooser.setTitle("Spielstand laden");
		chooser.getExtensionFilters().addAll(new ExtensionFilter("Patience Spielstand", "*.patience"));
		File file = chooser.showOpenDialog(stage);
		if (file != null) {
			String path = file.getPath();
			Game game = this.patienceCtr.getiOController().loadGame(path);
			patienceCtr.getPatience().setGame(game);

			if (game instanceof FreeCell)
				type = "FreeCell.fxml";
			else if (game instanceof AcesUp)
				type = "Idioten.fxml";
			else if (game instanceof Zank)
				type = "Zank.fxml";
			try {
				AnchorPane root = new AnchorPane();
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Hauptfenster.fxml"));
				root = (AnchorPane) fxmlLoader.load();
				root.setId("pane");
				HauptfensterController hauptfensterCtrl = fxmlLoader.getController();
				hauptfensterCtrl.setPatienceCtrl(patienceCtr);
				hauptfensterCtrl.initSpielfeld(type);
				Scene scene = new Scene(root);
				Stage window = new Stage();
				window.setOnCloseRequest(new EventHandler<WindowEvent>() {
					public void handle(WindowEvent event) {
				    	System.out.println("Stage is closing");	
				    	hauptfensterCtrl.pauseButtonClicked(null);
				    	openClosingRequestWindow(window, event, hauptfensterCtrl);		   				    	
				    }
				});
				window.setScene(scene);
				window.setResizable(false);
				window.show();

			} catch (Exception e) {
				e.printStackTrace();
			}
			stage.close();
			//Stage parentwindow = (Stage) stage.getOwner();
			//parentwindow.close();
		}

	}
	
	/**
	 * @param window, event
	 * opens a modal dialog which asks the user if the game should be dismissed or saved.
	 * For more information @see application.SpielBeendenController 
	 */
	private void openClosingRequestWindow(Stage window, WindowEvent event, HauptfensterController hautfensterController){
		try{
			AnchorPane closingRequestPane = new AnchorPane();
	    	FXMLLoader closingRequestLoader = new FXMLLoader(getClass().getResource("ClosingRequest.fxml"));
	    	closingRequestPane = (AnchorPane) closingRequestLoader.load();
	    	SpielBeendenController spielBeendenController = closingRequestLoader.getController();
	    	spielBeendenController.setPatienceController(this.patienceCtr);
	    	spielBeendenController.setEvent(event);	   
	    	spielBeendenController.setWindow(window);
	    	spielBeendenController.setHauptfensterController(hautfensterController);
	    	Scene closingRequestScene = new Scene(closingRequestPane);
	    	Stage closingRequestStage = new Stage();	    	
	    	closingRequestStage.setScene(closingRequestScene);
	    	closingRequestStage.setResizable(false);
	    	closingRequestStage.initStyle(StageStyle.UNDECORATED);
	    	closingRequestStage.initModality(Modality.APPLICATION_MODAL);
	    	closingRequestStage.setTitle("Wollen Sie wirklich schon aufhören?");
	    	closingRequestStage.showAndWait();
	    }
    	catch(IOException exception){
    		exception.printStackTrace();
    	}
	}

}
