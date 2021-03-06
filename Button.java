
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Buttons for title screen and other
 * 
 * @author (Jacky Yang & Aingharan Uthayakumar) 
 * @version (June 12 2015)
 */
public class Button extends Actor
{
  private String buttonText;
  private GreenfootImage myImage;
   /**
     * Construct a Button with a given String at the default size
     * 
     */
    public Button ()
    {
        
    }
    
    /**
     * Construct a Button with a given String at the default size
     * 
     * @param text  String value to display
     * @param textSize  size of String to display
     * 
     */
  public Button (String text, int textSize)
    {
        buttonText = text;
        GreenfootImage tempTextImage = new GreenfootImage (text, textSize, Color.RED, Color.WHITE);
        myImage = new GreenfootImage (tempTextImage.getWidth() + 8, tempTextImage.getHeight() + 8);
        myImage.setColor (Color.WHITE);
        myImage.fill();
        myImage.drawImage (tempTextImage, 4, 4);

        myImage.setColor (Color.BLACK);
        myImage.drawRect (0,0,tempTextImage.getWidth() + 7, tempTextImage.getHeight() + 7);
        setImage(myImage);
    }
}
