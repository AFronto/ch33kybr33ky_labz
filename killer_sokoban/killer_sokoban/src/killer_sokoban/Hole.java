package killer_sokoban;
import static killer_sokoban.Game.*;
import static killer_sokoban.Interpreter.*;

public class Hole extends Field {

	/**
	 *Konstruktor
	 */
	public Hole(){
		super();
	//	//printOnConstruct("Hole");
	//	//printOnExitConstuctor("Hole");
	}


	/**
	 *Minden a Hole-ba kerulo Moveable elpusztul.
	 */
	public void FieldAction()
	{
	//	//printOnEntry(this, "FieldAction");
		moveableDestroyed(myMoveable);
		myMoveable.Die();
		this.Remove();
	//	//printOnExit(this, "FieldAction", null);

	}

	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Hole";
	}
}
