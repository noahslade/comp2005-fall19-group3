import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Init extends JPanel {

	private JButton[] cpuButtons;
	
	private int humanPlayers;
	private int cpuPlayers;
	private int difficultyLevel;
	private boolean randomColors;
	private JPanel humanPlayerPanel;
	private JPanel cpuPlayerPanel;
	private JPanel difficultyPanel;
	private JPanel randomColorPanel;
	/**
	 * Create the panel.
	 * @param main 
	 */
	public Init(Blokus main) {

		setLayout(new GridLayout(5,2));
		setBounds(230,120,700,400);
		setBackground(new Color (86, 140, 48));
		
		JLabel num_player = new JLabel("No. of Player's");
		num_player.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(num_player);
		
		makePlayerOptions();
		
		JLabel num_cpu = new JLabel("No. of CPU's");
		num_cpu.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(num_cpu);
		
		makeCPUOptions();
		
		JLabel lblDifficulty = new JLabel("Difficulty Level");
		lblDifficulty.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(lblDifficulty);
		
		makeDifficultyOptions();
		
		JLabel lblRandomColor = new JLabel("Random Color");
		lblRandomColor.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(lblRandomColor);

		randomColorSelector();
		
		JPanel mainMenu = new JPanel();
		mainMenu.setBackground(Color.red);
		mainMenu.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				main.setRoute("land");
				
			}
		});
		
		JLabel mainMenuLabel = new JLabel("Main Menu");
		mainMenuLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		mainMenu.add(mainMenuLabel);
		add(mainMenu);

		JPanel next = new JPanel();
		next.setBackground(Color.GREEN);
		add(next);
		next.addMouseListener(new MouseAdapter() {
			@Override
			
			public void mousePressed(MouseEvent e) {
				main.gameInfo(humanPlayers, cpuPlayers, difficultyLevel);
				if (!randomColors) {
					main.setRoute("color");
					}
				else {
					main.setRoute("board");
				}
			}
		});
		
		JLabel nextLabel = new JLabel("Next");
		nextLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		next.add(nextLabel);
	}
	private void randomColorSelector() {
		randomColorPanel = new JPanel();
		
		JButton yesRandom = new JButton("Yes");
		JButton noRandom = new JButton("No");
		
		yesRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				randomColors = true;
				noRandom.setVisible(false);
			}
		});
		
		noRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				randomColors = false;
				yesRandom.setVisible(false);
			}
		});
		
		randomColorPanel.setBackground(new Color (86, 140, 48));
		randomColorPanel.add(yesRandom);
		randomColorPanel.add(noRandom);
		
		add(randomColorPanel);
		
	}
	
	private void makeDifficultyOptions() {

		difficultyPanel = new JPanel();
		
		JButton easyButton = new JButton("Easy");
		JButton mediumButton = new JButton("Medium");
		JButton hardButton = new JButton("Hard");

		easyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mediumButton.setVisible(false);
				hardButton.setVisible(false);
				difficultyLevel = 0;
			}
		});
		
		mediumButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				easyButton.setVisible(false);
				hardButton.setVisible(false);
				difficultyLevel = 1;
			}
		});
		
		hardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				easyButton.setVisible(false);
				mediumButton.setVisible(false);
				difficultyLevel = 2;
			}
		});
		
		difficultyPanel.setBackground(new Color (86, 140, 48));
		difficultyPanel.add(easyButton);
		difficultyPanel.add(mediumButton);
		difficultyPanel.add(hardButton);
		add(difficultyPanel);
	}
	
	
	private void activateCPUOptions(int numPlayers) {
		if (numPlayers == 1) {
			for (int i = 1; i < 4; i ++) {
				cpuButtons[i].setVisible(true);
			}
		}
		else {
			for (int i = 0; i < 5 - numPlayers; i ++) {
				cpuButtons[i].setVisible(true);
			}
		}
	}
	
	
	private void makePlayerOptions() {
		humanPlayerPanel = new JPanel();
		
		JButton onePlayer = new JButton ("1");
		JButton twoPlayer = new JButton ("2");
		JButton threePlayer = new JButton ("3");
		JButton fourPlayer = new JButton ("4");
		
		onePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				humanPlayers = 1;
				activateCPUOptions(1);
				twoPlayer.setVisible(false);
				threePlayer.setVisible(false);
				fourPlayer.setVisible(false);
			}
		});
		
		twoPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				humanPlayers = 2;
				activateCPUOptions(2);
				onePlayer.setVisible(false);
				threePlayer.setVisible(false);
				fourPlayer.setVisible(false);
			}
		});
		
		threePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				humanPlayers = 3;
				activateCPUOptions(3);
				onePlayer.setVisible(false);
				twoPlayer.setVisible(false);
				fourPlayer.setVisible(false);
			}
		});
		
		fourPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				humanPlayers = 4;
				activateCPUOptions(4);
				onePlayer.setVisible(false);
				twoPlayer.setVisible(false);
				threePlayer.setVisible(false);
			}
		});
	
		humanPlayerPanel.setBackground(new Color (86, 140, 48));
		humanPlayerPanel.add(onePlayer);
		humanPlayerPanel.add(twoPlayer);
		humanPlayerPanel.add(threePlayer);
		humanPlayerPanel.add(fourPlayer);
		
		add(humanPlayerPanel);
	}
	
	private void makeCPUOptions() {
		
		cpuPlayerPanel = new JPanel();

		JButton zeroCPU = new JButton ("0");
		JButton oneCPU = new JButton ("1");
		JButton twoCPU = new JButton ("2");
		JButton threeCPU = new JButton ("3");
		
		zeroCPU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cpuPlayers = 0;
				oneCPU.setVisible(false);
				twoCPU.setVisible(false);
				threeCPU.setVisible(false);
			}
		});
		oneCPU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cpuPlayers = 1;
				zeroCPU.setVisible(false);
				twoCPU.setVisible(false);
				threeCPU.setVisible(false);
			}
		});
		twoCPU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cpuPlayers = 2;
				zeroCPU.setVisible(false);
				oneCPU.setVisible(false);
				threeCPU.setVisible(false);
			}
		});
		threeCPU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cpuPlayers = 3;
				zeroCPU.setVisible(false);
				oneCPU.setVisible(false);
				twoCPU.setVisible(false);
			}
		});
		
		cpuButtons = new JButton[4];
		cpuButtons[0] = zeroCPU;
		cpuButtons[1] = oneCPU;
		cpuButtons[2] = twoCPU;
		cpuButtons[3] = threeCPU;
		
		zeroCPU.setVisible(false);
		oneCPU.setVisible(false);
		twoCPU.setVisible(false);
		threeCPU.setVisible(false);
		
		cpuPlayerPanel.setBackground(new Color (86, 140, 48));
		cpuPlayerPanel.add(zeroCPU);
		cpuPlayerPanel.add(oneCPU);
		cpuPlayerPanel.add(twoCPU);
		cpuPlayerPanel.add(threeCPU);
		
		add(cpuPlayerPanel);
	}
}
