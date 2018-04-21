package killer_sokoban;

import static killer_sokoban.Game.*;
import static killer_sokoban.Interpreter.*;

public class Wall extends Box{
	
	/**
	 *Konstruktor
	 */
	public Wall(){
		super();
		//printOnConstruct("Wall");
		//printOnExitConstuctor("Wall");
	}
	
	/**
	 *Mindig false-t ad vissza, igy nem lehet rajta atmenni es nem lehet eltolni se.
	 *
	 *@return Mindig false.
	 */
	public boolean Control(Player p, Direction d, int f)throws Exception{
		printOnEntry(this,"Control",p+"",d+"");
		SequenceCheck(this);
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
		//printOnEntry(this,"IsStuck");
		//printOnExit(this,"IsStuck","true");
		return true;
	}

	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Wall";
	}
}
