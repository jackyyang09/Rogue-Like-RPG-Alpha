import greenfoot.*;
import java.util.List;
/**
 * Write a description of class ItemInventory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ItemInventory extends Actor
{
    private boolean begin = false;
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
            setImage("chest2.png");
            equipType = 2;
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
    }    
    
    public void mouseDetect()
    {
        if (Greenfoot.mouseDragged(this)){setLocation(Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());}
        if (Greenfoot.mouseClicked(this))
        {
            if (this.isTouching(ItemInventory.class))
            {
                Inventory inv = (Inventory)getOneIntersectingObject(Inventory.class);
                if (inv.switchSlot(this, (ItemInventory)getOneIntersectingObject(ItemInventory.class)) == true){setLocation(prevX, prevY);}
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
                    if (checkLoc()){setLocation(prevX, prevY);}
                }
            }
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
