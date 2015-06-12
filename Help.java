import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Help here.
 * 
 * @author Aingharan Uthayakumar
 * @version June 2015
 */
public class Help extends World
{
    private boolean spawn = false;

    private Button armor; 
    private Button rust; private Button serpent; private Button camo; 
    private Button spectral; private Button plated;
    private Button rustInfo; private Button serpentInfo; private Button camoInfo;
    private Button spectralInfo; private Button platedInfo;

    private Button weapons;
    private Button monsters; 
    private Button items;

   
    private Button back; private Button rustBack; 
    private Button serpentBack; private Button camoBack; 
    private Button spectralBack; private Button platedBack;
    private Button mainBack; private Button armorBack;

    private Button armorScreen;
    private Button weaponScreen;
    private Button mobScreen;
    private Button itemScreen;

    /**
     * Constructor for objects of class Help.
     * 
     */
    public Help()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(946, 774, 1); 

        armor = new Button(); 

        rust = new Button(); serpent = new Button(); camo = new Button(); 
        spectral = new Button(); plated = new Button();

        rustInfo = new Button(); serpentInfo = new Button(); camoInfo = new Button();
        spectralInfo = new Button(); platedInfo = new Button();

        weapons = new Button(); 
        monsters = new Button();
        items = new Button(); itemScreen = new Button();

        
        back = new Button(); armorBack = new Button(); mainBack = new Button(); rustBack = new Button(); 
        serpentBack = new Button();
        camoBack = new Button(); spectralBack = new Button(); platedBack = new Button();
        addObject(mainBack, 836, 745); mainBack.setImage("BackButton.png");

        armorScreen = new Button();
        weaponScreen = new Button();
        mobScreen = new Button();
        itemScreen = new Button();

    }

    public void act()
    {
        checkMouse();
        if(!spawn)
        {
            Greenfoot.delay(5);
            addObject(armor, 472, 97); armor.setImage("Armor FINAL Text.png");
            Greenfoot.delay(5);
            addObject(weapons, 469, 280); weapons.setImage("WeaponsText.png");
            Greenfoot.delay(5);
            addObject(monsters, 472, 468); monsters.setImage("MonstersText.png");
            Greenfoot.delay(5);
            addObject(items, 477, 667); items.setImage("ItemsText.png");
        }
    }

    private void checkMouse()
    {
        if(Greenfoot.mouseClicked(mainBack))
        {
            Greenfoot.setWorld(new StartScreen()); 
        }

        if(Greenfoot.mouseClicked(armor))
        {
            addObject(armorScreen, 472, 387); armorScreen.setImage("ArmorScreen.png");
            addObject(rust, 129, 191); rust.setImage("RusticRampart.png");
            addObject(serpent, 479, 184); serpent.setImage("Striking Serpent.png");
            addObject(camo, 787, 198); camo.setImage("ColouredCamo.png");
            addObject(spectral, 297, 515); spectral.setImage("SpectralScreenBig.png");
            addObject(plated, 647, 518); plated.setImage("PlatedPatriot.png"); 
            addObject(armorBack, 836, 745); armorBack.setImage("BackButton.png");  
        }
        else if(Greenfoot.mouseClicked(rust))
        {
            addObject(rustInfo, 472, 387); rustInfo.setImage("RusticRampartInfo.png");
            addObject(rustBack, 836, 745); rustBack.setImage("BackButton.png");  

        }
        else if(Greenfoot.mouseClicked(rustBack))
        {
            removeObject(rustInfo); removeObject(rustBack);
        }

        else if(Greenfoot.mouseClicked(serpent))
        {
            addObject(serpentInfo, 472, 387); serpentInfo.setImage("StrikingSerpentInfo.png");
            addObject(serpentBack, 836, 745); serpentBack.setImage("BackButton.png");  
        }
        else if(Greenfoot.mouseClicked(serpentBack))
        {
            removeObject(serpentInfo); removeObject(serpentBack);
        }
        else if(Greenfoot.mouseClicked(camo))
        {
            addObject(camoInfo, 472, 387); camoInfo.setImage("ColouredCamoInfo.png");
            addObject(camoBack, 836, 745); camoBack.setImage("BackButton.png");  
        }
        else if(Greenfoot.mouseClicked(camoBack))
        {
            removeObject(camoInfo); removeObject(camoBack);
        }
        else if(Greenfoot.mouseClicked(spectral))
        {
            addObject(spectralInfo, 472, 387); spectralInfo.setImage("SpectralScreenInfo.png");
            addObject(spectralBack, 836, 745); spectralBack.setImage("BackButton.png");  
        }
        else if(Greenfoot.mouseClicked(spectralBack))
        {
            removeObject(spectralInfo); removeObject(spectralBack);
        }
        else if(Greenfoot.mouseClicked(plated))
        {
            addObject(platedInfo, 472, 387); platedInfo.setImage("PlatedPatriotInfo.png"); 
            addObject(platedBack, 836, 745); platedBack.setImage("BackButton.png");  
        }
        else if(Greenfoot.mouseClicked(platedBack))
        {
            removeObject(platedInfo); removeObject(platedBack);
        }
        else if(Greenfoot.mouseClicked(armorBack))
        {
            removeObject(armorScreen); removeObject(back); removeObject(rust); removeObject(serpent);
            removeObject(camo); removeObject(spectral); removeObject(plated);
            addObject(mainBack, 836, 745);
        }
        

        if(Greenfoot.mouseClicked(weapons))
        {
            addObject(weaponScreen, 472, 387); weaponScreen.setImage("Weapon Screen.png");
            addObject(back, 836,745);

        }
        else if(Greenfoot.mouseClicked(back))
        {
            removeObject(weaponScreen);
            removeObject(back);
            
        }

        if(Greenfoot.mouseClicked(monsters))
        {
            addObject(mobScreen, 472, 387); mobScreen.setImage("MonsterScreen.png");
            addObject(back, 836,745);
        }
        else if(Greenfoot.mouseClicked(back))
        {
            removeObject(mobScreen);
            removeObject(back);
        }

        if(Greenfoot.mouseClicked(items))
        {
            addObject(itemScreen, 472, 387); itemScreen.setImage("ItemsScreen.png");
            addObject(back, 836, 745); back.setImage("BackButton.png");
        }
        else if(Greenfoot.mouseClicked(back))
        {
            removeObject(itemScreen);
            removeObject(back);
            
        }
    }

}
