//This is any "Object" that can be drawn on the map

package LevelEditor;

/**
 *
 * @author Jim (Admin)
 */
public class ITObject
{
    private int ID; //Used for Sprite Mapping
    private int locX;
    private int locY;
    private Sprite sprite = null;

    public ITObject(int identification, int x, int y)
    {
        if (identification >= 0)
        {
            ID = identification;
        }
        else
        {
            ID = 0;
        }
        
        locX = x;
        locY = y;
    }

    public int getID()
    {
        return ID;
    }

    public int getX()
    {
        return locX;
    }

    public int getY()
    {
        return locY;
    }

    public int getTileX()
    {
        return ((locX + 19) / 40);
    }

    public int getTileY()
    {
        return ((locY + 19) / 40);
    }

    public void setID(int identification)
    {
        if (identification >= 0)
        {
            ID = identification;
        }
    }

    public void setX(int x)
    {
        locX = x;
    }

    public void setY(int y)
    {
        locY = y;
    }

    public void setSprite(Sprite s)
    {
        if (s != null)
        {
            sprite = s;
        }
    }

    public Sprite getSprite()
    {
        return sprite;
    }

}
