import greenfoot.*;

/**
 * A chest that holds items, clicking in close range brings up a dialog box 
 * 
 * @author Jacky Yang
 * @version (a version number or a date)
 */
public class Chest extends Actor
{
    private boolean open = false;
    public Chest(int rarity)
    {
        setImage("chest.png");
        generate(rarity);
    }
    
    /**
     * Act - do whatever the Chest wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
//         if (Greenfoot.mouseClicked(this))
//         {
//             
//         }
    }    
    
    /**
     * Called by the Player when they interact with the chest
     */
    public void open()
    {
        if (open)
        {
            open = false;
            setImage("chest2.png");
        }
        else
        {
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Sets the kind of item it'll drop according to the rarity
     */
    private void generate(int rarity)
    {
    }
}
