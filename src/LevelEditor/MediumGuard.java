/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelEditor;

/**
 *
 * @author Jim (Admin)
 */
public class MediumGuard extends NPC
{
    public MediumGuard(int x, int y, Direction d, NPCStatus s, Path p)
    {
        super(27, x, y, d, 20, s, false, 20, p, GuardType.MEDIUM);
    }

    @Override
    public NPC copy()
    {
        return new MediumGuard(this.getX(), this.getY(), this.getDirection(), this.getStatus(), this.getPath().copy());
    }
}
