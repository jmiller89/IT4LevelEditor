/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RoomEditPanel.java
 *
 * Created on Jun 3, 2012, 11:09:48 PM
 */

package LevelEditor;

import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Jim
 */
public class RoomEditPanel extends javax.swing.JPanel
{
    private JComboBox rooms;
    private Editor editor;
    private JButton newRoom;
    private JButton deleteRoom;
    private JLabel rLabel;

    /** Creates new form RoomEditPanel */
    public RoomEditPanel(Editor e)
    {
        initComponents();
        this.setLayout(null);
        this.setVisible(true);
        this.setSize(500, 200);
        editor = e;

        Integer[] roomIndecies = new Integer[editor.currLevel.getNumRooms()];

        for(int i = 0; i < roomIndecies.length; i++)
        {
            roomIndecies[i] = new Integer(i);
        }

        rooms = new JComboBox(roomIndecies);
        this.add(rooms);
        rooms.setBounds(0, 0, 100, 50);

        rooms.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                updateRoom();
            }
        });

        newRoom = new JButton("Add Room");
        deleteRoom = new JButton("Delete Room");
        rLabel = new JLabel("Select Room");

        this.add(newRoom);
        this.add(deleteRoom);
        this.add(rLabel);

        newRoom.setBounds(150, 0, 120, 50);
        deleteRoom.setBounds(150, 60, 120, 50);
        rLabel.setBounds(0, 40, 100, 50);


        newRoom.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //updateRoom();
                makeNewRoom();
            }
        });

        deleteRoom.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                //updateRoom();
                deleteThisRoom();
            }
        });
    }

//    private void saveRoom()
//    {
//        System.out.println(rooms.getSelectedIndex());
//
//        if (rooms.getSelectedIndex() >= 0)
//        {
//            editor.saveRoom(rooms.getSelectedIndex());
//        }
//        else
//        {
//            editor.addRoom();
//        }
//    }

    private void makeNewRoom()
    {
        editor.newRoom();
    }

    private void deleteThisRoom()
    {
        if (rooms.getSelectedIndex() >= 0)
        {
            editor.notEditing();
            editor.clearEdits();
            editor.deleteRoom(rooms.getSelectedIndex());
        }
    }

    private void updateRoom()
    {
        if (rooms.getSelectedIndex() >= 0)
        {
            editor.changeRoom(rooms.getSelectedIndex());
            editor.notEditing();
            editor.clearEdits();
        }
    }

    public void update()
    {
        //System.out.println("UC");
        rooms.removeAllItems();
        for(int i = 0; i < editor.currLevel.getNumRooms(); i++)
        {
            rooms.addItem(new Integer(i));
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
