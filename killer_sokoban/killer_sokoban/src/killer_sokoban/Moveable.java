package killer_sokoban;

public abstract class Moveable {

	protected Field myField;
	
	public boolean IsStuck(){
		return false;
	}
	
	public boolean Control(Player p, Direction d){
		return true; ///ez a forditas miatt kell csak 
	}
	public abstract void Kill();
	public abstract void Press();
	public abstract Player GetLastTouchedMe();
	public abstract void Die();
	
}
