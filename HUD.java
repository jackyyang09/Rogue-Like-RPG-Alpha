import greenfoot.*;
import java.awt.Color;
import java.awt.Font;
/**
 * Displays player information and controls displays
 * 
 * WIP 
 * - Action Bar
 * - Profile Picture
 * - Minimap(?)
 * 
 * @author Jacky Yang
 * @version (a version number or a date)
 */
public class HUD extends Actor
{
    ValueBox box;
    public HUD()
    {
        box = new ValueBox();
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
        getWorld().addObject(box, 413, 631);
    }
}
