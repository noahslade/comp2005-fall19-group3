import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class ColorSelection extends JPanel {
	private int playerColor=0; // Player's Color starting with player 1 color
	private int numPlayers;
	private int difficulty;
	private Color [] color_array= new Color[4];


	public void injectData(int numberOfPlayers, int difficulty){
		this.numPlayers=numberOfPlayers; // Comes from Init class, it is the number of human players
		this.difficulty=difficulty;
		this.color_array=new Color[4]; // This array contains 4 spaces to put a Color Class
	}

	public void setColor(Color color ,JButton button){
		if (playerColor<numPlayers) {
			color_array[playerColor] = color;
			button.setVisible(false);
			playerColor+=1;
		}
	}

	public void setColorAI(){
		int i =numPlayers;
		while (i<4){
			if(!Arrays.asList(color_array).contains(Color.RED)){color_array[i]=Color.RED;}
			else if(!Arrays.asList(color_array).contains(Color.YELLOW)){color_array[i]=Color.YELLOW;}
			else if(!Arrays.asList(color_array).contains(Color.GREEN)){color_array[i]=Color.GREEN;}
			else {color_array[i]=Color.BLUE;}
			i++;
		}
	}


	/**
	 * Create the panel.
	 */
	public ColorSelection(Blokus main) {
		setLayout(null);
	
		JLabel top_label = new JLabel("COLOR SELECTION");
		top_label.setFont(new Font("Tahoma", Font.BOLD, 15));
		top_label.setBounds(141, 11, 156, 53);
		add(top_label);
		
		
		// Making Color panels for the players to select color
		JButton yellow_color = new JButton("YELLOW");
		yellow_color.setBackground(Color.YELLOW);
		yellow_color.setBorderPainted(false);
		yellow_color.setOpaque(true);
		yellow_color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setColor(Color.YELLOW,yellow_color);
			}
		});
		yellow_color.setFont(new Font("Tahoma", Font.BOLD, 12));
		yellow_color.setBounds(121, 121, 89, 38);
		add(yellow_color);
		
		JButton blue_color = new JButton("Blue");
		blue_color.setForeground(Color.WHITE);
		blue_color.setBackground(Color.BLUE);
		blue_color.setFont(new Font("Tahoma", Font.BOLD, 12));
		blue_color.setBounds(208, 121, 89, 38);
		blue_color.setBorderPainted(false);
		blue_color.setOpaque(true);
		blue_color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setColor(Color.BLUE,blue_color);
			}
		});
		add(blue_color);
		
		JButton green_color = new JButton("Green");
		green_color.setBackground(Color.GREEN);
		green_color.setBorderPainted(false);
		green_color.setOpaque(true);
		green_color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setColor(Color.GREEN,green_color);
			}
		});
		green_color.setFont(new Font("Tahoma", Font.BOLD, 12));
		green_color.setBounds(121, 159, 89, 38);
		add(green_color);
		
		JButton red_color = new JButton("Red");
		red_color.setBackground(Color.RED);
		red_color.setBorderPainted(false);
		red_color.setOpaque(true);
		red_color.setFont(new Font("Tahoma", Font.BOLD, 12));
		red_color.setBounds(208, 159, 89, 38);
		red_color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setColor(Color.RED,red_color);
			}
		});
		add(red_color);
		
		// Finished with color panels
		
		
		// Making Back panel and Start panel
		JPanel back_panel = new JPanel();
		back_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				main.setObjectAtIndexRoute(0);
				main.setRoute("init");
				yellow_color.setVisible(true);
				red_color.setVisible(true);
				blue_color.setVisible(true);
				green_color.setVisible(true);
				playerColor=0;
			}
		});
		back_panel.setBackground(Color.RED);
		back_panel.setBounds(10, 261, 59, 28);
		add(back_panel);
		
		JLabel back_label = new JLabel("BACK");
		back_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				main.setObjectAtIndexRoute(0);
				main.setRoute("init");
				yellow_color.setVisible(true);
				red_color.setVisible(true);
				blue_color.setVisible(true);
				green_color.setVisible(true);
				playerColor=0;
			}
		});
		back_label.setBackground(Color.RED);
		back_label.setFont(new Font("Tahoma", Font.BOLD, 12));
		back_panel.add(back_label);
		
		JPanel start_panel = new JPanel();
		start_panel.setBackground(Color.GREEN);
		start_panel.setBounds(381, 261, 59, 28);
		add(start_panel);
		start_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (playerColor==numPlayers){
				setColorAI();
				main.getMain_panel().removeAll();
				Board thisGameBoard=new Board(color_array, difficulty, numPlayers, main,false,null,null,null,null);
				main.add(thisGameBoard);
				main.revalidate();
				main.repaint();
			}}
		});
		
		JLabel start_lbl = new JLabel("START");
		start_lbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (playerColor==numPlayers){
				setColorAI();
				main.getMain_panel().removeAll();
				 // We are setting up a new game
				main.add(new Board(color_array, difficulty, numPlayers, main,false,null,null,null,null));
				main.revalidate();
				main.repaint();
			}}
		});
		start_lbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		start_panel.add(start_lbl);
		
		

	}
}
