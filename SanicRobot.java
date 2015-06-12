import greenfoot.*;
import java.util.Random;

/**
 * Write a description of class SanicRobot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SanicRobot extends Enemy
{
    Random r;
    public void dropStuff(){
        r = new Random();
        
        int random = r.nextInt(100);
        if(random < 50){
            ((ScrollingMap)getWorld()).inputItem(this.mapX, this.mapY, 15);
        }
    }
}
