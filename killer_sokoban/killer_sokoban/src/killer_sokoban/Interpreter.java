package killer_sokoban;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
	Interpreter(String s) {
		/**
		 * szokozoknel eldarabolja a kapott stringet
		 */
		parts = new ArrayList<String>(Arrays.asList(s.split(" ")));
		
		/**
		 * ha tobb eleme van mint 5 akkor kuka, amugy eldontjuk mit akarunk vele kezdeni
		 */
		if (parts.size() > 5) {
			System.out.println("Tul sok parameter");
		} else {
			Decide(parts);
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
				map.CreateMap(4);

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

		case "connect":
			switch (p.get(1)) {
			case "Neighbour":
				/**
				 * a parancsban megadott nev� fieldet kiekeresi �s elv�gzi az �sszekapcsol�st
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
					fields.get(p.get(2)).SetmyMoveable(moveables.get(p.get(3)));
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
				
				/**
				 * indul a szopas
				 */
			default:
				System.out.println("A parancs nem ertelmezheto!");
				break;
				
			}

		case "choose":
			chosen = (Player) moveables.get(p.get(1));
			break;
			
		case "step":
			chosen.Control(null, Direction.valueOf(p.get(3)), chosen.getStrength());
			break;
			
		case "listNeighbour":
			Field f = chosen.GetmyField();
			for(Direction d : Direction.values()) {
				System.out.println(d + " : " + f.GetNeighbour(d));
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
}
