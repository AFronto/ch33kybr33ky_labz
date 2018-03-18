package killer_sokoban;
import static killer_sokoban.Game.*;

import java.util.EnumMap;

public class Field {
	
	protected EnumMap<Direction, Field> neighbours; 				///iranyokkal allíithato tomb
	protected Moveable myMoveable;									///A filden allo Moveable


	/**
	 *Konstruktor
	 */
	public Field(){
		printOnConstruct("Field");
		neighbours= new EnumMap<Direction,Field>(Direction.class);
		printOnExitConstuctor("Field");
	}

	/**
	 * Ez a fuggveny visszaadja a k?t iranyban levo szomszedot
	 *
	 * @param d Irany
	 * @return A kert szomszed
	 */
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
		printOnEntry(this,"GetmyMoveable");								
		printOnExit(this,"GetmyMoveable",myMoveable+"");
		return myMoveable;
	}
	
	/**
	 * Ez a fuggveny indtja a lepes folyamatat
	 *
	 * @param p Az utolso player aki tolt
	 * @param d Irany
	 * @return Rekurzivan megkeresi es visszadja,hogy vegul tudunk e lepni
	 */
	public boolean Step(Player p, Direction d){
		printOnEntry(this,"Step",p+"",d+"");
		boolean canGo=true;

		String[] stepStrings = {"1. Box",
								"2. Player",
								"3. Wall",
								"4. Ures"};

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
	
	/**
	 * Az adott Movablet bejegyzi a fieldre
	 *
	 * @param m A bejegyzendo Movable
	 */
	public void Register(Moveable m){
		printOnEntry(this,"Register",m+"");
		myMoveable = m;
		printOnExit(this,"Register",null);
	}

	/**
	 * Eltavolitja a Movablet a mezorol
	 */
	public void  Remove(){  	        
		printOnEntry(this,"Remove"); 	
		
		printOnExit(this,"Remove",null);
	}
	

	/**
	 *Az adott fieldre jellemzo akcio
	 */
	public void FieldAction(){
		printOnEntry(this, "FieldAction");
		
		printOnExit(this, "FieldAction", null);
	}
	
	/**
	 * Virtualis aktivalo fuggveny, a Buttonban van megvalositva
	 *
	 * @param b Megadja hogy aktivalja-e a Buttont ami majd a TrapDoort.
	 */
	public void Activate(boolean b) {}


	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	@Override
	public String toString(){
		return "Field";
	}
	

}
