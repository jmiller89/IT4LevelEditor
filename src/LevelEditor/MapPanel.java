/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MapPanel.java
 *
 * Created on Apr 20, 2012, 8:24:23 PM
 */

package LevelEditor;

/**
 *
 * @author Jim
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.*;
import javax.swing.Timer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MapPanel extends javax.swing.JPanel implements MouseListener, MouseMotionListener, ActionListener, KeyListener
{
    private Sprite[][] spriteMap;
    private Sprite defaultGroundCover;
    private Editor editor;
    private Timer timer;
    private boolean paused = false;

    public int mouseX = 0;
    public int mouseY = 0;

    public int editX = 0;
    public int editY = 0;
    public boolean edit = false;

    private int lastX = mouseX;
    private int lastY = mouseY;

    public int originX = 0;
    public int originY = 0;
    
    private boolean selection = false;
    private boolean finishedSelection = false;
    private int captureX = 0;
    private int captureY = 0;
    private int copyXStart = 0;
    private int copyYStart = 0;
    private int copyXFinish = 0;
    private int copyYFinish = 0;

    /** Creates new form MapPanel */
    public MapPanel(Editor ed)
    {
        initComponents();
        editor = ed;
        
        this.setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        requestFocus();

        spriteMap = editor.getSpriteMap();
        defaultGroundCover = editor.getSpriteLoader().getSprite(editor.getDefaultGroundCover());

        this.setSize(800, 600);

        setVisible(true);

        timer = new Timer(20, this);
        timer.setInitialDelay(0);
        timer.setCoalesce(true);
        startRendering();
    }

//    public void updateSpriteMap()
//    {
//        spriteMap = editor.getSpriteMap();
//    }
//
//    public void updateDGCSprite()
//    {
//        defaultGroundCover = editor.getSpriteLoader().getSprite(editor.getDefaultGroundCover());
//    }

    public int getMaxViewX()
    {
        return spriteMap[0].length;
    }

    public int getMaxViewY()
    {
        return spriteMap.length;
    }

    private void startRendering()
    {
        if (paused == true)
        {
            //Paused
        }
        else
        {
            timer.start();
        }
    }

    public void stopRendering()
    {
        timer.stop();
    }

    public void setSpriteMap()
    {
        spriteMap = editor.getSpriteMap();
    }

    public void setDefaultGroundCover()
    {
        defaultGroundCover = editor.getSpriteLoader().getSprite(editor.getDefaultGroundCover());
    }

    //The following four methods are used to cull out of view tiles
//    private int getMinX(int x)
//    {
//        if ((x - 12) < 0)
//        {
//            return 0;
//        }
//        else
//        {
//            return (x - 12);
//        }
//    }
//
//    private int getMinY(int y)
//    {
//        if ((y - 9) < 0)
//        {
//            return 0;
//        }
//        else
//        {
//            return (y - 9);
//        }
//    }

    private int getMaxX(int x, int max)
    {
        if ((x + 15) > max)
        {
            return max;
        }
        else
        {
            return (x + 15);
        }
    }

    private int getMaxY(int y, int max)
    {
        if ((y  + 15) > max)
        {
            return max;
        }
        else
        {
            return (y + 15);
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.red);

        spriteMap = editor.getSpriteMap();
        defaultGroundCover = editor.getSpriteLoader().getSprite(editor.getDefaultGroundCover());

        int maxX = getMaxX(originX, spriteMap[0].length);
        int maxY = getMaxY(originY, spriteMap.length);

        for(int j = originY; j < maxY; j++)
        {
            for(int i = originX; i < maxX; i++)
            {
                defaultGroundCover.draw(g, (i - originX) * 40, (j - originY) * 40);
                spriteMap[j][i].draw(g, (i - originX) * 40, (j - originY) * 40);
            }
        }

        for(int i = 0; i < editor.currRoom.getNPCs().size(); i++)
        {
            int x = (editor.getNPCs().get(i).getPath().getStartingWaypoint().getXPos() - originX) * 40;
            int y = (editor.getNPCs().get(i).getPath().getStartingWaypoint().getYPos() - originY) * 40;
            editor.getSpriteLoader().getSprite(editor.getNPCs().get(i).getID() + 1).draw(g, x, y);
        }

        if (editor.currRoom.isBossFight())
        {
            editor.currRoom.getBoss().getSprite().draw(g, (editor.currRoom.getBoss().getTileX() - originX) * 40, ((editor.currRoom.getBoss().getTileY() - originY) * 40));
            //g.setColor(Color.red);
            g.drawString("Boss", (editor.currRoom.getBoss().getTileX() - originX) * 40, ((editor.currRoom.getBoss().getTileY() - originY) * 40) + 40);
        }

        for(int i = 0; i < editor.currRoom.getItems().size(); i++)
        {
            editor.getSpriteLoader().getSprite(editor.currRoom.getItems().get(i).getID()).draw(g, (editor.currRoom.getItems().get(i).getTileX() - originX) * 40, ((editor.currRoom.getItems().get(i).getTileY() - originY) * 40));
            
            g.drawString(editor.currRoom.getItems().get(i).toString(), (editor.currRoom.getItems().get(i).getTileX() - originX) * 40, ((editor.currRoom.getItems().get(i).getTileY() - originY) * 40) + 40);
        }

        for(int i = 0; i < editor.currRoom.getDoors().size(); i++)
        {
            int dx = (editor.currRoom.getDoors().get(i).getTileX() - originX) * 40;
            int dy = (editor.currRoom.getDoors().get(i).getTileY() - originY) * 40;

            if (editor.currRoom.getDoors().get(i).getID() == 58)
            {
                editor.getSpriteLoader().getSprite(59).draw(g, dx, dy);
            }
            editor.getSpriteLoader().getSprite(editor.currRoom.getDoors().get(i).getID()).draw(g, dx, dy);
        }

        for(int i = 0; i < editor.currRoom.getWarps().size(); i++)
        {
            int wx = (editor.currRoom.getWarps().get(i).getTileX() - originX) * 40;
            int wy = (editor.currRoom.getWarps().get(i).getTileY() - originY) * 40;
            int wt = editor.currRoom.getWarps().get(i).getLevelX();
            int wtx = editor.currRoom.getWarps().get(i).getPlayerWarpX() / 40;
            int wty = editor.currRoom.getWarps().get(i).getPlayerWarpY() / 40;

            g.drawString("Warp", wx, wy);
            g.drawString("R: " + wt, wx, wy + 12);
            g.drawString("X: " + wtx, wx, wy + 24);
            g.drawString("Y: " + wty, wx, wy + 36);
        }

        for(int i = 0; i < editor.currRoom.getCameras().size(); i++)
        {
            int cx = (editor.currRoom.getCameras().get(i).getTileX() - originX) * 40;
            int cy = (editor.currRoom.getCameras().get(i).getTileY() - originY) * 40;

            editor.currRoom.getCameras().get(i).getSprite().draw(g, cx, cy);
        }

        for(int i = 0; i < editor.currRoom.getSpawns().size(); i++)
        {
            int sx = (editor.currRoom.getSpawns().get(i).tileX - originX) * 40;
            int sy = (editor.currRoom.getSpawns().get(i).tileY - originY) * 40;

            String st = editor.currRoom.getSpawns().get(i).guardType.toString();
            if (editor.currRoom.getSpawns().get(i).armored)
            {
                st += "_ARMORED";
            }

            g.drawString("Spawn", sx, sy);
            g.drawString(st, sx, sy + 14);
            g.drawString("Num: " + editor.currRoom.getSpawns().get(i).limit, sx, sy + 28);
        }

        if (editor.currLevel.getObjectives(editor.currRoom.id) != null)
        {
            for(int i = 0; i < editor.currLevel.getObjectives(editor.currRoom.id).size(); i++)
            {
                int ox = (editor.currLevel.getObjectives(editor.currRoom.id).get(i).getTileX() - originX) * 40;
                int oy = (editor.currLevel.getObjectives(editor.currRoom.id).get(i).getTileY() - originY) * 40;

                editor.currLevel.getObjectives(editor.currRoom.id).get(i).getSprite().draw(g, ox, oy);
                g.drawString("Objective", ox, oy + 40);
            }
        }

        

        //g.setColor(Color.red);
        g.drawRect((mouseX - originX) * 40, (mouseY - originY) * 40, 40, 40);

        g.setColor(Color.yellow);

        if (edit)
        {
            g.drawRect((editX - originX) * 40, (editY - originY) * 40, 40, 40);
        }

        editor.updateEditPanel();

        g.dispose();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(600, 600));
        setPreferredSize(new java.awt.Dimension(600, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void mouseClicked(MouseEvent e)
    {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mousePressed(MouseEvent e)
    {
        //throw new UnsupportedOperationException("Not supported yet.");
        //System.out.println(e.getX() + ", " + e.getY());
        
        System.out.println(mouseX + ", " + mouseY);
        editor.selectedPayload.x = mouseX;
        editor.selectedPayload.y = mouseY;
        editor.updateRoom();
    }

    public void mouseReleased(MouseEvent e)
    {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouseEntered(MouseEvent e)
    {
        //throw new UnsupportedOperationException("Not supported yet.");
        requestFocus();
    }

    public void mouseExited(MouseEvent e)
    {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }

    public void mouseDragged(MouseEvent e)
    {
        mouseX = editor.tileCoord(e.getX()) + originX;
        mouseY = editor.tileCoord(e.getY()) + originY;

        if ((mouseX != lastX) || (mouseY != lastY))
        {
            lastX = mouseX;
            lastY = mouseY;
            System.out.println(mouseX + "," + mouseY);
            editor.selectedPayload.x = mouseX;
            editor.selectedPayload.y = mouseY;
            editor.updateRoom();
        }
    }

    public void mouseMoved(MouseEvent e)
    {
        mouseX = editor.tileCoord(e.getX()) + originX;
        mouseY = editor.tileCoord(e.getY()) + originY;

        editor.updateEditPanel();
    }

    private void panUp()
    {
        if (originY - 1 >= 0)
        {
            originY--;
            mouseY--;
            editor.updateEditPanel();
        }
    }

    private void panDown()
    {
        if ((originY + 1) < (getMaxViewY() - 14))
        {
            originY++;
            mouseY++;
            editor.updateEditPanel();
        }
    }

    private void panLeft()
    {
        if (originX - 1 >= 0)
        {
            originX--;
            mouseX--;
            editor.updateEditPanel();
        }
    }

    private void panRight()
    {
        if ((originX + 1) < (getMaxViewX() - 14))
        {
            originX++;
            mouseX++;
            editor.updateEditPanel();
        }
    }

    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void keyPressed(KeyEvent e) {
        if ((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_W))
        {
            panUp();
        }
        if((e.getKeyCode() == KeyEvent.VK_DOWN) || (e.getKeyCode() == KeyEvent.VK_S))
        {
            panDown();
        }
        if((e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_A))
        {
            panLeft();
        }
        if((e.getKeyCode() == KeyEvent.VK_RIGHT) || (e.getKeyCode() == KeyEvent.VK_D))
        {
            panRight();
        }

        if (e.getKeyCode() == KeyEvent.VK_CONTROL)
        {
            if (!finishedSelection)
            {
                if (!selection)
                {
                    captureX = mouseX;
                    captureY = mouseY;
                }
                selection = true;
            }
        }

        //Copy
        if (e.getKeyCode() == KeyEvent.VK_C)
        {
            if (selection)
            {
                copyXStart = captureX;
                copyYStart = captureY;
                copyXFinish = mouseX;
                copyYFinish = mouseY;

                editor.copyTiles(copyXStart, copyYStart, copyXFinish, copyYFinish);
                selection = false;
                finishedSelection = true;
            }
        }

        //Paste
        if (e.getKeyCode() == KeyEvent.VK_V)
        {
            if (selection)
            {
                editor.pasteTiles(mouseX, mouseY);
                selection = false;
                finishedSelection = true;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet.");
        if (e.getKeyCode() == KeyEvent.VK_CONTROL)
        {
            selection = false;
            finishedSelection = false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
