/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelEditor;

/**
 *
 * @author Jim
 */

import java.util.ArrayList;

public class Dialog
{
    private ArrayList<String> dialogue;
    private int currentText = 0;

    public Dialog()
    {
        dialogue = new ArrayList<String>();
    }

    public Dialog(Dialog d)
    {
        dialogue = new ArrayList<String>();
        
        for(int i = 0; i < d.dialogue.size(); i++)
        {
            dialogue.add(new String(d.dialogue.get(i)));
        }
    }

    public boolean hasNext()
    {
        return (currentText < dialogue.size());
    }

    public String getNext()
    {
        return dialogue.get(currentText++);
    }

    public void add(String s)
    {
        dialogue.add(s);
    }

    public ArrayList<String> getContents()
    {
        return dialogue;
    }

    @Override
    public String toString()
    {
        String dst = "";

        for(int i = 0; i < dialogue.size(); i++)
        {
            dst += dialogue.get(i) + "\n";
        }

        return dst;
    }

}
