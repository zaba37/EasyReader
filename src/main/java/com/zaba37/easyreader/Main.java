/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaba37.easyreader;

import com.zaba37.easyreader.windows.MainWindow;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import net.sourceforge.tess4j.*;

public class Main {

    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (java.lang.Exception e) {
            JOptionPane.showMessageDialog(null, "GUI Error");
        }
        
        //SwingUtilities.invokeLater(() -> {
            new MainWindow().setVisible(true);
      //  });

    }
}
