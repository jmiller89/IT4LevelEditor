/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EditorPanel.java
 *
 * Created on Apr 21, 2012, 12:31:04 PM
 */

package LevelEditor;

import java.awt.CardLayout;
/*
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
 *
 */

/**
 *
 * @author Jim
 */
public class EditorPanel extends javax.swing.JPanel
{
    private Editor editor;
    private MapPanel mPanel;
    private PanResizePanel prp;
    private ModePanel mp;
    private ContentPanel cp;

    /** Creates new form EditorPanel */
    public EditorPanel(Editor e, MapPanel m)
    {
        initComponents();
        editor = e;
        mPanel = m;

        //this.setLayout(new GridLayout(3, 1));
        this.setLayout(null);

        prp = new PanResizePanel(editor, mPanel);
        this.add(prp);

        mp = new ModePanel(mPanel, editor);
        this.add(mp);

        mp.setBounds(0, 200, 400, 100);

        cp = new ContentPanel(editor);
        this.add(cp);
        cp.setBounds(0, 300, 400, 500);

        this.setSize(400, 600);
        this.setVisible(true);
    }

    public void updateGUI()
    {
        prp.repaint();
        mp.updateMapXY();
        mp.repaint();
        cp.repaint();
    }

    public void changeMode(Mode m)
    {
        CardLayout cl = (CardLayout)(cp.getLayout());
        cl.show(cp, m.toString());
    }

    public void enableNPCEditing()
    {
        cp.enableNPCEditing();
    }

    public void disableNPCEditing()
    {
        cp.disableNPCEditing();
    }

    //Edit things:
    public void editNPC(NPC n)
    {
        cp.editNPC(n);
    }

    public void editBoss(Boss b)
    {
        cp.editBoss(b);
    }

    public void editDoor(Door d)
    {
        cp.editDoor(d);
    }

    public void editWarp(Warp w)
    {
        cp.editWarp(w);
    }

    public void editObjective(Objective o)
    {
        cp.editObjective(o);
    }

    public void editSpawn(Spawn s)
    {
        cp.editSpawn(s);
    }

    public void editCamera(SecurityCamera c)
    {
        cp.editCamera(c);
    }

    public void clearNPCPathText()
    {
        cp.clearNPCPathText();
    }

    public void clearPlayerPathText()
    {
        cp.clearPlayerPathText();
    }

    public void updateDialog()
    {
        cp.updateDialog();
    }

    public void updatePlayerPath()
    {
        cp.updatePlayerPath();
    }

    public void updateLS()
    {
        cp.updateLS();
    }

    public void updateRS()
    {
        cp.updateRS();
    }

    public void updateWarpRooms()
    {
        cp.updateWarpRooms();
    }

    public void clear()
    {
        mp.clear();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}