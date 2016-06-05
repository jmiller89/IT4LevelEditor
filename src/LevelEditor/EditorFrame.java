/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EditorFrame.java
 *
 * Created on Apr 20, 2012, 8:06:02 PM
 */

package LevelEditor;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Jim
 */
public class EditorFrame extends javax.swing.JFrame
{
    private Editor editor;
    private MapPanel mPanel;
    private EditorPanel ePanel;
    private DGCPanel dgcPanel;
    private RoomEditPanel rePanel;

    /** Creates new form EditorFrame */
    public EditorFrame(Editor ed)
    {
        initComponents();

        editor = ed;
        this.setLayout(null);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        JMenuItem newAction = new JMenuItem("New");
        JMenuItem openAction = new JMenuItem("Open");
        JMenuItem saveAction = new JMenuItem("Save");
        JMenuItem exitAction = new JMenuItem("Exit");

        fileMenu.add(newAction);
        fileMenu.add(openAction);
        fileMenu.add(saveAction);
        fileMenu.add(exitAction);

        JMenuItem aboutAction = new JMenuItem("About");
        helpMenu.add(aboutAction);

        aboutAction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                showVersionInfo();
            }
        });

        newAction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                newMap();
            }
        });

        openAction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                loadMap();
            }
        });

        saveAction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                saveMapAs();
            }
        });

        exitAction.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                exitEditor();
            }
        });

        this.setSize(1000, 900);
        this.setVisible(true);
    }

    private void showVersionInfo()
    {
        JOptionPane.showMessageDialog(this, "Intruder's Thunder 4: The Endling's Artifice Level Editor\nProgrammed by jmiller89\n\nSoftware version 1.6.37", "About IT4 Level Editor", JOptionPane.INFORMATION_MESSAGE);
    }

    public void newMap()
    {
        editor.newLevel();
        rePanel.update();
    }

    private void loadMap()
    {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new IT2FileFilter());
        int retval = fc.showDialog(this, "Open Level");

        if (retval == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();

            if (file.getName().toLowerCase().endsWith(".it4"))
            {
                System.out.println(file.getName());
                System.out.println(file.getPath());
                //newGame(file.getPath(), false, false);
                editor.loadMap(file.getPath());
            }

        }

        rePanel.update();
    }

    private void saveMapAs()
    {
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new IT2FileFilter());
        int retval = fc.showDialog(this, "Save");

        if (retval == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();

            if (file.getName().toLowerCase().endsWith(".it4"))
            {
                System.out.println(file.getName());
                System.out.println(file.getPath());
                //newGame(file.getPath(), false, false);
                editor.saveMap(file.getPath());
            }
            else
            {
                file = new File(fc.getSelectedFile().getPath() + ".it4");
                System.out.println(file.getName());
                System.out.println(file.getPath());
                editor.saveMap(file.getPath());
            }

        }

        rePanel.update();
    }

    public void addMapPanel()
    {
        mPanel = new MapPanel(editor);
        mPanel.setAutoscrolls(true);
        
        this.add(mPanel);
        mPanel.setBounds(0, 0, 600, 600);
        
    }

    public void addEditorPanel()
    {
        ePanel = new EditorPanel(editor, mPanel);
        this.add(ePanel);
        ePanel.setBounds(601, 0, 400, 600);
    }

    public void addDGCPanel()
    {
        dgcPanel = new DGCPanel(editor);
        this.add(dgcPanel);
        dgcPanel.setBounds(10, 611, 200, 100);
    }

    public void addREPanel()
    {
        rePanel = new RoomEditPanel(editor);
        this.add(rePanel);
        rePanel.setBounds(240, 611, 500, 200);
    }

    public void updateMapPanel(boolean changeMap)
    {
        if (changeMap)
        {
            mPanel.originX = 0;
            mPanel.originY = 0;
        }

        mPanel.setSpriteMap();
        mPanel.setDefaultGroundCover();
    }

    public void updateEditPanel()
    {
        if (ePanel != null)
        {
            ePanel.updateGUI();
        }
    }

    public void updateREPanel()
    {
        rePanel.update();
    }

    private void exitEditor()
    {
        this.dispose();
        System.exit(0);
    }

    public void changeMode(Mode m)
    {
        ePanel.changeMode(m);
    }

    public void enableNPCEditing()
    {
        ePanel.enableNPCEditing();
    }

    public void disableNPCEditing()
    {
        ePanel.disableNPCEditing();
    }

    //EDIT THINGS
    public void editNPC(NPC n)
    {
        ePanel.editNPC(n);
    }

    public void editBoss(Boss b)
    {
        ePanel.editBoss(b);
    }

    public void editDoor(Door d)
    {
        ePanel.editDoor(d);
    }

    public void editWarp(Warp w)
    {
        ePanel.editWarp(w);
    }

    public void editObjective(Objective o)
    {
        ePanel.editObjective(o);
    }

    public void editSpawn(Spawn s)
    {
        ePanel.editSpawn(s);
    }

    public void editCamera(SecurityCamera c)
    {
        ePanel.editCamera(c);
    }


    public void clearNPCPathText()
    {
        ePanel.clearNPCPathText();
    }

    public void updateDialog()
    {
        ePanel.updateDialog();
    }

    public void updatePlayerPath()
    {
        ePanel.updatePlayerPath();
    }

    public void updateLS()
    {
        ePanel.updateLS();
    }

    public void updateRS()
    {
        ePanel.updateRS();
    }

    public void updateWarpRooms()
    {
        ePanel.updateWarpRooms();
    }

    public void updateDGC(int index)
    {
        dgcPanel.update(index);
    }

    public void setEdit(int x, int y)
    {
        mPanel.editX = x;
        mPanel.editY = y;
        
        if ((x >= 0) && (y >= 0))       
        {
            mPanel.edit = true;
        }
        else
        {
            mPanel.edit = false;
        }
    }

    public void clear()
    {
        ePanel.clear();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IT4 Level Editor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName("IT4 Level Editor");

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
