package killer_sokoban;
import static killer_sokoban.Game.*;
 

public class Hole extends Field {

	/**
	 *Konstruktor, az ose parameternelkuli konstruktorat hivja.
	 */
	public Hole(){
		super();
		//Betoltendo kep utvonala
		image="hole.png";
	}


	/**
	 *Ha van rajta Moveable, akkor megoli, mert minden a Hole-ba kerulo Moveable elpusztul.
	 */
	public void FieldAction()
	{	
		myMoveable.Die();
		this.Remove();
	}

	@Override
	public String toString(){
		return "Hole";
	}
}
