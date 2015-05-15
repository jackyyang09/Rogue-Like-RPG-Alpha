import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of class ScrollingMap here.
 * 
 * @author Ryan Huang
 * @version MAY 2015
 */
public class ScrollingMap extends World
{
    private final int TILESIZE = 86;
    private final int MAPWIDTH = 50 * TILESIZE;
    private final int MAPHEIGHT = 50 * TILESIZE;
    private final int MAPIMGWIDTH = 50;
    private final int MAPIMGHEIGHT = 50;
    private mapData map = new mapData();
    private GreenfootImage mapImg = map.getImage();
    private int leftBound = 0;
    private int bottomBound = MAPHEIGHT;
    private int topBound = MAPHEIGHT - getHeight();
    private int rightBound = getWidth();
    private List<Tile> blocks = new ArrayList<Tile>();
    private int x = 0,y = 0;

    Tile[][] feild = new Tile[50][50];
    /**
     * Constructor for objects of class ScrollingMap.
     * 
     */
    public ScrollingMap()
    {    
        super(1000, 850, 1, false);
        createMap();
        update();
    }

    /**
     * moves the screen in the desired direction by one tile
     * @param dir 1 = move down, 2 = move up, 3 = move right, 4 = move left
     */
    public void move(int dir){
        if (dir == 1){
            y = TILESIZE;
            shiftScreen(x,y);
            y = 0;
        }
        if (dir == 2){
            y = -TILESIZE;
            shiftScreen(x,y);
            y = 0;
        }
        if (dir == 3){
            x = TILESIZE;
            shiftScreen(x,y);
            x = 0;
        }
        if (dir == 4){
            x = -TILESIZE;
            shiftScreen(x,y);
            x = 0;
        }
    }

    /**
     * Reads Image File
     */
    private void createMap(){
        for(int x = 0; x < MAPIMGWIDTH; x++)
        {
            for(int y=0;y < MAPIMGHEIGHT;y++)
            {
                int colorRGB = mapImg.getColorAt(x, y).getRGB();
                if(colorRGB == Color.BLACK.getRGB())
                {
                    blocks.add(feild[x][y] = new Tile(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2, 1));
                } else if(colorRGB == Color.BLUE.getRGB()){
                    blocks.add(feild[x][y] = new Tile(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2, 1));
                } else {
                    feild[x][y] = null;
                }
            }
        }
    }

    /**
     * Shifts the viewport left right up down then calls the update method
     */
    private void shiftScreen(int changeX, int changeY)
    {
        //horizontal shifting
        leftBound += changeX;
        rightBound += changeX;
        if(leftBound <0)
        {                
            leftBound = 0;
            rightBound = getWidth();
        } else if(rightBound >= MAPWIDTH)
        {
            rightBound = MAPWIDTH;
            leftBound = MAPWIDTH - getWidth();   
        }
        //vertical shifting
        topBound += changeY;
        bottomBound += changeY;
        if(topBound <0)
        {
            topBound =0;
            bottomBound = getHeight();           
        } else if (bottomBound > MAPHEIGHT)
        {
            bottomBound = MAPHEIGHT;
            topBound = MAPHEIGHT - getHeight();
        }
        update();
    }

    /**
     * Updates the map by dectecting if its currently in the viewport if not remove from world if so add to world
     */
    private void update()
    {
        Tile block;
        int thisPlatformX;
        int thisPlatformY;
        int screenX;
        int screenY;

        for(int i=0; i<blocks.size(); i++)
        {
            block = blocks.get(i);
            thisPlatformX = block.mapX;
            thisPlatformY = block.mapY;

            if(thisPlatformX+TILESIZE>=leftBound && thisPlatformX-TILESIZE<=rightBound && thisPlatformY + TILESIZE >=topBound && thisPlatformY -TILESIZE<= bottomBound)
            {
                screenX = thisPlatformX - leftBound;
                screenY = thisPlatformY - topBound;
                if(block.getWorld()==null)
                {
                    addObject(block, screenX, screenY);
                } else {
                    block.setLocation(screenX, screenY);
                } 
            }else {
                if(block.getWorld()!=null)
                {
                    removeObject(block);     
                }
            }
        }
    }
    
//     /**
//      * Manual testing controls
//      */
//     public void act() 
//     {
//         if(Greenfoot.isKeyDown("s") || Greenfoot.isKeyDown("w")){
//             if(Greenfoot.isKeyDown("s")){
//                 move(1);
//             }
//             if(Greenfoot.isKeyDown("w")){
//                 move(2);
//             }
//         } else {
//             if(Greenfoot.isKeyDown("d")){
//                 move(3);
//             }
//             if(Greenfoot.isKeyDown("a")){
//                 move(4);
//             }
//         }
//     }
}
