package killer_sokoban;
import static killer_sokoban.Game.*;

public class TrapDoor extends Hole{

	private boolean isActive;
	
	public TrapDoor(){
		super();
		printOnConstruct("TrapDoor");
		printOnExitConstuctor("TrapDoor");
	}

	public void SetActive(boolean b){
		isActive = b;
	}
	
	public void FieldAction(){
		
	}

	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "TrapDoor";
	}
}
