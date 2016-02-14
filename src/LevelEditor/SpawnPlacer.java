/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SpawnPlacer.java
 *
 * Created on Jul 14, 2012, 12:30:57 PM
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
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SpawnPlacer extends javax.swing.JPanel
{
    private Editor editor;
    private JComboBox type;
    private JCheckBox armored;
    private JTextField capacity;
    private JButton save;

    public Spawn spawn = null;

    /** Creates new form SpawnPlacer */
    public SpawnPlacer(Editor e)
    {
        initComponents();
        editor = e;
        this.setLayout(null);
        this.setSize(400, 300);

        JLabel l1 = new JLabel("Spawns");
        this.add(l1);
        l1.setBounds(0, 0, 100, 50);

        String[] guardTypes = {"LIGHT", "MEDIUM", "HEAVY", "SCIENTIST1", "SCIENTIST2", "WORM", "LARVA", "ALIEN", "SPECIAL_ENEMY"};
        type = new JComboBox(guardTypes);

        this.add(type);
        type.setBounds(60, 50, 100, 50);

        JLabel tlbl = new JLabel("Type: ");
        this.add(tlbl);
        tlbl.setBounds(0, 50, 50, 50);

        armored = new JCheckBox("Armored?");
        this.add(armored);
        armored.setBounds(0, 100, 100, 50);

        JLabel l2 = new JLabel("Capacity:");
        this.add(l2);
        l2.setBounds(0, 150, 60, 50);

        capacity = new JTextField("1");
        this.add(capacity);
        capacity.setBounds(60, 150, 100, 50);

        save = new JButton("Update Spawn...");
        this.add(save);
        save.setBounds(0, 220, 150, 50);

        save.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                save();
            }

        });

        type.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                updateType();
            }

        });

        armored.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                updateArmored();
            }

        });

        capacity.getDocument().addDocumentListener(new DocumentListener()
        {

            public void insertUpdate(DocumentEvent e)
            {
                updateCapacity();
            }

            public void removeUpdate(DocumentEvent e)
            {
                updateCapacity();
            }

            public void changedUpdate(DocumentEvent e)
            {
                updateCapacity();
            }

        });
    }

    //Spawns must be able to spawn at least 1 guard. Negative numbers are not permitted
    private void updateCapacity()
    {
        try
        {
            editor.selectedPayload.capacity = Integer.parseInt(capacity.getText());

            if (editor.selectedPayload.capacity < 1)
            {
                editor.selectedPayload.capacity = 1;
            }
        }
        catch(Exception e)
        {
            editor.selectedPayload.capacity = 1;
        }
    }

    private void updateType()
    {
        if (type.getSelectedItem().toString().equals(GuardType.LIGHT.toString()))
        {
            editor.selectedPayload.gt = GuardType.LIGHT;
        }
        if (type.getSelectedItem().toString().equals(GuardType.MEDIUM.toString()))
        {
            editor.selectedPayload.gt = GuardType.MEDIUM;
        }
        if (type.getSelectedItem().toString().equals(GuardType.HEAVY.toString()))
        {
            editor.selectedPayload.gt = GuardType.HEAVY;
        }
        if (type.getSelectedItem().toString().equals(GuardType.SCIENTIST1.toString()))
        {
            editor.selectedPayload.gt = GuardType.SCIENTIST1;
        }
        if (type.getSelectedItem().toString().equals(GuardType.SCIENTIST2.toString()))
        {
            editor.selectedPayload.gt = GuardType.SCIENTIST2;
        }
        if (type.getSelectedItem().toString().equals(GuardType.WORM.toString()))
        {
            editor.selectedPayload.gt = GuardType.WORM;
        }
        if (type.getSelectedItem().toString().equals(GuardType.LARVA.toString()))
        {
            editor.selectedPayload.gt = GuardType.LARVA;
        }
        if (type.getSelectedItem().toString().equals(GuardType.ALIEN.toString()))
        {
            editor.selectedPayload.gt = GuardType.ALIEN;
        }
        if (type.getSelectedItem().toString().equals(GuardType.SPECIAL_ENEMY.toString()))
        {
            editor.selectedPayload.gt = GuardType.SPECIAL_ENEMY;
        }
    }

    private void updateArmored()
    {
        editor.selectedPayload.bodyArmor = armored.isSelected();
    }

    private void save()
    {
        updateCapacity();
        updateType();
        updateArmored();

        if (spawn != null)
        {
            editor.updateSpawn(spawn);
        }
    }

    public void update()
    {
        if (spawn != null)
        {
            capacity.setText("" + spawn.limit);
            armored.setSelected(spawn.armored);
            type.setSelectedItem(spawn.guardType.toString());
        }
        else
        {
            capacity.setText("1");
            armored.setSelected(false);
            type.setSelectedIndex(0);
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
