/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelEditor;

/**
 *
 * @author Jim
 */
public class SpecialGuardEnemy extends NPC
{
    public SpecialGuardEnemy(int x, int y, Direction d, NPCStatus s, Path p)
    {
        super(350, x, y, d, 45, s, false, 22, p, GuardType.SPECIAL_ENEMY);
    }

    @Override
    public NPC copy()
    {
        SpecialGuardEnemy g = new SpecialGuardEnemy(this.getX(), this.getY(), this.getDirection(), this.getStatus(), this.getPath().copy());

        g.bodyArmor = this.bodyArmor;

        return g;
    }
}
