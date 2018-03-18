package killer_sokoban;
import static killer_sokoban.Game.*;

public abstract class Moveable {

	protected Field myField;
	
	public boolean IsStuck(){
		return false;
	}
	
	//A leszármazottakba van kidolgozva
	public boolean Control(Player p, Direction d){
		return true; ///ez a forditas miatt kell csak 
	}
	
	public Field GetmyField() {
		printOnEntry(this, "GetmyField");
		printOnExit(this, "GetmyField", myField+"");
		return myField;		
	}
	
	public void SetmyField(Field f) {
		printOnEntry(this, "SetmyField", f+"");
		myField = f;
		printOnExit(this, "SetmyField", null);
		
	}
	
	public abstract boolean Kill();
	public abstract void Press();
	public abstract Player GetLastTouchedMe();
	public abstract void Die();
	
}
