/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelEditor;

/**
 *
 * @author Jim
 */
public class Scientist1 extends NPC
{

    public Scientist1(int x, int y, Direction d, NPCStatus s, Path p)
    {
        super(162, x, y, d, 7, s, false, 10, p, GuardType.SCIENTIST1);
    }

    @Override
    public NPC copy()
    {
        return new Scientist1(this.getX(), this.getY(), this.getDirection(), this.getStatus(), this.getPath().copy());
    }

}
