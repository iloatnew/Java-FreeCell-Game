package model;

import java.io.Serializable;

public class Move implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2416726912231749481L;
	private Cardstack from;
	private Cardstack target;
	private int numberOfCards;

	/**
	 * 
	 * @param prevFro, the Type and index of the prevFro,/Herkunft
	 * @param prevTo the Type and index of the prevTo
	 * @param numberWithCards wieviele Karten wurden bewegt
	 */
	public Move(Cardstack from, Cardstack target, int numberOfCards){
		this.setFrom(from);
		this.setTo(target);
		this.setNumberOfCards(numberOfCards);
	}
	
	public Cardstack getFrom() {
		return from;
	}

	public void setFrom(Cardstack from) {
		this.from = from;
	}

	public Cardstack getTo() {
		return target;
	}

	public void setTo(Cardstack target) {
		this.target = target;
	}

	public int getNumberOfCards() {
		return numberOfCards;
	}

	public void setNumberOfCards(int numberOfCards) {
		this.numberOfCards = numberOfCards;
	}
	
	public Move revertMove(){
		return new Move(this.target,this.from,this.numberOfCards);
	}
}
