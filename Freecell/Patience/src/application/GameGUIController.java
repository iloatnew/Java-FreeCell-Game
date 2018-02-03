package application;

import controller.PatienceController;

public abstract class GameGUIController{
	
	protected PatienceController patienceController;
	
	public PatienceController getPatienceController(){
		return this.patienceController;
	}

	public void setPatienceController(PatienceController patienceController){
		this.patienceController = patienceController;
	}
	
	public abstract void init();
}
