package controller;

import java.util.ArrayList;
import java.util.Date;

import aui.ZankAUI;
import exceptions.IllegalGameArgumentException;
import exceptions.NoMovesPossibleException;
import exceptions.NotEnoughCardsOnPlaystacksException;
import model.Card;
import model.Cardstack;
import model.Move;
import model.MultiplayerStatistics;
import model.Rank;
import model.Suit;
import model.Type;
import model.Zank;
import aicontroller.ZankAIController;

public class ZankController extends GameController {

	private int activePlayer;

	private Zank game;

	private ZankAUI zankAUI;
	
	private ArrayList<Cardstack> stackIDList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#initGame()
	 */
	@Override
	public void initGame() {
		game = (Zank) patienceController.getPatience().getGame();

		if (game.isNewGame()) {
			if (game.isStartPlayer1()) {
				activePlayer = 0;
			} else {
				activePlayer = 1;
			}

			Cardstack startStackPlayer1 = game.getStartStack();
			Cardstack startStackPlayer2 = game.getStartStackPlayer2();

			game.getHands()[0].clear();
			game.getHands()[1].clear();
			game.getHands()[0].addAll(startStackPlayer1);
			game.getHands()[1].addAll(startStackPlayer2);

			game.getReserves()[0].clear();
			game.getReserves()[0].addAll(game.getHands()[1].pop(13));
			game.getReserves()[0].flipStack();
			game.getReserves()[1].clear();
			game.getReserves()[1].addAll(game.getHands()[0].pop(13));
			game.getReserves()[1].flipStack();

			for (int i = 0; i < 4; i++) {
				game.getPlayingStacks()[i].clear();
				game.getPlayingStacks()[i].add(game.getHands()[0].pop());
				game.getPlayingStacks()[i + 4].clear();
				game.getPlayingStacks()[i + 4].add(game.getHands()[1].pop());
			}
			try {
				this.aIController.setGame(game);
			} catch (IllegalGameArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setStartTime((new Date()).getTime());
			game.setNewGame(false);
		}
		this.zankAUI.refreshAll();
		
		System.out.println(autoMoveController.autoMoveSuccess());
	}

	public void setZankAUI(ZankAUI zankAUI) {
		this.zankAUI = zankAUI;
	}

	public ZankAUI getZankAUI() {
		return this.zankAUI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#isDraggable(model.Cardstack, model.Card)
	 */
	@Override
	public boolean isDraggable(Cardstack stack, Card card) {
		if (!isOnTop(stack, card)) {
			return false;
		} else if (!isValidStack(stack)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * is the stack a valid stack for the plkayer to use
	 * 
	 * @param stack
	 *            to check
	 * @return true if is valid
	 */
	private boolean isValidStack(Cardstack stack) {
		return !isOtherPlayerStack(stack) && !isEliminationStack(stack);
	}

	/**
	 * is card on top of the stack?
	 * 
	 * @param stack
	 *            to check
	 * @param card
	 *            to check
	 * @return truen is card is on top
	 */
	private boolean isOnTop(Cardstack stack, Card card) {
		return !stack.isEmpty() && card == stack.peek();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#isDroppable(model.Cardstack)
	 */
	@Override
	public boolean isDroppable(Cardstack stack) {
		Card cardToDrop = getStackToDrag().firstElement();
		if (isEliminationStack(stack)) {
			return isDroppableOnElimination(stack, cardToDrop);
		} else if (isOtherPlayerStack(stack)) {
			return isDroppableOnPlayerStack(stack, cardToDrop);
		} else if (stack.getType() == Type.PLAYINGSTACK) {
			return isDroppableOnPlayingstack(stack, cardToDrop);
		} else if (stack.getType() == Type.WASTEPILE) {
			return sourceStack == game.getHands()[getActivePlayer()];
		} else {
			return false;
		}
	}

	private boolean isDroppableOnPlayingstack(Cardstack stack, Card cardToDrop) {
		return stack.isEmpty() || (cardToDrop.getColor() != stack.peek().getColor()
				&& cardToDrop.getRank().value == stack.peek().getRank().value - 1);
	}

	private boolean isDroppableOnPlayerStack(Cardstack stack, Card cardToDrop) {
		return stack.getType() != Type.HAND && cardToDrop.getSuit() == stack.peek().getSuit()
				&& Math.abs(cardToDrop.getRank().value - stack.peek().getRank().value) == 1;
	}

	/**
	 * isplayerStack
	 * 
	 * @param stack
	 *            isplayerStack
	 * @return isplayerStack
	 */
	private boolean isOtherPlayerStack(Cardstack stack) {
		if (stack == game.getHands()[(getActivePlayer() + 1) % 2]) {
			return true;
		} else if (stack == game.getReserves()[(getActivePlayer() + 1) % 2]) {
			return true;
		} else if (stack == game.getWastePiles()[(getActivePlayer() + 1) % 2]) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * isDroppableOnElimination
	 * 
	 * @param stack
	 *            to drop
	 * @param cardToDrop
	 *            to drop
	 * @return isDroppableOnElimination
	 */
	private boolean isDroppableOnElimination(Cardstack stack, Card cardToDrop) {
		if (stack.isEmpty()) {
			return cardToDrop.getRank() == Rank.ACE && equalsSuit(cardToDrop, stack);
		} else {
			return cardToDrop.getRank().value == stack.peek().getRank().value + 1 && equalsSuit(cardToDrop, stack);
		}
	}

	/**
	 * isEliminationStack
	 * 
	 * @param stack
	 *            isEliminationStack
	 * @return isEliminationStack
	 */
	private boolean isEliminationStack(Cardstack stack) {
		for (Cardstack eliminationStack : game.getEliminationStacks()) {
			if (stack == eliminationStack) {
				return true;
			}
		}
		return false;
	}

	/**
	 * checks if card fits on stack
	 * 
	 * @param card
	 *            card to fit
	 * @param stack
	 *            stack to check
	 * @return true if it fits else false
	 */
	private boolean equalsSuit(Card card, Cardstack stack) {
		return (card.getSuit() == Suit.CLUB && stack.getType() == Type.ELIMINATIONSTACKCLUB)
				|| (card.getSuit() == Suit.DIAMOND && stack.getType() == Type.ELIMINATIONSTACKDIAMOND)
				|| (card.getSuit() == Suit.HEART && stack.getType() == Type.ELIMINATIONSTACKHEART)
				|| (card.getSuit() == Suit.SPADE && stack.getType() == Type.ELIMINATIONSTACKSPADE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#isFinished()
	 */
	@Override
	public boolean isFinished() {
		return game.cardsOfPlayer1() == 0 || game.cardsOfPlayer2() == 0;
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
		this.movePossible();
	}

	private boolean movePossible() {
		try {
			Move nextMove;
			// System.out.println("AI: "+aIController);
			nextMove = aIController.getNextMove();
			// System.out.println("NextMove: "+nextMove);
			// System.out.println("NextMove.to.getType():
			// "+nextMove.getTo().getType());
			// System.out.println("NextMove.from.getType():
			// "+nextMove.getFrom().getType());
			// System.out.println();
			((ZankAIController) aIController).setLastMove(nextMove);
			executeMove(nextMove);
		} catch (NotEnoughCardsOnPlaystacksException e) {
			return false;
		} catch (NoMovesPossibleException e) {
			return false;
			// TODO finished
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#revertMove()
	 */
	@Override
	public void undoMove() {
		if (!game.getHistory().isEmpty()) {
			this.executeMove(game.getHistory().pop().revertMove());
			game.getHistory().pop();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#executeMove(model.Move)
	 */
	@Override
	public void executeMove(Move moveToExecute) {
		if ((moveToExecute.getFrom() == game.getHands()[getActivePlayer()]
				&& moveToExecute.getTo() == game.getWastePiles()[getActivePlayer()])
				|| moveToExecute.getTo() == game.getHands()[(getActivePlayer() + 1) % 2]
						&& moveToExecute.getFrom() == game.getWastePiles()[(getActivePlayer() + 1) % 2]) {
			switchPlayer();
		}
		zankAUI.animateMove( moveToExecute);
		System.out.println("Move.getTo(): "+moveToExecute.getFrom());
		System.out.println("Move.getFrom(): "+moveToExecute.getFrom());
		moveToExecute.getTo().add(moveToExecute.getFrom().pop());
		game.getHistory().push(moveToExecute);
		autoMoveController.autoMoveSuccess();
		if(isFinished()){
			int cardsaytivePlayer = this.countCards(activePlayer);
			int cardsOtherPlayer = this.countCards((activePlayer+1)%2);
			if(activePlayer == 0){
				this.game.getMultiplayerStatistics().setCardsPlayer1(cardsaytivePlayer);
				this.game.getMultiplayerStatistics().setCardsPlayer2(cardsOtherPlayer);
			}
			else{
				this.game.getMultiplayerStatistics().setCardsPlayer2(cardsaytivePlayer);
				this.game.getMultiplayerStatistics().setCardsPlayer1(cardsOtherPlayer);				
			}
			zankAUI.openFinishedWindow("Zank", getActivePlayer());
		}
//		zankAUI.refreshAll();
	}

	/**
	 * switches activePlayer
	 */
	public void switchPlayer() {
		if (getActivePlayer() == 0) {
			// activePlayer = game.getPlayer2();
			activePlayer = 1;
//			switch(game.getPlayer2()){
//			case 
//			}
		} else {
			// activePlayer = game.getPlayer1();
			activePlayer = 0;
		}
		((ZankAIController) aIController).setActivePlayer(activePlayer);
		zankAUI.refreshAll();
		autoMoveController.autoMoveSuccess();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#addStatistics()
	 */
	@Override
	public void addStatistics() {
		MultiplayerStatistics statistics = this.game.getMultiplayerStatistics();
		patienceController.getPatience().getStatistics().addZankStatistic(statistics);
		patienceController.getiOController().saveStatistics();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#restartGame()
	 */
	@Override
	public void restartGame() {
		this.patienceController.getPatience().setGame(new Zank(this.game.getStartStack(), this.game.getStartStackPlayer2(), this.game.getPlayer1(), this.game.getPlayer2(), this.game.isStartPlayer1()));
		this.game = (Zank) this.patienceController.getPatience().getGame();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#cancelGame()
	 */
	@Override
	public void cancelGame() {
		this.addStatistics();
		this.game = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controller.GameController#refreshKarte()
	 */
	@Override
	public void refreshKarte() {
		zankAUI.refreshAll();
	}

	/**
	 * @return 0 for player1 active 1 for player2 active
	 */
	public int getActivePlayer() {
		return activePlayer;
	}

	/**
	 * puts cards from wastepile to hand and flips them
	 */
	public void wastePileToHand() {
		if (game.getHands()[getActivePlayer()].isEmpty()) {
			game.getHands()[getActivePlayer()].addAll(game.getWastePiles()[getActivePlayer()]);
			game.getHands()[getActivePlayer()].flipStack();
			game.getWastePiles()[getActivePlayer()].removeAllElements();
			zankAUI.refreshAll();
		}
	}
	
	/**
	 * @param player
	 * counts the remaining cards on the stacks of the given player
	 */
	public int countCards(int player){
		int result = 0;
		result += this.game.getHands()[player].size();
		result += this.game.getReserves()[player].size();
		result += this.game.getWastePiles()[player].size();
		return result;
	}
}
