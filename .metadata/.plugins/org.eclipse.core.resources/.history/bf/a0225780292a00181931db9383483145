package killer_sokoban;
import static killer_sokoban.Game.*;

public class Target extends Field{
	
	public Target(){
		super();
		printOnConstruct("Target");
		printOnExitConstuctor("Target");
	}

	public void FieldAction(){
		printOnEntry(this,"FieldAction");
		
		printOnExit(this,"FieldAction");
		
		Player p = myMoveable.GetLastTouchedMe();
		
		if (p != null){
			Remove();
			p.AddScore();
		}else{
			return
		}
	}

	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Target";
	}
}
