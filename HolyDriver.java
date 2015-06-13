import greenfoot.*;
import java.util.Random;

/**
 * Write a description of class HolyDriver here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HolyDriver extends Enemy
{
     Random r;
     
     public HolyDriver(int getMapX, int getMapY){
            setImage("beefbot.png");
            level = 1;
            value = 45;
            xp = 0;
            baseHp = 40;
            currentHp = baseHp;
            baseAtt = 6;
            attack = baseAtt;
            baseDef = 5;
            defense = baseDef;
            baseDex = 1;
            dexterity = baseDex;
            baseHit = 5;
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
