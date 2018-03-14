package killer_sokoban;
import static killer_sokoban.Game.*;

import java.util.EnumMap;

public class Field {
	
	protected EnumMap<Direction, Field> neighbours; 				///iranyokkal allíithato tomb
	protected Moveable myMoveable;

	public Field(){
		printOnConstruct("Field");
		neighbours= new EnumMap<Direction,Field>(Direction.class);
		printOnExitConstuctor("Field");
	}
	
	public Field GetNeighbour(Direction d){
		printOnEntry(this,"GetNeighbour",d+"");
		String[] getNeighbourStrings = {"1. Van szomszed",
		"2. Nincs szomszed"};

		for (String s : getNeighbourStrings){
		printOption(s);
		}
		
		int sel = printQuestion("Van arra szomszed?", 1, 2);
		switch (sel) {
			case 1:
				if(neighbours.get(d).equals(null)){
					neighbours.put(d,this);
				}
				break;
			case 2:
				neighbours.put(d,null);
				break;				
		}
		printOnExit(this,"GetNeighbour",""+neighbours.get(d));
		return neighbours.get(d);
		
	}
	
	public void SetNeighbour(Direction d, Field f){
		printOnEntry(this,"SetNeighbour",d+"",f+"");
		neighbours.put(d,f);
		printOnExit(this,"SetNeighbour",null);
	}
	
	public Moveable GetmyMoveable(){
		printOnEntry(this,"GetmyMoveable");								////valosaghoz kozelibb de ezt akarjuk?
		printOnExit(this,"GetmyMoveable",myMoveable+"");
		return null;
	}
	

	public void SetmyMoveable(Moveable m){
		printOnEntry(this,"SetmyMoveable",m+"");
		myMoveable=m;
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
	
	/**
	 *  Virtualis aktivalo fuggveny, a Buttonban van megvalositva
	 * @param b Megadja hogy aktivalja-e a Buttont ami majd a TrapDoort.
	 */
	public void Activate(boolean b) {
		
	}


	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Field";
	}
	

}
