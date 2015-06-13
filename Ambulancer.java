import greenfoot.*;
import java.util.Random;

/**
 * Ambulancer enemy
 * 
 * @author (Kajamugesh Raneethran) 
 * @version (June 12 2015)
 */
public class Ambulancer extends Enemy
{
    Random r;
    
    /**
     * constructor for the Ambulancer class
     * 
     * @param getMapX x-coordinate to be spawned
     * @param getMapY y-coordinate to be spawned 
     */
    public Ambulancer(int getMapX, int getMapY)
    {
            setImage("ambulance.png");
            level = 1;
            value = 39;
            xp = 0;
            baseHp = 50;
            currentHp = baseHp;
            baseAtt = 10;
            attack = baseAtt;
            baseDef = 6;
            defense = baseDef;
            baseDex = 0;
            dexterity = baseDex;
            baseHit = 7;
            baseLuk = 7;
            luck = baseLuk;
            baseMove = 3;
            move = baseMove;
        mapX = getMapX;
        mapY = getMapY;
    }
    
    /**
     * enemy will drop item when defeated, at set drop rate 
     */
    public void dropStuff(){
        r = new Random();
        
        int random = r.nextInt(100);
        if(random < 49){
            convertToTile();
            ((ScrollingMap)getWorld()).inputItem(this.mapX, this.mapY, 11);
            convertToPixel();
        }

        random = r.nextInt(100);
        if(random == 95){
            convertToTile();
            ((ScrollingMap)getWorld()).inputItem(this.mapX, this.mapY, 13);
            convertToPixel();
        }
    }
}
