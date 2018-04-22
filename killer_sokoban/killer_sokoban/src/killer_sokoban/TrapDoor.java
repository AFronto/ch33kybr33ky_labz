package killer_sokoban;
import static killer_sokoban.Game.*;
import static killer_sokoban.Interpreter.*;

public class TrapDoor extends Hole{

	private boolean isActive;
	
	/**
	 * Konstruktor.
	 */
	public TrapDoor(){
		super();		
	}
	
	/**
	 * Beallitja hogy aktiv-e a trapdoor.
	 *
	 * @param b isActive valtozo erteke erre allitodik.
	 */
	public void SetActive(boolean b){		
		isActive = b;
		if (isActive) {
			FieldAction();
		}		
	}
	

	/**
	 * Ha Aktiv akkor ugy viselkedik mint egy Hole.
	 * Ha nem akkor olyan mint egy sima Field. 
	 */
	public void FieldAction(){		
		if(isActive && myMoveable != null)
		{
			moveableDestroyed(myMoveable);
			myMoveable.Die();
			this.Remove();
		}		
	}
	
	@Override
	public String toString(){
		return "TrapDoor";
	}
}
