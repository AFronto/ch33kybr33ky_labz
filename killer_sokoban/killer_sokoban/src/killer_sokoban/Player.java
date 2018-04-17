package killer_sokoban;

import static killer_sokoban.Game.*;

public class Player extends Moveable {

	private int score;
	private int strength;

	/**
	 * Konstruktor
	 */
	public Player(int s) {
		super();
	//	printOnConstruct("Player");

		score = 0;
		strength = s;

	//	printOnExitConstuctor("Player");
	}

	/**
	 * Egyet ad a Player pontjaihoz
	 */
	public void AddScore() {
		printOnEntry(this, "AddScore");

		score++;
		UpdateScore(this);

		printOnExit(this, "AddScore", null);
	}

	public int GetScore() {
		printOnEntry(this, "GetScore");
		printOnExit(this, "GetScore", score + "");

		return score;
	}

	/**
	 * Ezzel a fuggvennyel kezeljuk le a jatekos mozgatasat.
	 *
	 * @param p
	 *            A player aki a mozgast inditotta.
	 * @param d
	 *            Az irany amibe a doboz mozdul.
	 * @return Igaz hamis ertekkel ter vissza attol fuggoen hogy sikeresen
	 *         mozgott e.
	 */
	public boolean Control(Player m, Direction d, int f) {
	//	printOnEntry(this, "Control", m + "", d + "");
		
		boolean canGo = false;
		
		if(m.GetmyField().equals(myField.GetNeighbour(d.Opposite()))){		//Player tol Player eset ellenorzese
			return false;
		}
		
		if (m != null) {
			Field myNeighbour = m.GetmyField().GetNeighbour(d);
			canGo = myNeighbour.Step(m, d, f);
			if(canGo){
				myField.Remove();
				myNeighbour.Register(m);
				myNeighbour.FieldAction();
			}
			/*
			String[] runStrings = { "1. Igen", "2.  Nem" };
			for (String s : runStrings) {
				printOption(s);
			}
			int sel = printQuestion("K?zvetlen?l egy Player akar tov?b tolni?", 1, 2);
			switch (sel) {
			case 1:
				canGo = false;
				break;
			case 2:
				myField = new Field();
				myField.Register(this);
				Field f2 = myField.GetNeighbour(d);

				canGo = f2.Step(this, d);
				if (canGo) {
					myField.Remove();
					f2.Register(this);
					myField = f2;
					f2.FieldAction();
				}
				break;
			}
		} else {
			myField = new Field();
			myField.Register(this);
			Field f2 = myField.GetNeighbour(d);

			canGo = f2.Step(this, d);
			if (canGo) {
				myField.Remove();
				f2.Register(this);
				myField = f2;
				f2.FieldAction();
			} /// ha false akkor mindenkepp marad a Player, a tobbit a rekurzio
				/// intezi
				 
			 */
		}
		
	//	printOnExit(this, "Control", canGo + "");
		return canGo;
	}

	/**
	 * Torli a jatekos pontjait es elkezdi kivenni a jatekbol.
	 */
	public void DeadScore() {
		printOnEntry(this, "DeadScore");

		score = -1;
		UpdateScore(this);

		printOnExit(this, "DeadScore", null);
	}

	/**
	 * Azt kezeli, hogy az adott obijektum ossze nyomhato e. ("Megolheto-e?") A
	 * Player ossze nyomhato tehat meghal es eltunik.
	 *
	 * @return Igazzal ter vissza mert a Player ossze nyomhato.
	 */
	public boolean Kill() {
		printOnEntry(this, "Kill");
		
		Die();
		myField.Remove();
		
		printOnExit(this, "Kill", true + "");
		return true;
	}

	/**
	 * Ez az a fuggveny ami megnyomja a gombot amikor a jatekos ralep.
	 */
	public void Press() {
		printOnEntry(this, "Press");

		myField.Activate(false);
		
		printOnExit(this, "Press", null);

	}

	/**
	 * null-t add vissza, mert csak a boxoknal szamit, hogy ki erintette meg
	 * utoljara.
	 */
	public Player GetLastTouchedMe() {
		printOnEntry(this, "GetLastTouchedMe");
		printOnExit(this, "GetLastTouchedMe", null + "");
		
		return null;
	}

	/**
	 * A player mikor meghal ki esik a jatekbol ezt kezeli le ez a fuggveny.
	 */
	public void Die() {
		printOnEntry(this, "Die");

		DeadScore();

		printOnExit(this, "Die", null);
	}
	
	public int getStrength() {
		return strength;
	}
	
	public void changeFriction() {
		myField.SetFriction();
	}

	////////////////////////////////////////////////////////////// SKELETON
	////////////////////////////////////////////////////////////// FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString() {
		return "Player";
	}
}
