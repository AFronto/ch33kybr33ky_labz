package killer_sokoban;
import static killer_sokoban.Game.*;

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
		myMoveable.Die();
		this.Remove();
		System.out.println("Hole has killed a Moveable.");
	//	//printOnExit(this, "FieldAction", null);

	}

	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Hole";
	}
}
