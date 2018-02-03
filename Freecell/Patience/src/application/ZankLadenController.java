/**
 * Sample Skeleton for 'ZankLaden.fxml' Controller Class
 */

package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.DeckController;
import controller.PatienceController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import model.Cardstack;
import model.Player;
import model.Zank;

public class ZankLadenController {

	private PatienceController patienceCtrl;
	private File deckFile1;
	private File deckFile2;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="spielLadenPane"
	private AnchorPane spielLadenPane; // Value injected by FXMLLoader

	@FXML // fx:id="player1Label"
	private Label player1Label; // Value injected by FXMLLoader

	@FXML // fx:id="player1"
	private ChoiceBox<String> player1; // Value injected by FXMLLoader

	@FXML // fx:id="player2Label"
	private Label player2Label; // Value injected by FXMLLoader

	@FXML // fx:id="player2"
	private ChoiceBox<String> player2; // Value injected by FXMLLoader

	@FXML // fx:id="beginner"
	private Label beginner; // Value injected by FXMLLoader

	@FXML // fx:id="startPlayer1"
	private RadioButton startPlayer1; // Value injected by FXMLLoader

	@FXML // fx:id="startplayer"
	private ToggleGroup startplayer; // Value injected by FXMLLoader

	@FXML // fx:id="startPlayer2"
	private RadioButton startPlayer2; // Value injected by FXMLLoader

	@FXML // fx:id="deckLadenS1Button"
	private Button deckLadenS1Button; // Value injected by FXMLLoader

	@FXML // fx:id="spielStartenButton"
	private Button spielStartenButton; // Value injected by FXMLLoader

	@FXML // fx:id="player1Label1"
	private Label player1Label1; // Value injected by FXMLLoader

	@FXML // fx:id="zufälligesDeckButtonS1"
	private RadioButton zufälligesDeckButtonS1; // Value injected by FXMLLoader

	@FXML // fx:id="deckS1"
	private ToggleGroup deckS1; // Value injected by FXMLLoader

	@FXML // fx:id="eigenesDeckButtonS1"
	private RadioButton eigenesDeckButtonS1; // Value injected by FXMLLoader

	@FXML // fx:id="deckLadenS2Button"
	private Button deckLadenS2Button; // Value injected by FXMLLoader

	@FXML // fx:id="zufälligesDeckButtonS2"
	private RadioButton zufälligesDeckButtonS2; // Value injected by FXMLLoader

	@FXML // fx:id="deckS2"
	private ToggleGroup deckS2; // Value injected by FXMLLoader

	@FXML // fx:id="eigenesDeckButtonS2"
	private RadioButton eigenesDeckButtonS2; // Value injected by FXMLLoader

	@FXML
	void deckLadenS1ButtonClicked(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Kartendeck für Spieler 1 auswählen");
		deckFile1 = fileChooser.showOpenDialog(spielLadenPane.getScene().getWindow());
		if (deckFile1 != null) {
			deckLadenS1Button.setText(deckFile1.getName());
		}
	}

	@FXML
	void deckLadenS2ButtonClicked(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Kartendeck für Spieler 2 auswählen");
		deckFile2 = fileChooser.showOpenDialog(spielLadenPane.getScene().getWindow());
		if (deckFile2 != null) {
			deckLadenS2Button.setText(deckFile2.getName());
		}
	}

	@FXML
	void spielStartenButtonClicked(ActionEvent event) {
		Cardstack cs1, cs2;
		Player p1, p2;
		try {
			if (zufälligesDeckButtonS1.isSelected()) {
				cs1 = new DeckController().randomDeck();
			} else if (deckFile1 != null) {
				cs1 = this.patienceCtrl.getiOController().readCardstackFromFile(deckFile1.getPath());
			} else {
				Alert alert = new Alert(AlertType.ERROR, "Keine Datei für Deck 1 ausgewählt");
				alert.show();
				throw new Exception("Keine Datei für Deck 1 ausgewählt");

			}
			if (zufälligesDeckButtonS2.isSelected()) {
				cs2 = new DeckController().randomDeck();
			} else if (deckFile2 != null) {
				cs2 = this.patienceCtrl.getiOController().readCardstackFromFile(deckFile2.getPath());
			} else {
				Alert alert = new Alert(AlertType.ERROR, "Keine Datei für Deck 2 ausgewählt");
				alert.show();
				throw new Exception("Keine Datei für Deck 2 ausgewählt");
			}

			if (player1.getSelectionModel().getSelectedItem().equals("Mensch"))
				p1 = Player.PLAYER;
			else if (player1.getSelectionModel().getSelectedItem().equals("KI Leicht"))
				p1 = Player.AIEASY;
			else if (player1.getSelectionModel().getSelectedItem().equals("KI Mittel"))
				p1 = Player.AINORMAL;
			else if (player1.getSelectionModel().getSelectedItem().equals("KI Schwer"))
				p1 = Player.AIPRO;
			else
				throw new Exception("Fehler bei Spielerauswahl");

			if (player2.getSelectionModel().getSelectedItem().equals("Mensch"))
				p2 = Player.PLAYER;
			else if (player2.getSelectionModel().getSelectedItem().equals("KI Leicht"))
				p2 = Player.AIEASY;
			else if (player2.getSelectionModel().getSelectedItem().equals("KI Mittel"))
				p2 = Player.AINORMAL;
			else if (player2.getSelectionModel().getSelectedItem().equals("KI Schwer"))
				p2 = Player.AIPRO;
			else
				throw new Exception("Fehler bei Spielerauswahl");

			this.patienceCtrl.getPatience().setGame(new Zank(cs1, cs2, p1, p2, startPlayer1.isSelected()));

			AnchorPane root = new AnchorPane();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Hauptfenster.fxml"));
			root = (AnchorPane) fxmlLoader.load();
			root.setId("pane");
			HauptfensterController hauptfensterCtrl = fxmlLoader.getController();
			hauptfensterCtrl.setPatienceCtrl(patienceCtrl);
			hauptfensterCtrl.initSpielfeld("Zank.fxml");
			Scene scene = new Scene(root);
			Stage window = new Stage();
			window.setScene(scene);
			window.setResizable(false);
			window.setTitle("Zank-Patience");
			window.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					System.out.println("Stage is closing");
					hauptfensterCtrl.pauseButtonClicked(null);
					AnchorPane closingRequestPane = new AnchorPane();
					FXMLLoader closingRequestLoader = new FXMLLoader(getClass().getResource("ClosingRequest.fxml"));
					try {
						closingRequestPane = (AnchorPane) closingRequestLoader.load();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					SpielBeendenController spielBeendenController = closingRequestLoader.getController();
					spielBeendenController.setPatienceController(patienceCtrl);
					spielBeendenController.setEvent(event);
					spielBeendenController.setWindow(window);
					spielBeendenController.setHauptfensterController(hauptfensterCtrl);
					Scene closingRequestScene = new Scene(closingRequestPane);
					Stage closingRequestStage = new Stage();
					closingRequestStage.setScene(closingRequestScene);
					closingRequestStage.setResizable(false);
					closingRequestStage.initStyle(StageStyle.UNDECORATED);
					closingRequestStage.initModality(Modality.APPLICATION_MODAL);
					closingRequestStage.setTitle("Wollen Sie wirklich schon aufhören?");
					closingRequestStage.showAndWait();
				}
			});
			window.show();
			Stage stage = (Stage) spielLadenPane.getScene().getWindow();
			stage.close();
			Stage parentwindow = (Stage) stage.getOwner();
			parentwindow.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}

	@FXML
	void eigenesDeckButtonS1Clicked(ActionEvent event) {
		deckLadenS1Button.setDisable(false);
	}

	@FXML
	void eigenesDeckButtonS2(ActionEvent event) {
		deckLadenS2Button.setDisable(false);
	}

	@FXML
	void zufaelligesDeckS1ButtonClicked(ActionEvent event) {
		deckLadenS1Button.setDisable(true);
	}

	@FXML
	void zufaelligesDeckS2ButtonClicked(ActionEvent event) {
		deckLadenS2Button.setDisable(true);
	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert spielLadenPane != null : "fx:id=\"spielLadenPane\" was not injected: check your FXML file 'ZankLaden.fxml'.";
		assert player1Label != null : "fx:id=\"player1Label\" was not injected: check your FXML file 'ZankLaden.fxml'.";
		assert player1 != null : "fx:id=\"player1\" was not injected: check your FXML file 'ZankLaden.fxml'.";
		assert player2Label != null : "fx:id=\"player2Label\" was not injected: check your FXML file 'ZankLaden.fxml'.";
		assert player2 != null : "fx:id=\"player2\" was not injected: check your FXML file 'ZankLaden.fxml'.";
		assert beginner != null : "fx:id=\"beginner\" was not injected: check your FXML file 'ZankLaden.fxml'.";
		assert startPlayer1 != null : "fx:id=\"startPlayer1\" was not injected: check your FXML file 'ZankLaden.fxml'.";
		assert startplayer != null : "fx:id=\"startplayer\" was not injected: check your FXML file 'ZankLaden.fxml'.";
		assert startPlayer2 != null : "fx:id=\"startPlayer2\" was not injected: check your FXML file 'ZankLaden.fxml'.";
		assert deckLadenS1Button != null : "fx:id=\"deckLadenS1Button\" was not injected: check your FXML file 'ZankLaden.fxml'.";
		assert spielStartenButton != null : "fx:id=\"spielStartenButton\" was not injected: check your FXML file 'ZankLaden.fxml'.";
		assert player1Label1 != null : "fx:id=\"player1Label1\" was not injected: check your FXML file 'ZankLaden.fxml'.";
		assert zufälligesDeckButtonS1 != null : "fx:id=\"zufälligesDeckButtonS1\" was not injected: check your FXML file 'ZankLaden.fxml'.";
		assert deckS1 != null : "fx:id=\"deckS1\" was not injected: check your FXML file 'ZankLaden.fxml'.";
		assert eigenesDeckButtonS1 != null : "fx:id=\"eigenesDeckButtonS1\" was not injected: check your FXML file 'ZankLaden.fxml'.";
		assert deckLadenS2Button != null : "fx:id=\"deckLadenS2Button\" was not injected: check your FXML file 'ZankLaden.fxml'.";
		assert zufälligesDeckButtonS2 != null : "fx:id=\"zufälligesDeckButtonS2\" was not injected: check your FXML file 'ZankLaden.fxml'.";
		assert deckS2 != null : "fx:id=\"deckS2\" was not injected: check your FXML file 'ZankLaden.fxml'.";
		assert eigenesDeckButtonS2 != null : "fx:id=\"eigenesDeckButtonS2\" was not injected: check your FXML file 'ZankLaden.fxml'.";
		player1.getItems().setAll("Mensch", "KI Leicht", "KI Mittel", "KI Schwer");
		player2.getItems().setAll("Mensch", "KI Leicht", "KI Mittel", "KI Schwer");
		player1.getSelectionModel().select(0);
		player2.getSelectionModel().select(0);
		deckLadenS1Button.setDisable(true);
		deckLadenS2Button.setDisable(true);
	}

	/**
	 * @return the patienceCtrl
	 */
	public PatienceController getPatienceCtrl() {
		return patienceCtrl;
	}

	/**
	 * @param patienceCtrl
	 *            the patienceCtrl to set
	 */
	public void setPatienceCtrl(PatienceController patienceCtrl) {
		this.patienceCtrl = patienceCtrl;
	}
}
