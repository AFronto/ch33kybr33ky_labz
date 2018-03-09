package killer_sokoban;

public class Box {

	private boolean stuck;
	private Player lastTouchedMe;
	
	
	public Player GetLastTouchedMe(){
		return lastTouchedMe;
	}
	
	public boolean Control(Player p, Direction d){
		return true; ///ez a forditas miatt kell csak 
	}
	
	public boolean IsStuck(){
		return stuck;
	}
	
	public void SetStuck(){
		
	}
	
	public void Kill(){
		
	}
	
	public void Press(){
		
	}
	
	public void Die(){
		
	}
	
}
