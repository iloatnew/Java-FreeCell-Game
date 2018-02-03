package model;

import java.io.Serializable;
import java.util.Stack;

public class Cardstack extends Stack<Card> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 607487864789856978L;
	private Type type;
	private int location;
	private int cardstackID;
	public Cardstack(Type type) {
		super();
		this.setType(type);
	}

	public void setCardstackID(int cardstackID){
		this.cardstackID = cardstackID;
	}
	public int getCardstackID(){
		return cardstackID;
	}
	
	/**
	 * Pops first n Elements and returns them
	 * 
	 * @param n
	 * @return
	 */
	public Cardstack pop(int number) {
		Cardstack returnStack = new Cardstack(this.type);

		for (int i = 0; i < number; i++) {
			returnStack.push(this.pop());
		}

		returnStack.flipStack();

		return returnStack;
	}

	/**
	 * flips stack order
	 * 
	 * @return fliped stack
	 */
	public void flipStack() {
		Cardstack tempStack = new Cardstack(this.type);

		int number = this.size();
		
		for (int i = 0; i < number; i++) {
			tempStack.push(this.pop());
		}

		this.addAll(tempStack);
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Beispiel: Stapel: PIK 8 - HERZ 7 - PIK 6 - END Staßelänge: 3 - 2 - 1
	 * Stapel: PIK 10 - HERZ 9 - PIK 6 - HERZ 5 - END Staßelänge: 0 - 0 - 2 - 1
	 * 
	 * @param karte
	 * @return
	 */
	public int countCardsInRow(Card karte) {
		if (!this.contains(karte)) {
			return -1;
		} else {
			int cnt = 0;
			// if karte onTop, hat die Länge 1
			if (this.onTop(karte)) {
				return 1;
			}
			// mindestens eine karte on top vorhanden
			while (karte.isAltDesc(this.nextCard(karte))) {
				cnt++;
				karte = this.nextCard(karte);
				if (this.onTop(karte)) {
					return ++cnt;
				}
			}
			// wenn nicht bis Top, ist cnt 0.
			return 0;
		}

	}

	/**
	 * ist die Karte in Stapel aber nicht onTop?
	 * 
	 * @param karte
	 * @return
	 */
	public boolean onTop(Card karte) {
		if (!this.contains(karte) || this.indexOf(karte) != this.size() - 1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * gibt nächste Karte auf dem stack zurück
	 * 
	 * @param karte
	 * @return
	 */
	public Card nextCard(Card karte) {
		if (!this.onTop(karte)) {
			return this.get(this.indexOf(karte) + 1);
		} else {
			return null;
		}
	}

	/**
	 * löscht alle Karten, die hinter der Karte liegen
	 * 
	 * @param karte
	 */

	public void removeAllBelow(Card karte) {

		while (!this.onTop((karte))) {
			this.remove(this.nextCard(karte));
		}
		if (this.contains(karte)) {
			this.remove(karte);
		}
	}

	/**
	 * erzeugt ein Klone für alle Karten, die hinter der Karte liegen
	 * 
	 * @param karte
	 * @return
	 */
	public Cardstack cloneAllBelow(Card karte) {
		Cardstack stapel = new Cardstack(this.getType());
		if(karte == null){ return new Cardstack(this.getType());}
		while (!this.onTop(karte)) {
			stapel.add(karte);
			karte = this.nextCard(karte);
		}
		if (this.contains(karte)) {
			stapel.add(karte);
		}
		return stapel;
	}

	/**
	 * wenn Argument Integer ist, werden die i Karten am Oben kopiert
	 * 
	 * @param i
	 *            wieviele Karte werden kopiert
	 * @return
	 */
	public Cardstack cloneAllBelow(int number) {
		int index = this.size() - number;
		if(this.isEmpty()){ return cloneAllBelow(null);}
		Card karte = this.get(index);
		return cloneAllBelow(karte);
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
}
