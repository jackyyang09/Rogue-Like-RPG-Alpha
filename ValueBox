import greenfoot.*;
import java.awt.Color;
import java.awt.Font;
/**
 * Displays values in a bar like thing
 * 
 * @author Jacky Yang
 * @version (a version number or a date)
 */
public class ValueBox extends Actor
{
    Font font;
    Color red;
    GreenfootImage bar;
    private int[] xCoords = new int[4];
    private int[] yCoords = new int[4];
    public ValueBox()
    {
        begin();
    }

    public void begin()
    {
        bar = new GreenfootImage(200, 24);
        font = new Font("OCR A Extended", 2, 20);
        red = new Color(255, 0, 0);

        //Setup array of x and y coordinates for bar
        xCoords[0] = 0;
        yCoords[0] = 0;
        xCoords[1] = 200;
        yCoords[1] = 0;
        xCoords[2] = 200;
        yCoords[2] = 24;
        xCoords[3] = 0;
        yCoords[3] = 24;
    }
    
    /**
     * Act - do whatever the HUD wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        update();
    }   

    private void update()
    {
        bar.clear();
        bar.setColor(red);
        bar.fillPolygon(xCoords, yCoords, 4);
        setImage(bar);
    }

    public void changeValues(int current, int max)
    {
        double value = (double)current/(double)max;
        xCoords[1] = (int)(200 * value);
        xCoords[2] = xCoords[1];
    }
}
