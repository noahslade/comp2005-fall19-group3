import javax.swing.*;
import java.awt.*;

public class AI extends Shape{
    Color playerColor;
    int index;
    public AI(int x, int y , Color clr) {
        super(x,y,clr);
        this.playerColor=clr;

    }
}