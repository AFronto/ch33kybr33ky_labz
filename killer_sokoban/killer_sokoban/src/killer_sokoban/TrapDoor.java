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
		//printOnEntry(this, "SetActive", b+"");
		isActive = b;
		if (isActive) {
			FieldAction();
		}
		//printOnExit(this, "SetActive", null);
	}
	

	/**
	 * Ha Aktiv akkor ugy viselkedik mint egy hole.
	 * Ha nem akkor olyan mint egy sima Field. 
	 */
	public void FieldAction(){
		//printOnEntry(this, "FieldAction");

		if(isActive && myMoveable != null)
		{
			moveableDestroyed(myMoveable);
			myMoveable.Die();
			this.Remove();
		}
		
		//printOnExit(this, "FieldAction", null);
		
	}

	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "TrapDoor";
	}
}
