//This is a parent class for the Player and All NPCs

package LevelEditor;

/**
 *
 * @author Jim (Admin)
 */

import java.util.Date;

public abstract class ITCharacter extends ITObject
{
    private Direction dir;
    private int currentHealth;
    private boolean NPC;
    public boolean isInWater = false;
    public boolean isRecentlyWounded = false;
    public boolean BOSS = false;
    private Date lastDamaged = new Date();
    public boolean bodyArmor = false;
    public boolean ally = false;

    public ITCharacter(int id, int x, int y, Direction d, int chlth, boolean nonPlayerCharacter)
    {
        super(id, x, y);
        dir = d;
        currentHealth = chlth;
        NPC = nonPlayerCharacter;
    }

    public Direction getDirection()
    {
        return dir;
    }

    public void changeDirection(Direction d)
    {
        if (d != null)
        {
            dir = d;
        }
    }

    public int getCurrentHealth()
    {
        return currentHealth;
    }

    protected void setHealth(int h)
    {
        currentHealth = h;
    }

    //Call this when a bullet collides w/ the character
    public int receiveDamage(int dmg)
    {
        if (bodyArmor)
        {
            //dmg = (dmg + 1) / 2;
            float damage = (dmg * 0.7f);
            dmg = (int)damage;
        }

        if (BOSS == false)
        {
            currentHealth = currentHealth - dmg;

            if (dmg > 1)
            {
                isRecentlyWounded = true;
            }
        }
        else
        {
            Date currentDate = new Date();

            //This gives the boss a 2 second invincibility period between shots
            if (currentDate.getTime() >= lastDamaged.getTime() + 2000)
            {
                currentHealth = currentHealth - dmg;
                lastDamaged = currentDate;

                if (dmg > 1)
                {
                    isRecentlyWounded = true;
                }
            }
        }

        if (currentHealth > 0)
        {
            if (NPC == true)
            {
                System.out.println("NPC recieved damage! Health = " + currentHealth);
            }
            else
            {
                System.out.println("Player recieved damage! Health = " + currentHealth);
            }
        }
        else
        {
            if (NPC == true)
            {
                System.out.println("NPC has died.");
            }
            else
            {
                System.out.println("Player has died. Game Over.");
            }
        }
        
        return currentHealth;
    }

    public void addHealth(int maxHealth)
    {
        currentHealth = currentHealth + 50;

        //Bind currentHealth to maxHealth
        if (currentHealth > maxHealth)
        {
            currentHealth = maxHealth;
        }
    }

    public void fullHeal(int maxHealth)
    {
        currentHealth = maxHealth;
    }
}
