import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.reflect.Array;
import java.util.List;
/**
 * The player character
 * 
 * @author Jacky Yang
 * @version (a version number or a date)
 */
public class Player extends Mobs
{
    private int counter;
    private int armorBuff;
    private boolean speedBoost;
    private Items[] equips, items;
    /**
     * Sets all the base stats
     * 
     * BASE STATS SUBJECT TO CHANGE
     */
    public Player(int mapX, int mapY){
        baseHp = 100;
        currentHp = 100;
        baseAtt = 10;
        attack = baseAtt;
        baseDef = 5;
        defense = baseDef;
        baseMove = 2;
        maxHp = 100;
        move = baseMove;
        baseDex = 10;
        dexterity = baseDex;
        baseLuk = 10;
        luck = baseLuk;
        armorBuff = 0;
        equips = new Items[2];
        items = new Items[9];
        this.mapX = mapX;
        this.mapY = mapY;
        begin();
    }

    /**
     * Code that runs only once
     * Prepares a special image size for future use
     */
    public void begin()
    {
        GreenfootImage image = new GreenfootImage(144, 86);
        setImage("player2.png");
        image.drawImage(getImage(), 29, 0);
        setImage(image);
    }

    public void act()
    {
        if (speedBoost)
        {
            baseMove += 2;
            speedBoost = false;
        }
        if(xp >= (6/5 * Math.pow(level,3) - 15 * Math.pow(level,2) + 100*level - 140))
        {
            levelUp();
        }
    }

    public void levelUp()
    {
        level++;
    }

    /**
     * Things happen when the Player walks over an item
     */
    public void pickupItem()
    {
        boolean pickup = false; //Enables the pickup code if true
        if (this.isTouching(Items.class)) //Is true when the player walks over an item on the map
        {
            Items drop = (Items)getOneIntersectingObject(Items.class); //Gets a reference to it
            if (equips[0] == null && drop.getEquipType() == 1) //Checks if the sword slot is empty
            {
                equips[0] = drop;
                pickup = true;
            }
            else if (equips[1] == null && drop.getEquipType() == 2) //Checks if the armor slot is empty
            {
                equips[1] = drop;
                pickup = true;
            }
            else if (equips[0] != null || equips[1] != null || drop.getEquipType() == 0)
            {//Puts the item in the inventory if equiop slots are full or if the item type isn't a sword or armor
                for (int i = 0; i < Array.getLength(items); i++) //Searches through the inventory array for an empty slot
                {
                    if (items[i] == null) //Fills the first empty slot if an empty slot is found
                    {
                        items[i] = drop;
                        i = Array.getLength(items);
                        pickup = true;
                    }
                }
            }
            if (pickup) 
            {//If you actually had an available slot in your inventory and put it inside, remove the item from the world
                List<Inventory> inv = getWorld().getObjects(Inventory.class);
                for (Inventory i :inv){i.update();}
                List<ProfileWindow> pro = getWorld().getObjects(ProfileWindow.class);
                for (ProfileWindow p : pro){p.update();}
                removeTouching(Items.class);
                ((ScrollingMap)getWorld()).removeItem(mapX, mapY);
                update();
            }
        }
    }

    /**
     * Updates the player character's image to show his equipment
     */
    public void update()
    {
        GreenfootImage image = new GreenfootImage(144, 86);
        GreenfootImage player = new GreenfootImage("player2.png");
        image.drawImage(player, 29, 0);
        if (equips[0] != null)
        {
            image.drawImage(new GreenfootImage(equips[0].getFileName()), 29, 0);
            image.drawImage(new GreenfootImage("fist.png"), 29, 0);
        }
        if (equips[1] != null)
        {
            image.drawImage(new GreenfootImage(equips[1].getFileName()), 29, 0);
        }
        setImage(image);
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
        Items placeHolder = new Items(); //Placeholder to hold a copy of the item object
        if (slot1 < 9)
        { //If the item is currently within the item slots and not equip slots
            Items currentItem = (Items)Array.get(items, slot1);
            if (slot2 < 9)
            { //If the item to be exchanged is also with the item slots and not equip slots
                placeHolder = (Items)Array.get(items, slot2);
                Array.set(items, slot2, Array.get(items, slot1));
            }
            if (slot2 == 9 && currentItem.getEquipType() == 1){Array.set(equips, slot2 - 10, Array.get(items, slot1));} //Switch with sword
            if (slot2 == 10 && currentItem.getEquipType() == 2){Array.set(equips, slot2 - 10, Array.get(items, slot1));} //Switch with armor
            Array.set(items, slot1, placeHolder);
        }
        if (slot1 == 9 || slot1 == 10)
        { //If the item is in the sword or armor equip slots
            if (slot2 < 9)
            { //If the item to be exchanged is within the item slots and not equip slots
                placeHolder = (Items)Array.get(items, slot2);
                Array.set(items, slot2, Array.get(equips, slot1 - 9));
                Array.set(equips, slot1 - 9, placeHolder);
            }
            List<Inventory> inv = getWorld().getObjects(Inventory.class);
            for (Inventory i : inv){i.update();} //Update inventory to reflect change in inventory 
        }
        List<ProfileWindow> pro = getWorld().getObjects(ProfileWindow.class);
        for (ProfileWindow p : pro){p.update();} //Update profile window to reflect change in stats
        update();
    }

    /**
     * Drops an item onto the map and removes it from the inventory
     * 
     * @param item Index of the item to be dropped within the array
     */
    public void dropItem(int item)
    {
        ScrollingMap map = (ScrollingMap)getWorld();
        //Converts coordinate values
        int valX = (mapX - 43) / 86;
        int valY = (mapY - 43) / 86;
        if (item < 9){
            map.inputItem(valX, valY, items[item].getItemID()); //Adds item to an array in Scrolling Map
            items[item] = null;
        }
        else{
            map.inputItem(valX, valY, equips[item - 9].getItemID()); //Adds item to an array in Scrolling Map
            equips[item - 9] = null;
        }
        List<ProfileWindow> pro = getWorld().getObjects(ProfileWindow.class);
        for (ProfileWindow p : pro){p.update();} //Updates the information in the Profile Window
        update(); //Update Player's graphic
    }

    /**
     * Consumes an item in the inventory and activates an appropriate effect
     * 
     * @param item Index of the item to be dropped within the array
     */

    public void consumeItem(int item)
    {
        if (items[item].getItemID() == 11){healMe(getMaxHp() * 0.2);} //Heal 20% HP if Health Kit is used
        if (items[item].getItemID() == 12){armorBuff += 5;} //Increase defense by 5 permanently if Armor Tuneup is used
        if (items[item].getItemID() == 13){healMe(getMaxHp() * 0.6);} //Heal 60% HP if Big Health Kit is used
        if (items[item].getItemID() == 14){armorBuff += 15;} //Increase defense by 15 permanently if Engineer Toolbox is used
        if (items[item].getItemID() == 15)//Increase speed temporarily for 42 turns if Sanic Soda is used
        {
            speedBoost = true; 
            counter = 42;
        }
        items[item] = null;
        List<ProfileWindow> pro = getWorld().getObjects(ProfileWindow.class);
        for (ProfileWindow p : pro){p.update();}
    }

    /**
     * Returns the a value equalling the Player's attack value after item bonus calculations
     */
    public double getAtk()
    {
        int buff1 = 0;
        int buff2 = 0;
        if (equips[0] != null){buff1 = equips[0].getAtkBuff();}
        if (equips[1] != null){buff2 = equips[1].getAtkBuff();}
        return baseAtt + buff1 + buff2;
    }

    /**
     * Returns the a value equalling the Player's defense value after item bonus calculations
     */
    public double getDef()
    {
        int buff1 = 0;
        int buff2 = 0;
        if (equips[0] != null){buff1 = equips[0].getDefBuff();}
        if (equips[1] != null){buff2 = equips[1].getDefBuff();}
        return baseDef + buff1 + buff2 + armorBuff;
    }

    /**
     * Returns the a value equalling the Player's dexterity value after item bonus calculations
     */
    public double getDex()
    {
        int buff1 = 0;
        int buff2 = 0;
        if (equips[0] != null){buff1 = equips[0].getDexBuff();}
        if (equips[1] != null){buff2 = equips[1].getDexBuff();}
        return baseDex + buff1 + buff2;
    }

    /**
     * Returns the a value equalling the Player's luck value after item bonus calculations
     */
    public double getLuk()
    {
        int buff1 = 0;
        int buff2 = 0;
        if (equips[0] != null){buff1 = equips[0].getLukBuff();}
        if (equips[1] != null){buff2 = equips[1].getLukBuff();}
        return baseLuk + buff1 + buff2;
    }

    /**
     * Returns an array of items within Player
     */
    public Items[] getItems()
    {
        return items;
    }

    /**
     * Returns an array of equippable items within player
     */
    public Items[] getEquips()
    {
        return equips;
    }

    /**
     * Sets the Player's mapX value
     * 
     * @param newMapX The new value
     */
    public void setMapX(int newMapX)
    {
        mapX = newMapX;
    }

    /**
     * Sets the Player's mapY value
     * 
     * @param newMapY The new value
     */
    public void setMapY(int newMapY)
    {
        mapY = newMapY;
    }

    /**
     * Returns the Player's mapX value
     */
    public int getMapX()
    {
        return mapX;
    }

    /**
     * Returns the Player's mapX value
     */
    public int getMapY()
    {
        return mapY;
    }

    /**
     * Counts down the Player's timer for effect durations
     */
    public void count()
    {
        if(counter > 0){counter--;}
    }
}
