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

public class Init extends JPanel {

	/**
	 * Create the panel.
	 * @param main 
	 */
	public Init(Blokus main) {
		setLayout(null);
		
		JLabel num_player = new JLabel("No. of Player's");
		num_player.setFont(new Font("Times New Roman", Font.BOLD, 15));
		num_player.setBounds(10, 38, 105, 31);
		add(num_player);
		
		JLabel num_cpu = new JLabel("No. of CPU's");
		num_cpu.setFont(new Font("Times New Roman", Font.BOLD, 15));
		num_cpu.setBounds(10, 95, 105, 14);
		add(num_cpu);
		
		JLabel lblDifficulty = new JLabel("Difficulty Level");
		lblDifficulty.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDifficulty.setBounds(10, 151, 105, 14);
		add(lblDifficulty);
		
		JLabel lblRandomColor = new JLabel("Random Color");
		lblRandomColor.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblRandomColor.setBounds(10, 218, 105, 14);
		add(lblRandomColor);
		
		JPanel next = new JPanel();
		next.setBackground(Color.GREEN);
		next.setBounds(370, 258, 70, 31);
		add(next);
		next.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.setRoute("color");
			}
		});
		
		JLabel next_label = new JLabel("NEXT");
		next_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.setRoute("color");
			}
		});
		next_label.setFont(new Font("Times New Roman", Font.BOLD, 15));
		next.add(next_label);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 12));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"0","1", "2", "3"}));
		comboBox.setBounds(148, 95, 41, 22);
		add(comboBox);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4"}));
		comboBox_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		comboBox_1.setBounds(148, 41, 41, 22);
		add(comboBox_1);
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		comboBox_2.setModel(new DefaultComboBoxModel<String>(new String[] {"Easy", "Medium", "Hard"}));
		comboBox_2.setBounds(148, 151, 61, 22);
		add(comboBox_2);
		
		JComboBox<String> comboBox_3 = new JComboBox<String>();
		comboBox_3.setModel(new DefaultComboBoxModel<String>(new String[] {"Yes", "No"}));
		comboBox_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		comboBox_3.setBounds(148, 218, 61, 22);
		add(comboBox_3);

	}
}
