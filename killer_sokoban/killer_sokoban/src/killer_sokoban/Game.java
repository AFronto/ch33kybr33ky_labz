package killer_sokoban;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;
import java.util.HashMap;

public class Game{
	private static JFrame frame;
	// private static Game game;
	private static int boxes;
	private static int players = 2;
	private int[] overallScore;
	private static Map active_map;
	private static  int maxStrength=6;

	private static HashMap<Player, MyKeyListener> keylisteners = new HashMap<Player, MyKeyListener>();

	/**
	 * A legerosebb jatekos toloerejevel ter vissza.
	 *
	 * @return A maximalis toloero a jatek jelenlegi allasaban.
	 */
	public int GetMaxStrength() {
		return maxStrength;
	}

	/**
	 * Beallitja a tarolt maximalis toloerot.
	 *
	 * @param newMaxStrength
	 *            - az uj maximalis toloero.
	 */

	public void SetMaxStrength(int newMaxStrength) {
		maxStrength = newMaxStrength;
	}

	/**
	 * Konstruktor, letrehoz egy uj Mapot is.
	 */
	public Game() {
		active_map = new Map();
	}

	/**
	 * Program main-je
	 *
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	/*
	 * public static void main(String[] args) throws FileNotFoundException,
	 * IOException { Interpreter i = new Interpreter();
	 * 
	 * while (true) { if(0 < args.length) { File inFile = new File(args[0]);
	 * i.Read(inFile); break; }else { i.Run(); } } }
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Graphics window = new Graphics();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Uj jatek
	 *
	 * @param playerCount
	 *            A jatekosok szama ahanyan jatszani akarnak.
	 */
	public static void NewGame(int playerCount){		
		keylisteners=active_map.CreateMap(playerCount,maxStrength,frame);		
	}

	/**
	 * Jatek vege
	 */
	public static void EndGame() {
		getMyMap();
		System.out.println();
		System.out.println("Vege");
		System.exit(0);
	}

	/**
	 * Kiveszi a Player-t ha -1 a pontja van, figyel a jatek vegere is Es ez
	 * felel a pontok osszesiteseert es meg jeleniteseert is.
	 *
	 * @param p
	 *            A jatekos akinek epp updateli a pontjait
	 */
	public static void UpdateScore(Player p) {

		int score = p.GetScore();
		if (score == -1) {
			players--;
			frame.removeKeyListener(keylisteners.get(p));
		}
		if (p.GetImage().equals("blueplayer.png")) {
			Graphics.blueScore.setText("Blue: " + score);
		} else if (p.GetImage().equals("redplayer.png")) {
			Graphics.redScore.setText("Red: " + score);
		} else if (p.GetImage().equals("greenplayer.png")) {
			Graphics.greenScore.setText("Green: " + score);
		} else if (p.GetImage().equals("yellowplayer.png")) {
			Graphics.yellowScore.setText("Yellow :" + score);
		}

		if (players <= 1) {
			EndGame();
		}

	}

	/**
	 * Boxokat tartja szamon
	 *
	 * @param add
	 *            - Amennyivel novelni kell a szamontartott dobozok szamat.
	 */
	public static void CountBoxes(int add) {

		boxes += add;
		if (boxes == 0)
			EndGame();

	}

	/**
	 * Beallitja a Mapon levo Boxok szamat.
	 *
	 * @param i
	 *            - Boxok szama
	 */
	public static void setBoxNum(int i) {
		boxes = i;
	}


	public static void getMyMap(){
		active_map.printMyMap();
	}
}
