import greenfoot.*;
/**
 * Universal Item class
 * Also functions as a database for items
 * 
 * @author Jacky Yang
 * @version (a version number or a date)
 */
public class Items extends Actor
{
    private int id;
    private String name, description, fileName;
    private int equipType;
    private int atkBuff, defBuff, dexBuff, lukBuff;
    private int mapX, mapY;
    public Items()
    {
        setImage("transparent.png");
        id = 0;
    }

    /**
     * Basic constructor that functions as a database accessor
     */
    public Items(int num)
    {
        database(num, false);
    }

    /**
     * Main constructor that binds the item to the world
     */
    public Items(int num, int mapX, int mapY)
    {
        database(num, true);
        this.mapX = mapX;
        this.mapY = mapY;
    }

    public void act()
    {
        //
    }

    /**
     * Create custom weapon, debugging purposes
     */
    public Items(String name, int equipType, String image, int atkVar, int defVar)
    {
        this.name = name;
        this.equipType = equipType;
        setImage(image);
        atkBuff = atkVar;
        defBuff = defVar;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public int getItemID()
    {
        return id;
    }

    public int getEquipType()
    {
        return equipType;
    }

    public String getFileName()
    {
        return fileName;
    }

    public int getAtkBuff()
    {
        return atkBuff;
    }

    public int getDefBuff()
    {
        return defBuff;
    }

    public int getDexBuff()
    {
        return dexBuff;
    }

    public int getLukBuff()
    {
        return lukBuff;
    }

    public int getMapX()
    {
        return mapX;
    }

    public int getMapY()
    {
        return mapY;
    }

    public void setMapLoc(int x, int y)
    {
        mapX = x;
        mapY = y;
    }

    /**
     * Sets values of the item upon instantiation
     * 1 to 5 are weapons
     * 6 to 10 are armors
     * 11 to 15 are items
     */
    private void database(int num, boolean dropped)
    {
        id = num;
        if (num == 1)
        {
            name = "Beam Blade";
            equipType = 1;
            if (dropped){setImage("beamblade3.png");}
            else{setImage("beamblade1.png");}
            fileName = "beamblade1.png";
            description = "Affectionately named to avoid \ncopyright laws, apparently \n'Blight Blaber' isn't allowed.";
            atkBuff = 3;
            defBuff = 1;
            dexBuff = 0;
            lukBuff = 0;
        }
        if (num == 2)
        {
            name = "Electric Edge";
            equipType = 1;
            if (dropped){setImage("electricsword3.png");}
            else{setImage("electricsword1.png");}
            fileName = "electricsword1.png";
            description = "Hastily built by a fugitive \nengineer in his final moments.\nCareful how you hold it.";
            atkBuff = 4;
            defBuff = -1;
            dexBuff = 0;
            lukBuff = 0;
        }
        if (num == 3)
        {
            name = "Bane's Bane";
            equipType = 1;
            if (dropped){setImage("banesbane3.png");}
            else{setImage("banesbane1.png");}
            fileName = "banesbane1.png";
            description = "What is a Bane Slime? Why \ndoes it wear the mask? If you \ntake it off, it would be\nextremely painful...\nFor you.";
            atkBuff = 2;
            defBuff = 0;
            dexBuff = 0;
            lukBuff = 0;
        }
        if (num == 6)
        {
            name = "Rustic Rampart";
            equipType = 2;
            if (dropped){setImage("rustarmor3.png");}
            else{setImage("rustarmor1.png");}
            fileName = "rustarmor1.png";
            description = "Slimes corrode the armor \nturning it into this mess.\nBut hey, it looks cool.";
            atkBuff = 0;
            defBuff = 3;
            dexBuff = -1;
            lukBuff = 0;
        }
        if (num == 11)
        {
            name = "Health Kit";
            equipType = 0;
            setImage("firstaid1.png");
            description = "A small opened case filled \nwith supplies from bandaids\nto popsicle sticks. Perfect\nfor healing open wounds!";
        }
        if (num == 12)
        {
            name = "Armor Tuneup";
            equipType = 0;
            setImage("armorperk1.png");
            description = "A case full of worktools \nand spare parts fit for \nmaking your heavy armor a bit\nheavier than before.";
        }
    }
}
