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
    int mapX, mapY;
    public Chest(int getMapX, int getMapY, int rarity)
    {
        setImage("chest.png");
        mapX = getMapX;
        mapY = getMapY;
        generate(rarity);
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
            ((ScrollingMap)getWorld()).removeMe((mapX - 43) / 86, (mapY - 43) / 86, 5);
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
