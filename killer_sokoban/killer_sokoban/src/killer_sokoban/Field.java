package killer_sokoban;
import static killer_sokoban.Game.*;

import java.util.EnumMap;

public class Field {
	
	protected EnumMap<Direction, Field> neighbours; 				///iranyokkal allíithato tomb
	protected Moveable myMoveable;									///A filden allo Moveable
	protected int friction = 1;										// A mezo surlodasa

	/**
	 *Konstruktor
	 */
	public Field(){
	
		myMoveable=null;
		neighbours= new EnumMap<Direction,Field>(Direction.class);
		
	
	}

	/**
	 * Ez a fuggveny visszaadja a k?t iranyban levo szomszedot
	 *
	 * @param d Irany
	 * @return A kert szomszed
	 */
	public Field GetNeighbour(Direction d){
		
			
		return neighbours.get(d);
		
	}
	
	/**
	 * Beallit egy szomszedot egy adott iranyba.
	 * @param d - az irany, ahova a szomszedot szeretnenk beallitani.
	 * @param f - a szomszed amit be szeretnenk allitani.
	 */
	public void SetNeighbour(Direction d, Field f){
	
		neighbours.put(d,f);
	
	}
	
	/**
	 * Visszater a Field altal tarolt Moveable-lel.
	 */
	public Moveable GetmyMoveable(){
		
		return myMoveable;
	}
	
	/**
	 * Beallitja a Field surlodasat a tarolt ertek szerint.
	 */
	public void SetFriction(){
		int oilFriction = 0;		//kulonbozo anyagokhoz kulonbozo ertekek
		int honeyFriction = 2;
		int baseFriction = 1;
		
		switch(friction){
		case 1: 
			friction = oilFriction;
			break;
		case 0:
			friction = honeyFriction;
			break;
		case 2:
			friction = baseFriction;
			break;
		}
	}
	
	/**
	 * Visszater a Field surlodasaval.
	 */
	public int GetFriction(){
		return friction;
	}
	
	/**
	 * Ez a fuggveny indtja a lepes folyamatat
	 *
	 * @param p Az utolso player aki tolt
	 * @param d Irany
	 * @return Rekurzivan megkeresi es visszadja,hogy vegul tudunk e lepni
	 * @throws Exception 
	 */
	public boolean Step(Player p, Direction d,int f) throws Exception{
		//printOnEntry(this,"Step",p+"",d+"");
		boolean canGo=false;
		


		if(myMoveable != null){
			if(f == 0){		
				throw new Exception("Elfogyott az ero");
				//System.out.println("Elfogyott az ero");  // Elfogyott az ero, nem tudjuk eltolni ami itt van.
				//return false;
				
			}
			//System.out.println("Current force: "+f);
			canGo = myMoveable.Control(p, d, f-friction);  // Csokkentjuk az erot, a foldon levo anyagnak megfeleloen.
		}else{
			return true;		//Ures a mezo, lehet jonni.
		}
		

	//	//printOnExit(this,"Step",canGo+"");
		return canGo;  
	}
	
	/**
	 * Az adott Movablet bejegyzi a fieldre
	 *
	 * @param m A bejegyzendo Movable
	 */
	public void Register(Moveable m){
		////printOnEntry(this,"Register",m+"");
		myMoveable = m;
		if(m!=null){
			m.SetmyField(this);
		}
		////printOnExit(this,"Register",null);
	}

	/**
	 * Eltavolitja a Movablet a mezorol
	 */
	public void  Remove(){  	        
	//	//printOnEntry(this,"Remove"); 	
		myMoveable = null;
	//	//printOnExit(this,"Remove",null);
	}
	

	/**
	 *Az adott fieldre jellemzo akcio
	 */
	public void FieldAction(){
		//printOnEntry(this, "FieldAction");
		EnumMap<Direction, Boolean> stuckDirs=new EnumMap<Direction, Boolean>(Direction.class);
		//System.out.println("Numeber of neighbours:" +neighbours.values().size());
		for(Direction d: neighbours.keySet()){
			//System.out.println("Field Action : "+this.GetNeighbour(d));
			if(this.GetNeighbour(d)!=null){
				if(this.GetNeighbour(d).GetmyMoveable()!=null){
					stuckDirs.put(d,this.GetNeighbour(d).GetmyMoveable().IsStuck());
				}
			}
		}

		if(stuckDirs.get(Direction.UP)!=null&&stuckDirs.get(Direction.LEFT)!=null&&(stuckDirs.get(Direction.UP)&&stuckDirs.get(Direction.LEFT))){
			GetmyMoveable().SetStuck();
		}else if(stuckDirs.get(Direction.UP)!=null&&stuckDirs.get(Direction.RIGHT)!=null&&(stuckDirs.get(Direction.UP)&&stuckDirs.get(Direction.RIGHT))){
			GetmyMoveable().SetStuck();
		}else if(stuckDirs.get(Direction.DOWN)!=null&&stuckDirs.get(Direction.LEFT)!=null&&(stuckDirs.get(Direction.DOWN)&&stuckDirs.get(Direction.LEFT))){
			GetmyMoveable().SetStuck();
		}else if(stuckDirs.get(Direction.DOWN)!=null&&stuckDirs.get(Direction.RIGHT)!=null&&(stuckDirs.get(Direction.DOWN)&&stuckDirs.get(Direction.RIGHT))){
			GetmyMoveable().SetStuck();
		}

		///checkForInvalidShape();
		//printOnExit(this, "FieldAction", null);
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
