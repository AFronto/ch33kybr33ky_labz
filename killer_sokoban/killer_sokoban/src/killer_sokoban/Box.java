package killer_sokoban;

import static killer_sokoban.Game.*;

public class Box extends Moveable{

	private boolean stuck;
	private Player lastTouchedMe;
	
	public Box(){
		super();
		printOnConstruct("Box");
		CountBoxes(1);
		printOnExitConstuctor("Box");
	}
	
	/**
	 * Visszadja ki mozgatta meg a dobozt.
	 */
	public Player GetLastTouchedMe(){
		printOnEntry(this, "GetLastTouchedMe");
		printOnExit(this, "GetLastTouchedMe", lastTouchedMe+"");
		return lastTouchedMe;
	}
	
	public boolean Control(Player p, Direction d){
		printOnEntry(this,"Control",p+"",d+"");
		lastTouchedMe=p;
		myField = new Field();
		myField.Register(this);
		Field f2 = myField.GetNeighbour(d); 	///egy new Field() nek hivom meg a GetNeighboue() fuggvenyet, 
													///mert nincs a playernek beallitva
		boolean canGo = f2.Step(p,d);				///Playert adom tovabb aki hivta a controlt
		if(canGo==false){
			boolean dead=f2.GetmyMoveable().Kill();
			if(dead){
				canGo=f2.Step(p, d); 			
			}
		}
		if(canGo){
			myField.Remove();
			f2.Register(this);
			myField=f2;
			myField.FieldAction();
		}
		printOnExit(this,"Control","canGo");
		return canGo;  
	}
	
	/**
	 * Visszaadja, hogy a doboz be van-e szorulva.
	 */
	public boolean IsStuck(){
		
		printOnEntry(this, "IsStuck");
		
		printOption("1. igen");
		printOption("2. nem");
		
		int sel = printQuestion("Be van szorulva a Box?",1,2);
		switch (sel) {
		
		case 1: stuck = true;
		case 2: stuck = false;
		
		}
		
		printOnExit(this, "IsStuck",stuck+"");
		return stuck;
	}
	
	/**
	 * Beallitja, hogy be van-e szorulva a doboz.
	 */
	public void SetStuck(){
		printOnEntry(this, "SetStuck");
		
		printOption("1. Igen");
		printOption("2. Nem");
		
		int sel = printQuestion("Legyen beszorulva a doboz?",1,2);
		switch (sel) {
		case 1: stuck = true;
		case 2: stuck = false;
		}
		
		printOnExit(this,"SetStuck",null);
		
	}
	
	/**
	 * Megoli a jatekost. (?)
	 */
	public boolean Kill(){
		printOnEntry(this, "Kill");
		printOnExit(this, "Kill", false+"");
		return false;
	}
	
	/**
	 * Doboz megnyomja a gombot.
	 */
	public void Press(){
		
		printOnEntry(this, "Press");
		myField.Activate(true);
		printOnExit(this, "Press", null);
		
	}
	
	/**
	 * Eltunteti a dobozt (?)
	 */
	public void Die(){
		printOnEntry(this, "Die");
		printOnExit(this, "Die", null);
		
	}
//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Box";
	}
	
}
