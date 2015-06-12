import greenfoot.*;
import java.awt.Font;
import java.awt.Color;
import java.util.List;
/**
 * Write a description of class ExperienceBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExperienceBar extends Actor
{
    Font font;
    Color yellow, white;
    GreenfootImage bar;
    List<Player> player;
    private boolean begin;
    private int[] xCoords = new int[4];
    private int[] yCoords = new int[4];
    public ExperienceBar()
    {
        begin();
    }
    
    /**
     * Sets up a bunch of values
     */
    public void begin()
    {
        begin = false;
        bar = new GreenfootImage("exp.png");
        bar.setFont( new Font("OCR A Extended", 2, 16));
        yellow = new Color(244, 195, 56);
        white = new Color(255, 255, 255);

        //Setup array of x and y coordinates for bar
        xCoords[0] = 0;
        yCoords[0] = 0;
        xCoords[1] = 732;
        yCoords[1] = 0;
        xCoords[2] = 732;
        yCoords[2] = 25;
        xCoords[3] = 0;
        yCoords[3] = 25;
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
            GreenfootImage image = new GreenfootImage("exp.png");
            bar.drawImage(image, 0, 0);
            bar.setColor(yellow); //Temporarily changes colour to red
            changeValues(p.getXp(), 6/5 * Math.pow(p.getLevel(), 3) - 15 * Math.pow(p.getLevel(), 2) + 100*p.getLevel() - 140);
            bar.fillPolygon(xCoords, yCoords, 4);

            //Draws the text
            bar.setColor(white);
            bar.drawString("EXP: " + Double.toString(p.getXp()) + "/" +  Double.toString(6/5 * Math.pow(p.getLevel(), 3) - 15 * Math.pow(p.getLevel(), 2) + 100*p.getLevel() - 140), 263, 14);
        }
        setImage(bar);
    }

    /**
     * Changes the bar's x coordinates relative to the two values offered
     */
    public void changeValues(double current, double max)
    {
        double value = current/max;
        xCoords[1] = (int)(732 * value);
        xCoords[2] = xCoords[1];
    }
}
