import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class ScrollingMap here.
 * 
 * @author Ryan Huang
 * @version MAY 2015
 */
public class ScrollingMap extends World
{
    private final int TILESIZE = 86;
    private final int MAPWIDTH = 58 * TILESIZE;
    private final int MAPHEIGHT = 56 * TILESIZE;
    private final int MAPIMGWIDTH = 58;
    private final int MAPIMGHEIGHT = 56;
    private final int MAPDEPTH = 3;
    private mapData map = new mapData();
    private GreenfootImage mapImg = map.getImage();
    private int leftBound = 0;
    private int bottomBound = MAPHEIGHT;
    private int topBound = MAPHEIGHT - getHeight();
    private int rightBound = getWidth();
    private int x = 0,y = 0;
    private HUD hud = new HUD();

    Actor[][][] field = new Actor[MAPIMGWIDTH][MAPIMGHEIGHT][MAPDEPTH];
    /**
     * Constructor for objects of class ScrollingMap.
     */
    public ScrollingMap()
    {    
        super(946, 774, 1, false);
        setPaintOrder(Mobs.class, Tile.class);
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
     * State an object at a X and Y coordinate to be added on to the map state the depth too
     * Note: DO NOT PUT OBJECTS IN THE SAME DEPTH
     */
    public void inputObject(int object, int xC, int yC, int d){
        int xCo = xC * TILESIZE + TILESIZE/2;
        int yCo = yC * TILESIZE + TILESIZE/2;
        if(object == 1){
            field[xC][yC][d] = new Player(xCo, yCo);
        }
        if(object == 2){
            field[xC][yC][d] = new Enemy(xCo, yCo);
        }
        update();
    }

    /**
     * Reads Image File
     */
    private void createMap(){
        for(int x = 0; x < MAPIMGWIDTH; x++)
        {
            for(int y = 0;y < MAPIMGHEIGHT;y++)
            {
                int colorRGB = mapImg.getColorAt(x, y).getRGB();
                int xCoord = x * TILESIZE + TILESIZE/2;
                int yCoord = y * TILESIZE + TILESIZE/2;
                if(colorRGB == Color.BLACK.getRGB())
                {
                    field[x][y][0] = new Tile(xCoord, yCoord, 1);
                } else if(colorRGB == Color.BLUE.getRGB()){
                    field[x][y][0] = new Tile(xCoord, yCoord, 2);
                } else {
                    for(int i = 0; i < MAPDEPTH; i++){
                        field[x][y][i] = null;
                    }
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
        if(leftBound <= 0)
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
        if(topBound <= 0)
        {
            topBound = 0;
            bottomBound = getHeight();           
        } else if (bottomBound >= MAPHEIGHT)
        {
            bottomBound = MAPHEIGHT;
            topBound = MAPHEIGHT - getHeight();
        }
        update();
    }

    /**
     * Updates the map by dectecting if its currently in the viewport if not remove from world if so add to world
     */
    public void update()
    {
        Actor block;
        int blockX;
        int blockY;
        int screenX;
        int screenY;
        for(int x = 0; x < MAPIMGWIDTH; x++){
            for(int y = 0; y < MAPIMGHEIGHT; y++){
                for(int i = 0; i < MAPDEPTH; i++)
                {
                    if(field[x][y][i] != null){
                        block = field[x][y][i];
                        if(i == 0){
                            blockX = ((Tile)field[x][y][i]).mapX;
                            blockY = ((Tile)field[x][y][i]).mapY;
                        } else if (i == 1){
                            blockX = ((Player)field[x][y][i]).mapX;
                            blockY = ((Player)field[x][y][i]).mapY;
                        } else {
                            blockX = ((Enemy)field[x][y][i]).mapX;
                            blockY = ((Enemy)field[x][y][i]).mapY;
                        }
                        if(blockX + TILESIZE >= leftBound && blockX - TILESIZE <= rightBound && blockY + TILESIZE >= topBound && blockY - TILESIZE <= bottomBound)
                        {
                            screenX = blockX - leftBound;
                            screenY = blockY - topBound;
                            if(block.getWorld() == null)
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
            }
        }
    }

    /**
     * Manual testing controls
     */
    public void act() 
    {
        if(Greenfoot.isKeyDown("s")){
            move(1);
        }
        if(Greenfoot.isKeyDown("w")){
            move(2);
        }
        if(Greenfoot.isKeyDown("d")){
            move(3);
        }
        if(Greenfoot.isKeyDown("a")){
            move(4);
        }
    }
    
    public Actor[][][] getField(){
        return field;
    }
}
