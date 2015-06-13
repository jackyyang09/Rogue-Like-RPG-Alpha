import greenfoot.*;
import java.util.Random;

/**
 * Write a description of class BaneSlime here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BaneSlime extends Enemy
{
    Random r;
    
    public BaneSlime(int getMapX, int getMapY, int IDnum){
        if (IDnum ==1)
        {
            setImage(".png");
            level = 1;
            value = 25;
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
            move = 0;
            levelGenerate(Greenfoot.getRandomNumber(5) + 1);
        }
        //grid2 = ((ScrollingMap)getWorld()).getGrid();
        mapX = getMapX;
        mapY = getMapY;
    }
    
    public void dropStuff(){
        r = new Random();
        int random = r.nextInt(100);

        if(random < 10){
            ((ScrollingMap)getWorld()).inputItem(this.mapX, this.mapY, 3);
        }
        
        random = r.nextInt(100);
        
        if(random > 89){
            ((ScrollingMap)getWorld()).inputItem(this.mapX, this.mapY, 6);
        }
    }
}
