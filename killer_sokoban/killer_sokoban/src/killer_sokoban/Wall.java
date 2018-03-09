package killer_sokoban;

import static killer_sokoban.Game.*;

public class Wall extends Box{

	public Wall(){
		super();
		printOnEntry(this,"<init>");
		printOnExit(this,"<init>",null);
	}

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

	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Wall";
	}
}
