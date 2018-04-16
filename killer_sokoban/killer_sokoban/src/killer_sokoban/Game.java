package killer_sokoban;

import static killer_sokoban.Game.printOnEntry;
import static killer_sokoban.Game.printOnExit;

import java.util.Scanner;

public class Game {

	private static int boxes;
	private static int players;	
	private int[] overallScore;
	private Map active_map;
	private int maxStrength;
	
	public int GetMaxStrength() { return maxStrength; }
	
	public int SetMaxStrength(int newMaxStrength) { maxStrength = newMaxStrength; }
	
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
		printOnConstruct("Game");
		active_map= new Map();
		printOnExitConstuctor("Game");
	}
	
	/**
	 *Program main-je
	 */
	public static void main(String[] args) {
		game = new Game();
		Interpreter i = new Interpreter();
		/*boolean keepGoing=true;
		while(keepGoing){
			keepGoing = game.Run();
		}*/
		i.Run();
	}
	
	/**
	 *Uj jatek
	 *
	 *@param playerCount A jatekosok szama ahanyan jatszani akarnak.
	 */
	public void NewGame(int playerCount){
		printOnEntry(this,"NewGame",""+playerCount);
		active_map.CreateMap(playerCount);
		printOnExit(this,"NewGame",null);
	}
	
	/**
	 *Jatek vege
	 */
	public static void EndGame(){
		printOnEntry(game,"EndGame");
		printOnExit(game,"EndGame",null);
	}
	
	/**
	 *Kiveszi a Player-t ha -1 a pontja van, figyel a jatek vegere is
	 *Es ez felel a pontok osszesiteseert es meg jeleniteseert is.
	 *
	 *@param p A jatekos akinek epp updateli a pontjait
	 */
	public static void UpdateScore(Player p){
		printOnEntry(game,"UpdateScore",p+"");
		
		int score = p.GetScore();
		if(score == -1)
			players--;
		
		if(players <= 1)
			EndGame();
		
		printOnExit(game,"UpdateScore",null);
	}
	
	/**
	 *Boxokat tartja szamon
	 */
	public static void CountBoxes(int add){
		printOnEntry(game,"CountBoxes",""+add);
		boxes+=add;
		if(boxes == 0)
			EndGame();
		printOnExit(game,"CountBoxes",null);
	}
	
	public static void setBoxNum(int i){
		boxes=i;
	}
	
	/**
	 * Megnezi, hogy strength max erovel tekintve van-e beszorult doboz-blokk
	 * 
	 * @param strength A max tolhato dobozok szama
	 */
	public boolean CheckForInvalidShape(int strength)
	{
		int width = active_map.GetWidth();
		int height = active_map.GetHeight();
		boolean blockFull = false;
		
		for(int i = 0; i < height - strength; i++) {
			
			for(int j = 0; j < width - strength; j++) {
				
				//checking if current strength*strength block is filled with moveables
				for(int k = 0; k < strength; k++) {
					
					for(int l = 0; l < strength; l++) {
						
							if(active_map.GetByIndex(i + k, j + l)!=null && active_map.GetByIndex(i + k, j + l).IsStuck != null) blockFull = true;
							else blockFull = false;
						}
						
					}
				
				//setting all moveables stuck
				if(blockFull) {
				for(int k = 0; k < strength; k++) {
				
					for(int l = 0; l < strength; l++) {
					
						current_map.GetByIndex(i + k, j + l).SetStuck();
					}
				}
				
				}
					//moving to the next block --> reseting blockFull boolean variable	
					blockFull = false;
				}
				
			}
		}
	
	
	/**
	 * Method overloading - parameter nelkul is legyen hivhato a fuggveny. Ilyenkor  Game maxStrength ertekevel szamol.
	 */
	public boolean CheckForInvalidShape() {
		
		return CheckForInvalidShape(maxStrength);
	}

////////////////////////////////////////////////Skeleton fuggvenyek///////////////////////////////////////////////////////////////////////
	
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
		/*String[] runStrings = {"1. Uj Jatek",
							"2. Jatekos lep",
							"3. Kilepes"};

		for (String s : runStrings){
			printOption(s);
		}

		int sel = printQuestion("Mit akarsz csinalni?", 1, 3);
		switch (sel) {
			case 1:
				int numP=printQuestion("Hany jatekos legyen?", 1, 4);
				NewGame(numP);
				break;
			case 2:
				Player p= new Player();
				String[] directionString = {"1. UP",
							"2. DOWN",
							"3. LEFT",
							"4. RIGHT"};

				for (String s : directionString){
					printOption(s);
				}

				int dir=printQuestion("Hova lep?", 1, 4);
				if(dir==1){
					p.Control(null,Direction.UP);
				}else if(dir==2){
					p.Control(null,Direction.DOWN);
				}else if(dir==3){
					p.Control(null,Direction.LEFT);
				}else if(dir==4){
					p.Control(null,Direction.RIGHT);
				}
				break;
			case 3:
				return false;
		}*/
		
		Map newMap= new Map();
		newMap.CreateMap(4);
		return false;
	}

}

