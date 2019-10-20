package blokus;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;

public class board extends JPanel {

	/**
	 * Create the panel.
	 * 
	 */
	private shape shapeList=new shape(7,3);
	private shape shapeList1=new shape(7,3);
	
	public board() {
		this.setSize(1800, 980);
		
		
		GridLayout gridLayout_1 = (GridLayout) shapeList.getLayout();
		gridLayout_1.setVgap(3);
		gridLayout_1.setHgap(3);
		shapeList.setBounds(0,-55,170,737);
		shapeList.setBounds(0,0,170,703);
		setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(1449, 125, 170, 726);
		panel_2.setLayout(null);
		add(panel_2);
		GridLayout gridLayout_2 = (GridLayout) shapeList1.getLayout();
		gridLayout_2.setHgap(3);
		gridLayout_2.setVgap(3);
		shapeList1.setBounds(0, 0, 170, 703);
		panel_2.add(shapeList1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(241, 42, 1198, 160);
		add(panel_3);
		panel_3.setLayout(null);
		
		shape shape__2 = new shape(3, 7);
		shape__2.setBounds(20, 5, 1161, 137);
		GridLayout gridLayout_3 = (GridLayout) shape__2.getLayout();
		gridLayout_3.setVgap(3);
		gridLayout_3.setHgap(3);
		panel_3.add(shape__2);
		

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(1629, 219, 160, 485);
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
				shapeList1.rotateC();
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
		panel.setBounds(52, 114, 179, 737);
		panel.setLayout(null);
		panel.add(shapeList);	
		add(panel);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(241, 209, 1198, 564);
		add(panel_5);
		panel_5.setLayout(null);
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.setBounds(1668, 11, 89, 42);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("HINT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(1541, 11, 89, 42);
		add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("BLOKUS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(80, 0, 81, 53);
		add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(241, 822, 1189, 147);
		add(panel_1);
		panel_1.setLayout(null);
		
		shape shape_ = new shape(3, 7);
		GridLayout gridLayout = (GridLayout) shape_.getLayout();
		gridLayout.setHgap(3);
		gridLayout.setVgap(3);
		shape_.setBounds(14,5,1161,137);
		panel_1.add(shape_);
	}
}
