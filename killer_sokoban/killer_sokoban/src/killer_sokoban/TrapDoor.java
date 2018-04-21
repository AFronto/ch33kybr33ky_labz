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
		if (isActive) {
			FieldAction();
		}
		printOnExit(this, "SetActive", null);
	}
	

	/**
	 * Ha Aktiv akkor ugy viselkedik mint egy hole.
	 * Ha nem akkor olyan mint egy sima Field. 
	 */
	public void FieldAction(){
		printOnEntry(this, "FieldAction");

		if(isActive)
		{
			if(myMoveable == null) {
				System.out.println("Nincs rajtam semmi");
			}else {
				System.out.println("Van rajtam valami");
			}
				myMoveable.Die();
				this.Remove();
		}else {
				super.FieldAction();
		}
		
		printOnExit(this, "FieldAction", null);
		
	}

	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "TrapDoor";
	}
}
