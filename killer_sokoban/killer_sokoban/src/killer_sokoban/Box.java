package killer_sokoban;

import static killer_sokoban.Game.*;

public class Box extends Moveable{

	private boolean stuck;
	private Player lastTouchedMe;
	
	public Box(){
		printOnConstruct("Box");
		CountBoxes(1);
		printOnExitConstuctor("Box");
	}
	
	/**
	 * Visszadja ki mozgatta meg a dobozt.
	 */
	public Player GetLastTouchedMe(){
		printOnEntry(this, "GetLastTouchedMe");
		printOnExit(this, "GetLastTouchedMe", lastTouchedMe+"");
		return lastTouchedMe;
	}
	
	public boolean Control(Player p, Direction d){
		return true; ///ez a forditas miatt kell csak 
	}
	
	/**
	 * Visszaadja, hogy a doboz be van-e szorulva.
	 */
	public boolean IsStuck(){
		printOnEntry(this, "IsStuck");
		printOnExit(this, "IsStuck",stuck+"");
		return stuck;
	}
	
	/**
	 * Beallitja, hogy be van-e szorulva a doboz.
	 */
	public void SetStuck(){
		printOnEntry(this, "SetStuck");
		printOnExit(this,"SetStuck",null);
		
	}
	
	/**
	 * Megoli a jatekost. (?)
	 */
	public void Kill(){
		printOnEntry(this, "Kill");
		printOnExit(this, "Kill", null);
	}
	
	/**
	 * Doboz megnyomja a gombot.
	 */
	public void Press(){
		
		printOnEntry(this, "Press");
		myField.Activate(true);
		printOnExit(this, "Press", null);
		
	}
	
	/**
	 * Eltunteti a dobozt (?)
	 */
	public void Die(){
		printOnEntry(this, "Die");
		printOnExit(this, "Die", null);
		
	}
//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Box";
	}
	
}
