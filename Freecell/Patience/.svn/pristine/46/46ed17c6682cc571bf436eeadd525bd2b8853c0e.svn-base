package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Statistics implements Serializable {

	private ArrayList<SingleplayerStatistics> acesUpStatistics;
	
	private ArrayList<SingleplayerStatistics> freecellStatistics;

	private ArrayList<MultiplayerStatistics> zankStatistics;
	
	public Statistics(){
		this.acesUpStatistics = new ArrayList<SingleplayerStatistics>();
		this.freecellStatistics = new ArrayList<SingleplayerStatistics>();
		this.zankStatistics = new ArrayList<MultiplayerStatistics>();
	}

	public ArrayList<SingleplayerStatistics> getAcesUpStatistics() {
		return acesUpStatistics;
	}

	public void setAcesUpStatistics(ArrayList<SingleplayerStatistics> acesUpStatistics) {
		this.acesUpStatistics = acesUpStatistics;
	}

	public ArrayList<SingleplayerStatistics> getFreecellStatistics() {
		return freecellStatistics;
	}

	public void setFreecellStatistics(ArrayList<SingleplayerStatistics> freecellStatistics) {
		this.freecellStatistics = freecellStatistics;
	}

	public ArrayList<MultiplayerStatistics> getZankStatistics() {
		return zankStatistics;
	}

	public void setZankStatistics(ArrayList<MultiplayerStatistics> zankStatistics) {
		this.zankStatistics = zankStatistics;
	}

	public void addAcesUpStatistic(SingleplayerStatistics statistic){
		this.acesUpStatistics.add(statistic);
	}

	public void addFreeCellStatistic(SingleplayerStatistics statistic){
		this.freecellStatistics.add(statistic);
	}
	
	public void addZankStatistic(MultiplayerStatistics statistic){
		this.zankStatistics.add(statistic);
	}
}
