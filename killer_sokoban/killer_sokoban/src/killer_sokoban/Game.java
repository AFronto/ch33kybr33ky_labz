package killer_sokoban;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.xml.datatype.DatatypeConstants.Field;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.SystemColor;

import java.util.HashMap;

public class Game implements ActionListener {
	private static JFrame frame;
	private static JPanel panel = new JPanel();
	private static int boxes;
	private static int players = 2;
	private static int startingplayers = 2;
	private static Map active_map;
	private static int maxStrength = 4;
	private static JLabel blueScore;
	private static JLabel redScore;
	private static JLabel greenScore;
	private static JLabel yellowScore;
	private static JPanel menuPanel;
	private static JPanel scorePanel;
	private static 	JLabel blueLabel;
	private static 	JLabel redLabel;
	private static 	JLabel greenLabel;
	private static 	JLabel yellowLabel;
	private static int blueScoreNum = 0;
	private static int redScoreNum=0;
	private static int yellowScoreNum=0;
	private static int greenScoreNum=0;
	private static HashMap<Player, MyKeyListener> keylisteners = new HashMap<Player, MyKeyListener>();
	private JButton button;
	private JButton button_1;
	private static boolean gameOn=false;

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
/**
 * Letrehozza az osszes panelt: Menupanel,OptionsPanel, ScorePanel, valamint kezeli a gombok lenyomasat. Itt allitjuk a playerek szamat a comboboxok segitsegevel, valamint a 
 * scoreokat JLabelekkel irjuk ki.
 * A Panelek kozott a setVisibility segitsegevel valtunk
 */
	private void initialize() {

		//JFrame letrehozasa
		frame = new JFrame();
		//Hatter beallitasa
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.setBackground(Color.BLACK);
		frame.setBounds(100, 100, 565, 589);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
				// JPanels
				menuPanel = new JPanel();
				menuPanel.setBackground(SystemColor.info);
				menuPanel.setBounds(0, 0, 550, 550);
				frame.getContentPane().add(menuPanel);
				menuPanel.setLayout(null);

		//JPanel letrehozasa pontszamlalashoz
		scorePanel = new JPanel();
		//Hatter beallitasa
		scorePanel.setBackground(SystemColor.info);
		scorePanel.setBounds(0, 0, 550, 550);
		frame.getContentPane().add(scorePanel);
		scorePanel.setLayout(null);
		
		
		//New Game gomb letrehozasa
		button = new JButton("New Game", null);
		//ActionListener hozzarendelese
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scorePanel.setVisible(false);
				NewGame(players);
			}
		});
		//Button egyeb beallitasainek elvegzese
		//Button elhelyezese
		button.setVerticalTextPosition(SwingConstants.CENTER);
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		//Button szinenek beallitasa
		button.setForeground(Color.BLACK);
		//Button font beallitasa
		button.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		button.setBounds(175, 390, 200, 50);
		//Panelhoz addolas
		scorePanel.add(button);
		
		//Menu button letrehozasa
		button_1 = new JButton("Menu", null);
		//ActionListenerhez rendeles
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				scorePanel.setVisible(false);
				menuPanel.setVisible(true);
			}
		});
		//Button elhelyezese
		button_1.setVerticalTextPosition(SwingConstants.CENTER);
		button_1.setHorizontalTextPosition(SwingConstants.CENTER);
		//Button szinenek beallitasa
		button_1.setForeground(Color.BLACK);
		//Button font beallitasa
		button_1.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		button_1.setBounds(175, 446, 200, 50);
		//Hozzaadas panelhez
		scorePanel.add(button_1);
		
		//Kek Label letrehozasa
		blueLabel = new JLabel("");
		blueLabel.setBounds(175, 80, 100, 40);
		scorePanel.add(blueLabel);
		
		//Piros Label letrehozasa
		redLabel = new JLabel("");
		redLabel.setBounds(175, 130, 100, 40);
		scorePanel.add(redLabel);
		
		//zold label letrehozasa
		greenLabel = new JLabel("");
		greenLabel.setBounds(175, 180, 100, 40);
		scorePanel.add(greenLabel);
		
		//Sarga label letrehozasa
		yellowLabel = new JLabel("");
		yellowLabel.setBounds(175, 230, 100, 40);
		scorePanel.add(yellowLabel);
		scorePanel.setVisible(false);
		
		//Beallitasok panel letrehozasa
		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(null);
		optionsPanel.setBackground(SystemColor.info);
		optionsPanel.setBounds(0, 0, 550, 550);
		frame.getContentPane().add(optionsPanel);
		optionsPanel.setVisible(false);
		
		
		/// JButtons
		//Gomb grafika osszerendeles
		BufferedImage buttonIcon = null;
		try {
			buttonIcon = ImageIO.read(new File("buttonbg.png"));
		} catch (IOException e1) {
			System.out.println("bastya nem jo vmi: ");
			e1.printStackTrace();
		}
		//New Game gomb letrehozasa
		JButton newGameBtn = new JButton("New Game", (Icon) new ImageIcon(buttonIcon));
		//Szin beallitas
		newGameBtn.setForeground(Color.BLACK);
		//Button font beallitasa
		newGameBtn.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		//Button elhelyezes beallitasa
		newGameBtn.setHorizontalTextPosition(JButton.CENTER);
		newGameBtn.setVerticalTextPosition(JButton.CENTER);
		newGameBtn.setBounds(175, 50, 200, 50);
		//Button hozzaadasa a menuPanel-hez
		menuPanel.add(newGameBtn);
		//Button hozzarendelese ActionListenerhez
		newGameBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				menuPanel.setVisible(false);
				optionsPanel.setVisible(false);
				NewGame(players);

			}
		});
				//Exit button grafikajanak bellitasa			
				JButton exitBtn = new JButton("Exit", (Icon) new ImageIcon(buttonIcon));
				//Szin beallitasa
				exitBtn.setForeground(Color.BLACK);
				//Exit Button ActionListenerhez rendelese
				exitBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				//Exit Button elhelyezese
				exitBtn.setHorizontalTextPosition(JButton.CENTER);
				exitBtn.setVerticalTextPosition(JButton.CENTER);
				//Exit Button font bellitasa
				exitBtn.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
				exitBtn.setBackground(SystemColor.activeCaptionBorder);
				exitBtn.setBounds(175, 400, 200, 50);
				//Exit button hozzaadasa a menuPanelhez.
				menuPanel.add(exitBtn);
				
						//Options Button grafikajanak beallitasa
						JButton optionsBtn = new JButton("Options", (Icon) new ImageIcon(buttonIcon));
						//Szin beallitasa
						optionsBtn.setForeground(Color.BLACK);
						//Options button ActionListenerhez rendelese
						optionsBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								menuPanel.setVisible(false);
								optionsPanel.setVisible(true);
							}
						});
						//Options Button elhelyezese
						optionsBtn.setHorizontalTextPosition(JButton.CENTER);
						optionsBtn.setVerticalTextPosition(JButton.CENTER);
						//Options Button fontjanak beallitasa
						optionsBtn.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
						optionsBtn.setBackground(SystemColor.activeCaptionBorder);
						optionsBtn.setBounds(175, 150, 200, 50);
						//Options Button menuPanelre helyezese
						menuPanel.add(optionsBtn);
						
		//Back Buton grafikajanak beallitasa
		JButton backBtn = new JButton("Back", (Icon) new ImageIcon(buttonIcon));
		//Back Button szin beallitas
		backBtn.setForeground(Color.BLACK);
		//Back Button font beallitasa
		backBtn.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		//Back Button ActionListenerhez rendelese
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				optionsPanel.setVisible(false);
				menuPanel.setVisible(true);
			}
		});
		//Back Button elhelyezese
		backBtn.setHorizontalTextPosition(JButton.CENTER);
		backBtn.setVerticalTextPosition(JButton.CENTER);
		backBtn.setBounds(175, 175, 200, 50);
		//Back Button hozzadasa az optionsPanelhez
		optionsPanel.add(backBtn);
		///

		/// JLabels
		JLabel lblNewLabel = new JLabel("Number of players\r\n");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		lblNewLabel.setBounds(175, 70, 200, 23);
		optionsPanel.add(lblNewLabel);
		///
		// JRadioButtons
		JRadioButton radioButton_2 = new JRadioButton("2");
		radioButton_2.setBackground(SystemColor.scrollbar);
		radioButton_2.setBounds(222, 100, 35, 25);
		optionsPanel.add(radioButton_2);
		radioButton_2.setActionCommand("2");
		radioButton_2.setSelected(true);

		JRadioButton radioButton_3 = new JRadioButton("3");
		radioButton_3.setBackground(SystemColor.scrollbar);
		radioButton_3.setBounds(257, 100, 35, 25);
		optionsPanel.add(radioButton_3);
		radioButton_3.setActionCommand("3");

		JRadioButton radioButton_4 = new JRadioButton("4");
		radioButton_4.setBackground(SystemColor.scrollbar);
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

		blueScore = new JLabel("Blue: 0");
		blueScore.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		blueScore.setForeground(Color.WHITE);
		blueScore.setBounds(13, 503, 100, 40);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(blueScore);

		redScore = new JLabel("Red: 0");
		redScore.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		redScore.setForeground(Color.WHITE);
		redScore.setBounds(163, 503, 100, 40);
		frame.getContentPane().add(redScore);

		greenScore = new JLabel("Green: 0");
		greenScore.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		greenScore.setForeground(Color.WHITE);
		greenScore.setBounds(300, 503, 100, 40);
		greenScore.setVisible(false);
		frame.getContentPane().add(greenScore);

		yellowScore = new JLabel("Yellow: 0");
		yellowScore.setFont(new Font("Comic Sans MS", Font.PLAIN, 22));
		yellowScore.setForeground(Color.WHITE);
		yellowScore.setBounds(433, 503, 100, 40);
		yellowScore.setVisible(false);
		frame.getContentPane().add(yellowScore);

	}
/**
 * Kezeli a comboboxok valtasat.Akkor fut le amikor egy masik allapotra allitjuk. A jatekosok szamatol fugg, hogy kiirjuk e a pontokat. Mindig csak annak a pontjat irjuk ki aki tenylegesen jatszik
 */
	public void actionPerformed(ActionEvent e) {
		players = Integer.parseInt(e.getActionCommand());
		if (players >= 3)
			greenScore.setVisible(true);
		if (players >= 4) {
			yellowScore.setVisible(true);
		}
	}

	/**
	 * Uj jatek
	 *
	 * @param playerCount
	 *            A jatekosok szama ahanyan jatszani akarnak.
	 */
	public static void NewGame(int playerCount) {
		boxes=0;
		gameOn=true;
		startingplayers = players;
		keylisteners = active_map.CreateMap(playerCount, maxStrength, frame, panel);
	}

	/**
	 * Jatek vege
	 * Amikor vege egy jateknak, a Score kepernyore kerulunk, itt mindent alapallasba allitunk, hogy az elozo jateknak ne legyen hatasa az ujra. Jlabeleket es a pontokat nullazzuk.
	 * A jatekosok szamat visszaallitjuk a korrabbira
	 * Generalunk egy uj palyat
	 * A keylistenereket is frissitjuk
	 */
	public static void EndGame() {
		gameOn=false;

		scorePanel.setVisible(true);
		blueLabel.setText("Blue: "+blueScoreNum);
		if(blueScoreNum == -1)
			blueLabel.setText("Blue got rekt :(");
		redLabel.setText("Red: "+redScoreNum);
		if(redScoreNum == -1)
			redLabel.setText("Red got rekt :(");
		greenLabel.setText("Green: "+greenScoreNum);
		if(greenScoreNum == -1)
			greenLabel.setText("Green got rekt :(");
		yellowLabel.setText("Yellow: "+yellowScoreNum);
		if(yellowScoreNum == -1)
			yellowLabel.setText("Blue got rekt :(");
		blueScoreNum = 0;
		redScoreNum = 0;
		greenScoreNum = 0;
		yellowScoreNum=0;
		blueScore.setText("Blue: "+0);
		redScore.setText("Red: " + 0);
		greenScore.setText("Green: " + 0);
		yellowScore.setText("Yellow :" + 0);
		players = startingplayers;
		
		for(Player actKey : keylisteners.keySet()){
			frame.removeKeyListener(keylisteners.get(actKey));
		}
		active_map=null;
		active_map= new Map();
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
		if (p.GetColor().equals("blue")) {
			blueScore.setText("Blue: " + score);
			blueScoreNum = score;
		} else if (p.GetColor().equals("red")) {
			redScore.setText("Red: " + score);
			redScoreNum= score;
		} else if (p.GetColor().equals("green")) {
			greenScore.setText("Green: " + score);
			greenScoreNum= score;
		} else if (p.GetColor().equals("yellow")) {
			yellowScore.setText("Yellow :" + score);
			yellowScoreNum= score;
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

	/**
	 * Visszaadja az aktualis palyat.
	 */
	public static void getMyMap() {
		if(gameOn){
			active_map.printMyMap();
		}
	}
}
