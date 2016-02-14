/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelEditor;

/**
 *
 * @author Jim (Admin)
 */
public class HeavyGuard extends NPC
{
    public HeavyGuard(int x, int y, Direction d, NPCStatus s, Path p)
    {
        super(35, x, y, d, 30, s, false, 20, p, GuardType.HEAVY);
    }

    @Override
    public NPC copy()
    {
        return new HeavyGuard(this.getX(), this.getY(), this.getDirection(), this.getStatus(), this.getPath().copy());
    }
}
