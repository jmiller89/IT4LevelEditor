/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DGCPanel.java
 *
 * Created on Jun 3, 2012, 10:45:12 PM
 */

package LevelEditor;

import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;

/**
 *
 * @author Jim
 */
public class DGCPanel extends javax.swing.JPanel
{
    private JLabel label;
    private JComboBox dgc;
    private Editor editor;

    /** Creates new form DGCPanel */
    public DGCPanel(Editor e)
    {
        initComponents();
        this.setLayout(null);
        this.setVisible(true);

        editor = e;

        dgc = new JComboBox(e.floorTiles);
        this.add(dgc);
        dgc.setBounds(0, 0, 200, 50);
        this.setVisible(true);

        dgc.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                updateDGC();
            }
        });

        label = new JLabel("Default Background Tile");
        this.add(label);
        label.setBounds(0, 40, 150, 50);
        
    }

    public void update(int index)
    {
        dgc.setSelectedIndex(index);
    }

    private void updateDGC()
    {
        editor.updateDGC(dgc.getSelectedIndex());
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