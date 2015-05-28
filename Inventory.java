import greenfoot.*;
import java.lang.reflect.Array;
import java.util.List;
/**
 * Write a description of class Inventory here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Inventory extends Actor
{
    private Items[] items;
    private Items[] equips;
    private boolean update;
    public Inventory()
    {
        update = false;
    }

    /**
     * Act - do whatever the Inventory wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (update == false)
        {
            update();
        }    
    }

    public void update()
    {
        clear();
        List<Player> Playerlist = getWorld().getObjects(Player.class);
        for(Player P : Playerlist)
        {
            items = P.getItems();
            equips = P.getEquips();
            for (int i = 0; i < Array.getLength(items); i++)
            {
                if (items[i] != null)
                {            
                    if (i == 0){getWorld().addObject(new Items(items[i].getItemID(), true), 582, 111);}
                    if (i == 1){getWorld().addObject(new Items(items[i].getItemID(), true), 708, 111);}
                    if (i == 2){getWorld().addObject(new Items(items[i].getItemID(), true), 830, 111);}
                    if (i == 3){getWorld().addObject(new Items(items[i].getItemID(), true), 582, 237);}
                }else if (items[i] == null)
                {
                    if (i == 0){getWorld().addObject(new Items(), 582, 111);}
                    if (i == 1){getWorld().addObject(new Items(), 708, 111);}
                    if (i == 2){getWorld().addObject(new Items(), 830, 111);}
                    if (i == 3){getWorld().addObject(new Items(), 582, 237);}
                    if (i == 4){getWorld().addObject(new Items(), 582, 237);}
                    if (i == 5){getWorld().addObject(new Items(), 582, 237);}
                }
            }
            for (int i = 0; i < Array.getLength(equips); i++)
            {
                if (equips[i] != null)
                {
                    if (i == 0)
                    {
                        getWorld().addObject(new Items(equips[i].getItemID(), true), 580, 500);
                    }
                }
            }
        }
        update = true;
    }
    
    public void clear()
    {
        List<Items> ItemList = getIntersectingObjects(Items.class);
        for (Items item : ItemList)
        {
            removeTouching(Items.class);
        }
    }

    public void close()
    {
        clear();
        getWorld().removeObject(this);
    }
}
