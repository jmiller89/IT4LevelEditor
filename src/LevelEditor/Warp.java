//This is used to warp from one LevelMap to another within a Level

package LevelEditor;

/**
 *
 * @author Jim (Admin)
 */
public class Warp extends ITObject
{
    private int levX;
    //private int levY;
    private int playerX;
    private int playerY;
    private boolean nextLevelWarp;
    private int securityLevelRequired;
    public boolean isPlayerInWarp = false;
    
    public Warp(int id, int locX, int locY, int lX, int pX, int pY, boolean nextLvWarp, int securityLevel)
    {
        super(id, locX, locY);
        levX = lX;
        playerX = pX;
        playerY = pY;
        nextLevelWarp = nextLvWarp;

        if ((securityLevel < 0) || (securityLevel > 5))
        {
            securityLevelRequired = 0;
        }
        else
        {
            securityLevelRequired = securityLevel;
        }
    }

    public Warp copy()
    {
        return new Warp(this.getID(), this.getX(), this.getY(), this.levX, this.playerX, this.playerY, this.nextLevelWarp, this.securityLevelRequired);
    }

    public int getLevelX()
    {
        return levX;
    }

//    public int getLevelY()
//    {
//        return levY;
//    }

    public int getPlayerWarpX()
    {
        return playerX * 40;
    }

    public int getPlayerWarpY()
    {
        return playerY * 40;
    }

    public boolean isNextLevelWarp()
    {
        return nextLevelWarp;
    }

    public int getSecurityLevelRequired()
    {
        return securityLevelRequired;
    }

}
