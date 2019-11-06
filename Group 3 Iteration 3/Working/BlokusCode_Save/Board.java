import image.*;
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
import java.io.IOException;
import java.util.Arrays;
import java.io.File;
public class Board extends JPanel {
    /**
     * Create the panel.
     * 
     */
    private Shape shapeList1=new Shape(7,3,Color.red);
    private Shape shapeList2=new Shape(3,7,Color.blue);
    private Shape shapeList3=new Shape(7,3,Color.green);
    private Shape shapeList4=new Shape(3,7,Color.yellow);
    //Different layouts for each panel
    private GridLayout gridLayout_1;
    private GridLayout gridLayout_2;
    private GridLayout gridLayout_3;
    private GridLayout gridLayout_4;


    private int GridSize=20;
    //Creating an object of boardButton class to build an board of 20*20
    private BoardButton[][] board_button=new BoardButton[GridSize][GridSize];
    //action contains the coordinate of the selected piece
    private int[][] selectedShapeCord= {{}};  // short-hand syntax to declare arrays
    //thisbutton is used to add mouse listen for game_board grids
    private JButton thisButton;
    
    
    
    
    
    public void setSelection(int[][] selectedShapeCord) {
        this.selectedShapeCord=selectedShapeCord;
    }
    
    
    public Board() {
        this.setSize(1223, 980);
        setLayout(null); // absolute positioning
        
        // Making Alter the state panel which contains all the rotate and flip buttons
        JPanel alterState_pnl = new JPanel();
        alterState_pnl.setBounds(1036, 219, 160, 485);
        add(alterState_pnl);
        alterState_pnl.setLayout(null);
        
        JButton count_clockwisebtn = new JButton();
        count_clockwisebtn.setIcon(new ImageIcon(getClass().getResource("image/counterclockwise.png")));
        count_clockwisebtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shapeList1.rotateCC();
            }
        });
        count_clockwisebtn.setBounds(40, 11, 89, 68);
        count_clockwisebtn.setBackground(new Color(242, 226, 5));

        alterState_pnl.add(count_clockwisebtn);
        
        JButton clockwise_btn = new JButton();
        clockwise_btn.setIcon(new ImageIcon(getClass().getResource("image/clockwise.png")));
        clockwise_btn.setBackground(new Color(242, 226, 5));
        clockwise_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shapeList1.rotateC();
            }
        });
        clockwise_btn.setBounds(40, 122, 89, 68);
        alterState_pnl.add(clockwise_btn);
        
        JButton flip_vertical_btn = new JButton();
        flip_vertical_btn.setIcon(new ImageIcon(getClass().getResource("image/vertical.png")));
        flip_vertical_btn.setBackground(new Color(242, 226, 5));

        flip_vertical_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shapeList1.flipVer();
            }
        });
        flip_vertical_btn.setBounds(40, 236, 89, 68);
        alterState_pnl.add(flip_vertical_btn);
        alterState_pnl.setBackground(new Color (86, 140, 48));
        
        JButton flip_horizontal_btn = new JButton();
        flip_horizontal_btn.setIcon(new ImageIcon(getClass().getResource("image/switch_horizontal.png")));
        flip_horizontal_btn.setBounds(40, 356, 89, 68);
        flip_horizontal_btn.setBackground(new Color(242, 226, 5));
        flip_horizontal_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shapeList1.flipHor();
            }
        });
        alterState_pnl.add(flip_horizontal_btn);
        
        
        
        //Game Board of 20*20
        JPanel GAME_BOARD = new JPanel();
        GAME_BOARD.setBounds(228, 218, 580, 580);
        add(GAME_BOARD);
        GAME_BOARD.setLayout(new GridLayout(GridSize,GridSize));
        
        for(int i=0;i<GridSize;i++) {
            for(int j=0;j<GridSize;j++) {
                board_button[i][j] =new BoardButton(i,j); // BoardButton is an 2d array of buttons. board_button[0] contains 20 buttons.
                thisButton=board_button[i][j];
                thisButton.setBackground(Color.white);
                
                thisButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        try {
                            if (selectedShapeCord.length>0){
                            int x=((BoardButton)e.getSource()).getIndex()[0]; // it selects the coordinate x
                            int y=((BoardButton)e.getSource()).getIndex()[1]; // it selects the coordinate y
                            for (int i=0;i<selectedShapeCord.length;i++) {
                                board_button[x+selectedShapeCord[i][0]][y+selectedShapeCord[i][1]].setBackground(Color.red);}} // what does this line do?
                        }catch(Exception s) {}                         
                        
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        try {
                            int x=((BoardButton)e.getSource()).getIndex()[0];
                            int y=((BoardButton)e.getSource()).getIndex()[1];
                            for (int i=0;i<selectedShapeCord.length;i++) {
                                if(!board_button[x+selectedShapeCord[i][0]][y+selectedShapeCord[i][1]].isPlaced()){
                                    board_button[x+selectedShapeCord[i][0]][y+selectedShapeCord[i][1]].setBackground(Color.white);
                            }
                        }}catch(Exception s) {}
                    }
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            int x=((BoardButton)e.getSource()).getIndex()[0];
                            int y=((BoardButton)e.getSource()).getIndex()[1];
                            int k=0;
                            for (int i=0;i<selectedShapeCord.length;i++) {
                                if(board_button[x+selectedShapeCord[i][0]][y+selectedShapeCord[i][1]].isPlaced()==false && (x+selectedShapeCord[i][0]<=20 && y+selectedShapeCord[i][1]<=20)) {
                                    k=k+1;
                                }
                            }
                            if (k==selectedShapeCord.length){
                            for (int i=0;i<selectedShapeCord.length;i++) {
                                if(board_button[x+selectedShapeCord[i][0]][y+selectedShapeCord[i][1]].isPlaced()==false) {
                                    board_button[x+selectedShapeCord[i][0]][y+selectedShapeCord[i][1]].setBackground(Color.red);
                                    //Added code for making each board_button[x][y] be associated with a Color to save GameBoard
                                    board_button[x+selectedShapeCord[i][0]][y+selectedShapeCord[i][1]].setColorSquare(Color.red);
                                    //Ask Patricia about commas
                                    //I learned that to handle complexity, it is convenient to abstract to managing one instance of the population at test
                                    board_button[x+selectedShapeCord[i][0]][y+selectedShapeCord[i][1]].setTaken(true);
                                }
                            }
                            int [][] empty_array={{}};
                            setSelection(empty_array); //what does it do?
                        }
                        
                        
                        }catch(Exception s) {}

                    }
                });
                
                GAME_BOARD.add(board_button[i][j]);
            }
        }
        
        
        
        JButton saveButton = new JButton("SAVE");
        saveButton.setBounds(1062, 11, 89, 42);
        saveButton.setBackground(new Color (86, 140, 48));
        //Code that I have added
        saveButton.addActionListener(e->{
        	//CHANGE THIS TO YOUR CURRENT DIRECTORY, ELSE YOU WILL GET IOEXCEPTION
        	String fileName = "C:\\Users\\johan\\git\\comp2005-fall19-group3\\Group 3 Iteration 2\\blokus\\src\\test.txt"; 
        	
        	//Delete the contents of the last save
        	File file = new File(fileName);
        	file.delete();
        	SaveManager saveMyData = new SaveManager(fileName, true); // We want to append data to the text file.
        	
        	//This part that deals with logic should be in a different layer
        	  for(int i=0;i<GridSize;i++) {
                  for(int j=0;j<GridSize;j++) {
                	  if(board_button[i][j].isPlaced())
                	  {
                		  try {
                			
							saveMyData.writeToFile(board_button[i][j].getIndex()[0] + ","+ board_button[i][j].getIndex()[1] + "," + board_button[i][j].getColorSquare());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}//+ board_button[i][j].getColorSquare());
                		
                		  //System.out.println(board_button[i][j].getIndex()[0] + ","+ board_button[i][j].getIndex()[1]);
                	  }
                      
        }}});
        add(saveButton);
        
        JButton hintButton = new JButton("HINT");
        hintButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        hintButton.setBounds(909, 11, 89, 42);
        hintButton.setBackground(new Color(86, 140, 48));
        add(hintButton);

        
        
        //Top label of Blokus
        JLabel top_label = new JLabel("BLOKUS");
        top_label.setFont(new Font("Tahoma", Font.BOLD, 17));
        top_label.setBounds(38, 11, 81, 53);
        add(top_label);



        //Adding the shape_lists or shape_containers to the Board panel
        gridLayout_1 = (GridLayout) shapeList1.getLayout();// left most container
        gridLayout_1.setVgap(3);
        gridLayout_1.setHgap(3);
        shapeList1.setBounds(38, 284, 180, 420);
        add(shapeList1);
        shapeList1.setBoard(this);
        shapeList1.setBackground(new Color(175, 217, 85));

        shapeList2.setBounds(310, 809, 420, 160);//bottom container
        add(shapeList2);
        gridLayout_2 = (GridLayout) shapeList2.getLayout();
        gridLayout_2.setHgap(3);
        gridLayout_2.setVgap(3);
        shapeList2.setBackground(new Color(175, 217, 85));


        gridLayout_3 = (GridLayout) shapeList3.getLayout();//right most container
        gridLayout_3.setVgap(3);
        gridLayout_3.setHgap(3);
        shapeList3.setBounds(818, 284, 180, 420);
        add(shapeList3);
        shapeList3.setBackground(new Color(175, 217, 85));

        
        shapeList4.setBounds(310, 27, 420, 180);//top most container
        add(shapeList4);
        gridLayout_4 = (GridLayout) shapeList4.getLayout();
        gridLayout_4.setVgap(3);
        gridLayout_4.setHgap(3);
        shapeList4.setBackground(new Color(175, 217, 85));



        setBackground(new Color (175, 217, 85));
        
    }
}
