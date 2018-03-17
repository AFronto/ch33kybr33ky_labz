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
		String[] getNeighbourStrings = {"1. Field",
										"2. Hole",
										"3. Button",
										"4. TrapDoor",
										"5. Target"};

		for (String s : getNeighbourStrings){
		printOption(s);
		}
		
		int sel = printQuestion("Milyen szomszed van arra?", 1, 5);
		switch (sel) {
			case 1:
				neighbours.put(d,new Field());
				break;
			case 2:
				neighbours.put(d,new Hole());
				break;
			case 3:
				neighbours.put(d,new Button());
				break;		
			case 4:
				neighbours.put(d,new TrapDoor());
				break;	
			case 5:
				neighbours.put(d,new Target());
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
		return myMoveable;
	}
	
	///Regi CheckNeighbour
	public boolean Step(Player p, Direction d){
		printOnEntry(this,"Step",p+"",d+"");
		boolean canGo=true;

		String[] stepStrings = {"1. Box",
								"2. Player",
								"3. Wall",
								"4. Üres"};

		for (String s : stepStrings){
		printOption(s);
		}

		int sel = printQuestion("Milyen szomszed van arra?", 1, 4);
		switch (sel) {
			case 1:
				myMoveable=new Box();
				canGo=myMoveable.Control(p,d);
				break;
			case 2:
				myMoveable=new Player();
				canGo=myMoveable.Control(p,d); ///egyelore player eltolja a playert de majd meg erre ranezunk.
				break;
			case 3:
				myMoveable=new Wall();
				canGo=myMoveable.Control(p,d);
				break;		
			case 4:
				canGo=true;
				break;			
		}

		printOnExit(this,"Step",canGo+"");
		return canGo;  
	}
	
	/// Regi Step
	public void Register(Moveable m){
		printOnEntry(this,"Register",m+"");
		myMoveable = m;
		printOnExit(this,"Register",null);
	}
	
	public void  Remove(){  	        /// mindig megoli ami rajta van megha csak arrébb mozgatas is volt 
		printOnEntry(this,"Remove"); 	///jobb lenne a diet igazi halaloknal hivni nem ebben (ez a sequencian is szar) Ezt javítottam is!!!!
		
		printOnExit(this,"Remove",null);
	}
	
	public void FieldAction(){
		printOnEntry(this, "FieldAction");
		
		printOnExit(this, "FieldAction", null);
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
