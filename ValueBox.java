import greenfoot.*;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
/**
 * Displays values in a bar like thing
 * 
 * @author Jacky Yang
 * @version (a version number or a date)
 */
public class ValueBox extends Actor
{
    Font font;
    Color red, white;
    GreenfootImage bar;
    List<Player> player;
    private boolean begin;
    private int[] xCoords = new int[4];
    private int[] yCoords = new int[4];
    public ValueBox()
    {
        begin();
    }

    public void begin()
    {
        begin = false;
        bar = new GreenfootImage(200, 24);
        bar.setFont( new Font("OCR A Extended", 2, 16));
        red = new Color(255, 0, 0);
        white = new Color(255, 255, 255);

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
        if (begin == false)
        {
            player = getWorld().getObjects(Player.class);
            begin = true;
        }
        update();
    }   

    /**
     * Called by the act class to update the bar according to the player's health
     */
    private void update()
    {
        bar.clear(); //Prepares a blank canvas
        for (Player p : player)
        {
            //Draws the bar image
            bar.setColor(red); //Temporarily changes colour to red
            changeValues(p.getHP(), p.getMaxHP());
            bar.fillPolygon(xCoords, yCoords, 4);

            //Draws the text
            bar.setColor(white);
            bar.drawString(Double.toString(p.getHP()) + "/" + Double.toString(p.getMaxHP()), 50, 16);
        }
        setImage(bar);
    }

    public void changeValues(double current, double max)
    {
        double value = current/max;
        xCoords[1] = (int)(200 * value);
        xCoords[2] = xCoords[1];
    }
}
