/*
 * Objectives will be stored in each level, not the levelmaps
 */

package LevelEditor;

/**
 *
 * @author Jim
 */
public class Objective extends ITObject
{
    public String name;
    public Dialog dialog;
    public int mapIndex;
    private String title = "OBJECTIVE";
    public boolean remove = false;

    public Objective(int id, int x, int y, int map_Index, String objName)
    {
        super(id, x * 40, y * 40);
        name = objName;
        mapIndex = map_Index;
        dialog = new Dialog();

        //This prevents non-item objectives from being displayed as such
        if ((x < 0) || (y < 0))
        {
            title = "";
        }
    }

    public Objective(Objective o)
    {
        super(o.getID(), o.getX(), o.getY());
        name = o.name;
        mapIndex = o.mapIndex;
        dialog = new Dialog(o.dialog);
        title = o.title;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other.getClass().equals(this.getClass()))
        {
            Objective o = (Objective)other;
            return (this.hashCode() == o.hashCode());
        }
        else
        {
            return false;
        }
        
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 71 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 71 * hash + this.mapIndex;
        return hash;
    }

    @Override
    public String toString()
    {
        return title;
    }

    
}
