package application;

import java.io.IOException;
import java.util.ArrayList;

import aui.ZankAUI;
import controller.AnimationController;
import controller.ZankController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Card;
import model.Cardstack;
import model.Move;
import model.Type;
import model.Zank;
import movecontroller.DragDoneController;
import movecontroller.DragDropController;
import movecontroller.DragDropEnteredController;
import movecontroller.DragDropExitController;
import movecontroller.DragOverController;
import movecontroller.DragStartController;

public class ZankGUIController extends GameGUIController implements ZankAUI {

	@FXML
	private AnchorPane mainPane;

	@FXML
	private Pane ersatzstapelGegner;

	@FXML
	private Pane nachziehstapelGegner;

	@FXML
	private Pane ablegestapelGegner;

	@FXML
	private Pane anlegestapel1;

	@FXML
	private Pane anlegestapel2;

	@FXML
	private Pane anlegestapel3;

	@FXML
	private Pane anlegestapel4;

	@FXML
	private Pane anlegestapel5;

	@FXML
	private Pane anlegestapel6;

	@FXML
	private Pane anlegestapel7;

	@FXML
	private Pane anlegestapel8;

	@FXML
	private Pane mitteKaro1;

	@FXML
	private Pane mitteKaro2;

	@FXML
	private Pane mittePik1;

	@FXML
	private Pane mittePik2;

	@FXML
	private Pane mitteKreuz1;

	@FXML
	private Pane mitteKreuz2;

	@FXML
	private Pane mitteHerz1;

	@FXML
	private Pane mitteHerz2;

	@FXML
	private Pane ablegestapelSpieler;

	@FXML
	private Pane nachziehstapelSpieler;

	@FXML
	private Pane ersatzstapelSpieler;

	private Zank game;
	private ZankController controller;

	public ArrayList<Pane> playingPanes;
	public ArrayList<Pane> eliminationPanes;
	public ArrayList<Pane> handPanes;
	public ArrayList<Pane> reservePanes;
	public ArrayList<Pane> wastePanes;

	public Cardstack[] playingstacks;
	public Cardstack[] eliminationstacks;
	public Cardstack[] hands;
	public Cardstack[] reserves;
	public Cardstack[] wastepiles;

	/*
	 * (non-Javadoc)
	 * 
	 * @see application.GameGUIController#init()
	 */
	@Override
	public void init() {
		patienceController.getZankController().setZankAUI(this);

		controller = patienceController.getZankController();

		game = (Zank) patienceController.getPatience().getGame();

		mainPane.setOnDragOver(new DragOverController());
		mainPane.setOnDragDropped(new DragDropController(this.patienceController.getFreeCellController()));

		EventHandler<MouseEvent> clickHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
					if (controller.getActivePlayer() == 0) {
						if (mouseEvent.getSource().equals(nachziehstapelSpieler)) {
							controller.wastePileToHand();
						}
					} else {
						if (mouseEvent.getSource().equals(nachziehstapelGegner)) {
							controller.wastePileToHand();
						}
					}
				}
			}
		};

		nachziehstapelSpieler.setOnMouseClicked(clickHandler);

		nachziehstapelGegner.setOnMouseClicked(clickHandler);

		patienceController.getZankController().initGame();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see aui.ZankAUI#refreshAll()
	 */
	@Override
	public void refreshAll() {
		getStacks();
		clearPanes(playingPanes);
		clearPanes(eliminationPanes);
		clearPanes(handPanes);
		clearPanes(reservePanes);
		clearPanes(wastePanes);
		refreshPlayingfield();
		refreshPlayerstacks();
	}

	/**
	 * gets updated cardstacks from game
	 */
	private void getStacks() {

		playingstacks = game.getPlayingStacks();
		eliminationstacks = game.getEliminationStacks();
		hands = game.getHands();
		reserves = game.getReserves();
		wastepiles = game.getWastePiles();

	}

	/**
	 * clears all panes in list
	 * 
	 * @param listOfPanes
	 *            list of panes to clear
	 */
	private void clearPanes(ArrayList<Pane> listOfPanes) {
		for (Pane pane : listOfPanes) {
			pane.getChildren().clear();
		}
	}

	/**
	 * refreshs all cards on playerStacks
	 */
	private void refreshPlayerstacks() {
		for (int i = 0; i < handPanes.size(); i++) {
			refreshPane(handPanes.get(i), hands[i]);
			hands[i].setLocation(i);
			refreshPane(reservePanes.get(i), reserves[i]);
			reserves[i].setLocation(i);
			refreshPane(wastePanes.get(i), wastepiles[i]);
			wastepiles[i].setLocation(i);

		}
	}

	/**
	 * refreshs all cards on playingstacks
	 */
	private void refreshPlayingfield() {
		for (int i = 0; i < playingPanes.size(); i++) {
			refreshPane(playingPanes.get(i), playingstacks[i]);
			playingstacks[i].setLocation(i);
			refreshPane(eliminationPanes.get(i), eliminationstacks[i]);
			eliminationstacks[i].setLocation(i);
		}
	}

	/**
	 * puts cards on panes
	 * 
	 * @param pane
	 *            pane to put cards on
	 * @param cardstack
	 *            cards to put on
	 */
	private void refreshPane(Pane pane, Cardstack cardstack) {
		ZankController tmp = patienceController.getZankController();
		for (int i = 0; i < cardstack.size(); i++) {
			Card card = cardstack.get(i);
			Image cardImage;
			if (cardstack.getType().equals(Type.HAND)
					&& (!cardstack.equals(game.getHands()[tmp.getActivePlayer()]) || i != cardstack.size() - 1)) {
				cardImage = card.getBackImage();
			} else {
				cardImage = card.getFaceImage();
			}
			ImageView cardImageView = new ImageView(cardImage);
			cardImageView.setFitWidth(95 - 5);
			cardImageView.setFitHeight(138 - 5);
			cardImageView.setLayoutX(2.5);
			cardImageView.setLayoutY(2.5);
			pane.getChildren().add(cardImageView);
			if (cardstack.getType().equals(Type.PLAYINGSTACK)) {
				if (playingPanes.indexOf(pane) < playingPanes.size() / 2) {
					if (cardstack.size() < 8) {
						cardImageView.setLayoutX(i * -30 + 2.5);
					} else {
						cardImageView.setLayoutX(i * -240 / cardstack.size() + 2.5);
					}
				} else {
					if (cardstack.size() < 8) {
						cardImageView.setLayoutX(i * 30 + 2.5);
					} else {
						cardImageView.setLayoutX(i * 240 / cardstack.size() + 2.5);
					}
				}
			} else {
				cardImageView.setLayoutX(2.5);
				cardImageView.setLayoutY(i * -0.33 + 2.5);
			}
			// setzt DragController für eine Karte

			cardImageView.setOnDragDetected(new DragStartController(tmp, cardImageView, cardstack, card));
			cardImageView.setOnDragOver(new DragOverController(tmp, cardstack));
			cardImageView.setOnDragDropped(new DragDropController(tmp));
			cardImageView.setOnDragDone(new DragDoneController(tmp));

		}

		// spalte.setPrefHeight((stapel.size()-1)*30+153);
		pane.setOnDragDropped(new DragDropController(tmp));
		pane.setOnDragEntered(new DragDropEnteredController(tmp, cardstack, pane));
		pane.setOnDragExited(new DragDropExitController(tmp, pane));
		pane.setOnDragOver(new DragOverController());
		pane.setOnDragDone(new DragDoneController(tmp));

	}

	@FXML
	public void initialize() {
		playingPanes = new ArrayList<>();
		eliminationPanes = new ArrayList<>();
		handPanes = new ArrayList<>();
		reservePanes = new ArrayList<>();
		wastePanes = new ArrayList<>();

		playingPanes.add(anlegestapel1);
		playingPanes.add(anlegestapel2);
		playingPanes.add(anlegestapel3);
		playingPanes.add(anlegestapel4);
		playingPanes.add(anlegestapel5);
		playingPanes.add(anlegestapel6);
		playingPanes.add(anlegestapel7);
		playingPanes.add(anlegestapel8);

		eliminationPanes.add(mitteKaro1);
		eliminationPanes.add(mitteKaro2);
		eliminationPanes.add(mittePik1);
		eliminationPanes.add(mittePik2);
		eliminationPanes.add(mitteKreuz1);
		eliminationPanes.add(mitteKreuz2);
		eliminationPanes.add(mitteHerz1);
		eliminationPanes.add(mitteHerz2);

		handPanes.add(nachziehstapelSpieler);
		handPanes.add(nachziehstapelGegner);

		reservePanes.add(ersatzstapelSpieler);
		reservePanes.add(ersatzstapelGegner);

		wastePanes.add(ablegestapelSpieler);
		wastePanes.add(ablegestapelGegner);
	}

	@Override
	public void openFinishedWindow(String type, int player) {

		AnchorPane root = new AnchorPane();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FinishedWindow.fxml"));
		Stage window = new Stage();
		try {			
			root = (AnchorPane) fxmlLoader.load();
			Scene scene = new Scene(root);
			
			window.setScene(scene);
			window.setResizable(false);
			window.setTitle("Gewonnen!");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FinishedWindowController finishedCtrl = fxmlLoader.getController();
		finishedCtrl.setLabel("Spieler "+ player + " hat gewonnen");
		window.show();
		
	}

	@Override
	public void animateMove(Move move) {
		AnimationController animation = new AnimationController(this, move, mainPane);
		animation.doAnimation(false);
	}
}
