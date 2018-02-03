package model;

import java.util.Stack;

import controller.CloneController;

public class Zank extends Game {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4035717689666047600L;

	// Ersatzstapel
	private Cardstack[] reserves;
	// Nachziehstapel
	private Cardstack[] hands;
	// Ablagestapel
	private Cardstack[] wastePiles;
	// Anlagestapel
	private Cardstack[] playingStacks;
	// mittlereStapel
	private Cardstack[] eliminationStacks;

	private MultiplayerStatistics multiplayerStatistics;

	private Cardstack startStackPlayer2;

	private Player player1;

	private Player player2;

	private boolean startPlayer1;

	public Zank() {
		history = new Stack<>();
		this.fraction = (long) 0;
		this.time = (long) 0;
		this.isNewGame = true;
		this.multiplayerStatistics = new MultiplayerStatistics(52, 52, null, null, 0);
		reserves = new Cardstack[2];
		for (int i = 0; i < reserves.length; i++) {
			reserves[i] = new Cardstack(Type.RESERVE);
		}

		hands = new Cardstack[2];
		for (int i = 0; i < hands.length; i++) {
			hands[i] = new Cardstack(Type.HAND);
		}

		wastePiles = new Cardstack[2];
		for (int i = 0; i < wastePiles.length; i++) {
			wastePiles[i] = new Cardstack(Type.WASTEPILE);
		}

		playingStacks = new Cardstack[8];
		for (int i = 0; i < playingStacks.length; i++) {
			playingStacks[i] = new Cardstack(Type.PLAYINGSTACK);
		}

		eliminationStacks = new Cardstack[8];
		for (int i = 0; i < 2; i++) {
			eliminationStacks[i] = new Cardstack(Type.ELIMINATIONSTACKDIAMOND);
		}
		for (int i = 0; i < 2; i++) {
			eliminationStacks[2 + i] = new Cardstack(Type.ELIMINATIONSTACKSPADE);
		}
		for (int i = 0; i < 2; i++) {
			eliminationStacks[4 + i] = new Cardstack(Type.ELIMINATIONSTACKCLUB);
		}
		for (int i = 0; i < 2; i++) {
			eliminationStacks[6 + i] = new Cardstack(Type.ELIMINATIONSTACKHEART);
		}
	}

	public Zank(Cardstack stackplayer1, Cardstack stackplayer2, Player player1, Player player2, boolean startPlayer1) {
		this();
		this.setStartStack(stackplayer1);
		this.setStartStackPlayer2(stackplayer2);
		this.player1 = player1;
		this.player2 = player2;
		this.startPlayer1 = startPlayer1;
		this.multiplayerStatistics.setPlayer1(player1);
		this.multiplayerStatistics.setPlayer2(player2);
	}

	public Cardstack[] getReserves() {
		return reserves;
	}

	public void setReserves(Cardstack[] reserves) {
		this.reserves = reserves;
	}

	public Cardstack[] getHands() {
		return hands;
	}

	public void setHands(Cardstack[] hands) {
		this.hands = hands;
	}

	public Cardstack[] getWastePiles() {
		return wastePiles;
	}

	public void setWastePiles(Cardstack[] wastePiles) {
		this.wastePiles = wastePiles;
	}

	public Cardstack[] getPlayingStacks() {
		return playingStacks;
	}

	public void setPlayingStacks(Cardstack[] playingStacks) {
		this.playingStacks = playingStacks;
	}

	public Cardstack[] getEliminationStacks() {
		return eliminationStacks;
	}

	public void setEliminationStacks(Cardstack[] eliminationStacks) {
		this.eliminationStacks = eliminationStacks;
	}

	public MultiplayerStatistics getMultiplayerStatistics() {
		return multiplayerStatistics;
	}

	public void setMultiplayerStatistics(MultiplayerStatistics multiplayerStatistics) {
		this.multiplayerStatistics = multiplayerStatistics;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int getCardsOnBoard() {
		// TODO
		return 0;
	}

	public Cardstack getStartStackPlayer2() {
		return startStackPlayer2;
	}

	public void setStartStackPlayer2(Cardstack startStackPlayer2) {
		this.startStackPlayer2 = startStackPlayer2;
	}

	/**
	 * @return number of cards of player1
	 */
	public int cardsOfPlayer1() {
		return hands[0].size() + reserves[0].size() + wastePiles[0].size();
	}

	/**
	 * @return number of cards of player2
	 */
	public int cardsOfPlayer2() {
		return hands[1].size() + reserves[1].size() + wastePiles[1].size();
	}

	@Override
	public Game clone() {
		Zank cloneZank = new Zank();
		cloneZank.setEliminationStacks(CloneController.cloneCardstacks(eliminationStacks));
		cloneZank.setHands(CloneController.cloneCardstacks(hands));
		cloneZank.setHistory((Stack<Move>) history.clone());
		cloneZank.setMultiplayerStatistics(CloneController.cloneMultiplayerStatistics(multiplayerStatistics));
		cloneZank.setPlayer1(player1);
		cloneZank.setPlayer2(player2);
		cloneZank.setPlayingStacks(CloneController.cloneCardstacks(playingStacks));
		cloneZank.setReserves(CloneController.cloneCardstacks(reserves));
		cloneZank.setStartStack(CloneController.cloneCardstack(startStackPlayer2));
		cloneZank.setStartStackPlayer2(CloneController.cloneCardstack(startStackPlayer2));
		cloneZank.setWastePiles(CloneController.cloneCardstacks(wastePiles));
		return cloneZank;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Card getReservesToplevelCard(int playerID) {
		if(reserves[playerID].isEmpty()){return null;}
		return reserves[playerID].peek();
	}

	public Card getWastesToplevelCard(int playerID) {
		if(wastePiles[playerID].isEmpty()){return null;}
		return wastePiles[playerID].peek();
	}

	public Card getHandsToplevelCard(int playerID) {
		if(hands[playerID].isEmpty()){return null;}
		return hands[playerID].peek();
	}

	public boolean isStartPlayer1() {
		return startPlayer1;
	}

	public void setStartPlayer1(boolean startPlayer1) {
		this.startPlayer1 = startPlayer1;
	}
}
