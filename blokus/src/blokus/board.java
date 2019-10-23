package blokus;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class board extends JPanel {
	
	private class boardButton extends JButton{
		private int x,y;
		private boolean isTaken=false;
		
		
		public boolean isTaken() {
			return isTaken;
		}
		public void setTaken(boolean isTaken) {
			this.isTaken = isTaken;
		}
		public int[] getIndex() {
			int[] index= {this.x,this.y};
			return index;
		}
		boardButton(int x,int y){
			this.x=x;
			this.y=y;
		}
	}

	/**
	 * Create the panel.
	 * 
	 */
	private shape shapeList=new shape(7,3);
	private int GridSize=16;
	private boardButton[][] board_button=new boardButton[GridSize][GridSize];
	private int[][] actions= {{0,0}};
	private JButton thisButton;
	
	
	
	
	
	public void setActions(int[][] actions) {
		System.out.println("Setting aaction");
		this.actions=actions;
	}
	
	public board() {
		this.setSize(1223, 980);
		setLayout(null);
		

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(1036, 219, 160, 485);
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
		btnFh.setBounds(40, 356, 89, 68);
		btnFh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shapeList.flipHor();
			}
		});
		panel_4.add(btnFh);
		
		
		JPanel GAME_BOARD = new JPanel();
		GAME_BOARD.setBounds(228, 218, 580, 580);
		add(GAME_BOARD);
		GAME_BOARD.setLayout(new GridLayout(GridSize,GridSize));
		
		for(int i=0;i<GridSize;i++) {
			for(int j=0;j<GridSize;j++) {
				board_button[i][j] =new boardButton(i,j);
				thisButton=board_button[i][j];
				thisButton.setBackground(Color.white);
				
				thisButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						try {
							int x=((boardButton)e.getSource()).getIndex()[0];
							int y=((boardButton)e.getSource()).getIndex()[1];
							System.out.println(Arrays.deepToString(actions));
							for (int i=0;i<actions.length;i++) {
								board_button[x+actions[i][0]][y+actions[i][1]].setBackground(Color.green);
							}
						}catch(Exception s) {}
							
						
					}
					@Override
					public void mouseExited(MouseEvent e) {
						try {
							int x=((boardButton)e.getSource()).getIndex()[0];
							int y=((boardButton)e.getSource()).getIndex()[1];
							System.out.println(Arrays.deepToString(actions));
							for (int i=0;i<actions.length;i++) {
								if(!board_button[x+actions[i][0]][y+actions[i][1]].isTaken())
									board_button[x+actions[i][0]][y+actions[i][1]].setBackground(Color.white);
							}
						}catch(Exception s) {}
					}
					@Override
					public void mouseClicked(MouseEvent e) {
						try {
							int x=((boardButton)e.getSource()).getIndex()[0];
							int y=((boardButton)e.getSource()).getIndex()[1];
							for (int i=0;i<actions.length;i++) {
								if(!board_button[x+actions[i][0]][y+actions[i][1]].isTaken) {
								board_button[x+actions[i][0]][y+actions[i][1]].setBackground(Color.green);
								board_button[x+actions[i][0]][y+actions[i][1]].setTaken(true);}
							}
						}catch(Exception s) {}
					}
				});
				
				GAME_BOARD.add(board_button[i][j]);
			}
		}
		
		JButton btnNewButton = new JButton("SAVE");
		btnNewButton.setBounds(1062, 11, 89, 42);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("HINT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(909, 11, 89, 42);
		add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("BLOKUS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(38, 11, 81, 53);
		add(lblNewLabel);
		
		shape shape_ = new shape(3, 7);
		shape_.setBounds(310, 809, 420, 160);
		add(shape_);
		GridLayout gridLayout = (GridLayout) shape_.getLayout();
		gridLayout.setHgap(3);
		gridLayout.setVgap(3);
		
		shape shape__2 = new shape(3, 7);
		shape__2.setBounds(310, 27, 420, 180);
		add(shape__2);
		GridLayout gridLayout_3 = (GridLayout) shape__2.getLayout();
		gridLayout_3.setVgap(3);
		gridLayout_3.setHgap(3);
		
		
		
		
		GridLayout gridLayout_1 = (GridLayout) shapeList.getLayout();
		gridLayout_1.setVgap(3);
		gridLayout_1.setHgap(3);
		shapeList.setBounds(38, 284, 180, 420);
		add(shapeList);
		shapeList.setBoard(this);
		
		shape shape__1 = new shape(7, 3);
		GridLayout gridLayout_2 = (GridLayout) shape__1.getLayout();
		gridLayout_2.setVgap(3);
		gridLayout_2.setHgap(3);
		shape__1.setBounds(818, 284, 180, 420);
		add(shape__1);
	}
}
