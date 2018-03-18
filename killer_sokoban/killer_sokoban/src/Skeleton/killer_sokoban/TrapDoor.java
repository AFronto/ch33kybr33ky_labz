package killer_sokoban;
import static killer_sokoban.Game.*;

public class TrapDoor extends Hole{

	private boolean isActive;
	
	public TrapDoor(){
		super();
		printOnConstruct("TrapDoor");
		printOnExitConstuctor("TrapDoor");
	}
	
	/**
	 * Beallitja hogy aktiv-e a trapdoor.
	 *
	 * @param b isActive valtozo erteke erre allitodik.
	 */
	public void SetActive(boolean b){
		printOnEntry(this, "SetActive", b+"");
		isActive = b;
		printOnExit(this, "SetActive", null);
	}
	

	/**
	 * Ha Aktiv akkor ugy viselkedik mint egy hole.
	 * Ha nem akkor olyan mint egy sima Field. 
	 */
	public void FieldAction(){
		printOnEntry(this, "FieldAction");
		
		printOption("1. Igen");
		printOption("2. Nem");
		int sel = printQuestion("Aktiv a TrapDoor?",1,2);
		switch (sel)
		{
			case 1:
				myMoveable.Die();
				this.Remove();
				break;
			case 2:
				//Field.FieldAction() jon majd ide
				break;
		}
		
		printOnExit(this, "FieldAction", null);
		
	}

	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "TrapDoor";
	}
}
