package killer_sokoban;
import static killer_sokoban.Game.*;

public class Target extends Field{
	
	public Target(){
		super();
		printOnConstruct("Target");
		printOnExitConstuctor("Target");
	}

	public void FieldAction(){
		
	}

	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Target";
	}
}
