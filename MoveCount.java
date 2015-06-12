import greenfoot.*;
import java.awt.Font;
import java.util.List;
import java.awt.Color;
/**
 * A counter that displays the remaining amount of moves the Player has before their turn ends
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MoveCount extends Actor
{
    /**
     * Act - do whatever the MoveCount wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        GreenfootImage image = new GreenfootImage("moveCount.png");
        List<Player> player = getWorld().getObjects(Player.class);
        image.setFont( new Font("OCR A Extended", 2, 16));
        image.setColor(new Color(0, 165, 20));
        for (Player p : player){image.drawString("Moves: " + p.getMove(), 15, 25);}
        setImage(image);
    }    
}
