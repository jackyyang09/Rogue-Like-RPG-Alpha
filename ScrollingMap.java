import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.Random;

/**
 * Write a description of class ScrollingMap here.
 * 
 * @author Ryan Huang, Jacky Yang
 * @version MAY 2015
 */
public class ScrollingMap extends World
{
    private final int TILESIZE = 86;
    private final int MAPWIDTH = 58 * TILESIZE;
    private final int MAPHEIGHT = 56 * TILESIZE;
    private final int MAPIMGWIDTH = 58;
    private final int MAPIMGHEIGHT = 56;
    private final int MAPDEPTH = 6;
    private int leftBound = 0;
    private int bottomBound = MAPHEIGHT;
    private int topBound = MAPHEIGHT - getHeight();
    private int rightBound = getWidth();
    private int x = 0,y = 0;
    private HUD hud = new HUD();

    private int playerX = 8; // Starting
    private int playerY = 8; // Coords

    private int targetX = -1;
    private int targetY = -1;
    private boolean spawnTarget = true;

    private Generate generate = new Generate();

    Actor[][][] field = new Actor[MAPIMGWIDTH][MAPIMGHEIGHT][MAPDEPTH];
    boolean[][] grid = new boolean[58][56];
    private int floor = 1;
    /**
     * Constructor for objects of class ScrollingMap.
     */
    public ScrollingMap()
    {    
        super(946, 774, 1, false);
        setPaintOrder(ItemInventory.class, InfoTab.class, ProfileWindow.class, MoveCount.class, Button.class, ValueBox.class, HUD.class, Inventory.class, Target.class, Chest.class, Player.class, Items.class, Mobs.class, Tile.class);
        createMap(generate.generateMap());
        playerX = generate.getStartingCoorX();
        playerY = generate.getStartingCoorY();
        spawnPlayer();
        centerOnPlayer();
        update();
        prepare();
        Control c = new Control();
        addObject(c,0,0);
        //         inputObject(2,6,6,2);
    }

    /**
     * moves the player in the desired direction by one tile
     * @param dir 1 = move down, 2 = move up, 3 = move right, 4 = move left
     */
    public boolean movePlayer(int dir){
        for(int i = 0; i < 58; i++){
            for(int j = 0; j < 56; j++){
                if(field[i][j][1] != null){
                    if(((Tile)field[i][j][0]) != null){
                        if (dir == 1 && !((Tile)field[i][j + 1][0]).isAWall && (Enemy)field[i][j + 1][2] == null && (Chest)field[i][j + 1][5] == null){
                            ((Player)field[i][j][1]).setMapY(((Player)field[i][j][1]).getMapY() + 86);
                            field[playerX][playerY+1][1] = field[playerX][playerY][1];
                            field[playerX][playerY][1] = null;
                            playerY++;
                            centerOnPlayer();
                            dir = 0;
                            return true;
                        }
                        if (dir == 2 && !((Tile)field[i][j - 1][0]).isAWall && (Enemy)field[i][j - 1][2] == null && (Chest)field[i][j - 1][5] == null){
                            ((Player)field[i][j][1]).setMapY(((Player)field[i][j][1]).getMapY() - 86);
                            field[playerX][playerY-1][1] = field[playerX][playerY][1];
                            field[playerX][playerY][1] = null;
                            playerY--;
                            centerOnPlayer();
                            dir = 0;
                            return true;
                        }
                        if (dir == 3 && !((Tile)field[i + 1][j][0]).isAWall && (Enemy)field[i + 1][j][2] == null && (Chest)field[i + 1][j][5] == null){
                            ((Player)field[i][j][1]).setMapX(((Player)field[i][j][1]).getMapX() + 86);
                            field[playerX+1][playerY][1] = field[playerX][playerY][1];
                            field[playerX][playerY][1] = null;
                            playerX++;
                            centerOnPlayer();
                            dir = 0;
                            return true;
                        }
                        if (dir == 4 && !((Tile)field[i - 1][j][0]).isAWall && (Enemy)field[i - 1][j][2] == null && (Chest)field[i - 1][j][5] == null){
                            ((Player)field[i][j][1]).setMapX(((Player)field[i][j][1]).getMapX() - 86);
                            field[playerX-1][playerY][1] = field[playerX][playerY][1];
                            field[playerX][playerY][1] = null;
                            playerX--;
                            centerOnPlayer();
                            dir = 0;
                            return true;
                        }
                        dir = 0;
                        return false;
                    }
                } 
            }
        }
        update();
        return true;
    }

    int maxY = 0, minY = 0, maxX = 0, minX = 0;
    public void moveTarget(int dir){
        for(int i = 0; i < 58; i++){
            for(int j = 0; j < 56; j++){
                if(((Target)field[i][j][4]) != null){
                    if (dir == 1 && targetY < maxY && !((Tile)field[i][j + 1][0]).isAWall){
                        ((Target)field[i][j][4]).setMapY(((Target)field[i][j][4]).getMapY() + 86);
                        field[targetX][targetY+1][4] = field[targetX][targetY][4];
                        field[targetX][targetY][4] = null;
                        targetY++;
                    }
                    if (dir == 2 && targetY > minY && !((Tile)field[i][j - 1][0]).isAWall){
                        ((Target)field[i][j][4]).setMapY(((Target)field[i][j][4]).getMapY() - 86);
                        field[targetX][targetY-1][4] = field[targetX][targetY][4];
                        field[targetX][targetY][4] = null;
                        targetY--;
                    }
                    if (dir == 3 && targetX < maxX && !((Tile)field[i + 1][j][0]).isAWall){
                        ((Target)field[i][j][4]).setMapX(((Target)field[i][j][4]).getMapX() + 86);
                        field[targetX+1][targetY][4] = field[targetX][targetY][4];
                        field[targetX][targetY][4] = null;
                        targetX++;
                    }
                    if (dir == 4 && targetX > minX && !((Tile)field[i - 1][j][0]).isAWall){
                        ((Target)field[i][j][4]).setMapX(((Target)field[i][j][4]).getMapX() - 86);
                        field[targetX-1][targetY][4] = field[targetX][targetY][4];
                        field[targetX][targetY][4] = null;
                        targetX--;
                    }
                    dir = 0;
                }
            }
        }
    }

    public void removeTarget(){
        for(int x = 0; x < MAPIMGWIDTH; x++){
            for(int y = 0; y < MAPIMGHEIGHT; y++){
                if((Target)field[x][y][4] != null){
                    spawnTarget = true;
                    removeObject(field[x][y][4]);
                    field[x][y][4] = null;
                }
            }
        }
    }

    public void interact(){
        if(!spawnTarget){
            try{
                for(int x = 0; x < MAPIMGWIDTH; x++){
                    for(int y = 0; y < MAPIMGHEIGHT; y++){
                        if((Player)field[x][y][1] != null){
                            ((Player)field[x][y][1]).attack((Enemy)field[targetX][targetY][2]);
                        }
                    }
                }
            } catch(Exception e){
                System.out.println("NOT AN ENEMY");
            }
        }
    }

    public void spawnTargetIn(){
        for(int x = 0; x < MAPIMGWIDTH; x++){
            for(int y = 0; y < MAPIMGHEIGHT; y++){
                if((Target)field[x][y][4] != null){
                    spawnTarget = false;
                }
                if(spawnTarget && ((Player)field[x][y][1]) != null){
                    field[x][y][4] = new Target(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2);
                    targetX = x;
                    targetY = y;
                    spawnTarget = true;
                    maxY = targetY + 1;
                    minY = targetY - 1;
                    maxX = targetX + 1;
                    minX = targetX - 1;
                }
            }
        }
    }

    public boolean isSpawned(){
        return spawnTarget;
    }

    public void removeMe(int x, int y, int z){
        field[x][y][z] = null;
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
            field[xC][yC][d] = new Enemy(xCo, yCo, 1);
        }
        update();
    }

    public void inputItem(int xC, int yC, int id)
    {
        int xCo = xC * TILESIZE + TILESIZE/2;
        int yCo = yC * TILESIZE + TILESIZE/2;
        field[xC][yC][3] = new Items(id, xCo, yCo);
        update();
    }

    public void removeItem(int xC, int yC)
    {
        xC = (xC - 43) / 86;
        yC = (yC - 43) / 86;
        field[xC][yC][3] = null;
    }

    /**
     * Reads Image File
     */
    private void createMap(String[][][] data){
        //prepare the grid for pathfinding
        for(int i = 0; i < 58; i++){
            for(int j = 0; j < 56; j++){
                grid[i][j] = true;
            }
        }
        for(int x = 0; x < MAPIMGWIDTH; x++)
        {
            for(int y = 0 ; y < MAPIMGHEIGHT;y++)
            {
                for(int d = 0; d < MAPDEPTH; d++){
                    if(data[x][y][0] != null){
                        if(data[x][y][0].equals("door")){
                            field[x][y][0] = new Tile(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2, 1);
                        }
                        else if(data[x][y][0].equals("floorTile")){
                            field[x][y][0] = new Tile(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2, 2);
                        }
                        else if(data[x][y][0].equals("wall")){
                            field[x][y][0] = new Tile(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2, 3);
                            grid[x][y] = false;
                        }
                        else if(data[x][y][0].equals("sPortal")){
                            field[x][y][0] = new Tile(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2, 4);
                        }
                        else if(data[x][y][0].equals("ePortal")){
                            field[x][y][0] = new Tile(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2, 5);
                        }
                    }
                    if(data[x][y][2] != null && data[x][y][2].equals("enemy")){
                        field[x][y][2] = new Enemy(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2, 1);
                    }
                    if(data[x][y][5] != null && data[x][y][5].equals("chest")){
                        field[x][y][5] = new Chest(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2, 1);
                        grid[x][y] = false;
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
                    pMapX = ((Player)field[i][j][1]).getMapY();
                    pMapY = ((Player)field[i][j][1]).getMapX();
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
        for(int x = 0; x < 58; x++){
            for(int y = 0; y < 56; y++){
                for(int i = 0; i < MAPDEPTH; i++)
                {
                    if(field[x][y][i] != null){
                        block = field[x][y][i];
                        if(i == 0){
                            blockX = ((Tile)field[x][y][i]).mapY;
                            blockY = ((Tile)field[x][y][i]).mapX;
                        } else if (i == 1){
                            blockX = ((Player)field[x][y][i]).mapY;
                            blockY = ((Player)field[x][y][i]).mapX;
                        } else if (i == 2){
                            blockX = ((Enemy)field[x][y][i]).mapY;
                            blockY = ((Enemy)field[x][y][i]).mapX;
                        } else if(i == 3){
                            blockX = ((Items)field[x][y][i]).getMapY();
                            blockY = ((Items)field[x][y][i]).getMapX();
                        } else if(i == 4){
                            blockX = ((Target)field[x][y][i]).getMapY();
                            blockY = ((Target)field[x][y][i]).getMapX();
                        } else if(i == 5){
                            blockX = ((Chest)field[x][y][i]).mapY;
                            blockY = ((Chest)field[x][y][i]).mapX;
                        } else {
                            blockX = -1;
                            blockY = -1;
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
                        } else {
                            if(block.getWorld()!=null)
                            {
                                if(i != 2){
                                    removeObject(block);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //     /**
    //      * Manual testing controls
    //      */
    //     public void act() 
    //     {
    //         if(Greenfoot.isKeyDown("k")){
    //             move(1);
    //         }
    //         if(Greenfoot.isKeyDown("i")){
    //             move(2);
    //         }
    //         if(Greenfoot.isKeyDown("l")){
    //             move(3);
    //         }
    //         if(Greenfoot.isKeyDown("j")){
    //             move(4);
    //         }
    //     }

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
        addObject(hud, 472, 735);
    }

    public boolean[][] getGrid(){
        return grid;
    }

    public int getFloor(){
        return floor;
    }

    public void increaseFloor(){
        floor++;
    }
}
