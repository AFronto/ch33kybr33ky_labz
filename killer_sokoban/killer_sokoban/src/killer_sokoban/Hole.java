package killer_sokoban;
import static killer_sokoban.Game.*;
import static killer_sokoban.Interpreter.*;

public class Hole extends Field {

	/**
	 *Konstruktor
	 */
	public Hole(){
		super();
	}


	/**
	 *Minden a Hole-ba kerulo Moveable elpusztul.
	 */
	public void FieldAction()
	{	
		moveableDestroyed(myMoveable);
		myMoveable.Die();
		this.Remove();
	}

	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Hole";
	}
}
