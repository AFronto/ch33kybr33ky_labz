package killer_sokoban;
import static killer_sokoban.Game.*;

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
		myMoveable.Die();
		this.Remove();
		System.out.println("Hole has killed a Moveable.");
	}

	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Hole";
	}
}
