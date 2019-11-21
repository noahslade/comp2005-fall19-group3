import java.awt.Color;

public class Player extends Shape{
    Color playerColor;
    int index;
    public Player(int x, int y , Color clr) {
    	super(x,y,clr);
    	this.playerColor=clr;
    	
    }
}
