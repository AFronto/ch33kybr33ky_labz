package killer_sokoban;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.xml.datatype.DatatypeConstants.Field;

public class Game {
	private static JFrame frame;
	
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
	
	/**
	 *Konstruktor, letrehoz egy uj Mapot is.
	 */
	public Game() {		
		active_map= new Map();		
		initialize();
	}
	
	/**
	 *Program main-je
	 *
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	/*public static void main(String[] args) throws FileNotFoundException, IOException {
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
	}*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game window = new Game();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Interpreter i = new Interpreter();
		
		JButton newGameBtn = new JButton("New Game");
		newGameBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				i.Run();
			}
		});
		frame.getContentPane().add(newGameBtn);
	}

	/**
	 *Uj jatek
	 *
	 *@param playerCount A jatekosok szama ahanyan jatszani akarnak.
	 */
	public void NewGame(int playerCount){		
		active_map.CreateMap(playerCount,6,frame);		
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

