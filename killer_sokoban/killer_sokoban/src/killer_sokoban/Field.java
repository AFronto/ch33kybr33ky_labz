package killer_sokoban;

import static killer_sokoban.Game.printOnEntry;
import static killer_sokoban.Game.printOnExit;

public class Field {
	
	protected Field[] neighbours;
	protected Moveable myMoveable;

	public Field(){
		printOnEntry(this,"<init>");
		printOnExit(this,"<init>",null);
	}
	
	public Field GetNeighbour(Direction d){
		return new Field(); ///ez a forditas miatt kell csak 
	}
	
	public void SetNeighbour(Direction d, Field f){
	}
	
	public Moveable GetmyMoveable(){
		return myMoveable;
	}
	
	public void SetmyMoveable(Moveable m){
		
	}
	
	public boolean CheckNeighbour(Player p, Direction d){
		return true; ///ez a forditas miatt kell csak 
	}
	
	public void Step(Moveable m){
		
	}
	
	public void  Remove(){
		
	}
	
	public void FieldAction(){
		
	}
	

}
