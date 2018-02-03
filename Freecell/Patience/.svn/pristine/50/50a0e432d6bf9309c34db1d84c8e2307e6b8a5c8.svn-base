package application;
import java.util.ArrayList;

import controller.PatienceController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import model.MultiplayerStatistics;
import model.Player;
import model.SingleplayerStatistics;

public class StatistikController {
	
	private PatienceController patienceController;
	private ArrayList<SingleplayerStatistics> acesUpStatistics;
	private ArrayList<SingleplayerStatistics> freeCellStatistics;
	private ArrayList<MultiplayerStatistics> zankStatistics;

    @FXML
    private Tab acesUpTab;

    @FXML
    private Label acesUpNumberOfGamesLabel;

    @FXML
    private Label acesUpWonGamesLabel;

    @FXML
    private Label acesUpWinRateLabel;

    @FXML
    private Label acesUpSolutionRank1Label;

    @FXML
    private Label acesUpSolutionRank4Label;

    @FXML
    private Label acesUpSolutionRank3Label;

    @FXML
    private Label acesUpSolutionRank2Label;

    @FXML
    private Label acesUpLostGamesLabel;

    @FXML
    private Label acesUpAverageTimeLabel;

    @FXML
    private Tab freecellTab;

    @FXML
    private Label freecellNumberOfGamesLabel;

    @FXML
    private Label freecellWonGamesLabel;

    @FXML
    private Label freecellWinRateLabel;

    @FXML
    private Label freecellSolutionRank4Label;

    @FXML
    private Label freecellSolutionRank3Label;

    @FXML
    private Label freecellSolutionRank2Label;

    @FXML
    private Label freecellSolutionRank1Label;

    @FXML
    private Label freecellLostGamesLabel;

    @FXML
    private Label freecellAverageTimeLabel;

    @FXML
    private Tab zankPatienceTab;

    @FXML
    private Label zankNumberOfGamesLabel;

    @FXML
    private Label zankWinRateLabel;

    @FXML
    private Label zankStatisticsCom1Label;

    @FXML
    private Label zankStatisticsCom2Label;

    @FXML
    private Label zankStatisticsCom3Label;
    
    @FXML
    private Label zankAverageTimeLabel;

    @FXML
    private Label zankStatisticsPlayer2Label;

    //TODO: init Methode(), werte berechnung

    public void setPatienceController(PatienceController patienceController) {
		this.patienceController = patienceController;
		//registerAbstractUserInterfaces();
	}
    
    public void initialize(){
    	
    }
    
    public void updateStatistics(){
    	setAcesUpStat();
    	setFreeCellStat();
    	setZankStat();
    }
    
    private void setAcesUpStat(){
    	
    	int games = acesUpStatistics.size();
    	
    	acesUpNumberOfGamesLabel.setText(games+"");	
    	
    	int wonGames = calculateWonGamesSinglePlay(acesUpStatistics);

        acesUpWonGamesLabel.setText(wonGames+"");
        
        double winRate = 0.00;
        
        if(games>0){
        	winRate = (wonGames/(games+0.00)) * 100 ;
        }

        acesUpWinRateLabel.setText(winRate+ "%"); 
    	
        int[] ranks = calculateRanks(acesUpStatistics);
    	
        acesUpSolutionRank1Label.setText(ranks[0]+"");

        acesUpSolutionRank2Label.setText(ranks[1]+"");
    	
        acesUpSolutionRank3Label.setText(ranks[2]+"");

        acesUpSolutionRank4Label.setText(ranks[3]+"");

        acesUpLostGamesLabel.setText(games-wonGames+"");

    	double averageTime = calulateAverageTime(acesUpStatistics); // in minutes or seconds?? -> eventually casting to minutes
    	
        acesUpAverageTimeLabel.setText(averageTime+"");

    }
    
    private int calculateWonGamesSinglePlay( ArrayList<SingleplayerStatistics> list){
    	int result =0;
    	int maxEliminatedCards = 52;
    	for(SingleplayerStatistics gameStat:list){
    		if(gameStat.getNumberEliminatedCards() == maxEliminatedCards){
    			result++;
    		}
    	}
    	return result;
    }
    
    private int[] calculateRanks(ArrayList<SingleplayerStatistics> list){    	
    	int[] ranks = new int[4];
    	int maxRank1 = 12;
    	int maxRank2 = 25;
    	int maxRank3 = 38;
    	int maxRank4 = 51;
    	for(SingleplayerStatistics gameStat : list){
    		int eliminatedCards = gameStat.getNumberEliminatedCards();
    		if(eliminatedCards <=maxRank1){
    			ranks[0]++;
    		}
    		else if(eliminatedCards <=maxRank2){
    			ranks[1]++;
    		}
    		else if(eliminatedCards <= maxRank3){
    			ranks[2]++;
    		}
    		else if(eliminatedCards<= maxRank4){
    			ranks[3]++;
    		}
    	}
    	return ranks;
    }
    
    private double calulateAverageTime(ArrayList<SingleplayerStatistics> list){
    	double games = list.size();
    	double playTime = 0;
    	for(SingleplayerStatistics gameStat : list){
    		playTime+= gameStat.getNeededTime();
    	}
    	if(games>0){
    		return playTime/games;
    	}
    	return 0.00;
    }
    
    public void setFreeCellStat(){

	  int games = freeCellStatistics.size();
	    	
	  freecellNumberOfGamesLabel.setText(games+"");
	
	  int wonGames = calculateWonGamesSinglePlay(freeCellStatistics);
    	
	  freecellWonGamesLabel.setText(wonGames+"");
	
	  double winRate = 0.00;
	  
	  if(games>0){
		  winRate = wonGames/(games+0.00)* 100 ;
	  }
	  
	  freecellWinRateLabel.setText(winRate+"%");
	  
	  int[] ranks = calculateRanks(freeCellStatistics);
	    
	  freecellSolutionRank1Label.setText(ranks[0]+"");
	
	  freecellSolutionRank2Label.setText(ranks[1]+"");
	
	  freecellSolutionRank3Label.setText(ranks[2]+"");
	    
	  freecellSolutionRank4Label.setText(ranks[3]+"");
	
	  freecellLostGamesLabel.setText(games-wonGames+"");
	
	  double averageTime = calulateAverageTime(acesUpStatistics); // in minutes or seconds?? -> eventually casting to minutes
	    
	  freecellAverageTimeLabel.setText(averageTime+"");
    }
    
    public void setZankStat(){

    	int games = zankStatistics.size();
    	
    	zankNumberOfGamesLabel.setText(games+"");

    	int wonGames = calculateWonGamesMultiPlay(zankStatistics);
    	
    	double winRate = 0.00;
    	
    	if(games>0){
    		winRate= wonGames/(games+0.00)* 100;
    	}
    	
    	zankWinRateLabel.setText(winRate+"%");

    	int[][] wonGamesRival = calculateWonGamesPerRival();//played|won
    	
    	zankStatisticsCom1Label.setText(wonGamesRival[0][0]+"|"+wonGamesRival[0][1]+"");

    	zankStatisticsCom2Label.setText(wonGamesRival[1][0] +"|"+wonGamesRival[1][1]+"");

    	zankStatisticsCom3Label.setText(wonGamesRival[2][0] +"|"+wonGamesRival[2][1]+"");

    	zankStatisticsPlayer2Label.setText(wonGamesRival[3][0] +"|"+wonGamesRival[3][1]+"");
    	
    	double averageTime = calulateAverageTimeMulti(zankStatistics);
    	
    	zankAverageTimeLabel.setText(averageTime+"");
    }

    private double calulateAverageTimeMulti(ArrayList<MultiplayerStatistics> list){
    	double games = list.size();
    	double playTime = 0;
    	for(MultiplayerStatistics gameStat : list){
    		playTime+= gameStat.getNeededTime();
    	}
    	if(games >0){
    		return playTime/games;
    	}
    	return 0.00;
    }
    
	/**
	 * @return the patienceController
	 */
	public PatienceController getPatienceController() {
		return patienceController;
	}

	/**
	 * @param patienceController the patienceController to set
	 */
	public void initStatistics() {		
		this.freeCellStatistics = this.patienceController.getPatience().getStatistics().getFreecellStatistics();
		this.acesUpStatistics = this.patienceController.getPatience().getStatistics().getAcesUpStatistics();
		this.zankStatistics = this.patienceController.getPatience().getStatistics().getZankStatistics();
	}
    

  private int calculateWonGamesMultiPlay( ArrayList<MultiplayerStatistics> list){
  	int result =0;
		for(MultiplayerStatistics gameStat:list){
			if(gameStat.getCardsPlayer1() == 0){
				result++;
			}
		}
		return result;
  }
    
    private int[][] calculateWonGamesPerRival(){
    	int amountOfRivals = 4;//player2|AIeasy|AInormal|AIpro
    	int separations = 2;//played|won
    	int[][] multiStat = new int[amountOfRivals][separations];
    	for(MultiplayerStatistics gameStat : zankStatistics){
    		int remainingCards = gameStat.getCardsPlayer1();
    		if(gameStat.getPlayer2().toString().equals("PLAYER")){
    		 	multiStat[3][0]++; //games vs Player2
    		 	System.out.println(remainingCards);
    			if(remainingCards == 0){
    				multiStat[3][1]++; // games won vs Player2
    			}
    		}
    		else if(gameStat.getPlayer2().toString().equals("AIEASY")){
    			multiStat[0][0]++; //games vs AIeasy
    			if(remainingCards == 0);{
    				multiStat[0][1]++; // games won vs AIeasy
    			}
    		}
    		else if(gameStat.getPlayer2().toString().equals("AINORMAL")){
    			multiStat[1][0]++; //games vs AInormal
    			if(remainingCards == 0);{
    				multiStat[1][1]++; // games won vs AInormal
    			}
    		}
    		else if(gameStat.getPlayer2().toString().equals("AIPRO")){
    			multiStat[2][0]++; //games vs AInormal
    			if(remainingCards == 0);{
    				multiStat[2][1]++; // games won vs AInormal
    			}
    		}
    	}
    	return multiStat;
    }
    
    
}
