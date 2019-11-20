import javax.swing.*;
import java.awt.*;

public class AI extends Shape{
    private int playerColor;
    private int index;
    public AI(int x, int y , int clr) {
        super(x,y,clr);
        this.playerColor=clr;

    }
}