package blokus;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class color extends JPanel {
	private init initial;

	/**
	 * Create the panel.
	 */
	public color(Main main) {
		setLayout(null);
		System.out.print("workingColor");
		JLabel lblNewLabel = new JLabel("COLOR SELECTION");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(141, 11, 156, 53);
		add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Blue");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.BLUE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_1.setBounds(208, 121, 89, 38);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setOpaque(true);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Green");
		btnNewButton_2.setBackground(Color.GREEN);
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setOpaque(true);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_2.setBounds(121, 159, 89, 38);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Red");
		btnNewButton_3.setBackground(Color.RED);
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.setOpaque(true);
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_3.setBounds(208, 159, 89, 38);
		add(btnNewButton_3);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.setRoute("init");
			}
			
		});
		panel.setBackground(Color.RED);
		panel.setBounds(10, 261, 59, 28);
		add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("BACK");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.setRoute("init");
			}
		});
		lblNewLabel_1.setBackground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GREEN);
		panel_1.setBounds(381, 261, 59, 28);
		add(panel_1);
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.setRoute("board");
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("START");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.setRoute("board");
			}
		});
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Yellow");
		btnNewButton.setBackground(Color.YELLOW);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setOpaque(true);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(121, 121, 89, 38);
		add(btnNewButton);

	}
}
