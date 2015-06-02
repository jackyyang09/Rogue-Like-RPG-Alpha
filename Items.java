import greenfoot.*;
/**
 * Universal Item class
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
    private boolean begin;
    private int mapX, mapY;
    public Items()
    {
        setImage("transparent.png");
        id = 0;
    }

    public Items(int num)
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
            name = "Chest";
            equipType = 2;
            setImage("chest2.png");
            fileName = "chest2.png";
            atkBuff = 2;
            defBuff = 0;
        }
    }

    public Items(int num, int mapX, int mapY)
    {
        id = num;
        if (num == 1)
        {
            name = "Beam Blade";
            equipType = 1;
            setImage("beamblade1.png");
            fileName = "beamblade1.png";
            description = "Affectionately named to avoid copyright laws, apparently 'Blight Blaber' isn't allowed.";
            atkBuff = 2;
            defBuff = 0;
        }
        if (num == 2)
        {
            name = "Chest";
            equipType = 2;
            setImage("chest2.png");
            fileName = "chest2.png";
            atkBuff = 2;
            defBuff = 0;
        }
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
}
