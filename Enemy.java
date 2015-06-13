import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.LinkedList;
import java.awt.Point;

/**
 * enemy unit that attacks player
 * 
 * @author Sean Cao, Ryan Huang, Kajamugesh Raneethran
 * @version (June 12 2015)
 */
public abstract class Enemy extends Mobs
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
    
    public abstract void dropStuff();
}
