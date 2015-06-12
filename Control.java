import greenfoot.*;
import java.util.List;
/**
 * Write a description of class Control here.
 * 
 * @author Ryan Huang
 * @version June 2015
 */
public class Control extends Actor
{    
    /**
     * Manual testing controls
     */
    public void act() 
    {
        if(Greenfoot.isKeyDown("d") && ((ScrollingMap)getWorld()).movePlayer(1)){
            decreasePlayerMove(1);
            ((ScrollingMap)getWorld()).removeTarget();
            Greenfoot.delay(3);
        }
        if(Greenfoot.isKeyDown("a") && ((ScrollingMap)getWorld()).movePlayer(2)){
            decreasePlayerMove(1);
            ((ScrollingMap)getWorld()).removeTarget();
            Greenfoot.delay(3);
        }
        if(Greenfoot.isKeyDown("s") && ((ScrollingMap)getWorld()).movePlayer(3)){
            decreasePlayerMove(1);
            ((ScrollingMap)getWorld()).removeTarget();
            Greenfoot.delay(3);
        }
        if(Greenfoot.isKeyDown("w") && ((ScrollingMap)getWorld()).movePlayer(4)){
            decreasePlayerMove(1);
            ((ScrollingMap)getWorld()).removeTarget();
            Greenfoot.delay(3);
        }
        if(!((ScrollingMap)getWorld()).isSpawned()){
            if(Greenfoot.isKeyDown("right")){
                ((ScrollingMap)getWorld()).moveTarget(1);
                Greenfoot.delay(5);
            }
            if(Greenfoot.isKeyDown("left")){
                ((ScrollingMap)getWorld()).moveTarget(2);
                Greenfoot.delay(5);
            }
            if(Greenfoot.isKeyDown("down")){
                ((ScrollingMap)getWorld()).moveTarget(3);
                Greenfoot.delay(5);
            }
            if(Greenfoot.isKeyDown("up")){
                ((ScrollingMap)getWorld()).moveTarget(4);
                Greenfoot.delay(5);
            }
            if(Greenfoot.isKeyDown("space")){
                ((ScrollingMap)getWorld()).interact();
                decreasePlayerMove(getPlayerMove());
                Greenfoot.delay(5);
            }
        } else if(Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("up")){
            ((ScrollingMap)getWorld()).spawnTargetIn();
        }
        if(getPlayerMove() <= 0){
            Greenfoot.delay(3);
            moveEnemy();
            List<Player> player = getWorld().getObjects(Player.class);
            for (Player p : player){p.count();}
            resetPlayerMove();
        }
        ((ScrollingMap)getWorld()).update();
    }

    public void decreasePlayerMove(int amt){
        Actor[][][] grid = ((ScrollingMap)getWorld()).getField();
        for(int x = 0; x < 58; x++){
            for(int y = 0; y < 56; y++){
                if(grid[x][y][1] != null){
                    ((Player)grid[x][y][1]).decreaseMove(amt);
                }
            }
        }
    }

    public int getPlayerMove(){
        Actor[][][] grid = ((ScrollingMap)getWorld()).getField();
        for(int x = 0; x < 58; x++){
            for(int y = 0; y < 56; y++){
                if(grid[x][y][1] != null){
                    return ((Player)grid[x][y][1]).getMove();
                }
            }
        }
        return -1;
    }

    public void resetPlayerMove(){
        Actor[][][] grid = ((ScrollingMap)getWorld()).getField();
        for(int x = 0; x < 58; x++){
            for(int y = 0; y < 56; y++){
                if(grid[x][y][1] != null){
                    ((Player)grid[x][y][1]).resetMove();
                }
            }
        }
    }

    public void moveEnemy(){
        Actor[][][] grid = ((ScrollingMap)getWorld()).getField();
        for(int x = 0; x < 58; x++){
            for(int y = 0; y < 56; y++){
                if(grid[x][y][2] != null){
                    ((Enemy)grid[x][y][2]).setEnemyTurn(true);
                }
            }
        }
    }
}
