package aicontroller;

import java.util.ArrayList;

import controller.AutoMoveController;

import controller.FreeCellController;
import javafx.scene.input.KeyCombination.ModifierValue;
import model.AcesUp;

import model.Card;
import model.Cardstack;
import model.FreeCell;
import model.Move;
import model.Rank;

/**
 * @author sopr047 setGame hast to be called, whenever a move is executed
 *
 */
public class FreeCellAI {
	Card cardToReach;
	/**
	 * dummy FreeCellController to check if cards are dragable
	 */
	FreeCellController freeCellController = new FreeCellController();

	private final int EMPTY = 0;

	public ArrayList<Move> getMoves() {
		return moves;
	}

	public void setMoves(ArrayList<Move> moves) {
		this.moves = moves;
	}

	private ArrayList<Cardstack> movabelStack;
	
	private FreeCell freeCell;
	
	private ArrayList<Move> moves;

	/**
	 * contains 4 or less cards, wich are the next cards to put on HomeCellls
	 */
	public ArrayList<Card> possibleOnHomeCell = new ArrayList<>();

	private FreeCellAIController freeCellAIController;

	private AutoMoveController moveController;

	public FreeCellAI(FreeCellAIController aiController) {
		this.setFreeCellAIController(aiController);
		moves = new ArrayList<Move>();
		this.setMoveController(moveController);
		movabelStack = new ArrayList();

	}

	public Move getNextMove() {

		moveAces();

		if (moves.size() > EMPTY) {
			return moves.remove(0);
		} else {

			// Cards on Playingstacks or FreeCells
			if (freeCell.getCardsOnBoard() > 0) {
				if (isPlayingStackEmpty()) {
					// highest rank card to free Playingstack
					moves.add(new Move(getEmptyPlayingStack(), greatestRankInGame(), 1));

				}

			}

		}
		return null;

	}

	/**
	 * @param numberOfCards
	 *            number of cards wich can be moved
	 * @return List with all cardstacks
	 */
	public ArrayList<Cardstack> cardsMoveable(int numberOfCards) {
		int count = 0;

		ArrayList<Cardstack> tmp = new ArrayList<>();
		while (count != numberOfCards) {
			for (Cardstack cardstack : movabelStack) {
				if (cardstack.size() > numberOfCards) {
					if (freeCellController.isDraggable(cardstack, cardstack.get(cardstack.size() - numberOfCards))) {
						tmp.add(cardstack);
					}
				}
			}

			count++;
			numberOfCards = numberOfCards - count;
		}

		return tmp;

	}

	/**
	 * @return number of movable cards
	 */
	public int movabeleCardsAmount() {
		int count = 1;
		for (Cardstack cardstack : freeCell.getFreeCells()) {
			if (cardstack.isEmpty()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * @param game
	 *            current game. Has to be called, when ever a move is done, so
	 *            the movableStack is updated.
	 */
	public void setGame(FreeCell game) {
		this.freeCell = game;
		movabelStack.clear();

		for (Cardstack cardstack1 : freeCell.getFreeCells()) {
			if (!cardstack1.isEmpty()) {
				movabelStack.add(cardstack1);
			}
		}

		for (Cardstack cardstack : freeCell.getPlayingStacks()) {
			if (!cardstack.isEmpty()) {
				movabelStack.add(cardstack);
			}
		}

	}

	public FreeCell getGame() {
		return this.freeCell;
	}

	/**
	 * adds one Move Object to moves Arraylist, wich moves Acces to free
	 * Homecells
	 * 
	 */
	public void moveAces() {

		for (Cardstack cardstack : freeCell.getPlayingStacks()) {

			if (!cardstack.isEmpty()) {

				if (cardstack.firstElement().getRank().equals(Rank.ACE)) {

					for (Cardstack homecells : freeCell.getHomeCells()) {

						if (homecells.size() == 0) {
							moves.add(new Move(cardstack, homecells, 1));
						}
						break;
					}

				}

			}
		}
	}

	/**
	 * @return true if at least one move possible
	 * 
	 *         A move to HomeCell is not included, because these moves are done
	 *         by AutomoveController
	 */

	public boolean isMovePossible() {
		if (freeCell.getCardsOnBoard() > 0) {
			if (differentColorAndRank() || isFreeCellEmpty()) {
				return true;
			}
		}
		return false;

	}

	/**
	 * @return true if at least one Playingstack is empty
	 */
	public boolean isPlayingStackEmpty() {
		for (Cardstack stack : freeCell.getPlayingStacks()) {
			if (stack.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds the next possible Cards on HomeCells to ArrayList posibleOnHomeCell
	 * 
	 */
	public void calculatePossibleHomeCellCards() {
		for (Cardstack cardstack : freeCell.getHomeCells()) {
			if (!cardstack.isEmpty()) {
				if (cardstack.lastElement().getRank() != Rank.KING) {

					possibleOnHomeCell.add(new Card(Rank.values()[cardstack.lastElement().getRank().value],
							cardstack.lastElement().getSuit()));

				}
			}
		}
	}

	/**
	 * @return Empty Playingstack, starting from left
	 */
	public Cardstack getEmptyPlayingStack() {
		for (Cardstack stack : freeCell.getPlayingStacks()) {
			if (stack.isEmpty()) {
				return stack;
			}
		}
		return null;
	}

	/**
	 * @return stack in wich the greatest card in rank on Playingstacks or
	 *         FreeCells. If there are 2 greatest cards, the one in FreeCell is
	 *         prioritized or the most left one on Playingstacks
	 */
	public Cardstack greatestRankInGame() {
		Card tmp = null;
		Cardstack result = null;

		for (Cardstack stack : movabelStack) {
			if (!stack.isEmpty()) {
				if (tmp != null) {
					if (tmp.getRank().ordinal() < stack.lastElement().getRank().ordinal()) {
						tmp = stack.lastElement();
						result = stack;
					}

				} else {
					tmp = stack.lastElement();
					result = stack;
				}
			}
		}

		return result;
	}

	/**
	 * @return true if there are cards with different color and rank difference
	 *         of one on top of each playingstack
	 */
	public boolean differentColorAndRank() {
		for (Cardstack stackMain : this.movabelStack) {
			for (Cardstack stackCompare : this.movabelStack) {
				if (!stackMain.equals(stackCompare)) {
					if (!stackCompare.isEmpty() && !stackMain.isEmpty()) {
						if (differentColor(stackMain, stackCompare)) {
							return rankOneOverOrUnder(stackMain, stackCompare);
						}
					}
				}

			}

		}

		return false;
	}

	/**
	 * @return true if at least one FreeCell is Empty
	 */
	private boolean isFreeCellEmpty() {
		for (Cardstack freecell : freeCell.getFreeCells()) {
			if (freecell.size() == 0) {
				return true;
			}
		}

		return false;
	}

	public Cardstack getEmptyFreeCell() {
		for (Cardstack freecell : freeCell.getFreeCells()) {
			if (freecell.size() == 0) {
				return freecell;
			}
		}

		return null;
	}

	/**
	 * @param stack
	 *            first stack to compare
	 * @param compare
	 *            second stack to compare
	 * @return true if first cards colors are different
	 */
	private boolean differentColor(Cardstack stack, Cardstack compare) {
		if (stack.firstElement().getColor() != compare.firstElement().getColor()) {
			return true;
		}
		return false;
	}

	/**
	 * @param stack
	 *            first stack to compare
	 * @param compare
	 *            second stack to compare
	 * @return true if first cards rank has a difference of one
	 */

	private boolean rankOneOverOrUnder(Cardstack stack, Cardstack compare) {
		if (stack.firstElement().getRank().ordinal() == compare.firstElement().getRank().ordinal() + 1
				|| stack.firstElement().getRank().ordinal() + 1 == compare.firstElement().getRank().ordinal()) {
			return true;
		}
		return false;

	}

	public AutoMoveController getMoveController() {
		return moveController;
	}

	public void setMoveController(AutoMoveController moveController) {
		this.moveController = moveController;
	}

	public FreeCellAIController getFreeCellAIController() {
		return freeCellAIController;
	}

	public void setFreeCellAIController(FreeCellAIController freeCellAIController) {
		this.freeCellAIController = freeCellAIController;
	}

}
