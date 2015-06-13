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
            levelGenerate(getWorld().getFloor());

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
