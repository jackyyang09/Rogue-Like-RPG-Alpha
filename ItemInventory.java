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
    private boolean drag = false;
    private boolean open = false;
    private boolean close = false;
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
            setImage("beamblade3.png");
            equipType = 1;
        }
        if (num == 2)
        {
            setImage("electricsword3.png");
            equipType = 1;
        }
        id = num;
        setImage("Inventory.png")
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

    public void mouseDetect()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (mouse != null)
        {
            List objects = getWorld().getObjectsAt(mouse.getX(), mouse.getY(), ItemInventory.class);
            if (Greenfoot.mouseDragged(this)){
                setLocation(Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
                secure = false;
                drag = true;
            }
            if (Greenfoot.mouseClicked(this))
            {
                if (this.isTouching(ItemInventory.class))
                {
                    Inventory inv = (Inventory)getOneIntersectingObject(Inventory.class);
                    if (inv.switchSlot(this, (ItemInventory)getOneIntersectingObject(ItemInventory.class)) == true)
                    {
                        setLocation(prevX, prevY);
                    }
                    else
                    {
                        secure = false;
                    }
                }
                else
                {
                    if (checkBoundary())
                    {
                        List<Inventory> inventory = getWorld().getObjects(Inventory.class);
                        for (Inventory i :inventory){i.dropItem(this);}
                        getWorld().removeObject(this);
                    }
                    else
                    {
                        if (checkLoc())
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

    private boolean checkBoundary()
    {
        if (getX() < 488 || getX() > 926 || getY() < 12 || getY() > 581){return true;}
        return false;
    }

    private boolean checkLoc()
    {
        if (prevX != getX() || prevY != getY()){return true;}
        return false;
    }
}
