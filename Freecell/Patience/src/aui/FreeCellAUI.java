package aui;

import model.Move;

public interface FreeCellAUI {
	public void initializeStacks(); 
	public void refreshAll();
	public void animateMove(Move move);

	public void openFinishedWindow(String string, int integer);

}
