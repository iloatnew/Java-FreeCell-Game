package model;

import java.util.Stack;

public class FreeCell extends Game {

	/**
	 * 
	 */
	private static final long serialVersionUID = -869739190474563364L;

	private Cardstack[] homeCells;

	private Cardstack[] freeCells;

	private Cardstack[] playingStacks;

	private SingleplayerStatistics singleplayerStatistics;
	
	

	public FreeCell(Cardstack startStack) {	
		this.isNewGame = true;
		this.homeCells = new Cardstack[4];
		this.freeCells = new Cardstack[4];
		this.playingStacks = new Cardstack[8];
		this.history = new Stack<Move>();
		this.startStack = startStack;
		this.fraction = (long) 0;
		this.time = (long) 0;
		this.singleplayerStatistics = new SingleplayerStatistics(0, 0, 0);
		this.initStacks();
	}


	private void initStacks() {


		for (int i = 0; i < homeCells.length; i++) {
			homeCells[i] = new Cardstack(Type.HOMECELL);
		}

		for (int i = 0; i < freeCells.length; i++) {
			freeCells[i] = new Cardstack(Type.FREECELL);
		}

		for (int i = 0; i < playingStacks.length; i++) {
			playingStacks[i] = new Cardstack(Type.PLAYINGSTACK);
		}

		// for (Cardstack home : this.homeCells) {
		// home = new Cardstack(Type.HOMECELL);
		// }
		// for (Cardstack free : this.freeCells) {
		// free = new Cardstack(Type.FREECELL);
		// }
		// for (Cardstack play : this.playingStacks) {
		// play = new Cardstack(Type.PLAYINGSTACK);
		// }
	}

	/**
	 * @return the homeCell
	 */
	public Cardstack[] getHomeCells() {
		return homeCells;
	}

	/**
	 * @param homeCell
	 *            the homeCell to set
	 */
	public void setHomeCells(Cardstack[] homeCell) {
		this.homeCells = homeCell;
	}

	/**
	 * @return the freeCell
	 */

	public Cardstack[] getFreeCells() {
		return freeCells;

	}

	/**
	 * @param freeCell
	 *            the freeCell to set
	 */

	public void setFreeCells(Cardstack[] freeCell) {
		this.freeCells = freeCell;

	}

	/**
	 * @return the playingStacks
	 */
	public Cardstack[] getPlayingStacks() {
		return playingStacks;
	}

	/**
	 * @param playingStacks
	 *            the playingStacks to set
	 */
	public void setPlayingStacks(Cardstack[] playingStacks) {
		this.playingStacks = playingStacks;
	}

	/**
	 * @return the singleplayerStatistics
	 */
	public SingleplayerStatistics getSingleplayerStatistics() {
		return singleplayerStatistics;
	}

	/**
	 * @param singleplayerStatistics
	 *            the singleplayerStatistics to set
	 */
	public void setSingleplayerStatistics(SingleplayerStatistics singleplayerStatistics) {
		this.singleplayerStatistics = singleplayerStatistics;
	}

	@Override
	public int getCardsOnBoard() {
		int sum = 0;
		for (Cardstack cardstack : playingStacks) {
			sum += cardstack.size();
		}

		for (Cardstack freeCell : freeCells) {
			sum += freeCell.size();
		}
		return sum;
	}

	@Override
	public Game clone() {
		FreeCell cloneCell = new FreeCell(startStack);
		cloneCell.setFreeCells(freeCells);
		cloneCell.setHistory(getHistory());
		cloneCell.setHomeCells(homeCells);
		cloneCell.setPlayingStacks(playingStacks);
		cloneCell.setSingleplayerStatistics(singleplayerStatistics);
		cloneCell.setStartStack(startStack);
		return cloneCell;
	}


	
}
