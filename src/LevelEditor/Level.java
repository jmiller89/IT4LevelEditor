//This is a container class for all LevelMaps

package LevelEditor;

/**
 *
 * @author Jim (Admin)
 */

import java.util.ArrayList;

public class Level
{
    private ArrayList<LevelMap> levelMaps;
    private int levelID;
    
    private int startX = 1;
    private int startY = 1;

    private ArrayList<ArrayList<Objective>> objectives;
    private ArrayList<Objective> allObjectives;

    public boolean stripItems = false;
    public boolean transition = false;

    private Editor game;

    public Level(int size, int id, Editor gm)
    {
        levelMaps = new ArrayList<LevelMap>(size);
        levelID = id;

        //For the levelmap
        objectives = new ArrayList<ArrayList<Objective>>();
        //For the player to view
        allObjectives = new ArrayList<Objective>();
        game = gm;
    }

    public void initializeObjectives()
    {
        objectives.clear();
        
        for(int i = 0; i < levelMaps.size(); i++)
        {
            objectives.add(new ArrayList<Objective>());
        }

        //Add the objective to its appropriate sector
        for(int i = 0; i < allObjectives.size(); i++)
        {
            Objective obj = allObjectives.get(i);
            System.out.println(game.getSpriteLoader().MAX_TILE_INDEX);
            obj.setSprite(game.getSpriteLoader().getSprite(obj.getID()));

            Objective copy = new Objective(obj);
            copy.setSprite(obj.getSprite());

            objectives.get(obj.mapIndex).add(copy);
        }
    }

    public void addObjective(Objective o)
    {
        allObjectives.add(new Objective(o));
    }

    public Objective getObjective(int x, int y, int mapIndex)
    {
//        System.out.println("===Objectives===");
//        for(int i = 0; i < objectives.size(); i++)
//        {
//            System.out.println("** objectives[" + i + "] ***");
//            for(int j = 0; j < objectives.get(i).size(); j++)
//            {
//                System.out.println("" + objectives.get(i).get(j).name + " x=" + objectives.get(i).get(j).getTileX() + " y=" + objectives.get(i).get(j).getTileY() + " mapIndex=" + objectives.get(i).get(j).mapIndex);
//            }
//        }

        Objective obj = null;

        try
        {
            for(int i = 0; i < objectives.get(mapIndex).size(); i++)
            {
                //System.out.println(objectives.get(mapIndex).get(i).name + " X = " + objectives.get(mapIndex).get(i).getTileX() + "Y = " + objectives.get(mapIndex).get(i).getTileY());

                if ((objectives.get(mapIndex).get(i).getTileX() == x) && (objectives.get(mapIndex).get(i).getTileY() == y))
                {
                    obj = objectives.get(mapIndex).get(i);
                    break;
                }
            }
        }
        catch(Exception e)
        {

        }

        return obj;
    }

    public void removeObjective(int x, int y, int mapIndex)
    {
        for(int i = 0; i < allObjectives.size(); i++)
        {
            System.out.println("Objective " + i + " map Index = " + allObjectives.get(i).mapIndex + " x = " + allObjectives.get(i).getTileX() + " y = " + allObjectives.get(i).getTileY());
            if (mapIndex == allObjectives.get(i).mapIndex)
            {
                
                if ((allObjectives.get(i).getTileX() == x) && (allObjectives.get(i).getTileY() == y))
                {
                    System.out.println("Local objectives size = " + objectives.get(mapIndex).size());
                    for(int j = 0; j < objectives.get(mapIndex).size(); j++)
                    {
                        if ((objectives.get(mapIndex).get(j).getTileX() == x) && (objectives.get(mapIndex).get(j).getTileY() == y))
                        {
                            objectives.get(mapIndex).remove(j);
                        }
                    }

                    System.out.println("Removing objective");
                    allObjectives.remove(i);

                }
            }
        }
    }

    public void removeAndReorderObjectives(int index)
    {
        //Flag for removal
        for(int i = 0; i < allObjectives.size(); i++)
        {
            if (index == allObjectives.get(i).mapIndex)
            {
                allObjectives.get(i).remove = true;
            }
            else if (index < allObjectives.get(i).mapIndex)
            {
                allObjectives.get(i).mapIndex--;
            }
        }

        //Remove
        for(int i = 0; i < allObjectives.size(); i++)
        {
            if (allObjectives.get(i).remove)
            {
                allObjectives.remove(i);
            }
        }

        initializeObjectives();
        
    }

    public ArrayList<Objective> getObjectives(int index)
    {
        if (index < objectives.size())
        {
            return objectives.get(index);
        }
        else
        {
            return null;
        }
    }

    public ArrayList<Objective> getAllObjectives()
    {
        return allObjectives;
    }

    public void removeObjective(Objective obj, int index)
    {
        //System.out.println("Removing Objective");
        objectives.get(index).remove(obj);
        allObjectives.remove(obj);
    }

    public void setStartX(int x)
    {
        startX = x;
    }

    public void setStartY(int y)
    {
        startY = y;
    }

    public int getStartX()
    {
        return startX;
    }

    public int getStartY()
    {
        return startY;
    }

    public int getID()
    {
        return levelID;
    }

//    public void setLevelMap(LevelMap l, int loc)
//    {
//        levelMaps[loc] = l;
//        System.out.println("Lv map set");
//    }

    public void addLevelMap(LevelMap lm)
    {
        levelMaps.add(lm);
        updateIDs();
        System.out.println("Lv map set (added)");
    }

    public void setLevelMap(LevelMap lm, int index)
    {
        levelMaps.set(index, lm);
        updateIDs();
        System.out.println("Lv map set");
    }

    public LevelMap getLevelMap(int loc)
    {
        return levelMaps.get(loc);
    }

    public void removeLevelMap(int index)
    {
        levelMaps.remove(index);
        updateIDs();
    }

    private void updateIDs()
    {
        for(int i = 0; i < levelMaps.size(); i++)
        {
            levelMaps.get(i).id = i;
        }
    }

    public int getNumRooms()
    {
        return levelMaps.size();
    }

    public void destroy()
    {
        
    }
}
