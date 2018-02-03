/**
 * Sample Skeleton for 'ZankCLI.fxml' Controller Class
 */

package application;

import java.net.URL;
import java.util.ResourceBundle;

import controller.PatienceController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Move;

public class ZankCLIController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="anchorPane"
    private AnchorPane anchorPane; // Value injected by FXMLLoader

    @FXML // fx:id="textField"
    private TextField textField; // Value injected by FXMLLoader

    @FXML // fx:id="textFlow"
    private TextArea textArea; // Value injected by FXMLLoader
    
    private PatienceController patienceController;

    @FXML
    void onKeyPressed(KeyEvent event) {
    	String text = textField.getText();
    	if(event.getCode().equals(KeyCode.ENTER) && !text.isEmpty()){
    		try{
    		textArea.setText(textArea.getText() + text + "\n");
    		Move move = this.patienceController.getiOController().stringToMoveZank(text);    		
    		this.patienceController.getZankController().setStackToDrag(move.getFrom());
    		
    		if(this.patienceController.getZankController().isDroppable(move.getTo())){
    			this.patienceController.getZankController().executeMove(move);
    		}
    		}
    		catch(Exception e){
    			textArea.setText(textArea.getText() + e.getMessage() + "\n");
    		}
    		textArea.setScrollTop(Double.MAX_VALUE);
    		textField.clear();
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'ZankCLI.fxml'.";
        assert textField != null : "fx:id=\"textField\" was not injected: check your FXML file 'ZankCLI.fxml'.";
        assert textArea != null : "fx:id=\"textFlow\" was not injected: check your FXML file 'ZankCLI.fxml'.";
        textArea.setEditable(false);
    }

	public PatienceController getPatienceController() {
		return patienceController;
	}

	public void setPatienceController(PatienceController patienceController) {
		this.patienceController = patienceController;
	}
	
	public void addStringtoConsole(String text){
		this.textArea.setText(this.textArea.getText()+ "Computer sagt: " +text + "\n");
	}
	
	public String getConsoleContent(){
		return this.textArea.getText();
	}
	
	public void setConsoleContent(String text){
		this.textArea.setText(text);
	}
}
