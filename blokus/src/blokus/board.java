package blokus;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class board extends JPanel {

	/**
	 * Create the panel.
	 * 
	 */
	private shape shapeList=new shape();
	
	public board() {
		shapeList.setBounds(10,0,160,645);
		this.setSize(1280, 720);
		setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(221, 583, 686, 126);
		shape shape_ = new shape();
		panel_1.add(shape_);
		shape_.setLayout(null);
		add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(917, 64, 170, 645);
		add(panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(221, 0, 686, 126);
		add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(1110, 64, 160, 485);
		add(panel_4);
		panel_4.setLayout(null);
		
		JButton btnNewButton_2 = new JButton();
		btnNewButton_2.setIcon(new ImageIcon(getClass().getResource("image/counterclockwise.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeList.rotateCC();
			}
		});
		btnNewButton_2.setBounds(40, 11, 89, 68);
		panel_4.add(btnNewButton_2);
		
		JButton btnCc = new JButton();
		btnCc.setIcon(new ImageIcon(getClass().getResource("image/clockwise.png")));
		btnCc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeList.rotateC();
			}
		});
		btnCc.setBounds(40, 122, 89, 68);
		panel_4.add(btnCc);
		
		JButton btnFv = new JButton();
		btnFv.setIcon(new ImageIcon(getClass().getResource("image/vertical.png")));
		btnFv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeList.flipVer();
			}
		});
		btnFv.setBounds(40, 236, 89, 68);
		panel_4.add(btnFv);
		
		JButton btnFh = new JButton();
		btnFh.setIcon(new ImageIcon(getClass().getResource("image/switch_horizontal.png")));
		btnFh.setBounds(40, 354, 89, 68);
		btnFh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeList.flipHor();
			}
		});
		panel_4.add(btnFh);
		
		JPanel panel = new JPanel();
		panel.setBounds(41, 64, 170, 645);
		panel.setLayout(null);
		panel.add(shapeList);	
		add(panel);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(221, 137, 686, 435);
		add(panel_5);
		panel_5.setLayout(null);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.setBounds(1155, 11, 89, 42);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("HINT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(958, 11, 89, 42);
		add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("BLOKUS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(80, 0, 81, 53);
		add(lblNewLabel);

	}
}
