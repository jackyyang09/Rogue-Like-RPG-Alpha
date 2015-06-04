import greenfoot.*;
import java.awt.Font;
import java.util.List;
/**
 * Displays player information
 * 
 * @author Jacky Yang 
 * @version (a version number or a date)
 */
public class ProfileWindow extends Actor
{
    private boolean begin = false;
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
        if (begin == false){begin();}
    }    
    
    public void begin()
    {
        update();
        begin = true;
    }
    
    public void update()
    {
        GreenfootImage image = new GreenfootImage("profile.png");
        //image.setFont(fontName);
        //Code to insert playername here
        image.setFont(fontNums);
        List<Player> Playerlist = getWorld().getObjects(Player.class);
        for(Player P : Playerlist)
        {
            image.drawString(Integer.toString((int)(P.getAtk())), 70, 100);
            image.drawString(Integer.toString((int)(P.getDef())), 195, 100);
            image.drawString(Integer.toString((int)(P.getDex())), 70, 178);
            image.drawString(Integer.toString((int)(P.getLuk())), 195, 178);
        }
        setImage(image);
    }
}
