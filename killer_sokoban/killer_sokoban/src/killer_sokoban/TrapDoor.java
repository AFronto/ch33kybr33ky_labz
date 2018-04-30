package killer_sokoban;
import static killer_sokoban.Game.*;
 

public class TrapDoor extends Hole{

	private boolean isActive;
	private String OpenImage;
	private String ClosedImage;
	/**
	 * Konstruktor. Meghivja az ose konstruktorat.
	 */
	public TrapDoor(){
		super();	
		//Betoltendo kep utvonala
		OpenImage="trapdoor_open";
		ClosedImage="trapdoor_close";
		image=ClosedImage;	
	}
	
	/**
	 * Beallitja hogy az adott TrapDoor aktiv allapotu legyen vagy se.
	 *
	 * @param b isActive valtozo - azaz a TrapDoor aktivitasa - erre valtozik.
	 */
	public void SetActive(boolean b){		
		isActive = b;
		if (isActive) {
			image=OpenImage;
			FieldAction();
		}else{
			image=ClosedImage;
		}		
	}
	

	/**
	 * Ha az iActive true allapotban van, akkor ugy fut le, mintha egy Hole hivna,
	 * Ha nem akkor ugy, mintha egy sima Field hivna.
	 */
	public void FieldAction(){		
		if(isActive && myMoveable != null)
		{
			myMoveable.Die();
			this.Remove();
		}		
	}
	
	@Override
	public String toString(){
		return "TrapDoor";
	}
}
