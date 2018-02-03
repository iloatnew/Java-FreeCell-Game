package controller;

import java.util.Collections;

import model.Card;
import model.Cardstack;
import model.Type;
import model.Rank;
import model.Suit;

public class DeckController {

	private PatienceController patienceController;

	/**
	 * creates random Deck, with StapelType Default
	 * 
	 * @return shuffeld Cardstack with 52 Cards
	 */
	public Cardstack randomDeck() {

		Cardstack rdmDeck = new Cardstack(Type.DEFAULT);

		for (Suit s : Suit.values()) {
			for (Rank r : Rank.values()) {
				rdmDeck.add(new Card(r, s));
			}
		}
		Collections.shuffle(rdmDeck);

		return rdmDeck;
	}

	/**
	 * creates new Deck from String input, StapelType is DEFAULT
	 * 
	 * @param string
	 * @return cardstack
	 */
	public Cardstack deckFromString(String string) {
		String[] inhalt = string.split(" ");
		Cardstack deck = new Cardstack(Type.DEFAULT);
		for (int i = inhalt.length - 1; i >= 0; i--) {
			deck.add(convertToCard(inhalt[i]));
		}
		return deck;
	}

	/**
	 * converts String into Card Object
	 * 
	 * @param string
	 * @return card
	 */
	// siehe IOController
	@Deprecated
	public Card convertToCard(String string) {
		String[] inhalt = string.split("-");
		Suit suit = null;
		Rank rank = null;
		switch (inhalt[0]) {
		case "Pik":
			suit = Suit.SPADE;
			break;
		case "Kreuz":
			suit = Suit.CLUB;
			break;
		case "Herz":
			suit = Suit.HEART;
			break;
		case "Karo":
			suit = Suit.DIAMOND;
			break;
		}
		switch (inhalt[1]) {
		case "Ass":
			rank = Rank.ACE;
			break;
		case "Koenig":
			rank = Rank.KING;
			break;
		case "Dame":
			rank = Rank.QUEEN;
			break;
		case "Bube":
			rank = Rank.JACK;
			break;
		case "10":
			rank = Rank.TEN;
			break;
		case "9":
			rank = Rank.NINE;
			break;
		case "8":
			rank = Rank.EIGHT;
			break;
		case "7":
			rank = Rank.SEVEN;
			break;
		case "6":
			rank = Rank.SIX;
			break;
		case "5":
			rank = Rank.FIVE;
			break;
		case "4":
			rank = Rank.FOUR;
			break;
		case "3":
			rank = Rank.THREE;
			break;
		case "2":
			rank = Rank.TWO;
			break;
		}
		return new Card(rank, suit);
	}

	/**
	 * creates string from stack input
	 * 
	 * @param stack
	 * @return string
	 */
	public String deckToString(Cardstack stack) {
		String inhalt = "";
		for (Card card : stack) {
			inhalt = inhalt + " " + convertToString(card);
		}
		return inhalt;
	}

	/**
	 * converts card into String Object
	 * 
	 * @param card
	 * @return String
	 */
	public String convertToString(Card card) {
		String inhalt = "";
		switch (card.getSuit()) {
		case SPADE:
			inhalt = "Pik";
			break;
		case CLUB:
			inhalt = "Kreuz";
			break;
		case HEART:
			inhalt = "Herz";
			break;
		case DIAMOND:
			inhalt = "Karo";
			break;
		}
		inhalt = inhalt + "-";
		switch (card.getRank()) {
		case ACE:
			inhalt = inhalt + "Ass";
			break;
		case EIGHT:
			inhalt = inhalt + "8";
			break;
		case FIVE:
			inhalt = inhalt + "5";
			break;
		case FOUR:
			inhalt = inhalt + "4";
			break;
		case JACK:
			inhalt = inhalt + "Bube";
			break;
		case KING:
			inhalt = inhalt + "Koenig";
			break;
		case NINE:
			inhalt = inhalt + "9";
			break;
		case QUEEN:
			inhalt = inhalt + "Dame";
			break;
		case SEVEN:
			inhalt = inhalt + "7";
			break;
		case SIX:
			inhalt = inhalt + "6";
			break;
		case TEN:
			inhalt = inhalt + "10";
			break;
		case THREE:
			inhalt = inhalt + "3";
			break;
		case TWO:
			inhalt = inhalt + "2";
			break;
		}
		return inhalt;
	}

	/**
	 * getter
	 * 
	 * @return
	 */
	public PatienceController getPatienceController() {
		return patienceController;
	}

	/**
	 * setter
	 * 
	 * @param patienceController
	 */
	public void setPatienceController(PatienceController patienceController) {
		this.patienceController = patienceController;
	}

}
