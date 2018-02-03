package application;

import java.io.IOException;import java.util.ArrayList;

import aui.FreeCellAUI;
import controller.AnimationController;
import controller.GameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Card;
import model.Cardstack;
import model.FreeCell;
import model.Move;
import model.Type;
import movecontroller.DragDoneController;
import movecontroller.DragDropController;
import movecontroller.DragDropEnteredController;
import movecontroller.DragDropExitController;
import movecontroller.DragOverController;
import movecontroller.DragStartController;

public class FreeCellGUIController extends GameGUIController implements FreeCellAUI {
	@FXML
	AnchorPane hauptPane;
	@FXML
	Button undo;

	@FXML
	Pane freecell1;
	@FXML
	Pane freecell2;
	@FXML
	Pane freecell3;
	@FXML
	Pane freecell4;

	@FXML
	Pane homecell1;
	@FXML
	Pane homecell2;
	@FXML
	Pane homecell3;
	@FXML
	Pane homecell4;

	@FXML
	Pane spalte1;
	@FXML
	Pane spalte2;
	@FXML
	Pane spalte3;
	@FXML
	Pane spalte4;
	@FXML
	Pane spalte5;
	@FXML
	Pane spalte6;
	@FXML
	Pane spalte7;
	@FXML
	Pane spalte8;

	public Cardstack[] playingStacks;
	public Cardstack[] freeCells;
	public Cardstack[] homeCells;
	public ArrayList<Pane> spielfeld;
	public ArrayList<Pane> homecell;
	public ArrayList<Pane> freecell;

	@Override
	public void init() {

		patienceController.getFreeCellController().setFreeCellAUI(this);
		patienceController.getFreeCellController().initGame();
	}

	@FXML
	public void initialize() {
		spielfeld = new ArrayList<Pane>();
		freecell = new ArrayList<Pane>();
		homecell = new ArrayList<Pane>();

		spielfeld.add(spalte1);
		spielfeld.add(spalte2);
		spielfeld.add(spalte3);
		spielfeld.add(spalte4);
		spielfeld.add(spalte5);
		spielfeld.add(spalte6);
		spielfeld.add(spalte7);
		spielfeld.add(spalte8);
		homecell.add(homecell1);
		homecell.add(homecell2);
		homecell.add(homecell3);
		homecell.add(homecell4);
		freecell.add(freecell1);
		freecell.add(freecell2);
		freecell.add(freecell3);
		freecell.add(freecell4);

	}

	/**
	 * kann von Drag/ Drop-Controller geruft werden
	 */

	@Override
	public void initializeStacks() {
		patienceController.getFreeCellController().setFreeCellAUI(this);

		playingStacks = ((FreeCell) patienceController.getPatience().getGame()).getPlayingStacks();
		freeCells = ((FreeCell) patienceController.getPatience().getGame()).getFreeCells();
		homeCells = ((FreeCell) patienceController.getPatience().getGame()).getHomeCells();
		hauptPane.setOnDragOver(new DragOverController());
		hauptPane.setOnDragDropped(new DragDropController(this.patienceController.getFreeCellController()));

		refreshAll();
	}

	/**
	 * setzt ein Stapel in einer Spalte
	 * 
	 * @param Stapeln
	 */
	private void refreshSpalten() {
		for (int i = 0; i < spielfeld.size(); i++) {
			this.setFreeCellStacks(spielfeld.get(i), playingStacks[i]);
			playingStacks[i].setLocation(i);
		}
		for (int i = 0; i < freecell.size(); i++) {
			this.setFreeCellStacks(freecell.get(i), freeCells[i]);
			this.setFreeCellStacks(homecell.get(i), homeCells[i]);
			freeCells[i].setLocation(i);
			homeCells[i].setLocation(i);
		}
	}

	/**
	 * alle Karte soll untereinander gesetzt werden DragController für jede
	 * Karte setzen
	 * 
	 * @param row
	 * @param stack
	 */

	public void setFreeCellStacks(Pane row, Cardstack stack) {

		GameController tmp = patienceController.getFreeCellController();

		// nur f�r Stacktype playingstack wird schleife mehr als 1 mal
		// durchlaufen
		// bei allen anderen Typen nur einmal

		for (int i = 0; i < stack.size(); i++) {
			Card karte = stack.get(i);
			Image karteImg = karte.getFaceImage();
			ImageView karteImgView = new ImageView(karteImg);
			karteImgView.setLayoutX(5);
			karteImgView.setLayoutY(5);
			row.getChildren().add(karteImgView);
			if (stack.getType().equals(Type.PLAYINGSTACK)) {
				karteImgView.setLayoutY(i * 30);
			} else  if (stack.getType().equals(Type.HOMECELL)) {
				karteImgView.setLayoutY(5 + i * -0.33);
			}
			// setzt DragController für eine Karte

			karteImgView.setOnDragDetected(new DragStartController(tmp, karteImgView, stack, karte));
			karteImgView.setOnDragOver(new DragOverController(tmp, stack));
			karteImgView.setOnDragDropped(new DragDropController(tmp));
			karteImgView.setOnDragDone(new DragDoneController(tmp));
			karteImgView.setOnMouseClicked(e -> {
				if (tmp.isDraggable(stack, karte)) {
					if (e.getClickCount() == 2) {
						autoByClick(stack, karte);
					}
				}

			});

		}

		// spalte.setPrefHeight((stapel.size()-1)*30+153);
		row.setOnDragDropped(new DragDropController(tmp));
		row.setOnDragEntered(new DragDropEnteredController(tmp, stack, row));
		row.setOnDragExited(new DragDropExitController(tmp, row));
		row.setOnDragOver(new DragOverController());
		row.setOnDragDone(new DragDoneController(tmp));
	}

	private void autoByClick(Cardstack stack, Card karte) {
		if (karte.getRank().ordinal() == 0) {
			for (Cardstack home : ((FreeCell) patienceController.getPatience().getGame()).getHomeCells()) {
				if (home.size() == 0) {
					Move tmp = new Move(stack, home, 1);
					patienceController.getFreeCellController().executeMove(tmp);
					patienceController.getPatience().getGame().getHistory().push(tmp);
					break;
				}
			}
		} else {
			for (Cardstack home : ((FreeCell) patienceController.getPatience().getGame()).getHomeCells()) {
				if (home.size() > 0) {
					if (home.peek().sameColorSorted(karte)) {
						Move tmp = new Move(stack, home, 1);
						patienceController.getFreeCellController().executeMove(tmp);
						patienceController.getPatience().getGame().getHistory().push(tmp);
						break;
					}
				}
			}
		}

	}

	@Override
	public void refreshAll() {
		for (Pane freecell : this.freecell) {
			freecell.getChildren().clear();
		}
		for (Pane homecell : this.homecell) {
			homecell.getChildren().clear();
		}
		for (Pane field : this.spielfeld) {
			field.getChildren().clear();
		}
		refreshSpalten();
	}

	@FXML
	public void undo() {
		patienceController.getFreeCellController().undoMove();
	}

	@Override
	public void animateMove(Move move) {
		AnimationController animation = new AnimationController(this, move, hauptPane);
		animation.doAnimation(false);
	}


	@Override
	public void openFinishedWindow(String string, int i) {
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
		finishedCtrl.setLabel("Du hast Gewonnen!");
		window.show();	
	}
}
