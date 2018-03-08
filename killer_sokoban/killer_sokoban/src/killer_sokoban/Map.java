package killer_sokoban;

import static killer_sokoban.Game.printOnEntry;
import static killer_sokoban.Game.printOnExit;

public class Map {
	
	private Field[] fields;
	
	public void CreateMap(){
		
		printOnEntry(this,"CreateMap");
		printOnExit(this,"CreateMap",null);
		}
}
