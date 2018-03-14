package killer_sokoban;
import static killer_sokoban.Game.*;

public class Player extends Moveable{

	private int score;
	
	public Player(){
		printOnConstruct("Player");
		printOnExitConstuctor("Player");
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
	
	/**
	 * Ez az a fuggveny ami megnyomja a gombot amikor a jatekos ralep.
	 */
	public void Press(){
	printOnEntry(this, "Press");
	//myField nem Button, ezert nem ismeri az Activate() fuggvenyt
	myField.Activate(false);
	printOnExit(this, "Press", null);
		
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
