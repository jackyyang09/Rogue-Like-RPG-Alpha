import greenfoot.*;

/**
 * Write a description of class Control here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Control extends Actor
{
    /**
     * Manual testing controls
     */
    public void act() 
    {
        if(Greenfoot.isKeyDown("s")){
            ((ScrollingMap)getWorld()).movePlayer(1);
        }
        if(Greenfoot.isKeyDown("w")){
            ((ScrollingMap)getWorld()).movePlayer(2);
        }
        if(Greenfoot.isKeyDown("d")){
            ((ScrollingMap)getWorld()).movePlayer(3);
        }
        if(Greenfoot.isKeyDown("a")){
            ((ScrollingMap)getWorld()).movePlayer(4);
        }
        ((ScrollingMap)getWorld()).update();
    }
}
