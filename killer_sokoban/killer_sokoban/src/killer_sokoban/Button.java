package killer_sokoban;

import static killer_sokoban.Game.*;

public class Button extends Field{
	
	private TrapDoor myTrap;

	public Button(){
		super();
		printOnConstruct("Button");
		printOnExitConstuctor("Button");
	}
	
	public TrapDoor GetmyTrap() {
		printOnEntry(this, "GetmyTrap");
		printOnExit(this, "GetmyTrap", myTrap+"");
		return myTrap;
	}
	
	public void SetmyTrap(TrapDoor t) {
		printOnEntry(this, "SetmyTrap", t+"");
		myTrap = t;
		printOnExit(this, "SetmyTrap", null);
	}
	
	/**
	 * Ez a fuggveny aktivalja a trapdoort.
	 * 
	 * @param b Megadja hogy aktivalja-e a gombot ami majd a trapdoort.
	 */
	public void Activate(boolean b){
		printOnEntry(this, "Activate", b+"");
		
		myTrap.SetActive(b);
		
		printOnExit(this, "Activate", null);		
	}
	
	public void FieldAction(){
		
	}

	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Button";
	}
}
