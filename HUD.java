import greenfoot.*;
import java.awt.Color;
import java.awt.Font;
/**
 * Displays player information and controls other display elements
 * 
 * WIP 
 * - Action meter
 * - Exp bar
 * - Minimap(?)
 * 
 * @author Jacky Yang
 * @version (a version number or a date)
 */
public class HUD extends Actor
{
    private boolean begin;
    private boolean sackopen = false;
    private boolean profileopen = false;
    private ValueBox box;
    private Button bp;
    private Button profile;
    private Inventory inv;
    private MoveCount movecount;
    private ProfileWindow pro;
    public HUD()
    {
        setImage("HUD.png");
        begin = false;
        box = new ValueBox();
        bp = new Button();
        profile = new Button();
        movecount = new MoveCount();
    }
    
    /**
     * Run only once, adds all necessary elements to the screen
     */
    public void begin()
    {
        bp.setImage("Sack.png");
        profile.setImage("profilebutton.png");
        getWorld().addObject(profile, 56, 644);
        getWorld().addObject(bp, 835, 644);
        getWorld().addObject(box, 253, 735);
        getWorld().addObject(movecount, 56, 556);
        begin = true;
    }

    /**
     * Act - do whatever the HUD wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (begin == false){begin();}
        mouseDetectBackpack();
        mouseDetectProfile();
    }   

    /**
     * Detects mouse presses on the backpack button
     */
    private void mouseDetectBackpack()
    {
        if (Greenfoot.mousePressed(bp) == true)
        {
            bp.setImage("SackClick.png");
        }
        if (Greenfoot.mouseClicked(bp) == true)
        {
            if (sackopen == true)
            { 
                inv.close();
                bp.setImage("Sack.png");
                sackopen = false;
            }
            else
            {
                inv = new Inventory();
                getWorld().addObject(inv, getX() + 232, getY() - 433);
                bp.setImage("SackOpen.png");
                sackopen = true;
            }
        }
        else if (Greenfoot.mouseClicked(null) == true)
        {
            if (sackopen == false){bp.setImage("Sack.png");}
            else{bp.setImage("SackOpen.png");}
        }
    }

    /**
     * Detects mouse presses on the profile button
     */
    private void mouseDetectProfile()
    {
        if (Greenfoot.mousePressed(profile) == true)
        {
            profile.setImage("profilebuttonpressed.png");
        }
        if (Greenfoot.mouseClicked(profile) == true)
        {
            if (profileopen == true)
            { 
                getWorld().removeObject(pro);
                profile.setImage("profilebutton.png");
                profileopen = false;
            }
            else
            {
                pro = new ProfileWindow();
                getWorld().addObject(pro, 271, 542);
                profile.setImage("profilebuttonactive.png");
                profileopen = true;
            }
        }
        else if (Greenfoot.mouseClicked(null) == true)
        {
            if (profileopen == false){profile.setImage("profilebutton.png");}
            else{profile.setImage("profilebuttonactive.png");}
        }
    }
}
