package killer_sokoban;

import static killer_sokoban.Game.printOnEntry;
import static killer_sokoban.Game.printOnExit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.datatype.DatatypeConstants.Field;

public class Game {

	private static int boxes;
	private static int players;	
	private int[] overallScore;
	private Map active_map;
	private int maxStrength;
	
	/**
	 * A legerosebb jatekos toloerejevel ter vissza.
	 * @return A maximalis toloero a jatek jelenlegi allasaban.
	 */
	public int GetMaxStrength() { return maxStrength; }
	
	/**
	 * Beallitja a tarolt maximalis toloerot.
	 * @param newMaxStrength - az uj maximalis toloero.
	 */
	public void SetMaxStrength(int newMaxStrength) { maxStrength = newMaxStrength; }
	
	/**
	 *Csak a Skeletonhoz hasznalt valtozok
	 */
	private static Scanner sc = new Scanner(System.in);
	private static int tabs = 0;
	private static Game game;
	
	/**
	 *Konstruktor
	 */
	public Game() {		
		active_map= new Map();		
	}
	
	/**
	 *Program main-je
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		game = new Game();
		Interpreter i = new Interpreter();
		/*boolean keepGoing=true;
		while(keepGoing){
			keepGoing = game.Run();
		}*/
		while (true) {
			if(0 < args.length) {
				File inFile = new File(args[0]);
				i.Read(inFile);
				break;
			}else {
				i.Run();
			}
		}
	}
	
	/**
	 *Uj jatek
	 *
	 *@param playerCount A jatekosok szama ahanyan jatszani akarnak.
	 */
	public void NewGame(int playerCount){		
		active_map.CreateMap(playerCount,6);		
	}
	
	/**
	 *Jatek vege
	 */
	public static void EndGame(){
		
	}
	
	/**
	 *Kiveszi a Player-t ha -1 a pontja van, figyel a jatek vegere is
	 *Es ez felel a pontok osszesiteseert es meg jeleniteseert is.
	 *
	 *@param p A jatekos akinek epp updateli a pontjait
	 */
	public static void UpdateScore(Player p){
	
		
		int score = p.GetScore();
		if(score == -1)
			players--;
		
		if(players <= 1)
			EndGame();
		
	
	}
	
	/**
	 *Boxokat tartja szamon
	 *@param add - Amennyivel novelni kell a szamontartott dobozok szamat.
	 */
	public static void CountBoxes(int add){
	
		boxes+=add;
		if(boxes == 0)
			EndGame();
	
	}
	
	/**
	 * Beallitja a Mapon levo Boxok szamat.
	 * @param i - Boxok szama
	 */
	public static void setBoxNum(int i){
		boxes=i;
	}
	

	/**
	 *A beindentalast tartja szamon es vegzi el.
	 */
	public static void printIndent() {
		for (int i = 0; i < tabs; ++i)
			System.out.print("    ");
	}

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
	 * Ezzel a fuggvennyel jelezzuk az adott konstruktor elejen a konstruktor hivast 
	 *
	 * @param method a hivott fuggveny tulajdonosa,neve es a utanna sorban a parameterei ha vannak 
	 */
	public static void printOnConstruct(String... method) {
		System.out.print("create    ");
		tabs++;
		printIndent();

		String methodEnter = "-->";
		methodEnter += method[0] + "(";
		if (method.length != 1) {
			int i;
			for (i = 1; i < method.length - 1; i++)
				methodEnter += method[i] + ", ";
			methodEnter += method[i];
		}
		methodEnter += "):";


		System.out.println(methodEnter);    //-->Constructor(params...):
	}

	/**
	 * Ezzel a fuggvennyel jelezzuk az adott metodus vegen, hogy a metodus viszater 
	 *
	 * @param returningObject az objektum, amibol meg lett hivva a metodus
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
	 * Ezzel a fuggvennyel jelezzuk az adott konstruktor vegen, hogy a konstruktor viszater 
	 *
	 * @param method a hivott fuggveny neve
	 */
	public static void printOnExitConstuctor(String method) {
		System.out.print("return    ");
		printIndent();

		
		String methodExit = "<--";
		methodExit += method + "(" + ")";
		System.out.println(methodExit); ///<--Constructor()
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
	 * @return stringkent a megadott valasz
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


	/**
	 * A Futo teszt fo menuje van itt.
	 *
	 * @return Igaz hamisat ad vissza ami arra valasz hogy akarjuk e meg folytatni a tesztelest.
	 */
	public boolean Run() { 
				
		Map newMap= new Map();
		newMap.CreateMap(4,6);
		return false;
	}

}

