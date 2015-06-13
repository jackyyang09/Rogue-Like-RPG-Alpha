import greenfoot.*;
import java.util.Random;

/**
 * SanicRobot enemy
 * 
 * @author (Kajamugesh Raneethran) 
 * @version (June 12 2015)
 */
public class SanicRobot extends Enemy
{
    Random r;
    
   /**
     * constructor for the SanicRobot class
     * 
     * @param getMapX x-coordinate to be spawned
     * @param getMapY y-coordinate to be spawned 
     */
    public SanicRobot(int getMapX, int getMapY){
            setImage("sanicsrobot.png");
            level = 1;
            value = 60;
            xp = 0;
            baseHp = 10;
            currentHp = baseHp;
            baseAtt = 6;
            attack = baseAtt;
            baseDef = 5;
            defense = baseDef;
            baseDex = 6;
            dexterity = baseDex;
            baseHit = 3;
            baseLuk = 5;
            luck = baseLuk;
            baseMove = 5;
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
        if(random < 50){
            convertToTile();
            ((ScrollingMap)getWorld()).inputItem(this.mapX, this.mapY, 15);
            convertToPixel();
        }
    }
}
