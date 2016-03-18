/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelEditor;

/**
 *
 * @author Jim
 */
public class SelectedPayload
{
    public Mode mode = Mode.TILE;
    public SelectionMode selectionMode = SelectionMode.CREATE;
    public int id = 0;
    public int x = 0;
    public int y = 0;
    public GuardType gt = GuardType.LIGHT;
    public String path = "";
    public boolean bodyArmor = false;
    public int itemID = 0;
    public int secLev = 0;
    public int objLev = 0;
    public boolean isDark = false;
    public boolean alert = false;
    public String weather = "none";
    public int gotoRoom = 0;
    public boolean nextLev = false;
    public int toX = 1;
    public int toY = 1;
    public String name = "";
    public String dialog = "";
    public String dialog_pre = "";
    public String dialog_post = "";
    public int capacity = 0;
    public Direction dir = Direction.UP;
    public boolean isFixed = false;
    public int delta = 0;
    public int songIndex = 0;
    public int health = 100;
    public int damage = 20;
    public int speed = 15;
    public int viewdistance = 800;
    public String precip = "none";
    public boolean forceprone = false;
    public int rank = 1;
    public boolean friendly = false;
}
