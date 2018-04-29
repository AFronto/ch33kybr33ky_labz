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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.xml.datatype.DatatypeConstants.Field;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game implements ActionListener {
	private static JFrame frame;
	// private static Game game;
	private static int boxes;
	private static int players = 2;
	private int[] overallScore;
	private Map active_map;
	private int maxStrength;

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
		initialize();
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
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.setBackground(Color.ORANGE);
		frame.setBounds(100, 100, 550, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// JPanels
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(Color.YELLOW);
		menuPanel.setBounds(0, 0, 534, 511);
		frame.getContentPane().add(menuPanel);
		menuPanel.setLayout(null);

		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(null);
		optionsPanel.setBackground(Color.YELLOW);
		optionsPanel.setBounds(0, 0, 534, 511);
		frame.getContentPane().add(optionsPanel);
		optionsPanel.setVisible(false);

		JButton newGameBtn = new JButton("New Game");
		newGameBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		newGameBtn.setBackground(Color.MAGENTA);
		newGameBtn.setBounds(175, 50, 200, 50);
		menuPanel.add(newGameBtn);
		newGameBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				NewGame(players);
				menuPanel.setVisible(false);
			}
		});
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				optionsPanel.setVisible(false);
				menuPanel.setVisible(true);
			}
		});
		backBtn.setBounds(222, 175, 105, 48);
		optionsPanel.add(backBtn);


		JButton exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		exitBtn.setBackground(Color.MAGENTA);
		exitBtn.setBounds(175, 400, 200, 50);
		menuPanel.add(exitBtn);

		JButton optionsBtn = new JButton("Options");
		optionsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPanel.setVisible(false);
				optionsPanel.setVisible(true);
			}
		});
		optionsBtn.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		optionsBtn.setBackground(Color.MAGENTA);
		optionsBtn.setBounds(175, 150, 200, 50);
		menuPanel.add(optionsBtn);
		///
		
		///JLabels
		JLabel lblNewLabel = new JLabel("Number of players\r\n");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		lblNewLabel.setBounds(175, 70, 200, 23);
		optionsPanel.add(lblNewLabel);
		///
		// JRadioButtons
		JRadioButton radioButton_2 = new JRadioButton("2");
		radioButton_2.setBackground(Color.MAGENTA);
		radioButton_2.setBounds(222, 100, 35, 25);
		optionsPanel.add(radioButton_2);
		radioButton_2.setActionCommand("2");
		radioButton_2.setSelected(true);

		JRadioButton radioButton_3 = new JRadioButton("3");
		radioButton_3.setBackground(Color.MAGENTA);
		radioButton_3.setBounds(257, 100, 35, 25);
		optionsPanel.add(radioButton_3);
		radioButton_3.setActionCommand("3");

		JRadioButton radioButton_4 = new JRadioButton("4");
		radioButton_4.setBackground(Color.MAGENTA);
		radioButton_4.setBounds(292, 100, 35, 25);
		optionsPanel.add(radioButton_4);
		radioButton_4.setActionCommand("4");
		
		radioButton_2.addActionListener((ActionListener) this);
		radioButton_3.addActionListener((ActionListener) this);
		radioButton_4.addActionListener((ActionListener) this);
		
		ButtonGroup group = new ButtonGroup();
		group.add(radioButton_2);
		group.add(radioButton_3);
		group.add(radioButton_4);
		//////

	}

	public void actionPerformed(ActionEvent e) {
		players = Integer.parseInt(e.getActionCommand());
	}

	/**
	 * Uj jatek
	 *
	 * @param playerCount
	 *            A jatekosok szama ahanyan jatszani akarnak.
	 */
	public void NewGame(int playerCount) {
		active_map.CreateMap(playerCount, 6);
	}

	/**
	 * Jatek vege
	 */
	public static void EndGame() {

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
		if (score == -1)
			players--;

		if (players <= 1)
			EndGame();

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
}
