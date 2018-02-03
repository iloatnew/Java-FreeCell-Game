package controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Card;
import model.Cardstack;
import model.Game;
import model.Move;
import model.Rank;
import model.Statistics;
import model.Suit;
import model.Type;
import model.Zank;

/**
 * @author sopr041
 *
 */
/**
 * @author sopr041
 *
 */
/**
 * @author sopr041
 *
 */
public class IOController {

	private PatienceController patienceController;

	/**
	 * @return the patienceController
	 */
	public PatienceController getPatienceController() {
		return patienceController;
	}

	/**
	 * @param patienceController
	 *            the patienceController to set
	 */
	public void setPatienceController(PatienceController patienceController) {
		this.patienceController = patienceController;
	}

	// TODO:
	// game implements Serializable + testen
	public void saveGame(Game game, String fileName) {
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;
		try {
			fileOut = new FileOutputStream(fileName);
			out = new ObjectOutputStream(fileOut);
			out.writeObject(game);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
				}
			if (fileOut != null)
				try {
					fileOut.close();
				} catch (IOException e) {
				}
		}
	}

	public Game loadGame(String filePath) {
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();
			if (obj instanceof Game) {
				Game so = (Game) obj;
				return so;

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (ois != null)
				try {
					ois.close();
				} catch (IOException e) {
				}
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
				}
		}
		return null;

	}

	public void saveStatistics() {
		FileOutputStream fileOut = null;
		ObjectOutputStream out = null;
		try {
			fileOut = new FileOutputStream("statistic.ser");
			out = new ObjectOutputStream(fileOut);
			out.writeObject(this.patienceController.getPatience().getStatistics());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
				}
			if (fileOut != null)
				try {
					fileOut.close();
				} catch (IOException e) {
				}
		}
	}

	public void loadStatistics() {
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("statistic.ser");
			ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();
			// Einlesen todo
			if (obj instanceof Statistics) {
				Statistics so = (Statistics) obj;
				this.patienceController.getPatience().setStatistics(so);
			}
		} catch (FileNotFoundException e) {
			// TODO
			Statistics stat = new Statistics();
			this.patienceController.getPatience().setStatistics(stat);
			saveStatistics();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (ois != null)
				try {
					ois.close();
				} catch (IOException e) {
				}
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
				}
		}

	}

	/**
	 * player2 ist Gegner
	 * 
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public Move stringToMoveZank(String string) throws Exception {

		Cardstack from = new Cardstack(Type.DEFAULT);
		Cardstack to = new Cardstack(Type.DEFAULT);
		String[] moves = string.split("->");

		for (int i = 0; i < 2; i++) {
			switch (moves[i]) {
			case "A1":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getReserves()[1];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getReserves()[1];
				}
				break;
			case "A2":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getPlayingStacks()[0];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getPlayingStacks()[0];
				}
				break;
			case "A3":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getPlayingStacks()[1];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getPlayingStacks()[1];
				}
				break;
			case "A4":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getPlayingStacks()[2];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getPlayingStacks()[2];
				}
				break;
			case "A5":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getPlayingStacks()[3];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getPlayingStacks()[3];
				}
				break;
			case "B1":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getHands()[1];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getHands()[1];
				}
				break;
			case "B2":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getEliminationStacks()[0];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getEliminationStacks()[0];
				}
				break;
			case "B3":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getEliminationStacks()[1];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getEliminationStacks()[1];
				}
				break;
			case "B4":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getEliminationStacks()[2];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getEliminationStacks()[2];
				}
				break;
			case "B5":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getEliminationStacks()[3];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getEliminationStacks()[3];
				}
				break;
			case "B6":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getWastePiles()[0];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getWastePiles()[0];
				}
				break;
			case "C1":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getWastePiles()[1];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getWastePiles()[1];
				}
				break;
			case "C2":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getEliminationStacks()[4];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getEliminationStacks()[4];
				}
				break;
			case "C3":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getEliminationStacks()[5];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getEliminationStacks()[5];
				}
				break;
			case "C4":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getEliminationStacks()[6];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getEliminationStacks()[6];
				}
				break;
			case "C5":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getEliminationStacks()[7];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getEliminationStacks()[7];
				}
				break;
			case "C6":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getHands()[0];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getHands()[0];
				}
				break;
			case "D2":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getPlayingStacks()[4];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getPlayingStacks()[4];
				}
				break;
			case "D3":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getPlayingStacks()[5];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getPlayingStacks()[5];
				}
				break;
			case "D4":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getPlayingStacks()[6];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getPlayingStacks()[6];
				}
				break;
			case "D5":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getPlayingStacks()[7];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getPlayingStacks()[7];
				}
				break;
			case "D6":
				if (i == 0) {
					from = ((Zank) this.patienceController.getPatience().getGame()).getReserves()[0];
				} else {
					to = ((Zank) this.patienceController.getPatience().getGame()).getReserves()[0];
				}
				break;
			default:
				throw new Exception("Fehlerhafte Eingabe!");
			}
		}
		return new Move(from, to, 1);
	}

	public String moveToStringZank(Move move) {
		String from = "";
		String to = "";

		switch (move.getFrom().getType()) {
		case RESERVE:
			if (move.getFrom().equals(((Zank) patienceController.getPatience().getGame()).getReserves()[1])) {
				from = "A1";
			} else {
				from = "D6";
			}
			break;
		case HAND:
			if (move.getFrom().equals(((Zank) patienceController.getPatience().getGame()).getHands()[1])) {
				from = "B1";
			} else {
				from = "B6";
			}
			break;
		case WASTEPILE:
			if (move.getFrom().equals(((Zank) patienceController.getPatience().getGame()).getWastePiles()[1])) {
				from = "C1";
			} else {
				from = "C6";
			}
			break;
		case PLAYINGSTACK:
			if (move.getFrom().equals(((Zank) patienceController.getPatience().getGame()).getPlayingStacks()[0])) {
				from = "A2";
			}
			if (move.getFrom().equals(((Zank) patienceController.getPatience().getGame()).getPlayingStacks()[1])) {
				from = "A3";
			}
			if (move.getFrom().equals(((Zank) patienceController.getPatience().getGame()).getPlayingStacks()[2])) {
				from = "A4";
			}
			if (move.getFrom().equals(((Zank) patienceController.getPatience().getGame()).getPlayingStacks()[3])) {
				from = "A5";
			}
			if (move.getFrom().equals(((Zank) patienceController.getPatience().getGame()).getPlayingStacks()[4])) {
				from = "D2";
			}
			if (move.getFrom().equals(((Zank) patienceController.getPatience().getGame()).getPlayingStacks()[5])) {
				from = "D3";
			}
			if (move.getFrom().equals(((Zank) patienceController.getPatience().getGame()).getPlayingStacks()[6])) {
				from = "D4";
			}
			if (move.getFrom().equals(((Zank) patienceController.getPatience().getGame()).getPlayingStacks()[7])) {
				from = "D5";
			}
			break;
		case ELIMINATIONSTACKDIAMOND:
			if (move.getFrom().equals(((Zank) patienceController.getPatience().getGame()).getEliminationStacks()[0])) {
				from = "B2";
			} else {
				from = "C2";
			}
			break;
		case ELIMINATIONSTACKSPADE:
			if (move.getFrom().equals(((Zank) patienceController.getPatience().getGame()).getEliminationStacks()[1])) {
				from = "B3";
			} else {
				from = "C3";
			}
			break;
		case ELIMINATIONSTACKCLUB:
			if (move.getFrom().equals(((Zank) patienceController.getPatience().getGame()).getEliminationStacks()[2])) {
				from = "B4";
			} else {
				from = "C4";
			}
			break;
		case ELIMINATIONSTACKHEART:
			if (move.getFrom().equals(((Zank) patienceController.getPatience().getGame()).getEliminationStacks()[3])) {
				from = "B5";
			} else {
				from = "C5";
			}
			break;

		default:
			break;
		}

		switch (move.getTo().getType()) {
		case RESERVE:
			if (move.getTo().equals(((Zank) patienceController.getPatience().getGame()).getReserves()[1])) {
				to = "A1";
			} else {
				to = "D6";
			}
			break;
		case HAND:
			if (move.getTo().equals(((Zank) patienceController.getPatience().getGame()).getHands()[1])) {
				to = "B1";
			} else {
				to = "B6";
			}
			break;
		case WASTEPILE:
			if (move.getTo().equals(((Zank) patienceController.getPatience().getGame()).getWastePiles()[1])) {
				to = "C1";
			} else {
				to = "C6";
			}
			break;
		case PLAYINGSTACK:
			if (move.getTo().equals(((Zank) patienceController.getPatience().getGame()).getPlayingStacks()[0])) {
				to = "A2";
			}
			if (move.getTo().equals(((Zank) patienceController.getPatience().getGame()).getPlayingStacks()[1])) {
				to = "A3";
			}
			if (move.getTo().equals(((Zank) patienceController.getPatience().getGame()).getPlayingStacks()[2])) {
				to = "A4";
			}
			if (move.getTo().equals(((Zank) patienceController.getPatience().getGame()).getPlayingStacks()[3])) {
				to = "A5";
			}
			if (move.getTo().equals(((Zank) patienceController.getPatience().getGame()).getPlayingStacks()[4])) {
				to = "D2";
			}
			if (move.getTo().equals(((Zank) patienceController.getPatience().getGame()).getPlayingStacks()[5])) {
				to = "D3";
			}
			if (move.getTo().equals(((Zank) patienceController.getPatience().getGame()).getPlayingStacks()[6])) {
				to = "D4";
			}
			if (move.getTo().equals(((Zank) patienceController.getPatience().getGame()).getPlayingStacks()[7])) {
				to = "D5";
			}
			break;
		case ELIMINATIONSTACKDIAMOND:
			if (move.getTo().equals(((Zank) patienceController.getPatience().getGame()).getEliminationStacks()[0])) {
				to = "B2";
			} else {
				to = "C2";
			}
			break;
		case ELIMINATIONSTACKSPADE:
			if (move.getTo().equals(((Zank) patienceController.getPatience().getGame()).getEliminationStacks()[1])) {
				to = "B3";
			} else {
				to = "C3";
			}
			break;
		case ELIMINATIONSTACKCLUB:
			if (move.getTo().equals(((Zank) patienceController.getPatience().getGame()).getEliminationStacks()[2])) {
				to = "B4";
			} else {
				to = "C4";
			}
			break;
		case ELIMINATIONSTACKHEART:
			if (move.getTo().equals(((Zank) patienceController.getPatience().getGame()).getEliminationStacks()[3])) {
				to = "B5";
			} else {
				to = "C5";
			}
			break;

		default:
			break;
		}

		return from + "->" + to;
	}

	/**
	 * Parsed aus einer Datei (Dateipfad wird als Argument übergeben) ein
	 * Cardstack mit dem dann gespielt werden kann
	 * 
	 * @param path
	 * @return ein kartendeck
	 */
	public Cardstack readCardstackFromFile(String path) {
		String zeile;
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(path));
			Cardstack cardStack = new Cardstack(Type.DEFAULT);
			while ((zeile = br.readLine()) != null) {
				String[] cardStrings = zeile.split(" ");
				for (String cardString : cardStrings) {
					String[] sr = cardString.split("-");
					Suit suit;
					Rank rank;
					if (sr[0].equals("Pik"))
						suit = Suit.SPADE;
					else if (sr[0].equals("Kreuz"))
						suit = Suit.CLUB;
					else if (sr[0].equals("Karo"))
						suit = Suit.DIAMOND;
					else if (sr[0].equals("Herz"))
						suit = Suit.HEART;
					else
						throw new IOException("Symbol konnte nicht gelesen werden");

					if (sr[1].equals("Ass"))
						rank = Rank.ACE;
					else if (sr[1].equals("Bube"))
						rank = Rank.JACK;
					else if (sr[1].equals("Dame"))
						rank = Rank.QUEEN;
					else if (sr[1].equals("Koenig"))
						rank = Rank.KING;
					else if (sr[1].equals("10"))
						rank = Rank.TEN;
					else if (sr[1].equals("9"))
						rank = Rank.NINE;
					else if (sr[1].equals("8"))
						rank = Rank.EIGHT;
					else if (sr[1].equals("7"))
						rank = Rank.SEVEN;
					else if (sr[1].equals("6"))
						rank = Rank.SIX;
					else if (sr[1].equals("5"))
						rank = Rank.FIVE;
					else if (sr[1].equals("4"))
						rank = Rank.FOUR;
					else if (sr[1].equals("3"))
						rank = Rank.THREE;
					else if (sr[1].equals("2"))
						rank = Rank.TWO;
					else
						throw new IOException("Wert konnte nicht gelesen werden");

					cardStack.add(new Card(rank, suit));

					br.readLine();
				}
			}
			br.close();
			cardStack.flipStack();
			return cardStack;
		} catch (FileNotFoundException e) {
			System.err.println("Datei nicht gefunden");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
		return null;
	}
}