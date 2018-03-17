package killer_sokoban;
import static killer_sokoban.Game.*;

public class Hole extends Field {

	public Hole(){
		super();
		printOnConstruct("Hole");
		printOnExitConstuctor("Hole");
	}

	public void FieldAction()
	{
		printOnEntry(this, "FieldAction");
		myMoveable.Die();
		this.Remove();
		printOnExit(this, "FieldAction", null);

	}

	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Hole";
	}
}
