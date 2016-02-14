//This is a path for NPCs

package LevelEditor;

/**
 *
 * @author Jim (Admin)
 */

import java.util.ArrayList;

public class Path
{
    private ArrayList<Waypoint> waypoints;
    private int nextWaypoint;

    public Path()
    {
        waypoints = null;
    }

    public void addWaypoints(ArrayList<Waypoint> wpts)
    {
        waypoints = wpts;

        //There must be at least 2 waypoints
        if (waypoints.size() > 1)
        {
            nextWaypoint = 1;
        }
        else
        {
            nextWaypoint = 0;
        }
    }

    public void removeAllWaypoints()
    {
        waypoints.clear();
    }

    public Waypoint getStartingWaypoint()
    {
        return waypoints.get(0);
    }

    public Waypoint getNextWaypoint()
    {
        return waypoints.get(nextWaypoint);
    }

    public Waypoint getCurrentWaypoint()
    {
        if(nextWaypoint-1 >= 0)
        {
            return waypoints.get(nextWaypoint-1);
        }
        else
        {
            return waypoints.get(waypoints.size()-1);
        }
    }

    public void setNextWaypoint(int index)
    {
        if ((index >= 0) && (index < waypoints.size()))
        {
            nextWaypoint = index;
        }
    }

    public int getNextWaypointIndex()
    {
        return nextWaypoint;
    }

    public void reachedWaypoint()
    {
        nextWaypoint++;

        //System.out.println("Next Waypoint: " + nextWaypoint);
        
        //Loop back to the starting Waypoint
        if (nextWaypoint >= waypoints.size())
        {
           nextWaypoint = 0;
        }
    }

    public Path copy()
    {
        Path np = new Path();
        ArrayList<Waypoint> wpts = new ArrayList<Waypoint>();
        for(int i = 0; i < waypoints.size(); i++)
        {
            wpts.add(waypoints.get(i).copy());
        }
        np.addWaypoints(wpts);
        return np;
    }

    public ArrayList<Waypoint> getAllWaypoints()
    {
        return waypoints;
    }

    @Override
    public String toString()
    {
        String ps = "";

        for(int i = 0; i < waypoints.size(); i++)
        {
            ps += "" + waypoints.get(i).getXPos() + " " + waypoints.get(i).getYPos() + " " + waypoints.get(i).getDirection() + " " + waypoints.get(i).getBehavior() + "\n";
        }

        return ps;
    }

}
