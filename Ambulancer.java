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
    
    public Ambulancer(int getMapX, int getMapY)
    {
            setImage("ambulance.png");
            level = 1;
            value = 39;
            xp = 0;
            baseHp = 25;
            currentHp = baseHp;
            baseAtt = 5;
            attack = baseAtt;
            baseDef = 6;
            defense = baseDef;
            baseDex = 0;
            dexterity = baseDex;
            baseHit = 3;
            baseLuk = 7;
            luck = baseLuk;
            baseMove = 3;
            move = baseMove;
            levelGenerate(getWorld().getFloor());
        //grid2 = ((ScrollingMap)getWorld()).getGrid();
        mapX = getMapX;
        mapY = getMapY;
    }
    
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
