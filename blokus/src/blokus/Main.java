package blokus;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class Main extends JFrame {

	private JPanel contentPane;
	private init initial;
	private color colorClass;
	private board Board;
	private JPanel[] routes= {new init(this),new color(this),new JPanel(),new board()};
	private String route="board";
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		initial=(blokus.init) routes[0];
		initial.setVisible(false);
		colorClass=(color) routes[1];
		colorClass.setVisible(false);
		initial.setBounds(0,0,460,310);
		colorClass.setBounds(0,0,460,310);
	    Board=(blokus.board) routes[3];
		Board.setBounds(0,0,1280,720);
		Board.setVisible(true);
		
		
		
		
		System.setProperty("awt.useSystemAAFontSettings","on");
		System.setProperty("swing.aatext", "true");
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 800);
		setResizable(false);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		JPanel land_panel = routes[2];
		land_panel.setBounds(0, 0, 485, 343);
		contentPane.add(land_panel);
		land_panel.setLayout(null);
		land_panel.setVisible(false);
		contentPane.add((initial));
		contentPane.add((colorClass));
		contentPane.add((land_panel));
		contentPane.add(Board);
		
		JLabel lblNewLabel = new JLabel("Blokus");
		lblNewLabel.setBounds(211, 70, 57, 24);
		land_panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JLabel lblNewLabel_1 = new JLabel("Start");
		lblNewLabel_1.setBackground(Color.GREEN);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(199, 194, 82, 30);
		land_panel.add(lblNewLabel_1);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setRoute("init");
			}
		});
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	}
}
