
public class Player extends Shape{
    private int playerColor;
    int index;
    
    public Player(int x, int y , int clr) {
    	super(x,y,clr);
    	this.playerColor=clr;
    	
    }
}