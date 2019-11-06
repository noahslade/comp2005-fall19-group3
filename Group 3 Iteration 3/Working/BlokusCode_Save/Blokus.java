
import image.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;


public class Blokus extends JFrame {

	private JPanel Main_panel;
	private JPanel land_panel;
	private Init initiate_panel;
	private ColorSelection color_panel;
	private Board Board_panel;
	private JPanel[] routes= {new Init(this),new ColorSelection(this),new JPanel(),new Board()};
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Blokus frame = new Blokus();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setRoute(String route) {
		if(route=="init") {
			routes[0].setVisible(true);
			routes[1].setVisible(false);
			routes[2].setVisible(false);
			routes[3].setVisible(false);
		}
		else if(route=="color") {
			routes[0].setVisible(false);
			routes[1].setVisible(true);
			routes[2].setVisible(false);
			routes[3].setVisible(false);
		}
		else if(route=="board") {
			routes[0].setVisible(false);
			routes[1].setVisible(false);
			routes[2].setVisible(false);
			routes[3].setVisible(true);
			
			
		}
	}

	/**
	 * Create the frame.
	 */
	public Blokus() {
		
		// Creating main panel of the frame which is going to contain all the other panels
		Main_panel = new JPanel();
		Main_panel.setLayout(new BorderLayout());
		Main_panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		//All the other panels of the project as of now
		initiate_panel=(Init) routes[0];
		initiate_panel.setVisible(false);
		color_panel=(ColorSelection) routes[1];
		color_panel.setVisible(false);
		initiate_panel.setBounds(400,250,460,310);
		initiate_panel.setBackground(new Color (86, 140, 48));
		color_panel.setBounds(385,250,460,310);
		color_panel.setBackground(new Color (86, 140, 48));
	    Board_panel=(Board) routes[3];
		Board_panel.setBounds(0,0,1223,980);
		Board_panel.setVisible(false);
		
		
		
		
		
		System.setProperty("awt.useSystemAAFontSettings","on");
		System.setProperty("swing.aatext", "true");
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 1020);
		setResizable(false);
		setContentPane(Main_panel);
		setLocationRelativeTo(null);
		Main_panel.setLayout(null);

		// Main Screen panel which contains logo blokus and start a game button
		land_panel = routes[2];
		land_panel.setBounds(0, 0, 800, 600);
		land_panel.setLayout(null);
		land_panel.setVisible(true);
		land_panel.setBackground(new Color (175, 217, 85));
		

		//Adding all the other panels of the project to the main panel of the frame 
		Main_panel.add(initiate_panel);
		Main_panel.add(color_panel);
		Main_panel.add(land_panel);
		Main_panel.add(Board_panel);
		
		JLabel blokus_label = new JLabel("Blokus");
		blokus_label.setBounds(550, 70, 185, 45);
		land_panel.add(blokus_label);
		blokus_label.setFont(new Font("Tahoma", Font.BOLD, 55));
		
		JButton start_button = new JButton("Start");
		start_button.setBackground(Color.GREEN);
		start_button.setHorizontalAlignment(SwingConstants.CENTER);
		start_button.setBounds(590, 300, 100, 50);
		land_panel.add(start_button);
		start_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRoute("init");
			}
		});
		start_button.setFont(new Font("Tahoma", Font.BOLD, 25));

		Main_panel.setBackground(new Color (175, 217, 85));
	}
}
