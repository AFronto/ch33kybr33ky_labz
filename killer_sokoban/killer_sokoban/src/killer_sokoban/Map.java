package killer_sokoban;

import static killer_sokoban.Game.*;


public class Map {
	
	private Field[][] fields;
	
	public Map(){
		printOnConstruct("Map");
		fields=new Field[20][20];				///ez a palya csak teszt
		printOnExitConstuctor("Map");
	}
/**
 * Generalja a palyat.
 * @param playerCount a jatekosok szama
 */
	public void CreateMap(int playerCount){
	
		printOnEntry(this,"CreateMap",playerCount+"");
		String[] newFieldStrings = {"1. Field",
		"2. Hole", "3. TrapDoor","4. Button","5. Target","6. Vegeztem az epitessel"};


		boolean buildingMap = true;
		
		while(buildingMap){
			for (String s : newFieldStrings){
				printOption(s);
			}
			int sel = printQuestion("Milyen mezot akarsz rakni?", 1, 6);
			switch (sel) {
				case 1:
					Field f= new Field();
					f.SetNeighbour(Direction.UP,f);
					f.SetNeighbour(Direction.DOWN,f);
					f.SetNeighbour(Direction.LEFT,f);
					f.SetNeighbour(Direction.RIGHT,f);
					MovableOption(f);
					////f.GetNeighbour(Direction.UP);    ////teszt
					break;
				case 2:
					Hole h= new Hole();
					h.SetNeighbour(Direction.UP,h);
					h.SetNeighbour(Direction.DOWN,h);
					h.SetNeighbour(Direction.LEFT,h);
					h.SetNeighbour(Direction.RIGHT,h);
					break;
				case 3:
					TrapDoor t= new TrapDoor();
					t.SetNeighbour(Direction.UP,t);
					t.SetNeighbour(Direction.DOWN,t);
					t.SetNeighbour(Direction.LEFT,t);
					t.SetNeighbour(Direction.RIGHT,t);
					MovableOption(t);
					break;
				case 4:
					Button b= new Button();
					b.SetNeighbour(Direction.UP,b);
					b.SetNeighbour(Direction.DOWN,b);
					b.SetNeighbour(Direction.LEFT,b);
					b.SetNeighbour(Direction.RIGHT,b);
					MovableOption(b);
					break;
				case 5:
					Target ta= new Target();
					ta.SetNeighbour(Direction.UP,ta);
					ta.SetNeighbour(Direction.DOWN,ta);
					ta.SetNeighbour(Direction.LEFT,ta);
					ta.SetNeighbour(Direction.RIGHT,ta);
					MovableOption(ta);
					break;
				case 6:
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
				f.Register(p);
				break;
			case 2:
				Box b = new Box();
				f.Register(b);
				break;
			case 3:
				Wall w = new Wall();
				f.Register(w);
				break;
			case 4:
				f.Register(null);
				break;

		}
	}
}

