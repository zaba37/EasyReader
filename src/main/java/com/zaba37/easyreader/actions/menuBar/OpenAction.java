/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaba37.easyreader.actions.menuBar;

import com.zaba37.easyreader.Utils;
import com.zaba37.easyreader.windows.MainWindow;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author zaba3
 */
public class OpenAction extends AbstractAction {

    private JFileChooser fileChooser;
    private JFrame frame;

    public OpenAction(JFrame frame) {
        this.frame = frame;
        fileChooser = new JFileChooser("Open image");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new ImageFilter());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int returnVal = fileChooser.showDialog(frame, "Open image");

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();
            ((MainWindow)frame).loadImage(file);

        }
    }

    private class ImageFilter extends FileFilter {

        @Override
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }

            String extension = Utils.getExtension(f);
            if (extension != null) {
                if (extension.equals(Utils.bmp) || extension.equals(Utils.jpeg) || extension.equals(Utils.pdf) || extension.equals(Utils.png) || extension.equals(Utils.tiff) || extension.equals(Utils.jpg)) {
                    return true;
                } else {
                    return false;
                }
            }

            return false;
        }

        @Override
        public String getDescription() {
            return "Just Image (JPEG, BMP, PDF, PNG, TIFF) files";
        }

    }

}
