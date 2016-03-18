/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Jim
 */
public class BossDialogEditor extends javax.swing.JPanel
{
    private BossPlacer bossPlacer;
    private JTextArea dlgTA;
    private JButton save;
    private JButton close;
    private String contents = "";
    public JLabel dLabel;
    public boolean pre = true;

    public BossDialogEditor(BossPlacer bp)
    {
        bossPlacer = bp;
        this.setLayout(null);
        this.setSize(400, 500);

        dLabel = new JLabel("Dialog");
        this.add(dLabel);
        dLabel.setBounds(0, 0, 100, 50);

        dlgTA = new JTextArea("");
        dlgTA.setAutoscrolls(true);
        dlgTA.setEditable(true);

        JScrollPane jsp = new JScrollPane(dlgTA);
        jsp.setBounds(0, 50, 350, 200);
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(jsp);

        save = new JButton("Confirm Changes");
        this.add(save);
        save.setBounds(0, 260, 150, 40);

        save.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                saveChanges();
            }
        });

        close = new JButton("Close");
        this.add(close);
        close.setBounds(170, 260, 150, 40);

        close.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                bossPlacer.hideDialogEditor();
            }
        });
    }

    private void saveChanges()
    {
        if (pre)
        {
            bossPlacer.setPreDialog(dlgTA.getText());
        }
        else
        {
            bossPlacer.setPostDialog(dlgTA.getText());
        }

        bossPlacer.hideDialogEditor();
    }

    public void updateDialog(Dialog d)
    {
        contents = "";

        for(int i = 0; i < d.getContents().size(); i++)
        {
            contents += d.getContents().get(i) + "\n";
        }

        dlgTA.setText(contents);
    }

    public void updateDialog(String s)
    {
        contents = s;
        dlgTA.setText(contents);
    }
}
