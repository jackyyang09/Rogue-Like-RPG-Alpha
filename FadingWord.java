import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Font;
import java.awt.Color;
/**
 * Write a description of class fadingWord here.
 * 
 * @author (Kajamugesh) 
 * @version (a version number or a date)
 */
public class FadingWord extends Actor
{
    private int transparency;
    private GreenfootImage image;
    private int timer = 0;
    public FadingWord (String text)
    {
        Color transparent = new Color(0,0,0,0);
        Font font = new Font("OCR A Extended", 3, 20);
        transparency = 255;
        GreenfootImage image = new GreenfootImage (text, 12, Color.RED, transparent);
        setImage(image);
    }

    /**
     * Act - do whatever the fadingWord wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (timer%4 == 0){
            setLocation(getX(), getY() -2);
        }
        timer+=2;
        if (timer >= 100){
            getImage().setTransparency(getImage().getTransparency() - 2);
        }
        if (timer >= 195){
            getWorld().removeObject(this);
        }
    }    
}
