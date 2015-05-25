import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player
 * 
 * @author Jacky Yang, Kajamaugesh
 * @version (a version number or a date)
 */
public class Player extends Mobs
{
    private Items[] equips;
    private Items[] items;
    private int itemCount = 0;
    public Player(){
        baseHp = 100;
        baseAtt = 10;
        baseDef = 5;
        baseMov = 2;
        equips = new Items[1];
        items = new Items[1];
        begin();
    }

    public void begin()
    {
        GreenfootImage image = new GreenfootImage(115, 86);
        image.drawImage(getImage(), 0, 0);
        setImage(image);
    }

    public void act()
    {
        pickupItem();
        update();
    }

    public void pickupItem()
    {
        if (this.isTouching(Items.class))
        {
            if (itemCount < 1)
            {
                items[itemCount] = (Items)getOneIntersectingObject(Items.class);
                if (items[itemCount].getEquippable() == true)
                {
                    equips[0] = items[itemCount];
                }
                itemCount++;
            }
            removeTouching(Items.class);
        }
    }

    public void update()
    {
        if (equips[0] != null)
        {
            GreenfootImage image = new GreenfootImage(115, 86);
            image.drawImage(getImage(), 0, 0);
            image.drawImage(equips[0].getImage(), 0, 0);
            setImage(image);
        }
    }
}
