import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColorSelection extends JPanel {

	/**
	 * Create the panel.
	 */
	public ColorSelection(Blokus main) {
		setLayout(null);
		System.out.print("workingColor");
		JLabel top_label = new JLabel("COLOR SELECTION");
		top_label.setFont(new Font("Times New Roman", Font.BOLD, 15));
		top_label.setBounds(141, 11, 156, 53);
		add(top_label);
		
		
		// Making Color panels for the players to select color
		JButton yellow_color = new JButton("Yellow");
		yellow_color.setBackground(Color.YELLOW);
		yellow_color.setBorderPainted(false);
		yellow_color.setOpaque(true);
		yellow_color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		add(blue_color);
		
		JButton green_color = new JButton("Green");
		green_color.setBackground(Color.GREEN);
		green_color.setBorderPainted(false);
		green_color.setOpaque(true);
		green_color.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		add(red_color);
		
		// Finished with color panels
		
		
		// Making Back panel and Start panel
		JPanel back_panel = new JPanel();
		back_panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.setRoute("init");
			}
			
		});
		back_panel.setBackground(Color.RED);
		back_panel.setBounds(10, 261, 59, 28);
		add(back_panel);
		
		JLabel back_label = new JLabel("BACK");
		back_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.setRoute("init");
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
				main.setRoute("board");
			}
		});
		
		JLabel start_lbl = new JLabel("START");
		start_lbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.setRoute("board");
			}
		});
		start_lbl.setFont(new Font("Tahoma", Font.BOLD, 12));
		start_panel.add(start_lbl);
		
		

	}
}
