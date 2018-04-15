package killer_sokoban;

import java.util.ArrayList;
import java.util.Arrays;

public class Interpreter {
	// parancs
	ArrayList<String> parts;

	// letrehozott fieldek es nevuk
	ArrayList<Field> fields = new ArrayList<Field>();
	ArrayList<String> fNames = new ArrayList<String>();

	// letrehozott moveableek es nevuk
	ArrayList<Moveable> moveables = new ArrayList<Moveable>();
	ArrayList<String> mNames = new ArrayList<String>();

	Interpreter(String s) {
		//szokozoknel eldarabolja a kapott stringet
		parts = new ArrayList<String>(Arrays.asList(s.split(" ")));
		
		//ha tobb eleme van mint 5 akkor kuka, amugy eldontjuk mit akarunk vele kezdeni
		if (parts.size() > 5) {
			System.out.println("Túl sok paraméter");
		} else {
			Decide(parts);
		}
	}
	
	//megadott nev alapjan visszaadja az indexet
	int getFieldIndex(String s) {
		for (int i = 0; i < fNames.size(); i++) {
			if(fNames.get(i) == s) {
				return i;
			}
		}
		return -1;
	}
	
	//megadott nev alapjan visszaadja az indexet
	int getMoveableIndex(String s) {
		for (int i = 0; i < mNames.size(); i++) {
			if(mNames.get(i) == s) {
				return i;
			}
		}
		return -1;
	}

	//a megfelelõ cucliba kerul es ott elvileg az tortenik vele ami kell
	void Decide(ArrayList<String> p) {
		switch (p.get(0)) {
		case "create":
			switch (p.get(1)) {
			case "Map":
				Map map = new Map();
				map.CreateMap(4);

			case "Field":
				fields.add(new Field());
				fNames.add(p.get(2));

			case "Wall":
				moveables.add(new Wall());
				mNames.add(p.get(2));

			case "Hole":
				fields.add(new Hole());
				fNames.add(p.get(2));

			case "Button":
				fields.add(new Button());
				fNames.add(p.get(2));

			case "TrapDoor":
				fields.add(new TrapDoor());
				fNames.add(p.get(2));

			case "Target":
				fields.add(new Target());
				fNames.add(p.get(2));

			case "Player":
				moveables.add(new Player());
				mNames.add(p.get(2));

			case "Box":
				moveables.add(new Box());
				mNames.add(p.get(2));

			default:
				System.out.println("A parancs nem ertelmezheto!");
			}

		case "connect":
			switch (p.get(1)) {
			case "Neighbour":
				int first = getFieldIndex(p.get(2));
				int secound = getFieldIndex(p.get(3));
				fields.get(first).SetNeighbour(Direction.valueOf(p.get(4)), fields.get(secound));
				fields.get(secound).SetNeighbour(Direction.valueOf(p.get(4)), fields.get(first));

			case "Moveable":
				int f = getFieldIndex(p.get(2));
				int m = getMoveableIndex(p.get(3));
				fields.get(f).SetmyMoveable(moveables.get(m));

			case "Button-TrapDoor":
				int b = getFieldIndex(p.get(2));
				int t = getFieldIndex(p.get(3));
				//indul a szopas
			default:
				System.out.println("A parancs nem ertelmezheto!");
			}

		case "choose":

		case "step":

		case "listNeighbour":

		case "putSomeThing":

		case "listBox":

		case "listPlayer":

		default:
			System.out.println("A parancs nem ertelmezheto!");
		}
	}
}
