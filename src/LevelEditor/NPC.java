//NPC = Non-Player Character

package LevelEditor;

/**
 *
 * @author Jim (Admin)
 */
public abstract class NPC extends ITCharacter
{
    private NPCStatus status;
    private boolean friendly;
    private int weaponDamage;
    private Path path;
    private GuardType type;

    public int wayX;
    public int wayY;
    public int timeToWait = 0;
    public int timeToWaitSuspect = 100;
    public int timeToWaitBloodSpatter = 15;
    public boolean guardStopped = false;
    public boolean pathFound = false;
    //public Vertex[][] vertices;
    public int currentShotInterval = 15;
    public int animationIterations = 6;
    //public int speedAdjuster = 20;
    public int[][] obstacleMatrix;
    public boolean trapped = false;
    public int viewDistance = 320;
    public boolean correcting = false;

    public boolean initialized;

    public boolean spawned = false;

    public NPC(int id, int x, int y, Direction d, int chlth, NPCStatus s, boolean friend, int weaponDmg, Path p, GuardType t)
    {
        super(id, x, y, d, chlth, true);
        status = s;
        friendly = friend;
        weaponDamage = weaponDmg;
        path = p;
        type = t;
        initialized = false;

        if ((type == GuardType.FEMALE_ALLY) || (type == GuardType.FEMALE_ALLY_PRISONER) || (type == GuardType.WOMAN1) || (type == GuardType.WOMAN2)
           || (type == GuardType.WOMAN3) || (type == GuardType.ISLAND_GUY) || (type == GuardType.CHIEF) || (type == GuardType.SPECIAL))
        {
            ally = true;
        }
    }

    public NPCStatus getStatus()
    {
        return status;
    }

    public boolean isFriendly()
    {
        return friendly;
    }

    public void setStatus(NPCStatus npcs)
    {
        if (npcs != null)
        {
            status = npcs;
        }
    }

    public int getWeaponDamage()
    {
        return weaponDamage;
    }

    public Path getPath()
    {
        return path;
    }

    public GuardType getType()
    {
        return type;
    }
    
    public abstract NPC copy();
    

    protected void setPath(Path p)
    {
        path = p;
    }

    public boolean isPathAttainable()
    {
        boolean attainable = false;

        if (((getX()+19)/40) == (path.getStartingWaypoint().getXPos()) && ((getY()+19)/40) == (path.getStartingWaypoint().getYPos()))
        {
            attainable = true;
        }

        return attainable;
    }
}
