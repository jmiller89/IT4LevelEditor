/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelEditor;

import java.util.ArrayList;

/**
 *
 * @author Jim
 */
public class Editor
{
    public LevelMap currRoom;
    public Level currLevel;
    private SpriteLoader sl;
    private EditorFrame ef;
    private LevelLoader levLoad;
    private LevelWriter levWrite;
    public SelectedPayload selectedPayload = new SelectedPayload();
    public String[] images;
    public String[] floorTiles;
    public String[] items;
    public String[] objectives;
    private int[] ftIndices;
    private int[] tileIndices;
    private int[] itemIndices;
    private int[] objectiveIndices;
    private int[][] copyBuffer = null;

    public static final String[] songs = {"DarkForest.ogg", "Warfare.ogg", "BrokenFragment.ogg",
    "RetroSteel.ogg", "IAmYourProduct.ogg", "AndTheSunReappeared.ogg", "FourBraveChampions.ogg",
    "BuildingUp.ogg", "TightSpot.ogg", "Cavedrips.ogg", "Mystic.ogg", "AssaultOnYosuke.ogg",
    "BreakDownInTheCold.ogg", "ConfusingJustice.ogg", "Frisson.ogg", "LaunchDetected.ogg",
    "Religions.ogg"};

    public Editor()
    {
        sl = new SpriteLoader();
        ef = new EditorFrame(this);

        firstNewLevel();

        images = new String[] {"Tile0", "Water", "sand", "TallGrass", "*gate", "*tree", "*bricks", "*HorizontalPipes",
        "*VerticalPipes", "*Concrete2", "*RockWall", "*SimpleConcrete",
        "*DryWall", "IndoorFloorTile", "woodfloor", "VerticalRoadCenter", "HorizontalRoadCenter",
        "HorizontalRoadSideLeft", "HorizontalRoadSideRight", "VerticalRoadSideLeft", "VerticalRoadSideRight", "RoadSideLowerLeftCorner",
        "RoadSideLowerRightCorner", "RoadSideUpperLeftCorner", "RoadSideUpperRightCorner", "grass", "Snow",
        "fancyfloor2", "IndoorFloorTile3", "whitetiles", "water2", "grass2",
        "LadderUp", "LadderDown", "desert", "duct", "*SnowTreeTop",
        "*SnowTreeBottom", "*PalmTreeTop", "*PalmTreeBottom", "*streetlight_left_top", "*streetlight_left_bottom",
        "*streetlight_right_top", "*streetlight_right_bottom", "*Crate", "*DarkSolid", "*SubTopLeft",
        "*SubTopRight", "*computer", "*NoseCone", "*MissileSegment", "*LeftFin",
        "*MiddleFin", "*RightFin", "fancyfloor", "IndoorFloorTile5", "IndoorFloorTile6",
        "*bed1", "*bed2", "chair", "*desk", "*desk2",
        "*computer_slacker", "*computer_slacker2", "*conferencedeskbottom", "*conferencedesktop", "fancycarpet", "*hpainting",
        "*painting", "*fancywall", "*locker", "*computer2", "*crate2",
        "*tombstone", "*StoveTop", "*ChemLab", "*GlassWall", "*bookcase",
        "*GrassWall", "*tank00", "*tank10", "*tank20", "*tank01",
        "*tank11", "*tank21", "*tank02", "*tank12", "*tank22",
        "*tankbarrel", "HorizontalStairs", "VerticalStairs",
        "*stonewall", "*stonewall2", "water3", "crimescene", "woodfloor2", "fancycarpet2", "surrogate1", "surrogate2", "surrogate3", "female_ally_prisoner_front",
        "*cactus", "*garagedoor", "*metalboxtop1", "*metalboxbottom1", "RoadCornerLeft", "RoadCornerRight",
        "*truck00", "*truck10", "*truck20", "*truck01", "*truck11", "*truck21", "*truck02", "truck12", "*truck22", "*FireExtinguisher", "RoadCornerLeft2", "RoadCornerRight2",
        "*painting2", "*painting3", "*painting4", "*painting5", "*airventclosed",
        "wateredgetop", "wateredgebottom", "wateredgeleft", "wateredgeright", "wateredgetopleft", "wateredgetopright", "wateredgebottomleft", "wateredgebottomright",
        "wateredgetopleftjunction", "wateredgetoprightjunction", "wateredgebottomleftjunction", "wateredgebottomrightjunction", "bathroom",
        "*treetop", "*treebottom", "*darktreetop", "*darktreebottom",
        "*cliff_face", "*cliff_left", "*cliff_right", "*cliff_cornerleft", "*cliff_cornerright", "*cliff_cornerupperleft", "*cliff_cornerupperright",
        "*cliff_back", "*GrassWallBottom", "stairs_left_down", "stairs_right_up", "stairs_right_down", "stairs_left_up",
        "*boat00", "boat10", "*boat20", "*boat01", "boat11", "*boat21", "*boat02", "*boat12", "*boat22", "*boat03", "*boat13", "*boat23", "*boat04", "*boat14", "*boat24",
        "*roof", "*roof2", "*roof3", "*roof4", "*window", "*window_dark", "*window_light"};

        floorTiles = new String[] {"Tile0", "Water", "Sand", "IndoorFloorTile", "woodfloor",
        "Grass", "Snow", "fancyfloor2", "IndoorFloorTile3", "whitetiles", "Water2",
        "Grass2", "Desert", "duct", "fancyfloor", "IndoorFloorTile5", "IndoorFloorTile6",
        "fancycarpet", "HorizontalStairs", "VerticalStairs", "water3", "woodfloor2", "fancycarpet2"};

        ftIndices = new int[] {0, 43, 44, 55, 56, 78, 79, 80, 81, 82, 83, 84, 97, 98, 118, 143, 144, 154, 220, 221, 254, 256, 257};

        tileIndices = new int[]{0, 43, 44, 45, 46, 47, 48,
                                49, 50, 51, 52, 53,
                                54, 55, 56, 60, 61,
                                62, 63, 64, 65, 66,
                                67, 68, 69, 78, 79,
                                80, 81, 82, 83, 84,
                                85, 86, 97, 98, 99,
                                100, 101, 102, 103, 104,
                                105, 106, 107, 109, 110,
                                111, 112, 113, 114, 115,
                                116, 117, 118, 143, 144,
                                145, 146, 147, 148, 149,
                                150, 151, 152, 153, 154, 155,
                                156, 157, 158, 159, 160,
                                161, 178, 179, 180, 208,
                                209, 210, 211, 212, 213,
                                214, 215, 216, 217, 218,
                                219, 220, 221,
                                252, 253, 254, 255, 256, 257, 258, 259, 260, 270,
                                186, 187, 299, 300, 308, 309,
                                358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 369, 370, 371, 372, 373, 374, 377,
                                379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391,
                                392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404,
                                445, 446, 447, 448,
                                495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509,
                                511, 512, 513, 514, 515, 516, 517};

        System.out.println("Num tiles: " + tileIndices.length);

        items = new String[] {"MEDKIT", "SECONDARY_AMMO", "PRIMARY_AMMO", "SECONDARY_MAG", "PRIMARY_MAG",
        "TRANQ_PISTOL", "PISTOL", "ASSAULT_RIFLE", "SECONDARY_SILENCER", "CARDKEY_1", "CARDKEY_2", "CARDKEY_3", "CARDKEY_4", "CARDKEY_5",
        "CARDKEY_6", "CARDKEY_7", "CARDKEY_8", "CARDKEY_9", "CARDKEY_10",
        "BOOSTER_KIT", "SMG", "SHOTGUN", "GRENADE", "GASMASK", "NVG", "BODY_ARMOR", "PRIMARY_SILENCER", "LANDMINE",
        "LASER_HORIZONTAL", "LASER_VERTICAL", "MINE_DETECTOR", "C4"};

        itemIndices = new int[] {70, 74, 74, 74, 74, 250, 71, 73, 72, 75, 76, 77, 201, 202, 75, 76, 77, 201, 202, 96, 205, 206, 510, 204, 203, 207, 72, 293, 294, 295, 296, 510};

        objectives = new String[] {"Waypoint", "Laptop", "Push Button", "Documents", "Briefcase"};
        objectiveIndices = new int[] {251, 222, 223, 224, 225};

        ef.addMapPanel();
        ef.addEditorPanel();
        ef.addDGCPanel();
        ef.addREPanel();

        levLoad = new LevelLoader(this);
        levWrite = new LevelWriter(this);

        ef.newMap();

    }

    public SpriteLoader getSpriteLoader()
    {
        return sl;
    }

    public Sprite[][] getSpriteMap()
    {
        return currRoom.getSpriteMap();
    }

    public int getDefaultGroundCover()
    {
        return currRoom.getDefaultGroundCover();
    }

    public ArrayList<NPC> getNPCs()
    {
        return currRoom.getNPCs();
    }

    public ArrayList<SecurityCamera> getCameras()
    {
        return currRoom.getCameras();
    }

    public ArrayList<Spawn> getSpawns()
    {
        return currRoom.getSpawns();
    }

    public void newLevel()
    {
        currLevel = new Level(1, 0, this);

        int[][] tileMap = new int[15][15];

        for(int j = 0; j < 15; j++)
        {
            for(int i = 0; i < 15; i++)
            {
                tileMap[j][i] = 0;
            }
        }

        currRoom = new LevelMap(this, tileMap, 0);

        currLevel.addLevelMap(new LevelMap(currRoom));

        ef.updateLS();
        ef.updateRS();
        
        ef.updateMapPanel(true);
    }

    //Try not to add stuff to this.
    private void firstNewLevel()
    {
        currLevel = new Level(1, 0, this);

        int[][] tileMap = new int[15][15];

        for(int j = 0; j < 15; j++)
        {
            for(int i = 0; i < 15; i++)
            {
                tileMap[j][i] = 0;
            }
        }
        
        currRoom = new LevelMap(this, tileMap, 0);

    }

    public void newRoom()
    {
        if (currLevel.getNumRooms() == 0)
        {
            currLevel.addLevelMap(new LevelMap(currRoom));
        }
        
        int[][] tileMap = new int[15][15];

        for(int j = 0; j < 15; j++)
        {
            for(int i = 0; i < 15; i++)
            {
                tileMap[j][i] = 0;
            }
        }

        currLevel.addLevelMap(new LevelMap(this, tileMap, 0));
        
        ef.updateMapPanel(true);

        ef.updateREPanel();

        ef.updateDialog();
        ef.updatePlayerPath();

        ef.updateRS();
        ef.updateWarpRooms();
        
    }

    public void deleteRoom(int index)
    {
        currLevel.removeLevelMap(index);
        
        if (currLevel.getNumRooms() == 0)
        {
            int[][] tileMap = new int[15][15];

            for(int j = 0; j < 15; j++)
            {
                for(int i = 0; i < 15; i++)
                {
                    tileMap[j][i] = 0;
                }
            }
            
            currLevel.addLevelMap(new LevelMap(this, tileMap, 0));

            ef.updateMapPanel(true);
        }

        currLevel.removeAndReorderObjectives(index);

        ef.updateREPanel();
        ef.updateDialog();
        ef.updatePlayerPath();
        ef.updateRS();
        ef.updateWarpRooms();
    }

    public void changeRoom(int index)
    {
        System.out.println("Changing room");
        currRoom = currLevel.getLevelMap(index);
        ef.updateDialog();
        ef.updatePlayerPath();
        ef.updateMapPanel(true);
        
        ef.updateRS();

        ef.updateDGC(getDGCIndex(currRoom.getDefaultGroundCover()));
    }

    private int getDGCIndex(int d)
    {
        int dg = 0;
        boolean found = false;
        int i = 0;

        while ((!found) && (i < ftIndices.length))
        {
            if (d == ftIndices[i])
            {
                found = true;
                dg = i;
            }

            i++;
        }


        return dg;
    }

    public void updateEditPanel()
    {
        ef.updateEditPanel();
    }

    public void updateRoomSettings()
    {
        currRoom.setAlert(selectedPayload.alert);
        currRoom.setWeather(selectedPayload.weather);
        currRoom.setPrecip(selectedPayload.precip);
        currRoom.songIndex = selectedPayload.songIndex;
        currRoom.forceprone = selectedPayload.forceprone;
    }

    public void updateRoom()
    {
        if (selectedPayload.selectionMode == SelectionMode.CREATE)
        {
            if (selectedPayload.mode == Mode.TILE)
            {
                currRoom.setTile(tileIndices[selectedPayload.id], selectedPayload.y, selectedPayload.x);
            }
            else if (selectedPayload.mode == Mode.NPC)
            {
                currRoom.addNPC(makeNPC(selectedPayload.x, selectedPayload.y, selectedPayload.gt, selectedPayload.path, selectedPayload.friendly));
            }
            else if (selectedPayload.mode == Mode.BOSS)
            {
                //int bossID, int x, int y, GuardType bossType, int bossHealth, int bossDamage, int speed, int viewDistance, boolean bdyArmor
                Boss boss = new Boss(selectedPayload.id, 40 * selectedPayload.x, 40 * selectedPayload.y, selectedPayload.gt, selectedPayload.health, selectedPayload.damage, selectedPayload.speed, selectedPayload.viewdistance, selectedPayload.bodyArmor, null);

                boss.name = selectedPayload.name;

                boss.setPreDialog(makeDialog(selectedPayload.dialog_pre));
                boss.setPostDialog(makeDialog(selectedPayload.dialog_post));

                currRoom.initializeBoss(boss);
            }
            else if (selectedPayload.mode == Mode.ITEM)
            {
                currRoom.addItem(makeItem(selectedPayload.x, selectedPayload.y, selectedPayload.itemID, selectedPayload.rank));
            }
            else if (selectedPayload.mode == Mode.DOOR)
            {
                //int id, int locX, int locY, int secLev, int objLev, boolean drk
                if (selectedPayload.secLev >= 0)
                {
                    currRoom.addDoor(new Door(57, 40*selectedPayload.x, 40*selectedPayload.y, selectedPayload.secLev, selectedPayload.objLev, selectedPayload.isDark));
                }
                else
                {
                    currRoom.addDoor(new CrawlDoor(378, 40 * selectedPayload.x, 40 * selectedPayload.y));
                }
            }
            else if (selectedPayload.mode == Mode.WARP)
            {
                //int id, int locX, int locY, int lX, int pX, int pY, boolean nextLvWarp, 0
                currRoom.addWarp(new Warp(0, 40*selectedPayload.x, 40*selectedPayload.y, selectedPayload.gotoRoom, selectedPayload.toX, selectedPayload.toY, selectedPayload.nextLev, 0));
            }
            else if (selectedPayload.mode == Mode.OBJECTIVE)
            {
                //int roomIndex = currRoom.id;
                //int id, int x, int y, int map_Index, String objName
                Objective newObjective = new Objective(objectiveIndices[selectedPayload.id], selectedPayload.x, selectedPayload.y, currRoom.id, selectedPayload.name);
                newObjective.dialog = makeDialog(selectedPayload.dialog);
                System.out.println("Adding Objective to room " + currRoom.id);
                currLevel.addObjective(newObjective);
                currLevel.initializeObjectives();
            }
            else if (selectedPayload.mode == Mode.SPAWN)
            {
                //int x, int y, GuardType gt, int lim, boolean armor
                currRoom.addSpawn(new Spawn(selectedPayload.x, selectedPayload.y, selectedPayload.gt, selectedPayload.capacity, selectedPayload.bodyArmor));
            }
            else if (selectedPayload.mode == Mode.CAMERA)
            {   
                int id = 197; //197 == Direction.UP
                int dx = 0;
                int dy = 0;

                if (selectedPayload.dir == Direction.UP)
                {
                    //id = 197;
                    dx = selectedPayload.delta;
                }
                else if (selectedPayload.dir == Direction.DOWN)
                {
                    //id = 198;
                    dx = selectedPayload.delta;
                }
                else if (selectedPayload.dir == Direction.LEFT)
                {
                    //id = 199;
                    dy = selectedPayload.delta;
                }
                else if (selectedPayload.dir == Direction.RIGHT)
                {
                    //id = 200;
                    dy = selectedPayload.delta;
                }

                if (selectedPayload.cameraType == SecurityCameraType.NORMAL)
                {
                    id = 197;
                }
                else if (selectedPayload.cameraType == SecurityCameraType.GUN)
                {
                    id = 483;
                }
                else if (selectedPayload.cameraType == SecurityCameraType.DRONE)
                {
                    id = 487;
                }
                else if (selectedPayload.cameraType == SecurityCameraType.GUN_DRONE)
                {
                    id = 491;
                }

                if (selectedPayload.dir == Direction.DOWN)
                {
                    id += 1;
                }
                else if (selectedPayload.dir == Direction.LEFT)
                {
                    id += 2;
                }
                else if (selectedPayload.dir == Direction.RIGHT)
                {
                    id += 3;
                }

                //int x, int y, Direction d, WaypointBehavior b
                Waypoint start = new Waypoint(selectedPayload.x, selectedPayload.y, selectedPayload.dir, WaypointBehavior.CONTINUE);
                Waypoint end = new Waypoint((selectedPayload.x + dx), (selectedPayload.y + dy), selectedPayload.dir, WaypointBehavior.CONTINUE);

                //int id, int x, int y, Direction dir, Waypoint way1, Waypoint way2, boolean isFixed
                currRoom.addCamera(new SecurityCamera(id, 40 * selectedPayload.x, 40 * selectedPayload.y, selectedPayload.dir, start, end, selectedPayload.isFixed, selectedPayload.cameraType));
            }
        }
        else if (selectedPayload.selectionMode == SelectionMode.DELETE)
        {
            if (selectedPayload.mode == Mode.TILE)
            {
                currRoom.setTile(currRoom.getDefaultGroundCover(), selectedPayload.y, selectedPayload.x);
            }
            else if (selectedPayload.mode == Mode.NPC)
            {
                currRoom.removeNPC(selectedPayload.x, selectedPayload.y);
            }
            else if (selectedPayload.mode == Mode.BOSS)
            {
                currRoom.initializeBoss(null);
            }
            else if (selectedPayload.mode == Mode.ITEM)
            {
                currRoom.removeItem(selectedPayload.x, selectedPayload.y);
            }
            else if (selectedPayload.mode == Mode.DOOR)
            {
                currRoom.removeDoor(selectedPayload.x, selectedPayload.y);
            }
            else if (selectedPayload.mode == Mode.WARP)
            {
                currRoom.removeWarp(selectedPayload.x, selectedPayload.y);
            }
            else if (selectedPayload.mode == Mode.OBJECTIVE)
            {
                //int roomIndex = currRoom.id;
                //int id, int x, int y, int map_Index, String objName
                currLevel.removeObjective(selectedPayload.x, selectedPayload.y, currRoom.id);
            }
            else if (selectedPayload.mode == Mode.SPAWN)
            {
                currRoom.removeSpawn(selectedPayload.x, selectedPayload.y);
            }
            else if (selectedPayload.mode == Mode.CAMERA)
            {
                currRoom.removeCamera(selectedPayload.x, selectedPayload.y);
            }
        }
        else if (selectedPayload.selectionMode == SelectionMode.EDIT)
        {
            boolean found = false;

            if (selectedPayload.mode == Mode.NPC)
            {
                NPC n = null;
                n = currRoom.getNPC(selectedPayload.x, selectedPayload.y);

                if (n != null)
                {
                    editing(selectedPayload.x, selectedPayload.y);
                    found = true;
                }

                ef.editNPC(n);
            }
            if (selectedPayload.mode == Mode.BOSS)
            {
                Boss b = null;
                b = currRoom.getBoss();

                if (b != null)
                {
                    editing(selectedPayload.x, selectedPayload.y);
                    found = true;
                }

                ef.editBoss(b);
            }
            if (selectedPayload.mode == Mode.DOOR)
            {
                Door d = null;
                d = currRoom.getDoor(selectedPayload.x, selectedPayload.y);

                if (d != null)
                {
                    editing(selectedPayload.x, selectedPayload.y);
                    found = true;
                }

                ef.editDoor(d);
            }
            if (selectedPayload.mode == Mode.WARP)
            {
                Warp w = null;
                w = currRoom.getWarp(selectedPayload.x, selectedPayload.y);

                if (w != null)
                {
                    editing(selectedPayload.x, selectedPayload.y);
                    found = true;
                }

                ef.editWarp(w);
            }
            if (selectedPayload.mode == Mode.OBJECTIVE)
            {
                Objective o = null;
                o = currLevel.getObjective(selectedPayload.x, selectedPayload.y, currRoom.id);

                if (o != null)
                {
                    editing(selectedPayload.x, selectedPayload.y);
                    found = true;
                }

                ef.editObjective(o);
            }
            if (selectedPayload.mode == Mode.SPAWN)
            {
                Spawn s = null;
                s = currRoom.getSpawn(selectedPayload.x, selectedPayload.y);

                if (s != null)
                {
                    editing(selectedPayload.x, selectedPayload.y);
                    found = true;
                }
                
                ef.editSpawn(s);
            }
            if (selectedPayload.mode == Mode.CAMERA)
            {
                SecurityCamera c = null;
                c = currRoom.getCamera(selectedPayload.x, selectedPayload.y);

                if (c != null)
                {
                    editing(selectedPayload.x, selectedPayload.y);
                    found = true;
                }

                ef.editCamera(c);
            }

            if (!found)
            {
                editing(-1, -1);
            }

        }

        //Misc.
        if (selectedPayload.mode == Mode.LEVEL_SETTINGS)
        {
            currLevel.setStartX(selectedPayload.x);
            currLevel.setStartY(selectedPayload.y);

            ef.updateLS();
        }
        if (selectedPayload.mode == Mode.ROOM_SETTINGS)
        {
            ef.updateRS();
        }
    }

    private void editing(int x, int y)
    {
        ef.setEdit(x, y);
    }

    public void notEditing()
    {
        ef.setEdit(-1, -1);
    }

    public void clearEdits()
    {
        ef.clear();
    }

    private Item makeItem(int x, int y, int id, int rank)
    {
        //int id, int x, int y, ItemType it
        ItemType type = ItemType.MEDKIT;
        String itid = items[id];
        type = getItemType(itid);

        return new Item(this.itemIndices[id], x * 40, y * 40, type, rank);
    }

    private ItemType getItemType(String id)
    {
        ItemType it = ItemType.MEDKIT;

        try
        {
            it = ItemType.valueOf(id);
        }
        catch(Exception e)
        {
            it = ItemType.MEDKIT;
        }

        return it;
    }

    public void updateNPC(NPC npc)
    {
        currRoom.removeNPC(npc.getPath().getStartingWaypoint().getXPos(), npc.getPath().getStartingWaypoint().getYPos());
        NPC newNPC = makeNPC(selectedPayload.x, selectedPayload.y, selectedPayload.gt, selectedPayload.path, selectedPayload.friendly);

        if (newNPC != null)
        {
            if (selectedPayload.bodyArmor)
            {
                newNPC.bodyArmor = true;
            }
            
            currRoom.addNPC(newNPC);            
        }
    }

    public void updateBoss()
    {
        Boss boss = new Boss(selectedPayload.id, 40 * selectedPayload.x, 40 * selectedPayload.y, selectedPayload.gt, selectedPayload.health, selectedPayload.damage, selectedPayload.speed, selectedPayload.viewdistance, selectedPayload.bodyArmor, currRoom.getBoss().event);

        boss.name = selectedPayload.name;

        boss.setPreDialog(makeDialog(selectedPayload.dialog_pre));
        boss.setPostDialog(makeDialog(selectedPayload.dialog_post));

        currRoom.initializeBoss(boss);
    }

    public void updatePlayerPath()
    {
        currRoom.playerPath = makePath(selectedPayload.path);
        if (currRoom.playerPath != null)
        {
            System.out.println("Player path is:\n" + currRoom.playerPath.toString());
        }
    }

    public void updateDoor(Door d)
    {
        if (d != null)
        {
            int x = d.getTileX();
            int y = d.getTileY();
            currRoom.removeDoor(d.getTileX(), d.getTileY());
            //int id, int locX, int locY, int secLev, int objLev, boolean drk
            Door newDoor;
            if (selectedPayload.secLev >= 0)
            {
                newDoor = new Door(57, 40 * x, 40 * y, selectedPayload.secLev, selectedPayload.objLev, selectedPayload.isDark);
            }
            else
            {
                newDoor = new CrawlDoor(378, 40 * x, 40 * y);
            }
            currRoom.addDoor(newDoor);
        }
    }

    public void updateWarp(Warp w)
    {
        if (w != null)
        {
            int x = w.getTileX();
            int y = w.getTileY();

            currRoom.removeWarp(x, y);
            //int id, int locX, int locY, int lX, int pX, int pY, boolean nextLvWarp, 0
            Warp newWarp = new Warp(0, 40*x, 40*y, selectedPayload.gotoRoom, selectedPayload.toX, selectedPayload.toY, selectedPayload.nextLev, 0);
            currRoom.addWarp(newWarp);
        }
    }

    public void updateObjective(Objective o)
    {
        if (o != null)
        {
            int x = o.getTileX();
            int y = o.getTileY();

            currLevel.removeObjective(x, y, currRoom.id);

            //int id, int x, int y, int map_Index, String objName
            Objective newObjective = new Objective(objectiveIndices[selectedPayload.id], x, y, currRoom.id, selectedPayload.name);
            newObjective.dialog = makeDialog(selectedPayload.dialog);

            currLevel.addObjective(newObjective);
            currLevel.initializeObjectives();
        }
    }

    public void updateSpawn(Spawn s)
    {
        if (s != null)
        {
            int x = s.tileX;
            int y = s.tileY;

            currRoom.removeSpawn(x, y);
            //int x, int y, GuardType gt, int lim, boolean armor
            Spawn newSpawn = new Spawn(x, y, selectedPayload.gt, selectedPayload.capacity, selectedPayload.bodyArmor);
            currRoom.addSpawn(newSpawn);
        }
    }

    public void updateCamera(SecurityCamera c)
    {
        if (c != null)
        {
            int x = c.getTileX();
            int y = c.getTileY();

            int id = 197; //197 == Direction.UP
            int dx = 0;
            int dy = 0;

            if (selectedPayload.dir == Direction.UP)
            {
                id = 197;
                dx = selectedPayload.delta;
            }
            else if (selectedPayload.dir == Direction.DOWN)
            {
                id = 198;
                dx = selectedPayload.delta;
            }
            else if (selectedPayload.dir == Direction.LEFT)
            {
                id = 199;
                dy = selectedPayload.delta;
            }
            else if (selectedPayload.dir == Direction.RIGHT)
            {
                id = 200;
                dy = selectedPayload.delta;
            }
            
            if (selectedPayload.cameraType == SecurityCameraType.GUN)
            {
                id = 483;
            }
            else if (selectedPayload.cameraType == SecurityCameraType.DRONE)
            {
                id = 487;
            }
            else if (selectedPayload.cameraType == SecurityCameraType.GUN_DRONE)
            {
                id = 491;
            }

            if (selectedPayload.dir == Direction.DOWN)
            {
                id += 1;
            }
            else if (selectedPayload.dir == Direction.LEFT)
            {
                id += 2;
            }
            else if (selectedPayload.dir == Direction.RIGHT)
            {
                id += 3;
            }

            Waypoint start = new Waypoint(selectedPayload.x, selectedPayload.y, selectedPayload.dir, WaypointBehavior.CONTINUE);
            Waypoint end = new Waypoint((selectedPayload.x + dx), (selectedPayload.y + dy), selectedPayload.dir, WaypointBehavior.CONTINUE);

            currRoom.removeCamera(x, y);
            //int id, int x, int y, Direction dir, Waypoint way1, Waypoint way2, boolean isFixed
            SecurityCamera newCamera = new SecurityCamera(id, 40*x, 40*y, selectedPayload.dir, start, end, selectedPayload.isFixed, selectedPayload.cameraType);
            currRoom.addCamera(newCamera);
        }
    }

    public void resetDoor()
    {
        ef.editDoor(null);
    }

    public void resetWarp()
    {
        ef.editWarp(null);
    }

    public void resetObjective()
    {
        ef.editObjective(null);
    }

    public void resetSpawn()
    {
        ef.editSpawn(null);
    }

    public void resetCamera()
    {
        ef.editCamera(null);
    }

    private NPC makeNPC(int x, int y, GuardType gt, String path, boolean friendly)
    {
        NPC myNPC = null;

        if (path.equals(""))
        {
            path = "" + x + " " + y + " " + Direction.DOWN + " " + WaypointBehavior.STOP;
        }

        Path p = makePath(path);

        myNPC = NPC.create(gt, x, y, Direction.DOWN, NPCStatus.PATROL, friendly, p);

        if (myNPC != null)
        {
            myNPC.setX(40 * myNPC.getPath().getStartingWaypoint().getXPos());
            myNPC.setY(40 * myNPC.getPath().getStartingWaypoint().getYPos());
            myNPC.changeDirection(myNPC.getPath().getStartingWaypoint().getDirection());
        }

        return myNPC;
    }

    private Path makePath(String path)
    {
        Path p = new Path();
        ArrayList<Waypoint> w = new ArrayList<Waypoint>();

        String[] wStrings = path.split("\\n");

        for(int i = 0; i < wStrings.length; i++)
        {
            String[] varStrings = wStrings[i].split(" ");
            System.out.println("Length of varStrings is " + varStrings.length);
            int x = 1;
            
            try
            {
                x = Integer.parseInt(varStrings[0]);
            }
            catch(Exception e)
            {

            }

            int y = 1;
            
            try
            {
                y = Integer.parseInt(varStrings[1]);
            }
            catch(Exception e)
            {

            }

            Direction d = Direction.DOWN;
            WaypointBehavior b = WaypointBehavior.STOP;

            if (varStrings[2].equalsIgnoreCase(Direction.UP.toString()))
            {                
                d = Direction.UP;
            }
            if (varStrings[2].equalsIgnoreCase(Direction.DOWN.toString()))
            {
                d = Direction.DOWN;
            }
            if (varStrings[2].equalsIgnoreCase(Direction.LEFT.toString()))
            {
                d = Direction.LEFT;
            }
            if (varStrings[2].equalsIgnoreCase(Direction.RIGHT.toString()))
            {
                d = Direction.RIGHT;
            }

            try
            {
                b = WaypointBehavior.valueOf(varStrings[3].toUpperCase());
            }
            catch(Exception e)
            {
                b = WaypointBehavior.STOP;
            }

            /*
            if (varStrings[3].equalsIgnoreCase(WaypointBehavior.CONTINUE.toString()))
            {
                b = WaypointBehavior.CONTINUE;
            }
            if (varStrings[3].equalsIgnoreCase(WaypointBehavior.LONG_WAIT_AND_CONTINUE.toString()))
            {
                b = WaypointBehavior.LONG_WAIT_AND_CONTINUE;
            }
            if (varStrings[3].equalsIgnoreCase(WaypointBehavior.SLEEP.toString()))
            {
                b = WaypointBehavior.SLEEP;
            }
            if (varStrings[3].equalsIgnoreCase(WaypointBehavior.STOP.toString()))
            {
                b = WaypointBehavior.STOP;
            }
            if (varStrings[3].equalsIgnoreCase(WaypointBehavior.WAIT_AND_CONTINUE.toString()))
            {
                b = WaypointBehavior.WAIT_AND_CONTINUE;
            }
            if (varStrings[3].equalsIgnoreCase(WaypointBehavior.FOLLOW_PLAYER.toString()))
            {
                b = WaypointBehavior.FOLLOW_PLAYER;
            }
            */
            
            w.add(new Waypoint(x, y, d, b));            
        }
        
        p.addWaypoints(w);

        return p;
    }

    public void setDialog(String dlg)
    {
        Dialog d = new Dialog();

        String[] dStrings = dlg.split("\\n");

        for(int i = 0; i < dStrings.length; i++)
        {
            d.add(dStrings[i]);
        }

        currRoom.setDialogue(d);
    }

    private Dialog makeDialog(String dlg)
    {
        Dialog d = new Dialog();

        if (dlg.length() > 0)
        {
            String[] dStrings = dlg.split("\\n");

            for(int i = 0; i < dStrings.length; i++)
            {
                d.add(dStrings[i]);
            }
        }

        return d;
    }

    public void updateDGC(int dgc)
    {
        System.out.println("Update dgc");
        int groundcover = this.ftIndices[dgc];
        currRoom.setDefaultGroundCover(groundcover);
        ef.updateMapPanel(false);
    }

    public void resizeRoom(int dX, int dY)
    {
        currRoom.resize(dX, dY);
    }

    public void resizeRoomDims(int nX, int nY)
    {
        currRoom.resize_dims(nX, nY);
    }

    public int tileCoord(int z)
    {
        return (z / 40);
    }

    public void loadMap(String fp)
    {
        currLevel = levLoad.getLevel(fp, 0, false);
        currRoom = currLevel.getLevelMap(0);
        ef.updateMapPanel(true);
        ef.updateDialog();
        ef.updatePlayerPath();
        ef.updateLS();
        ef.updateRS();
        ef.updateWarpRooms();
    }

    public void saveMap(String fp)
    {
        //Save the map
        levWrite.writeLevel(fp);
    }

    public void enableNPCEditing()
    {
        ef.enableNPCEditing();
    }

    public void disableNPCEditing()
    {
        ef.disableNPCEditing();
    }

    public void clearNPCPathText()
    {
        ef.clearNPCPathText();
    }

    public void changeMode(String m)
    {
        if (m.equals(Mode.TILE.toString()))
        {
            ef.changeMode(Mode.TILE);
        }
        if (m.equals(Mode.NPC.toString()))
        {
            ef.changeMode(Mode.NPC);
        }
        if (m.equals(Mode.WARP.toString()))
        {
            ef.changeMode(Mode.WARP);
        }
        if (m.equals(Mode.DOOR.toString()))
        {
            ef.changeMode(Mode.DOOR);
        }
        if (m.equals(Mode.BOSS.toString()))
        {
            ef.changeMode(Mode.BOSS);
        }
        if (m.equals(Mode.CAMERA.toString()))
        {
            ef.changeMode(Mode.CAMERA);
        }
        if (m.equals(Mode.DIALOG.toString()))
        {
            ef.changeMode(Mode.DIALOG);
        }
        if (m.equals(Mode.OBJECTIVE.toString()))
        {
            ef.changeMode(Mode.OBJECTIVE);
        }
        if (m.equals(Mode.SPAWN.toString()))
        {
            ef.changeMode(Mode.SPAWN);
        }
        if (m.equals(Mode.ITEM.toString()))
        {
            ef.changeMode(Mode.ITEM);
        }
        if (m.equals(Mode.ROOM_SETTINGS.toString()))
        {
            ef.changeMode(Mode.ROOM_SETTINGS);
        }
        if (m.equals(Mode.LEVEL_SETTINGS.toString()))
        {
            ef.changeMode(Mode.LEVEL_SETTINGS);
        }
        if (m.equals(Mode.PLAYER_PATH.toString()))
        {
            ef.changeMode(Mode.PLAYER_PATH);
        }

    }

    public EditorFrame getEditorFrame()
    {
        return ef;
    }

    public void copyTiles(int x1, int y1, int x2, int y2)
    {
        int startX = Math.min(x1, x2);
        int endX = Math.max(x1, x2);
        int startY = Math.min(y1, y2);
        int endY = Math.max(y1, y2);

        int height = (endY - startY) + 1;
        int width = (endX - startX) + 1;

        copyBuffer = new int[height][width];

        for(int j = 0; j < copyBuffer.length; j++)
        {
            for(int i = 0; i < copyBuffer[j].length; i++)
            {
                copyBuffer[j][i] = currRoom.getTile(startX + i, startY + j);
            }
        }

        System.out.println("Copied " + height + "x" + width + " tiles");
    }

    public void pasteTiles(int x, int y)
    {
        if (copyBuffer != null)
        {
            for(int j = 0; j < copyBuffer.length; j++)
            {
                for(int i = 0; i < copyBuffer[j].length; i++)
                {
                    currRoom.setTile(copyBuffer[j][i], y+j, x+i);
                }
            }

            System.out.println("Pasted " + copyBuffer.length + "x" + copyBuffer[0].length + " tiles");
        }
    }
}
