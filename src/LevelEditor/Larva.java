/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelEditor;

/**
 *
 * @author Jim
 */
public class Larva extends NPC
{
    public Larva(int x, int y, Direction d, NPCStatus s, Path p)
    {
        super(285, x, y, d, 30, s, false, 19, p, GuardType.LARVA);
    }
    @Override

    public NPC copy()
    {
        Larva g = new Larva(this.getX(), this.getY(), this.getDirection(), this.getStatus(), this.getPath().copy());

        g.bodyArmor = this.bodyArmor;

        return g;
    }
}
