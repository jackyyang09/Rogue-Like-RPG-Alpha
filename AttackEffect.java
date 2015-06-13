import greenfoot.*;

/**
 * An attack animation that plays once and disappears
 * 
 * @author Jacky Yang
 * @version (a version number or a date)
 */
public class AttackEffect extends Actor
{
    private int animTimer = 0;
    
    /**
     * constructor for the AttackEffect class
     */
    
    public AttackEffect()
    {
        setImage("transparent.png");
    }
    
    /**
     * Act - do whatever the AttackEffect wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (animTimer == 0){setImage("attack1.png");}
        if (animTimer == 4){setImage("attack2.png");}
        if (animTimer == 8){setImage("attack3.png");}
        if (animTimer == 12){setImage("attack4.png");}
        if (animTimer == 16){getWorld().removeObject(this);}
        animTimer++;
    }    
}
