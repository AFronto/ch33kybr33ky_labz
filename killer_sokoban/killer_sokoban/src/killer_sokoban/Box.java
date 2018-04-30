package killer_sokoban;

import static killer_sokoban.Game.*;
import static killer_sokoban.Interpreter.*;

public class Box extends Moveable {

	private boolean stuck;
	private Player lastTouchedMe;

	/**
	 * Konstruktor, hivja az ose konstruktorat, illetve noveli a Boxok szamlalojat egyel.
	 */
	public Box() {
		super();
		CountBoxes(1); //Egyel noveli a Box szamlalot
		stuck = false; //Alapbol nincs beszorulva
		//Betoltendo kep utvonala
		image="box.png";
	}

	/**
	 * Visszadja ki mozgatta meg a dobozt.
	 * @return Az ot utoljara megerinto Player.
	 */
	public Player GetLastTouchedMe() {
		return lastTouchedMe;
	}

	/**
	 * Ezzel a fuggvennyel kezeljuk le a doboz mozgatasat.
	 *
	 * @param p   A player aki a mozgast inditotta.
	 * @param d   Az irany amibe a doboz mozdul.
	 * @return 	  Igaz hamis ertekkel ter vissza attol fuggoen hogy sikeresen
	 *        	  mozgott e.
	 * @throws    Ez az Exception azert felel ha rossz volna a sorrend vagy mert elfogy az ero.
	 */
	public boolean Control(Player p, Direction d, int f) {

		///SequenceCheck(this);
		///PushPos(myField,d);

		boolean canGo = false;
		lastTouchedMe = p;
		if (p == null) { // Ez elvileg nem lehet nulla, de ki tudja
			System.out.println("Invalid movement from the box");
		}
		if (p != null) {
			//A szomszedra hivjuk a step fuggvenyt, tehat lepesi szandekot kezdemenyezunk
			Field myNeighbour = this.GetmyField().GetNeighbour(d);
			canGo = myNeighbour.Step(p, d, f);
			if (canGo) {
				//Ha sikerult leszedjuk a jelenlegi helyrol atteszuk a szomszedra es lefut a mezo akcioja
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

		///CheckPos(this,d);
		
		//vegso visszateresi ertek
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
	 * Amikor a doboz beszorul akkor kivonjuk az osszes dobozok szamabol.
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
		return false;
	}

	/**
	 * Doboz megnyomja a gombot.
	 */
	public void Press() {
		myField.Activate(true);
	}

	/**
	 * Eltunteti a dobozt
	 */
	public void Die() {
		CountBoxes(-1);
	}


	@Override
	public String toString() {
		return "Box";
	}

}
