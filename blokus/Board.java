
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

public class Board extends JPanel {
    /**
     * Create the panel.
     * 
     */
    private Shape shapeList1=new Shape(7,3);
    private Shape shapeList2=new Shape(3,7);
    private Shape shapeList3=new Shape(7,3);
    private Shape shapeList4=new Shape(3,7);

    private GridLayout gridLayout_1;
    private GridLayout gridLayout_2;
    private GridLayout gridLayout_3;
    private GridLayout gridLayout_4;


    private int GridSize=20;
    //Creating an object of boardButton class to build an board of 20*20
    private boardButton[][] board_button=new boardButton[GridSize][GridSize];
    //action contains the coordinate of the selected piece
    private int[][] actions= {{}};
    //thisbutton is used to add mouse listen for game_board grids
    private JButton thisButton;
    
    
    
    
    
    public void setActions(int[][] actions) {
        this.actions=actions;
    }
    
    
    public Board() {
        this.setSize(1223, 980);
        setLayout(null);
        
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
        alterState_pnl.add(count_clockwisebtn);
        
        JButton clockwise_btn = new JButton();
        clockwise_btn.setIcon(new ImageIcon(getClass().getResource("image/clockwise.png")));
        clockwise_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shapeList1.rotateC();
            }
        });
        clockwise_btn.setBounds(40, 122, 89, 68);
        alterState_pnl.add(clockwise_btn);
        
        JButton flip_vertical_btn = new JButton();
        flip_vertical_btn.setIcon(new ImageIcon(getClass().getResource("image/vertical.png")));
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
                board_button[i][j] =new boardButton(i,j);
                thisButton=board_button[i][j];
                thisButton.setBackground(Color.white);
                
                thisButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        try {
                            if (actions.length>0){
                            int x=((boardButton)e.getSource()).getIndex()[0];
                            int y=((boardButton)e.getSource()).getIndex()[1];
                            for (int i=0;i<actions.length;i++) {
                                board_button[x+actions[i][0]][y+actions[i][1]].setBackground(Color.green);}}
                        }catch(Exception s) {}                         
                        
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        try {
                            int x=((boardButton)e.getSource()).getIndex()[0];
                            int y=((boardButton)e.getSource()).getIndex()[1];
                            for (int i=0;i<actions.length;i++) {
                                if(!board_button[x+actions[i][0]][y+actions[i][1]].isPlaced()){
                                    board_button[x+actions[i][0]][y+actions[i][1]].setBackground(Color.white);
                            }
                        }}catch(Exception s) {}
                    }
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            int x=((boardButton)e.getSource()).getIndex()[0];
                            int y=((boardButton)e.getSource()).getIndex()[1];
                            int k=0;
                            for (int i=0;i<actions.length;i++) {
                                if(board_button[x+actions[i][0]][y+actions[i][1]].isPlaced()==false && (x+actions[i][0]<=20 && y+actions[i][1]<=20)) {
                                    k=k+1;
                                }
                            }
                            if (k==actions.length){
                            for (int i=0;i<actions.length;i++) {
                                if(board_button[x+actions[i][0]][y+actions[i][1]].isPlaced()==false) {
                                    board_button[x+actions[i][0]][y+actions[i][1]].setBackground(Color.green);
                                    board_button[x+actions[i][0]][y+actions[i][1]].setTaken(true);
                                }
                            }
                            int [][] empty_array={{}};
                            setActions(empty_array);
                        }
                        
                        
                        }catch(Exception s) {}

                    }
                });
                
                GAME_BOARD.add(board_button[i][j]);
            }
        }
        
        
        
        JButton saveButton = new JButton("SAVE");
        saveButton.setBounds(1062, 11, 89, 42);
        add(saveButton);
        
        JButton hintButton = new JButton("HINT");
        hintButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        hintButton.setBounds(909, 11, 89, 42);
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
        shapeList1.setBackground(new Color(132, 217, 4));

        shapeList2.setBounds(310, 809, 420, 160);//bottom container
        add(shapeList2);
        gridLayout_2 = (GridLayout) shapeList2.getLayout();
        gridLayout_2.setHgap(3);
        gridLayout_2.setVgap(3);

        gridLayout_3 = (GridLayout) shapeList3.getLayout();//right most container
        gridLayout_3.setVgap(3);
        gridLayout_3.setHgap(3);
        shapeList3.setBounds(818, 284, 180, 420);
        add(shapeList3);
        
        shapeList4.setBounds(310, 27, 420, 180);//top most container
        add(shapeList4);
        gridLayout_4 = (GridLayout) shapeList4.getLayout();
        gridLayout_4.setVgap(3);
        gridLayout_4.setHgap(3);

        setBackground(new Color (175, 217, 85));
        
    }
}
