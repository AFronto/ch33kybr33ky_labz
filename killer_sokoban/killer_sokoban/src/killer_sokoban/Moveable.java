package killer_sokoban;

public abstract class Moveable {

	protected Field myField;
	
	public boolean IsStuck(){
		
	}
	
	public boolean Control(Player p, Direction d){
		
	}
	public void Kill();
	public void Press();
	public Player GetLastTouchedMe();
	public void Die();
	
}
