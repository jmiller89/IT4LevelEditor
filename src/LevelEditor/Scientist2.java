/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelEditor;

/**
 *
 * @author Jim
 */
public class Scientist2 extends NPC
{
    public Scientist2(int x, int y, Direction d, NPCStatus s, Path p)
    {
        super(170, x, y, d, 7, s, false, 10, p, GuardType.SCIENTIST2);
    }

    @Override
    public NPC copy()
    {
        return new Scientist2(this.getX(), this.getY(), this.getDirection(), this.getStatus(), this.getPath().copy());
    }
}
