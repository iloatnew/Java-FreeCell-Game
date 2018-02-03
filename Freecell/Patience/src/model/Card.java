package model;

import java.io.Serializable;


import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Card implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2478803182497583520L;
	private String backImage;
	private String faceImage;
	private Rank rank;
	private Suit suit;

	public Card(Rank rank, Suit suit) {
		this.setRank(rank);
		this.setSuit(suit);
		this.backImage = "cover.gif";
		//setBackImage(new Image("cover.gif"));
		this.faceImage = suit.name() + Integer.valueOf(rank.value).toString() + ".gif";
		//setFaceImage(new Image(suit.name() + Integer.valueOf(rank.value).toString() + ".gif"));
	}
 
	/**
	 * alternierend color und absteigende wert
	 * 
	 * @param karte
	 * @return
	 */
	public boolean isAltDesc(Card topCard) {
		if (this.getColor() != topCard.getColor()) {
			if (this.getRank().ordinal() == topCard.getRank().ordinal() + 1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * gleiche Suit und aufsteigende NachbarnRank
	 * 
	 * @return
	 */
	public boolean sameColorSorted(Card karte) {
		if (this.getSuit().equals(karte.getSuit())) {
			if (this.getRank().ordinal() == karte.getRank().ordinal() - 1) {
				return true;
			}
		}
		return false;
	}

	public Image getFaceImage() {
		return new Image(faceImage);
	}

	public void setFaceImage(String faceImage) {
		this.faceImage = faceImage;
	}

	public Image getBackImage() {
		return new Image(backImage);
	}

	public void setBackImage(String backImage) {
		this.backImage = backImage;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public Color getColor() {
		return suit.color();
	}


	/**
	 * @param card
	 * @return True, if the given card has the same suit as this card object
	 * False, otherwise
	 */
	public boolean isSameSuit(Card card) {
		return this.suit.equals(card.getSuit());
	}

	/**
	 * @param card
	 * @return True, if this card object has a lower Rank as the given card
	 * False, otherwise
	 */
	public boolean hasLowerRankAs(Card card) {
		return this.rank.ordinal()<card.getRank().ordinal() || card.getRank().ordinal()==0;
	}
	
	@Override
	public String toString() {
		return suit+"-"+ rank;
	};
	
	
	
	public boolean equals(Card card)
	{
		
		if(this.suit.equals(card.getSuit()))
		{
			if(this.rank.equals(card.getRank()))
			{
				return true;
			}
		}
		
		return false;
	}

}
