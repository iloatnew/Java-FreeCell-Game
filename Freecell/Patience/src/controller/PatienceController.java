package controller;

import aicontroller.AcesUpAIController;
import aicontroller.FreeCellAIController;
import aicontroller.ZankAIController;
import application.AcesUpGUIController;
import application.FreeCellGUIController;
import model.Patience;

public class PatienceController {

	private Patience patience;

	private IOController iOController;

	private ZankController zankController;

	private FreeCellController freeCellController;

	private FreeCellGUIController freeCellGuiController;

	private AcesUpController acesUpController;

	private DeckController deckController;

	private ZankAIController zankAIController;

	private FreeCellAIController freeCellAIController;

	private AcesUpAIController acesUpAIController;

	private AcesUpGUIController acesUpGuiController;
	
	private AutoMoveController autoMoveController;

	/**
	 * @return the patience
	 */
	public Patience getPatience() {
		return patience;
	}

	public AcesUpGUIController getAcesUpGuiController() {
		return acesUpGuiController;
	}

	public void setAcesUpGuiController(AcesUpGUIController acesUpGuiController) {
		this.acesUpGuiController = acesUpGuiController;
	}

	/**
	 * @param patience
	 *            the patience to set
	 */
	public void setPatience(Patience patience) {
		this.patience = patience;
	}

	/**
	 * @return the iOController
	 */
	public IOController getiOController() {
		return iOController;
	}

	/**
	 * @param iOController
	 *            the iOController to set
	 */
	public void setiOController(IOController iOController) {
		this.iOController = iOController;
	}

	/**
	 * @return the zankController
	 */
	public ZankController getZankController() {
		return zankController;
	}

	/**
	 * @param zankController
	 *            the zankController to set
	 */
	public void setZankController(ZankController zankController) {
		this.zankController = zankController;
	}

	/**
	 * @return the freeCellController
	 */
	public FreeCellController getFreeCellController() {
		return freeCellController;
	}

	/**
	 * @param freeCellController
	 *            the freeCellController to set
	 */
	public void setFreeCellController(FreeCellController freeCellController) {
		this.freeCellController = freeCellController;
	}

	/**
	 * @return the acesUpController
	 */
	public AcesUpController getAcesUpController() {
		return acesUpController;
	}

	/**
	 * @param acesUpController
	 *            the acesUpController to set
	 */
	public void setAcesUpController(AcesUpController acesUpController) {
		this.acesUpController = acesUpController;
	}

	/**
	 * @return the deckController
	 */
	public DeckController getDeckController() {
		return deckController;
	}

	/**
	 * @param deckController
	 *            the deckController to set
	 */
	public void setDeckController(DeckController deckController) {
		this.deckController = deckController;
	}

	/**
	 * @return the zankAIController
	 */
	public ZankAIController getZankAIController() {
		return zankAIController;
	}

	/**
	 * @param zankAIController
	 *            the zankAIController to set
	 */
	public void setZankAIController(ZankAIController zankAIController) {
		this.zankAIController = zankAIController;
	}

	/**
	 * @return the freeCellAIController
	 */
	public FreeCellAIController getFreeCellAIController() {
		return freeCellAIController;
	}

	/**
	 * @param freeCellAIController
	 *            the freeCellAIController to set
	 */
	public void setFreeCellAIController(FreeCellAIController freeCellAIController) {
		this.freeCellAIController = freeCellAIController;
	}

	/**
	 * @return the acesUpAIController
	 */
	public AcesUpAIController getAcesUpAIController() {
		return acesUpAIController;
	}

	/**
	 * @param acesUpAIController
	 *            the acesUpAIController to set
	 */
	public void setAcesUpAIController(AcesUpAIController acesUpAIController) {
		this.acesUpAIController = acesUpAIController;
	}

	public FreeCellGUIController getFreeCellGuiController() {
		return freeCellGuiController;
	}

	public void setFreeCellGuiController(FreeCellGUIController freeCellGuiController) {
		this.freeCellGuiController = freeCellGuiController;
	}
	
	public AutoMoveController getAutoMoveController(){
		return autoMoveController;
	}
	
	public void setAutoMoveController(AutoMoveController autoMoveController){
		this.autoMoveController = autoMoveController;
	}

	/**
	 * @param patience
	 * @param iOController
	 * @param zankController
	 * @param freeCellController
	 * @param acesUpController
	 * @param deckController
	 * @param zankAIController
	 * @param freeCellAIController
	 * @param acesUpAIController
	 */
	/*public PatienceController(Patience patience, IOController iOController, ZankController zankController,
			FreeCellController freeCellController, AcesUpController acesUpController, DeckController deckController,
			ZankAIController zankAIController, FreeCellAIController freeCellAIController,
			AcesUpAIController acesUpAIController) {
		super();
		this.patience = patience;
		this.iOController = iOController;
		this.zankController = zankController;
		this.freeCellController = freeCellController;
		this.acesUpController = acesUpController;
		this.deckController = deckController;
		this.zankAIController = zankAIController;
		this.freeCellAIController = freeCellAIController;
		this.acesUpAIController = acesUpAIController;
	}*/

	public PatienceController() {
		this.patience = new Patience();
		this.patience = new Patience();
		this.iOController = new IOController();
		this.zankController = new ZankController();
		this.freeCellController = new FreeCellController();
		this.acesUpController = new AcesUpController();
		this.deckController = new DeckController();
		this.zankAIController = new ZankAIController();
		this.freeCellAIController = new FreeCellAIController();
		this.acesUpAIController = new AcesUpAIController();
		this.autoMoveController = new AutoMoveController();
	}
	
	public void initController(){
		this.iOController.setPatienceController(this);
		this.deckController.setPatienceController(this);
		this.acesUpController.setPatienceController(this);
		this.freeCellController.setPatienceController(this);
		this.zankController.setPatienceController(this);
		this.autoMoveController.setPatienceController(this);
		this.acesUpController.setAIController(acesUpAIController);
		this.freeCellController.setAIController(freeCellAIController);
		this.zankController.setAIController(zankAIController);
		this.acesUpController.setAutoMoveController(autoMoveController);
		this.freeCellController.setAutoMoveController(autoMoveController);
		this.zankController.setAutoMoveController(autoMoveController);
		this.acesUpAIController.initAI(autoMoveController);
		this.zankAIController.initAI(autoMoveController);
	}

}
