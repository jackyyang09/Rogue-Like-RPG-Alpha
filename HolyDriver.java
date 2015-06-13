import greenfoot.*;
import java.util.Random;

/**
 * Holy Driver Enemy
 * 
 * @author (Kajamugesh Raneethran) 
 * @version (a version number or a date)
 */
public class HolyDriver extends Enemy
{
     Random r;
     
   /** 
     * constructor for the HolyDriver class
     * 
     * @param getMapX x-coordinate to be spawned
     * @param getMapY y-coordinate to be spawned 
     */
     public HolyDriver(int getMapX, int getMapY){
            setImage("beefbot.png");
            level = 1;
            value = 45;
            xp = 0;
            baseHp = 250;
            currentHp = baseHp;
            baseAtt = 50;
            attack = baseAtt;
            baseDef = 35;
            defense = baseDef;
            baseDex = 15;
            dexterity = baseDex;
            baseHit = 5;
            baseLuk = 15;
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
        
        
        if(random < 5){
            convertToTile();
            ((ScrollingMap)getWorld()).inputItem(this.mapX, this.mapY, 12);
            convertToPixel();
        }
        
        random = r.nextInt(100);
        
        if(random == 26){
            convertToTile();
            ((ScrollingMap)getWorld()).inputItem(this.mapX, this.mapY, 14);
            convertToPixel();
        }
    }
}
