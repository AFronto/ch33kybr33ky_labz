package killer_sokoban;
import static killer_sokoban.Game.*;
import static killer_sokoban.Interpreter.*;

public class TrapDoor extends Hole{

	private boolean isActive;
	
	/**
	 * Konstruktor. Meghivja az ose konstruktorat.
	 */
	public TrapDoor(){
		super();		
	}
	
	/**
	 * Beallitja hogy az adott TrapDoor aktiv allapotu legyen vagy se.
	 *
	 * @param b isActive valtozo - azaz a TrapDoor aktivitasa - erre valtozik.
	 */
	public void SetActive(boolean b){		
		isActive = b;
		if (isActive) {
			FieldAction();
		}		
	}
	

	/**
	 * Ha az iActive true allapotban van, akkor ugy fut le, mintha egy Hole hivna,
	 * Ha nem akkor ugy, mintha egy sima Field hivna.
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
