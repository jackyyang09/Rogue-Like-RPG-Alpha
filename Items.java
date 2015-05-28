import greenfoot.*;

/**
 * Universal Item class
 * 
 * @author Jacky Yang
 * @version (a version number or a date)
 */
public class Items extends Actor
{
    private int id;
    private String name;
    private int equipType;
    private int atkBuff;
    private int defBuff;
    private boolean begin;
    private boolean inventory;
    public Items()
    {
        //Inventory
    }
    
    public Items(int num, boolean inventory)
    {
        if (num == 1)
        {
            id = num;
            name = "Beam Blade";
            equipType = 1;
            this.inventory = inventory;
            if (inventory == true){setImage("beamblade3.png");}
            else{setImage("beamblade1.png");}
            atkBuff = 2;
            defBuff = 0;
        }
    }

    public void act()
    {
        if (begin == false)
        {
            prevX = getX();
            prevY = getY();
        }
        if (Greenfoot.mousePressed())
        {
            setLocation(Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
        }
        if (Greenfoot.mouseClicked())
        {
            if (checkLoc())
            {
                setLocation(prevX, prevY);
            }
        }
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

    private int checkLoc()
    {
        if (prevX != getX() || prevY != getY()){return true;}
        return false;
    }

    public int getEquipType()
    {
        return equipType;
    }
}
