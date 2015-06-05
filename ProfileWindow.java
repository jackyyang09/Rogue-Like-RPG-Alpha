import greenfoot.*;
import java.awt.Font;
import java.util.List;
/**
 * Displays player information
 * 
 * @author Jacky Yang 
 * @version Beta Release
 */
public class ProfileWindow extends Actor
{
    private boolean update = false;
    Font fontName, fontNums;
    public ProfileWindow()
    {
        fontName = new Font("OCR A Extended", 2, 20);
        fontNums = new Font("OCR A Extended", 0, 30);
    }
    
    /**
     * Act - do whatever the ProfileWindow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (update == false){update();}
    }    
    
    /**
     * Generates the player's information and loads them onto the image
     */
    public void update()
    {
        GreenfootImage image = new GreenfootImage("profile.png"); //Prepare the image to contain all information
        //image.setFont(fontName);
        //Code to insert playername here
        image.setFont(fontNums);
        List<Player> Playerlist = getWorld().getObjects(Player.class);
        for(Player P : Playerlist)
        {
            image.drawString(Integer.toString((int)(P.getAtk())), 70, 100); //Attack stat
            image.drawString(Integer.toString((int)(P.getDef())), 195, 100); //Defense stat
            image.drawString(Integer.toString((int)(P.getDex())), 70, 178); //Dexterity stat
            image.drawString(Integer.toString((int)(P.getLuk())), 195, 178); //Luck stat
        }
        setImage(image);
    }
}
