/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CameraEditor.java
 *
 * Created on Jul 15, 2012, 1:12:38 PM
 */

package LevelEditor;

/**
 *
 * @author Jim
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JOptionPane;

public class CameraEditor extends javax.swing.JPanel
{
    private Editor editor;
    private JComboBox direction;
    private JComboBox camType;
    private JTextField delta;
    private JCheckBox isStatic;
    private JButton save;
    private JButton help;

    public SecurityCamera camera = null;

    /** Creates new form CameraEditor */
    public CameraEditor(Editor e)
    {
        initComponents();
        editor = e;
        this.setLayout(null);
        this.setSize(400, 300);

        JLabel l1 = new JLabel("Security Cameras");
        this.add(l1);
        l1.setBounds(0, 0, 150, 50);

        JLabel l2 = new JLabel("Facing Direction:");
        this.add(l2);
        l2.setBounds(0, 50, 110, 50);

        String[] dirs = {"UP", "DOWN", "LEFT", "RIGHT"};
        direction = new JComboBox(dirs);
        this.add(direction);
        direction.setBounds(110, 50, 100, 50);

        isStatic = new JCheckBox("Fixed?");
        this.add(isStatic);
        isStatic.setBounds(0, 160, 100, 50);

        isStatic.setSelected(true);

        JLabel l2b = new JLabel("Camera Type:");
        this.add(l2b);
        l2b.setBounds(100,160,100,50);

        ArrayList<String> camTypeStrs = new ArrayList<String>();
        for(SecurityCameraType ct : SecurityCameraType.values())
        {
            camTypeStrs.add(ct.toString());
        }

        camType = new JComboBox(camTypeStrs.toArray());

        camType.setBounds(200, 160, 150, 50);
        this.add(camType);

        JLabel l3 = new JLabel("Delta");
        this.add(l3);
        l3.setBounds(0, 120, 40, 30);

        delta = new JTextField("0");
        this.add(delta);
        delta.setBounds(50, 120, 50, 30);

        help = new JButton("?");
        this.add(help);
        help.setBounds(130, 10, 50, 30);

        save = new JButton("Update Camera...");
        this.add(save);
        save.setBounds(0, 220, 150, 50);

        save.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                save();
            }

        });

        help.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                showHelp();
            }

        });

        direction.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                updateDir();
            }

        });

        camType.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                updateCamType();
            }
        });

        isStatic.addActionListener(new ActionListener()
        {

            public void actionPerformed(ActionEvent e)
            {
                updateStatic();
            }

        });

        delta.getDocument().addDocumentListener(new DocumentListener()
        {

            public void insertUpdate(DocumentEvent e)
            {
                updateDelta();
            }

            public void removeUpdate(DocumentEvent e)
            {
                updateDelta();
            }

            public void changedUpdate(DocumentEvent e)
            {
                updateDelta();
            }

        });

    }

    private void save()
    {
        updateDir();
        updateDelta();
        updateStatic();
        
        if (camera != null)
        {
            editor.updateCamera(camera);
        }
    }

    private void updateDir()
    {
        if (direction.getSelectedIndex() == 0)
        {
            editor.selectedPayload.dir = Direction.UP;
        }
        else if (direction.getSelectedIndex() == 1)
        {
            editor.selectedPayload.dir = Direction.DOWN;
        }
        else if (direction.getSelectedIndex() == 2)
        {
            editor.selectedPayload.dir = Direction.LEFT;
        }
        else if (direction.getSelectedIndex() == 3)
        {
            editor.selectedPayload.dir = Direction.RIGHT;
        }
    }

    private void updateCamType()
    {
         String cts = (String)camType.getSelectedItem();

         try
         {
            editor.selectedPayload.cameraType = SecurityCameraType.valueOf(cts);
         }
         catch(Exception e)
         {
             System.err.println(e);
             editor.selectedPayload.cameraType = SecurityCameraType.NORMAL;
         }
    }

    private void updateDelta()
    {
        try
        {
            editor.selectedPayload.delta = Integer.parseInt(delta.getText());

            if (editor.selectedPayload.delta != 0)
            {
                isStatic.setSelected(false);
                updateStatic();
            }
        }
        catch (Exception e)
        {
            editor.selectedPayload.delta = 0;
            isStatic.setSelected(true);
            updateStatic();
        }
    }

    private void updateStatic()
    {
        editor.selectedPayload.isFixed = isStatic.isSelected();
    }

    private void showHelp()
    {
        String message = "Cameras facing UP or DOWN will move LEFT and RIGHT.\nCameras facing LEFT or RIGHT will move UP and DOWN.\n\n"
                + "The Delta property dictates how many tiles the Camera will traverse based on its direction.\n"
                + "Ex #1: A camera facing RIGHT with a delta of 3 will oscillate between its current position and 3 tiles down.\n"
                + "Ex #2: A camera facing UP with a delta of -2 will oscillate between its current position and 2 tiles to the left.\n\n"
                + "Checking the 'Fixed?' box will have the camera always remain still. It is essentially equivalent of setting the\n"
                + "Delta property to zero, but will allow for better performance, especially on slower systems.\n\n"
                + "Note: Cameras ignore all collision detection while moving. For best results, place cameras on\n"
                + "walls and do not allow them to travel off of the walls they are placed on.";
        JOptionPane.showMessageDialog(this, message, "Creating/Editing Cameras", JOptionPane.QUESTION_MESSAGE);
    }

    public void update()
    {
        if (camera != null)
        {
            direction.setSelectedItem(camera.getDirection().toString());
            isStatic.setSelected(camera.fixed);
            camType.setSelectedItem(camera.type.toString());

            delta.setText(getDelta());
        }
        else
        {
            direction.setSelectedIndex(0);
            delta.setText("0");
            isStatic.setSelected(true);
            camType.setSelectedIndex(0);
        }
    }

    private String getDelta()
    {
        String d = "";

        if (camera != null)
        {
            if ((camera.getDirection() == Direction.UP) || (camera.getDirection() == Direction.DOWN))
            {
                int dx = camera.end.getXPos() - camera.start.getXPos();
                d = Integer.toString(dx);
            }
            else if ((camera.getDirection() == Direction.LEFT) || (camera.getDirection() == Direction.RIGHT))
            {
                int dy = camera.end.getYPos() - camera.start.getYPos();
                d = Integer.toString(dy);
            }
        }

        return d;
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
