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

	
		Player p = myMoveable.GetLastTouchedMe();
		

		if (p != null){
			myMoveable.Die();
			Remove();
			p.AddScore();
		}else{

			printOnExit(this,"FieldAction",null);
			return;
		}
		printOnExit(this,"FieldAction",null);

	}

	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Target";
	}
}
