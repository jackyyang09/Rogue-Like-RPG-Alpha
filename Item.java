import greenfoot.*;

/**
 * Universal Item class
 * 
 * @author Jacky Yang
 * @version (a version number or a date)
 */
public class Items extends Actor
{
    private int id;
    private String name;
    private String imageName;
    private boolean equippable;
    private int atkBuff;
    private int defBuff;
    public Items(int num)
    {
        if (num == 1)
        {
            id = num;
            name = "Beam Blade";
            equippable = true;
            setImage("beamblade1.png");
            imageName = "beamblade1.png";
            atkBuff = 2;
            defBuff = 0;
        }
    }
    
    public boolean getEquippable()
    {
        return equippable;
    }
}
