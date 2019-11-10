
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.util.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.io.IOException;
import java.io.File;


import java.util.concurrent.TimeUnit;

public class Board extends JPanel {
    /**
     * Create the panel.
     */
    private Shape shapeList1 = new Shape(7, 3, Color.red);
    private Shape shapeList2 = new Shape(3, 7, Color.blue);
    private Shape shapeList3 = new Shape(7, 3, Color.green);
    private Shape shapeList4 = new Shape(3, 7, Color.yellow);

    private GridLayout gridLayout_1;
    private GridLayout gridLayout_2;
    private GridLayout gridLayout_3;
    private GridLayout gridLayout_4;

    private int [][][] placed_shapes_cord = new int [21][5][5];
    private int array_index=0;

    private int GridSize = 20;
    
    //Creating an object of boardButton class to build an board of 20*20
    private BoardButton[][] board_button = new BoardButton[GridSize][GridSize];
    
    //action contains the coordinate of the selected piece
    private int[][] selectedShapeCord = {{}};
    
    //thisbutton is used to add mouse listen for game_board grids
    private JButton thisButton;


    // Set color of the the grids to default where hints were highlighted
    private void setDefaultColor(){
        for(int i=0; i<board_button.length;i++){
            for(int j=0; j<board_button.length;j++){
                if (!board_button[i][j].isPlaced()){
                    board_button[i][j].setBackground(Color.WHITE);
                }}}
    }



    //Checks if the first shape is being placed or not
    public boolean checkCorner(int x, int y, int[][] selectedShapeCord) {
        boolean isCornerOccupied = false;
        for (int i = 0; i < selectedShapeCord.length; i++) {
            if (x+selectedShapeCord[i][0] == 0 && y + selectedShapeCord[i][1] == 0) {
                isCornerOccupied = true;
            }
        }
        return isCornerOccupied;
    }

    // Checks if the shape is being placed inside the grid i.e it's indexes don't exceed BoardSize
    public int checkGrid(int x,int y,int [][] actions){
        int k=0;
        for (int i = 0; i < actions.length; i++) {
            if ((x + actions[i][0] <20 && y + actions[i][1] < 20)&&(x + actions[i][0] >=0 && y + actions[i][1] >= 0)) {
               if (board_button[x + actions[i][0]][y + actions[i][1]].isPlaced() == false) {
                        k = k + 1;
            }}
        }
        return k;
    }

    // get the coordinates of the selected shape from the Shapes Class
    public void setSelection(int[][] selectedShapeCord) {
    	setDefaultColor();
        this.selectedShapeCord = selectedShapeCord;
    }

    // It is the main function which places the shape once the check corner method or checkGrid method is being considered
    public void placeShape(int x, int y, int[][] selectedShapeCord) {
        int[][]current_shape_cord=new int [5][5];
        for (int i = 0; i < selectedShapeCord.length; i++) {
            if (board_button[x + selectedShapeCord[i][0]][y + selectedShapeCord[i][1]].isPlaced() == false) {

                board_button[x + selectedShapeCord[i][0]][y + selectedShapeCord[i][1]].setBackground(Color.red);
                board_button[x + selectedShapeCord[i][0]][y + selectedShapeCord[i][1]].setTaken(true);
                int [] cord= {x + selectedShapeCord[i][0], y + selectedShapeCord[i][1]};
                current_shape_cord[i] =cord;
            }
        }
        placed_shapes_cord[array_index]=current_shape_cord;
        array_index=array_index+1;

        int[][] empty_array = {{}};
        setSelection(empty_array);
    }

    // Checks if there is any shape on the top, bottom , left and right of the bloks of the current selected shape
    private boolean isPlaceableOnLRTB(int x, int y, int[][] actions) {
        int[][] cActions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int i = 0; i < actions.length; i++) {
            for (int j = 0; j < cActions.length; j++) {
                try {
                    int k=checkGrid(x,y,actions);
                    if (k==actions.length && (x + actions[i][0] + cActions[j][0])>=0 &&(x + actions[i][0] + cActions[j][0]<20)&&(y + actions[i][1] + cActions[j][1]>=0)&&(y + actions[i][1] + cActions[j][1]<20) ) {
                        if (board_button[x + actions[i][0] + cActions[j][0]][y + actions[i][1] + cActions[j][1]].isPlaced()) {
                        return false;
                    }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    // Checks the places on the board for the shapes which are already placed by the user so new selected shape could be placed
    private boolean isPlaceableDiag(int x, int y, int[][] actions) {
        int[][] cardinalActions = {{1, 1}, {-1, -1}, {-1, 1}, {1, -1}}; // Valid places where the new shapes could be placed
        for (int i = 0; i < actions.length; i++) {
            for (int j = 0; j < cardinalActions.length; j++) {
                try {
                    int k=checkGrid(x,y,actions);
                    if( (x + actions[i][0] + cardinalActions[j][0]>=0 )&&(x + actions[i][0] + cardinalActions[j][0]<this.GridSize )
                            &&(y + actions[i][1] + cardinalActions[j][1]>=0)&&(y + actions[i][1] + cardinalActions[j][1]<this.GridSize)){
                    if (k==actions.length) {
                    if (board_button[x + actions[i][0] + cardinalActions[j][0]][y + actions[i][1] + cardinalActions[j][1]].isPlaced()) {
                        return true;
                    }
                }}
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return false;
    }
    //Main Hitman for the hint Button which go through every already placed shape and find out the places on the board where new selected shapes could be placed
    private void hint(int[][] actions) {
//        for (int i = 0; i < array_index; i++) {
//            for(int j=0;j<placed_shapes_cord[i].length;j++){
            for (int x = -GridSize; x < GridSize; x++) {
                for (int y = -GridSize; y <= GridSize; y++) {
//                    if ((placed_shapes_cord[i][j][0] + x >= 0 && placed_shapes_cord[i][j][0] + x < this.GridSize) && (placed_shapes_cord[i][j][1] + y >= 0 && placed_shapes_cord[i][j][1] + y < this.GridSize)) {
                        int k = checkGrid(x, y, actions);
                        if (k == actions.length) {

                        if (isPlaceableOnLRTB(x, y, actions)) {
                            try {
                                if (isPlaceableDiag(x, y, actions)) {
                                    board_button[x][y].highlight();
                                }
                            } catch (Exception e) {
                                e.printStackTrace(); }
                        } }
                    }
                }
            }
//    }


    /**
     * Constructor
     */
    public Board() {
        this.setSize(1011, 716);
        setLayout(null);

        // Making Alter the state panel which contains all the rotate and flip buttons
        JPanel alterState_pnl = new JPanel();
        alterState_pnl.setBounds(849, 157, 129, 436);
        add(alterState_pnl);
        alterState_pnl.setLayout(null);

        JButton count_clockwisebtn = new JButton();
        count_clockwisebtn.setIcon(new ImageIcon(getClass().getResource("image/counterclockwise.png")));
        count_clockwisebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setDefaultColor();
                shapeList1.rotateCC();
            }
        });
        count_clockwisebtn.setBounds(24, 11, 89, 68);
        count_clockwisebtn.setBackground(new Color(242, 226, 5));

        alterState_pnl.add(count_clockwisebtn);

        JButton clockwise_btn = new JButton();
        clockwise_btn.setIcon(new ImageIcon(getClass().getResource("image/clockwise.png")));
        clockwise_btn.setBackground(new Color(242, 226, 5));
        clockwise_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setDefaultColor();
                shapeList1.rotateC();
            }
        });
        clockwise_btn.setBounds(24, 122, 89, 68);
        alterState_pnl.add(clockwise_btn);

        JButton flip_vertical_btn = new JButton();
        flip_vertical_btn.setIcon(new ImageIcon(getClass().getResource("image/vertical.png")));
        flip_vertical_btn.setBackground(new Color(242, 226, 5));

        flip_vertical_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setDefaultColor();
                shapeList1.flipVer();
            }
        });
        flip_vertical_btn.setBounds(24, 236, 89, 68);
        alterState_pnl.add(flip_vertical_btn);
        alterState_pnl.setBackground(new Color(86, 140, 48));

        JButton flip_horizontal_btn = new JButton();
        flip_horizontal_btn.setIcon(new ImageIcon(getClass().getResource("image/switch_horizontal.png")));
        flip_horizontal_btn.setBounds(24, 357, 89, 68);
        flip_horizontal_btn.setBackground(new Color(242, 226, 5));
        flip_horizontal_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setDefaultColor();
                shapeList1.flipHor();
            }
        });
        alterState_pnl.add(flip_horizontal_btn);


        //Game Board of 20*20
        JPanel GAME_BOARD = new JPanel();
        GAME_BOARD.setBounds(227, 157, 420, 420);
        add(GAME_BOARD);
        GAME_BOARD.setLayout(new GridLayout(GridSize, GridSize));

        for (int i = 0; i < GridSize; i++) {
            for (int j = 0; j < GridSize; j++) {
                board_button[i][j] = new BoardButton(i, j);
                thisButton = board_button[i][j];

                thisButton.setBackground(Color.white);

                thisButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        try {
                            if (selectedShapeCord.length > 0) {
                                int x = ((BoardButton) e.getSource()).getIndex()[0];
                                int y = ((BoardButton) e.getSource()).getIndex()[1];
                                for (int i = 0; i < selectedShapeCord.length; i++) {
                                    board_button[x + selectedShapeCord[i][0]][y + selectedShapeCord[i][1]].setBackground(Color.red);
                                }
                            }
                        } catch (Exception s) {
                        }

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        try {
                            int x = ((BoardButton) e.getSource()).getIndex()[0];
                            int y = ((BoardButton) e.getSource()).getIndex()[1];
                            for (int i = 0; i < selectedShapeCord.length; i++) {
                                if (!board_button[x + selectedShapeCord[i][0]][y + selectedShapeCord[i][1]].isPlaced()) {
                                    board_button[x + selectedShapeCord[i][0]][y + selectedShapeCord[i][1]].setBackground(Color.white);
                                }
                            }
                        } catch (Exception s) {
                        }
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int x = ((BoardButton) e.getSource()).getIndex()[0];
                        int y = ((BoardButton) e.getSource()).getIndex()[1];
                        try {
                            if (!board_button[0][0].isPlaced()) {
                                boolean isCornerOccupied = checkCorner(x, y, selectedShapeCord);
                                if (isCornerOccupied) {
                                    placeShape(x, y, selectedShapeCord);
                                }
                            } else {
                                int k = 0;
                                if (isPlaceableOnLRTB(x, y, selectedShapeCord)) {
                                    if (isPlaceableDiag(x, y, selectedShapeCord)) {
                                        k = checkGrid(x, y, selectedShapeCord);

                                        if (k == selectedShapeCord.length) {
                                            placeShape(x, y, selectedShapeCord);
                                            setDefaultColor();
                                        }

                                    }
                                }
                            }
                        } catch (Exception s) {
                        }

                    }
                });

                GAME_BOARD.add(board_button[i][j]);

            }
        }


        JButton saveButton = new JButton("SAVE");
        saveButton.setBounds(892, 11, 89, 42);
        saveButton.setBackground(new Color(86, 140, 48));
        saveButton.addActionListener(e->{
            String fileName = "savedDataFinal.txt";// . is for current directory

            //Delete the contents of the last save
            File file = new File(fileName);
            
            SaveManager saveMyData = new SaveManager(fileName, true); // We want to append data to the text file.
                if(file.exists())
        	{
        		
        		JDialog confirmationDelete = new JDialog();
        		confirmationDelete.setSize(400, 500);
        		confirmationDelete.setTitle("Warning Data Will Be Overwritten");
        		JPanel options = new JPanel();
        		options.setLayout(new GridLayout(2,1));
        		JButton confirmDel = new JButton("Confirm");
        		confirmDel.setBackground(Color.ORANGE);
        		
        		confirmDel.addActionListener(g ->
        		{
        			file.delete();
        			analyzeStateBoard(saveMyData);
        			confirmationDelete.dispose();
        		});
        		
        		JButton cancelDel = new JButton("Cancel");
        		cancelDel.setBackground(Color.GREEN);
        		
        		cancelDel.addActionListener(f-> {
        			confirmationDelete.dispose();
        		});
        		
        		options.add(confirmDel);
        		options.add(cancelDel);
        		confirmationDelete.add(options);
        		
        		confirmationDelete.setVisible(true);
        		
        	
        		
        		
        		
        		
        		
        	}
        	else
        	{
        		analyzeStateBoard(saveMyData);
        	}
        	
        	
        	
        	
        	
        	
        	
        	
        	 });
        add(saveButton);

        JButton hintButton = new JButton("HINT");
        hintButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!board_button[0][0].isPlaced()) {
                    board_button[0][0].highlight();
                }
                else{hint(selectedShapeCord);
            }

            }
        });


        hintButton.setBounds(779, 11, 89, 42);
        hintButton.setBackground(new Color(86, 140, 48));
        add(hintButton);


        //Top label of Blokus
        JLabel top_label = new JLabel("BLOKUS");
        top_label.setFont(new Font("Tahoma", Font.BOLD, 17));
        top_label.setBounds(60, 11, 81, 53);
        add(top_label);


        //Adding the shape_lists or shape_containers to the Board panel
        gridLayout_1 = (GridLayout) shapeList1.getLayout();// left most container
        gridLayout_1.setVgap(3);
        gridLayout_1.setHgap(3);
        shapeList1.setBounds(43, 157, 180, 420);
        add(shapeList1);
        shapeList1.setBoard(this);
        shapeList1.setBackground(new Color(175, 217, 85));

        shapeList2.setBounds(227, 580, 420, 130);//bottom container
        add(shapeList2);
        gridLayout_2 = (GridLayout) shapeList2.getLayout();
        gridLayout_2.setHgap(3);
        gridLayout_2.setVgap(3);
        shapeList2.setBackground(new Color(175, 217, 85));


        gridLayout_3 = (GridLayout) shapeList3.getLayout();//right most container
        gridLayout_3.setVgap(3);
        gridLayout_3.setHgap(3);
        shapeList3.setBounds(649, 157, 180, 420);
        add(shapeList3);
        shapeList3.setBackground(new Color(175, 217, 85));


        shapeList4.setBounds(227, 23, 420, 130);//top most container
        add(shapeList4);
        gridLayout_4 = (GridLayout) shapeList4.getLayout();
        gridLayout_4.setVgap(3);
        gridLayout_4.setHgap(3);
        shapeList4.setBackground(new Color(175, 217, 85));


        setBackground(new Color(175, 217, 85));

    }
    /**
	 * Assistant function of action event save gamto make my code more cohesive. 
	 */
	private void analyzeStateBoard(SaveManager saveMyData)
	{
		 for(int i=0;i<GridSize;i++) {
             for(int j=0;j<GridSize;j++) {
           	  if(board_button[i][j].isPlaced())
           	  {
           		  try {
           			
						saveMyData.writeToFile(board_button[i][j].getIndex()[0] + ","+ board_button[i][j].getIndex()[1] + "," + board_button[i][j].getColorSquare());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
           		
           		  
           	  }
                 
   }}
	}
}
