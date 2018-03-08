package killer_sokoban;

public class Wall extends Box{

	public boolean Control(Player p, Direction d){
		boolean canGo = false;
		return canGo;
	}
	
	/**
	 * Fixen true, mivel a fal nem tud mozogni.
	 */
	public boolean IsStuck(){
		
		return true;
	}
}
