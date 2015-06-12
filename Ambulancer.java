import greenfoot.*;
import java.util.Random;

/**
 * Write a description of class Ambulance here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ambulancer extends Enemy
{
    Random r;
    public void dropStuff(){
        r = new Random();
        
        int random = r.nextInt(100);
        if(random < 49){
            ((ScrollingMap)getWorld()).inputItem(this.mapX, this.mapY, 11);
        }

        random = r.nextInt(100);
        if(random == 95){
            ((ScrollingMap)getWorld()).inputItem(this.mapX, this.mapY, 13);
        }
    }
}
