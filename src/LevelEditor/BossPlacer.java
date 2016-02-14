/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BossPlacer.java
 *
 * Created on Jul 15, 2012, 3:05:07 PM
 */

package LevelEditor;

/**
 *
 * @author Jim
 */

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class BossPlacer extends javax.swing.JPanel
{
    private Editor editor;
    private JComboBox type;
    private JButton save;
    private JButton editDialogPre;
    private JButton editDialogPost;
    private JTextField name;
    private JTextField health;
    private JTextField damage;
    private JTextField speed;
    private JTextField viewdistance;
    private JCheckBox bodyArmor;
    public Boss boss = null;
    private JDialog deFrame;
    private BossDialogEditor bdEditor;

    /** Creates new form BossPlacer */
    public BossPlacer(Editor e)
    {
        initComponents();
        editor = e;
        this.setLayout(null);
        this.setSize(400, 300);

        JLabel label1 = new JLabel("Boss");
        this.add(label1);
        label1.setBounds(0, 0, 100, 50);

        ArrayList<String> guardTypes = new ArrayList<String>();

        GuardType[] exclude = {GuardType.LIGHT, GuardType.MEDIUM, GuardType.HEAVY, GuardType.SCIENTIST1, GuardType.SCIENTIST2, GuardType.FEMALE_ALLY_PRISONER, GuardType.WORM, GuardType.LARVA, GuardType.ALIEN, GuardType.WOMAN1, GuardType.WOMAN2, GuardType.WOMAN3};
        for(GuardType gt : GuardType.values())
        {
            boolean validType = true;
            for(GuardType ex: exclude)
            {
                if (gt == ex)
                {
                    validType = false;
                    break;
                }
            }

            if (validType)
            {
                guardTypes.add(gt.toString());
            }
        }

        type = new JComboBox(guardTypes.toArray());

        this.add(type);
        type.setBounds(60, 40, 100, 50);

        JLabel tlbl = new JLabel("Type: ");
        this.add(tlbl);
        tlbl.setBounds(0, 40, 50, 50);

        bodyArmor = new JCheckBox("Body Armor");
        this.add(bodyArmor);
        bodyArmor.setBounds(170, 40, 100, 50);

        JLabel label3 = new JLabel("Name: ");
        this.add(label3);
        label3.setBounds(0, 100, 50, 30);

        name = new JTextField("");
        this.add(name);
        name.setBounds(60, 100, 320, 30);

        JLabel label4 = new JLabel("Damage: ");
        this.add(label4);
        label4.setBounds(0, 140, 60, 30);

        damage = new JTextField("");
        this.add(damage);
        damage.setBounds(60, 140, 70, 30);

        JLabel label5 = new JLabel("Speed: ");
        this.add(label5);
        label5.setBounds(140, 140, 50, 30);

        speed = new JTextField("");
        this.add(speed);
        speed.setBounds(240, 140, 70, 30);

        JLabel label6 = new JLabel("Health: ");
        this.add(label6);
        label6.setBounds(0, 180, 50, 30);

        health = new JTextField("");
        this.add(health);
        health.setBounds(60, 180, 70, 30);

        JLabel label7 = new JLabel("View Distance:");
        this.add(label7);
        label7.setBounds(140, 180, 100, 30);

        viewdistance = new JTextField("");
        this.add(viewdistance);
        viewdistance.setBounds(240, 180, 70, 30);

        editDialogPre = new JButton("Pre-Fight Dialog");
        this.add(editDialogPre);
        editDialogPre.setBounds(0, 215, 150, 40);

        editDialogPost = new JButton("Post-Fight Dialog");
        this.add(editDialogPost);
        editDialogPost.setBounds(170, 215, 150, 40);

        save = new JButton("Save Changes");
        this.add(save);
        save.setBounds(0, 260, 150, 40);

        changeType();

        type.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                changeType();
            }
        });

        bodyArmor.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                bArmor();
            }
        });

        save.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                saveChanges();
            }
        });

        editDialogPre.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                editPrefightDialog();
            }
        });

        editDialogPost.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                editPostfightDialog();
            }
        });

        name.getDocument().addDocumentListener(new DocumentListener()
        {

            public void insertUpdate(DocumentEvent e)
            {
                updateName();
            }

            public void removeUpdate(DocumentEvent e)
            {
                updateName();
            }

            public void changedUpdate(DocumentEvent e)
            {
                updateName();
            }

        });

        damage.getDocument().addDocumentListener(new DocumentListener()
        {

            public void insertUpdate(DocumentEvent e)
            {
                updateDamage();
            }

            public void removeUpdate(DocumentEvent e)
            {
                updateDamage();
            }

            public void changedUpdate(DocumentEvent e)
            {
                updateDamage();
            }

        });

        speed.getDocument().addDocumentListener(new DocumentListener()
        {

            public void insertUpdate(DocumentEvent e)
            {
                updateSpeed();
            }

            public void removeUpdate(DocumentEvent e)
            {
                updateSpeed();
            }

            public void changedUpdate(DocumentEvent e)
            {
                updateSpeed();
            }

        });

        health.getDocument().addDocumentListener(new DocumentListener()
        {

            public void insertUpdate(DocumentEvent e)
            {
                updateHealth();
            }

            public void removeUpdate(DocumentEvent e)
            {
                updateHealth();
            }

            public void changedUpdate(DocumentEvent e)
            {
                updateHealth();
            }

        });

        viewdistance.getDocument().addDocumentListener(new DocumentListener()
        {

            public void insertUpdate(DocumentEvent e)
            {
                updateViewDistance();
            }

            public void removeUpdate(DocumentEvent e)
            {
                updateViewDistance();
            }

            public void changedUpdate(DocumentEvent e)
            {
                updateViewDistance();
            }

        });

        deFrame = new JDialog();
        deFrame.setTitle("Boss Dialog Editor");
        bdEditor = new BossDialogEditor(this);
        deFrame.add(bdEditor);
        deFrame.setSize(400, 400);
        deFrame.setResizable(false);
        deFrame.setVisible(false);
    }

    private void editPrefightDialog()
    {
        if (!deFrame.isVisible())
        {
            //Load the dialog
            bdEditor.updateDialog(editor.selectedPayload.dialog_pre);
        }
        bdEditor.dLabel.setText("Pre-Fight Dialog");
        bdEditor.pre = true;
        Point p = editor.getEditorFrame().getLocationOnScreen();
        deFrame.setLocation(p.x + 815, p.y + 365);
        deFrame.setVisible(true);
    }
    
    private void editPostfightDialog()
    {
        if (!deFrame.isVisible())
        {
            //Load the dialog
            bdEditor.updateDialog(editor.selectedPayload.dialog_post);
        }
        bdEditor.dLabel.setText("Post-Fight Dialog");
        bdEditor.pre = false;
        Point p = editor.getEditorFrame().getLocationOnScreen();
        deFrame.setLocation(p.x + 815, p.y + 365);
        deFrame.setVisible(true);
    }

    public void setPreDialog(String s)
    {
        editor.selectedPayload.dialog_pre = s;
    }

    public void setPostDialog(String s)
    {
        editor.selectedPayload.dialog_post = s;
    }

    public void hideDialogEditor()
    {
        deFrame.setVisible(false);
    }

    private void updateName()
    {
        try
        {
            editor.selectedPayload.name = name.getText();
        }
        catch (Exception e)
        {
            editor.selectedPayload.name = "";
        }
    }

    private void updateDamage()
    {
        try
        {
            editor.selectedPayload.damage = Integer.parseInt(damage.getText());

            if (editor.selectedPayload.damage < 0)
            {
                editor.selectedPayload.damage = 0;
            }
        }
        catch (Exception e)
        {
            editor.selectedPayload.damage = 20;
        }
    }

    private void updateSpeed()
    {
        try
        {
            editor.selectedPayload.speed = Integer.parseInt(speed.getText());

            if (editor.selectedPayload.speed < 1)
            {
                editor.selectedPayload.speed = 1;
            }
        }
        catch (Exception e)
        {
            editor.selectedPayload.speed = 15;
        }
    }

    private void updateHealth()
    {
        try
        {
            editor.selectedPayload.health = Integer.parseInt(health.getText());

            if (editor.selectedPayload.health < 1)
            {
                editor.selectedPayload.health = 1;
            }
        }
        catch (Exception e)
        {
            editor.selectedPayload.health = 100;
        }
    }

    private void updateViewDistance()
    {
        try
        {
            editor.selectedPayload.viewdistance = Integer.parseInt(viewdistance.getText());

            if (editor.selectedPayload.viewdistance < 40)
            {
                editor.selectedPayload.viewdistance = 40;
            }
        }
        catch (Exception e)
        {
            editor.selectedPayload.viewdistance = 800;
        }
    }

    private void saveChanges()
    {
        if (boss != null)
        {
            updateDamage();
            updateHealth();
            updateName();
            updateSpeed();
            updateViewDistance();

            editor.updateBoss();
        }
    }

    private void bArmor()
    {
        editor.selectedPayload.bodyArmor = this.bodyArmor.isSelected();
    }

    private void changeType()
    {
        GuardType gtype = GuardType.BOSS1;

        try
        {
            gtype = GuardType.valueOf(type.getSelectedItem().toString());
        }
        catch(Exception e)
        {
            System.err.println("Invalid guard type selected");
        }

        editor.selectedPayload.gt = gtype;
    }

    public final void enableEditing()
    {
        save.setEnabled(true);
    }

    public final void disableEditing()
    {
        save.setEnabled(false);
    }

    public void update()
    {
        if (boss != null)
        {
            bodyArmor.setSelected(boss.bodyArmor);
            type.setSelectedItem(boss.getType().toString());
            
            name.setText(boss.name);
            health.setText("" + boss.getMaxHealth());
            damage.setText("" + boss.getWeaponDamage());
            speed.setText("" + boss.speed);
            viewdistance.setText("" + boss.viewDistance);

            String precontents = "";
            if (boss.getPreDialog() != null)
            {
                for(int i = 0; i < boss.getPreDialog().getContents().size(); i++)
                {
                    precontents += boss.getPreDialog().getContents().get(i) + "\n";
                }
            }
            editor.selectedPayload.dialog_pre = precontents;

            String postcontents = "";
            if (boss.getPostDialog() != null)
            {
                for(int i = 0; i < boss.getPostDialog().getContents().size(); i++)
                {
                    postcontents += boss.getPostDialog().getContents().get(i) + "\n";
                }
            }
            editor.selectedPayload.dialog_post = postcontents;
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
