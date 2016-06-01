/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaba37.easyreader;

import com.zaba37.easyreader.windows.MainWindow;
import java.io.File;
import javax.swing.SwingUtilities;
import net.sourceforge.tess4j.*;

public class Main {

    public static void main(String[] args) {
//        File imageFile = new File("phototest.tif");
//        ITesseract instance = new Tesseract();  // JNA Interface Mapping
//        //ITesseract instance = new Tesseract1(); // JNA Direct Mapping
//
//        try {
//            String result = instance.doOCR(imageFile);
//            System.out.println(result);
//        } catch (TesseractException e) {
//            System.err.println(e.getMessage());
//        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainWindow();
            }
        });

    }
}
