package killer_sokoban;

import static killer_sokoban.Game.*;

public class Wall extends Box{
	
	//Konstruktor
	public Wall(){
		super();
		printOnConstruct("Wall");
		printOnExitConstuctor("Wall");
	}
	
	//Mindig false-t ad vissza, így nem lehet rajta átmenni
	public boolean Control(Player p, Direction d){
		printOnEntry(this,"Control",p+"",d+"");
		boolean canGo = false;
		printOnExit(this,"Control",canGo+"");
		return canGo;
	}
	
	/**
	 * Fixen true, mivel a fal nem tud mozogni.
	 */
	public boolean IsStuck(){
		printOnEntry(this,"Control");
		printOnExit(this,"Control",null);
		return true;
	}

	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Wall";
	}
}
