package controller;

import aui.FreeCellAUI;
import exceptions.NoMovesPossibleException;
import exceptions.NotEnoughCardsOnPlaystacksException;
import model.Card;
import model.Cardstack;
import model.FreeCell;
import model.Move;
import model.Rank;
import model.SingleplayerStatistics;
import model.Type;

/**
 * @author sopr047
 *
 */
public class FreeCellController extends GameController {

	private FreeCellAUI freeCellAUI;

	private FreeCell game;


	/**
	 * bewege Karten auf HomeCells
	 * 
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#initGame()
	 */
	@Override
	public void initGame() {

		game = (FreeCell) patienceController.getPatience().getGame();

		if (game.isNewGame()) {
			Cardstack tmp = new Cardstack(Type.DEFAULT);

			for (Card card : game.getStartStack()) {
				tmp.push(card);

			}

			for (int i = 0; i < 52; i++) {
				game.getPlayingStacks()[i % 8].push(tmp.pop());
			}

			game.setNewGame(false);
		}
		this.freeCellAUI.initializeStacks();

	}

	public void setFreeCellAUI(FreeCellAUI freeCellAUI) {
		this.freeCellAUI = freeCellAUI;
	}

	public FreeCellAUI getFreeCellAUI() {
		return this.freeCellAUI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param Cardstack Stapel auf der die Karte liegt
	 * 
	 * @param Card Karte die gezogen werden soll
	 */
	@Override
	public boolean isDraggable(Cardstack stack, Card card) {

		if (stack.getType().equals(Type.PLAYINGSTACK)) {
			// TODO: prüft, wieviele FreeCells frei sind
			int count = 0;
			for (Cardstack freeCell : game.getFreeCells()) {
				if (freeCell.size() == 0)
					count++;
			}

			// die Länge der Strasse
			int length = stack.countCardsInRow(card);
			if (length <= count + 1 && length > 0)
				return true;
			else
				return false;

		}

		// freecell ist immer ziehbar
		else if (stack.getType().equals(Type.FREECELL)) {

			return true;
		}
		// Homecell nicht ziehbar
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#isDroppable(model.Cardstack)
	 */
	@Override
	public boolean isDroppable(Cardstack targetStack) {

		// unterste Karte im gezogenem stapel
		if (stackToDrag.size() > 0) {
			Card root = getStackToDrag().firstElement();

			switch (targetStack.getType()) {
			case PLAYINGSTACK:
				return isplayingStackDroppable(targetStack, root);
			case HOMECELL:
				return isHomeCellDroppable(targetStack, root);
			case FREECELL:
				return isFreeCellDroppable(targetStack);
			default:
				return false;

			}
		} else {
			return false;
		}
	}

	/**
	 * @param targetStack
	 * @param root
	 * @return true, wenn auf HomeCell dropbar, sonst false
	 */
	private boolean isHomeCellDroppable(Cardstack targetStack, Card root) {
		// mehr als eine Karte gezogen
		int size = 1;
		if (getStackToDrag().size() != size) {
			return false;
		}

		else {

			if (targetStack.size() == 0) {
				if (root.getRank().equals(Rank.ACE)) {
					return true;
				}

			} else {
				if (targetStack.peek().sameColorSorted(root)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * @param targetStack
	 * @return true, wenn auf FreeCell dropbar, sonst false
	 */
	private boolean isFreeCellDroppable(Cardstack targetStack) {
		// mehr als eine Karte gezogen
		int size = 1;
		if (getStackToDrag().size() != size) {
			return false;
		}
		// wenn freecell nicht belegt
		return targetStack.size() == 0;
	}

	/**
	 * @param targetStack
	 * @param root
	 * @return true, wenn auf dem Spielfeld dropbar, sonst false
	 */
	private boolean isplayingStackDroppable(Cardstack targetStack, Card root) {

		if (targetStack.size() == 0) {
			return true;
		}

		else {
			return targetStack.peek().isAltDesc(root);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#isFinished()
	 */
	@Override
	public boolean isFinished() {
		int sum = 0;
		for (Cardstack cardstack : game.getHomeCells()) {
			sum += cardstack.size();
		}

		if (sum == game.getStartStack().size()) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#autoPlay()
	 */
	@Override
	public void autoPlay() {
		while (true) { // TODO interrupt durch button o.ä.
			showTipp();
		}
	}

	@Override
	public void showTipp() {
		try {
			executeMove(aIController.getNextMove());
		} catch (NotEnoughCardsOnPlaystacksException e) {
			// TODO finished
		} catch (NoMovesPossibleException e) {
			// TODO ausgabe: KI fertig.
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#undoMove()
	 */
	@Override
	public void undoMove() {

		if (!game.getHistory().isEmpty()) {
			Move tempMove = game.getHistory().pop();
			if (tempMove != null) {
				executeMove(tempMove.revertMove());
				game.getHistory().pop();
			}
		}
		// refreshKarte();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#addStatistics()
	 */
	@Override
	public void addStatistics() {

		SingleplayerStatistics statistic = this.game.getSingleplayerStatistics();
		patienceController.getPatience().getStatistics().addFreeCellStatistic(statistic);
		patienceController.getiOController().saveStatistics();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#restartGame()
	 */
	@Override
	public void restartGame() {
		this.patienceController.getPatience().setGame(new FreeCell(this.game.getStartStack()));
		this.game = (FreeCell) this.patienceController.getPatience().getGame();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#cancelGame()
	 */
	@Override
	public void cancelGame() {
		// TODO Auto-generated method stub
		addStatistics();
		this.game= null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#refreshKarte()
	 */
	@Override
	public void refreshKarte() {
		freeCellAUI.refreshAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#getPatienceController()
	 */
	public PatienceController getPatienceController() {
		return patienceController;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#setPatienceController(controller.
	 * PatienceController)
	 */
	public void setPatienceController(PatienceController patienceController) {
		this.patienceController = patienceController;
		game = (FreeCell) patienceController.getPatience().getGame();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#executeMove(model.Move)
	 */
	@Override
	public void executeMove(Move moveToExecute) {
		// TODO executeMove: ersetze diesen teil durch animation

		freeCellAUI.animateMove(moveToExecute);
		Cardstack moveFrom = moveToExecute.getFrom();
		Cardstack moveTo = moveToExecute.getTo();
		int size = moveToExecute.getNumberOfCards();

		Cardstack tmp = new Cardstack(Type.DEFAULT);

		for (int i = 0; i < size; i++) {
			tmp.push(moveFrom.pop());
		}

		for (int i = 0; i < size; i++) {
			moveTo.push(tmp.pop());
		}

		// freeCellAUI.refreshAll();

		game.getHistory().push(moveToExecute);
		
		if(isFinished()){
			freeCellAUI.openFinishedWindow("AcesUp",0);
		}

	}
}
