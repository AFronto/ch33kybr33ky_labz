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
//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Box";
	}
	
}
