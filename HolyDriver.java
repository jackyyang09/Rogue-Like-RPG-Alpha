import greenfoot.*;
import java.util.Random;

/**
 * Write a description of class HolyDriver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HolyDriver extends Enemy
{
     Random r;
    public void dropStuff(){
        r = new Random();
        int random = r.nextInt(100);
        
        if(random < 5){
            ((ScrollingMap)getWorld()).inputItem(this.mapX, this.mapY, 12);
        }
        
        random = r.nextInt(100);
        
        if(random == 26){
            ((ScrollingMap)getWorld()).inputItem(this.mapX, this.mapY, 14);
        }
    }
}
