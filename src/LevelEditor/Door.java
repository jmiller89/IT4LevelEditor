/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelEditor;

/**
 *
 * @author Jim
 */
public class Door extends ITObject
{
    protected int openPos = 0;
    private int securityLevel = 0;
    protected boolean opening = false;
    protected boolean closing = false;
    private boolean dark = false;
    private int objectiveLevel = 0;
    public boolean crawl = false;

    public Door(int id, int locX, int locY, int secLev, int objLev, boolean drk)
    {
        super(id, locX, locY);
        securityLevel = secLev;
        dark = drk;
        objectiveLevel = objLev;
    }

    public Door copy()
    {
        return new Door(this.getID(), this.getX(), this.getY(), this.securityLevel, this.objectiveLevel, this.dark);
    }

    public boolean isDark()
    {
        return dark;
    }

    public void open()
    {
        if (openPos < 40)
        {
            openPos+=2;
            //System.out.println("Opening " + openPos);
        }
        else
        {
            opening = false;
        }
    }

    public void close()
    {
        if (openPos > 0)
        {
            openPos-=2;
        }
        else
        {
            closing = false;
        }
    }

    public boolean isOpen()
    {
        return (openPos > 39);
    }

    public boolean isClosed()
    {
        return (openPos < 1);
    }

    public int getOpenPos()
    {
        return openPos;
    }

    public int getSecuritylevel()
    {
        return securityLevel;
    }

    public int getObjectiveLevel()
    {
        return objectiveLevel;
    }

    @Override
    public String toString()
    {
        return "" + getTileX() + " " + getTileY() + " " + getSecuritylevel() + " " + getObjectiveLevel() + " " + isDark();
    }
}
