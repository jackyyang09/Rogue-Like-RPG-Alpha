import greenfoot.*;
import java.util.Random;

/**
 * Drone enemy
 * 
 * @author (Kajamugesh Raneethran) 
 * @version (June 12 2015)
 */
public class Drone extends Enemy
{
    Random r;

   /**
     * constructor for the Drone class
     * 
     * @param getMapX x-coordinate to be spawned
     * @param getMapY y-coordinate to be spawned 
     */
    public Drone(int getMapX, int getMapY)
    {
            setImage("arrowsmith.png");
            level = 1;
            value = 15;
            xp = 0;
            baseHp = 75;
            currentHp = baseHp;
            baseAtt = 20;
            attack = baseAtt;
            baseDef = 10;
            defense = baseDef;
            baseDex = 15;
            dexterity = baseDex;
            baseHit = 8;
            baseLuk = 5;
            luck = baseLuk;
            baseMove = 4;
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

        if(random < 10){
            convertToTile();
            ((ScrollingMap)getWorld()).inputItem(this.mapX, this.mapY, 12);
            convertToPixel();
        }
    }
}
