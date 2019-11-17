
import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
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
    private int call=0;
    private Blokus main;
    private int difficulty;

    int[][] shapeBounds={{43, 157, 180, 420},{227, 580, 420, 130},{649, 157, 180, 420},{227, 23, 420, 130}};

    private int turn =0;
    private int [][] all_corners={{0,0},{0,19},{19,19},{19,0}};

    private Shape[] allPlayers=new Shape[4];

    private GridLayout gridLayout_1;


    private int GridSize = 20;

    //Creating an object of boardButton class to build an board of 20*20
    private BoardButton[][] board_button = new BoardButton[GridSize][GridSize];

    //action contains the coordinate of the selected piece
    private int[][] selectedShapeCord = {{}};

    //thisbutton is used to add mouse listen for game_board grids
    private JButton thisButton;


    /*
    * Making Methods to check if the game is Over or not
     */
    private boolean isGameOver(){
        return (allPlayers[0].isHasSkippedTurn()&& allPlayers[1].isHasSkippedTurn()&&allPlayers[2].isHasSkippedTurn()&&allPlayers[3].isHasSkippedTurn());
    }
    private void gameOver(){
        call=1;
        boolean gameOvr=isGameOver();
        if (gameOvr){
            String topPlayer;
            int maxScore=0;
            int maxScoreIndex=0;
            for(int i=0; i<4;i++){
                if (maxScore<allPlayers[i].getScore()){
                    maxScore=allPlayers[i].getScore();
                    maxScoreIndex=i;
                }
            }
            topPlayer=allPlayers[maxScoreIndex].getPlayerName();
            JOptionPane.showMessageDialog(null,topPlayer+" WON "+ " AND HAS SCORED "+ maxScore);
            JOptionPane.showMessageDialog(null,"Wanna Play Again, then Restart Please");
            this.setVisible(false);
            main.setVisible(false);
            Blokus newGame=new Blokus();
            newGame.setVisible(true);

        }
    }


    /* AI BEING IMPLEMENTED */

    /*
    Various Methods for our AI
    First getting the place to place the piece
    Second placing the piece for Easy Difficulty
    Third for placing the piece for Medium Difficulty
     */

    private ArrayList hintForAI(int [][] actions){
        ArrayList possiblePlaces=new ArrayList();
        for (int x = 0; x < GridSize; x++) {
            for (int y = 0; y <= GridSize; y++) {
                int k = checkGrid(x, y, actions);
                if (k == actions.length) {

                    if (isPlaceableOnLRTB(x, y, actions)) {
                        try {
                            if (isPlaceableDiag(x, y, actions)) {
                                int [] index={x,y};
                                possiblePlaces.add(index);
                            }
                        } catch (Exception e) {
                            e.printStackTrace(); }
                    } }
            }
        }
        //System.out.println(Arrays.deepToString(possiblePlaces.toArray()));
    return possiblePlaces;
    }

    public void playAI() {
        if (board_button[all_corners[turn][0]][all_corners[turn][1]].isPlaced()) {
            for(int i =1; i<21;i++){
                if(!allPlayers[turn].getPlacedShapesIndex().contains(i)){
                    allPlayers[turn].getShape(i);
                    allPlayers[turn].setPlacedShapesIndex(i);
                    break;
                }
            }
            if(allPlayers[turn].getPlacedShapesIndex().size()<21  && !isGameOver()){
                if(hintForAI(selectedShapeCord).size()>0){
                int[] indexes = (int[]) hintForAI(selectedShapeCord).get(0);
                //System.out.println(Arrays.deepToString((Object[]) indexes.get(0)));
                if (indexes.length > 0){
                    placeShape(indexes[0], indexes[1], selectedShapeCord);
                allPlayers[turn].setScore(selectedShapeCord);
                allPlayers[turn].setHasSkippedTurn(false);}


            }}
                else{
                    allPlayers[turn].setHasSkippedTurn(true);
                    setTurn();
                    setOtherShapes();
                if(allPlayers[turn].getClass()==AI.class){
                    while(turn!=0 && !isGameOver()){
                        if (difficulty==1){
                            playAI();}
                        else{mediumPlayAI();}
                        setTurn();
                        setOtherShapes();
                    }}
            }}

            else {
            allPlayers[turn].getShape(0);
            allPlayers[turn].setPlacedShapesIndex(0);
            placeShape(all_corners[turn][0], all_corners[turn][1], selectedShapeCord);
            allPlayers[turn].setScore(selectedShapeCord);
            }
    }

    // Medium level Difficulty AI
    public void mediumPlayAI(){
        if (board_button[all_corners[turn][0]][all_corners[turn][1]].isPlaced()) {
            int i =20;
            while (i > 0){
                if(!allPlayers[turn].getPlacedShapesIndex().contains(i)){
                    allPlayers[turn].getShape(i);
                    if(hintForAI(selectedShapeCord).size()>0) {
                        allPlayers[turn].setPlacedShapesIndex(i);
                        break;
                    }
                    else{continue;}
                }
                i--;
            }
            if(allPlayers[turn].getPlacedShapesIndex().size()<21 && !isGameOver()){
            if (hintForAI(selectedShapeCord).size()>0){
            int[] indexes = (int[]) hintForAI(selectedShapeCord).get(0);
            if (indexes.length > 0){
                placeShape(indexes[0], indexes[1], selectedShapeCord);
                allPlayers[turn].setScore(selectedShapeCord);
                allPlayers[turn].setHasSkippedTurn(false);}}}
            else{
                allPlayers[turn].setHasSkippedTurn(true);
                setTurn();
                setOtherShapes();
                if(allPlayers[turn].getClass()==AI.class){
                    while(turn!=0 && !isGameOver()){
                        if (difficulty==1){
                            playAI();}
                        else{mediumPlayAI();}
                        setTurn();
                        setOtherShapes();
                    }}
            }

        }

        else {
            allPlayers[turn].getShape(0);
            allPlayers[turn].setPlacedShapesIndex(0);
            placeShape(all_corners[turn][0], all_corners[turn][1], selectedShapeCord);
            allPlayers[turn].setScore(selectedShapeCord);
        }
    }


    // Method to take hide the shapes of the players if they don't have their turn

    private void setOtherShapes(){
        for(int i=0; i<allPlayers.length; i++){
            if (turn ==i){
            allPlayers[i].setVisible(true);
            }
            else{
                allPlayers[i].setVisible(false);
        }
    }
    }

    // Setting turn and keep the game rolling
    private void setTurn(){
        if(!isGameOver()){
            if (turn < 3){
            turn++; }
            else{
              turn=0; }
    }
    else{
        turn=0;
        if(call==0){
        gameOver();}
    }
}

    // Set color of the the grids to default where hints were highlighted
    private void setDefaultColor(){
        for(int i=0; i<board_button.length;i++){
            for(int j=0; j<board_button.length;j++){
                if (!board_button[i][j].isPlaced()){
                    board_button[i][j].setBackground(Color.WHITE);
                }}}
    }



    /* Methods to place the pieces are being made*/

    //Checks if the first shape is being placed or not
    public boolean checkCorner(int x, int y, int[][] selectedShapeCord) {
        boolean isCornerOccupied = false;
        for (int i = 0; i < selectedShapeCord.length; i++) {
            if (x+selectedShapeCord[i][0] == all_corners[turn][0] && y + selectedShapeCord[i][1] == all_corners[turn][1]) {
                isCornerOccupied = true;
            }
        }
        int k=checkGrid(x,y,selectedShapeCord);
        if(k!=selectedShapeCord.length){isCornerOccupied=false;}
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
        for (int i = 0; i < selectedShapeCord.length; i++) {
            if (board_button[x + selectedShapeCord[i][0]][y + selectedShapeCord[i][1]].isPlaced() == false) {

                board_button[x + selectedShapeCord[i][0]][y + selectedShapeCord[i][1]].setBackground(allPlayers[turn].getColor());
                board_button[x + selectedShapeCord[i][0]][y + selectedShapeCord[i][1]].setTaken(true);
                board_button[x + selectedShapeCord[i][0]][y + selectedShapeCord[i][1]].setColorSquare(allPlayers[turn].getColor());
            }
        }
        if(allPlayers[turn].getClass()!=AI.class){
        allPlayers[turn].setPlacedShapesIndex(allPlayers[turn].getCurrentSelected()); }
        allPlayers[turn].setScore(selectedShapeCord);
        allPlayers[turn].setHasSkippedTurn(false);
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
                        if (board_button[x + actions[i][0] + cActions[j][0]][y + actions[i][1] + cActions[j][1]].isPlaced() && board_button[x + actions[i][0] + cActions[j][0]][y + actions[i][1] + cActions[j][1]].getColorSquare()==allPlayers[turn].getColor()) {
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
                    if (board_button[x + actions[i][0] + cardinalActions[j][0]][y + actions[i][1] + cardinalActions[j][1]].isPlaced() && board_button[x + actions[i][0] + cardinalActions[j][0]][y + actions[i][1] + cardinalActions[j][1]].getColorSquare()==(allPlayers[turn].getColor())) {
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
                        }
                        }
                    }
                }
            }
//    }


    /**
     * Constructor
     */
    public Board(Color[] color_array,int difficulty, int numPlayers, Blokus Main) {

        this.main=Main;
        this.difficulty=difficulty;
        setUpPlayers(color_array,difficulty,numPlayers);
        setOtherShapes();
        this.setBounds(0,0,1011, 716);
        setLayout(null);

        //SKIP THE TURN PANEL

        JPanel skip_turn = new JPanel();
        skip_turn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                allPlayers[turn].setHasSkippedTurn(true);
                setTurn();
                setOtherShapes();
                if(allPlayers[turn].getClass()==AI.class){
                    while(turn!=0 && !isGameOver()){
                        if (difficulty==1){
                            playAI();}
                        else{mediumPlayAI();}
                        setTurn();
                        setOtherShapes();
                    }}
            }
        });
        skip_turn.setBounds(849, 93, 129, 53);
        add(skip_turn);
        skip_turn.setLayout(null);

        // label for the skip the turn

        JLabel skipLabel = new JLabel("SKIP TURN");
        skipLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent s) {
                allPlayers[turn].setHasSkippedTurn(true);
                setTurn();
                setOtherShapes();
                if(allPlayers[turn].getClass()==AI.class){
                    while(turn!=0 && !isGameOver()){
                        if (difficulty==1){
                            playAI();}
                        else{mediumPlayAI();}
                        setTurn();
                        setOtherShapes();
                    }}
            }
        });
        skipLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        skipLabel.setBounds(10, 11, 109, 31);
        skip_turn.add(skipLabel);



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
                allPlayers[turn].rotateCC();
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
                allPlayers[turn].rotateC();
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
                allPlayers[turn].flipVer();
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
                allPlayers[turn].flipHor();
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
                                    if (!board_button[x + selectedShapeCord[i][0]][y + selectedShapeCord[i][1]].isPlaced()){
                                    board_button[x + selectedShapeCord[i][0]][y + selectedShapeCord[i][1]].setBackground(allPlayers[turn].getColor());
                                }
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
                            if (!board_button[all_corners[turn][0]][all_corners[turn][1]].isPlaced()) {
                                boolean isCornerOccupied = checkCorner(x, y, selectedShapeCord);
                                if (isCornerOccupied) {
                                    placeShape(x, y, selectedShapeCord);
                                    allPlayers[turn].hideShapePanel(allPlayers[turn].getCurrentSelected());
                                    setDefaultColor();
                                    setTurn();
                                    setOtherShapes();
                                    if(allPlayers[turn].getClass()==AI.class){
                                        while(turn!=0){
                                            if (difficulty==0){
                                        playAI();}
                                            else{mediumPlayAI();}
                                        setTurn();
                                        setOtherShapes();
                                    }}
                                }
                            } else {
                                int k = 0;
                                if (isPlaceableOnLRTB(x, y, selectedShapeCord)) {
                                    if (isPlaceableDiag(x, y, selectedShapeCord)) {
                                        k = checkGrid(x, y, selectedShapeCord);

                                        if (k == selectedShapeCord.length) {
                                            placeShape(x, y, selectedShapeCord);
                                            allPlayers[turn].hideShapePanel(allPlayers[turn].getCurrentSelected());
                                            setDefaultColor();
                                            setTurn();
                                            setOtherShapes();
                                        }

                                    }
                                }
                                if(allPlayers[turn].getClass()==AI.class){
                                    while(turn!=0){
                                        if (difficulty==1){
                                            playAI();}
                                        else{mediumPlayAI();}
                                        setTurn();
                                        setOtherShapes();
                                    }}
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
                    if (turn==0){
                        if (!board_button[0][0].isPlaced()) {
                            board_button[0][0].highlight();}
                        else{hint(selectedShapeCord);}
                    }
                    else if (turn ==1){
                        if (!board_button[0][19].isPlaced()) {
                            board_button[0][19].highlight();}
                        else{hint(selectedShapeCord);}
                        }
                    else if (turn==2){
                        if (!board_button[19][19].isPlaced()) {
                            board_button[19][19].highlight();}
                        else{hint(selectedShapeCord);}
                        }
                     else{
                        if (!board_button[19][0].isPlaced()) {
                            board_button[19][0].highlight();}
                        else{hint(selectedShapeCord);}
                        }

        }});


        hintButton.setBounds(779, 11, 89, 42);
        hintButton.setBackground(new Color(86, 140, 48));
        add(hintButton);


        //Top label of Blokus
        JLabel top_label = new JLabel("BLOKUS");
        top_label.setFont(new Font("Tahoma", Font.BOLD, 17));
        top_label.setBounds(60, 11, 81, 53);
        add(top_label);


        setBackground(new Color(175, 217, 85));


    }

    private void setUpPlayers(Color[] color_array, int difficulty, int numPlayers) {
        for(int i=0;i<color_array.length;i++){
            switch (i){
                case 0:
                    allPlayers[i]=new Player(7,3,color_array[i]);
                    allPlayers[i].setBounds(43, 157, 180, 420);
                    allPlayers[i].setPlayerName("Player1");
                    break;
                case 1:
                    if(i<numPlayers){
                        allPlayers[i]=new Player(3,7,color_array[i]);
                        allPlayers[i].setBounds(227, 23, 420, 130);//top most container
                        allPlayers[i].setPlayerName("Player2");
                    }else{
                        allPlayers[i]=new AI(3,7,color_array[i]);
                        allPlayers[i].setBounds(227, 23, 420, 130);//top most container
                        allPlayers[i].setPlayerName("CPU1");
                    }
                    break;
                case 2:
                    if(i<numPlayers){
                        allPlayers[i]=new Player(7,3,color_array[i]);
                        allPlayers[i].setBounds(649, 157, 180, 420);
                        allPlayers[i].setPlayerName("Player3");
                    }
                    else{
                        allPlayers[i]=new AI(7,3,color_array[i]);
                        allPlayers[i].setBounds(649, 157, 180, 420);
                        allPlayers[i].setPlayerName("CPU2");
                    }
                    break;
                case 3:
                    if(i<numPlayers){
                        allPlayers[i]=new Player(3,7,color_array[i]);
                        allPlayers[i].setBounds(227, 580, 420, 130);//bottom container
                        allPlayers[i].setPlayerName("Player4");
                    }else{
                        allPlayers[i]=new AI(3,7,color_array[i]);
                        allPlayers[i].setBounds(227, 580, 420, 130);//bottom container
                        allPlayers[i].setPlayerName("CPU3");
                    }
                    break;
            }
            gridLayout_1 = (GridLayout) allPlayers[i].getLayout();// left most container
            gridLayout_1.setVgap(3);
            gridLayout_1.setHgap(3);
            add(allPlayers[i]);
            allPlayers[i].setBoard(this);
            allPlayers[i].setBackground(new Color(175, 217, 85));
        }
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
