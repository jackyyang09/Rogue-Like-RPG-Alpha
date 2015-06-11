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
            description = "The sword of victory, no\npromises. Affectionately \nnamed to avoid copyright laws,\napparently 'Blight Blaber' \nisn't allowed.";
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
            description = "What is a Bane Slime? Why \ndoes it wear the mask? If you \ntake it off, it would be\nextremely painful...\nFor you.";
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
            description = "Although the practicality of\nthis weapon is quite iffy,\nyou'll be able to cut\nthings up just by punching\nthem, which is pretty cool!";
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
            description = "Imbued with many LEDs and a\ndark energy, this weapon is\nguaranteed to hurt you and\nothers badly!";
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
            description = "Slimes corrode the armor \nturning it into this mess.\nBut hey, it looks cool.";
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
            description = "Rainbow coloured armor is\nuseful for attracting the\nattention of all sorts.\nAlso helps camouflauge with\nrainbows.";
            atkBuff = 0;
            defBuff = 7;
            dexBuff = 1;
            lukBuff = 7;
        }
        if (num == 11)
        {
            name = "Health Kit";
            equipType = 0;
            setImage("firstaid1.png");
            description = "A small opened case filled \nwith supplies from bandaids\nto popsicle sticks. Perfect\nfor healing open wounds!";
        }
        if (num == 12)
        {
            name = "Armor Tuneup";
            equipType = 0;
            setImage("armorperk1.png");
            description = "A case full of worktools \nand spare parts fit for \nmaking your heavy armor a bit\nheavier than before.";
        }
        if (num == 13)
        {
            name = "Big Health Kit";
            equipType = 0;
            setImage("firstaidlarge1.png");
            description = "A big case filled with \nsupplies from splints to \nsutures. Perfect for healing \nbumps and bruises!";
        }
        if (num == 14)
        {
            name = "Engineer Toolbox";
            equipType = 0;
            setImage("armorbuff1.png");
            description = "A big case filled with \npowertools akin to your \ngrandmother's basket of\nsewing equipment.";
        }
        if (num == 15)
        {
            name = "Sanic Soda";
            equipType = 0;
            setImage("sanic.png");
            description = "Although critics argue whether\nit's actually the dilluted\nsweat of the elusive Sanic or\nnot, one cannot doubt its\ntangy flavour.";
        }
    }
}
