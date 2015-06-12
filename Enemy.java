import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.LinkedList;
import java.awt.Point;

/**
 * Write a description of class Enemy here.
 * 
 * @author Sean Cao, Ryan Huang, Kajamugesh Raneethran
 * @version (a version number or a date)
 */
public class Enemy extends Mobs
{
    BFSPathFinding bfs = new BFSPathFinding();
    int middle;
    LinkedList<Point> moveTo;
    int tempX;
    int tempY;
    int ID;
    //boolean[][] grid2;
    boolean enemyTurn = false;

    public Enemy(){
    }
    
    /**
     * Constructor for Enemy
     * <p>
     * List of tasks:
     * -Sets image
     * -Sets Base Stats
     * -Sets a UNIQUE ID number
     * -Sets the desired 
     * 
     * @param getMapX
     * @param getMapY
     * @param IDnum
     */
    public Enemy(int getMapX, int getMapY, int IDnum){
        if (IDnum ==1)
        {
            setImage("slime.png");
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
        if (IDnum ==2)
        {
            setImage("ambulance.png");
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
        if (IDnum ==3)
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
        if (IDnum ==4)
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
        if (IDnum ==5)
        {
            setImage("beefbot.png");
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

    /**
     * enemy levels up each stat per level at a moderate pacce
     */
    public void levelUp() 
    {
        baseHp += 5;
        baseAtt += 1;
        baseDef += 1;
        baseDex += 1;
        baseHit += 1;
        baseLuk += 1;
        level++;
    }   

    /**
     * Act Method
     */
    public void act(){
        if(currentHp <= 0){
            convertToTile();
            enemyTurn = false;
            ((ScrollingMap)getWorld()).removeMe(mapX,mapY,2);
            convertToPixel();
            this.dropStuff();
            getWorld().removeObject(this);
        }
        if(enemyTurn && move > 0){
            move--;
            convertToTile();
            for(int i = 0; i < 58; i++){
                for(int j = 0; j < 56; j++){
                    if(((ScrollingMap)getWorld()).field[i][j][1] != null){
                        playerX = i;
                        playerY = j;
                    }
                }
            }
            moveTo = bfs.BFSPathFinding(mapY, mapX, playerY, playerX, ((ScrollingMap)getWorld()).grid);
            bfs.reset();
            if(moveTo.size() == 2){
                moveTo.removeFirst();
                attack((Player)((ScrollingMap)getWorld()).field[moveTo.getFirst().y][moveTo.getFirst().x][1]);
                move = 0;
            }
            else if(moveTo.size() > 1 && moveTo.size() < 17){
                moveTo.removeFirst();   
                tempY = moveTo.getFirst().x;
                tempX = moveTo.getFirst().y;
                ((ScrollingMap)getWorld()).field[tempX][tempY][2] = this;
                ((ScrollingMap)getWorld()).field[mapX][mapY][2] = null;
                mapY = tempY;
                mapX = tempX;
                ((ScrollingMap)getWorld()).grid[tempX][tempY] = false;
                ((ScrollingMap)getWorld()).grid[mapX][mapY] = true;
            }
            convertToPixel();
            ((ScrollingMap)getWorld()).update();
        }
        if(move <= 0){
            enemyTurn = false;
            move = baseMove;
        }
    }

    /**
     * Returns the ID of this specific enemy
     * 
     * @return ID specific ID number for this enemy
     */
    public int getID(){
        return this.ID;
    }

    public void setEnemyTurn(boolean turn){
        if(turn){
            enemyTurn = true;
            move = baseMove;
        } else {
            enemyTurn = false;
            move = 0;
        }
    }
    
    public void dropStuff(){
        
    }
}
