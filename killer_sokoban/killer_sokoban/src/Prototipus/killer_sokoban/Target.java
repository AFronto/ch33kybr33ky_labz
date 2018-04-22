package killer_sokoban;
import static killer_sokoban.Game.*;

public class Target extends Field{
	
	/**
	 *Konstruktor, meghivja az os konstruktorat, es a rajta levo Moveable-t null-ra allitja.
	 */
	public Target(){
		super();		
		myMoveable = null;		
	}

	/**
	 * Ha dobozt tolnak ra lekerdezi melyik jatekos inditott meg az adott mozgast, annak jovair egy pontot, majd az adott dobozt
	 * ,,megoli''.
	 */
	public void FieldAction(){		
		Player p = myMoveable.GetLastTouchedMe();
		if (p != null){
			myMoveable.Die();
			Remove();
			p.AddScore();
			System.out.println("Player scored!");
		}
	}

	@Override
	public String toString(){
		return "Target";
	}
}
