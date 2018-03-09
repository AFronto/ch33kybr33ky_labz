package killer_sokoban;
import static killer_sokoban.Game.*;

public class Field {
	
	protected Field[] neighbours;
	protected Moveable myMoveable;

	public Field(){
	}
	
	public Field GetNeighbour(Direction d){
		printOnEntry(this,"GetNeighbour");
		String[] getNeighbourStrings = {"1. Van szomszed",
		"2. Nincs szomszed"};

		for (String s : getNeighbourStrings){
		printOption(s);
		}
		
		int sel = printQuestion("Mit akarsz csinalni?", 1, 2);
		switch (sel) {
			case 1:
				printOnExit(this,"GetNeighbour","Field");
				return new Field();
			case 2:
				printOnExit(this,"GetNeighbour",null);
				return null;
		}
		return null;
		
	}
	
	public void SetNeighbour(Direction d, Field f){
		printOnEntry(this,"SetNeighbour");
		printOnExit(this,"SetNeighbour",null);
	}
	
	public Moveable GetmyMoveable(){
		printOnEntry(this,"GetmyMoveable");
		String[] getMovStrings = {"1. Van Moveable",
		"2. Nincs Moveable"};

		for (String s : getMovStrings){
		printOption(s);
		}
		
		int sel = printQuestion("Mit akarsz csinalni?", 1, 2);
		switch (sel) {
			case 1:
				printOnExit(this,"GetmyMoveable","Moveable");
				return myMoveable;
			case 2:
				printOnExit(this,"GetmyMoveable",null);
				return null;
		}
		return null;
	}
	
	public void SetmyMoveable(){
		printOnEntry(this,"SetmyMoveable");
		printOnExit(this,"SetmyMoveable",null);
	}
	///Regi CheckNeighbour
	public boolean Step(Player p, Direction d){
		return true; ///ez a forditas miatt kell csak 
	}
	/// Regi Step
	public void Register(Moveable m){
		
	}
	
	public void  Remove(){
		
	}
	
	public void FieldAction(){
		
	}
	

}
