/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ObjectiveEditor.java
 *
 * Created on Jul 3, 2012, 6:48:02 PM
 */

package LevelEditor;

/**
 *
 * @author Jim
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ObjectiveEditor extends javax.swing.JPanel
{
    private Editor editor;
    private JComboBox type;
    private JTextArea dialog;
    private JButton save;
    private JTextField name;

    public Objective objective = null;
    
    /** Creates new form ObjectiveEditor */
    public ObjectiveEditor(Editor e)
    {
        initComponents();
        editor = e;
        this.setLayout(null);
        this.setSize(400, 300);

        JLabel label1 = new JLabel("Objectives");
        this.add(label1);
        label1.setBounds(0, 0, 100, 50);

        JLabel label2 = new JLabel("Type: ");
        this.add(label2);
        label2.setBounds(0, 50, 50, 30);

        type = new JComboBox(editor.objectives);
        this.add(type);
        type.setBounds(50, 50, 100, 30);

        JLabel label3 = new JLabel("Name: ");
        this.add(label3);
        label3.setBounds(0, 90, 50, 30);

        name = new JTextField("");
        this.add(name);
        name.setBounds(50, 90, 320, 30);

        JLabel label4 = new JLabel("Dialog: ");
        this.add(label4);
        label4.setBounds(0, 120, 50, 30);

        dialog = new JTextArea("");
        dialog.setAutoscrolls(true);
        dialog.setEditable(true);

        JScrollPane jsp = new JScrollPane(dialog);
        jsp.setBounds(0, 150, 370, 100);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(jsp);

        save = new JButton("Update Objective");
        this.add(save);
        save.setBounds(0, 260, 150, 40);

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

        dialog.getDocument().addDocumentListener(new DocumentListener()
        {

            public void insertUpdate(DocumentEvent e)
            {
                updateDialog();
            }

            public void removeUpdate(DocumentEvent e)
            {
                updateDialog();
            }

            public void changedUpdate(DocumentEvent e)
            {
                updateDialog();
            }

        });
    }

    public void update()
    {
        if (objective != null)
        {
            dialog.setText(objective.dialog.toString());
            name.setText(objective.name);
            updateTypeEdit();
        }
        else
        {
            dialog.setText("");
            name.setText("");
            type.setSelectedIndex(0);
        }
    }

    private void updateTypeEdit()
    {
        if (objective != null)
        {
            if (objective.getID() == 222)
            {
                type.setSelectedIndex(1);
            }
            else if (objective.getID() == 223)
            {
                type.setSelectedIndex(2);
            }
            else if (objective.getID() == 224)
            {
                type.setSelectedIndex(3);
            }
            else if (objective.getID() == 225)
            {
                type.setSelectedIndex(4);
            }
            else
            {
                type.setSelectedIndex(0);
            }
        }
    }

    private void updateType()
    {
        editor.selectedPayload.id = type.getSelectedIndex();
    }

    private void updateName()
    {
        editor.selectedPayload.name = name.getText();
        //System.out.println(editor.selectedPayload.name);
    }

    private void updateDialog()
    {
        editor.selectedPayload.dialog = dialog.getText();
        //System.out.println(editor.selectedPayload.dialog);
    }

    private void save()
    {
        updateType();
        updateName();
        updateDialog();

        if (objective != null)
        {
            editor.updateObjective(objective);
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
