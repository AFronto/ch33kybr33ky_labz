package killer_sokoban;
import static killer_sokoban.Game.*;

public class Player extends Moveable{

	private int score;
	
	public void AddScore(){
		
	}
	
	public int GetScore(){
		return score;
	}
	
	public boolean Control(Player m, Direction d){
		printOnEntry(this,"Control");
		printOnExit(this,"Control","canGo");
	}
	
	public void DeadScore(){
		
	}
	public void Kill(){
		
	}
	public void Press(){
		
	}
	/**
	 * null-t add vissza, mert csak a boxokn�l sz�m�t, hogy ki �rintette meg utolj�ra.
	 */
	public Player GetLastTouchedMe(){
		return null;
	}
	public void Die(){
		
	}
}
