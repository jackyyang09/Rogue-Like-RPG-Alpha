import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.reflect.Array;
import java.util.List;
/**
 * Player
 * 
 * @author Jacky Yang
 * @version (a version number or a date)
 */
public class Player extends Mobs
{
    private Items[] equips, items;
    private int itemCount = 0;
    public Player(int mapX, int mapY){
        baseHp = 100;
        baseAtt = 10;
        baseDef = 5;
        baseMov = 2;
        equips = new Items[2];
        items = new Items[10];
        this.mapX = mapX;
        this.mapY = mapY;
        begin();
    }

    public void begin()
    {
        GreenfootImage image = new GreenfootImage(144, 86);
        image.drawImage(getImage(), 29, 0);
        setImage(image);
    }

    public void act()
    {
        pickupItem();
        update();
    }

    /**
     * Things happen when the Player walks over an item
     */
    public void pickupItem()
    {
        if (this.isTouching(Items.class))
        {
            if (itemCount < 10)
            {
                Items drop = (Items)getOneIntersectingObject(Items.class);
                if (drop.getEquipType() == 1)
                {
                    if (equips[0] == null)
                    {
                        equips[0] = drop;
                        itemCount--;
                    }
                    else
                    {
                        items[itemCount] = drop;
                    }
                }
                else if (drop.getEquipType() == 2)
                {
                    if (equips[1] == null)
                    {
                        equips[1] = drop;
                        itemCount--;
                    }
                    else
                    {
                        items[itemCount] = drop;
                    }
                }
                else
                {
                    items[itemCount] = drop;
                }
                itemCount++;
            }
            List<Inventory> inv = getWorld().getObjects(Inventory.class);
            for (Inventory i :inv){i.update();}
            removeTouching(Items.class);
        }
    }

    public void update()
    {
        GreenfootImage image = new GreenfootImage(144, 86);
        GreenfootImage player = new GreenfootImage("player2.png");
        image.drawImage(player, 29, 0);
        if (equips[0] != null)
        {
            image.drawImage(equips[0].getImage(), 29, 0);
        }
        setImage(image);
    }

    public int checkInv()
    {
        return itemCount; 
    }

    /**
     * Deposit an item directly into the inventory
     * Probably only used with chests
     */
    public void addItemInv(Items item)
    {

    }

    /**
     * Switch an item with a different one in a different slot
     * The slot for the item to go into 0 - 9, 10 and 11 are the weapon and armor slots
     * 
     * @param slot1 The current slot
     * @param slot2 The destination slot
     */ 
    public void switchSlots(int slot1, int slot2)
    {
        Items placeHolder = new Items();
        if (slot1 < 10)
        {
            Items currentItem = (Items)Array.get(items, slot1);
            if (slot2 < 10)
            {
                placeHolder = (Items)Array.get(items, slot2);
                Array.set(items, slot2, Array.get(items, slot1));
            }
            //             if (slot2 == 10 && currentItem.getEquipType() == 1){Array.set(equips, slot2 - 10, Array.get(items, slot1));}
            //             if (slot2 == 11 && currentItem.getEquipType() == 2){Array.set(equips, slot2 - 10, Array.get(items, slot1));}
        }
        if (slot1 == 10 || slot1 == 11 && slot2 < 10)
        {
            placeHolder = (Items)Array.get(items, slot2);
            if (slot2 < 10){Array.set(items, slot2, Array.get(equips, slot1 - 10));}
            Array.set(equips, slot1 - 10, placeHolder);
        }
        List<Inventory> inv = getWorld().getObjects(Inventory.class);
        for (Inventory i :inv){i.update();}
    }

    public Items[] getItems()
    {
        return items;
    }

    public Items[] getEquips()
    {
        return equips;
    }

    public void setMapX(int newMapX){
        mapX = newMapX;
    }
    
    public void setMapY(int newMapY){
        mapY = newMapY;
    }
    
    public int getMapX(){
        return mapX;
    }
    
    public int getMapY(){
        return mapY;
    }
}
