import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Init extends JPanel {

	private JButton[] humanButtons;
	private JButton[] cpuButtons;
	private JButton[] difficultyButtons;
	private JButton[] randomColorButtons;
	
	private int humanPlayers;
	private int cpuPlayers = 0;
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
				for(JButton curButton:humanButtons) {
					curButton.setVisible(true);
				}
				for(JButton curButton: cpuButtons) {
					curButton.setVisible(false);
				}
				for(JButton curButton: difficultyButtons) {
					curButton.setVisible(true);
				}
				for(JButton curButton: randomColorButtons) {
					curButton.setVisible(true);
				}
				
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
		
		randomColorButtons = new JButton[2];
		randomColorButtons[0]= new JButton("Yes");
		randomColorButtons[1]= new JButton("No");
		
		randomColorButtons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				randomColors = true;
				randomColorButtons[1].setVisible(false);
			}
		});
		
		randomColorButtons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				randomColors = false;
				randomColorButtons[0].setVisible(false);
			}
		});
		
		randomColorPanel.setBackground(new Color (86, 140, 48));
		randomColorPanel.add(randomColorButtons[0]);
		randomColorPanel.add(randomColorButtons[1]);
		
		add(randomColorPanel);
		
	}
	
	private void makeDifficultyOptions() {

		difficultyPanel = new JPanel();

		difficultyButtons = new JButton[3];
		
		difficultyButtons[0]= new JButton("Easy");
		difficultyButtons[1]= new JButton("Medium");
		difficultyButtons[2]= new JButton("Hard");

		difficultyButtons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficultyButtons[1].setVisible(false);
				difficultyButtons[2].setVisible(false);
				difficultyLevel = 0;
			}
		});
		
		difficultyButtons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficultyButtons[0].setVisible(false);
				difficultyButtons[2].setVisible(false);
				difficultyLevel = 1;
			}
		});
		
		difficultyButtons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficultyButtons[0].setVisible(false);
				difficultyButtons[1].setVisible(false);
				difficultyLevel = 2;
			}
		});
		
		difficultyPanel.setBackground(new Color (86, 140, 48));
		for(JButton difficultyButton:difficultyButtons) {
			difficultyPanel.add(difficultyButton);
		}
		
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
		
		humanButtons = new JButton[4];
		humanButtons[0] = new JButton ("1");
		humanButtons[1]  = new JButton ("2");
		humanButtons[2]  = new JButton ("3");
		humanButtons[3]  = new JButton ("4");
		
		humanButtons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				humanPlayers = 1;
				activateCPUOptions(1);
				humanButtons[1].setVisible(false);
				humanButtons[2].setVisible(false);
				humanButtons[3].setVisible(false);
			}
		});
		
		humanButtons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				humanPlayers = 2;
				activateCPUOptions(2);
				humanButtons[0].setVisible(false);
				humanButtons[2].setVisible(false);
				humanButtons[3].setVisible(false);
			}
		});
		
		humanButtons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				humanPlayers = 3;
				activateCPUOptions(3);
				humanButtons[0].setVisible(false);
				humanButtons[1].setVisible(false);
				humanButtons[3].setVisible(false);
			}
		});
		
		humanButtons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				humanPlayers = 4;
				activateCPUOptions(4);
				humanButtons[0].setVisible(false);
				humanButtons[1].setVisible(false);
				humanButtons[2].setVisible(false);
			}
		});
	
		humanPlayerPanel.setBackground(new Color (86, 140, 48));
		
		for (JButton humanButton:humanButtons) {
			humanPlayerPanel.add(humanButton);
		}
		
		add(humanPlayerPanel);
	}
	
	private void makeCPUOptions() {
		
		cpuPlayerPanel = new JPanel();

		cpuButtons = new JButton[4];
		cpuButtons[0] = new JButton ("0");
		cpuButtons[1] =new JButton ("1");
		cpuButtons[2] = new JButton ("2");
		cpuButtons[3] = new JButton ("3");

		
		cpuButtons[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cpuPlayers = 0;
				cpuButtons[1].setVisible(false);
				cpuButtons[2].setVisible(false);
				cpuButtons[3].setVisible(false);
			}
		});
		cpuButtons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cpuPlayers = 1;
				cpuButtons[0].setVisible(false);
				cpuButtons[2].setVisible(false);
				cpuButtons[3].setVisible(false);
			}
		});
		cpuButtons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cpuPlayers = 2;
				cpuButtons[0].setVisible(false);
				cpuButtons[1].setVisible(false);
				cpuButtons[3].setVisible(false);
			}
		});
		cpuButtons[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cpuPlayers = 3;
				cpuButtons[0].setVisible(false);
				cpuButtons[1].setVisible(false);
				cpuButtons[2].setVisible(false);
			}
		});
		
		for (JButton cpuButton:cpuButtons) {
			cpuButton.setVisible(false);
			cpuPlayerPanel.add(cpuButton);
		}
		
		cpuPlayerPanel.setBackground(new Color (86, 140, 48));
		
		add(cpuPlayerPanel);
	}
}
