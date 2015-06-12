import greenfoot.*;
import java.util.Random;

/**
 * Write a description of class SanicRobot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SanicRobot extends Enemy
{
    Random r;
    public SanicRobot(int getMapX, int getMapY, int IDNum){
        if (IDNum ==1)
        {
            setImage("sanicsrobot.png");
            level = 1;
            value = 39;
            xp = 0;
            baseHp = 10;
            currentHp = baseHp;
            baseAtt = 3;
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
        if(random < 50){
            ((ScrollingMap)getWorld()).inputItem(this.mapX, this.mapY, 15);
        }
    }
}
