package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Stack;

public abstract class Game implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4104055189219076131L;
	
	protected Long fraction;
	protected Long time;
	protected boolean isNewGame;

	protected Cardstack startStack;

	protected Stack<Move> history;
	
	


	public Cardstack getStartStack() {
		return startStack;
	}

	public void setStartStack(Cardstack startStack) {
		this.startStack = startStack;
	}

	public Stack<Move> getHistory() {
		return history;
	}

	public void setHistory(Stack<Move> history) {
		this.history = history;
	}

	public abstract int getCardsOnBoard();
	
	public abstract Game clone();

	/**
	 * @return the fraction
	 */
	public Long getFraction() {
		return fraction;
	}

	/**
	 * @param fraction the fraction to set
	 */
	public void setFraction(Long fraction) {
		this.fraction = fraction;
	}

	/**
	 * @return the time
	 */
	public Long getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Long time) {
		this.time = time;
	}
	
	/**
	 * @return the isNewGame
	 */
	public boolean isNewGame() {
		return isNewGame;
	}


	/**
	 * @param isNewGame the isNewGame to set
	 */
	public void setNewGame(boolean isNewGame) {
		this.isNewGame = isNewGame;
	}

}
