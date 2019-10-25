import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Shape extends JPanel {
	private Board currentBoard;
	
	
	
	private CustomShapeButton[][][] shapeList=new CustomShapeButton[21][5][5];
	
	private int currentSelected=0;
	private Color color;

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
			{{1, -1}, {0, -1}, {1, 1}, {0, 1}, {0, 0}},
			{{-1, 0}, {0, -1}, {0, 0}, {1, 0}, {2, 0}},
			{{-2, 0}, {-1, 0}, {0, 0}, {1, 0}, {2, 0}},
		    {{0, 0}, {1, 0}},{{-1, 0}, {0, 0}, {1, 0}}
			};
	private int[][] selectedShapeCord=shapesCoordinates[0];
	
	
	private void rotateCoordinatesCW() {
		rotateCoordinatesCCW();
		for (int i=0;i<selectedShapeCord.length;i++){
				selectedShapeCord[i][0]=-selectedShapeCord[i][0];
				selectedShapeCord[i][1]=-selectedShapeCord[i][1];
			}
			
	}
	
	private void rotateCoordinatesCCW() {
		//Taken from StackOverflow
		for (int i=0;i<selectedShapeCord.length;i++) {
			if(selectedShapeCord[i][0]==0) {
				if(selectedShapeCord[i][1]==0) {
					selectedShapeCord[i][0]=0;
					selectedShapeCord[i][1]=0;
				}
				else if(selectedShapeCord[i][1]==1) {
					selectedShapeCord[i][0]=-1;
					selectedShapeCord[i][1]=0;
				}else if(selectedShapeCord[i][1]==-1) {
					selectedShapeCord[i][0]=1;
					selectedShapeCord[i][1]=0;
				}else if(selectedShapeCord[i][1]==2) {
					selectedShapeCord[i][0]=-2;
					selectedShapeCord[i][1]=0;
				}else if(selectedShapeCord[i][1]==-2) {
					selectedShapeCord[i][0]=2;
					selectedShapeCord[i][1]=0;
				}
			}else if(selectedShapeCord[i][0]==1) {
				if(selectedShapeCord[i][1]==0) {
					selectedShapeCord[i][0]=0;
					selectedShapeCord[i][1]=1;
				}else if(selectedShapeCord[i][1]==1) {
					selectedShapeCord[i][0]=-1;
					selectedShapeCord[i][1]=1;
				}else if(selectedShapeCord[i][1]==-1) {
					selectedShapeCord[i][0]=1;
					selectedShapeCord[i][1]=1;
				}else if(selectedShapeCord[i][1]==2) {
					selectedShapeCord[i][0]=1;
					selectedShapeCord[i][1]=2;
				}else if(selectedShapeCord[i][1]==-2) {
					selectedShapeCord[i][0]=1;
					selectedShapeCord[i][1]=2;
				}
			}else if(selectedShapeCord[i][0]==-1) {
				if(selectedShapeCord[i][1]==0) {
					selectedShapeCord[i][0]=0;
					selectedShapeCord[i][1]=-1;
				}else if(selectedShapeCord[i][1]==1) {
					selectedShapeCord[i][0]=-1;
					selectedShapeCord[i][1]=-1;
				}else if(selectedShapeCord[i][1]==-1) {
					selectedShapeCord[i][0]=1;
					selectedShapeCord[i][1]=-1;
				}else if(selectedShapeCord[i][1]==2) {
					selectedShapeCord[i][0]=-1;
					selectedShapeCord[i][1]=-2;
				}else if(selectedShapeCord[i][1]==-2) {
					selectedShapeCord[i][0]=1;
					selectedShapeCord[i][1]=-2;
				}
			}else if(selectedShapeCord[i][0]==2) {
				if(selectedShapeCord[i][1]==0) {
					selectedShapeCord[i][0]=0;
					selectedShapeCord[i][1]=2;
				}else if(selectedShapeCord[i][1]==1) {
					selectedShapeCord[i][0]=-2;
					selectedShapeCord[i][1]=1;
				}else if(selectedShapeCord[i][1]==-1) {
					selectedShapeCord[i][0]=2;
					selectedShapeCord[i][1]=1;
				}else if(selectedShapeCord[i][1]==2) {
					selectedShapeCord[i][0]=-2;
					selectedShapeCord[i][1]=2;
				}else if(selectedShapeCord[i][1]==-2) {
					selectedShapeCord[i][0]=2;
					selectedShapeCord[i][1]=2;
				}
			}else if(selectedShapeCord[i][0]==-2) {
				if(selectedShapeCord[i][1]==0) {
					selectedShapeCord[i][0]=0;
					selectedShapeCord[i][1]=-2;
				}else if(selectedShapeCord[i][1]==1) {
					selectedShapeCord[i][0]=-2;
					selectedShapeCord[i][1]=-1;
				}else if(selectedShapeCord[i][1]==-1) {
					selectedShapeCord[i][0]=2;
					selectedShapeCord[i][1]=-1;
				}else if(selectedShapeCord[i][1]==2) {
					selectedShapeCord[i][0]=-2;
					selectedShapeCord[i][1]=-2;
				}else if(selectedShapeCord[i][1]==-2) {
					selectedShapeCord[i][0]=2;
					selectedShapeCord[i][1]=-2;
				}
				
			}
		}
	}

	private void flipv() {
		for (int i=0;i<selectedShapeCord.length;i++){
			selectedShapeCord[i][0]=-selectedShapeCord[i][0];
		}
	}

	private void fliph() {
		for (int i=0;i<selectedShapeCord.length;i++){
			selectedShapeCord[i][1]=-selectedShapeCord[i][1];
		}
		
	}

	public void rotateC() {
		rotateCoordinatesCW();
		makeShapes();
	}

	public void rotateCC() {
		rotateCoordinatesCCW();
		makeShapes();
	}
	
	
	public void flipHor() {
		fliph();
		makeShapes();
	}

	public void flipVer() {
		flipv();
		makeShapes();
	}
	
	private void makeShapes() {
		//Setting the visibility of all the shapes to false
		for (int i=0;i<21;i++)
		for(int j=0;j<5;j++)
			for(int k=0;k<5;k++) {
				shapeList[i][j][k].setVisible(false);
				shapeList[i][j][k].setOne(0);
			}
		//Making shapes according to their coordinates
		for (int i=0;i<21;i++)
			for(int j=0;j<5;j++)
				for(int k=0;k<5;k++) {
					if(j==2 && k==2) {
						for(int x=0;x<shapesCoordinates[i].length;x++) {
							CustomShapeButton thisButton=shapeList[i][shapesCoordinates[i][x][0]+j][shapesCoordinates[i][x][1]+k];
							thisButton.setVisible(true);
							thisButton.setOne(1);
							thisButton.setBackground(color);
						}
						}
					shapeList[i][j][k].shapeSize=shapesCoordinates[i].length;
					}
				}

	// Implementing the constructor of the shape			
	public Shape(int x, int y,Color color) {
		this.color=color;
		setLayout(new GridLayout(x,y));
		for (int i=0;i<21;i++) {
			JPanel panel = new JPanel();
			panel.setBackground(Color.white);
			panel.setLayout(new GridLayout(5,5));
			//panel.setBackground(new Color(175, 217, 85));
			panel.setBounds(0, 0, 50, 50);
			for(int j=0;j<5;j++)
				for(int k=0;k<5;k++) {
					CustomShapeButton button=new CustomShapeButton();
					shapeList[i][j][k]=button;
					button.setBounds(0,0,1,3);
					panel.add(button);
					button.setIndex(j,k);
					button.setSelectionIndex(i);
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							CustomShapeButton thisButton=((CustomShapeButton) e.getSource());
							selectedShapeCord=shapesCoordinates[thisButton.getSelectionIndex()];
							currentSelected=thisButton.getSelectionIndex();
							currentBoard.setSelection(selectedShapeCord);
						}
					});
				}
			add(panel);
		}
		makeShapes();
	}
	public void setBoard(Board currentBoard) {
				this.currentBoard=currentBoard;
	}

}
