package killer_sokoban;

import static killer_sokoban.Game.printOnEntry;
import static killer_sokoban.Game.printOnExit;

import java.util.Scanner;

public class Game {

	private int boxes;
	private int players;
	private int[] overallScore;
	private Map active_map;
	
	/**
	 *Csak a Skeletonhoz hasznalt valtozok
	 */
	private static Scanner sc = new Scanner(System.in);
	private static int tabs = 0;

	public Game() {
		printOnEntry(this,"<init>");
		active_map= new Map();
		printOnExit(this,"<init>",null);
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.Menu();

		boolean keepGoing=true;
		while(keepGoing){
			keepGoing = game.Menu();
		}
	}
	
	public void NewGame(int playerCount){
		printOnEntry(this,"NewGame",""+playerCount);
		active_map.CreateMap();
		printOnExit(this,"NewGame",null);
	}
	
	public static void EndGame(){
		
	}

	public static void UpdateScore(Player p){
		
	}
	
	public static void CountBoxes(int add){
		
	}
	
	public static void printIndent() {
		for (int i = 0; i < tabs; ++i)
			System.out.print("    ");
	}

////////////////////////////////////////////////Skeleton fuggvenyek///////////////////////////////////////////////////////////////////////

	/**
	 * Ezzel a fuggvennyel jelezzuk az adott metodus elejen a metodus hivast 
	 *
	 * @param callingObject az objektum, aminek a metodusat hivtak
	 * @param method a hivott fuggveny neve es a utanna sorban a parameterei ha vannak 
	 */
	public static void printOnEntry(Object callingObject, String... method) {
		System.out.print("call      ");
		tabs++;
		printIndent();

		String methodEnter = "-->" + callingObject.getClass().getSimpleName();
		methodEnter += "." + method[0] + "(";
		if (method.length != 1) {
			int i;
			for (i = 1; i < method.length - 1; i++)
				methodEnter += method[i] + ", ";
			methodEnter += method[i];
		}
		methodEnter += "):";


		System.out.println(methodEnter);    //-->Class.method(params...):
	}

	/**
	 * Ezzel a fuggvennyel jelezzuk az adott metodus vegen, hogy a metodus viszater 
	 *
	 * @param returningObject az objektum, amibol meg lett hívva a metodus
	 * @param method a hivott fuggveny neve
	 * @param ret a vissza teresi ertek Stringben ha van
	 */
	public static void printOnExit(Object returningObject,String method,String ret) {
		System.out.print("return    ");
		printIndent();

		
		String methodExit = "<--" + returningObject.getClass().getSimpleName();
		methodExit += "." + method + "(" + ")";
		if(ret != null){
			methodExit += "=" + ret;
		}
		System.out.println(methodExit); ///<--Class.method()=ret
		tabs--;
	}

	/**
	 * Kiir egy opciot
	 * @param opt a ki irando opcio 
	 */
	public static void printOption(String opt) {
		System.out.print("-         ");
		printIndent();
		System.out.println("" + opt);
	}

	/**
	 * Kiir ja a kerdest
	 *
	 * @param msg A felteendo kerdes
	 * @return stringként a megadott valasz
	 */
	public static int printQuestion(String msg,int min, int max) {
		while (true) {
			System.out.print("choice    ");
			printIndent();
			System.out.print(msg + " (" + min + "-" + max +"):");
			if (sc.hasNextInt()) {
				int answer = sc.nextInt();
				if (min <= answer && answer <= max){
					return answer;
				}
			}else{
				sc.next();
			}
		}
	}


	public boolean Menu() { 
		String[] menuStrings = {"1. Uj Jatek",
							"2. Kilepes"};

		for (String s : menuStrings){
			printOption(s);
		}

		int sel = printQuestion("Mit akarsz csinalni?", 1, 2);
		switch (sel) {
			case 1:
				int numP=printQuestion("Hany jatekos legyen?", 1, 4);
				NewGame(numP);
				break;
			case 2:
				return false;
		}

		return true;
	}

}

