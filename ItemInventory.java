import greenfoot.*;
import java.util.List;
/**
 * Item that only shows up in the inventory screen
 * 
 * @author Jacky Yang
 * @version (a version number or a date)
 */
public class ItemInventory extends Actor
{
    private boolean begin = false;
    private boolean drag = false; //True if item is currently being dragged by the mouse
    private boolean secure = true;//True if the item has left it's slot, useful if the item glitches into the air
    private InfoTab info;
    private int equipType = 0;
    private int prevX, prevY;
    private int id;
    public ItemInventory(int num)
    {
        id = num;
        if (num == 0){setImage("transparent.png");}
        if (num == 1)
        {
            setImage("beamblade2.png");
            equipType = 1;
        }
        if (num == 2)
        {
            setImage("electricsword2.png");
            equipType = 1;
        }
        if (num == 6)
        {
            setImage("rustarmor2.png");
            equipType = 2;
        }
        if (num == 11)
        {
            setImage("firstaid2.png");
            equipType = 0;
        }
        if (num == 12)
        {
            setImage("armorperk2.png");
            equipType = 0;
        }
        id = num;
    }

    /**
     * Act - do whatever the ItemInventory wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (begin == false)
        {
            prevX = getX();
            prevY = getY();
            begin = true;   
        }
        if (id != 0){mouseDetect();}
        if (id != 0){hoverDetect();}
    }    

    /**
     * Detects mouse clickes and presses and runs appropriate functions as necessary
     * Handles item dropping and slot switching
     */
    public void mouseDetect()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null)
        {
            List objects = getWorld().getObjectsAt(mouse.getX(), mouse.getY(), ItemInventory.class);
            if (Greenfoot.mouseDragged(this)){ //Enables ability to drag this object around
                setLocation(Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
                secure = false;
                drag = true;
            }
            if (Greenfoot.mouseClicked(this)) //Things happen when the mouse key is released
            {
                if (this.isTouching(ItemInventory.class))
                {
                    Inventory inv = (Inventory)getOneIntersectingObject(Inventory.class);
                    if (inv.switchSlot(this, (ItemInventory)getOneIntersectingObject(ItemInventory.class)) == true)//Switch slots
                    {
                        setLocation(prevX, prevY);
                        secure = true;
                    }
                    else
                    {
                        secure = false;
                    }
                }
                else
                {
                    if (checkBoundary()) //Drop item onto the map if the item is dragged outside the inventory
                    {
                        List<Inventory> inventory = getWorld().getObjects(Inventory.class);
                        for (Inventory i :inventory){i.dropItem(this);}
                        getWorld().removeObject(this);
                    }
                    else
                    {
                        if (checkLoc()) //Reset the item's location in the inventory if item is dragged to any other location
                        {
                            setLocation(prevX, prevY);
                            secure = true;
                        }
                    }
                }
                drag = false;
            }
        }
    }

    /**
     * Does a check if and where the mouse is hovering and displays an information screen accordingly
     */
    public void hoverDetect()
    {
        if ((Greenfoot.mouseMoved(this) && info == null) && secure == true)
        {
            info = new InfoTab(this);
            getWorld().addObject(info, 243, 127);
        }
        else if ((Greenfoot.mouseMoved(null) && !Greenfoot.mouseMoved(this)) || drag == true)
        {
            getWorld().removeObject(info);
            info = null;
        }
    }

    public int getItemID()
    {
        return id;
    }

    public int getEquipType()
    {
        return equipType;
    }
    
    public void setSecure()
    {
        secure = true;
    }

    /**
     * Checks the item's current position on the screen
     * Returns true if the item is located outside the bounds of the inventory
     */
    private boolean checkBoundary()
    {
        if (getX() < 488 || getX() > 926 || getY() < 12 || getY() > 581){return true;}
        return false;
    }

    /**
     * Checks the item's current position relative to it's original position
     * Returns true if it has changed
     */
    private boolean checkLoc()
    {
        if (prevX != getX() || prevY != getY()){return true;}
        return false;
    }
}
