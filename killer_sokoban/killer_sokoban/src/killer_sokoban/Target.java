package killer_sokoban;
import static killer_sokoban.Game.*;

public class Target extends Field{
	
	/**
	 *Konstruktor
	 */
	public Target(){
		super();
		printOnConstruct("Target");
		myMoveable = null;
		printOnExitConstuctor("Target");
	}

	/**
	 * Ha dobozt tolnak ra le kerdezi ki tolta ad neki egy pontot es a dobozt eltunteti 
	 */
	public void FieldAction(){
		printOnEntry(this,"FieldAction");
		Player p = myMoveable.GetLastTouchedMe();
		if (p != null){
			myMoveable.Die();
			Remove();
			p.AddScore();
			System.out.println("Player scored!");
		}
		printOnExit(this,"FieldAction",null);

	}
	public boolean Step(Player p, Direction d,int f){
		return true;
	}


	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Target";
	}
}
