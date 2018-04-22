package killer_sokoban;

import static killer_sokoban.Game.*;
import static killer_sokoban.Interpreter.*;

public class Wall extends Box{
	
	/**
	 *Konstruktor
	 */
	public Wall(){
		super();		
	}
	
	/**
	 *Mindig false-t ad vissza, igy nem lehet rajta atmenni es nem lehet eltolni se.
	 *@param p - A mozgast kezdemenyezo jatekos.
	 *@param d - A mozgas iranya.
	 *@param f - A toloero.
	 *@return Mindig false.
	 */
	public boolean Control(Player p, Direction d, int f)throws Exception{		
		SequenceCheck(this);
		boolean canGo = false;		
		return canGo;
	}
	
	/**
	 * Azt mondja meg hogy be van e szorulva az adott moveable de a fal mindig be van szorulva.
	 *
	 * @return Fixen true, mivel a fal nem tud mozogni.
	 */
	public boolean IsStuck(){		
		return true;
	}
	
	@Override
	public String toString(){
		return "Wall";
	}
}
