package killer_sokoban;
import static killer_sokoban.Game.*;

public abstract class Moveable {

	protected Field myField;
	
	/**
	 * Megadja, hogy a Moveable be van-e szorulva. Itt defaultbol false-szal ter vissza, de minden leszarmazott
	 * valodi korulmenyek alapjan ter vissza ertekkel.
	 */
	public boolean IsStuck(){
		return false;
	}
	
	/**
	 * Beallitja hogy a Moveable be van-e szorulva. Itt nem csinal semmit, de a leszarmazottjaiban igen.
	 */
	public void SetStuck(){}

	/**
	 * 
	 */
	public boolean Control(Player p, Direction d,int f) throws Exception{
		return true; 
	}
	
	public Field GetmyField() {	
		return myField;		
	}
	
	public void SetmyField(Field f) {	
		myField = f;		
	}
	
	public abstract boolean Kill();
	public abstract void Press();
	public abstract Player GetLastTouchedMe();
	public abstract void Die();
	
}
