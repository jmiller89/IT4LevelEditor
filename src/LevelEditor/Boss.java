/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelEditor;

import java.util.ArrayList;

/**
 *
 * @author Jim (Admin)
 */
public class Boss extends NPC
{
    private LevelEditor.Dialogue pre;
    private LevelEditor.Dialogue post;
    public int speed = 15;
    public int shotInterval = 40;
    public String name = "Boss";
    private int maxHealth;

    public Boss(int bossID, int x, int y, GuardType bossType,
                int bossHealth, int bossDamage, int speed, int viewDistance, boolean bdyArmor)
    {
        super(bossID, x, y, Direction.DOWN, bossHealth, NPCStatus.ALERT, false, bossDamage, null, bossType);
        this.BOSS = true;
        this.bodyArmor = bdyArmor;
        this.viewDistance = viewDistance;
        this.speed = speed;

        //Set the speed of the gun based on the damage it does.
        //I know this is kind of strange, but I really didn't feel like adding
        //Another field to the boss element in the data file...
        if ((this.getWeaponDamage() >= 20) && (this.getWeaponDamage() < 25))
        {
            shotInterval = 5;
        }
        else if (this.getWeaponDamage() >= 25)
        {
            shotInterval = 50;
        }
        else //(Less than 20 damage per hit)
        {
            shotInterval = 20;
        }

        maxHealth = this.getCurrentHealth();

        int id = 119;

        //TODO: Support more boss types

        if (bossType == GuardType.BOSS1)
        {
            id = 119;
        }
        else if (bossType == GuardType.BOSS2)
        {
            id = 127;
        }
        else if (bossType == GuardType.BOSS3)
        {
            id = 135;
        }
        else if (bossType == GuardType.BOSS4)
        {
            id = 226;
        }
        else if (bossType == GuardType.BOSS5)
        {
            id = 234;
        }
        else if (bossType == GuardType.BOSS6)
        {
            id = 242;
        }
        else if (bossType == GuardType.CHIEF)
        {
            id = 334;
        }
        else if ((bossType == GuardType.SPECIAL) || (bossType == GuardType.SPECIAL_ENEMY))
        {
            id = 350;
        }
        else if (bossType == GuardType.ISLAND_GUY)
        {
            id = 342;
        }
        else if (bossType == GuardType.FEMALE_ALLY)
        {
            id = 261;
        }

        this.setID(id);
    }

    @Override
    public NPC copy()
    {
        Boss b = new Boss(this.getID(), this.getX(), this.getY(),
                this.getType(), this.getCurrentHealth(), this.getWeaponDamage(), this.speed, this.viewDistance, this.bodyArmor);
        b.name = this.name;

        if (this.getPreDialog() != null)
        {
            b.setPreDialog(new Dialogue());
            ArrayList<String> contents = this.getPreDialog().getContents();
            for(String s : contents)
            {
                b.getPreDialog().add(s);
            }
        }

        if (this.getPostDialog() != null)
        {
            b.setPostDialog(new Dialogue());
            ArrayList<String> contents = this.getPostDialog().getContents();
            for(String s : contents)
            {
                b.getPostDialog().add(s);
            }
        }
        
        return b;
    }

    public void setPreDialog(Dialogue d)
    {
        pre = d;
    }

    public void setPostDialog(Dialogue d)
    {
        post = d;
    }

    public Dialogue getPreDialog()
    {
        return pre;
    }

    public Dialogue getPostDialog()
    {
        return post;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }
}
