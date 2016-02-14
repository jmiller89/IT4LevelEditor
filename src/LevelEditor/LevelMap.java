//This is one room in a Level.

package LevelEditor;

/**
 *
 * @author Jim (Admin)
 */

import java.util.ArrayList;

public class LevelMap
{
    private Editor game;
    private int[][] levMap;
    private int[][] obstacleMatrix;
    private Sprite[][] spriteMap;
    private ArrayList<NPC> guards;
    private ArrayList<ITObject> collidableObjects;
    private int defaultGroundCover;
    private ArrayList<Warp> warps;
    private ArrayList<Door> doors;
    private ArrayList<SecurityCamera> cameras;
    private ArrayList<Spawn> spawns;
    private ArrayList<Item> items;
    private ArrayList<Objective> objectives;
    private ArrayList<Water> water;
    private ArrayList<TallGrass> tallGrass;
    private boolean alertMode = false;
    private Boss boss = null;

    private Dialogue dlg;

    public boolean dark = false;
    public boolean semidark = false;
    public boolean gas = false;
    public boolean haze = false;
    public boolean jam = false;
    public boolean rain = false;
    public boolean snow = false;
    public boolean forceprone = false;

    public int songIndex = 0;

    public int id = 0;

    public Path playerPath = null;

    public LevelMap(Editor g, int[][] tilemap, int defaultGC)
    {
        game = g;

        levMap = new int[tilemap.length][tilemap[0].length];
        obstacleMatrix = new int[tilemap.length][tilemap[0].length];
        defaultGroundCover = defaultGC;

        //Shave off anything out of bounds
        
        for(int i = 0; i < levMap[0].length; i++)
        {
            for(int j = 0; j < levMap.length; j++)
            {
                if ((levMap[j][i] < 0) || (levMap[j][i] > SpriteLoader.MAX_TILE_INDEX))
                {
                    levMap[j][i] = defaultGroundCover;
                }
                else
                {
                    levMap[j][i] = tilemap[j][i];
                }
            }
        }

        spriteMap = new Sprite[levMap.length][levMap[0].length];
        guards = new ArrayList<NPC>();
        doors = new ArrayList<Door>();
        warps = new ArrayList<Warp>();
        items = new ArrayList<Item>();
        cameras = new ArrayList<SecurityCamera>();
        spawns = new ArrayList<Spawn>();
        objectives = new ArrayList<Objective>();

        initializeCollidableObjects();
        initializeWaterAndTallGrass();

        setSpriteTiles();
        dlg = new Dialogue();
    }

    public LevelMap(LevelMap l)
    {
        game = l.game;

        levMap = new int[l.levMap.length][l.levMap[0].length];
        obstacleMatrix = new int[l.spriteMap.length][l.spriteMap[0].length];
        defaultGroundCover = l.defaultGroundCover;

        //Shave off anything out of bounds

        for(int i = 0; i < levMap[0].length; i++)
        {
            for(int j = 0; j < levMap.length; j++)
            {
                if ((levMap[j][i] < 0) || (levMap[j][i] > SpriteLoader.MAX_TILE_INDEX))
                {
                    levMap[j][i] = defaultGroundCover;
                }
                else
                {
                    levMap[j][i] = l.levMap[j][i];
                }
            }
        }

        spriteMap = new Sprite[levMap.length][levMap[0].length];
        guards = new ArrayList<NPC>();

        for(int i = 0; i < l.guards.size(); i++)
        {
            guards.add(l.guards.get(i).copy());
        }

        doors = new ArrayList<Door>();

        for(int i = 0; i < l.doors.size(); i++)
        {
            doors.add(l.doors.get(i).copy());
        }

        warps = new ArrayList<Warp>();

        for(int i = 0; i < l.warps.size(); i++)
        {
            warps.add(l.warps.get(i).copy());
        }

        items = new ArrayList<Item>();

        for(int i = 0; i < l.items.size(); i++)
        {
            items.add(l.items.get(i).copy());
        }

        cameras = new ArrayList<SecurityCamera>();

        for(int i = 0; i < l.cameras.size(); i++)
        {
            cameras.add(l.cameras.get(i).copy());
        }

        spawns = new ArrayList<Spawn>();

        for(int i = 0; i < l.spawns.size(); i++)
        {
            spawns.add(l.spawns.get(i).copy());
        }

        objectives = new ArrayList<Objective>(); //use copy ctor

        for(int i = 0; i < l.objectives.size(); i++)
        {
            objectives.add(new Objective(l.objectives.get(i)));
        }

        if (l.isBossFight())
        {
            boss = (Boss) l.boss.copy();
        }

        dlg = new Dialogue(l.dlg);

        initializeCollidableObjects();
        initializeWaterAndTallGrass();
        this.initializeBoss(boss);
        this.initializeCameras(cameras);
        this.initializeItems(items);
        this.initializeDoors(doors);
        this.initializeNPCs(guards);
        this.initializeObjectives(objectives);
        this.initializeSpawns(spawns);
        this.initializeWarps(warps);
        setSpriteTiles();
    }

    public void setAlert(boolean alert)
    {
        alertMode = alert;
    }

    public void setPrecip(String precip)
    {
        if (precip.startsWith("rain"))
        {
            snow = false;
            rain = true;
        }
        else if (precip.startsWith("snow"))
        {
            rain = false;
            snow = true;
        }
        else
        {
            rain = false;
            snow = false;
        }
    }

    public void setWeather(String weather)
    {
        if (weather.startsWith("gas"))
        {
            gas = true;
            dark = false;
            semidark = false;
            haze = false;
        }
        else if (weather.startsWith("dark"))
        {
            gas = false;
            dark = true;
            semidark = false;
            haze = false;
        }
        else if (weather.startsWith("semidark"))
        {
            gas = false;
            dark = false;
            semidark = true;
            haze = false;
        }
        else if (weather.startsWith("haze"))
        {
            gas = false;
            dark = false;
            semidark = false;
            haze = true;
        }
        else if (weather.startsWith("jam"))
        {
            gas = false;
            dark = false;
            semidark = false;
            haze = false;
            jam = true;
        }
        else
        {
            gas = false;
            dark = false;
            semidark = false;
            haze = false;
            jam = false;
        }

        if (weather.endsWith("_jam"))
        {
            jam = true;
        }
    }

    public void setTile(int id, int y, int x)
    {
        if ((y < levMap.length) && (y >= 0) && (x < levMap[0].length) && (x >= 0))
        {
            spriteMap[y][x] = game.getSpriteLoader().getSprite(id);
            levMap[y][x] = id;
        }
    }

    public void addNPC(NPC guard)
    {
        NPC newNPC = guard.copy();

        newNPC.bodyArmor = guard.bodyArmor;
        guards.add(newNPC);
    }

    public void removeNPC(int x, int y)
    {
        for(int i = 0; i < guards.size(); i++)
        {
            if ((guards.get(i).getPath().getStartingWaypoint().getXPos() == x) && (guards.get(i).getPath().getStartingWaypoint().getYPos() == y))
            {
                guards.remove(i);
                break;
            }
        }

    }

    public void addItem(Item item)
    {
        items.add(item.copy());
    }

    public void removeItem(int x, int y)
    {
        for(int i = 0; i < items.size(); i++)
        {
            if ((items.get(i).getTileX() == x) && (items.get(i).getTileY() == y))
            {
                items.remove(i);
                break;
            }
        }
    }

    public void addDoor(Door d)
    {
        doors.add(d.copy());
    }

    public void removeDoor(int x, int y)
    {
        for(int i = 0; i < doors.size(); i++)
        {
            if ((doors.get(i).getTileX() == x) && (doors.get(i).getTileY() == y))
            {
                doors.remove(i);
                break;
            }
        }
    }

    public Door getDoor(int x, int y)
    {
        Door d = null;

        for(int i = 0; i < doors.size(); i++)
        {
            if ((doors.get(i).getTileX() == x) && (doors.get(i).getTileY() == y))
            {
                d = doors.get(i);
                break;
            }
        }

        return d;
    }

    public void addWarp(Warp w)
    {
        warps.add(w.copy());
    }

    public void removeWarp(int x, int y)
    {
        for(int i = 0; i < warps.size(); i++)
        {
            if ((warps.get(i).getTileX() == x) && (warps.get(i).getTileY() == y))
            {
                warps.remove(i);
                break;
            }
        }
    }

    public Warp getWarp(int x, int y)
    {
        Warp w = null;

        for(int i = 0; i < warps.size(); i++)
        {
            if ((warps.get(i).getTileX() == x) && (warps.get(i).getTileY() == y))
            {
                w = warps.get(i);
                break;
            }
        }

        return w;
    }

    public void addSpawn(Spawn s)
    {
        spawns.add(s.copy());
    }

    public void removeSpawn(int x, int y)
    {
        for(int i = 0; i < spawns.size(); i++)
        {
            if ((spawns.get(i).tileX == x) && (spawns.get(i).tileY == y))
            {
                spawns.remove(i);
                break;
            }
        }
    }

    public Spawn getSpawn(int x, int y)
    {
        Spawn s = null;

        for(int i = 0; i < spawns.size(); i++)
        {
            if ((spawns.get(i).tileX == x) && (spawns.get(i).tileY == y))
            {
                s = spawns.get(i);
                break;
            }
        }

        return s;
    }

    public void addCamera(SecurityCamera c)
    {
        cameras.add(c.copy());
    }

    public void removeCamera(int x, int y)
    {
        for(int i = 0; i < cameras.size(); i++)
        {
            if ((cameras.get(i).getTileX() == x) && (cameras.get(i).getTileY() == y))
            {
                cameras.remove(i);
                break;
            }
        }
    }

    public SecurityCamera getCamera(int x, int y)
    {
        SecurityCamera c = null;

        for(int i = 0; i < cameras.size(); i++)
        {
            if ((cameras.get(i).getTileX() == x) && (cameras.get(i).getTileY() == y))
            {
                c = cameras.get(i);
                break;
            }
        }

        return c;
    }

    public NPC getNPC(int x, int y)
    {
        NPC n = null;

        for(int i = 0; i < guards.size(); i++)
        {
            if ((guards.get(i).getPath().getStartingWaypoint().getXPos() == x) && (guards.get(i).getPath().getStartingWaypoint().getYPos() == y))
            {
                n = guards.get(i);
                break;
            }
        }

        return n;
    }

    public void setDefaultGroundCover(int dgc)
    {
        System.out.println("DGC SETTING = " + dgc);
        System.out.println("OLD = " + defaultGroundCover);
        int old = defaultGroundCover;
        defaultGroundCover = dgc;
        for(int j = 0; j < levMap.length; j++)
        {
            for(int i = 0; i < levMap[0].length; i++)
            {
                if (levMap[j][i] == old)
                {
                    levMap[j][i] = defaultGroundCover;
                    spriteMap[j][i] = game.getSpriteLoader().getSprite(defaultGroundCover);
                }
            }
        }
    }

    public void resize(int dx, int dy)
    {
        int nX = levMap[0].length + dx;
        int nY = levMap.length + dy;

        if ((nX > 0) && (nY > 0))
        {
            int[][] copy = new int[levMap.length][levMap[0].length];

            //Copy levmap
            for(int j = 0; j < levMap.length; j++)
            {
                for(int i = 0; i < levMap[0].length; i++)
                {
                    copy[j][i] = levMap[j][i];
                }
            }

            //Create new levmap array
            levMap = new int[nY][nX];
            spriteMap = new Sprite[levMap.length][levMap[0].length];

            //Initialize all new tiles to defaultgroundcover
            for(int j = 0; j < levMap.length; j++)
            {
                for(int i = 0; i < levMap[0].length; i++)
                {
                    levMap[j][i] = defaultGroundCover;
                    spriteMap[j][i] = game.getSpriteLoader().getSprite(defaultGroundCover);
                }
            }

            //Copy old elements to new levmap
            for(int j = 0; j < copyUpTo(copy.length, nY); j++)
            {
                for(int i = 0; i < copyUpTo(copy[0].length, nX); i++)
                {
                    levMap[j][i] = copy[j][i];
                    spriteMap[j][i] = game.getSpriteLoader().getSprite(copy[j][i]);
                }
            }
        }
    }

    public void resize_dims(int nX, int nY)
    {
        if ((nX > 0) && (nY > 0))
        {
            int[][] copy = new int[levMap.length][levMap[0].length];

            //Copy levmap
            for(int j = 0; j < levMap.length; j++)
            {
                for(int i = 0; i < levMap[0].length; i++)
                {
                    copy[j][i] = levMap[j][i];
                }
            }

            //Create new levmap array
            levMap = new int[nY][nX];
            spriteMap = new Sprite[levMap.length][levMap[0].length];

            //Initialize all new tiles to defaultgroundcover
            for(int j = 0; j < levMap.length; j++)
            {
                for(int i = 0; i < levMap[0].length; i++)
                {
                    levMap[j][i] = defaultGroundCover;
                    spriteMap[j][i] = game.getSpriteLoader().getSprite(defaultGroundCover);
                }
            }

            //Copy old elements to new levmap
            for(int j = 0; j < copyUpTo(copy.length, nY); j++)
            {
                for(int i = 0; i < copyUpTo(copy[0].length, nX); i++)
                {
                    levMap[j][i] = copy[j][i];
                    spriteMap[j][i] = game.getSpriteLoader().getSprite(copy[j][i]);
                }
            }
        }
    }

    private int copyUpTo(int oldSize, int newSize)
    {
        if (oldSize > newSize)
        {
            return newSize;
        }
        else
        {
            return oldSize;
        }
    }

    public int[][] getObstacleMatrix()
    {
        int[][] obstacles = new int[levMap.length][levMap[0].length];
        
        for(int j = 0; j < levMap.length; j++)
        {
            for(int i = 0; i < levMap[0].length; i++)
            {
                obstacles[j][i] = obstacleMatrix[j][i];
            }
        }

        return obstacles;
    }

    public void makeAlertStage()
    {
        alertMode = true;
    }

    public boolean getAlertMode()
    {
        return alertMode;
    }

    public void initializeBoss(Boss b)
    {
        boss = b;
        
        if (b != null)
        {
            boss.setSprite(game.getSpriteLoader().getSprite(boss.getID() + 1));
        }
    }

    public Boss getBoss()
    {
        return boss;
    }

    public boolean isBossFight()
    {
        return (boss != null);
    }

    private void setSpriteTiles()
    {
        for(int j = 0; j < levMap.length; j++)
        {
            for(int i = 0; i < levMap[0].length; i++)
            {
                spriteMap[j][i] = game.getSpriteLoader().getSprite(levMap[j][i]);
            }
        }
    }

    private void initializeCollidableObjects()
    {
        collidableObjects = new ArrayList<ITObject>();

        for(int j = 0; j < levMap.length; j++)
        {
            for(int i = 0; i < levMap[0].length; i++)
            {
                //This is the range of "collidables" (walls)
                if (((levMap[j][i] >= 48) && (levMap[j][i] <= 54)) || ((levMap[j][i] >= 99) && (levMap[j][i] <= 107))
                        || ((levMap[j][i] >= 109) && (levMap[j][i] <= 117)) || (levMap[j][i] == 57) || ((levMap[j][i] > 144) && (levMap[j][i] < 220)))
                {
                    ITObject collidable = new ITObject(levMap[j][i], i*40, j*40);
                    //collidable.setSprite(game.getSpriteLoader().getSprite(levMap[j][i]));
                    collidableObjects.add(collidable);
                    obstacleMatrix[j][i] = 0;
                }
                else
                {
                    obstacleMatrix[j][i] = 1;
                }
            }
        }

    }

    public void openBossDoor(int bossIndex)
    {
//        if (bossIndex == 2)
//        {
//            System.out.println("Opening boss door");
//
//            levMap[4][3] = 59;
//            spriteMap[4][3] = game.getSpriteLoader().getSprite(levMap[4][3]);
//            initializeCollidableObjects();
//        }
//        else if (bossIndex == 3)
//        {
//            System.out.println("Opening boss door");
//
//            levMap[1][0] = 59;
//            spriteMap[1][0] = game.getSpriteLoader().getSprite(levMap[1][0]);
//            initializeCollidableObjects();
//        }
    }

    private void initializeWaterAndTallGrass()
    {
        water = new ArrayList<Water>();
        tallGrass = new ArrayList<TallGrass>();

        for(int j = 0; j < levMap.length; j++)
        {
            for(int i = 0; i < levMap[0].length; i++)
            {
                //This is the range of water
                if ((levMap[j][i] == 43) || (levMap[j][i] == 83))
                {
                    //Water waterTile = new Water(levMap[j][i], i*40, j*40);
                    Water waterTile = new Water(levMap[j][i], i, j);
                    //waterTile.setSprite(game.getSpriteLoader().getSprite(levMap[j][i]));
                    water.add(waterTile);
                }
                else if (levMap[j][i] == 45)
                {
                    //TallGrass tallGrassTile = new TallGrass(levMap[j][i], i*40, j*40);
                    TallGrass tallGrassTile = new TallGrass(levMap[j][i], i, j);
                    //tallGrassTile.setSprite(game.getSpriteLoader().getSprite(levMap[j][i]));
                    tallGrass.add(tallGrassTile);
                }
            }
        }

    }

    public void initializeNPCs(ArrayList<NPC> npcs)
    {
        guards = npcs;
    }

    public void initializeWarps(ArrayList<Warp> w)
    {
        warps = w;
    }

    public void initializeSpawns(ArrayList<Spawn> s)
    {
        spawns = s;
    }

    public void initializeCameras(ArrayList<SecurityCamera> c)
    {
        cameras = c;

        for(int i = 0; i < cameras.size(); i++)
        {
            cameras.get(i).setSprite(game.getSpriteLoader().getSprite(cameras.get(i).getID()));
        }
    }

    public void initializeDoors(ArrayList<Door> d)
    {
        doors = d;

        //Set this so RenderThread knows not to draw defaultGC over the doors
        for(int i = 0; i < doors.size(); i++)
        {
            doors.get(i).setSprite(game.getSpriteLoader().getSprite(doors.get(i).getID()));
            //setTile(doors.get(i).getID(), doors.get(i).getTileY(), doors.get(i).getTileX());
        }
    }

    public void initializeItems(ArrayList<Item> it)
    {
        items = it;

        //Set the Item sprites
        for(int i = 0; i < items.size(); i++)
        {
            items.get(i).setSprite(game.getSpriteLoader().getSprite(items.get(i).getID()));
        }
    }

    public void initializeObjectives(ArrayList<Objective> ob)
    {
        objectives = ob;

        //Set the Objective sprites
        for(int i = 0; i < objectives.size(); i++)
        {
            objectives.get(i).setSprite(game.getSpriteLoader().getSprite(objectives.get(i).getID()));
        }
    }

    public int getTile(int x, int y)
    {
        return levMap[y][x];
    }

    public int[][] getTiles()
    {
        return levMap;
    }

    public Sprite[][] getSpriteMap()
    {
        return spriteMap;
    }

    public int getDefaultGroundCover()
    {
        return defaultGroundCover;
    }

    public ArrayList<NPC> getNPCs()
    {
        ArrayList<NPC> npcs = new ArrayList<NPC>();

        for(int i = 0; i < guards.size(); i++)
        {
            NPC copy = guards.get(i).copy();

            if (guards.get(i).bodyArmor)
            {
                copy.bodyArmor = true;
            }

            npcs.add(copy);
        }

        return npcs;
    }

    public ArrayList<ITObject> getCollidables()
    {
        return collidableObjects;
    }

    public ArrayList<Warp> getWarps()
    {
        return warps;
    }

    public ArrayList<Door> getDoors()
    {
        return doors;
    }

    public ArrayList<SecurityCamera> getCameras()
    {
        ArrayList<SecurityCamera> tCameras = new ArrayList<SecurityCamera>();

        for(int i = 0; i < cameras.size(); i++)
        {
            tCameras.add(cameras.get(i).copy());
        }

        for(int i = 0; i < tCameras.size(); i++)
        {
            tCameras.get(i).setSprite(game.getSpriteLoader().getSprite(tCameras.get(i).getID()));
        }

        return tCameras;
    }

    public ArrayList<Spawn> getSpawns()
    {
        return spawns;
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    public ArrayList<Objective> getObjectives()
    {
        return objectives;
    }

    public ArrayList<Water> getWater()
    {
        return water;
    }

    public ArrayList<TallGrass> getTallGrass()
    {
        return tallGrass;
    }

    public void setDialogue(Dialogue d)
    {
        dlg = d;
    }

    public Dialogue getDialogue()
    {
        return dlg;
    }

    //Release all of the resources for better memory management
    public void destroy()
    {

    }
}
