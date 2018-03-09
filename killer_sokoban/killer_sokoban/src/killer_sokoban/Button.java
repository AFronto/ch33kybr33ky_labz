package killer_sokoban;

import static killer_sokoban.Game.*;

public class Button extends Field{
	
	protected TrapDoor myTrap;

	public Button(){
		super();
		printOnConstruct("Button");
		printOnExitConstuctor("Button");
	}
	
	public void Activate(boolean b){
		
	}
	
	public void FieldAction(){
		
	}

	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Button";
	}
}
