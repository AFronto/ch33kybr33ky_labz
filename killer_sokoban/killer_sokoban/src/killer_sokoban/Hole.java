package killer_sokoban;
import static killer_sokoban.Game.*;
import static killer_sokoban.Interpreter.*;

public class Hole extends Field {

	/**
	 *Konstruktor, az ose parameternelkuli konstruktorat hivja.
	 */
	public Hole(){
		super();
	}


	/**
	 *Ha van rajta Moveable, akkor megöli, mert minden a Hole-ba kerulo Moveable elpusztul.
	 */
	public void FieldAction()
	{	
		moveableDestroyed(myMoveable);
		myMoveable.Die();
		this.Remove();
	}

	@Override
	public String toString(){
		return "Hole";
	}
}
