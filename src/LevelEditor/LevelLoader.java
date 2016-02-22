/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelEditor;

/**
 *
 * @author Jim
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.Scanner;
import java.util.ArrayList;

public class LevelLoader
{
    private Editor game;

    //Tags
    private static final String ROOM_START = "[room]";
    private static final String ROOM_END = "[/room]";
    private static final String HEADER_START = "[header]";
    private static final String HEADER_END = "[/header]";
    private static final String TM_START = "[tileMap]";
    private static final String TM_END = "[/tileMap]";
    private static final String ITEMS_START = "[items]";
    private static final String ITEMS_END = "[/items]";
    private static final String NPCS_START = "[npcs]";
    private static final String NPCS_END = "[/npcs]";
    private static final String PATH_START = "[path]";
    private static final String PATH_END = "[/path]";
    private static final String WARPS_START = "[warps]";
    private static final String WARPS_END = "[/warps]";
    private static final String DLG_START = "[dialog]";
    private static final String DLG_END = "[/dialog]";
    private static final String BOSS_START = "[boss]";
    private static final String BOSS_END = "[/boss]";
    private static final String DOORS_START = "[doors]";
    private static final String DOORS_END = "[/doors]";
    private static final String CAMERAS_START = "[cameras]";
    private static final String CAMERAS_END = "[/cameras]";
    private static final String SPAWNS_START = "[spawns]";
    private static final String SPAWNS_END = "[/spawns]";
    private static final String OBJECTIVES_START = "[objectives]";
    private static final String OBJECTIVES_END = "[/objectives]";
    private static final String PLAYER_PATH_START = "[playerpath]";
    private static final String PLAYER_PATH_END = "[/playerpath]";

    //subtags
    private static final String PRE_START = "[pre]";
    private static final String PRE_END = "[/pre]";
    private static final String POST_START = "[post]";
    private static final String POST_END = "[/post]";

    private static final String CAMERA_START = "[camera]";
    private static final String CAMERA_END = "[/camera]";

    private static final String ARMORED = "_ARMORED";
    private static final String STRIP_ITEMS = "STRIP_ITEMS";
    private static final String TRANSITION = "TRANSITION";

    private int sizeX = 20;

    private int sizeY = 15;

    //Ctor
    public LevelLoader(Editor g)
    {
        game = g;
    }

    public Level getLevel(String filePath, int id, boolean internal)
    {
        int sections = 1;
        Level level = null;
        Scanner scanner = null;
        String line = "";
        int[][] tm = null;
        ArrayList<LevelMap> levmaps = new ArrayList<LevelMap>();
        boolean readStartHeader = false;
        int currentLevelMap = 0;
        ArrayList<NPC> npcs = new ArrayList<NPC>();
        ArrayList<Item> items = new ArrayList<Item>();
        ArrayList<Warp> warps = new ArrayList<Warp>();
        ArrayList<Door> doors = new ArrayList<Door>();
        ArrayList<SecurityCamera> cameras = new ArrayList<SecurityCamera>();
        ArrayList<Spawn> spawns = new ArrayList<Spawn>();
        Dialogue dialog = new Dialogue();
        Boss boss = null;
        Path playerPath = null;

        boolean gas = false;
        boolean dark = false;
        boolean semidark = false;
        boolean midnight = false;
        boolean jam = false;
        boolean rain = false;
        boolean snow = false;
        boolean forceprone = false;

        int song = 0;

        int defaultGroundCover = 0;

        boolean alertStage = false;

        //try
        {
            if (internal)
            {
                URL url = this.getClass().getClassLoader().getResource(filePath);

                try
                {
                    scanner = new Scanner(url.openStream());
                }
                catch(Exception e)
                {

                }
            }
            else
            {
                try
                {
                    scanner = new Scanner(new BufferedReader(new FileReader(filePath)));
                }
                catch(Exception e)
                {

                }
            }

            System.out.println("Opened scanner");

            while(scanner.hasNextLine())
            {
                line = scanner.nextLine();

                //System.out.println(line);

                if (line.startsWith(ROOM_START))
                {
                    npcs = new ArrayList<NPC>();
                    items = new ArrayList<Item>();
                    warps = new ArrayList<Warp>();
                    doors = new ArrayList<Door>();
                    cameras = new ArrayList<SecurityCamera>();
                    spawns = new ArrayList<Spawn>();
                    dialog = new Dialogue();
                    boss = null;
                    playerPath = null;
                    alertStage = false;
                    gas = false;
                    dark = false;
                    semidark = false;
                    midnight = false;
                    jam = false;
                    rain = false;
                    snow = false;
                    forceprone = false;
                }
                else if (line.startsWith(ROOM_END))
                {
                    levmaps.add(new LevelMap(game, tm, defaultGroundCover));
                    levmaps.get(currentLevelMap).initializeNPCs(npcs);
                    levmaps.get(currentLevelMap).initializeItems(items);
                    levmaps.get(currentLevelMap).initializeWarps(warps);
                    levmaps.get(currentLevelMap).initializeDoors(doors);
                    levmaps.get(currentLevelMap).initializeCameras(cameras);
                    levmaps.get(currentLevelMap).initializeSpawns(spawns);
                    levmaps.get(currentLevelMap).setDialogue(dialog);
                    levmaps.get(currentLevelMap).initializeBoss(boss);
                    levmaps.get(currentLevelMap).playerPath = playerPath;
                    if (alertStage)
                    {
                        levmaps.get(currentLevelMap).makeAlertStage();
                    }
                    
                    levmaps.get(currentLevelMap).songIndex = song;
                    //levmaps.get(currentLevelMap)

                    levmaps.get(currentLevelMap).dark = dark;
                    levmaps.get(currentLevelMap).semidark = semidark;
                    levmaps.get(currentLevelMap).gas = gas;
                    levmaps.get(currentLevelMap).midnight = midnight;
                    levmaps.get(currentLevelMap).jam = jam;

                    levmaps.get(currentLevelMap).rain = rain;
                    levmaps.get(currentLevelMap).snow = snow;
                    levmaps.get(currentLevelMap).forceprone = forceprone;

                    //level.setLevelMap(levmaps.get(currentLevelMap), currentLevelMap);
                    level.addLevelMap(levmaps.get(currentLevelMap));
                    
                    currentLevelMap++;

                }
                else if (line.startsWith(HEADER_START))
                {
                    System.out.println("Reading a header");

                    if (!readStartHeader)
                    {
                        line = scanner.nextLine();
                        
                        int startX = Integer.parseInt(line.substring(7));
                        line = scanner.nextLine();
                        
                        int startY = Integer.parseInt(line.substring(7));
                        line = scanner.nextLine();


                        sections = Integer.parseInt(line.substring(9));
                        line = scanner.nextLine();

                        boolean strip = false;
                        boolean transition = false;
                        while (!line.startsWith(this.HEADER_END))
                        {
                            if (line.startsWith(STRIP_ITEMS))
                            {
                                strip = true;
                            }
                            else if (line.startsWith(TRANSITION))
                            {
                                transition = true;
                            }

                            line = scanner.nextLine();
                        }

                        level = new Level(sections, id, game);
                        level.setStartX(startX);
                        level.setStartY(startY);

                        level.stripItems = strip;
                        level.transition = transition;

                        System.out.println("Level Created " + sections + " " + id + " " + startX + " " + startY);

                        readStartHeader = true;
                    }
                    else
                    {
                        line = scanner.nextLine();
                        
                        sizeX = Integer.parseInt(line.substring(6));

                        line = scanner.nextLine();
                        
                        sizeY = Integer.parseInt(line.substring(6));
                        line = scanner.nextLine();
                        
                        defaultGroundCover = Integer.parseInt(line.substring(4));
                        line = scanner.nextLine();
                        
                        alertStage = Boolean.parseBoolean(line.substring(6));
                        line = scanner.nextLine();

                        if (line.startsWith("song"))
                        {
                            song = Integer.parseInt(line.substring(5));
                            line = scanner.nextLine();
                        }

                        //Weather effects (dark, semidark, gas, haze, jam)
                        if (!line.startsWith(HEADER_END))
                        {
                            String[] weatherLines = line.split("\\|");
                            String wline = weatherLines[0];

                            if (wline.startsWith("gas"))
                            {
                                gas = true;
                            }
                            if (wline.startsWith("dark"))
                            {
                                dark = true;
                            }
                            if (wline.startsWith("semidark"))
                            {
                                semidark = true;
                            }
                            if ((wline.startsWith("haze")) || (wline.startsWith("midnight")))
                            {
                                midnight = true;
                            }
                            if (wline.indexOf("jam") >= 0)
                            {
                                jam = true;
                            }

                            //Precipitation (rain and snow)
                            if (weatherLines.length > 1)
                            {
                                String precip = weatherLines[1];
                                if (precip.startsWith("rain"))
                                {
                                    rain = true;
                                    snow = false;
                                }
                                else if (precip.startsWith("snow"))
                                {
                                    snow = true;
                                    rain = false;
                                }
                                else
                                {
                                    rain = false;
                                    snow = false;
                                }
                            }

                            //Check if forceprone
                            if (weatherLines.length > 2)
                            {
                                String forceProneStr = weatherLines[2];
                                if (forceProneStr.startsWith("forceprone"))
                                {
                                    forceprone = true;
                                }
                            }

                        }
                        
                        //dialogStage = Boolean.parseBoolean(line.substring(7));

                    }
                }
                else if (line.startsWith(HEADER_END))
                {
                    System.out.println("Header End");
                }
                else if (line.startsWith(TM_START))
                {
                    System.out.println("Reading Tilemap");
                    tm = new int[sizeY][sizeX];

                    //Load the tiles
                    int x = 0;
                    int y = 0;
                    
                    while (scanner.hasNextInt())
                    {
                        //System.out.println("(" + x + ", " + y + ")");

                        if (y < sizeY)
                        {
                            tm[y][x] = scanner.nextInt();
                        }
                        
                        x++;

                        if (x >= sizeX)
                        {
                            x = 0;
                            y++;
                        }
                    }
                }
                else if (line.startsWith(TM_END))
                {

                }
                else if (line.startsWith(ITEMS_START))
                {
                    while(!line.startsWith(ITEMS_END))
                    {
                        line = scanner.nextLine();

                        if (!(line.startsWith("#") || line.startsWith(ITEMS_END)))
                        {
                            items.add(parseItem(line));
                        }
                    }
                }
                else if (line.startsWith(ITEMS_END))
                {

                }
                else if (line.startsWith(DOORS_START))
                {
                    while(!line.startsWith(DOORS_END))
                    {
                        line = scanner.nextLine();

                        if (!(line.startsWith("#") || line.startsWith(DOORS_END)))
                        {
                            doors.add(parseDoor(line));
                        }
                    }
                }
                else if (line.startsWith(DOORS_END))
                {

                }
                else if (line.startsWith(NPCS_START))
                {
                    while(!line.startsWith(NPCS_END))
                    {
                        line = scanner.nextLine();

                        if (!(line.startsWith("#") || line.startsWith(NPCS_END)))
                        {
                            npcs.add(parseNPC(line, scanner));
                        }
                    }
                }
                else if (line.startsWith(NPCS_END))
                {

                }
                else if (line.startsWith(PATH_START))
                {

                }
                else if (line.startsWith(PATH_END))
                {

                }
                else if (line.startsWith(PLAYER_PATH_START))
                {
                    playerPath = parsePlayerPath(scanner);
                }
                else if (line.startsWith(PLAYER_PATH_END))
                {

                }
                else if (line.startsWith(WARPS_START))
                {
                    while(!line.startsWith(WARPS_END))
                    {
                        line = scanner.nextLine();

                        if (!(line.startsWith("#") || line.startsWith(WARPS_END)))
                        {
                            warps.add(parseWarp(line));
                        }
                    }
                }
                else if (line.startsWith(WARPS_END))
                {

                }
                else if (line.startsWith(DLG_START))
                {
                    while(!line.startsWith(DLG_END))
                    {
                        line = scanner.nextLine();

                        if (!(line.startsWith("#") || line.startsWith(DLG_END)))
                        {
                            dialog.add(line);
                        }
                    }
                }
                else if (line.startsWith(DLG_END))
                {

                }
                else if (line.startsWith(BOSS_START))
                {
                    line = scanner.nextLine();
                    boss = parseBoss(line);

                    Dialogue pre = new Dialogue();
                    Dialogue post = new Dialogue();

                    line = scanner.nextLine();
                    boss.name = line;
                    line = scanner.nextLine();

                    if (line.startsWith(PRE_START))
                    {
                        line = scanner.nextLine();

                        while (!line.startsWith(PRE_END))
                        {
                            pre.add(line);
                            line = scanner.nextLine();
                        }
                    }

                    line = scanner.nextLine();

                    if (line.startsWith(POST_START))
                    {
                        line = scanner.nextLine();

                        while (!line.startsWith(POST_END))
                        {
                            post.add(line);
                            line = scanner.nextLine();
                        }
                    }

                    boss.setPreDialog(pre);
                    boss.setPostDialog(post);
                }
                else if (line.startsWith(BOSS_END))
                {

                }
                else if (line.startsWith(CAMERAS_START))
                {
                    while(!line.startsWith(CAMERAS_END))
                    {
                        line = scanner.nextLine();
                        System.out.println(line);

                        if (!(line.startsWith("#") || line.startsWith(CAMERAS_END)))
                        {
                            line = scanner.nextLine();
                            Direction cd = Direction.DOWN;
                            int x = 0;
                            int y = 0;
                            Waypoint start;
                            Waypoint end;
                            int sX = 0;
                            int sY = 0;
                            int eX = 0;
                            int eY = 0;
                            boolean fixed = false;
                            int cid = 197;

                            //line = scanner.nextLine();

                            if (line.equals(Direction.UP.toString()))
                            {
                                cd = Direction.UP;
                                cid = 197;
                            }
                            if (line.equals(Direction.DOWN.toString()))
                            {
                                cd = Direction.DOWN;
                                cid = 198;
                            }
                            if (line.equals(Direction.LEFT.toString()))
                            {
                                cd = Direction.LEFT;
                                cid = 199;
                            }
                            if (line.equals(Direction.RIGHT.toString()))
                            {
                                cd = Direction.RIGHT;
                                cid = 200;
                            }

                            line = scanner.nextLine();
                            x = Integer.parseInt(line);
                            line = scanner.nextLine();
                            y = Integer.parseInt(line);

                            line = scanner.nextLine();
                            int index = line.indexOf(' ');
                            sX = Integer.parseInt(line.substring(0, index));
                            sY = Integer.parseInt(line.substring(index + 1));

                            line = scanner.nextLine();
                            index = line.indexOf(' ');
                            eX = Integer.parseInt(line.substring(0, index));
                            eY = Integer.parseInt(line.substring(index + 1));

                            line = scanner.nextLine();
                            fixed = Boolean.parseBoolean(line);

                            start = new Waypoint(sX, sY, cd, WaypointBehavior.CONTINUE);
                            end = new Waypoint(eX, eY, cd, WaypointBehavior.CONTINUE);

                            cameras.add(new SecurityCamera(cid, x*40, y*40, cd, start, end, fixed));
                            line = scanner.nextLine();
                            
                        }
                    }
                }
                else if (line.startsWith(CAMERAS_END))
                {

                }
                else if (line.startsWith(SPAWNS_START))
                {
                    while(!line.startsWith(SPAWNS_END))
                    {
                        line = scanner.nextLine();

                        if (!(line.startsWith("#") || line.startsWith(SPAWNS_END)))
                        {
                            spawns.add(parseSpawn(line));
                        }
                    }
                }
                else if (line.startsWith(SPAWNS_END))
                {

                }
                else if (line.startsWith(OBJECTIVES_START))
                {
                    line = scanner.nextLine();
                    
                    while (!line.startsWith(OBJECTIVES_END))
                    {
                        Objective obj;
                        
                        System.out.println("parsing " + line);
                        int index = line.indexOf(" ");
                        int objectiveid = Integer.parseInt(line.substring(0, index));
                        String l2 = line.substring(index+1);
                        index = l2.indexOf(" ");
                        int mapindex = Integer.parseInt(l2.substring(0, index));
                        String l3 = l2.substring(index+1);
                        index = l3.indexOf(" ");
                        int objectiveX = Integer.parseInt(l3.substring(0, index));
                        int objectiveY = Integer.parseInt(l3.substring(index+1));
                        
                        line = scanner.nextLine();
                        String objectiveDescription = line;

                        obj = new Objective(objectiveid, objectiveX, objectiveY, mapindex, objectiveDescription);

                        line = scanner.nextLine();
                        line = scanner.nextLine();

                        Dialogue d = new Dialogue();

                        while (!line.startsWith(this.DLG_END))
                        {
                            d.add(line);
                            line = scanner.nextLine();
                        }

                        obj.dialog = d;

                        level.addObjective(obj);

                        line = scanner.nextLine();
                        
                    }
                }
            }

        }
//        catch(Exception e)
//        {
//            System.err.println("Error: Could not open " + filePath);
//        }

        scanner.close();

        level.initializeObjectives();
        return level;
    }

    private Warp parseWarp(String w)
    {
        System.out.println(w);
        
        int index = w.indexOf(' ');
        int x = Integer.parseInt(w.substring(0, index));
        w = w.substring(index + 1);
        //System.out.println(w);

        index = w.indexOf(' ');
        int y = Integer.parseInt(w.substring(0, index));
        w = w.substring(index + 1);
        //System.out.println(w);

        index = w.indexOf(' ');
        boolean nextLv = Boolean.parseBoolean(w.substring(0, index));
        w = w.substring(index + 1);
        //System.out.println(w);

        index = w.indexOf(' ');
        int lvIndex = Integer.parseInt(w.substring(0, index));
        w = w.substring(index + 1);
        //System.out.println(w);

        index = w.indexOf(' ');
        int pX = Integer.parseInt(w.substring(0, index));
        w = w.substring(index + 1);
        //System.out.println(w);

        index = w.indexOf(' ');
        int pY = Integer.parseInt(w.substring(0, index));
        w = w.substring(index + 1);
        //System.out.println(w);
        
        int secLv = Integer.parseInt(w.substring(0));

        return new Warp(0, x*40, y*40, lvIndex, pX, pY, nextLv, secLv);
    }

    private Item parseItem(String it)
    {
        ItemType type = ItemType.MEDKIT;
        short id = 74;
        System.out.println(it);

        String[] splits = it.split(" ");
        String iType = splits[0];

        int x = Integer.parseInt(splits[1]);
        int y = Integer.parseInt(splits[2]);
        x = x * 40;
        y = y * 40;

        int rank = 1;
        //boolean isWeapon = false;

        if (splits.length > 3)
        {
            rank = Integer.parseInt(splits[3]);
        }

        if (iType.equals(ItemType.PRIMARY_AMMO.toString()))
        {
            type = ItemType.PRIMARY_AMMO;
            id = 74;
        }
        if (iType.equals(ItemType.SECONDARY_AMMO.toString()))
        {
            type = ItemType.SECONDARY_AMMO;
            id = 74;
        }
        if (iType.equals(ItemType.PRIMARY_MAG.toString()))
        {
            type = ItemType.PRIMARY_MAG;
            id = 74;
        }
        if (iType.equals(ItemType.SECONDARY_MAG.toString()))
        {
            type = ItemType.SECONDARY_MAG;
            id = 74;
        }
        if (iType.equals(ItemType.ASSAULT_RIFLE.toString()))
        {
            type = ItemType.ASSAULT_RIFLE;
            id = 73;
            //isWeapon = true;
        }
        if (iType.equals(ItemType.BODY_ARMOR.toString()))
        {
            type = ItemType.BODY_ARMOR;
            id = 207;
        }
        if (iType.equals(ItemType.BOOSTER_KIT.toString()))
        {
            type = ItemType.BOOSTER_KIT;
            id = 96;
        }
        if (iType.equals(ItemType.CARDKEY_1.toString()))
        {
            type = ItemType.CARDKEY_1;
            id = 75;
        }
        if (iType.equals(ItemType.CARDKEY_2.toString()))
        {
            type = ItemType.CARDKEY_2;
            id = 76;
        }
        if (iType.equals(ItemType.CARDKEY_3.toString()))
        {
            type = ItemType.CARDKEY_3;
            id = 77;
        }
        if (iType.equals(ItemType.CARDKEY_4.toString()))
        {
            type = ItemType.CARDKEY_4;
            id = 201;
        }
        if (iType.equals(ItemType.CARDKEY_5.toString()))
        {
            type = ItemType.CARDKEY_5;
            id = 202;
        }
        if (iType.equals(ItemType.GASMASK.toString()))
        {
            type = ItemType.GASMASK;
            id = 204;
        }
        if (iType.equals(ItemType.GRENADE.toString()))
        {
            type = ItemType.GRENADE;
            id = 191;
            //isWeapon = true;
        }
        if (iType.equals(ItemType.C4.toString()))
        {
            type = ItemType.C4;
            id = 368;
            //isWeapon = true;
        }
        if (iType.equals(ItemType.MEDKIT.toString()))
        {
            type = ItemType.MEDKIT;
            id = 70;
        }
        if (iType.equals(ItemType.NVG.toString()))
        {
            type = ItemType.NVG;
            id = 203;
        }
        if (iType.equals(ItemType.PISTOL.toString()))
        {
            type = ItemType.PISTOL;
            id = 71;
            //isWeapon = true;
        }
        if (iType.equals(ItemType.SHOTGUN.toString()))
        {
            type = ItemType.SHOTGUN;
            id = 206;
            //isWeapon = true;
        }
        if (iType.equals(ItemType.PRIMARY_SILENCER.toString()))
        {
            type = ItemType.PRIMARY_SILENCER;
            id = 72;
        }
        if (iType.equals(ItemType.SECONDARY_SILENCER.toString()))
        {
            type = ItemType.SECONDARY_SILENCER;
            id = 72;
        }
        if (iType.equals(ItemType.SMG.toString()))
        {
            type = ItemType.SMG;
            id = 205;
            //isWeapon = true;
        }
        if (iType.equals(ItemType.TRANQ_PISTOL.toString()))
        {
            type = ItemType.TRANQ_PISTOL;
            id = 250;
            //isWeapon = true;
        }
        if (iType.equals("WRISTWATCH"))
        {
            type = ItemType.TRANQ_PISTOL;
            id = 250;
            //isWeapon = true;
        }
        if (iType.equals("SMG_SILENCER"))
        {
            type = ItemType.PRIMARY_SILENCER;
            id = 72;
        }
        if (iType.equals("SILENCER"))
        {
            type = ItemType.SECONDARY_SILENCER;
            id = 72;
        }
        if (iType.equals(ItemType.LANDMINE.toString()))
        {
            type = ItemType.LANDMINE;
            id = 293;
        }
        if (iType.equals(ItemType.LASER_HORIZONTAL.toString()))
        {
            type = ItemType.LASER_HORIZONTAL;
            id = 294;
        }
        if (iType.equals(ItemType.LASER_VERTICAL.toString()))
        {
            type = ItemType.LASER_VERTICAL;
            id = 295;
        }
        if (iType.equals(ItemType.MINE_DETECTOR.toString()))
        {
            type = ItemType.MINE_DETECTOR;
            id = 296;
        }

        if (iType.equals("AR_AMMO"))
        {
            type = ItemType.PRIMARY_AMMO;
            id = 74;
        }

        if (iType.equals("PISTOL_AMMO"))
        {
            type = ItemType.SECONDARY_AMMO;
            id = 74;
        }

        if (iType.equals("SHOTGUN_AMMO"))
        {
            type = ItemType.PRIMARY_AMMO;
            id = 74;
        }
        if (iType.equals("SMG_AMMO"))
        {
            type = ItemType.PRIMARY_AMMO;
            id = 74;
        }
        if (iType.equals("WW_AMMO"))
        {
            type = ItemType.SECONDARY_AMMO;
            id = 74;
        }

        //id, x, y, type
        return new Item(id, x, y, type, rank);
    }

    private Door parseDoor(String d)
    {
        int id = 57;
        int x = 0;
        int y = 0;
        int secLv = 0;
        int objLev = 0;
        boolean dark = false;

        System.out.println(d);

        int index = d.indexOf(' ');
        String r0 = d.substring(0, index);
        
        x = Integer.parseInt(r0);

        id = 57;

        String r1 = d.substring(index+1);
        index = r1.indexOf(' ');
        String r2 = r1.substring(index+1);
        
        y = Integer.parseInt(r1.substring(0, index));

        index = r2.indexOf(' ');

        secLv = Integer.parseInt(r2.substring(0, index));
        String r3 = r2.substring(index+1);
        index = r3.indexOf(" ");
        objLev = Integer.parseInt(r3.substring(0, index));
        dark = Boolean.parseBoolean(r3.substring(index+1));

        x = x * 40;
        y = y * 40;

        if (secLv > 0)
        {
            id = 58;
        }
        else if (secLv < 0)
        {
            id = 378;
            return new CrawlDoor(id, x, y);
        }

        return new Door(id, x, y, secLv, objLev, dark);
    }

    private NPC parseNPC(String n, Scanner s)
    {
        NPC npc = null;
        GuardType NPCType = GuardType.LIGHT;

        if (n.startsWith(GuardType.LIGHT.toString()))
        {
            NPCType = GuardType.LIGHT;
        }
        if (n.startsWith(GuardType.MEDIUM.toString()))
        {
            NPCType = GuardType.MEDIUM;
        }
        if (n.startsWith(GuardType.HEAVY.toString()))
        {
            NPCType = GuardType.HEAVY;
        }
        if (n.startsWith(GuardType.SCIENTIST1.toString()))
        {
            NPCType = GuardType.SCIENTIST1;
        }
        if (n.startsWith(GuardType.SCIENTIST2.toString()))
        {
            NPCType = GuardType.SCIENTIST2;
        }

        //New to IT2 v2.05
        if (n.startsWith(GuardType.FEMALE_ALLY_PRISONER.toString()))
        {
            NPCType = GuardType.FEMALE_ALLY_PRISONER;
        }
        else if (n.startsWith(GuardType.FEMALE_ALLY.toString()))
        {
            NPCType = GuardType.FEMALE_ALLY;
        }

        if (n.startsWith(GuardType.WORM.toString()))
        {
            NPCType = GuardType.WORM;
        }
        if (n.startsWith(GuardType.LARVA.toString()))
        {
            NPCType = GuardType.LARVA;
        }
        if (n.startsWith(GuardType.ALIEN.toString()))
        {
            NPCType = GuardType.ALIEN;
        }
        
        //New to IT3
        if (n.startsWith(GuardType.WOMAN1.toString()))
        {
            NPCType = GuardType.WOMAN1;
        }
        if (n.startsWith(GuardType.WOMAN2.toString()))
        {
            NPCType = GuardType.WOMAN2;
        }
        if (n.startsWith(GuardType.WOMAN3.toString()))
        {
            NPCType = GuardType.WOMAN3;
        }
        if (n.startsWith(GuardType.ISLAND_GUY.toString()))
        {
            NPCType = GuardType.ISLAND_GUY;
        }
        if (n.startsWith(GuardType.CHIEF.toString()))
        {
            NPCType = GuardType.CHIEF;
        }
        if (n.startsWith(GuardType.SPECIAL_ENEMY.toString()))
        {
            NPCType = GuardType.SPECIAL_ENEMY;
        }
        else if (n.startsWith(GuardType.SPECIAL.toString()))
        {
            NPCType = GuardType.SPECIAL;
        }

        Path p = parsePath(s);

        if (NPCType == GuardType.LIGHT)
        {
            npc = new LightGuard(0, 0, Direction.DOWN, NPCStatus.PATROL, p);
        }
        if (NPCType == GuardType.MEDIUM)
        {
            npc = new MediumGuard(0, 0, Direction.DOWN, NPCStatus.PATROL, p);
        }
        if (NPCType == GuardType.HEAVY)
        {
            npc = new HeavyGuard(0, 0, Direction.DOWN, NPCStatus.PATROL, p);
        }
        if (NPCType == GuardType.SCIENTIST1)
        {
            npc = new Scientist1(0, 0, Direction.DOWN, NPCStatus.PATROL, p);
        }
        if (NPCType == GuardType.SCIENTIST2)
        {
            npc = new Scientist2(0, 0, Direction.DOWN, NPCStatus.PATROL, p);
        }
        if (NPCType == GuardType.FEMALE_ALLY)
        {
            npc = new FemaleAlly(0, 0, Direction.DOWN, NPCStatus.PATROL, p);
        }
        if (NPCType == GuardType.FEMALE_ALLY_PRISONER)
        {
            npc = new FemaleAllyPrisoner(0, 0, Direction.DOWN, NPCStatus.PATROL, p);
        }
        if (NPCType == GuardType.WORM)
        {
            npc = new Worm(0, 0, Direction.DOWN, NPCStatus.PATROL, p);
        }
        if (NPCType == GuardType.LARVA)
        {
            npc = new Larva(0, 0, Direction.DOWN, NPCStatus.PATROL, p);
        }
        if (NPCType == GuardType.ALIEN)
        {
            npc = new Alien(0, 0, Direction.DOWN, NPCStatus.PATROL, p);
        }

        //New to IT3
        if (NPCType == GuardType.WOMAN1)
        {
            npc = new Woman1(0, 0, Direction.DOWN, NPCStatus.PATROL, p);
        }
        if (NPCType == GuardType.WOMAN2)
        {
            npc = new Woman2(0, 0, Direction.DOWN, NPCStatus.PATROL, p);
        }
        if (NPCType == GuardType.WOMAN3)
        {
            npc = new Woman3(0, 0, Direction.DOWN, NPCStatus.PATROL, p);
        }
        if (NPCType == GuardType.ISLAND_GUY)
        {
            npc = new IslandGuy(0, 0, Direction.DOWN, NPCStatus.PATROL, p);
        }
        if (NPCType == GuardType.CHIEF)
        {
            npc = new Chief(0, 0, Direction.DOWN, NPCStatus.PATROL, p);
        }
        if (NPCType == GuardType.SPECIAL)
        {
            npc = new SpecialGuard(0, 0, Direction.DOWN, NPCStatus.PATROL, p);
        }
        if (NPCType == GuardType.SPECIAL_ENEMY)
        {
            npc = new SpecialGuardEnemy(0, 0, Direction.DOWN, NPCStatus.PATROL, p);
        }

        if (n.endsWith(ARMORED))
        {
            npc.bodyArmor = true;
        }

        return npc;
    }

    private Path parsePlayerPath(Scanner s)
    {
        Path p = new Path();
        ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();
        //String line = s.nextLine();
        String line = "";

        while(!line.startsWith(PLAYER_PATH_END))
        {
            line = s.nextLine();

            if (!(line.startsWith("#") || line.startsWith(PLAYER_PATH_END)))
            {
                waypoints.add(parseWaypoint(line));
            }
        }

        p.addWaypoints(waypoints);

        if (p.getAllWaypoints().size() < 1)
        {
            p = null;
        }

        return p;
    }

    private Path parsePath(Scanner s)
    {
        Path p = new Path();
        ArrayList<Waypoint> waypoints = new ArrayList<Waypoint>();
        String line = s.nextLine();

        while(!line.startsWith(PATH_END))
        {
            line = s.nextLine();

            if (!(line.startsWith("#") || line.startsWith(PATH_END)))
            {
                waypoints.add(parseWaypoint(line));
            }
            
        }

        p.addWaypoints(waypoints);

        return p;
    }

    private Waypoint parseWaypoint(String w)
    {
        int x;
        int y;
        Direction d = Direction.DOWN;
        WaypointBehavior wb = WaypointBehavior.STOP;

        int index = w.indexOf(' ');
        x = Integer.parseInt(w.substring(0, index));
        String r1 = w.substring(index+1);
        index = r1.indexOf(' ');
        y = Integer.parseInt(r1.substring(0, index));
        String r2 = r1.substring(index+1);
        index = r2.indexOf(' ');
        String dir = r2.substring(0, index);
        String wayb = r2.substring(index+1);

        if (dir.equals(Direction.UP.toString()))
        {
            d = Direction.UP;
        }
        if (dir.equals(Direction.DOWN.toString()))
        {
            d = Direction.DOWN;
        }
        if (dir.equals(Direction.LEFT.toString()))
        {
            d = Direction.LEFT;
        }
        if (dir.equals(Direction.RIGHT.toString()))
        {
            d = Direction.RIGHT;
        }

        if (wayb.equals(WaypointBehavior.CONTINUE.toString()))
        {
            wb = WaypointBehavior.CONTINUE;
        }
        if (wayb.equals(WaypointBehavior.LONG_WAIT_AND_CONTINUE.toString()))
        {
            wb = WaypointBehavior.LONG_WAIT_AND_CONTINUE;
        }
        if (wayb.equals(WaypointBehavior.SLEEP.toString()))
        {
            wb = WaypointBehavior.SLEEP;
        }
        if (wayb.equals(WaypointBehavior.STOP.toString()))
        {
            wb = WaypointBehavior.STOP;
        }
        if (wayb.equals(WaypointBehavior.WAIT_AND_CONTINUE.toString()))
        {
            wb = WaypointBehavior.WAIT_AND_CONTINUE;
        }
        if (wayb.equals(WaypointBehavior.FOLLOW_PLAYER.toString()))
        {
            wb = WaypointBehavior.FOLLOW_PLAYER;
        }

        System.out.println("Waypoint: " + x + ", " + y + ", " + d.toString() + ", " + wb.toString());

        return new Waypoint(x, y, d, wb);
    }

    private Spawn parseSpawn(String s)
    {
        int x = 1;
        int y = 1;
        GuardType gt = GuardType.LIGHT;
        int num = 0;
        boolean armored = false;

        System.out.println(s);

        int index = s.indexOf(' ');
        x = Integer.parseInt(s.substring(0, index));
        String s1 = s.substring(index+1);
        index = s1.indexOf(' ');
        y = Integer.parseInt(s1.substring(0, index));
        String s2 = s1.substring(index+1);
        index = s2.indexOf(' ');

        String gts = s2.substring(0, index);
        String ns = s2.substring(index+1);

        if (gts.startsWith(GuardType.LIGHT.toString()))
        {
            gt = GuardType.LIGHT;
        }
        if (gts.startsWith(GuardType.MEDIUM.toString()))
        {
            gt = GuardType.MEDIUM;
        }
        if (gts.startsWith(GuardType.HEAVY.toString()))
        {
            gt = GuardType.HEAVY;
        }
        if (gts.startsWith(GuardType.SCIENTIST1.toString()))
        {
            gt = GuardType.SCIENTIST1;
        }
        if (gts.startsWith(GuardType.SCIENTIST2.toString()))
        {
            gt = GuardType.SCIENTIST2;
        }
        if (gts.startsWith(GuardType.WORM.toString()))
        {
            gt = GuardType.WORM;
        }
        if (gts.startsWith(GuardType.LARVA.toString()))
        {
            gt = GuardType.LARVA;
        }
        if (gts.startsWith(GuardType.ALIEN.toString()))
        {
            gt = GuardType.ALIEN;
        }
        if (gts.startsWith(GuardType.SPECIAL_ENEMY.toString()))
        {
            gt = GuardType.SPECIAL_ENEMY;
        }

        if (gts.endsWith(ARMORED))
        {
            armored = true;
        }

        num = Integer.parseInt(ns);

        return new Spawn(x, y, gt, num, armored);
    }

    private Boss parseBoss(String s)
    {
        int id = 119;
        int x = 1;
        int y = 1;
        GuardType gt = GuardType.BOSS1;
        int health = 80;
        int damage = 15;
        int speed = 15;
        int viewDist = 320;
        boolean bodyArmor = false;

        System.out.println(s);
        
        int index = s.indexOf(" ");
        String bt = s.substring(0, index);
        String s1 = s.substring(index + 1);
        index = s1.indexOf(" ");
        String xs = s1.substring(0, index);
        String s2 = s1.substring(index + 1);
        index = s2.indexOf(" ");
        String ys = s2.substring(0, index);
        String s3 = s2.substring(index + 1);
        index = s3.indexOf(" ");
        String hs = s3.substring(0, index);
        String s4 = s3.substring(index + 1);
        index = s4.indexOf(" ");
        String ds = s4.substring(0, index);
        String s5 = s4.substring(index + 1);
        index = s5.indexOf(" ");
        String ss = s5.substring(0, index);
        String s6 = s5.substring(index + 1);
        index = s6.indexOf(" ");

        String vs = s6.substring(0, index);
        String bs = s6.substring(index + 1);

        if (bt.equalsIgnoreCase(GuardType.BOSS1.toString()))
        {
            gt = GuardType.BOSS1;
            id = 119;
        }
        if (bt.equalsIgnoreCase(GuardType.BOSS2.toString()))
        {
            gt = GuardType.BOSS2;
            id = 127;
        }
        if (bt.equalsIgnoreCase(GuardType.BOSS3.toString()))
        {
            gt = GuardType.BOSS3;
            id = 135;
        }
        if (bt.equalsIgnoreCase(GuardType.BOSS4.toString()))
        {
            gt = GuardType.BOSS4;
            id = 226;
        }
        if (bt.equalsIgnoreCase(GuardType.BOSS5.toString()))
        {
            gt = GuardType.BOSS5;
            id = 234;
        }
        if (bt.equalsIgnoreCase(GuardType.BOSS6.toString()))
        {
            gt = GuardType.BOSS6;
            id = 242;
        }
        if (bt.equalsIgnoreCase(GuardType.CHIEF.toString()))
        {
            gt = GuardType.CHIEF;
            id = 334;
        }
        if (bt.equalsIgnoreCase(GuardType.SPECIAL.toString()) || (bt.equalsIgnoreCase(GuardType.SPECIAL_ENEMY.toString())))
        {
            gt = GuardType.SPECIAL;
            id = 350;
        }
        if (bt.equalsIgnoreCase(GuardType.ISLAND_GUY.toString()))
        {
            gt = GuardType.ISLAND_GUY;
            id = 342;
        }
        if (bt.equalsIgnoreCase(GuardType.FEMALE_ALLY.toString()))
        {
            gt = GuardType.FEMALE_ALLY;
            id = 261;
        }


        x = Integer.parseInt(xs);
        y = Integer.parseInt(ys);
        health = Integer.parseInt(hs);
        damage = Integer.parseInt(ds);
        speed = Integer.parseInt(ss);
        viewDist = Integer.parseInt(vs);
        bodyArmor = Boolean.parseBoolean(bs);

        return new Boss(id, (x * 40), (y * 40), gt, health, damage, speed, viewDist, bodyArmor);
    }
}
