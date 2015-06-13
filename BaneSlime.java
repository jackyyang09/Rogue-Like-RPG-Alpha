import greenfoot.*;
import java.util.Random;

/**
 * Write a description of class BaneSlime here.
 * 
 * @author (Kajamugesh Raneethran) 
 * @version (a version number or a date)
 */
public class BaneSlime extends Enemy
{
    Random r;

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
        levelGenerate(((ScrollingMap)getWorld()).getFloor());
        //grid2 = ((ScrollingMap)getWorld()).getGrid();
        mapX = getMapX;
        mapY = getMapY;
    }

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
