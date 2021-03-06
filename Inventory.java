import greenfoot.*;
import java.lang.reflect.Array;
import java.util.List;
/**
 * A visual representation of the Player's inventory array
 * 
 * @author Jacky Yang
 * @version (June 12 2015)
 */
public class Inventory extends Actor
{
    private Items[] items;
    private Items[] equips;
    private ItemInventory[] slots;
    private boolean update;
    public Inventory()
    {
        update = false;
        setImage("Inventory.png");
    }

    /**
     * Act - do whatever the Inventory wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (update == false){update();}    
    }

    /**
     * Run once to show the latest values
     * Ocassionally called by other values when changes are made
     */
    public void update()
    {
        clear();
        slots = new ItemInventory[11];
        List<Player> Playerlist = getWorld().getObjects(Player.class);
        for(Player P : Playerlist)
        {
            items = P.getItems();
            equips = P.getEquips();
            for (int i = 0; i < Array.getLength(items); i++)
            {// Checks for empty slots in the inventory and adds in an empty iteminventory in an appropriate slot
                ItemInventory newItem = null;
                if (items[i] != null){newItem = new ItemInventory(items[i].getItemID());}
                else if (items[i] == null){newItem = new ItemInventory(0);}
                if (i == 0){getWorld().addObject(newItem, 582, 115);}
                if (i == 1){getWorld().addObject(newItem, 705, 115);}
                if (i == 2){getWorld().addObject(newItem, 828, 115);}
                if (i == 3){getWorld().addObject(newItem, 582, 239);}
                if (i == 4){getWorld().addObject(newItem, 705, 239);}
                if (i == 5){getWorld().addObject(newItem, 828, 239);}
                if (i == 6){getWorld().addObject(newItem, 582, 363);}
                if (i == 7){getWorld().addObject(newItem, 705, 363);}
                if (i == 8){getWorld().addObject(newItem, 828, 363);}
                slots[i] = newItem;
            }
            for (int i = 0; i < Array.getLength(equips); i++)
            {// Checks for items in the inventory and adds an apprioprate item inventory in an appropriate slot
                ItemInventory newItem = null;
                if (equips[i] != null){newItem = new ItemInventory(equips[i].getItemID());}
                if (equips[i] == null){newItem = new ItemInventory(0);}
                if (i == 0){getWorld().addObject(newItem, 582, 503);}
                if (i == 1){getWorld().addObject(newItem, 705, 503);}
                slots[i + 9] = newItem;
            }
        }
        update = true;
    }

    /**
     * Switches the sprites within the inventory screen and sends a command to Player to switch items in their inventory
     * 
     * @param item1 The item itself
     * @param item2 The item it's touching
     * @return boolean Only used by Items
     */
    public boolean switchSlot(ItemInventory item1, ItemInventory item2)
    {
        int slot1 = 0;
        int slot2 = 0;
        for (int i = 0; i < Array.getLength(slots); i++)
        {// Searches for the instance of the first item
            if (slots[i] == item1)
            {
                slot1 = i;
                i = Array.getLength(slots);
            }
        }
        for (int i = 0; i < Array.getLength(slots); i++)
        {// Searches for the instance of the item to be switched
            if (slots[i] == item2)
            {
                slot2 = i;
                i = Array.getLength(slots);
            }
        }
        boolean pass = false; //Becomes true if the items are qualified to switch
        //Check if you're trying to put a non-weapon into a weapon slot or a non-armor into an armor slot
        if ((slot2 == 9 && item1.getEquipType() == 1) || (slot2 == 10 && item1.getEquipType() == 2)){pass = true;} // Items > Equips
        else if ((slot1 == 9 && item2.getEquipType() == 1) || (slot1 == 10 && item2.getEquipType() == 2)){pass = true;} // Equips > Items
        else if ((slot1 == 9 && item2.getItemID() == 0) || (slot1 == 10 && item2.getItemID() == 0)){pass = true;} // Equips > Empty Items
        else if (slot1 < 9 && slot2 < 9){pass = true;}
        else{return true;}
        if (pass)
        {
            item1.setSecure();
            item2.setSecure();
            List<Player> Playerlist = getWorld().getObjects(Player.class);
            for(Player P : Playerlist)
            {
                if (slot1 < slot2)      
                {// Sets the slot to be switched as the lower value for convinience
                    int hold;
                    hold = slot1;
                    slot1 = slot2;
                    slot2 = hold;
                }
                P.switchSlots(slot1, slot2);
            }
            update = false;
        }
        return false;
    }

    /**
     * Sends a command to the player to drop an item onto the ground
     * 
     * @param item ItemInventory object.
     * @param action 1 for item drop, 2 for item consume
     */
    public void itemInteract(ItemInventory item, int action)
    {
        int index = 0;
        for (int i = 0; i < Array.getLength(slots); i++)
        {// Searches for the index of the item to be dropped
            if (slots[i] == item)
            {
                index = i;
                i = Array.getLength(slots);
            }
        }
        List<Player> player = getWorld().getObjects(Player.class);
        for (Player p :player)
        {
            if (action == 1){p.dropItem(index);}//Tells player to drop the item
            if (action == 2){p.consumeItem(index);}//Tells player to consume the item
        } 
        update = false;
    }

    /**
     * Removes every existance of ItemInventory when the inventory closes
     */
    public void clear()
    {
        List<ItemInventory> ItemList = getIntersectingObjects(ItemInventory.class);
        for (ItemInventory item : ItemList)
        {
            removeTouching(ItemInventory.class);
        }
    }

    public void close()
    {
        clear();
        getWorld().removeObject(this);
    }
}
