import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * the start screen of the game
 * 
 * @author Aingharan Uthayakumar
 * @version June 10
 */
public class StartScreen extends World
{
    private Button beginButton; private Button beginHover;
    private Button settingsButton; private Button settingsHover;
    private Button helpButton; private Button helpHover;
    private Button system; private Button systemHover;
    private Button down; private Button downHover;
    private Button player; 
    private Button sword;
    private boolean spawn = false;
    private GreenfootSound title;
    /**
     * Constructor for objects of class StartScreen.
     * 
     */
    public StartScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(946, 774, 1, false);
        title = new GreenfootSound("title.mp3");
        title.play();
        beginButton = new Button("BEGIN", 150); beginHover = new Button();
        settingsButton = new Button(); settingsHover = new Button();
        helpButton = new Button(); helpHover = new Button();
        system = new Button(); systemHover = new Button();
        down = new Button(); downHover = new Button();
        player = new Button();
        sword = new Button();

    }

    public void act()
    {

        if(spawn == false)
        {
            addObject (beginButton,483,103);beginButton.setImage("Begin.png"); 
            addObject (settingsButton, 468, 695); settingsButton.setImage("Settings.png"); Greenfoot.delay(5);
            addObject (helpButton, 847, 102); helpButton.setImage("Help.png"); Greenfoot.delay(5);
            addObject (sword, 242,420); sword.setImage("Sword.png"); Greenfoot.delay(5);
            addObject(player, 766, 371); player.setImage("PlayerMain.png"); Greenfoot.delay(5);
            addObject (system, 372, 316); system.setImage("System.png"); Greenfoot.delay(5);
            addObject (down, 656, 437); down.setImage("Down.png"); 
            spawn = true;
        }
        checkMouse();
        hoverDetect();
    }

   /**
     * checks mouse clicks and reacts
     */
    private void checkMouse()
    {
        if (Greenfoot.mouseClicked(null))
        {
            if (Greenfoot.mouseClicked(beginButton) || Greenfoot.mouseClicked(beginHover))
            {
                title.stop();
                Greenfoot.setWorld(new CharacterSelection());
            }
            else if (Greenfoot.mouseClicked(settingsButton) || Greenfoot.mouseClicked(settingsHover))
            {
                Greenfoot.setWorld(new Settings());
            }
            else if (Greenfoot.mouseClicked(helpButton) || Greenfoot.mouseClicked(helpHover))
            {
                Greenfoot.setWorld(new Help());
            }
        }
    }

   /**
     * checks mouse hover and reacts
     */
    private void hoverDetect()
    {
        if(Greenfoot.mouseMoved(beginButton))
        {
            addObject(beginHover,486,101); beginHover.setImage("Begin Hover Text.png");
        }
        else if(Greenfoot.mouseMoved(null))
        {
            removeObject(beginHover);
        }

        if(Greenfoot.mouseMoved(system))
        {
            addObject(systemHover,377,314); systemHover.setImage("final hover SYSTEM.png");
            addObject(downHover,655,437); downHover.setImage("final hover DOWN.png");
        }
        else if(Greenfoot.mouseMoved(null))
        {
            removeObject(downHover);
            removeObject(systemHover);
        }

        if(Greenfoot.mouseMoved(down))
        {
            addObject(systemHover,377,314); systemHover.setImage("final hover SYSTEM.png");
            addObject(downHover,655,437); downHover.setImage("final hover DOWN.png");
        }
        else if(Greenfoot.mouseMoved(null))
        {
            removeObject(downHover);
            removeObject(systemHover);
        }

        if(Greenfoot.mouseMoved(helpButton))
        {
            addObject(helpHover, 847, 101); helpHover.setImage("Help Hover.png");
        }
        else if(Greenfoot.mouseMoved(null))
        {
            removeObject(helpHover);
        }

        if(Greenfoot.mouseMoved(settingsButton))
        {
            addObject(settingsHover, 465, 701); settingsHover.setImage("Settings Hover.png");
        }
        else if(Greenfoot.mouseMoved(null))
        {
            removeObject(settingsHover);
        }
    }
}
