package killer_sokoban;
import static killer_sokoban.Game.*;

public abstract class Moveable {

	protected Field myField;
	protected String image;

	
	public String GetImage(){
		return image;
	}
	/**
	 * Megadja, hogy a Moveable be van-e szorulva. Itt defaultbol false-szal ter vissza, de minden leszarmazott
	 * valodi korulmenyek alapjan ter vissza ertekkel.
	 *
	 * @return Mindig false-szal ter vissza, a leszarmazottjai mashogy valositjak meg.
	 */
	public boolean IsStuck(){
		return false;
	}
	
	/**
	 * Beallitja hogy a Moveable be van-e szorulva. Itt nem csinal semmit, de a leszarmazottjaiban igen.
	 */
	public void SetStuck(){}

	/**
	 * Egy lepest valosit meg egy Moveablebol leszarmaztatott objektumon. Itt nem tortenik semmi.
	 *
	 * @param p - A jatekos aki kezdemenyezte a lepest.
	 * @param d - Az irany amerre a mozgas toretnik.
	 * @param f - Az ero, amivel a mozgast kezdemenyezo Moveable rendelkezik meg.
	 * @return Logikai ertek, hogy megteheto-e a lepes.
	 */
	public boolean Control(Player p, Direction d,int f) {
		return true; 
	}
	
	/**
	 * Visszater a Moveablet tartalmazo Fielddel.
	 *
	 * @param A Moveable-t tartalmazo Field.
	 * @return A Moveable-t tartalmazo Field.
	 */
	public Field GetmyField() {	
		return myField;		
	}
	
	/**
	 * Beallitja a Moveable-t tartalmazo Fieldet.
	 *
	 * @param f A Field ami tartalmazza a Moveablet.
	 */
	public void SetmyField(Field f) {	
		myField = f;		
	}
	
	/**
	 * Kezdemenyezi a Moveable leszarmazottjanak "megoleset" (osszenyomas) es eltunteteset elokeszito fuggveny.
	 */
	public abstract boolean Kill();
	
	/**
	 * Egy Moveable leszarmazott gombnyomasat valositja meg. Absztrakt fuggveny.
	 */
	public abstract void Press();
	
	/**
	 * Egy Moveable leszarmazottat utoljara megerinto Playerrel ter vissza. Absztrakt fuggveny.
	 */
	public abstract Player GetLastTouchedMe();
	
	/**
	 * Az adott Moveable megolese. Absztrakt fuggveny.
	 */
	public abstract void Die();
	
}
