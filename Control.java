import greenfoot.*;

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
        if(Greenfoot.isKeyDown("s") && ((ScrollingMap)getWorld()).movePlayer(1)){
            decreasePlayerMove();
        }
        if(Greenfoot.isKeyDown("w") && ((ScrollingMap)getWorld()).movePlayer(2)){
            decreasePlayerMove();
        }
        if(Greenfoot.isKeyDown("d") && ((ScrollingMap)getWorld()).movePlayer(3)){
            decreasePlayerMove();
        }
        if(Greenfoot.isKeyDown("a") && ((ScrollingMap)getWorld()).movePlayer(4)){
            decreasePlayerMove();
        }
        ((ScrollingMap)getWorld()).update();
        if(getPlayerMove() <= 0){
            System.out.println("enemy's turn");
            resetPlayerMove();
        }
    }

    public void decreasePlayerMove(){
        Actor[][][] grid = ((ScrollingMap)getWorld()).getField();
        for(int x = 0; x < 58; x++){
            for(int y = 0; y < 56; y++){
                if(grid[x][y][1] != null){
                    ((Player)grid[x][y][1]).decreaseMove();
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
}
