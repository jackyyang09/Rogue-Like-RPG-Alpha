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
    private boolean open = false;
    public Chest(int rarity, int mapX, int mapY)
    {
        setImage("chest.png");
        generate(rarity);
        this.mapX = mapX;
        this.mapY = mapY;
    }

    /**
     * Act - do whatever the Chest wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //
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
            ScrollingMap map = (ScrollingMap)getWorld();
            //Converts x and y values to values useable by ScrollingMap
            //Potentially unnecessary
            //             int valX = (mapX - 43) / 86;
            //             int valY = (mapY - 43) / 86;
            map.inputItem(valX, valY, generate(1)); //Adds item to an array in Scrolling Map
            map.removeme(mapX, mapY, 4);
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
