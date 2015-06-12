import greenfoot.*;
import java.util.Random;

/**
 * Write a description of class Drone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Drone extends Enemy
{
    Random r;

    public Drone(int getMapX, int getMapY, int IDnum){
        if (IDnum ==1)
        {
            setImage("arrowsmith.png");
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

        if(random < 10){
            ((ScrollingMap)getWorld()).inputItem(this.mapX, this.mapY, 12);
        }
    }
}
