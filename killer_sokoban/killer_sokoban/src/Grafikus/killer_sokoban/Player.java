package killer_sokoban;

import static killer_sokoban.Game.*;
 

public class Player extends Moveable {

	private int score;
	private int strength;
	private String color;
	/**
	 * Konstruktor
	 * @param s - A letrejovo Player toloereje.
	 */
	public Player(int s,String color) {
		super();
		//Nullla ponttal indul mindenki
		score = 0;
		//Annyi erovel, amennyit megadunk
		strength = s;

		this.color=color;
	}

	public String GetColor(){
		return color;
	}

	/**
	 * Egyet ad a Player pontjaihoz
	 */
	public void AddScore() {
		score++;
		UpdateScore(this);
	}

	/**
	 * Visszaadja a Player pontjait
	 * @return Az adott jatekos pontszama.
	 */
	public int GetScore() {
		return score;
	}

	/**
	 * Ezzel a fuggvennyel kezeljuk le a jatekos mozgatasat.
	 *
	 * @param p   A player aki a mozgast inditotta.
	 * @param d   Az irany amibe a doboz mozdul.
	 * @return    Igaz hamis ertekkel ter vissza attol fuggoen hogy sikeresen
	 *            mozgott e.
	 * @throws 	  Ez az Exception azert felel ha rossz volna a sorrend vagy mert elfogy az ero.
	 */
	public boolean Control(Player m, Direction d, int f) {
		///SequenceCheck(this);
		///PushPos(myField,d);

		boolean canGo = false;
		Field myNeighbour;
		myNeighbour = this.GetmyField().GetNeighbour(d);
		if (m == null) {
			//A szomszedra hivjuk a step fuggvenyt, tehat lepesi szandekot kezdemenyezunk
			canGo = myNeighbour.Step(this, d, this.strength);
			if (canGo) {
				//Ha sikerult leszedjuk a jelenlegi helyrol atteszuk a szomszedra
				myField.Remove();
				myNeighbour.Register(this);
				myNeighbour.FieldAction();
			}
		}
		if (m != null) {
			if (m.GetmyField().equals(myField.GetNeighbour(d.Opposite()))) { // Player tol Player eset ellenorzese
				///CheckPos(this,d);
				return false;
			}
			canGo = myNeighbour.Step(this, d, f);
			if (canGo) {
				myField.Remove();
				myNeighbour.Register(this);
				myNeighbour.FieldAction();
			}
		}
		////CheckPos(this,d);
		//Visszateres
		return canGo;
	}

	/**
	 * Torli a jatekos pontjait es elkezdi kivenni a jatekbol.
	 */
	public void DeadScore() {
		//-1 lesz a Player pontja ha meghal
		score = -1;
		UpdateScore(this);
	}

	/**
	 * Azt kezeli, hogy az adott obijektum ossze nyomhato e. ("Megolheto-e?") A
	 * Player ossze nyomhato tehat meghal es eltunik.
	 *
	 * @return Igazzal ter vissza mert a Player ossze nyomhato.
	 */
	public boolean Kill() {
		Die();
		myField.Remove();
		return true;
	}

	/**
	 * Ez az a fuggveny ami megnyomja a gombot amikor a jatekos ralep.
	 */
	public void Press() {
		//Mivel jatekos nem tudja aktivalni ezert mindig false-al hivodik meg
		myField.Activate(false);
	}

	/**
	 * Megadja, hogy ki erintette meg utoljara.
	 * @return null-t add vissza, mert csak a boxoknal szamit, hogy ki erintette meg
	 * utoljara.
	 */
	public Player GetLastTouchedMe() {
		return null;
	}

	/**
	 * A player mikor meghal ki esik a jatekbol ezt kezeli le ez a fuggveny.
	 */
	public void Die() {
		//Visszaalitja a pontjait
		DeadScore();
	}
	
	/**
	 * Visszaadja az adott Player erejet.
	 * @return Az adott Player toloereje.
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * Megvaltoztatja az adott Field surlodasat.
	 */
	public void changeFriction() {
		myField.SetFriction();
	}

	@Override
	public String toString() {
		return "Player";
	}
}
