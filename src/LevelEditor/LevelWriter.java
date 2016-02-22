/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelEditor;

/**
 *
 * @author Jim
 */

import java.io.BufferedWriter;
import java.io.FileWriter;

public class LevelWriter
{
    Editor editor;

    public LevelWriter(Editor e)
    {
        editor = e;
    }

    public void writeLevel(String fp)
    {
        try
        {
            FileWriter fstream = new FileWriter(fp);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write("#This level was created using the IT4 Level Editor");
            out.newLine();
            out.newLine();

            out.write("[header]");
            out.newLine();
            out.write("startX " + editor.currLevel.getStartX());
            out.newLine();
            out.write("startY " + editor.currLevel.getStartY());
            out.newLine();
            out.write("sections " + editor.currLevel.getNumRooms());
            out.newLine();

            if (editor.currLevel.stripItems)
            {
                out.write("STRIP_ITEMS");
                out.newLine();
            }
            if (editor.currLevel.transition)
            {
                out.write("TRANSITION");
                out.newLine();
            }

            out.write("[/header]");
            out.newLine();
            out.newLine();

            //Begin objectives
            out.write("[objectives]");
            out.newLine();
            for(int i = 0; i < editor.currLevel.getAllObjectives().size(); i++)
            {
                Objective o = editor.currLevel.getAllObjectives().get(i);

                String ostr = "" + o.getID() + " " + o.mapIndex + " " + o.getTileX() + " " + o.getTileY();
                out.write(ostr);
                out.newLine();
                out.write(o.name);
                out.newLine();
                out.write("[dialog]");
                out.newLine();

                for(int j = 0; j < o.dialog.getContents().size(); j++)
                {
                    String ds = o.dialog.getContents().get(j);
                    out.write(ds);
                    out.newLine();
                }

                out.write("[/dialog]");
                out.newLine();
            }
            out.write("[/objectives]");
            out.newLine();
            out.newLine();
            //End objectives

            //Begin Rooms
            System.out.println("Writing rooms");
            for(int i = 0; i < editor.currLevel.getNumRooms(); i++)
            {
                out.write("[room]");
                out.newLine();
                out.newLine();

                //Begin Header
                out.write("[header]");
                out.newLine();
                out.write("sizeX " + editor.currLevel.getLevelMap(i).getTiles()[0].length);
                out.newLine();
                out.write("sizeY " + editor.currLevel.getLevelMap(i).getTiles().length);
                out.newLine();
                out.write("DGC " + editor.currLevel.getLevelMap(i).getDefaultGroundCover());
                out.newLine();
                out.write("alert " + Boolean.toString(editor.currLevel.getLevelMap(i).getAlertMode()));
                out.newLine();
                out.write("song " + editor.currLevel.getLevelMap(i).songIndex);
                out.newLine();

                String precipString = "none";
                if (editor.currLevel.getLevelMap(i).rain)
                {
                    precipString = "rain";
                }
                else if (editor.currLevel.getLevelMap(i).snow)
                {
                    precipString = "snow";
                }

                String forceproneString = "normal";
                if (editor.currLevel.getLevelMap(i).forceprone)
                {
                    forceproneString = "forceprone";
                }

                boolean suffix = false;
                if (editor.currLevel.getLevelMap(i).dark)
                {
                    out.write("dark");
                    suffix = true;
                }
                if (editor.currLevel.getLevelMap(i).semidark)
                {
                    out.write("semidark");
                    suffix = true;
                }
                if (editor.currLevel.getLevelMap(i).gas)
                {
                    out.write("gas");
                    suffix = true;
                }
                if (editor.currLevel.getLevelMap(i).midnight)
                {
                    out.write("midnight");
                    suffix = true;
                }

                if (editor.currLevel.getLevelMap(i).jam)
                {
                    if (suffix)
                    {
                        out.write("_jam");
                    }
                    else
                    {
                        out.write("jam");
                    }
                }

                out.write("|" + precipString);

                out.write("|" + forceproneString);

                if (editor.currLevel.getLevelMap(i).jam)
                {
                    //This is stupid, but we need to preserve compatibility w/ older IT3 versions
                    if (suffix)
                    {
                        out.write("_jam");
                    }
                }

                out.newLine();

                out.write("[/header]");
                out.newLine();
                out.newLine();
                //End Header

                //Begin tilemap
                System.out.println("Writing tilemap");
                out.write("[tileMap]");
                out.newLine();

                for(int j = 0; j < editor.currLevel.getLevelMap(i).getTiles().length; j++)
                {
                    for(int k = 0; k < editor.currLevel.getLevelMap(i).getTiles()[j].length; k++)
                    {
                        if (k + 1 < editor.currLevel.getLevelMap(i).getTiles()[j].length)
                        {
                            out.write(editor.currLevel.getLevelMap(i).getTile(k, j) + " ");
                        }
                        else
                        {
                            out.write("" + editor.currLevel.getLevelMap(i).getTile(k, j));
                        }
                    }
                    out.newLine();
                }

                out.write("[/tileMap]");
                out.newLine();
                out.newLine();
                //End tilemap

                System.out.println("Tilemap written");

                //Begin items
                out.write("[items]");
                out.newLine();

                for(int j = 0; j < editor.currLevel.getLevelMap(i).getItems().size(); j++)
                {
                    Item it = editor.currLevel.getLevelMap(i).getItems().get(j);
                    out.write(it.getType().toString() + " " + it.getTileX() + " " + it.getTileY() + " " + it.rank);
                    out.newLine();
                }

                out.write("[/items]");
                out.newLine();
                out.newLine();
                //End items

                //Begin doors
                out.write("[doors]");
                out.newLine();

                for(int j = 0; j < editor.currLevel.getLevelMap(i).getDoors().size(); j++)
                {
                    Door door = editor.currLevel.getLevelMap(i).getDoors().get(j);
                    out.write(door.toString());
                    out.newLine();
                }

                out.write("[/doors]");
                out.newLine();
                out.newLine();
                //End doors

                //Begin NPCs
                out.write("[npcs]");
                out.newLine();

                for(int j = 0; j < editor.currLevel.getLevelMap(i).getNPCs().size(); j++)
                {
                    NPC npc = editor.currLevel.getLevelMap(i).getNPCs().get(j);
                    out.write(npc.getType().toString());

                    if (editor.currLevel.getLevelMap(i).getNPCs().get(j).bodyArmor)
                    {
                        out.write("_ARMORED");
                    }
                    out.newLine();

                    out.write("[path]");
                    out.newLine();

                    for(int k = 0; k < npc.getPath().getAllWaypoints().size(); k++)
                    {
                        Waypoint w = npc.getPath().getAllWaypoints().get(k);
                        out.write("" + w.getXPos() + " " + w.getYPos() + " " + w.getDirection().toString() + " " + w.getBehavior().toString());
                        out.newLine();
                    }

                    out.write("[/path]");
                    out.newLine();
                }

                out.write("[/npcs]");
                out.newLine();
                out.newLine();
                //End NPCs

                //Begin spawns
                out.write("[spawns]");
                out.newLine();

                for(int j = 0; j < editor.currLevel.getLevelMap(i).getSpawns().size(); j++)
                {
                    Spawn s = editor.currLevel.getLevelMap(i).getSpawns().get(j);
                    String stype = s.guardType.toString();

                    if (s.armored)
                    {
                        stype += "_ARMORED";
                    }

                    out.write("" + s.tileX + " " + s.tileY + " " + stype + " " + s.limit);
                    out.newLine();
                }

                out.write("[/spawns]");
                out.newLine();
                out.newLine();
                //End spawns

                System.out.println("Warps");

                //Begin warps
                out.write("[warps]");
                out.newLine();

                for(int j = 0; j < editor.currLevel.getLevelMap(i).getWarps().size(); j++)
                {
                    Warp w = editor.currLevel.getLevelMap(i).getWarps().get(j);
                    out.write("" + w.getTileX() + " " + w.getTileY() + " " + w.isNextLevelWarp() + " " + w.getLevelX() + " " + w.getPlayerWarpX()/40 + " " + w.getPlayerWarpY()/40 + " 0");
                    out.newLine();
                }

                out.write("[/warps]");
                out.newLine();
                out.newLine();
                //End warps

                System.out.println("Dialog");

                //Begin dialog
                out.write("[dialog]");
                out.newLine();

                if (editor.currLevel.getLevelMap(i).getDialogue() == null)
                {
                    System.out.println("NULL DIALOG");
                }

                for(int j = 0; j < editor.currLevel.getLevelMap(i).getDialogue().getContents().size(); j++)
                {
                    String line = editor.currLevel.getLevelMap(i).getDialogue().getContents().get(j);
                    out.write(line);
                    out.newLine();
                }

                out.write("[/dialog]");
                out.newLine();
                out.newLine();
                //End dialog

                System.out.println("Cameras");

                //Begin cameras
                out.write("[cameras]");
                out.newLine();

                for(int j = 0; j < editor.currLevel.getLevelMap(i).getCameras().size(); j++)
                {
                    SecurityCamera c = editor.currLevel.getLevelMap(i).getCameras().get(j);
                    out.write("[camera]");
                    out.newLine();

                    out.write(c.getDirection().toString());
                    out.newLine();

                    out.write("" + c.getTileX());
                    out.newLine();

                    out.write("" + c.getTileY());
                    out.newLine();

                    out.write("" + c.start.getXPos() + " " + c.start.getYPos());
                    out.newLine();

                    out.write("" + c.end.getXPos() + " " + c.end.getYPos());
                    out.newLine();

                    out.write(Boolean.toString(c.fixed));
                    out.newLine();
                    
                    out.write("[/camera]");
                    out.newLine();
                }

                out.write("[/cameras]");
                out.newLine();
                out.newLine();
                //End cameras

                System.out.println("Player Path");
                //Begin player path
                out.write("[playerpath]");
                out.newLine();
                Path p = editor.currLevel.getLevelMap(i).playerPath;
                if (p != null)
                {
                    for(int t = 0; t < p.getAllWaypoints().size(); t++)
                    {
                        Waypoint w = p.getAllWaypoints().get(t);
                        out.write("" + w.getXPos() + " " + w.getYPos() + " " + w.getDirection().toString() + " " + w.getBehavior().toString());
                        out.newLine();
                    }
                }
                out.write("[/playerpath]");
                out.newLine();
                out.newLine();
                //End player path

                System.out.println("Begin Bossfight");

                //Begin Boss
                if (editor.currLevel.getLevelMap(i).isBossFight())
                {
                    out.write("[boss]");
                    out.newLine();

                    Boss b = editor.currLevel.getLevelMap(i).getBoss();
                    Dialogue pre = b.getPreDialog();
                    Dialogue post = b.getPostDialog();

                    out.write(b.getType().toString() + " " + b.getTileX() + " " + b.getTileY() + " " + b.getMaxHealth() + " " + b.getWeaponDamage() + " " + b.speed + " " + b.viewDistance + " " + b.bodyArmor);
                    out.newLine();
                    out.write(b.name);
                    out.newLine();

                    out.write("[pre]");
                    out.newLine();

                    for(int j = 0; j < pre.getContents().size(); j++)
                    {
                        out.write(pre.getContents().get(j));
                        out.newLine();
                    }

                    out.write("[/pre]");
                    out.newLine();

                    out.write("[post]");
                    out.newLine();

                    for(int j = 0; j < post.getContents().size(); j++)
                    {
                        out.write(post.getContents().get(j));
                        out.newLine();
                    }

                    out.write("[/post]");
                    out.newLine();

                    out.write("[/boss]");
                    out.newLine();
                    out.newLine();
                }
                //End Boss


                out.write("[/room]");
                out.newLine();
                out.newLine();
            }
            //End Rooms

            out.close();
        }
        catch(Exception e)
        {
            System.out.println("An error occured when writing to the file!");
        }
    }
}
