//This is the basic class for a sprite

package LevelEditor;

/**
 *
 * @author Jim (Admin)
 */

import java.awt.Graphics;
import java.awt.Image;

public class Sprite
{
    private Image image;
    private int ID;

    public Sprite(Image img, int i)
    {
        image = img;
        ID = i;
    }

    public int getWidth()
    {
		return image.getWidth(null);
	}

	public int getHeight()
    {
		return image.getHeight(null);
	}

    public int getID()
    {
        return ID;
    }

    public void draw(Graphics g, int x, int y)
    {
		g.drawImage(image, x, y, null);
	}


}
