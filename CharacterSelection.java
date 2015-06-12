import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CharacterSelection here.
 * 
 * @author Aingharan Uthayakumar 
 * @version (a version number or a date)
 */
public class CharacterSelection extends World
{
    private Button back; private Button dlcBack; private Button mainBack; private Button begin; private Button lock;
    private Button title; private Button story; private Button instructions;
    private Button marine; 
    private Button marineInfo; private Button dlcInfo;
    
    private boolean spawn = false;
    /**
     * Constructor for objects of class CharacterSelection.
     * 
     */
    public CharacterSelection()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(946, 774, 1); 
        back = new Button(); dlcBack = new Button();
        title = new Button(); marine = new Button(); marineInfo = new Button();
        lock = new Button(); mainBack = new Button(); dlcInfo = new Button();
        begin = new Button(); story = new Button(); instructions = new Button();
        
    }

    public void act()
    {
        if(!spawn)
        {
            Greenfoot.delay(5);
            addObject(title, 473,91); title.setImage("CharacterSelectionText.png");
            Greenfoot.delay(7);
            addObject(marine, 250, 452); marine.setImage("PlayerMarine.png");
            addObject(lock, 717 ,446); lock.setImage("Lock.png");
            Greenfoot.delay(5);
            addObject(mainBack, 836, 745); mainBack.setImage("BackButton.png");
        }
        checkMouse();
    }

    private void checkMouse()
    {
        if(Greenfoot.mouseClicked(marine))
        {
            addObject(marineInfo, 472, 387); marineInfo.setImage("MarineInfo.png");
            addObject(back, 836, 745); back.setImage("BackButton.png");
            addObject(begin, 136, 745); begin.setImage("BeginButton.png");
        }
        else if(Greenfoot.mouseClicked(begin))
        {
            removeObject(marineInfo);
            addObject(story, 472, 387); story.setImage("Story.png");Greenfoot.delay(5);
            removeObject(story); addObject(instructions,472, 387); instructions.setImage("Instructions.png"); Greenfoot.delay(5);
            Greenfoot.setWorld(new ScrollingMap());
        }
        else if(Greenfoot.mouseClicked(back))
        {
            removeObject(marineInfo); removeObject(back); removeObject(story); removeObject(instructions);
        }
        
        if(Greenfoot.mouseClicked(lock))
        {
            addObject(dlcInfo, 472, 387); dlcInfo.setImage("DLCInfo.png");
            addObject(dlcBack, 836, 745); dlcBack.setImage("BackButton.png");
        }
        else if(Greenfoot.mouseClicked(dlcBack))
        {
            removeObject(dlcInfo); removeObject(dlcBack);
        }
        
        if(Greenfoot.mouseClicked(mainBack))
        {
            Greenfoot.setWorld(new StartScreen());
        }
        
    }

}
