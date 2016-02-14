/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanResizePanel.java
 *
 * Created on Jun 3, 2012, 3:35:01 PM
 */

package LevelEditor;

import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Jim
 */
public class PanResizePanel extends javax.swing.JPanel
{

    private MapPanel mPanel;
    private Editor editor;
    private JButton rUp;
    private JButton rDown;
    private JButton rLeft;
    private JButton rRight;
    private JButton pUp;
    private JButton pDown;
    private JButton pLeft;
    private JButton pRight;
    private JButton rsButton;
    private JLabel pLabel;

    /** Creates new form PanResizePanel */
    public PanResizePanel(Editor e, MapPanel m)
    {
        initComponents();
        editor = e;
        mPanel = m;
        this.setSize(400, 200);
        this.setLayout(null);

        //▲▼◄►
        rUp = new JButton("─");
        rDown = new JButton("┼");
        rLeft = new JButton("─");
        rRight = new JButton("┼");

        pUp = new JButton("▲");
        pDown = new JButton("▼");
        pLeft = new JButton("◄");
        pRight = new JButton("►");

        pUp.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                panUp();
            }
        });

        pDown.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                panDown();
            }
        });

        pLeft.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                panLeft();
            }
        });

        pRight.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                panRight();
            }
        });

        rUp.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                resizeUp();
            }
        });

        rDown.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                resizeDown();
            }
        });

        rLeft.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                resizeLeft();
            }
        });

        rRight.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                resizeRight();
            }
        });

        rsButton = new JButton("Resize Room");
        pLabel = new JLabel("Pan View");

        this.add(pUp);
        this.add(pDown);
        this.add(pLeft);
        this.add(pRight);
        this.add(rUp);
        this.add(rDown);
        this.add(rLeft);
        this.add(rRight);
        this.add(rsButton);
        this.add(pLabel);

        rUp.setBounds(50, 0, 50, 50);
        rDown.setBounds(50, 100, 50, 50);
        rLeft.setBounds(0, 50, 50, 50);
        rRight.setBounds(100, 50, 50, 50);

        pUp.setBounds(250, 0, 50, 50);
        pDown.setBounds(250, 100, 50, 50);
        pLeft.setBounds(200, 50, 50, 50);
        pRight.setBounds(300, 50, 50, 50);

        rsButton.setBounds(15, 160, 120, 30);
        pLabel.setBounds(245, 150, 100, 50);

        rsButton.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e)
            {
                String retval = JOptionPane.showInputDialog((Component)e.getSource(), "Enter in the size of the map as {length},{width}\ne.g. 32,48", "Edit Map Dimensions", JOptionPane.QUESTION_MESSAGE);
                if (retval != null)
                {
                    String data = retval.replaceAll("[^0-9,]", "");
                    String[] dsplit = data.split(",");
                    int x = 20;
                    int y = 15;

                    try
                    {
                        x = Integer.parseInt(dsplit[0]);
                    }
                    catch(Exception ex)
                    {
                        System.err.println("Error parsing new map width");
                    }

                    if (dsplit.length > 0)
                    {
                        try
                        {
                            y = Integer.parseInt(dsplit[1]);
                        }
                        catch(Exception ex)
                        {
                            System.err.println("Error parsing new map height");
                        }
                    }
                    else
                    {
                        System.err.println("Error parsing new map height");
                    }

                    resizeMap(x, y);
                }
            }

        });
    }

    private void panUp()
    {
        if (mPanel.originY - 1 >= 0)
        {
            mPanel.originY--;
        }
    }

    private void panDown()
    {
        if ((mPanel.originY + 1) < (mPanel.getMaxViewY() - 14))
        {
            mPanel.originY++;
        }
    }

    private void panLeft()
    {
        if (mPanel.originX - 1 >= 0)
        {
            mPanel.originX--;
        }
    }

    private void panRight()
    {
        if ((mPanel.originX + 1) < (mPanel.getMaxViewX() - 19))
        {
            mPanel.originX++;
        }
    }

    private void resizeUp()
    {
        editor.resizeRoom(0, -1);
        //mPanel.updateSpriteMap();
    }

    private void resizeDown()
    {
        editor.resizeRoom(0, 1);
        //mPanel.updateSpriteMap();
    }

    private void resizeLeft()
    {
        editor.resizeRoom(-1, 0);
        //mPanel.updateSpriteMap();
    }

    private void resizeRight()
    {
        editor.resizeRoom(1, 0);
        //mPanel.updateSpriteMap();
    }

    private void resizeMap(int x, int y)
    {
        System.out.println("Resizing to " + x + "," + y);
        editor.resizeRoomDims(x, y);
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
