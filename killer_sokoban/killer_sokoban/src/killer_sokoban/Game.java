package killer_sokoban;

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
	 *
	 * @return A maximalis toloero a jatek jelenlegi allasaban.
	 */
	public int GetMaxStrength() { return maxStrength; }
	
	/**
	 * Beallitja a tarolt maximalis toloerot.
	 *
	 * @param newMaxStrength - az uj maximalis toloero.
	 */
	public void SetMaxStrength(int newMaxStrength) { maxStrength = newMaxStrength; }


	private static Game game;
	
	/**
	 *Konstruktor, letrehoz egy uj Mapot is.
	 */
	public Game() {		
		active_map= new Map();		
	}
	
	/**
	 *Program main-je
	 *
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Interpreter i = new Interpreter();

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
	 *
	 *@param add - Amennyivel novelni kell a szamontartott dobozok szamat.
	 */
	public static void CountBoxes(int add){
	
		boxes+=add;
		if(boxes == 0)
			EndGame();
	
	}
	
	/**
	 * Beallitja a Mapon levo Boxok szamat.
	 *
	 * @param i - Boxok szama
	 */
	public static void setBoxNum(int i){
		boxes=i;
	}
		
}

