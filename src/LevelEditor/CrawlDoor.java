/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelEditor;

/**
 *
 * @author Jim
 */
public class CrawlDoor extends Door
{
    //Door(short id, int locX, int locY, int secLev, int objLev, boolean drk)
    public CrawlDoor(int id, int locX, int locY)
    {
        super(id, locX, locY, 0, 0, false);
        crawl = true;
    }

    @Override
    public void open()
    {
        openPos = 40;
        opening = false;
        closing = false;
    }

    @Override
    public void close()
    {
        openPos = 0;
        opening = false;
        closing = false;
    }

    @Override
    public int getOpenPos()
    {
        return 0;
    }

    @Override
    public Door copy()
    {
        return new CrawlDoor(this.getID(), this.getX(), this.getY());
    }

    @Override
    public String toString()
    {
        return "" + getTileX() + " " + getTileY() + " -1 0 false";
    }
}
