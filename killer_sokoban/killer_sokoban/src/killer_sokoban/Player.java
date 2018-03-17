package killer_sokoban;
import static killer_sokoban.Game.*;

public class Player extends Moveable{

	private int score;
	
	public Player(){
		printOnConstruct("Player");
		printOnExitConstuctor("Player");
	}

	public void AddScore(){
		
	}
	
	public int GetScore(){
		return score;
	}
	
	public boolean Control(Player m, Direction d){

		printOnEntry(this,"Control",m+"",d+"");
		myField=new Field();
		Field f2 = myField.GetNeighbour(d);

		boolean canGo = f2.Step(this,d);
		if(canGo){
			myField.Remove();
			f2.Register(this);
			myField=f2;
			f2.FieldAction();
		}										///ha false akkor mindenkepp marad a Player, a tobbit a rekurzio intezi 
		printOnExit(this,"Control",canGo+"");
		return canGo;  
	}
	
	public void DeadScore(){
		
	}
	public void Kill(){
		
	}
	
	/**
	 * Ez az a fuggveny ami megnyomja a gombot amikor a jatekos ralep.
	 */
	public void Press(){
	printOnEntry(this, "Press");
	//myField nem Button, ezert nem ismeri az Activate() fuggvenyt
	myField.Activate(false);
	printOnExit(this, "Press", null);
		
	}
	
	/**
	 * null-t add vissza, mert csak a boxoknal szamit, hogy ki erintette meg utoljara.
	 */
	public Player GetLastTouchedMe(){
		return null;
	}
	public void Die(){
		
	}
	
		

	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Player";
	}
}
