package killer_sokoban;

import static killer_sokoban.Game.*;


public class Map {
	
	private Field[]fields;
	
	public Map(){
		printOnEntry(this,"<init>");
		printOnExit(this,"<init>",null);
	}

	public void CreateMap(int playerCount){
	
		printOnEntry(this,"CreateMap");
		String[] newFieldStrings = {"1. Field",
		"2. Hole", "3. TrapDoor","4. Button"};


		boolean buildingMap = true;
		
		while(buildingMap){
			for (String s : newFieldStrings){
				printOption(s);
			}
			int sel = printQuestion("Mit akarsz csinalni?", 1, 4);
			switch (sel) {
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				case 4:
					buildingMap = false;
					break;
	
			}
			
		}
		printOnExit(this,"CreateMap",null);
	}
	
	//////////////////////////////////////////////////////////////SKELETON FUGGVENYEK/////////////////////////////////////////
	public void MovableOption(){
		String[] movableStrings = {"1. Player","2. Box","3. Ures"};
		
		for (String s : movableStrings){
			printOption(s);
		}
		int sel = printQuestion("Mit akarsz csinalni?", 1, 3);
		switch (sel) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;


		}
	}
}

