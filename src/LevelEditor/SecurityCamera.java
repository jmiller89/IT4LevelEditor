/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelEditor;

/**
 *
 * @author Jim
 */
public class SecurityCamera extends ITObject
{
    private Direction direction;
    public Waypoint start;
    public Waypoint end;
    private int currWaypoint = 0;
    public boolean fixed;
    public boolean remove = false;

    //Used for checking for player
    public int xOffset = 0;
    public int yOffset = 0;

    private static final int NORMAL_WAIT = 1;
    private static final int WP_WAIT = 100;
    private int wait = 0;

    public SecurityCamera(int id, int x, int y, Direction dir, Waypoint way1, Waypoint way2, boolean isFixed)
    {
        super(id, x, y);
        direction = dir;
        start = way1;
        end = way2;
        fixed = isFixed;

        if (direction == Direction.UP)
        {
            yOffset = -40;
            xOffset = 20;
        }
        if (direction == Direction.DOWN)
        {
            yOffset = 40;
            xOffset = 20;
        }
        if (direction == Direction.LEFT)
        {
            xOffset = -40;
            yOffset = 20;
        }
        if (direction == Direction.RIGHT)
        {
            xOffset = 40;
            yOffset = 20;
        }
    }

    public SecurityCamera copy()
    {
        return new SecurityCamera(this.getID(), this.getX(), this.getY(), this.direction, this.start, this.end, this.fixed);
    }

    public Direction getDirection()
    {
        return direction;
    }

    public void move()
    {
        if (!fixed)
        {
            if (wait <= 0)
            {
                if (currWaypoint == 0)
                {
                    if ((direction == Direction.UP) || (direction == Direction.DOWN))
                    {
                        if (end.getXPos() * 40 > this.getX())
                        {
                            setX(this.getX() + 1);
                        }
                        else if (end.getXPos() * 40 < this.getX())
                        {
                            setX(this.getX() - 1);
                        }
                        else
                        {
                            currWaypoint = 1;
                            wait = WP_WAIT;
                        }
                    }
                    else
                    {
                        if (end.getYPos() * 40 > this.getY())
                        {
                            setY(this.getY() + 1);
                        }
                        else if (end.getYPos() * 40 < this.getY())
                        {
                            setY(this.getY() - 1);
                        }
                        else
                        {
                            currWaypoint = 1;
                            wait = WP_WAIT;
                        }
                    }
                }
                else
                {
                    if ((direction == Direction.UP) || (direction == Direction.DOWN))
                    {
                        if (start.getXPos() * 40 > this.getX())
                        {
                            setX(this.getX() + 1);
                        }
                        else if (start.getXPos() * 40 < this.getX())
                        {
                            setX(this.getX() - 1);
                        }
                        else
                        {
                            currWaypoint = 0;
                            wait = WP_WAIT;
                        }
                    }
                    else
                    {
                        if (start.getYPos() * 40 > this.getY())
                        {
                            setY(this.getY() + 1);
                        }
                        else if (start.getYPos() * 40 < this.getY())
                        {
                            setY(this.getY() - 1);
                        }
                        else
                        {
                            currWaypoint = 0;
                            wait = WP_WAIT;
                        }
                    }
                }

                if (wait <= 0)
                {
                    wait = NORMAL_WAIT;
                }
            }
            else
            {
                wait--;
            }
        }

    }
}
