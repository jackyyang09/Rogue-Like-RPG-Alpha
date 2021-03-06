import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.JOptionPane;

/**
 * KEYBINDINGS and other settings
 * 
 * @author Aingharan Uthayakumar
 * @version (June 12 2015)
 */
public class Settings extends World
{
    private Button up; private Button down; private Button left; private Button right;
    private Button chUp; private Button chDown; private Button chLeft; private Button chRight;
    private Button pause; private Button action; private Button pickUp; private Button keyFrame;
    private Button upBindInput; private Button back;

    private String upBind, downBind, leftBind, rightBind, chUpBind, chDownBind, chLeftBind, chRightBind, pauseBind, actionBind, pickUpBind;

    private boolean spawn = false;

    /**
     * Constructor for objects of class Settings.
     * 
     */
    public Settings()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(946, 774, 1);  
        up = new Button(); down = new Button(); left = new Button(); right = new Button();
        chUp = new Button(); chDown = new Button(); chLeft = new Button(); chRight = new Button(); 
        pause = new Button(); action = new Button(); keyFrame = new Button(); pickUp = new Button();
        back = new Button();
        
        
    }

    public void act()

    {
        checkMouse();
        if(!spawn)
        {
            addObject(up, 201 , 41); up.setImage("MoveUp.png"); Greenfoot.delay(5);
            addObject(down, 200, 110); down.setImage("MoveDown.png"); Greenfoot.delay(5);
            addObject(left, 201, 184); left.setImage("MoveLeft.png"); Greenfoot.delay(5);
            addObject(right, 200,256); right.setImage("MoveRight.png"); Greenfoot.delay(5);

            addObject(chUp, 200, 325); chUp.setImage("MoveChUp.png"); Greenfoot.delay(5);
            addObject(chDown, 200, 395); chDown.setImage("MoveChDown.png"); Greenfoot.delay(5);
            addObject(chLeft, 200, 470); chLeft.setImage("MoveChLeft.png"); Greenfoot.delay(5);
            addObject(chRight, 200, 541); chRight.setImage("MoveChRight.png"); Greenfoot.delay(5);

            addObject(action, 200, 610); action.setImage("InteractAttack.png");Greenfoot.delay(5);
            addObject(pickUp, 200, 682); pickUp.setImage("PickUpItem.png"); Greenfoot.delay(5);
            addObject(keyFrame, 700, 360); keyFrame.setImage("KeyFramesAll.png");
            addObject(back, 836, 745); back.setImage("BackButton.png");

        }
    }
   /**
     * master checkMouse method
     */
    private void checkMouse()
    {
        checkMouseMovementKeys();
        checkMouseCrosshairMovement();
        checkMouseOtherKeys();
        if(Greenfoot.mouseClicked(back))
        {
            Greenfoot.setWorld(new StartScreen());
        }
    }

   /**
     * checks mouse movement for keys and reacts
     */
    private void checkMouseMovementKeys()
    {
        if(Greenfoot.mouseClicked(up))
        {
            upBind = (String)JOptionPane.showInputDialog("Pick any key!");
            addObject(new TextField(upBind), 700, 40);
        }
        else if(Greenfoot.mouseClicked(down))
        {
            downBind = (String)JOptionPane.showInputDialog("Pick any key!");
            addObject(new TextField(downBind), 700, 114);
        }
        else if(Greenfoot.mouseClicked(left))
        {
            leftBind = (String)JOptionPane.showInputDialog("Pick any key!");
            addObject(new TextField(leftBind), 700, 185);
        }
        else if (Greenfoot.mouseClicked(right))
        {
            rightBind = (String)JOptionPane.showInputDialog("Pick any key!");
            addObject(new TextField(rightBind), 700, 256);
        }
    }

  /**
     * checks mouse movement for cross hairs and reacts
     */
    private void checkMouseCrosshairMovement()
    {
        if(Greenfoot.mouseClicked(chUp))
        {
            chUpBind = (String)JOptionPane.showInputDialog("Pick any key!");
            addObject(new TextField(chUpBind), 700, 327);
        }
        else if(Greenfoot.mouseClicked(chDown))
        {
            chDownBind = (String)JOptionPane.showInputDialog("Pick any key!");
            addObject(new TextField(chDownBind), 700, 398);
        }
        else if(Greenfoot.mouseClicked(chLeft))
        {
            chLeftBind = (String)JOptionPane.showInputDialog("Pick any key!");
            addObject(new TextField(chLeftBind), 700, 470);
        }
        else if(Greenfoot.mouseClicked(chRight))
        {
            chRightBind = (String)JOptionPane.showInputDialog("Pick any key!");
            addObject(new TextField(chRightBind), 700, 543);
        }
    }

   /**
     * checks mouse movement for other keys and reacts
     */
    private void checkMouseOtherKeys()

    {
        if(Greenfoot.mouseClicked(pickUp))
        {
            pickUpBind = (String)JOptionPane.showInputDialog("Pick any key!");
            addObject(new TextField(pickUpBind), 700, 612);
        }
        else if(Greenfoot.mouseClicked(action))
        {
            actionBind = (String)JOptionPane.showInputDialog("Pick any key!");
            addObject(new TextField(actionBind), 700, 686);
        }
    }

    private static void KeyBindLoader(String[] args)
    {

    }
}
