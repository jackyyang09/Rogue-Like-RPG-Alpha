import greenfoot.*;
import java.util.Random;

/**
 * Write a description of class Drone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Drone extends Enemy
{
    Random r;
    public void dropStuff(){
        r = new Random();
        int random = r.nextInt(100);
        
        if(random < 10){
            ((ScrollingMap)getWorld()).inputItem(this.mapX, this.mapY, 12);
        }
    }
}
