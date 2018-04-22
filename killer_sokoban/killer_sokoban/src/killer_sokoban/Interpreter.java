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
	private static boolean failedTest=false;

	private static ArrayList<String> parts;
	private static ArrayList<Moveable> seq=new ArrayList<Moveable>();
	private static ArrayList<Field> originalPositions=new ArrayList<Field>();

	/**
	 * letrehozott fieldek es nevuk
	 */
	private static HashMap<String, Field> fields = new HashMap<String, Field>();

	/**
	 * letrehozott moveableek es nevuk
	 */
	private static HashMap<String, Moveable> moveables = new HashMap<String, Moveable>();

	/**
	 * Globalis player
	 */
	private static Player chosen = new Player(10);
	Interpreter() {
	}
	
	public static void Run() {
		/**
		 * enterrel elvalasztva a parancsokat nezik
		 */
		System.out.print(">>> ");
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
			System.out.print(">>> ");
		}
	}
	
	public static void Read(File file) throws FileNotFoundException, IOException {
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line = br.readLine();
		    while (line != null) {
		    	parts = new ArrayList<String>(Arrays.asList(line.split(" ")));
		    	Decide(parts);
		        line = br.readLine();
		    }
		    if(!failedTest){
		    	System.out.println("\nSuccessfull test!");
		    }
		}
	}

	/**
	 * a megfelelo cucliba kerul es ott elvileg az tortenik vele ami kell
	 */
	public static void Decide(ArrayList<String> p) {
		switch (p.get(0)) {
		case "create":
			switch (p.get(1)) {
			case "Map":
				Map map = new Map();
				map.CreateMap(4,6);
				break;

			case "Field":
				fields.put(p.get(2), new Field());
				if(getFieldName(fields.get(p.get(2))).equals(p.get(2))){											//Teszt, hogy valoban letre jott-e az objektum
					System.out.println("Field "+getFieldName(fields.get(p.get(2)))+" has been created.");
				}
				break;
				
			case "Wall":
				moveables.put(p.get(2), new Wall());
				if(getMoveableName(moveables.get(p.get(2))).equals(p.get(2))){											//Teszt, hogy valoban letre jott-e az objektum
					System.out.println("Wall "+getMoveableName(moveables.get(p.get(2)))+" has been created.");
				}
				break;
				
			case "Hole":
				fields.put(p.get(2), new Hole());
				if(getFieldName(fields.get(p.get(2))).equals(p.get(2))){											//Teszt, hogy valoban letre jott-e az objektum
					System.out.println("Hole "+getFieldName(fields.get(p.get(2)))+" has been created.");
				}
				break;
				
			case "Button":
				fields.put(p.get(2), new Button());
				if(getFieldName(fields.get(p.get(2))).equals(p.get(2))){											//Teszt, hogy valoban letre jott-e az objektum
					System.out.println("Button "+getFieldName(fields.get(p.get(2)))+" has been created.");
				}
				break;
				
			case "TrapDoor":
				fields.put(p.get(2), new TrapDoor());
				if(getFieldName(fields.get(p.get(2))).equals(p.get(2))){											//Teszt, hogy valoban letre jott-e az objektum
					System.out.println("TrapDoor "+getFieldName(fields.get(p.get(2)))+" has been created.");
				}
				break;
				
			case "Target":
				fields.put(p.get(2), new Target());
				if(getFieldName(fields.get(p.get(2))).equals(p.get(2))){											//Teszt, hogy valoban letre jott-e az objektum
					System.out.println("Target "+getFieldName(fields.get(p.get(2)))+" has been created.");
				}
				break;
				
			case "Player":
				moveables.put(p.get(2), new Player(5));
				if(getMoveableName(moveables.get(p.get(2))).equals(p.get(2))){											//Teszt, hogy valoban letre jott-e az objektum
					System.out.println("Player "+getMoveableName(moveables.get(p.get(2)))+" has been created.");
				}
				break;
				
			case "Box":
				moveables.put(p.get(2), new Box());
				if(getMoveableName(moveables.get(p.get(2))).equals(p.get(2))){											//Teszt, hogy valoban letre jott-e az objektum
					System.out.println("Box "+getMoveableName(moveables.get(p.get(2)))+" has been created.");
				}
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
					if(fields.get(p.get(2)).GetNeighbour(Direction.valueOf(p.get(4))).equals(fields.get(p.get(3))) && 							//Teszteljuk hogy valoban jo szomszedokat allitottun be
							fields.get(p.get(3)).GetNeighbour(Direction.valueOf(p.get(4)).Opposite()).equals(fields.get(p.get(2)))){			//Oda vissza megnezzuk a viszonyokat
						System.out.println(getFieldName(fields.get(p.get(3)))+" has been connected to the "+Direction.valueOf(p.get(4))+" side of "+getFieldName(fields.get(p.get(2)))+"." );
					}
					break;
					
				case "Moveable":															
					if(fields.get(p.get(2)) == null || moveables.get(p.get(3)) == null) {
						System.out.println("Valamelyik parameter hibas!");
					}else {
						fields.get(p.get(2)).Register(moveables.get(p.get(3)));
						
						if(getMoveableName(fields.get(p.get(2)).GetmyMoveable()).equals(p.get(3))){				//Teszt, hogy valoban ra lett e rakva a mezore
							System.out.println(getMoveableName(fields.get(p.get(2)).GetmyMoveable())+" has been placed to "+getFieldName(fields.get(p.get(2))));
						}
					}
					break;
					
				case "Button-TrapDoor":
					if(fields.get(p.get(2)) == null || fields.get(p.get(3)) == null) {
						System.out.println("Valamelyik parameter hibas!");
					}else {
						Button b = (Button) fields.get(p.get(2));
						TrapDoor td = (TrapDoor) fields.get(p.get(3));
						b.SetmyTrap(td);
						if(b.GetmyTrap().equals(td)){
							System.out.println(getFieldName(fields.get(p.get(2)))+" Button has been connected to "+getFieldName(fields.get(p.get(3))));
						}
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
				if(getMoveableName(chosen).equals(p.get(1))){
					System.out.println(getMoveableName(chosen)+" has been selected.");
				}
			}else{
				System.out.println("Hallod ba$tya, csak playert lehet valasztani");
			}
			break;
			
		case "step":
			try {
				seq.add(chosen);
				Field nextField=chosen.GetmyField().GetNeighbour(Direction.valueOf(p.get(1)));
				while(nextField!=null){
					if(nextField.GetmyMoveable()!=null){
						seq.add(nextField.GetmyMoveable());
					}else{
						break;
					}
					nextField=nextField.GetNeighbour(Direction.valueOf(p.get(1)));
				}

				
				chosen.Control(null, Direction.valueOf(p.get(1)), 0);
			} catch (Exception e) {
				System.out.println("Sikertelen teszt: "+e);
				failedTest=true;
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

		case "exit":
			 System.exit(0);
			break;
		default:
			System.out.println("A parancs nem ertelmezheto!");
			break;
			
		}
	}
	
	/*
	 * Visszaadja a Moveable nevet az interpreterben hasznalt a HashMapbol.
	 */
	public static String getMoveableName(Moveable m){
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
	public static String getFieldName(Field m){
		for(String key:fields.keySet()){
			if(m.equals(fields.get(key))){
				return key;
			}
		}
		return null;
	}

	public static void SequenceCheck(Moveable m) throws Exception {
		if(seq.size()>0){
			Moveable act=seq.remove(0);
			if(!act.equals(m)){
				throw new Exception("Hibas sorrendben hivodnak az elemek!");
			}
		}else{
			throw new Exception("Hibas sorrendben hivodnak az elemek!");
		}
	}

	public static void PushPos(Field originalPos, Direction d){
		System.out.println("Moving "+getMoveableName(originalPos.GetmyMoveable())+" "+d);
		originalPositions.add(originalPos);
	}

	public static void CheckPos(Moveable m, Direction d){
		Field oP=originalPositions.remove(originalPositions.size()-1);
		if(oP.equals(m.GetmyField().GetNeighbour(d.Opposite()))){         										//Megnezem hogy valoban megtortent-e a lepes.
			System.out.println(getMoveableName(m)+" has been moved to "+getFieldName(m.GetmyField())+".");																													//mozgatassal ellenkezo iranybeli szomszeddal akkor sikeres
		}else if(oP.equals(m.GetmyField())){																	//Ha az eredeti pozicio megegyezik az uj pozicio
			System.out.println(getMoveableName(m)+" can not be moved to "+getFieldName(m.GetmyField().GetNeighbour(d))+".");
		}
	}

	public static void moveableDestroyed(Moveable p){
		System.out.println(getMoveableName(p)+" has been destroyed");
	}
}
