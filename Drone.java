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
            baseHp = 20;
            currentHp = baseHp;
            baseAtt = 3;
            attack = baseAtt;
            baseDef = 5;
            defense = baseDef;
            baseDex = 0;
            dexterity = baseDex;
            baseHit = 8;
            baseLuk = 5;
            luck = baseLuk;
            baseMove = 4;
            move = baseMove;
            levelGenerate(((ScrollingMap)getWorld()).getFloor());

        //grid2 = ((ScrollingMap)getWorld()).getGrid();
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
