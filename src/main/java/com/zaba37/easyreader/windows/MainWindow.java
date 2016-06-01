/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaba37.easyreader.windows;

import com.zaba37.easyreader.actions.OpenAction;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

//goo.gl/KmZ7DQ
/**
 *
 * @author zaba3
 */
public class MainWindow extends JFrame {

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MainWindow() {
        super("Easy Reader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        this.createMenuBar();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void addComponents(){
        
    }
    
    private void createMenuBar(){
        JMenuBar menuBar = new JMenuBar();
        
        //File menu
        JMenu fileMenu = new JMenu("File");
        
        JMenuItem loadMI = new JMenuItem("Open");
        loadMI.addActionListener(new OpenAction(this));
        
        JMenu saveAsMI = new JMenu("Save As");
        JMenuItem exitMI = new JMenuItem("Exit");
        
        JMenuItem saveAsTxtMI = new JMenuItem("TXT");
        JMenuItem saveAsDocMI = new JMenuItem("DOC");
        JMenuItem saveAsDocxMI = new JMenuItem("DOCX");
        JMenuItem saveAsPdfMI = new JMenuItem("PDF");
        
        //Save As menu
        saveAsMI.add(saveAsTxtMI);
        saveAsMI.add(saveAsDocMI);
        saveAsMI.add(saveAsDocxMI);
        saveAsMI.add(saveAsPdfMI);
        
        fileMenu.add(loadMI);
        fileMenu.add(saveAsMI);
        fileMenu.addSeparator();
        fileMenu.add(exitMI);
        
        menuBar.add(fileMenu);
        
        //OCR menu
        JMenu ocrMenu = new JMenu("OCR");
        
        JMenuItem startOCRMI = new JMenuItem("Start OCR Processing");
        
        ocrMenu.add(startOCRMI);
        menuBar.add(ocrMenu);
        
        //Image menu
        JMenu imageMenu = new JMenu("Image");
        
        JMenuItem binarizationMI = new JMenuItem("Binarization");
        JMenuItem invertMI = new JMenuItem("Invert");
        JMenuItem clearMI = new JMenuItem("Clear");
        JMenuItem rotateMI = new JMenuItem("Rotate");
        JMenuItem flipMI = new JMenuItem("Flip");
        
        imageMenu.add(binarizationMI);
        imageMenu.addSeparator();
        imageMenu.add(invertMI);
        imageMenu.add(clearMI);
        imageMenu.addSeparator();
        imageMenu.add(rotateMI);
        imageMenu.add(flipMI);
        menuBar.add(imageMenu);
        
        //Settings menu
        JMenu settingsMenu = new JMenu("Settings");
        
        JMenuItem changeFontMI = new JMenuItem("Change Font");
        JMenuItem changeLanguageMI = new JMenuItem("Change Language");
        JMenuItem openLanguageFolderMI = new JMenuItem("Open Language Folder");
        
        settingsMenu.add(changeFontMI);
        settingsMenu.add(changeLanguageMI);
        settingsMenu.addSeparator();
        settingsMenu.add(openLanguageFolderMI);
        menuBar.add(settingsMenu);
        
        this.setJMenuBar(menuBar);
    }

}
