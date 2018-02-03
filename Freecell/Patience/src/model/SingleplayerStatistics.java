package model;

import java.io.Serializable;

/**
 * @author sopr041
 *
 */
public class SingleplayerStatistics implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int numberMoves;

	private int numberEliminatedCards;

	private int neededTime;
	
	/**
	 * @param numberMoves
	 * @param numberEliminatedCards
	 * @param neededTime
	 */
	public SingleplayerStatistics(int numberMoves, int numberEliminatedCards, int neededTime) {
		this.numberMoves = numberMoves;
		this.numberEliminatedCards = numberEliminatedCards;
		this.neededTime = neededTime;
	}
	
	
	/**
	 * Erhöht die Anzahl der Moves
	 */
	public void incrementNumberMoves(){
		this.numberMoves++;
		System.out.println(numberMoves);
	}
	
	/**
	 * Dekrementiert die Anzahl der Moves
	 */
	public void decrementNumberMoves(){
		this.numberMoves--;
	}
	
	/**
	 * Inkrementiert die Anzal der eliminierten Karten
	 */
	public void incrementNumberEliminatedCards(){
		this.numberEliminatedCards++;
	}
	
	/**
	 * Dekrementiert die Anzahl der eliminierten Karten
	 */
	public void decrementNumberEliminatedCards(){
		this.numberEliminatedCards--;
	}

	/**
	 * @return the numberMoves
	 */
	public int getNumberMoves() {
		return numberMoves;
	}

	/**
	 * @param numberMoves the numberMoves to set
	 */
	public void setNumberMoves(int numberMoves) {
		this.numberMoves = numberMoves;
	}

	/**
	 * @return the numberEliminatedCards
	 */
	public int getNumberEliminatedCards() {
		return numberEliminatedCards;
	}

	/**
	 * @param numberEliminatedCards the numberEliminatedCards to set
	 */
	public void setNumberEliminatedCards(int numberEliminatedCards) {
		this.numberEliminatedCards = numberEliminatedCards;
	}

	/**
	 * @return the neededTime
	 */
	public int getNeededTime() {
		return neededTime;
	}

	/**
	 * @param neededTime the neededTime to set
	 */
	public void setNeededTime(int neededTime) {
		this.neededTime = neededTime;
	}
}
