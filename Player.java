import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Player
 * 
 * @author Jacky Yang
 * @version (a version number or a date)
 */
public class Player extends Mobs
{
    private Items[] equips;
    private Items[] items;
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

    public Items[] getItems()
    {
        return items;
    }

    public Items[] getEquips()
    {
        return equips;
    }
    
        public int getMapX(){
        return mapX;
    }
    
    public int getMapY(){
        return mapY;
    }
}
