import greenfoot.*;
import java.util.List;
import java.awt.Font;
import java.awt.Color;
/**
 * Displays the current
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FloorCount extends Actor
{
    /**
     * Act - do whatever the FloorCount wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        ScrollingMap map = (ScrollingMap)getWorld();
        GreenfootImage image = new GreenfootImage("floorcount.png");
        image.setFont( new Font("OCR A Extended", 2, 52));
        image.setColor(new Color(0, 165, 20));
        image.drawString(Integer.toString(map.getFloor()), 15, 75);
        setImage(image);
    }    
}
