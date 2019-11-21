import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
//Classes for saving
import java.io.FileNotFoundException;
import java.io.File;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class Blokus extends JFrame {

	public JPanel getMain_panel() {
		return Main_panel;
	}

	private JPanel Main_panel;
	private JPanel land_panel;
	private Init initiate_panel;
	private ColorSelection color_panel;
	private Board Board_panel;
	private JPanel[] routes= {new Init(this),new ColorSelection(this), new JPanel() ,new JPanel()}; //Setting up the frames

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Blokus frame = new Blokus();
					frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setRoute(String route) {
		if(route=="init") {
			routes[0].setVisible(true);
			routes[1].setVisible(false);
			routes[2].setVisible(false);
			routes[3].setVisible(false);
		}
		else if(route=="color") {
			routes[0].setVisible(false);
			routes[1].setVisible(true);
			routes[2].setVisible(false);
			routes[3].setVisible(false);
		}
		else if(route=="board") {
			routes[0].setVisible(false);
			routes[1].setVisible(false);
			routes[2].setVisible(false);
			routes[3].setVisible(true);
		}
		else if (route=="land"){
			routes[0].setVisible(false);
			routes[1].setVisible(false);
			routes[2].setVisible(true);
			routes[3].setVisible(false);
		}
//		else if(route =="Land"){
//			getMain_panel().add(land_panel);
//			getMain_panel().add(initiate_panel);
//			getMain_panel().add(color_panel);
//		}
	}

//	public void gameInfo(int humanPlayers, int cpuPlayers, int difficultyLevel) {
//		this.humanPlayers = humanPlayers;
//		this.cpuPlayers = cpuPlayers;
//		this.difficultyLevel = difficultyLevel;
//	}
//
	/**
	 * Create the frame.
	 */
	public Blokus() {
		
		// Creating main panel of the frame which is going to contain all the other panels
		Main_panel = new JPanel();
		Main_panel.setLayout(new BorderLayout());
		Main_panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		//All the other panels of the project as of now
		initiate_panel=(Init) routes[0];
		initiate_panel.setVisible(false);
		color_panel=(ColorSelection) routes[1];
		color_panel.setVisible(false);
		color_panel.setBounds(280,150,460,310);
		color_panel.setBackground(new Color (86, 140, 48));
//	    Board_panel=routes[3];
//		Board_panel.setBounds(0,0,1015,720);
//		Board_panel.setVisible(false);
		
		
		
		
		
		System.setProperty("awt.useSystemAAFontSettings","on");
		System.setProperty("swing.aatext", "true");
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1025, 745);
		setResizable(false);
		setContentPane(Main_panel);
		setLocationRelativeTo(null);
		Main_panel.setLayout(null);

		// Main Screen panel which contains logo blokus and start a game button
		land_panel = routes[2];
		land_panel.setBounds(0, 0, 800, 600);
		land_panel.setLayout(null);
		land_panel.setVisible(true);
		land_panel.setBackground(new Color (175, 217, 85));
		

		//Adding all the other panels of the project to the main panel of the frame 
		Main_panel.add(initiate_panel);
		Main_panel.add(color_panel);
		Main_panel.add(land_panel);
//		Main_panel.add(Board_panel);
		
		JLabel blokus_label = new JLabel("Blokus");
		blokus_label.setBounds(400, 70, 185, 45);
		land_panel.add(blokus_label);
		blokus_label.setFont(new Font("Tahoma", Font.BOLD, 55));
		
		JButton start_button = new JButton("Start");
		start_button.setBackground(Color.GREEN);
		start_button.setHorizontalAlignment(SwingConstants.CENTER);
		start_button.setBounds(475, 300, 100, 50);
		land_panel.add(start_button);
		start_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRoute("init");
			}
		});
		start_button.setFont(new Font("Tahoma", Font.BOLD, 25));
		//Load functionality
				JButton load_Button= new JButton("Load");
				load_Button.setBackground(Color.orange);
				load_Button.setHorizontalAlignment(SwingConstants.CENTER);
				load_Button.setBounds(375, 300, 100, 50);
				load_Button.setFont(new Font("Tahoma",Font.BOLD,25));
				land_panel.add(load_Button);
				load_Button.addActionListener(e ->
				{
					loadUpFile();
				});
				

		Main_panel.setBackground(new Color (175, 217, 85));
	}

	public Object getObjectAtIndexRoute(int i) {
		return routes[i];
	}
private void loadUpFile() 
	
	{
		try
		{
			File file = new File("savedDataFinal.txt");
			Scanner sc = new Scanner(file);
			ArrayList<String> arrayFile = new ArrayList<>();
			String line = sc.next();
			//System.out.println("Reading file");
			while(!line.equals(""))
			{
				
					//System.out.println(line);
					arrayFile.add(line);
					if (sc.hasNext())
						line = sc.next();
					else
						break;
			}
			sc.close();
			//System.out.println("Array to check");
			//arrayFile.forEach(n -> System.out.println(n)); //lambda for debugging
			//System.out.println("Size: "+ arrayFile.size());
			
		//#################################### Up until this point, all the data has been correctly read
			String element = arrayFile.remove(0); // This item will be removed and analyzed separately
			
			String[] gameInfo = element.split(",");
			String difficultyAI= gameInfo[1];
			int difficultyLevel= Integer.parseInt(difficultyAI);
			String numHumanPlayers= gameInfo[2];
			int numPlayers = Integer.parseInt(numHumanPlayers);
			
			String currentTurn = gameInfo[3];
			int currentTurnInt = Integer.parseInt(currentTurn);
			
			int[] arrayOfGameData = new int[3];
			arrayOfGameData[0]= difficultyLevel;
			arrayOfGameData[1]= numPlayers;
			arrayOfGameData[2]= currentTurnInt;
			
			//Key represents player's name while value it is the color
			//HashMap<String,String> playersInformation = new HashMap<>();
			ArrayList<String> stateBoard = new ArrayList<>();
			
			
			//This collection will be split into colorGame and nameGame
			String[][] nameAndColor = new String[4][];
			 
			//This collection analyzes the played pieces
			ArrayList<String> playedPieces= new ArrayList<>();
			
			
			int counterPlayer = 0;
			boolean evenNumber=true;  //taking zero as if it were even  
		    while(arrayFile.size()!=0)
		    {
		    	element = arrayFile.remove(0); // Alternating series
		    	
		    	
		    	if (evenNumber)
		    	{
		    		//System.out.println("a");
		    		if(element.equals("Board")) // Delimiter that marks state of board
		    		{
		    			while(arrayFile.size()!=0) // From now we are sure, that all the elements will contain the state of the board
		    			{
		    				element = arrayFile.remove(0);
		    				stateBoard.add(element);
		    				
		    				
		    			}
		    		}
		    		else
		    		{
		    			String playerNameColor = element;
			    		String[] playerInfo= playerNameColor.split(",");
			    		//System.out.println(Arrays.deepToString(playerInfo));
			    		nameAndColor[counterPlayer]= playerInfo;
			    		counterPlayer++;
			    		//playersInformation.put(playerInfo[0], playerInfo[1]); //Alternate option
			    		evenNumber=false; // Process other line
		    		}
		    		
		    		
		    		
		    	}
		    	else // Odd number contains arrays of the player's pieces indexes
		    	{
		    		//System.out.println("b");
		    		
		    		//System.out.println(piecesPlaced);
		    		playedPieces.add(element);
		    		
		    		evenNumber = true; // Now process the other line
		    	}
		    }	
//**********************************************Once Data has been processed ***********************
		    //System.out.println(Arrays.deepToString(nameAndColor));
		    	/*System.out.println("Done");
		    	System.out.println("Difficulty level: "+difficultyAI);
		    	System.out.println("Number of Human Players: "+ numHumanPlayers);
		    	System.out.println("Current turn: "+currentTurn);
		    	
		    	playersInformation.entrySet().forEach(entry->{
		    	    System.out.println(entry.getKey() + " " + entry.getValue());  
		    	 });
		    	stateBoard.forEach(e-> System.out.println(e));
		    	*/
		        //Must solve the issue of how to pass more information to Board
		    			// Changing the constructor with more info could be an option, but it is not a good idea
		    String[] playersName= new String[4];
		    Color[] playersColor = new Color[4];
		    System.out.println(Arrays.deepToString(nameAndColor));
		    for(int i=0; i<4 ; i++)
		    {
		    	for(int j=0; j<2; j++)
		    	{
		    		if (j%2==0) // We know that were are in the player name column
		    		playersName[i]=nameAndColor[i][1]; //Transfering columns 1
		    		else
		    			if(nameAndColor[i][j].equals(Color.RED.toString())) 
		    			playersColor[i]= Color.RED;
		    			else if(nameAndColor[i][j].equals(Color.YELLOW.toString()))
		    			playersColor[i]= Color.YELLOW;
		    			else if(nameAndColor[i][j].equals(Color.GREEN.toString()))
		    			playersColor[i]= Color.GREEN;
		    			else {
		    			playersColor[i]= Color.BLUE;}
		    		
		    	}
		    }
		    	
		    	this.getMain_panel().removeAll();
				this.add(new Board(playersColor, difficultyLevel, numPlayers, this,true,playersName,arrayOfGameData,stateBoard,playedPieces)); 
				this.revalidate();
				this.repaint();
		    	
		    	
		    
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error opening file");
		}
		catch(NoSuchElementException e2)
		{
		
			System.out.println("Error there is one more line");; // Last line of the text file is inserted with a blank line; gives me this error
		}
	
	}
}