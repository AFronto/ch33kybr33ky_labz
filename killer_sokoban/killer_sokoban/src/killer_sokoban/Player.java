package killer_sokoban;
import static killer_sokoban.Game.*;

public class Player extends Moveable{

	private int score;
	
	public Player(){
		printOnConstruct("Player");
		score=0;
		printOnExitConstuctor("Player");
	}

	public void AddScore(){
		printOnEntry(this,"AddScore");
		
		score++;
		UpdateScore(this);
		
		printOnExit(this,"AddScore",null);
	}
	
	public int GetScore(){
		printOnEntry(this,"GetScore");
		printOnExit(this,"GetScore",score+"");
		return score;
	}
	
	public boolean Control(Player m, Direction d){		///az atvett m Playertol megkerdezhetne a getmyfieldel hogy hol van es ha szomszedos mezon akkor insta false?

		printOnEntry(this,"Control",m+"",d+"");
		myField=new Field();
		myField.Register(this);
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
		printOnEntry(this,"DeadScore");
		
		score = -1;
		UpdateScore(this);
		
		printOnExit(this,"DeadScore",null);
	}
	public boolean Kill(){
		printOnEntry(this, "Kill");
		Die();
		myField.Remove();
		printOnExit(this, "Kill", true+"");
		return true;
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
