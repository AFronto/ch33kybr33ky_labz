package killer_sokoban;

import static killer_sokoban.Game.*;

public class Wall extends Box{
	
	/**
	 *Konstruktor
	 */
	public Wall(){
		super();
		printOnConstruct("Wall");
		printOnExitConstuctor("Wall");
	}
	
	/**
	 *Mindig false-t ad vissza, igy nem lehet rajta atmenni es nem lehet eltolni se.
	 *
	 *@return Mindig false.
	 */
	public boolean Control(Player p, Direction d){
		printOnEntry(this,"Control",p+"",d+"");
		boolean canGo = false;
		printOnExit(this,"Control",canGo+"");
		return canGo;
	}
	
	/**
	 * Azt mondja meg hogy be van e szorulva az adott moveable de a fal mindig be van szorulva.
	 *
	 * @return Fixen true, mivel a fal nem tud mozogni.
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
