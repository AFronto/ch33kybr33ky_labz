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
	private static Player chosen = null;
	Interpreter() {
	}
	
	/**
	 * Interpreter inditasa.
	 */
	public static void Run() {
		
		 // enterrel elvalasztva a parancsokat nezik		 
		System.out.print(">>> ");
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()) {
			String command = sc.nextLine();
			parts = new ArrayList<String>(Arrays.asList(command.split(" ")));
			
			
			//ha tobb eleme van mint 5 akkor kuka, amugy eldontjuk mit akarunk vele kezdeni			 
			if (parts.size() > 5) {
				System.out.println("Tul sok parameter");
			} else {
				Decide(parts);
			}
			System.out.print(">>> ");
		}
	}
	
	/**
	 * Script beolvasasa filebol.
	 *
	 * @param file - A file amibol olvasunk.
	 */
	public static void Read(File file) throws FileNotFoundException, IOException {
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line = br.readLine();
		    while (line != null) {													//Amig nincs vege a fajlnak addig megy
		    	parts = new ArrayList<String>(Arrays.asList(line.split(" ")));		//Feldarabolja a parancsot
		    	Decide(parts);
		        line = br.readLine();												//Beolvassa az aktualis sort a fajlbol
		    }
		    if(!failedTest){
		    	System.out.println("\nSuccessfull test!");							//Ha nem kapott exceptiont kozben a tesztet sikeresnek tudja be 
		    }
		}
	}
		  
	/**
	 * Parancsertelmezes.
	 *
	 * @param p - A parancs szavankent szetszedve egy ArrayList<String>-be.
	 */
	public static void Decide(ArrayList<String> p) {
		switch (p.get(0)) {														//Az elso parancs szo switch case-e
		case "create":															//Ha krealunk egy elemet ez akkor kell.	
			switch (p.get(1)) {													//A lehetseges krealt elemek switch case-e
			case "Map":															//A Map krealasa
				Map map = new Map();
				System.out.println("\n----------- Legend -----------");			//Hasznalati utmutato a terkep olvasasahoz
				System.out.println("W: Wall");
				System.out.println("H: Hole");
				System.out.println("D: Trapdoor");
				System.out.println("B: Button");				
				System.out.println("T: Target");
				System.out.println("b: Box");
				System.out.println("p: Player");
				System.out.println("------------------------------\n");
				
				
				
				map.CreateMap(4,6);												//Map gyartas 4 playerrel 6 maximum erovel.
				System.out.println("\nThe Map has been created succesfully.");
				break;

			case "Field":																						//A Field krealasa
				fields.put(p.get(2), new Field());
				if(getFieldName(fields.get(p.get(2))).equals(p.get(2))){											//Teszt, hogy valoban letre jott-e az objektum
					System.out.println("Field "+getFieldName(fields.get(p.get(2)))+" has been created.");
				}else{
					System.out.println("Field has not been created!!!");
				}
				break;
				
			case "Wall":																						//A Wall krealasa
				moveables.put(p.get(2), new Wall());
				if(getMoveableName(moveables.get(p.get(2))).equals(p.get(2))){											//Teszt, hogy valoban letre jott-e az objektum
					System.out.println("Wall "+getMoveableName(moveables.get(p.get(2)))+" has been created.");
				}else{
					System.out.println("Wall has not been created!!!");
				}
				break;
				
			case "Hole":																						//A Hole krealasa
				fields.put(p.get(2), new Hole());
				if(getFieldName(fields.get(p.get(2))).equals(p.get(2))){											//Teszt, hogy valoban letre jott-e az objektum
					System.out.println("Hole "+getFieldName(fields.get(p.get(2)))+" has been created.");
				}else{
					System.out.println("Hole has not been created!!!");
				}
				break;
				
			case "Button":																						//A Button krealasa
				fields.put(p.get(2), new Button());
				if(getFieldName(fields.get(p.get(2))).equals(p.get(2))){											//Teszt, hogy valoban letre jott-e az objektum
					System.out.println("Button "+getFieldName(fields.get(p.get(2)))+" has been created.");
				}else{
					System.out.println("Button has not been created!!!");
				}
				break;
				
			case "TrapDoor":																					//A TrapDoor krealasa
				fields.put(p.get(2), new TrapDoor());
				if(getFieldName(fields.get(p.get(2))).equals(p.get(2))){											//Teszt, hogy valoban letre jott-e az objektum
					System.out.println("TrapDoor "+getFieldName(fields.get(p.get(2)))+" has been created.");
				}else{
					System.out.println("TrapDoor has not been created!!!");
				}
				break;
				
			case "Target":																						//A Target krealasa
				fields.put(p.get(2), new Target());
				if(getFieldName(fields.get(p.get(2))).equals(p.get(2))){											//Teszt, hogy valoban letre jott-e az objektum
					System.out.println("Target "+getFieldName(fields.get(p.get(2)))+" has been created.");
				}else{
					System.out.println("Target has not been created!!!");
				}
				break;
				
			case "Player":																						//A Player krealasa
				moveables.put(p.get(2), new Player(5));
				if(getMoveableName(moveables.get(p.get(2))).equals(p.get(2))){											//Teszt, hogy valoban letre jott-e az objektum
					System.out.println("Player "+getMoveableName(moveables.get(p.get(2)))+" has been created.");
				}else{
					System.out.println("Player has not been created!!!");
				}
				break;
				
			case "Box":																							//A Box krealasa
				moveables.put(p.get(2), new Box());
				if(getMoveableName(moveables.get(p.get(2))).equals(p.get(2))){											//Teszt, hogy valoban letre jott-e az objektum
					System.out.println("Box "+getMoveableName(moveables.get(p.get(2)))+" has been created.");
				}else{
					System.out.println("Box has not been created!!!");
				}
				break;
				
			default:
				System.out.println("Command is undefined!");
				break;
				
			}
			break;
			
		case "connect":																							//Az ossze kapcsolo parancs esete
				switch (p.get(1)) {																				//Szomszedossagi ossze kapcsolas
				case "Neighbour":
					//a parancsban megadott nevu fieldet kikeresi es elvegzi az osszekapcsolast
					//ha nincs olyan hibauzenetet dob
					if(fields.get(p.get(2)) == null || fields.get(p.get(3)) == null) {
						System.out.println("There is no Field with this name!!!");
					}else {
						fields.get(p.get(2)).SetNeighbour(Direction.valueOf(p.get(4)), fields.get(p.get(3)));					//Lekeri a megfelelo mezoket es oda vissza ossze koti oket
						fields.get(p.get(3)).SetNeighbour(Direction.valueOf(p.get(4)).Opposite(), fields.get(p.get(2)));
					}
					if(fields.get(p.get(2)).GetNeighbour(Direction.valueOf(p.get(4))).equals(fields.get(p.get(3))) && 							//Teszteljuk hogy valoban jo szomszedokat allitottun be
							fields.get(p.get(3)).GetNeighbour(Direction.valueOf(p.get(4)).Opposite()).equals(fields.get(p.get(2)))){			//Oda vissza megnezzuk a viszonyokat
						System.out.println(getFieldName(fields.get(p.get(3)))+" has been connected to the "+Direction.valueOf(p.get(4))+" side of "+getFieldName(fields.get(p.get(2)))+"." );
					}else{
						System.out.println("There was a connection error!!!");																	//jelezzuk a hibat
					}
					break;
					
				case "Moveable":																				//Moveable lehelyezesenek esete										
					if(fields.get(p.get(2)) == null || moveables.get(p.get(3)) == null) {
						System.out.println("There is a parameter error!");
					}else {
						fields.get(p.get(2)).Register(moveables.get(p.get(3)));									//amennyiben jok a parameterek regisztralja a megflelo mezon a megfelelo Moveable-t
						
						if(getMoveableName(fields.get(p.get(2)).GetmyMoveable()).equals(p.get(3))){				//Teszt, hogy valoban ra lett e rakva a mezore
							System.out.println(getMoveableName(fields.get(p.get(2)).GetmyMoveable())+" has been placed to "+getFieldName(fields.get(p.get(2))));
						}else{
							System.out.println("There was a connection error!!!");																	//jelezzuk a hibat
						}
					}
					break;
					
				case "Button-TrapDoor":																			//Buttont est TrapDoort ossze kapcsolo eset
					if(fields.get(p.get(2)) == null || fields.get(p.get(3)) == null) {							//Ellenorizzuk hogy leteznek e a megadot nevu elemek
						System.out.println("There is a parameter error!");
					}else {
						Button b = (Button) fields.get(p.get(2));
						TrapDoor td = (TrapDoor) fields.get(p.get(3));
						b.SetmyTrap(td);
						if(b.GetmyTrap().equals(td)){															//Ellenorizzuk hogy tenyleg sikeres e az osszekotes
							System.out.println(getFieldName(fields.get(p.get(2)))+" Button has been connected to "+getFieldName(fields.get(p.get(3))));
						}else{
							System.out.println("There was a connection error!!!");																	//jelezzuk a hibat
						}
					}
					break;
					
					
				default:																						//Amennyiben a parancs nem feldolgozhato akkor ez fut le
					System.out.println("Command is undefined!");
					break;
					
				}
				break;
			
		case "choose":																							//Player kivalasztasanak esete
			if(p.size()>1 && moveables.get(p.get(1))!=null && moveables.get(p.get(1)).toString().equals("Player")){ //Le ellenorzi hogy van e parameter hogy az helyes e es hogy az tenyleg player e
				chosen = (Player) moveables.get(p.get(1));
				if(getMoveableName(chosen).equals(p.get(1))){													//Megnezzuk tenyleg jo playert valasztotttunk e ki.
					System.out.println(getMoveableName(chosen)+" has been selected.");
				}else{
					System.out.println("Selection error!");
				}
			}else{
				chosen=null;
				System.out.println("You can only choose from players and only from existing players!");			//Felhivjuk a felhasznalo figyelmet hogy helytelenul hasznalja a kivalaszto parancsot.
			}
			break;
			
		case "step":
			try {
				if(chosen==null){																				//igy nem lehet rossz a kivalasztott player
					throw new Exception("Fatal Exception: bad choice for player!!!");
				}
				seq.add(chosen);																				//Hogy a Moveablek tenyleg megfelelo sorrendben hivodnak e ahhoz itt allitjuk ossze a referencia tombot
				Field nextField=chosen.GetmyField().GetNeighbour(Direction.valueOf(p.get(1)));
				while(nextField!=null){																			//a choosen utani osszes sorba fuzott moveablet megtalalaja (ez a mezokon valo vegig haladasra figyel)
					if(nextField.GetmyMoveable()!=null){
						seq.add(nextField.GetmyMoveable());														
					}else{
						break;																					//ha meg vannak is fieldek de megszakad a moveable lanc akkor mar eleg ezert megszakitjuk a lanc elmenteset is
					}
					nextField=nextField.GetNeighbour(Direction.valueOf(p.get(1)));								//ez lepteti a kovi mezore az egeszet.
				}

				
				chosen.Control(null, Direction.valueOf(p.get(1)), 0);											//null pointerrel hivom a kivalasztott controlljat ez a lepes kezdemenyezese.
			} catch (Exception e) {
				System.out.println("The test was not succesfull: "+e);
				failedTest=true;
			}
			break;
			
		case "listNeighbour":																					//A szomszedokat kilistazo eset.
			if(chosen!=null){
				Field f = chosen.GetmyField();																	
				String toPrint = "null";
				for(Direction d : Direction.values()) {															//Vegig megy az osszes iranyon es megnezi milyen szomszed van arra null-t mond ha nincs arra szomszed.
					for(String key:fields.keySet()){
						if(f.GetNeighbour(d) != null && f.GetNeighbour(d).equals(fields.get(key))){
							toPrint = key;
						}
					}
					System.out.println(d + " : " + f.GetNeighbour(d)+" name: "+toPrint);						//Kiiratom a szomszedokat a megfelelo iranyokba.
					toPrint = "null";
				}
			}else{
				System.out.println("Bad choice for player!!!");
			}																					
			break;
			
		case "putSomeThing":																					//Az az eset amikor valamit lerakunk egy adott mezore
			if(p.size()>1){																						//Ha keves a parancs hossza 
				Field field = fields.get(p.get(1));
				if (field == null) {
					System.out.println("This is an invalid Field!!!");											//Szol hogy hibas a mezo
				}else {
					field.SetFriction();
					if(getFieldName(field).equals(p.get(1))){
						System.out.println(getFieldName(field)+"'s friction has been changed. Now it has "+nameOfStuff(field.GetFriction())+" on.");		//kiiratoma a Mezot es a ra be allitott anyagot
					}
				}
			}else{
				System.out.println("There is a missing argument");												//Szol hogy nincs argumentum
			}
			break;

		case "exit":																							//A kilepes esete
			 System.exit(0);																									
			break;
		default:
			System.out.println("Command is undefined!");														//Nem megfelelo parancsok eseten fut le
			break;
			
		}
	}
	
	/**
	 * Visszaadja a Moveable nevet az interpreterben hasznalt a HashMapbol.
	 *
	 * @param m - A Moveable aminek a nevet keressuk.
	 * @return - A Moveable neve.
	 */
	public static String getMoveableName(Moveable m){
		for(String key:moveables.keySet()){
			if(m.equals(moveables.get(key))){
				return key;
			}
		}
		return null;
	}
	
	/**
	 * Visszaadja a Field nevet az interpreterben hasznalt a HashMapbol.
	 *
	 * @param m - A Field aminek a nevet keresunk.
	 * @return - A Field neve.
	 */
	public static String getFieldName(Field m){
		for(String key:fields.keySet()){
			if(m.equals(fields.get(key))){
				return key;
			}
		}
		return null;
	}
	
	
	/**
	 * Ellenorzi, hogy megfelelo sorrenben megy-e vegbe a mozgas.
	 *
	 * @param m - A soron kovetkezo Moveable.
	 * @throws Exception.
	 */
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
	
	/**
	 * Kimenti a Moveable poziciojat.
	 *
	 * @param originalPos - jelenlegi pozicioja a Moveable-nek.
	 * @param d - Lepes iranya.
	 */
	public static void PushPos(Field originalPos, Direction d){
		System.out.println("Moving "+getMoveableName(originalPos.GetmyMoveable())+" "+d);
		originalPositions.add(originalPos);
	}
	
	/**
	 * Ellenorzi, hogy egy lepes valoban megtortent-e.
	 *
	 * @param originalPos - jelenlegi pozicioja a Moveable-nek.
	 * @param d - Lepes iranya.
	 */
	public static void CheckPos(Moveable m, Direction d){
		Field oP=originalPositions.remove(originalPositions.size()-1);
		if(oP.equals(m.GetmyField().GetNeighbour(d.Opposite()))){         										//Megnezem hogy valoban megtortent-e a lepes.
			System.out.println(getMoveableName(m)+" has been moved to "+getFieldName(m.GetmyField())+".");																													//mozgatassal ellenkezo iranybeli szomszeddal akkor sikeres
		}else if(oP.equals(m.GetmyField())){																	//Ha az eredeti pozicio megegyezik az uj pozicio
			System.out.println(getMoveableName(m)+" can not be moved to "+getFieldName(m.GetmyField().GetNeighbour(d))+".");
		}
	}
	
	/**
	 * Kiirja a megolt Moveable nevet.
	 *
	 * @param p - A kiirando Moveable.
	 */
	public static void moveableDestroyed(Moveable p){
		System.out.println(getMoveableName(p)+" has been destroyed");
	}


	/**
	 * Vissza adja hogy mi van az adott mezon
	 *
	 * @param friction - A mezo surlodasa ebbol mondja meg hogy mi van raja.
	 * @return Szoveg ami le irja a a mezo kenetet.
	 */
	public static String nameOfStuff(int friction){
		switch(friction){						//egy switch case ami megmondja mi van a 3 lehetseges kozul a mezon.
			case 1: 
				return "Empty";
			case 0:
				return "Oil";
			case 2:
				return "Honey";
		}

		return "Alma";
	}
}
