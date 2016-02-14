/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package LevelEditor;

import java.io.File;

/**
 *
 * @author Jim
 */
public class IT2FileFilter extends javax.swing.filechooser.FileFilter
{

    @Override
    public boolean accept(File f)
    {
        return (f.getName().toLowerCase().endsWith(".it2")) || (f.getName().toLowerCase().endsWith(".it3")) || (f.getName().toLowerCase().endsWith(".it4"));
    }

    @Override
    public String getDescription()
    {
        return "IT2, IT3 and IT4 Level Data [.it2] [.it3] [.it4]";
    }

}

