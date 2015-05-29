import greenfoot.*;
import java.util.List;
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
    private int atkBuff, defBuff;
    private boolean begin;
    private boolean inventory;
    private int prevX, prevY;
    private int mapX, mapY;
    public Items()
    {
        setImage("transparent.png");
        id = 0;
    }

    public Items(int num, boolean inventory)
    {
        id = num;
        if (num == 1)
        {
            name = "Beam Blade";
            equipType = 1;
            this.inventory = inventory;
            if (inventory == true){setImage("beamblade3.png");}
            else{setImage("beamblade1.png");}
            atkBuff = 2;
            defBuff = 0;
        }
        if (num == 2)
        {
            name = "Chest";
            equipType = 2;
            this.inventory = inventory;
            if (inventory == true){setImage("chest.png");}
            else{setImage("chest2.png");}
            atkBuff = 2;
            defBuff = 0;
        }
    }

    public Items(int num, boolean inventory, int mapX, int mapY)
    {
        id = num;
        if (num == 1)
        {
            name = "Beam Blade";
            equipType = 1;
            this.inventory = inventory;
            if (inventory == true){setImage("beamblade3.png");}
            else{setImage("beamblade1.png");}
            atkBuff = 2;
            defBuff = 0;
        }
        if (num == 2)
        {
            name = "Chest";
            equipType = 2;
            this.inventory = inventory;
            if (inventory == true){setImage("chest.png");}
            else{setImage("chest2.png");}
            atkBuff = 2;
            defBuff = 0;
        }
        this.mapX = mapX;
        this.mapY = mapY;
    }

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
            if (this.isTouching(Items.class))
            {
                Inventory inv = (Inventory)getOneIntersectingObject(Inventory.class);
                if (inv.switchSlot(this, (Items)getOneIntersectingObject(Items.class)) == true){setLocation(prevX, prevY);}
            }
            else
            {
                if (checkBoundary())
                {
                    List<Player> player = getWorld().getObjects(Player.class);
                    for (Player p :player){p.dropItem(this);}
                    getWorld().removeObject(this);
                }
                else
                {
                    if (checkLoc()){setLocation(prevX, prevY);}
                }
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

    public int getItemID()
    {
        return id;
    }

    public int getEquipType()
    {
        return equipType;
    }

    public boolean getInventory()
    {
        return inventory;
    }

    public void setMapLoc(int x, int y)
    {
        mapX = x;
        mapY = y;
    }
}
