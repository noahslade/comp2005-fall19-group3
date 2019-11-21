import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Shape extends JPanel {
	/**
	 *Attributes with getter and Setter that we are going to use in the Board Class
	 */

	private int score=0;         // To keep track of the pieces placed and use this to decide who is the winner
	private Board currentBoard;  // Making Board Object to set the out put of selected shape to the Board Class
	private boolean hasSkippedTurn; // To check if the user's have skipped their turns or not
	private String playerName; //To output the name of the Winner at the end of the game
	private String texture;
	private List<Integer> placedShapesIndex = new ArrayList<>();
	private CustomShapeButton[][][] shapeList=new CustomShapeButton[21][5][5];
	private JPanel[] shapePanels=new JPanel[21];

	public int getCurrentSelected() {
		return currentSelected;
	}
	private int currentSelected=0;
	private Color color;

	private int[][][] shapesCoordinates = {      // Coordinates of all the possible 21 Shapes which later on we will use to draw shapes
			{{0, 0}}//first piece
			,{{-1, 0}, {0, 0}, {0, 1}}, //second piece
			{{-1, 0}, {0, 0}, {1, 0}, {1, 1}}, //third
			{{-1, 0}, {0, 0}, {1, 0}, {0, -1}},  //fourth
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


	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}



	public void setScore(int actionsLength){
		this.score+=actionsLength;
	}

	public int getScore(){
		return this.score;
	}
	public boolean isHasSkippedTurn() {
		return hasSkippedTurn;
	}

	public void setHasSkippedTurn(boolean hasSkippedTurn) {
		this.hasSkippedTurn = hasSkippedTurn;
	}


	public void getShape(int x){
		currentBoard.setSelection(shapesCoordinates[x]);
	}


	public List<Integer> getPlacedShapesIndex() {
		return this.placedShapesIndex;
	}

	public void setPlacedShapesIndex(int index) {
		this.placedShapesIndex.add(index);
	}

	/**
	 * Create the panel.
	 */


	public String getTexture(){
		return this.texture;
	}
	public Color getColor(){
		return this.color;
	}

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
	public void hideShapePanel(int x){
		shapePanels[x].setVisible(false);
		
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
		for (int i=0;i<21;i++) {
			int j = 2;
			int k = 2;
			for(int x=0;x<shapesCoordinates[i].length;x++) {
				CustomShapeButton thisButton=shapeList[i][shapesCoordinates[i][x][0]+2][shapesCoordinates[i][x][1]+2];
				thisButton.setVisible(true);
				thisButton.setOne(1);
				thisButton.setIcon(new ImageIcon(getClass().getResource(texture)));
			}
			shapeList[i][j][k].shapeSize=shapesCoordinates[i].length;
		}
	}
				

	// Implementing the constructor of the shape			
	public Shape(int x, int y,Color color) {
		this.color=color;
		setLayout(new GridLayout(x,y));
		for (int i=0;i<21;i++) {
			shapePanels[i] = new JPanel();
			shapePanels[i].setBackground(Color.white);
			shapePanels[i].setLayout(new GridLayout(5,5));
			//panel.setBackground(new Color(175, 217, 85));
			shapePanels[i].setBounds(0, 0, 50, 50);
			for(int j=0;j<5;j++)
				for(int k=0;k<5;k++) {
					CustomShapeButton button=new CustomShapeButton();
					shapeList[i][j][k]=button;
					button.setBounds(0,0,1,3);
					shapePanels[i].add(button);
					button.setIndex(j,k); // what?
					button.setSelectionIndex(i); // what does this do?
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							CustomShapeButton thisButton=((CustomShapeButton) e.getSource());
							currentSelected=thisButton.getSelectionIndex();
							selectedShapeCord=shapesCoordinates[thisButton.getSelectionIndex()];
							currentBoard.setSelection(selectedShapeCord); // Sets the coordinates of the shape to be displayed on the board
						}
					});
				}
			add(shapePanels[i]);
		}
		if (color == Color.RED){
			texture = "image/redStripes.jpeg";
		}
		else if (color == Color.BLUE){
			texture = "image/blueStripes.jpeg";
		}
		else if (color == Color.YELLOW){
			texture = "image/yellowCross.png";
		}
		else if (color == Color.GREEN){
			texture = "image/greenDots.jpg";
		}
		makeShapes();
	}
	public void setBoard(Board currentBoard) {
				this.currentBoard=currentBoard;
	}

}
