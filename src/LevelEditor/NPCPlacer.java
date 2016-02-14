/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NPCPlacer.java
 *
 * Created on Jun 9, 2012, 6:19:30 PM
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
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class NPCPlacer extends javax.swing.JPanel
{
    private Editor editor;
    private JComboBox type;
    private JTextArea pathTA;
    private JCheckBox bodyArmor;
    private JButton save;
    public NPC npc = null;

    /** Creates new form NPCPlacer */
    public NPCPlacer(Editor e)
    {
        initComponents();
        editor = e;
        this.setLayout(null);
        this.setSize(400, 500);
        JLabel nlbl = new JLabel("NPCs");
        this.add(nlbl);
        nlbl.setBounds(0, 0, 100, 50);

        ArrayList<String> guardTypes = new ArrayList<String>();
        GuardType[] exclude = {GuardType.BOSS1, GuardType.BOSS2, GuardType.BOSS3, GuardType.BOSS4, GuardType.BOSS5, GuardType.BOSS6};
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
        type.setBounds(60, 50, 100, 50);

        JLabel tlbl = new JLabel("Type: ");
        this.add(tlbl);
        tlbl.setBounds(0, 50, 50, 50);

        JLabel plbl = new JLabel("Path and Behavior: ");
        this.add(plbl);
        plbl.setBounds(0, 100, 150, 50);

        pathTA = new JTextArea("");
        
        pathTA.setAutoscrolls(true);
        pathTA.setEditable(true);

        JScrollPane jsp = new JScrollPane(pathTA);
        jsp.setBounds(0, 150, 300, 100);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(jsp);

        bodyArmor = new JCheckBox("Body Armor");
        this.add(bodyArmor);
        bodyArmor.setBounds(170, 50, 100, 50);

        save = new JButton("Save Changes");
        this.add(save);
        save.setBounds(0, 260, 150, 40);

        JButton help = new JButton("?");
        this.add(help);
        help.setBounds(250, 110, 50, 30);

        disableEditing();

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

        help.addActionListener(new ActionListener()
        {
           public void actionPerformed(ActionEvent e)
           {
                dispHelp();
           }
        });

    }

    private void dispHelp()
    {
        String message = "To use the NPC Path editor, the following format must be strictly adhered to:\nX-Coordinate[space]Y-Coordinate[space]DIRECTION[space]BEHAVIOR\n"
                + "\nAcceptable Directions include:\nUP, DOWN, LEFT, RIGHT\n\nAcceptable Behaviors include:\nCONTINUE, SLEEP, STOP, WAIT_AND_CONTINUE, LONG_WAIT_AND_CONTINUE,\nFOLLOW_PLAYER\n\n"
                + "Example Path:\n10 5 RIGHT CONTINUE\n15 5 DOWN WAIT_AND_CONTINUE\n15 12 LEFT STOP\n\nPlease note that if one waypoint is heading LEFT or RIGHT, then the Y-Coordinate\nof the following waypoint must match the current waypoint's Y-Coordinate.\n"
                + "The same is true for waypoints heading UP or DOWN, but with X-Coordinates.\n\n*To edit paths, you must be in EDIT mode, not CREATE mode or DELETE mode.";
        JOptionPane.showMessageDialog(this, message, "Using NPC Path Editor", JOptionPane.QUESTION_MESSAGE);
    }

    private void saveChanges()
    {
        if (npc != null)
        {
            editor.selectedPayload.path = pathTA.getText();
            editor.updateNPC(npc);
        }
    }

    private void bArmor()
    {
        editor.selectedPayload.bodyArmor = this.bodyArmor.isSelected();
    }

    private void changeType()
    {
        GuardType gtype = GuardType.LIGHT;

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
        pathTA.setEnabled(true);
        pathTA.setEditable(true);
    }

    public final void disableEditing()
    {
        save.setEnabled(false);
        pathTA.setEditable(false);
        pathTA.setEnabled(false);
    }

    public void update()
    {
        if (npc != null)
        {
            bodyArmor.setSelected(npc.bodyArmor);
            type.setSelectedItem(npc.getType().toString());
            pathTA.setText(npc.getPath().toString());
        }
    }

    //This resets everything and allows functionality to persist post-editing
    public void clearPathText()
    {
        pathTA.setText("");
        bodyArmor.setSelected(false);
        type.setSelectedIndex(0);
        npc = null;
        editor.selectedPayload.path = "";
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
