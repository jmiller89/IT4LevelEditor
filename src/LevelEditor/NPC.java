//NPC = Non-Player Character

package LevelEditor;

/**
 *
 * @author Jim (Admin)
 */
public class NPC extends ITCharacter
{
    private NPCStatus status;
    private boolean friendly;
    private int weaponDamage;
    private Path path;
    private GuardType type;

    public short timeToWaitSuspect = 150;
    public short timeToWaitBloodSpatter = 15;

    public boolean pathFound = false;

    //public Vertex[][] vertices;
    public byte currentShotInterval = 15;
    public byte animationIterations = 6;

    //public byte[][] obstacleMatrix;
    public boolean trapped = false;
    public int viewDistance = 320;
    public boolean correcting = false;

    public boolean initialized;

    public boolean spawned = false;

    public boolean following = false;

    public boolean firstAI = true;

    public long tranqedDate = 0;
    public int tranqTimeMillis = 0;

    public float movementDelta = 1.0f;

    public boolean markedForDeath = false;
    public byte markedForDeathIters = 8;

    public final short initialID;

    public Dialog dialog;

    public static NPC create(GuardType t, int x, int y, Direction d, NPCStatus s, boolean friend, Path p)
    {
        short id = 0;
        int currHealth = 0;
        int weaponDmg = 0;

        //LIGHT, MEDIUM, HEAVY, SPECIAL, BOSS0, BOSS1, BOSS2, BOSS3, BOSS4, NAZI_HUNTER, SCIENTIST1, SCIENTIST2, PROMINENT_SCIENTIST,
        //FEMALE_ALLY, FEMALE_ALLY_WORKOUT, OLD_MAN, UN_GUY, CHIEF, MUTANT1, MUTANT2, CRIPPLE, EVA, MAN, WOMAN1, WOMAN2, WOMAN3

        switch(t)
        {
            case LIGHT:
                id = 19;
                currHealth = 22;
                weaponDmg = 25;
                break;
            case MEDIUM:
                id = 27;
                currHealth = 51;
                weaponDmg = 40;
                break;
            case HEAVY:
                id = 35;
                currHealth = 71;
                weaponDmg = 40;
                break;
            case SPECIAL:
                id = 350;
                currHealth = 100;
                weaponDmg = 50;
                break;
            case BOSS0:
                id = 242;
                currHealth = 100;
                weaponDmg = 50;
                break;
            case BOSS1:
                id = 119;
                currHealth = 100;
                weaponDmg = 50;
                break;
            case BOSS2:
                id = 127;
                currHealth = 100;
                weaponDmg = 50;
                break;
            case BOSS3:
                id = 135;
                currHealth = 100;
                weaponDmg = 50;
                break;
            case BOSS4:
                id = 226;
                currHealth = 100;
                weaponDmg = 50;
                break;
            case NAZI_HUNTER:
                id = 234;
                currHealth = 100;
                weaponDmg = 50;
                break;
            case SCIENTIST1:
                id = 162;
                currHealth = 15;
                weaponDmg = 20;
                break;
            case SCIENTIST2:
                id = 170;
                currHealth = 15;
                weaponDmg = 20;
                break;
            case PROMINENT_SCIENTIST:
                id = 421;
                currHealth = 56;
                weaponDmg = 20;
                break;
            case FEMALE_ALLY:
                id = 261;
                currHealth = 120;
                weaponDmg = 40;
                break;
            case FEMALE_ALLY_WORKOUT:
                id = 269;
                currHealth = 120;
                weaponDmg = 40;
                break;
            case OLD_MAN:
                id = 405;
                currHealth = 30;
                weaponDmg = 40;
                break;
            case UN_GUY:
                id = 342;
                currHealth = 40;
                weaponDmg = 20;
                break;
            case CHIEF:
                id = 334;
                currHealth = 40;
                weaponDmg = 20;
                break;
            case MUTANT1:
                id = 277;
                currHealth = 96;
                weaponDmg = 48;
                break;
            case MUTANT2:
                id = 285;
                currHealth = 256;
                weaponDmg = 60;
                break;
            case CRIPPLE:
                id = 413;
                currHealth = 100;
                weaponDmg = 30;
                break;
            case EVA:
                id = 429;
                currHealth = 15;
                weaponDmg = 20;
                break;
            case MAN:
                id = 437;
                currHealth = 15;
                weaponDmg = 20;
                break;
            case WOMAN1:
                id = 310;
                currHealth = 15;
                weaponDmg = 20;
                break;
            case WOMAN2:
                id = 318;
                currHealth = 15;
                weaponDmg = 20;
                break;
            case WOMAN3:
                id = 326;
                currHealth = 15;
                weaponDmg = 20;
                break;
            default:
                id = 19;
                currHealth = 22;
                weaponDmg = 25;
                break;
        }

        NPC toSpawn = new NPC(id, x, y, d, currHealth, s, friend, weaponDmg, p, t);
        return toSpawn;
    }

    public NPC(short id, int x, int y, Direction d, int chlth, NPCStatus s, boolean friend, int weaponDmg, Path p, GuardType t)
    {
        super(id, x, y, d, chlth, true);
        initialID = id;
        status = s;
        friendly = friend;
        weaponDamage = weaponDmg;
        path = p;
        type = t;
        initialized = false;

        if (friendly)
        {
            ally = true;

            if (path.getStartingWaypoint().getBehavior() == WaypointBehavior.FOLLOW_PLAYER)
            {
                following = true;
                //followingPath = new Path();
            }
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
            if (status == NPCStatus.TRANQUILIZED_SLEEP)
            {
                tranqedDate = System.currentTimeMillis();
            }
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

    public NPC copy()
    {
        //(short id, int x, int y, Direction d, int chlth, NPCStatus s, boolean friend, int weaponDmg, Path p, GuardType t)
        NPC n = new NPC(this.initialID, this.getX(), this.getY(), this.getDirection(), this.getCurrentHealth(), this.getStatus(), this.friendly, this.getWeaponDamage(), this.getPath(), this.getType());
        n.bodyArmor = this.bodyArmor;
        n.dialog = this.dialog;

        return n;
    }

    public void suspect(Direction attackFrom)
    {
        Direction oldDirection = this.getDirection();
        if (attackFrom == Direction.UP)
        {
            changeDirection(Direction.DOWN);
        }
        else if (attackFrom == Direction.DOWN)
        {
            changeDirection(Direction.UP);
        }
        else if (attackFrom == Direction.LEFT)
        {
            changeDirection(Direction.RIGHT);
        }
        else if (attackFrom == Direction.RIGHT)
        {
            changeDirection(Direction.LEFT);
        }

        suspectUpdateID();

        if ((oldDirection != getDirection()) || (getStatus() == NPCStatus.SLEEP))
        {
            status = NPCStatus.SUSPICIOUS;
        }
    }

    private void suspectUpdateID()
    {
        if (!friendly)
        {
            if (this.getDirection() == Direction.UP)
            {
                this.setID(initialID);
            }
            else if (this.getDirection() == Direction.DOWN)
            {
                this.setID(initialID+1);
            }
            else if (this.getDirection() == Direction.LEFT)
            {
                this.setID(initialID+2);
            }
            else if (this.getDirection() == Direction.RIGHT)
            {
                this.setID(initialID+3);
            }
        }
    }

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