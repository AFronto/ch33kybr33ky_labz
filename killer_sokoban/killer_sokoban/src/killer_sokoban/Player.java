package killer_sokoban;
import static killer_sokoban.Game.*;

public class Player extends Moveable{

	private int score;
	
	public Player(){
		printOnEntry(this,"<init>");
		printOnExit(this,"<init>",null);
	}

	public void AddScore(){
		
	}
	
	public int GetScore(){
		return score;
	}
	
	public boolean Control(Player m, Direction d){
		printOnEntry(this,"Control");
		printOnExit(this,"Control","canGo");
		return true; ///ez a forditas miatt kell csak 
	}
	
	public void DeadScore(){
		
	}
	public void Kill(){
		
	}
	public void Press(){
		
	}
	/**
	 * null-t add vissza, mert csak a boxoknal szamit, hogy ki erintette meg utoljara.
	 */
	public Player GetLastTouchedMe(){
		return null;
	}
	public void Die(){
		
	}

	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Player";
	}
}
