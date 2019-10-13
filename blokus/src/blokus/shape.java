package blokus;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class shape extends JPanel {
	private JButton[][][] shapeList=new JButton[21][5][5];

	/**
	 * Create the panel.
	 */
	private int[][][] shapesCoordinates = {
			{{0, 0}},

			{{0, 0}, {1, 0}},

			{{-1, 0}, {0, 0}, {1, 0}},
			{{-1, 0}, {0, 0}, {0, 1}},

			{{-2, 0}, {-1, 0}, {0, 0}, {1, 0}}, 
			{{-1, 0}, {0, 0}, {1, 0}, {1, 1}},
			{{-1, 0}, {0, 0}, {1, 0}, {0, -1}}, 
			{{0, 0}, {1, 0}, {0, -1}, {1, -1}},
			{{-1, 1}, {-1, 0}, {0, 0}, {0, -1}},

			{{-2, 0}, {-1, 0}, {0, 0}, {1, 0}, {2, 0}},
			{{-2, 0}, {-1, 0}, {0, 0}, {1, 0}, {1, 1}},
			{{-1, 1}, {-1, 0}, {0, 0}, {0, -1}, {0, -2}},
			{{-1, 0}, {0, 0}, {-1, -1}, {0, -1}, {0, -2}},
			{{0, -2}, {-1, -2}, {-1, -1}, {-1, 0}, {0, 0}},
			{{-1, 1}, {0, 1}, {0, 0}, {0, -1}, {1, -1}},
			{{-1, 1}, {-1, 0}, {0, 0}, {0, -1}, {1, -1}},
			{{-2, 0}, {-1, 0}, {0, 0}, {0, -1}, {0, -2}},
			{{-2, 0}, {-1, 0}, {0, 0}, {0, -1}, {0, 1}},
			{{-1, 1}, {0, 1}, {0, 0}, {1, 0}, {0, -1}},
			{{-1, 0}, {1, 0}, {0, 0}, {0, 1}, {0, -1}},
			{{-1, 0}, {0, -1}, {0, 0}, {1, 0}, {2, 0}}
			};
	
	
	private void hideShapes()
	{
		for (int i=0;i<21;i++)
		for(int j=0;j<5;j++)
			for(int k=0;k<5;k++) {
				shapeList[i][j][k].setVisible(false);
			}
	}	
	
	private void drawShapes() {
		for (int i=0;i<21;i++)
			for(int j=0;j<5;j++)
				for(int k=0;k<5;k++) {
					if(j==2 && k==2) {
						for(int x=0;x<shapesCoordinates[i].length;x++)
						shapeList[i][shapesCoordinates[i][x][0]+j][shapesCoordinates[i][x][1]+k].setVisible(true);
						}
					}
					
				}
	public shape() {
		setLayout(null);
		setLayout(new GridLayout(7,3));
		for (int i=0;i<21;i++) {
			JPanel panel = new JPanel();
			panel.setBackground(Color.black);
			panel.setLayout(new GridLayout(5,5));
			panel.setBounds(0, 0, 10, 10);
			for(int j=0;j<5;j++)
				for(int k=0;k<5;k++) {
					JButton button=new JButton();
					shapeList[i][j][k]=button;
					button.setBounds(0,0,10,10);
					panel.add(button);
				}
			add(panel);
		}
		hideShapes();
		drawShapes();
	}

}
