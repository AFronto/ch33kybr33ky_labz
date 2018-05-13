package killer_sokoban;

import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.lang.Math;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static killer_sokoban.Game.*;

public class Map {

	private Field[][] fields;

	private int[][] maze;
	private int width;
	private int height;
	private int corridorLength;
	private double roomChance;
		private JFrame myFrame;
		private JPanel myPanel;

	/**
	 * Konstruktor
	 */
	public Map() {
		width = 11;
		height = 11;
		corridorLength = 2;
		roomChance = 1;
	}

	/**
	 * Generalja a palyat.
	 *
	 * @param playerCount
	 *            a jatekosok szama
	 * @param maxStrength
	 *            a maximalis ero
	 */

	public HashMap<Player, MyKeyListener> CreateMap(int playerCount, int maxStrength, JFrame frame,JPanel panel) {
		myFrame = frame;
		myPanel = panel;
		myPanel.setBounds(0, 0, 550, 550);
		myPanel.setLayout(null);
		myFrame.add(myPanel);

		fields = new Field[height][width];

		for (int y = 0; y < height; y++) /// csinal egy width x height méretu
											/// palyat sima mezokbol.
		{
			for (int x = 0; x < width; x++) {
				fields[y][x] = new Field();
				if (x - 1 >= 0) {
					fields[y][x - 1].SetNeighbour(Direction.RIGHT, fields[y][x]);
					fields[y][x].SetNeighbour(Direction.LEFT, fields[y][x - 1]);
				}
				if (y - 1 >= 0) {
					fields[y - 1][x].SetNeighbour(Direction.DOWN, fields[y][x]);
					fields[y][x].SetNeighbour(Direction.UP, fields[y - 1][x]);
				}
			}
		}

		generateMazeBase();

		for (int y = 0; y < height; y++) /// felviszi a falakat a palyara
		{
			for (int x = 0; x < width; x++) {
				if (maze[y][x] == 0) {
					fields[y][x].Register(null);
				} else {
					Wall w = new Wall();
					w.SetmyField(fields[y][x]);
					fields[y][x].Register(w);
				}
			}
		}

		setBoxNum(0); /// kinullazza a doboz szamlalot

		double trapDoorChance = 0.03;
		double holeChance = 0.03;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				double chance = Math.random();
				if (fields[y][x].GetmyMoveable() == null) {
					if (chance > 1 - holeChance) { /// ha a random szam bele
													/// esik a megszabott
													/// valoszinusegbe akkor
													/// Hole-t rak
						fields[y][x] = new Hole();
						fields[y][x - 1].SetNeighbour(Direction.RIGHT, fields[y][x]);
						fields[y][x].SetNeighbour(Direction.LEFT, fields[y][x - 1]);
						fields[y - 1][x].SetNeighbour(Direction.DOWN, fields[y][x]);
						fields[y][x].SetNeighbour(Direction.UP, fields[y - 1][x]);

						fields[y][x + 1].SetNeighbour(Direction.LEFT, fields[y][x]);
						fields[y][x].SetNeighbour(Direction.RIGHT, fields[y][x + 1]);
						fields[y + 1][x].SetNeighbour(Direction.UP, fields[y][x]);
						fields[y][x].SetNeighbour(Direction.DOWN, fields[y + 1][x]);
					} else if (chance < trapDoorChance) { /// A TrapDoor
															/// rakasnal is
															/// hasonloan
															/// valoszinuseg
															/// alapjan jar el.
						TrapDoor t = new TrapDoor();

						int counter = 0;
						int nX = ThreadLocalRandom.current().nextInt(1, 10);
						int nY = ThreadLocalRandom.current().nextInt(1, 10);
						while ((fields[nY][nX].GetmyMoveable() != null || !(fields[nY][nX] + "").equals("Field"))
								&& counter < 20) { /// Viszont a TrapDoort csak
													/// akkor rakja le ha gombot
							nX = ThreadLocalRandom.current().nextInt(1, 10); /// ha
																				/// gombot
																				/// is
																				/// tud
																				/// raknihozza.
							nY = ThreadLocalRandom.current().nextInt(1, 10);
							counter++;
						}

						if (counter < 20) {
							Button b = new Button();
							b.SetmyTrap(t);

							fields[nY][nX] = b;

							fields[nY][nX - 1].SetNeighbour(Direction.RIGHT, fields[nY][nX]);
							fields[nY][nX].SetNeighbour(Direction.LEFT, fields[nY][nX - 1]);
							fields[nY - 1][nX].SetNeighbour(Direction.DOWN, fields[nY][nX]);
							fields[nY][nX].SetNeighbour(Direction.UP, fields[nY - 1][nX]);

							fields[nY][nX + 1].SetNeighbour(Direction.LEFT, fields[nY][nX]);
							fields[nY][nX].SetNeighbour(Direction.RIGHT, fields[nY][nX + 1]);
							fields[nY + 1][nX].SetNeighbour(Direction.UP, fields[nY][nX]);
							fields[nY][nX].SetNeighbour(Direction.DOWN, fields[nY + 1][nX]);

							fields[y][x] = t;

							fields[y][x - 1].SetNeighbour(Direction.RIGHT, fields[y][x]);
							fields[y][x].SetNeighbour(Direction.LEFT, fields[y][x - 1]);
							fields[y - 1][x].SetNeighbour(Direction.DOWN, fields[y][x]);
							fields[y][x].SetNeighbour(Direction.UP, fields[y - 1][x]);

							fields[y][x + 1].SetNeighbour(Direction.LEFT, fields[y][x]);
							fields[y][x].SetNeighbour(Direction.RIGHT, fields[y][x + 1]);
							fields[y + 1][x].SetNeighbour(Direction.UP, fields[y][x]);
							fields[y][x].SetNeighbour(Direction.DOWN, fields[y + 1][x]);
						}
					}
				}
			}
		}

		int nX = ThreadLocalRandom.current().nextInt(1, 10);
		int nY = ThreadLocalRandom.current().nextInt(1, 10);
		while ((fields[nY][nX].GetmyMoveable() != null || !(fields[nY][nX] + "").equals("Field"))) { /// addig generalgat random koordinatakat amig nem talala egy ures mezot
			nX = ThreadLocalRandom.current().nextInt(1, 10);
			nY = ThreadLocalRandom.current().nextInt(1, 10);
		}

		fields[nY][nX] = new Target(); /// ide rakja le a targetet

		fields[nY][nX - 1].SetNeighbour(Direction.RIGHT, fields[nY][nX]);
		fields[nY][nX].SetNeighbour(Direction.LEFT, fields[nY][nX - 1]);
		fields[nY - 1][nX].SetNeighbour(Direction.DOWN, fields[nY][nX]);
		fields[nY][nX].SetNeighbour(Direction.UP, fields[nY - 1][nX]);

		fields[nY][nX + 1].SetNeighbour(Direction.LEFT, fields[nY][nX]);
		fields[nY][nX].SetNeighbour(Direction.RIGHT, fields[nY][nX + 1]);
		fields[nY + 1][nX].SetNeighbour(Direction.UP, fields[nY][nX]);
		fields[nY][nX].SetNeighbour(Direction.DOWN, fields[nY + 1][nX]);

		nX = ThreadLocalRandom.current().nextInt(2, 9); /// Ez kell hogy 1 box
															/// fixen legyen
		nY = ThreadLocalRandom.current().nextInt(2, 9);
		while ((fields[nY][nX].GetmyMoveable() != null || !(fields[nY][nX] + "").equals("Field"))) {
			nX = ThreadLocalRandom.current().nextInt(2, 9);
			nY = ThreadLocalRandom.current().nextInt(2, 9);
		}
		Box bx = new Box();
		bx.SetmyField(fields[nY][nX]);
		fields[nY][nX].Register(bx);
		fields[nY][nX].FieldAction();

		double boxChance = 0.05;
		int myBcounter = 5; /// Igy max 6 doboz lehet

		for (int y = 2; y < height-2; y++) /// veletlen szeruen lerak dobozokat
		{
			for (int x = 2; x < width-2; x++) {
				if (fields[y][x].GetmyMoveable() == null) {
					if ((fields[y][x] + "").equals("Field")) {
						double chance = Math.random();
						if (chance < boxChance) {
							Box b = new Box();
							b.SetmyField(fields[y][x]);
							fields[y][x].Register(b);

                            myBcounter--;
							
                            if (myBcounter == 0) {
								break;
							}
						}
					}
				}
			}

			if (myBcounter == 0) {
				break;
			}
		}

        for (int y = 0; y < height; y++) /// Megnezi hogy a dobozok be vannak e szorulva
        {
            for (int x = 0; x < width; x++) {
                if (fields[y][x].GetmyMoveable() != null && (fields[y][x].GetmyMoveable() + "").equals("Box")) {
                    fields[y][x].FieldAction();
                }
            }
        }

		ArrayList<Integer> keysWASD = new ArrayList<Integer>();
		keysWASD.add(KeyEvent.VK_W);
		keysWASD.add(KeyEvent.VK_S);
		keysWASD.add(KeyEvent.VK_A);
		keysWASD.add(KeyEvent.VK_D);
        keysWASD.add(KeyEvent.VK_Q);
		ArrayList<Integer> keysARROWS = new ArrayList<Integer>();
		keysARROWS.add(KeyEvent.VK_UP);
		keysARROWS.add(KeyEvent.VK_DOWN);
		keysARROWS.add(KeyEvent.VK_LEFT);
		keysARROWS.add(KeyEvent.VK_RIGHT);
        keysARROWS.add(KeyEvent.VK_MINUS);
		ArrayList<Integer> keysUHJK = new ArrayList<Integer>();
		keysUHJK.add(KeyEvent.VK_U);
		keysUHJK.add(KeyEvent.VK_J);
		keysUHJK.add(KeyEvent.VK_H);
		keysUHJK.add(KeyEvent.VK_K);
        keysUHJK.add(KeyEvent.VK_Z);
		ArrayList<Integer> keysNUMPAD = new ArrayList<Integer>();
		keysNUMPAD.add(KeyEvent.VK_NUMPAD5);
		keysNUMPAD.add(KeyEvent.VK_NUMPAD2);
		keysNUMPAD.add(KeyEvent.VK_NUMPAD1);
		keysNUMPAD.add(KeyEvent.VK_NUMPAD3);
        keysNUMPAD.add(KeyEvent.VK_NUMPAD4);
		ArrayList<ArrayList<Integer>> keys = new ArrayList<ArrayList<Integer>>();
		keys.add(keysWASD);
		keys.add(keysARROWS);
		keys.add(keysUHJK);
		keys.add(keysNUMPAD);

		HashMap<Player, MyKeyListener> keylisteners = new HashMap<Player, MyKeyListener>();

		ArrayList<String> colors = new ArrayList<String>();
		colors.add("blue");
		colors.add("red");
		colors.add("green");
		colors.add("yellow");

		nX = ThreadLocalRandom.current().nextInt(1, 10);
		nY = ThreadLocalRandom.current().nextInt(1, 10);
		while (playerCount > 0) { /// random helyekre rakja le a megfelelo szamu
									/// playert.
			while ((fields[nY][nX].GetmyMoveable() != null || !(fields[nY][nX] + "").equals("Field"))) {
				nX = ThreadLocalRandom.current().nextInt(1, 10);
				nY = ThreadLocalRandom.current().nextInt(1, 10);
			}
			Player p = new Player(maxStrength, colors.get(playerCount - 1));
			p.SetmyField(fields[nY][nX]);
			fields[nY][nX].Register(p);
			maxStrength--; /// fokozatosan csokkenti a jatekosok erejet.
			playerCount--;

			MyKeyListener mKl = new MyKeyListener(p, keys.get(playerCount));
			myFrame.addKeyListener(mKl);
			myFrame.setFocusable(true);
			myFrame.setFocusTraversalKeysEnabled(false);
			keylisteners.put(p, mKl);
		}

		printMyMap();
		return keylisteners;
	}

	public void printMyMap() {
		myPanel.removeAll();
		for (int y = 0; y < height; y++) /// kiirja a vegeredmenyt
		{
			for (int x = 0; x < width; x++) {
				if (fields[y][x].GetmyMoveable() == null) {
                    JLabel toLoad = new JLabel(new ImageIcon(fields[y][x].GetImage()));
                    toLoad.setBounds(x * 50, y * 50, 50, 50);
                    myPanel.add(toLoad);
                } else {
                    JLabel toLoad = new JLabel(new ImageIcon(fields[y][x].GetmyMoveable().GetImage()));
                    toLoad.setBounds(x * 50, y * 50, 50, 50);
                    myPanel.add(toLoad);
                }
            }
        }
		myPanel.repaint();
		myFrame.repaint();
		myFrame.requestFocus();
	}

	/**
	 * A megfelelo indexen levo field elemet adja vissza.
	 * 
	 * @param i
	 *            Az i index a 2d tombben
	 * @param j
	 *            A j index a 2d tombben
	 * @return A map matrixanak i,j-edik elemevel ter vissza - ez egy Field
	 *         tipusu objektum egyebkent.
	 */
	public Field GetByIndex(int i, int j) {
		return fields[i][j];
	}

	/**
	 * Vissaza adja a terkep szelesseget.
	 *
	 * @return width
	 */
	public int GetWidth() {
		return width;
	}

	/**
	 * Vissaza adja a terkep magassagat.
	 *
	 * @return height
	 */
	public int GetHeight() {
		return height;
	}

	/**
	 * DFS segitsegeve rekurzivan legeneralja a palya alapjait
	 */
	public void generateMazeBase() {
		maze = new int[height][width];
		// Initialize
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				maze[i][j] = 1;
			}
		}

		int r = ThreadLocalRandom.current().nextInt(0, height);
		while (r % 2 == 0) {
			r = ThreadLocalRandom.current().nextInt(0, height);
		}

		int c = ThreadLocalRandom.current().nextInt(0, width);
		while (c % 2 == 0) {
			c = ThreadLocalRandom.current().nextInt(0, width);
		}

		maze[r][c] = 0;

		recursion(r, c);
	}

	/**
	 * Ez magaa a rekurzio ami a terkep ket koordinatajaval dolgozik amik azt
	 * irjak le hogy epp hol all a DFS.
	 * 
	 * @param r
	 *            Az eppen aktualis r koordinata
	 * @param c
	 *            Az eppen aktualis c koordinata
	 */
	public void recursion(int r, int c) {
		// 4 random directions
		ArrayList<Integer> randDirs = generateRandomDirections();
		// Examine each direction
		for (int i = 0; i < randDirs.size(); i++) {
			switch (randDirs.get(i)) {
			case 0: // Up
					// 　Whether 2 cells up is out or not
				if (r - corridorLength <= 0)
					continue;
				if (maze[r - corridorLength][c] != 0) {

					for (int act = 1; act < corridorLength + 1; act++) {
						maze[r - act][c] = 0;
						if (act == corridorLength / 2) {
							GenRoom(r - act, c);
						}
					}
					recursion(r - corridorLength, c);
				}
				break;
			case 1: // Right
					// Whether 2 cells to the right is out or not
				if (c + corridorLength >= width - 1)
					continue;
				if (maze[r][c + corridorLength] != 0) {
					for (int act = 1; act < corridorLength + 1; act++) {
						maze[r][c + act] = 0;
						if (act == corridorLength / 2) {
							GenRoom(r, c + act);
						}
					}
					recursion(r, c + corridorLength);
				}
				break;
			case 2: // Down
					// Whether 2 cells down is out or not
				if (r + corridorLength >= height - 1)
					continue;
				if (maze[r + corridorLength][c] != 0) {
					for (int act = 1; act < corridorLength + 1; act++) {
						maze[r + act][c] = 0;
						if (act == corridorLength / 2) {
							GenRoom(r + act, c);
						}
					}

					recursion(r + corridorLength, c);
				}
				break;
			case 3: // Left
					// Whether 2 cells to the left is out or not
				if (c - corridorLength <= 0)
					continue;
				if (maze[r][c - corridorLength] != 0) {
					for (int act = 1; act < corridorLength + 1; act++) {
						maze[r][c - act] = 0;
						if (act == corridorLength / 2) {
							GenRoom(r, c - act);
						}
					}
					recursion(r, c - corridorLength);
				}
				break;
			}
		}
	}

	/**
	 * Random sorrendben lerakja a lehetseges 4 iranyt egy tombben
	 *
	 * @return MyArray Ez a negy iranyra utalo integereket tartalmazo tomb.
	 */
	public ArrayList<Integer> generateRandomDirections() {
		ArrayList<Integer> MyArray = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {
			int value = ThreadLocalRandom.current().nextInt(0, 4);
			while (MyArray.contains(value)) {
				value = ThreadLocalRandom.current().nextInt(0, 4);
			}
			MyArray.add(value);
		}

		return MyArray;
	}

	/**
	 * General egy ureget az algoritmusbna gyartott folyosok menten. Ez a
	 * tagasabb terk generalasa vegett kerult be.
	 *
	 * @param x
	 *            Az aktualis x koordinata ahonan a aszobanak ki kene nonie
	 * @param y
	 *            Az aktualis Y koordinata ahonan a aszobanak ki kene nonie
	 */
	public void GenRoom(int x, int y) {
		double chanche = Math.random();
		if (chanche < roomChance) {
			for (int i = -corridorLength / 2; i < corridorLength / 2; i++) {
				for (int j = -corridorLength / 2; j < corridorLength / 2; j++) {
					if (x + i > 0 && x + i < width - 1 && y + j > 0 && y + j < height - 1) {
						maze[x + i][y + j] = 0;
					}
				}
			}
		}
	}
}
