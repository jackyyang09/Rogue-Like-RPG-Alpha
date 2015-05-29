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
    private final int MAPDEPTH = 4;
    private int leftBound = 0;
    private int bottomBound = MAPHEIGHT;
    private int topBound = MAPHEIGHT - getHeight();
    private int rightBound = getWidth();
    private int x = 0,y = 0;
    private HUD hud = new HUD();
    private int playerX = 2;
    private int playerY = 2;

    private Generate generate = new Generate();
    //private Control c = new Control();

    Actor[][][] field = new Actor[MAPIMGWIDTH][MAPIMGHEIGHT][MAPDEPTH];
    /**
     * Constructor for objects of class ScrollingMap.
     */
    public ScrollingMap()
    {    
        super(946, 774, 1, false);
        setPaintOrder(Items.class, Inventory.class, ValueBox.class, Button.class, HUD.class, Mobs.class, Tile.class);
        createMap(generate.generateBorder());
        //addObject(c,0,0);
        spawnPlayer();
        centerOnPlayer();
        update();
        prepare();
    }

    /**
     * moves the player in the desired direction by one tile
     * @param dir 1 = move down, 2 = move up, 3 = move right, 4 = move left
     */
    public void movePlayer(int dir){
        for(int i = 0; i < 58; i++){
            for(int j = 0; j < 56; j++){
                if(field[i][j][1] != null){
                    if (dir == 1){
                        ((Player)field[i][j][1]).setMapY(((Player)field[i][j][1]).getMapY() + 86);
                        field[playerX][playerY+1][1] = field[playerX][playerY][1];
                        field[playerX][playerY][1] = null;
                        playerY++;
                    }
                    if (dir == 2){
                        ((Player)field[i][j][1]).setMapY(((Player)field[i][j][1]).getMapY() - 86);
                        field[playerX][playerY-1][1] = field[playerX][playerY][1];
                        field[playerX][playerY][1] = null;
                        playerY--;
                    }
                    if (dir == 3){
                        ((Player)field[i][j][1]).setMapX(((Player)field[i][j][1]).getMapX() + 86);
                        field[playerX+1][playerY][1] = field[playerX][playerY][1];
                        field[playerX][playerY][1] = null;
                        playerX++;
                    }
                    if (dir == 4){
                        ((Player)field[i][j][1]).setMapX(((Player)field[i][j][1]).getMapX() - 86);
                        field[playerX-1][playerY][1] = field[playerX][playerY][1];
                        field[playerX][playerY][1] = null;
                        playerX--;
                    }
                    centerOnPlayer();
                    dir = 0;
                } 
            }
        }
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

    public void spawnPlayer(){
        int xCo = playerX * TILESIZE + TILESIZE/2;
        int yCo = playerY * TILESIZE + TILESIZE/2;
        field[playerX][playerY][1] = new Player(xCo, yCo);
        update();
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
    
    public void inputItem(Items item, int xC, int yC)
    {
        xC = (xC - 43) / 86;
        yC = (yC - 43) / 86;
        field[xC][yC][3] = item;
        item.setMapLoc(xC, yC);
    }


    /**
     * Reads Image File
     */
    private void createMap(String[][][] data){
        for(int x = 0; x < MAPIMGWIDTH; x++)
        {
            for(int y = 0;y < MAPIMGHEIGHT;y++)
            {
                for(int d = 0; d < MAPDEPTH; d++){
                    if(data[x][y][d] != null){
                        if(data[x][y][d].equals("border")){
                            field[x][y][d] = new Tile(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2, 1);
                        }
                        if(data[x][y][d].equals("floorTile")){
                            field[x][y][d] = new Tile(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2, 2);
                        }
                        if(data[x][y][d].equals("wall")){
                            field[x][y][d] = new Tile(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2, 3);
                        }
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

    public void centerOnPlayer(){
        int pMapX, pMapY;
        for(int i = 0; i < 58; i++){
            for(int j = 0; j < 56; j++){
                if(field[i][j][1] != null){
                    pMapX = ((Player)field[i][j][1]).getMapX();
                    pMapY = ((Player)field[i][j][1]).getMapY();
                    topBound = pMapY - (getHeight()/2);
                    bottomBound = pMapY + (getHeight()/2);
                    leftBound = pMapX - (getWidth()/2);
                    rightBound = pMapX + (getWidth()/2);
                    update();
                    break;
                }
            }
        }        
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
                        } else if (i == 2){
                            blockX = ((Enemy)field[x][y][i]).mapX;
                            blockY = ((Enemy)field[x][y][i]).mapY;
                        } else if (i == 3){
                            blockX = ((Items)field[x][y][i]).mapX;
                            blockY = ((Items)field[x][y][i]).mapY;
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
        //movePlayer(1);
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

        if(Greenfoot.isKeyDown("k")){
            movePlayer(1);
        }
        if(Greenfoot.isKeyDown("i")){
            movePlayer(2);
        }
        if(Greenfoot.isKeyDown("l")){
            movePlayer(3);
        }
        if(Greenfoot.isKeyDown("j")){
            movePlayer(4);
        }

        update();
    }

    public Actor[][][] getField(){
        return field;
    }
    
     /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare()
    {
        HUD hud = new HUD();
        addObject(hud, 473, 689);
    }
}
