import greenfoot.*;

/**
 * Write a description of class Chest here.
 * 
 * @author Jacky Yang
 * @version (a version number or a date)
 */
public class Chest extends Actor
{
    int mapX, mapY;
    int rarity;
    private boolean open = false;
    /**
     * Constructor for chest
     * 
     * @param mapX The x Location of the chest used by ScrollingMap
     * @param mapY The y Location of the chest used by ScrollingMap
     * @param 
     */
    public Chest(int mapX, int mapY, int rarity)
    {
        setImage("chest.png");
        this.mapX = mapX;
        this.mapY = mapY;
        this.rarity = rarity;
    }

    /**
     * Called by the Player when they interact with the chest
     */
    public void open()
    {
        if (open)
        {
            open = false;
            setImage("chest2.png");
        }
        else
        {
            int valX = (mapX - 43) / 86;
            int valY = (mapY - 43) / 86;
            ((ScrollingMap)getWorld()).inputItem(mapX, mapY, generate(1)); //Adds item to an array in Scrolling Map
            ((ScrollingMap)getWorld()).removeMe(valX, valY, 5);
            getWorld().removeObject(this);
        }
    }

    /**
     * Creates an item according to the rarity (1-15)
     */
    private int generate(int rarity)
    {
        int item = 0;
        int type = Greenfoot.getRandomNumber(100);
        if (rarity == 1)
        {
            if (type < 15){item = 3;} //15% chance of Bane's Bane
            else if (type >= 15 && type < 30){item = 6;} //15% chance of Rustic Rampart
            else if (type >= 30){item = 11;} //70% chance of Health Kit
        }
        if (rarity == 2)
        {
            if (type < 30){item = 3;} //30% chance of Bane's Bane
            else if (type >= 30 && type < 60){item = 6;} //30% chance of Rustic Rampart
            else if (type >= 60){item = 11;} //40% chance of Health Kit
        }
        if (rarity == 3)
        {
            if (type < 15){item = 4;} //15% chance of Czar's Katars
            else if (type >= 15 && type < 30){item = 6;} //15% chance of Rustic Rampart
            else if (type >= 30 && type < 90){item = 11;} //60% chance of Health Kit
            else if (type >= 90){item = 15;} //10% chance of Sanic Soda
        }
        if (rarity == 4)
        {
            if (type < 30){item = 4;} //30% chance of Czar's Katars
            else if (type >= 30 && type < 60){item = 9;} //30% chance of Striking Serpent
            else if (type >= 60 && type < 90){item = 11;} //60% chance of Health Kit
            else if (type >= 90){item = 15;} //10% chance of Sanic Soda
        }
        if (rarity == 5)
        {
            if (type < 15){item = 2;} //15% chance of Electric Edge
            else if (type >= 15 && type < 30){item = 9;} //15% chance of Striking Serpent
            else if (type >= 30 && type < 85){item = 11;} //55% chance of Health Kit
            else if (type >= 85){item = 15;} //15% chance of Sanic Soda
        }
        if (rarity == 6)
        {
            if (type < 15){item = 2;} //15% chance of Electric Edge
            else if (type >= 15 && type < 20){item = 9;} //5% chance of Striking Serpent
            else if (type >= 20 && type < 25){item = 7;} //5% chance of Coloured Camo
            else if (type >= 25 && type < 30){item = 8;} //5% chance of Spectral Screen
            else if (type >= 30 && type < 70){item = 11;} //40% chance of Health Kit
            else if (type >= 70 && type < 80){item = 15;} //10% chance of Sanic Soda
            else if (type >= 80 && type < 95){item = 13;} //15% chance of Large Health Kit
            else if (type >= 95){item = 12;} //5% chance of Armor Tuneup
        }
        if (rarity == 7)
        {
            if (type < 10){item = 1;} //15% chance of Beam Blade
            else if (type >= 10 && type < 15){item = 5;} //5% chance of Soulless Sabre
            else if (type >= 15 && type < 30){item = 9;} //15% chance of Striking Serpent
            else if (type >= 30 && type < 80){item = 11;} //40% chance of Health Kit
            else if (type >= 85){item = 15;} //15% chance of Sanic Soda
        }
        return item;
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
