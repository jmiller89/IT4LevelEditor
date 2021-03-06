/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RoomSettingsEditor.java
 *
 * Created on Jun 30, 2012, 8:46:21 PM
 */

package LevelEditor;

/**
 *
 * @author Jim
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

public class RoomSettingsEditor extends javax.swing.JPanel
{
    private Editor editor;
    private JButton save;
    private JCheckBox alertStage;
    private JComboBox weather;
    private JComboBox precip;
    private JCheckBox forceProne;
    private JComboBox songSelect;

    /** Creates new form RoomSettingsEditor */
    public RoomSettingsEditor(Editor e)
    {
        initComponents();
        editor = e;
        this.setLayout(null);
        this.setSize(400, 300);

        JLabel l1 = new JLabel("Room Settings");
        this.add(l1);
        l1.setBounds(0, 0, 100, 50);

        alertStage = new JCheckBox("Alert stage?");
        this.add(alertStage);
        alertStage.setBounds(0, 30, 100, 30);

        forceProne = new JCheckBox("Force prone?");
        this.add(forceProne);
        forceProne.setBounds(100, 30, 100, 30);

        JLabel l2 = new JLabel("Weather Conditions:");
        this.add(l2);
        l2.setBounds(0, 60, 150, 30);

        String[] wconds = {"none", "gas", "dark", "semidark", "midnight", "jam", "gas_jam", "dark_jam", "semidark_jam", "midnight_jam"};
        weather = new JComboBox(wconds);
        this.add(weather);
        weather.setBounds(150, 60, 150, 30);

        JLabel l4 = new JLabel("Precipitation:");
        this.add(l4);
        l4.setBounds(0, 110, 150, 30);

        String[] pconds = {"none", "rain", "snow"};
        precip = new JComboBox(pconds);
        this.add(precip);
        precip.setBounds(150, 110, 150, 30);

        JLabel l3 = new JLabel("Song selection:");
        this.add(l3);
        l3.setBounds(0, 210, 100, 30);
        
        songSelect = new JComboBox(Editor.songs);
        this.add(songSelect);
        songSelect.setBounds(100, 210, 200, 30);

        save = new JButton("Update Room Settings");
        this.add(save);
        save.setBounds(0, 250, 200, 50);

        save.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                save();
            }

        });
    }

    private void save()
    {
        editor.selectedPayload.alert = alertStage.isSelected();
        editor.selectedPayload.weather = (String)weather.getSelectedItem();
        editor.selectedPayload.precip = (String)precip.getSelectedItem();
        editor.selectedPayload.songIndex = songSelect.getSelectedIndex();
        editor.selectedPayload.forceprone = forceProne.isSelected();

        editor.updateRoomSettings();
    }

    public void update()
    {
        alertStage.setSelected(editor.currRoom.getAlertMode());

        forceProne.setSelected(editor.currRoom.forceprone);

        weather.setSelectedIndex(0);

        if (editor.currRoom.jam)
        {
            weather.setSelectedIndex(5);

            if (editor.currRoom.gas)
            {
                weather.setSelectedIndex(6);
            }
            if (editor.currRoom.dark)
            {
                weather.setSelectedIndex(7);
            }
            if (editor.currRoom.semidark)
            {
                weather.setSelectedIndex(8);
            }
            if (editor.currRoom.midnight)
            {
                weather.setSelectedIndex(9);
            }
        }
        else
        {
            if (editor.currRoom.gas)
            {
                weather.setSelectedIndex(1);
            }
            if (editor.currRoom.dark)
            {
                weather.setSelectedIndex(2);
            }
            if (editor.currRoom.semidark)
            {
                weather.setSelectedIndex(3);
            }
            if (editor.currRoom.midnight)
            {
                weather.setSelectedIndex(4);
            }
        }

        precip.setSelectedIndex(0);
        if (editor.currRoom.rain)
        {
            precip.setSelectedIndex(1);
        }
        else if (editor.currRoom.snow)
        {
            precip.setSelectedIndex(2);
        }

        if (editor.currRoom.songIndex < editor.songs.length)
        {
            songSelect.setSelectedIndex(editor.currRoom.songIndex);
        }
        else
        {
            songSelect.setSelectedIndex(0);
        }

        
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
