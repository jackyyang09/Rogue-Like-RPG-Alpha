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
    protected int id;
    private String name, description, fileName;
    private int equipType;
    private int atkBuff, defBuff, dexBuff, lukBuff;
    private int mapX, mapY;.
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
        database(num);
    }

    /**
     * Main constructor that binds the item to the world
     */
    public Items(int num, int mapX, int mapY)
    {
        database(num);
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
     */
    private void database(int num)
    {
        id = num;
        if (num == 1)
        {
            name = "Beam Blade";
            equipType = 1;
            setImage("beamblade1.png");
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
            setImage("electricsword1.png");
            fileName = "electricsword1.png";
            description = "Hastily built by a fugitive \nengineer in his final moments.\nCareful how you hold it.";
            atkBuff = 4;
            defBuff = -1;
            dexBuff = 0;
            lukBuff = 0;
        }
    }
}
