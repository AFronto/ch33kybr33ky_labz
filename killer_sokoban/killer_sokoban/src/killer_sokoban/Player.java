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
		// printOnConstruct("Player");

		score = 0;
		strength = s;

		// printOnExitConstuctor("Player");
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
		// printOnEntry(this, "GetScore");
		// printOnExit(this, "GetScore", score + "");

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
	 * @throws Exception 
	 */
	public boolean Control(Player m, Direction d, int f) throws Exception {
		printOnEntry(this, "Control", m + "", d + "");

		boolean canGo = false;
		Field myNeighbour;
		try { // Az ero miatt
			if (m == null) {
				myNeighbour = this.GetmyField().GetNeighbour(d);
				canGo = myNeighbour.Step(this, d, this.strength);
				if (canGo) {
					myField.Remove();
					myNeighbour.Register(this);
					myNeighbour.FieldAction();
				}
			}
			if (m != null) {
				if (m.GetmyField().equals(myField.GetNeighbour(d.Opposite()))) { // Player tol Playereset ellenorzese
					return false;
				}
				myNeighbour = m.GetmyField().GetNeighbour(d);
				canGo = myNeighbour.Step(this, d, f);
				if (canGo) {
					myField.Remove();
					myNeighbour.Register(m);
					myNeighbour.FieldAction();
				}
			}
		} catch (Exception e) { // Ha a step dobott akkor tov�bbdobjuk az
								// Interpreternek
			throw e;
		}

		printOnExit(this, "Control", canGo + "");
		return canGo;
	}

	/**
	 * Torli a jatekos pontjait es elkezdi kivenni a jatekbol.
	 */
	public void DeadScore() {
		// printOnEntry(this, "DeadScore");

		score = -1;
		UpdateScore(this);

		// printOnExit(this, "DeadScore", null);
	}

	/**
	 * Azt kezeli, hogy az adott obijektum ossze nyomhato e. ("Megolheto-e?") A
	 * Player ossze nyomhato tehat meghal es eltunik.
	 *
	 * @return Igazzal ter vissza mert a Player ossze nyomhato.
	 */
	public boolean Kill() {
		// printOnEntry(this, "Kill");

		Die();
		myField.Remove();

		// printOnExit(this, "Kill", true + "");
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
		// printOnEntry(this, "GetLastTouchedMe");
		// printOnExit(this, "GetLastTouchedMe", null + "");

		return null;
	}

	/**
	 * A player mikor meghal ki esik a jatekbol ezt kezeli le ez a fuggveny.
	 */
	public void Die() {
		// printOnEntry(this, "Die");

		DeadScore();

		// printOnExit(this, "Die", null);
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
