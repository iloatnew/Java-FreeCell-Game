package model;

import java.io.Serializable;

public class MultiplayerStatistics implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3457398438693931015L;

	private int cardsPlayer1;

	private int cardsPlayer2;

	private Player player1;
	
	private Player player2;
	
	private int neededTime;
	
	public MultiplayerStatistics(int cardsPlayer1, int cardsPlayer2, Player player1, Player player2, int neededTime) {
		this.cardsPlayer1 = cardsPlayer1;
		this.cardsPlayer2 = cardsPlayer2;
		this.player1 = player1;
		this.player2 = player2;
		this.neededTime = neededTime;
	}

	public int getCardsPlayer1() {
		return cardsPlayer1;
	}

	public void setCardsPlayer1(int cardsPlayer1) {
		this.cardsPlayer1 = cardsPlayer1;
	}

	public int getCardsPlayer2() {
		return cardsPlayer2;
	}

	public void setCardsPlayer2(int cardsPlayer2) {
		this.cardsPlayer2 = cardsPlayer2;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player) {
		this.player1 = player;
	}
	
	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player) {
		this.player2 = player;
	}
	
	public int getNeededTime(){
		return this.neededTime;
	}
	
	public void decrementNumberofCardsPlayer1(){
		if(this.cardsPlayer1>0){
			this.cardsPlayer1--;
		}
	}
	
	public void decrementNumberofCardsPlayer2(){
		if(this.cardsPlayer2>0){
			this.cardsPlayer2--;
		}
	}
	
	public void incrementNumberofCardsPlayer1(){
		if(this.cardsPlayer1<104){
			this.cardsPlayer1++;
		}
	}
	
	public void incrementNumberofCardsPlayer2(){
		if(this.cardsPlayer2<104){
			this.cardsPlayer2++;
		}
	}	
	
}
