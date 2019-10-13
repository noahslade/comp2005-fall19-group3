package blokus;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class init extends JPanel {
	private color color_panel;

	/**
	 * Create the panel.
	 * @param main 
	 */
	public init(Main main) {
		setLayout(null);
		//color_panel = new color();
		//color_panel.setBounds(0,0,450,300);
		
		JLabel lblNewLabel = new JLabel("No. of Player's");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 38, 105, 31);
		add(lblNewLabel);
		
		JLabel lblNoOfCpus = new JLabel("No. of CPU's");
		lblNoOfCpus.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNoOfCpus.setBounds(10, 95, 105, 14);
		add(lblNoOfCpus);
		
		JLabel lblDifficulty = new JLabel("Difficulty Level");
		lblDifficulty.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDifficulty.setBounds(10, 151, 105, 14);
		add(lblDifficulty);
		
		JLabel lblRandomColor = new JLabel("Random Color");
		lblRandomColor.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblRandomColor.setBounds(10, 218, 105, 14);
		add(lblRandomColor);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(370, 258, 70, 31);
		add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("NEXT");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.setRoute("color");
				System.out.print("workinginit");
			}
		});
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		comboBox.setBounds(148, 95, 41, 22);
		add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		comboBox_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		comboBox_1.setBounds(148, 41, 41, 22);
		add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Easy", "Medium", "Hard", ""}));
		comboBox_2.setBounds(148, 151, 61, 22);
		add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Yes", "No"}));
		comboBox_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		comboBox_3.setBounds(148, 218, 61, 22);
		add(comboBox_3);

	}
}
