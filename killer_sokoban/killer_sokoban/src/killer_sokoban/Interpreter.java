package killer_sokoban;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Interpreter {
	/**
	 * parancs
	 */
	ArrayList<String> parts;

	/**
	 * letrehozott fieldek es nevuk
	 */
	HashMap<String, Field> fields = new HashMap<String, Field>();

	/**
	 * letrehozott moveableek es nevuk
	 */
	HashMap<String, Moveable> moveables = new HashMap<String, Moveable>();

	/**
	 * Globalis player
	 */
	Player chosen = new Player(4);
	Interpreter() {	
	}
	
	void Run() {
		/**
		 * enterrel elvalasztva a parancsokat nezik
		 */
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()) {
			String command = sc.nextLine();
			parts = new ArrayList<String>(Arrays.asList(command.split(" ")));
			
			/**
			 * ha tobb eleme van mint 5 akkor kuka, amugy eldontjuk mit akarunk vele kezdeni
			 */
			if (parts.size() > 5) {
				System.out.println("Tul sok parameter");
			} else {
				Decide(parts);
			}
		}
	}
	
	void Read(File file) throws FileNotFoundException, IOException {
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line = br.readLine();

		    while (line != null) {
		    	parts = new ArrayList<String>(Arrays.asList(line.split(" ")));
		    	Decide(parts);
		    	
		        line = br.readLine();
		    }
		}
	}

	/**
	 * a megfelelo cucliba kerul es ott elvileg az tortenik vele ami kell
	 */
	void Decide(ArrayList<String> p) {
		switch (p.get(0)) {
		case "create":
			switch (p.get(1)) {
			case "Map":
				Map map = new Map();
				map.CreateMap(4,6);
				break;

			case "Field":
				fields.put(p.get(2), new Field());
				break;
				
			case "Wall":
				moveables.put(p.get(2), new Wall());
				break;
				
			case "Hole":
				fields.put(p.get(2), new Hole());
				break;
				
			case "Button":
				fields.put(p.get(2), new Button());
				break;
				
			case "TrapDoor":
				fields.put(p.get(2), new TrapDoor());
				break;
				
			case "Target":
				fields.put(p.get(2), new Target());
				break;
				
			case "Player":
				moveables.put(p.get(2), new Player(4));
				break;
				
			case "Box":
				moveables.put(p.get(2), new Box());
				break;
				
			default:
				System.out.println("A parancs nem ertelmezheto!");
				break;
				
			}
			break;
			
		case "connect":
				switch (p.get(1)) {
				case "Neighbour":
					/**
					 * a parancsban megadott nevu fieldet kiekeresi es elvegzi az osszekapcsolast
					 * ha nincs olyan hibauzenetet dob
					 */
					if(fields.get(p.get(2)) == null || fields.get(p.get(3)) == null) {
						System.out.println("Nincs ilyen nevu mezo!");
					}else {
						fields.get(p.get(2)).SetNeighbour(Direction.valueOf(p.get(4)), fields.get(p.get(3)));
						fields.get(p.get(3)).SetNeighbour(Direction.valueOf(p.get(4)).Opposite(), fields.get(p.get(2)));
					}
					break;
					
				case "Moveable":
					if(fields.get(p.get(2)) == null || moveables.get(p.get(3)) == null) {
						System.out.println("Valamelyik parameter hibas!");
					}else {
						fields.get(p.get(2)).Register(moveables.get(p.get(3)));
					}
					break;
					
				case "Button-TrapDoor":
					if(fields.get(p.get(2)) == null || fields.get(p.get(3)) == null) {
						System.out.println("Valamelyik parameter hibas!");
					}else {
						Button b = (Button) fields.get(p.get(2));
						TrapDoor td = (TrapDoor) fields.get(p.get(3));
						b.SetmyTrap(td);
					}
					break;
					
					
				default:
					System.out.println("A parancs nem ertelmezheto!");
					break;
					
				}
				break;
			
		case "choose":
			if(moveables.get(p.get(1)).toString().equals("Player")){
				chosen = (Player) moveables.get(p.get(1));
				System.out.println("Chosen:"+moveables.get(p.get(1))+" name: "+p.get(1));
			}else{
				System.out.println("Hallod ba$tya, csak playert lehet valasztani");
			}
			break;
			
		case "step":
			Field originalPos =	chosen.GetmyField();
			chosen.Control(chosen, Direction.valueOf(p.get(1)), chosen.getStrength());
			if(originalPos.equals(chosen.GetmyField().GetNeighbour(Direction.valueOf(p.get(1)).Opposite()))){
				System.out.println(getMoveableName(chosen)+" has been moved to "+getFieldName(chosen.GetmyField()));
			}
			break;
			
		case "listNeighbour":
			Field f = chosen.GetmyField();
			String toPrint = "null";
			for(Direction d : Direction.values()) {
				for(String key:fields.keySet()){
					if(f.GetNeighbour(d) != null && f.GetNeighbour(d).equals(fields.get(key))){
						toPrint = key;
					}
				}
				System.out.println(d + " : " + f.GetNeighbour(d)+" name: "+toPrint);
				toPrint = "null";
			}
			break;
			
		case "putSomeThing":
			chosen.changeFriction();
			break;
			
		case "listBox":
			
			break;
			
		case "listPlayer":
			break;
			
		default:
			System.out.println("A parancs nem ertelmezheto!");
			break;
			
		}
	}
	
	/*
	 * Visszaadja a Moveable nevet az interpreterben hasznalt a HashMapbol.
	 */
	public String getMoveableName(Moveable m){
		for(String key:moveables.keySet()){
			if(m.equals(moveables.get(key))){
				return key;
			}
		}
		return null;
	}
	/*
	 * Visszaadja a Field nevet az interpreterben hasznalt a HashMapbol.
	 */
	public String getFieldName(Field m){
		for(String key:fields.keySet()){
			if(m.equals(fields.get(key))){
				return key;
			}
		}
		return null;
	}
}
