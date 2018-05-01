package killer_sokoban;

import static killer_sokoban.Game.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Graphics implements ActionListener {
	static JFrame frame;
	static JLabel blueScore;
	static JLabel redScore;
	static JLabel greenScore;
	static JLabel yellowScore;
	static int players = 2;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public Graphics() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.setBackground(Color.BLACK);
		frame.setBounds(100, 100, 565, 589);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// JPanels
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(SystemColor.info);
		menuPanel.setBounds(0, 0, 550, 550);
		frame.getContentPane().add(menuPanel);
		menuPanel.setLayout(null);

		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(null);
		optionsPanel.setBackground(SystemColor.info);
		optionsPanel.setBounds(0, 0, 550, 550);
		frame.getContentPane().add(optionsPanel);
		optionsPanel.setVisible(false);

		/// JButtons
		BufferedImage buttonIcon = null;
		try {
			buttonIcon = ImageIO.read(new File("buttonbg.png"));
		} catch (IOException e1) {
			System.out.println("bastya nem jo vmi: ");
			e1.printStackTrace();
		}
		JButton newGameBtn = new JButton("New Game", (Icon) new ImageIcon(buttonIcon));
		newGameBtn.setForeground(SystemColor.text);
		newGameBtn.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		newGameBtn.setHorizontalTextPosition(JButton.CENTER);
		newGameBtn.setVerticalTextPosition(JButton.CENTER);
		newGameBtn.setBounds(175, 50, 200, 50);
		menuPanel.add(newGameBtn);
		newGameBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				menuPanel.setVisible(false);
				optionsPanel.setVisible(false);
				NewGame(players);

			}
		});

		JButton backBtn = new JButton("Back", (Icon) new ImageIcon(buttonIcon));
		backBtn.setForeground(SystemColor.text);
		backBtn.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				optionsPanel.setVisible(false);
				menuPanel.setVisible(true);
			}
		});
		backBtn.setHorizontalTextPosition(JButton.CENTER);
		backBtn.setVerticalTextPosition(JButton.CENTER);
		backBtn.setBounds(175, 175, 200, 50);
		optionsPanel.add(backBtn);

		JButton exitBtn = new JButton("Exit", (Icon) new ImageIcon(buttonIcon));
		exitBtn.setForeground(SystemColor.text);
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitBtn.setHorizontalTextPosition(JButton.CENTER);
		exitBtn.setVerticalTextPosition(JButton.CENTER);
		exitBtn.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		exitBtn.setBackground(SystemColor.activeCaptionBorder);
		exitBtn.setBounds(175, 400, 200, 50);
		menuPanel.add(exitBtn);

		JButton optionsBtn = new JButton("Options", (Icon) new ImageIcon(buttonIcon));
		optionsBtn.setForeground(SystemColor.text);
		optionsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuPanel.setVisible(false);
				optionsPanel.setVisible(true);
			}
		});
		optionsBtn.setHorizontalTextPosition(JButton.CENTER);
		optionsBtn.setVerticalTextPosition(JButton.CENTER);
		optionsBtn.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 20));
		optionsBtn.setBackground(SystemColor.activeCaptionBorder);
		optionsBtn.setBounds(175, 150, 200, 50);
		menuPanel.add(optionsBtn);
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

	public void actionPerformed(ActionEvent e) {
		players = Integer.parseInt(e.getActionCommand());
		if (players >= 3)
			greenScore.setVisible(true);
		if (players >= 4) {
			yellowScore.setVisible(true);
		}
	}
}
