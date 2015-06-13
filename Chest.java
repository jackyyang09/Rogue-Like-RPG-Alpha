import greenfoot.*;

/**
 * chest that contains item
 * 
 * @author Jacky Yang, Ryan Huang
 * @version (June 12 2015)
 */
public class Chest extends Actor
{
    int mapX, mapY;
    int rarity;
    private boolean open;
    /**
     * Constructor for chest
     * 
     * @param mapX The x Location of the chest used by ScrollingMap
     * @param mapY The y Location of the chest used by ScrollingMap
     * @param rarity rarity of item in chest
     */
    public Chest(int mapX, int mapY, int rarity)
    {
        open = false;
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
        if (open == false)
        {
            GreenfootSound openSnd = new GreenfootSound("open.wav");
            openSnd.play();
            setImage("chest2.png");
            Greenfoot.delay(30);
            open = true;
        }
        else
        {
            int valX = (mapX - 43) / 86;
            int valY = (mapY - 43) / 86;
            ((ScrollingMap)getWorld()).inputItem(valX, valY, generate(1)); //Adds item to an array in Scrolling Map
            ((ScrollingMap)getWorld()).removeMe(valX, valY, 5); //Removes chest from ScrollingMap's database
            getWorld().removeObject(this);
        }
    }

    /**
     * Creates an item according to the rarity (1-7)
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
            if (type < 14){item = 2;} //14% chance of Electric Edge
            else if (type == 14){item = 5;} //1% chance of Soulless Sabre
            else if (type >= 15 && type < 19){item = 9;} //4% chance of Striking Serpent
            else if (type == 19){item = 10;} //1% chance of Plated Patriot
            else if (type >= 20 && type < 25){item = 7;} //5% chance of Coloured Camo
            else if (type >= 25 && type < 30){item = 8;} //5% chance of Spectral Screen
            else if (type >= 30 && type < 70){item = 11;} //40% chance of Health Kit
            else if (type >= 70 && type < 80){item = 15;} //10% chance of Sanic Soda
            else if (type >= 80 && type < 95){item = 13;} //15% chance of Large Health Kit
            else if (type >= 95){item = 12;} //5% chance of Armor Tuneup
        }
        if (rarity == 7)
        {
            if (type < 10){item = 1;} //10% chance of Beam Blade
            else if (type >= 10 && type < 15){item = 5;} //5% chance of Soulless Sabre
            else if (type >= 15 && type < 20){item = 9;} //5% chance of Striking Serpent
            else if (type >= 20 && type < 25){item = 7;} //5% chance of Coloured Camo
            else if (type >= 25 && type < 30){item = 8;} //5% chance of Spectral Screen
            else if (type >= 30 && type < 35){item = 10;} //5% chance of Plated Patriot
            else if (type >= 35 && type < 60){item = 11;} //25% chance of Health Kit
            else if (type >= 60 && type < 75){item = 13;} //15% chance of Big Health Kit
            else if (type >= 75 && type < 90){item = 15;} //15& chance of Sanic Soda
            else if (type >= 90 && type < 97){item = 12;} //7% chance of Armor Tuneup
            else if (type >= 97){item = 14;} //3% chance of Engineer Toolbox
        }
        return item;
    }

   /**
     * sets x position of chest
     * 
     * @param newMapX The x Location of the chest used by ScrollingMap
     */
    public void setMapX(int newMapX){
        mapX = newMapX;
    }

   /**
     * sets y position of chest
     * 
     * @param newMapY The y Location of the chest used by ScrollingMap
     */
    public void setMapY(int newMapY){
        mapY = newMapY;
    }

   /**
     * returns x position of chest
     * 
     * @return mapX The x Location of the chest used by ScrollingMap
     */
    public int getMapX(){
        return mapX;
    }

   /**
     * returns y position of chest
     * 
     * @return mapY The y Location of the chest used by ScrollingMap
     */
    public int getMapY(){
        return mapY;
    }
}
