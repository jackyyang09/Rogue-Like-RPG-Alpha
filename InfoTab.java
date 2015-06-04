import greenfoot.*;
import java.awt.Font;
import java.lang.reflect.Array;
/**
 * Displays a window showing detailed information when hovering over an item
 * 
 * @author Jacky Yang
 * @version (a version number or a date)
 */
public class InfoTab extends Actor
{
    ItemInventory items;
    private boolean begin = false;
    private Font fontName, fontDesc, fontNums;
    private String[] description;
    public InfoTab(ItemInventory iteminventory)
    {
        items = iteminventory;
        fontName = new Font("OCR A Extended", 2, 20);
        fontDesc = new Font("OCR A Extended", 1, 13);
        fontNums = new Font("OCR A Extended", 3, 20);
        setImage("Info.png")
    }

    /**
     * Act - do whatever the InfoTab wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (begin == false)
        {
            begin();
        }
    }    

    public void begin()
    {
        GreenfootImage image = new GreenfootImage(getImage());
        image.setFont(fontName);
        image.drawImage(items.getImage(), 23, 28);
        Items item = new Items(items.getItemID());
        image.drawString(item.getName(), 213, 56);
        image.setFont(fontDesc);
        image.drawString(item.getDescription(), 155, 85);
        image.setFont(fontNums);
        image.drawString(Integer.toString(item.getAtkBuff()), 65, 167);
        image.drawString(Integer.toString(item.getDefBuff()), 120, 167);
        image.drawString(Integer.toString(item.getLukBuff()), 65, 192);
        image.drawString(Integer.toString(item.getDexBuff()), 120, 192);
        setImage(image);
        begin = true;
    }
}
