/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelEditor;

/**
 *
 * @author Jim
 */
public class Alien extends NPC
{
    public Alien(int x, int y, Direction d, NPCStatus s, Path p)
    {
        super(242, x, y, d, 40, s, false, 22, p, GuardType.ALIEN);
    }
    
    @Override
    public NPC copy()
    {
        Alien g = new Alien(this.getX(), this.getY(), this.getDirection(), this.getStatus(), this.getPath().copy());

        g.bodyArmor = this.bodyArmor;

        return g;
    }

}
