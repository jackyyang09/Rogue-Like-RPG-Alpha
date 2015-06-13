import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.Random;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * the 2d array grid based map in which everything exists
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
    private int endX = 5; //Ending
    private int endY = 5; //Coords

    private int targetX = -1;
    private int targetY = -1;
    private boolean spawnTarget = true;
    private int maxY = 0, minY = 0, maxX = 0, minX = 0;

    private Generate generate = new Generate();
    Scanner scan;
    Actor[][][] field = new Actor[MAPIMGWIDTH][MAPIMGHEIGHT][MAPDEPTH];
    boolean[][] grid = new boolean[58][56];
    private int floor = 1;
    private int floorR = 1;
    private GreenfootSound bgm = new GreenfootSound("bgm2.mp3");
    /**
     * Constructor for objects of class ScrollingMap.
     */
    public ScrollingMap()
    {    
        super(946, 774, 1, false);
        setPaintOrder(ItemInventory.class, InfoTab.class, ProfileWindow.class, MoveCount.class, Button.class, ValueBox.class, Inventory.class, ExperienceBar.class, HUD.class, FloorCount.class, FadingWord.class, AttackEffect.class, Target.class, Chest.class, Player.class, Items.class, Mobs.class, Tile.class);
        generate.setMaxEnemies(20);
        createMap(generate.generateMap());
        playerX = generate.getStartingCoorX();
        playerY = generate.getStartingCoorY();
        endX = generate.getEndingCoorX();
        endY = generate.getEndingCoorY();
        spawnPlayer();
        centerOnPlayer();
        update();
        prepare();
        Control c = new Control();
        addObject(c,0,0);
    }
        public void started(){
        bgm.playLoop();
    }

    public void stopped(){
        bgm.pause();
    }
    /**
     * Constructor for objects of class ScrollingMap.
     */
    public ScrollingMap(Player player, int fl)
    {    
        super(946, 774, 1, false);
        setPaintOrder(ItemInventory.class, InfoTab.class, ProfileWindow.class, MoveCount.class, Button.class, ValueBox.class, HUD.class, Inventory.class, FloorCount.class, ExperienceBar.class, Target.class, Chest.class, Player.class, Items.class, Mobs.class, Tile.class);
        generate.setMaxEnemies(20);
        createMap(generate.generateMap());
        int playerX2 = generate.getStartingCoorX();
        int playerY2 = generate.getStartingCoorY();
        playerX = playerX2;
        playerY = playerY2;
        endX = generate.getEndingCoorX();
        endY = generate.getEndingCoorY();
        spawnPlayer(getFileBack());
        //spawnPlayer(player, playerX2, playerY2);
        centerOnPlayer();
        update();
        prepare();
        floor = fl;
        Control c = new Control();
        addObject(c,0,0);
    }

    /**
     * moves the player in the desired direction by one tile
     * @param dir 1 = move right, 2 = move left, 3 = move down, 4 = move up
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

    /**
     * moves the target in the desired direction by one tile
     * @param dir 1 = move right, 2 = move left, 3 = move down, 4 = move up
     */
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

    /**
     * removes the target from the map;
     */
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

    /**
     * call to interact with objects that the target is currently on
     * @param attackOpen attack that tile if true open chest if otherwise
     */
    public void interact(boolean attackOpen){
        if(attackOpen){
            if(!spawnTarget){
                try{
                    for(int x = 0; x < MAPIMGWIDTH; x++){
                        for(int y = 0; y < MAPIMGHEIGHT; y++){
                            if((Player)field[x][y][1] != null){
                                addObject(new AttackEffect(), ((Enemy)field[targetX][targetY][2]).getX(), ((Enemy)field[targetX][targetY][2]).getY());
                                ((Player)field[x][y][1]).attack((Enemy)field[targetX][targetY][2]);
                            }
                        }
                    }
                } catch(Exception e){
                    System.out.println("NOT AN ENEMY");
                }
            }
        } else {
            try{
                    for(int x = 0; x < MAPIMGWIDTH; x++){
                        for(int y = 0; y < MAPIMGHEIGHT; y++){
                            if((Chest)field[x][y][5] != null){
                                ((Chest)field[targetX][targetY][5]).open();
                            }
                        }
                    }
                } catch(Exception e){
                    //System.out.println("NOT A CHEST");
                }
        }
    }

    public Actor returnActorInTarget(){
        return (Enemy)field[targetX][targetY][2];
    }

    /**
     * spawns in target checks to see if target is there already if true then don's spawn target
     */
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

    /**
     * return true if target has been spawned false otherwise
     */
    public boolean isSpawned(){
        return spawnTarget;
    }

    /**
     * removes object from 2D array
     * @param x the x coordinate of object
     * @param y the y coordinate of object
     * @param z the z coordinate of object (layer/depth)
     */
    public void removeMe(int x, int y, int z){
        field[x][y][z] = null;
    }

   /**
     * spawns player into world
     */
    public void spawnPlayer(){
        int xCo = playerX * TILESIZE + TILESIZE/2;
        int yCo = playerY * TILESIZE + TILESIZE/2;
        field[playerX][playerY][1] = new Player(xCo, yCo);
        update();
    }
    
    public void spawnPlayer(ArrayList<Integer> file){
        int xCo = playerX * TILESIZE + TILESIZE/2;
        int yCo = playerY * TILESIZE + TILESIZE/2;
        field[playerX][playerY][1] = new Player(file);
        update();
    }
    
    /**
     * spawns player at certain cooordinate on map
     * @param x the x coordinate of object
     * @param y the y coordinate of object
     * @param play the player to be spawned
     */
    public void spawnPlayer(Player play, int x, int y){
        field[x][y][1] = play;
        x = playerX;
        y = playerY;
        field[playerX][playerY][1] = null;
        update();
    }

   /**
     * spawns item onto map
     * @param xC the x coordinate of object
     * @param yC the y coordinate of object
     * @param id id number of item
     */
    public void inputItem(int xC, int yC, int id)
    {
        int xCo = xC * TILESIZE + TILESIZE/2;
        int yCo = yC * TILESIZE + TILESIZE/2;
        field[xC][yC][3] = new Items(id, xCo, yCo);
        update();
    }

/**
     * removes item  from 2D array
     * @param x the x coordinate of object
     * @param y the y coordinate of object
     */
    public void removeItem(int xC, int yC)
    {
        xC = (xC - 43) / 86;
        yC = (yC - 43) / 86;
        field[xC][yC][3] = null;
    }


    public void setFloorR(){
        if(floor < 3){
            floorR = 1;
        } else if(floor < 6 && floor >= 3){
            floorR = 2;
        } else if(floor < 10 && floor >= 6){
            floorR = 3;
        } else if(floor < 15 && floor >= 10){
            floorR = 4;
        } else if(floor < 18 && floor >= 15){
            floorR = 5;
        } else if(floor < 20 && floor >= 18){
            floorR = 6;
        } else if(floor >= 20){
            floorR = 7;
        }
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
                if(data[x][y][0] != null){
                    if(data[x][y][0].equals("door")){
                        field[x][y][0] = new Tile(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2, 1);
                        //System.out.print("d ");
                    }
                    else if(data[x][y][0].equals("floorTile")){
                        field[x][y][0] = new Tile(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2, 2);
                        //System.out.print("t ");
                    }
                    else if(data[x][y][0].equals("wall")){
                        field[x][y][0] = new Tile(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2, 3);
                        grid[x][y] = false;
                        //System.out.print("w ");
                    }
                    else if(data[x][y][0].equals("sPortal")){
                        field[x][y][0] = new Tile(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2, 4);
                        //System.out.print("s ");
                    }
                    else if(data[x][y][0].equals("ePortal")){
                        field[x][y][0] = new Tile(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2, 5);
                        //System.out.print("e ");
                    }
                }
                if(data[x][y][2] != null && data[x][y][2].equals("enemy")){
                    field[x][y][2] = new BaneSlime(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2);
                }
                else if(data[x][y][2] != null && data[x][y][2].equals("enemy1")){
                    field[x][y][2] = new Ambulancer(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2);
                }
                else if(data[x][y][2] != null && data[x][y][2].equals("enemy2")){
                    field[x][y][2] = new Drone(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2);
                }
                else if(data[x][y][2] != null && data[x][y][2].equals("enemy3")){
                    field[x][y][2] = new SanicRobot(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2);
                }
                else if(data[x][y][2] != null && data[x][y][2].equals("enemy4")){
                    field[x][y][2] = new HolyDriver(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2);
                }
                if(data[x][y][5] != null && data[x][y][5].equals("chest")){
                    field[x][y][5] = new Chest(x * TILESIZE + TILESIZE/2, y * TILESIZE + TILESIZE/2, floorR);
                    grid[x][y] = false;
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
     * centers player on map
     */
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
    
    public void act(){
        if(playerX == endX && playerY == endY){
            reset();
        }
    }
   
   /**
     * resets the entire map
     */
    private void reset(){
        //         if(playerX == endX && playerY == endY){
        ArrayList<Integer> playstat = new ArrayList<Integer>();
        int b = JOptionPane.showConfirmDialog(null, "Enter the Portal?", "Warning", JOptionPane.YES_NO_OPTION);
        if(b == JOptionPane.YES_OPTION){
            //Reset the thing
            increaseFloor();
            //             resetField();
            Player p = (Player)field[endX][endY][1];
            playstat.add((int)p.getLevel());
            playstat.add((int)p.getBaseHp());
            playstat.add((int)p.getHp());
            playstat.add((int)p.getAtk());
            playstat.add((int)p.getDef());
            playstat.add((int)p.getDex());
            playstat.add((int)p.getLuk());
            playstat.add((int)p.getArmorBuff());
            for(int i = 0; i < 9; i++){
                try{
                    playstat.add(p.getItems()[i].getItemID());
                }catch(NullPointerException e){
                    playstat.add(0);
                }
            }
            for(int i = 0; i < 2; i++){
                try{
                    playstat.add(p.getEquips()[i].getItemID());
                }catch(NullPointerException e){
                    playstat.add(0);
                }
            }
            playstat.add((int)p.getMapX());
            playstat.add((int)p.getMapY());
            playstat.add((int)p.xp);
            

            writeToFile(playstat);
            Greenfoot.setWorld(new ScrollingMap(p, getFloor()));
            //String message = "Thank you for playing the Lite Version of System Down. For more content, please buy our DLC pack Biogenisis or order the Full Game Online at www.systemdown.ca";
            //JOptionPane.showMessageDialog(null, message, "Congratulations", JOptionPane.PLAIN_MESSAGE);
        }
        else if (b== JOptionPane.NO_OPTION){
            //Stop an infinite loop
            movePlayer(4);

            //             }
        }
    }
    
    /**
     * creates save to use in next play
     * 
     * @param values values that will be written to text
     */
    public void writeToFile(ArrayList<Integer> values){
        for(int a : values){
            try{
                WriteFile data = new WriteFile ("playerMemory.txt", true);
                data.writeToFile(a);
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public ArrayList<Integer> getFileBack(){
        ArrayList<Integer> textFileContents = new ArrayList<Integer>();
        try {
            scan = new Scanner (new File ("playerMemory.txt"));
            // Make use of two interesting new methods found on the Scanner API
            while (scan.hasNext())
            {
                // Use the ArrayList's add() method and the Scanner's nextLine() method
                int a = Integer.parseInt(scan.nextLine());
                textFileContents.add(a); 
            }
        }
        //         catch (FileNotFoundException e)
        //         {
        //             System.out.println("File not found");
        //         }
        catch (IOException e)
        {
            //System.out.println("Error reading data");
        }

        // ADD A FINALLY BLOCK HERE TO CLOSE THE SCANNER
        finally{
            if(scan != null){
                scan.close();
            }
        }
        return textFileContents;
    }
    /**
     * empties map
     */
    public void resetField(){
        for(int x = 0; x < MAPIMGWIDTH; x++){
            for(int y = 0 ; y < MAPIMGHEIGHT;y++){
                for(int i = 0; i < MAPDEPTH; i++){
                    if(i != 1){
                        field[x][y][i] = null;
                    }
                }
            }
        }
    }

   /**
     * set position of player
     * 
     * @param x the x coordinate of object
     * @param y the y coordinate of object
     */
    public void placePlayer(int x, int y){
        field[x][y][1] = field[playerX][playerY][1];
        field[playerX][playerY][1] = null;
        x = playerX;
        y = playerY;
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
