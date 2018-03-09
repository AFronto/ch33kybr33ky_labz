package killer_sokoban;

import static killer_sokoban.Game.*;


public class Map {
	
	private Field[][] fields;
	
	public Map(){
		printOnEntry(this,"<init>");
		fields=new Field[20][20];				///ez a palya csak teszt
		printOnExit(this,"<init>",null);
	}

	public void CreateMap(int playerCount){
	
		printOnEntry(this,"CreateMap",playerCount+"");
		String[] newFieldStrings = {"1. Field",
		"2. Hole", "3. TrapDoor","4. Button","5. Vegeztem az epitessel"};


		boolean buildingMap = true;
		
		while(buildingMap){
			for (String s : newFieldStrings){
				printOption(s);
			}
			int sel = printQuestion("Milyen mezot akarsz rakni?", 1, 5);
			switch (sel) {
				case 1:
					Field f= new Field();
					f.SetNeighbour(Direction.UP,f);
					f.SetNeighbour(Direction.DOWN,f);
					f.SetNeighbour(Direction.LEFT,f);
					f.SetNeighbour(Direction.RIGHT,f);
					MovableOption(f);
					f.GetNeighbour(Direction.UP);
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				case 4:

					break;
				case 5:
					buildingMap = false;
					break;
	
			}
			
		}
		printOnExit(this,"CreateMap",null);
	}
	
	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	public void MovableOption(Field f){
		String[] movableStrings = {"1. Player","2. Box","3. Wall","4. Ures"};
		
		for (String s : movableStrings){
			printOption(s);
		}
		int sel = printQuestion("Mi legyen rajta?", 1, 4);
		switch (sel) {
			case 1:
				Player p = new Player();
				f.SetmyMoveable(p);
				break;
			case 2:
				Box b = new Box();
				f.SetmyMoveable(b);
				break;
			case 3:
				Wall w = new Wall();
				f.SetmyMoveable(w);
				break;
			case 4:
				f.SetmyMoveable(null);
				break;

		}
	}
}

