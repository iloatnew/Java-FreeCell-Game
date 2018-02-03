package model;

public enum Rank {

	ACE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,JACK,QUEEN,KING;

	/**
	 * 1 for Ace up to 13 for King.
	 */
	public final int value = ordinal() + 1;
	
}
