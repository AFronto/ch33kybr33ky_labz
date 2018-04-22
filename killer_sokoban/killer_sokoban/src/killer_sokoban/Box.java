package killer_sokoban;

import static killer_sokoban.Game.*;
import static killer_sokoban.Interpreter.*;

public class Box extends Moveable {

	private boolean stuck;
	private Player lastTouchedMe;

	/**
	 * Konstruktor
	 */
	public Box() {
		super();
		// //printOnConstruct("Box");
		CountBoxes(1);
		stuck = false;
		// //printOnExitConstuctor("Box");
	}

	/**
	 * Visszadja ki mozgatta meg a dobozt.
	 */
	public Player GetLastTouchedMe() {
		//printOnEntry(this, "GetLastTouchedMe");
		//printOnExit(this, "GetLastTouchedMe", lastTouchedMe + "");
		return lastTouchedMe;
	}

	/**
	 * Ezzel a fuggvennyel kezeljuk le a doboz mozgatasat.
	 *
	 * @param p
	 *            A player aki a mozgast inditotta.
	 * @param d
	 *            Az irany amibe a doboz mozdul.
	 * @return Igaz hamis ertekkel ter vissza attol fuggoen hogy sikeresen
	 *         mozgott e.
	 * @throws Exception
	 */
	public boolean Control(Player p, Direction d, int f) throws Exception {
		////printOnEntry(this, "Control", p + "", d + "");

		SequenceCheck(this);
		PushPos(myField,d);

		boolean canGo = false;
		lastTouchedMe = p;
		if (p == null) { // Ez elvileg nem lehet nulla, de ki tudja
			System.out.println("Valami nem jo ba$tya");
		}
		if (p != null) {
			Field myNeighbour = this.GetmyField().GetNeighbour(d);
			canGo = myNeighbour.Step(p, d, f);
			if (canGo) {
				myField.Remove();
				myNeighbour.Register(this);
				myNeighbour.FieldAction();
			} else { // Megnezzuk, hogy player van-e ott, ha igen, akkor
						// megoljuk
				if (myNeighbour.GetmyMoveable() != null) { 
					//just for the interpreter
					Moveable actm = myNeighbour.GetmyMoveable();
					//just for the interpreter
					boolean died = myNeighbour.GetmyMoveable().Kill();
					if (died) { // Ha sikerult megolni..
						moveableDestroyed(actm);
						myField.Remove();
						myNeighbour.Register(this);
						myNeighbour.FieldAction();
						canGo = true; // .. akkor lehet jonni
					}
				}
			}
		}

		CheckPos(this,d);

		return canGo;
	}

	/**
	 * A beszorulas ellenorzesehez hasznalt fuggveyn.
	 *
	 * @return Visszaadja, hogy a doboz be van-e szorulva.
	 */
	public boolean IsStuck() {
		return stuck;
	}

	/**
	 * Beallitja, hogy be van-e szorulva a doboz.
	 */
	public void SetStuck() {
		stuck = true;
		CountBoxes(-1);
	}

	/**
	 * Azt kezeli, hogy az adott obijektum ossze nyomhato e. ("Megolheto-e?") A
	 * doboz nem ossze nyomhato tehat nem tesz semmit.
	 *
	 * @return Hamissal ter vissza mert a doboz nem hal meg.
	 */
	public boolean Kill() {
		//printOnEntry(this, "Kill");
		//printOnExit(this, "Kill", false + "");
		return false;
	}

	/**
	 * Doboz megnyomja a gombot.
	 */
	public void Press() {

		//printOnEntry(this, "Press");
		myField.Activate(true);
		//printOnExit(this, "Press", null);

	}

	/**
	 * Eltunteti a dobozt
	 */
	public void Die() {
		// //printOnEntry(this, "Die");
		CountBoxes(-1);
		// //printOnExit(this, "Die", null);
	}

	////////////////////////////////////////////////////////////// SKELETON
	////////////////////////////////////////////////////////////// FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString() {
		return "Box";
	}

}
