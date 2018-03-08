package killer_sokoban;

public class Game {

	private int boxes;
	private int players;
	private int[] overallScore;
	/**
	 *Csak a Skeletonhoz hasznalt valtozok
	 */
	private static int tabs = 0;

	public Game() {
		printOnEntry(this,"<init>");
		printOnExit(this,"<init>",null);
	}

	public static void main(String[] args) {
		Game game = new Game();
	}

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
		System.out.print("call  ");
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
	 * @param returningObject az objektum, amiből meg lett hívva a metódus
	 * @param method a hivott fuggveny neve
	 * @param ret a vissza teresi ertek Stringben ha van
	 */
	public static void printOnExit(Object returningObject,String method,String ret) {
		System.out.print("return");
		printIndent();

		
		String methodExit = "<--" + returningObject.getClass().getSimpleName();
		methodExit += "." + method + "(" + ")";
		if(ret != null){
			methodExit += "=" + ret;
		}
		System.out.println(methodExit);
		tabs--;
	}

	/**
	 * Kiir egy opciot
	 */
	public static void printOption(String opt) {
		System.out.print("-     ");
		printIndent();
		System.out.println("  " + opt);
	}

	public static void UpdateScore(Player p){
		
	}
	
	public static void CountBoxes(int add){
		
	}
	
	public static void NewGame(int playerCount){
		
	}
	
	public static void EndGame(){
		
	}
}

