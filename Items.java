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
            description = "The sword of victory, no promises.\nAffectionately named to avoid\ncopyright laws, apparently\n'Blexcalibur' isn't allowed.";
            atkBuff = 30;
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
            description = "What is a Bane Slime? Why does it\nwear the mask? If you take it\noff, it would be extremely\npainful... For you.";
            atkBuff = 2;
            defBuff = 0;
            dexBuff = 0;
            lukBuff = 0;
        }
        if (num == 4)
        {
            name = "Czar's Katars";
            equipType = 1;
            if (dropped){setImage("qatar3.png");}
            else{setImage("qatar1.png");}
            fileName = "qatar1.png";
            description = "Although the practicality of this\nweapon is quite iffy, you'll be\nable to cut things up just by\npunching them, which is pretty\ncool!";
            atkBuff = 32;
            defBuff = 0;
            dexBuff = 1;
            lukBuff = 0;
        }
        if (num == 5)
        {
            name = "Soulless Sabre";
            equipType = 1;
            if (dropped){setImage("soullesssabre3.png");}
            else{setImage("soullesssabre1.png");}
            fileName = "soullesssabre1.png";
            description = "Imbued with many LEDs and a dark\nenergy, this weapon is guaranteed\nto hurt you and others badly!";
            atkBuff = 666;
            defBuff = -12;
            dexBuff = -1;
            lukBuff = -7;
        }
        if (num == 6)
        {
            name = "Rustic Rampart";
            equipType = 2;
            if (dropped){setImage("rustarmor3.png");}
            else{setImage("rustarmor1.png");}
            fileName = "rustarmor1.png";
            description = "Slimes corrode the armor turning\nit into this mess. But hey, it\nlooks cool.";
            atkBuff = 0;
            defBuff = 3;
            dexBuff = -1;
            lukBuff = 0;
        }
        if (num == 7)
        {
            name = "Coloured Camo";
            equipType = 2;
            if (dropped){setImage("flamboyant3.png");}
            else{setImage("flamboyant1.png");}
            fileName = "flamboyant1.png";
            description = "Rainbow coloured armor is useful\nfor attracting the attention of\nall sorts. Also helps camouflauge\nwith rainbows.";
            atkBuff = 0;
            defBuff = 7;
            dexBuff = 1;
            lukBuff = 7;
        }
        if (num == 8)
        {
            name = "Spectral Screen";
            equipType = 2;
            if (dropped){setImage("spectralscreen3.png");}
            else{setImage("spectralscreen1.png");}
            fileName = "spectralscreen1.png";
            description = "Surprise your friends, shock your\nfamily, and sneak away from your\nenemies with this cowardly\ncloaking device!";
            atkBuff = -10;
            defBuff = -10;
            dexBuff = 10;
            lukBuff = 10;
        } 
        if (num == 9)
        {
            name = "Striking Serpent";
            equipType = 2;
            if (dropped){setImage("light3.png");}
            else{setImage("light1.png");}
            fileName = "light1.png";
            description = "Fly like butterfly, sting like\nbee! With the weight off your\nshoulders, you can run fast and\nkill fast!";
            atkBuff = 5;
            defBuff = -2;
            dexBuff = 10;
            lukBuff = 0;
        } 
        if (num == 10)
        {
            name = "Plated Patriot";
            equipType = 2;
            if (dropped){setImage("heavy3.png");}
            else{setImage("heavy1.png");}
            fileName = "heavy1.png";
            description = "You are unstoppable, a tank, a\njuggernaut! Steamroll past\nheretics in the name of the\nemperor! ONWARDS TO FREEDOM!";
            atkBuff = 0;
            defBuff = 20;
            dexBuff = -10;
            lukBuff = 0;
        } 
        if (num == 11)
        {
            name = "Health Kit";
            equipType = 0;
            setImage("firstaid1.png");
            description = "A small opened case filled with supplies\nfrom bandaids to\npopsicle sticks. Perfect for\nhealing open wounds!";
        }
        if (num == 12)
        {
            name = "Armor Tuneup";
            equipType = 0;
            setImage("armorperk1.png");
            description = "A case full of worktools and\nspare parts fit for making your\nheavy armor a bit heavier than\nbefore.";
        }
        if (num == 13)
        {
            name = "Big Health Kit";
            equipType = 0;
            setImage("firstaidlarge1.png");
            description = "A big case filled with supplies\nfrom splints to sutures. Perfect\nfor healing bumps and bruises!";
        }
        if (num == 14)
        {
            name = "Engineer Toolbox";
            equipType = 0;
            setImage("armorbuff1.png");
            description = "A big case filled with powertools\nakin to your grandmother's basket\nof sewing equipment.";
        }
        if (num == 15)
        {
            name = "Sanic Soda";
            equipType = 0;
            setImage("sanic.png");
            description = "Although critics argue whether\nit's actually the dilluted sweat\nof the elusive Sanic or not, one\ncannot doubt its tangy flavour.";
        }
    }
}
