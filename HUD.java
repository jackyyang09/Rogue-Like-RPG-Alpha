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
    private boolean begin;
    private boolean open = false;
    private ValueBox box;
    private Button bp;
    private Inventory inv;
    public HUD()
    {
        begin = false;
        box = new ValueBox();
        bp = new Button();
    }

    public void begin()
    {
        bp.setImage("Sack.png");
        getWorld().addObject(bp, getX() + 200, getY());
        getWorld().addObject(box, 413, 631);
        begin = true;
    }

    /**
     * Act - do whatever the HUD wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (begin == false){begin();}
        mouseDetect();
        update();
    }   

    /**
     * Detects mouse presses
     */
    private void mouseDetect()
    {
        if (Greenfoot.mousePressed(bp) == true)
        {
            bp.setImage("SackClick.png");
        }
        if (Greenfoot.mouseClicked(bp) == true)
        {
            if (open == true)
            { 
                inv.close();
                bp.setImage("Sack.png");
                open = false;
            }
            else
            {
                inv = new Inventory();
                getWorld().addObject(inv, getX() + 232, getY() - 385);
                bp.setImage("SackOpen.png");
                open = true;
            }
        }
        else if (Greenfoot.mouseClicked(null) == true)
        {
            if (open == false){bp.setImage("Sack.png");}
            else{bp.setImage("SackOpen.png");}
        }
    }

    private void update()
    {

    }
}
