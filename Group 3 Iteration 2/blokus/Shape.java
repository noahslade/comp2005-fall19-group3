import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Shape extends JPanel {
	private Board currentBoard;
	
	
	
	private customShapeButton[][][] shapeList=new customShapeButton[21][5][5];
	
	private int currentSelected=0;

	/**
	 * Create the panel.
	 */
	private int[][][] shapesCoordinates = {
			{{0, 0}},{{-1, 0}, {0, 0}, {0, 1}}, 
			{{-1, 0}, {0, 0}, {1, 0}, {1, 1}},
			{{-1, 0}, {0, 0}, {1, 0}, {0, -1}}, 
			{{0, 0}, {1, 0}, {0, -1}, {1, -1}},
			{{-1, 1}, {-1, 0}, {0, 0}, {0, -1}},
			{{-2, 0}, {-1, 0}, {0, 0}, {1, 0}},
			{{-2, 0}, {-1, 0}, {0, 0}, {0, -1}, {0, -2}},

			{{-2, 0}, {-1, 0}, {0, 0}, {0, -1}, {0, 1}},
			
			{{-2, 0}, {-1, 0}, {0, 0}, {1, 0}, {1, 1}},
			{{-1, 1}, {-1, 0}, {0, 0}, {0, -1}, {0, -2}},
			{{-1, 0}, {0, 0}, {-1, -1}, {0, -1}, {0, -2}},
			
			{{-1, 1}, {0, 1}, {0, 0}, {0, -1}, {1, -1}},
			{{-1, 1}, {-1, 0}, {0, 0}, {0, -1}, {1, -1}},
			
			{{-1, 1}, {0, 1}, {0, 0}, {1, 0}, {0, -1}},
			{{-1, 0}, {1, 0}, {0, 0}, {0, 1}, {0, -1}},
			{{1, -1}, {0, -1}, {1, 1}, {0, 1}, {0, 0}},//
			{{-1, 0}, {0, -1}, {0, 0}, {1, 0}, {2, 0}},
			{{-2, 0}, {-1, 0}, {0, 0}, {1, 0}, {2, 0}},
		    {{0, 0}, {1, 0}},{{-1, 0}, {0, 0}, {1, 0}}
			};
	private int[][] action=shapesCoordinates[0];
	
	
	private void hideShapes()
	{
		for (int i=0;i<21;i++)
		for(int j=0;j<5;j++)
			for(int k=0;k<5;k++) {
				shapeList[i][j][k].setVisible(false);
				shapeList[i][j][k].setOne(0);
			}
	}
	private void rotateCoordinatesCW() {
		rotateCoordinatesCCW();
		for (int i=0;i<action.length;i++){
				action[i][0]=-action[i][0];
				action[i][1]=-action[i][1];
			}
			
	}
	
	private void rotateCoordinatesCCW() {
		for (int i=0;i<action.length;i++) {
			if(action[i][0]==0) {
				if(action[i][1]==0) {
					action[i][0]=0;
					action[i][1]=0;
				}
				else if(action[i][1]==1) {
					action[i][0]=-1;
					action[i][1]=0;
				}else if(action[i][1]==-1) {
					action[i][0]=1;
					action[i][1]=0;
				}else if(action[i][1]==2) {
					action[i][0]=-2;
					action[i][1]=0;
				}else if(action[i][1]==-2) {
					action[i][0]=2;
					action[i][1]=0;
				}
			}else if(action[i][0]==1) {
				if(action[i][1]==0) {
					action[i][0]=0;
					action[i][1]=1;
				}else if(action[i][1]==1) {
					action[i][0]=-1;
					action[i][1]=1;
				}else if(action[i][1]==-1) {
					action[i][0]=1;
					action[i][1]=1;
				}else if(action[i][1]==2) {
					action[i][0]=1;
					action[i][1]=2;
				}else if(action[i][1]==-2) {
					action[i][0]=1;
					action[i][1]=2;
				}
			}else if(action[i][0]==-1) {
				if(action[i][1]==0) {
					action[i][0]=0;
					action[i][1]=-1;
				}else if(action[i][1]==1) {
					action[i][0]=-1;
					action[i][1]=-1;
				}else if(action[i][1]==-1) {
					action[i][0]=1;
					action[i][1]=-1;
				}else if(action[i][1]==2) {
					action[i][0]=-1;
					action[i][1]=-2;
				}else if(action[i][1]==-2) {
					action[i][0]=1;
					action[i][1]=-2;
				}
				//TODO
			}else if(action[i][0]==2) {
				if(action[i][1]==0) {
					action[i][0]=0;
					action[i][1]=2;
				}else if(action[i][1]==1) {
					action[i][0]=-2;
					action[i][1]=1;
				}else if(action[i][1]==-1) {
					action[i][0]=2;
					action[i][1]=1;
				}else if(action[i][1]==2) {
					action[i][0]=-2;
					action[i][1]=2;
				}else if(action[i][1]==-2) {
					action[i][0]=2;
					action[i][1]=2;
				}
				//TODO
			}else if(action[i][0]==-2) {
				if(action[i][1]==0) {
					action[i][0]=0;
					action[i][1]=-2;
				}else if(action[i][1]==1) {
					action[i][0]=-2;
					action[i][1]=-1;
				}else if(action[i][1]==-1) {
					action[i][0]=2;
					action[i][1]=-1;
				}else if(action[i][1]==2) {
					action[i][0]=-2;
					action[i][1]=-2;
				}else if(action[i][1]==-2) {
					action[i][0]=2;
					action[i][1]=-2;
				}
				
			}
		}
	}
	public void rotateC() {
		rotateCoordinatesCW();
		hideShapes();
		drawShapes();
	}
	public void rotateCC() {
		rotateCoordinatesCCW();
		hideShapes();
		drawShapes();
	}
	private void fliph() {
		for (int i=0;i<action.length;i++){
			action[i][1]=-action[i][1];
		}
		
	}
	private void flipv() {
		for (int i=0;i<action.length;i++){
			action[i][0]=-action[i][0];
		}
	}
	public void flipHor() {
		fliph();
		hideShapes();
		drawShapes();
	}
	public void flipVer() {
		flipv();
		hideShapes();
		drawShapes();
	}
	
	private void drawShapes() {
		for (int i=0;i<21;i++)
			for(int j=0;j<5;j++)
				for(int k=0;k<5;k++) {
					if(j==2 && k==2) {
						for(int x=0;x<shapesCoordinates[i].length;x++) {
						shapeList[i][shapesCoordinates[i][x][0]+j][shapesCoordinates[i][x][1]+k].setVisible(true);
						shapeList[i][shapesCoordinates[i][x][0]+j][shapesCoordinates[i][x][1]+k].setOne(1);}
						
						}
					shapeList[i][j][k].shapeSize=shapesCoordinates[i].length;
					}
				}
	public Shape(int x, int y) {
		setLayout(new GridLayout(x,y));
		for (int i=0;i<21;i++) {
			JPanel panel = new JPanel();
			panel.setBackground(Color.white);
			panel.setLayout(new GridLayout(5,5));
			panel.setBounds(0, 0, 50, 50);
			for(int j=0;j<5;j++)
				for(int k=0;k<5;k++) {
					customShapeButton button=new customShapeButton();
					shapeList[i][j][k]=button;
					button.setBounds(0,0,1,3);
					panel.add(button);
					button.setIndex(j,k);
					button.setActionIndex(i);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							customShapeButton thisButton=((customShapeButton) e.getSource());
							action=shapesCoordinates[thisButton.getActionIndex()];
							currentSelected=thisButton.getActionIndex();
							currentBoard.setActions(action);
						}
					});
				}
			add(panel);
		}
		hideShapes();
		drawShapes();
	}
	public void setBoard(Board currentBoard) {
				this.currentBoard=currentBoard;
	}

}
