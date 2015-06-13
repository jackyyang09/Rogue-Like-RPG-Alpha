import greenfoot.*;
import java.util.Random;

/**
 * BaneSlime enemy
 * 
 * @author (Kajamugesh Raneethran) 
 * @version (June 12 2015)
 */
public class BaneSlime extends Enemy
{
    Random r;

   /**
     * constructor for the BaneSlime class
     * 
     * @param getMapX x-coordinate to be spawned
     * @param getMapY y-coordinate to be spawned 
     */
    public BaneSlime(int getMapX, int getMapY){
        setImage("slime.png");
        level = 1;
        value = 10;
        xp = 0;
        baseHp = 20;
        currentHp = baseHp;
        baseAtt = 5;
        attack = baseAtt;
        baseDef = 5;
        defense = baseDef;
        baseDex = 0;
        dexterity = baseDex;
        baseHit = 3;
        baseLuk = 5;
        luck = baseLuk;
        baseMove = 2;
        move = baseMove;
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
            ((ScrollingMap)getWorld()).inputItem(this.mapX, this.mapY, 3);
            convertToPixel();
        }

        random = r.nextInt(100);

        if(random > 89){
            convertToTile();
            ((ScrollingMap)getWorld()).inputItem(this.mapX, this.mapY, 6);
            convertToPixel();
        }
    }
}
