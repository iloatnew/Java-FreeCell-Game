package aui;

import model.Move;

public interface ZankAUI {
	public void refreshAll();
	public void openFinishedWindow(String gametype, int player);
	public void animateMove(Move move);
}
