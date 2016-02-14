/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelEditor;

/**
 *
 * @author Jim (Admin)
 */
public class LightGuard extends NPC
{
    public LightGuard(int x, int y, Direction d, NPCStatus s, Path p)
    {
        super(19, x, y, d, 10, s, false, 15, p, GuardType.LIGHT);
    }

    @Override
    public NPC copy()
    {
        return new LightGuard(this.getX(), this.getY(), this.getDirection(), this.getStatus(), this.getPath().copy());
    }
}
