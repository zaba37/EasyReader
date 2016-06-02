/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaba37.easyreader.windows;

import com.zaba37.easyreader.actions.OpenAction;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;

//goo.gl/KmZ7DQ
/**
 *
 * @author zaba3
 */
public class MainWindow extends JFrame {

    private JPanel containerPanel;
    private Container contentPane;
    private JTextPane textArea;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MainWindow() {
        super("Easy Reader");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        contentPane = this.getContentPane();
        containerPanel = new JPanel();

        addComponents();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addComponents() {
        createMenuBar();

        containerPanel.setBackground(Color.red);
        containerPanel.setLayout(new GridLayout(1, 2, 0, 0)); //0,0 are gaps
        containerPanel.add(createImagePanel());
        containerPanel.add(createTextPanel());
        
        this.add(containerPanel);
    }

    private void createMenuBar() {
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

    private JPanel createImagePanel() {
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.yellow);

        return imagePanel;
    }

    private JPanel createTextPanel() {
        JPanel textPanel = new JPanel(new GridBagLayout());
        GridBagConstraints componentConstraints = new GridBagConstraints();

        componentConstraints.fill = GridBagConstraints.HORIZONTAL;
        componentConstraints.weightx = 0.2;
        componentConstraints.weighty = 0;
        componentConstraints.gridx = 0;
        componentConstraints.gridy = 0;

        textPanel.setBackground(Color.green);
        textPanel.add(createTextToolsPanel(), componentConstraints);
        
        componentConstraints.gridx = 0;
        componentConstraints.gridy = 1;
        componentConstraints.fill = GridBagConstraints.BOTH;
        componentConstraints.weightx = 1.0;
        componentConstraints.weighty = 1.0;

        textPanel.add(createTextEditorArea(), componentConstraints);
        
        return textPanel;
    }

    private JPanel createTextToolsPanel() {
        JPanel textToolsPanel = new JPanel();
        JButton b = new JButton("button 1");

        textToolsPanel.add(b);

        return textToolsPanel;
    }

    private JScrollPane createTextEditorArea() {
        JPanel textEditorPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        textArea = new JTextPane();

        textArea.setContentType("text/html");
        scrollPane.add(textArea);

        textEditorPanel.add(scrollPane);
        scrollPane.setViewportView(textArea);

        return scrollPane;
    }

    private JPanel createImageToolsPanel() {
        JPanel imageToolsPanel = new JPanel();

        return imageToolsPanel;
    }
}
