package killer_sokoban;
import static killer_sokoban.Game.*;

public abstract class Moveable {

	protected Field myField;
	
	public boolean IsStuck(){
		return false;
	}
	
	public boolean Control(Player p, Direction d,int f){
		return true; 
	}
	
	public Field GetmyField() {
	//	printOnEntry(this, "GetmyField");
	//	printOnExit(this, "GetmyField", myField+"");
		return myField;		
	}
	
	public void SetmyField(Field f) {
	//	printOnEntry(this, "SetmyField", f+"");
		myField = f;
	//	printOnExit(this, "SetmyField", null);
		
	}
	
	public abstract boolean Kill();
	public abstract void Press();
	public abstract Player GetLastTouchedMe();
	public abstract void Die();
	
}
