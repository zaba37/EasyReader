/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaba37.easyreader.windows;

import com.zaba37.easyreader.actions.imageEditor.ZoomInAction;
import com.zaba37.easyreader.actions.imageEditor.ZoomOutAction;
import com.zaba37.easyreader.actions.menuBar.OpenAction;
import com.zaba37.easyreader.actions.menuBar.SaveAsAction;
import com.zaba37.easyreader.actions.menuBar.StartOcrAction;
import com.zaba37.easyreader.actions.textEditor.BoldAction;
import com.zaba37.easyreader.actions.textEditor.ChangeFontAction;
import com.zaba37.easyreader.actions.textEditor.ChangeFontSizeAction;
import com.zaba37.easyreader.actions.textEditor.ItalicAction;
import com.zaba37.easyreader.actions.textEditor.UnderlineAction;
import com.zaba37.easyreader.imageView.ImagePanel;
import com.zaba37.easyreader.imageView.ImageScrollView;
import com.zaba37.easyreader.imageView.ScaledImageLabel;
import com.zaba37.easyreader.ocr.OcrEngine;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Tesseract1;

//goo.gl/KmZ7DQ
/**
 *
 * @author zaba3
 */
public class MainWindow extends JFrame {

    private JPanel containerPanel;
    private Container contentPane;
    private JTextPane textArea;
    private JLabel imageView;
    private File currentImageFile;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public MainWindow() {
        super("Easy Reader");
            
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        setMinimumSize(new Dimension(800, 600));

        contentPane = this.getContentPane();
        containerPanel = new JPanel();
        textArea = new JTextPane();
        
        addComponents();

        pack();
        setLocationRelativeTo(null);
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
        
        saveAsTxtMI.addActionListener(new SaveAsAction(this, textArea, 1));

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
        // startOCRMI.setEnabled(false);
        startOCRMI.addActionListener(new StartOcrAction(this));

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
        JPanel imagePanel = new JPanel(new GridBagLayout());
        GridBagConstraints componentConstraints = new GridBagConstraints();

        componentConstraints.fill = GridBagConstraints.HORIZONTAL;
        componentConstraints.weightx = 0.2;
        componentConstraints.weighty = 0;
        componentConstraints.gridx = 0;
        componentConstraints.gridy = 0;

        imagePanel.setBackground(Color.yellow);
        imagePanel.add(createImageToolsPanel(), componentConstraints);

        componentConstraints.gridx = 0;
        componentConstraints.gridy = 1;
        componentConstraints.fill = GridBagConstraints.BOTH;
        componentConstraints.weightx = 1.0;
        componentConstraints.weighty = 1.0;
        
        //createImageViewArea();
        imagePanel.add(createImageViewArea(), componentConstraints);

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
        JPanel textToolsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JButton boldButton = new JButton();
        JButton italicButton = new JButton();
        JButton underlineButton = new JButton();
        JButton textColorButton = new JButton();
        Image img;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();
        JComboBox fontFamilyChooser = new JComboBox();
       
        JComboBox fontSizeChooser = new JComboBox();
        fontSizeChooser.setEditable(true);
        fontSizeChooser.addItem(new Float(4));
        fontSizeChooser.addItem(new Float(8));
        fontSizeChooser.addItem(new Float(12));
        fontSizeChooser.addItem(new Float(16));
        fontSizeChooser.addItem(new Float(20));
        fontSizeChooser.addItem(new Float(24));

        for (int i = 0; i < fontNames.length; i++) {
            fontFamilyChooser.addItem(fontNames[i]);
        }
        fontFamilyChooser.setSelectedItem(fontNames[0]);

        boldButton.setOpaque(false);
        boldButton.setBorderPainted(false);
        boldButton.setMargin(new Insets(1, 1, 1, 1));
        italicButton.setOpaque(false);
        italicButton.setBorderPainted(false);
        italicButton.setMargin(new Insets(1, 1, 1, 1));
        underlineButton.setOpaque(false);
        underlineButton.setBorderPainted(false);
        underlineButton.setMargin(new Insets(1, 1, 1, 1));
        textColorButton.setOpaque(false);
        textColorButton.setBorderPainted(false);
        textColorButton.setMargin(new Insets(1, 1, 1, 1));

        try {
            img = ImageIO.read(getClass().getResource("/textEditorResource/Bold-104.png"));
            boldButton.setIcon(new ImageIcon(img));

            img = ImageIO.read(getClass().getResource("/textEditorResource/Italic-104.png"));
            italicButton.setIcon(new ImageIcon(img));

            img = ImageIO.read(getClass().getResource("/textEditorResource/TextColor-104.png"));
            textColorButton.setIcon(new ImageIcon(img));

            img = ImageIO.read(getClass().getResource("/textEditorResource/Underline-104.png"));
            underlineButton.setIcon(new ImageIcon(img));

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JPanel buttonsPanel = new JPanel();
        FlowLayout layout = (FlowLayout) buttonsPanel.getLayout();
        layout.setVgap(1);
        layout.setHgap(1);

        boldButton.addActionListener(new BoldAction(textArea));
        buttonsPanel.add(boldButton);
        
        italicButton.addActionListener(new ItalicAction(textArea));
        buttonsPanel.add(italicButton);
        
        buttonsPanel.add(textColorButton);
        
        underlineButton.addActionListener(new UnderlineAction(textArea));
        buttonsPanel.add(underlineButton);
        
        fontFamilyChooser.addActionListener(new ChangeFontAction(textArea, fontFamilyChooser));
        buttonsPanel.add(fontFamilyChooser);
        
        fontSizeChooser.addActionListener(new ChangeFontSizeAction(textArea, fontSizeChooser));
        buttonsPanel.add(fontSizeChooser);

        c.weightx = 0.1;
        c.weighty = 0.1;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;

        textToolsPanel.add(buttonsPanel, c);

        return textToolsPanel;
    }

    private JScrollPane createTextEditorArea() {
        JPanel textEditorPanel = new JPanel();
        JScrollPane scrollPane = new JScrollPane();

        textArea.setBackground(Color.white);
        textArea.setContentType("text/html");
        scrollPane.add(textArea);

        textEditorPanel.add(scrollPane);
        scrollPane.setViewportView(textArea);

        return scrollPane;
    }

    private JPanel createImageToolsPanel() {
        JPanel imageToolsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JButton incresZoomButton = new JButton();
        JButton decresZoomButton = new JButton();
        JButton rotateLeftButton = new JButton();
        JButton rotateRightButton = new JButton();
        Image img;
        
        incresZoomButton.setOpaque(false);
        incresZoomButton.setBorderPainted(false);
        incresZoomButton.setMargin(new Insets(1, 1, 1, 1));
        decresZoomButton.setOpaque(false);
        decresZoomButton.setBorderPainted(false);
        decresZoomButton.setMargin(new Insets(1, 1, 1, 1));
        rotateLeftButton.setOpaque(false);
        rotateLeftButton.setBorderPainted(false);
        rotateLeftButton.setMargin(new Insets(1, 1, 1, 1));
        rotateRightButton.setOpaque(false);
        rotateRightButton.setBorderPainted(false);
        rotateRightButton.setMargin(new Insets(1, 1, 1, 1));
        
         try {
            img = ImageIO.read(getClass().getResource("/imageEditorResource/zoom_incresing.png"));
            incresZoomButton.setIcon(new ImageIcon(img));

            img = ImageIO.read(getClass().getResource("/imageEditorResource/zoom_decreasing.png"));
            decresZoomButton.setIcon(new ImageIcon(img));

            img = ImageIO.read(getClass().getResource("/imageEditorResource/rotate_left.png"));
            rotateLeftButton.setIcon(new ImageIcon(img));

            img = ImageIO.read(getClass().getResource("/imageEditorResource/rotate_right.png"));
            rotateRightButton.setIcon(new ImageIcon(img));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        JPanel buttonsPanel = new JPanel();
        FlowLayout layout = (FlowLayout) buttonsPanel.getLayout();
        layout.setVgap(1);
        layout.setHgap(1);

        incresZoomButton.addActionListener(new ZoomInAction());
        buttonsPanel.add(incresZoomButton);
        
        decresZoomButton.addActionListener(new ZoomOutAction());
        buttonsPanel.add(decresZoomButton);
        
        buttonsPanel.add(rotateLeftButton);
        
        //underlineButton.addActionListener(new UnderlineAction(textArea));
        buttonsPanel.add(rotateRightButton);

        c.weightx = 0.1;
        c.weighty = 0.1;
        c.insets = new Insets(0, 0, 0, 0);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;

        imageToolsPanel.add(buttonsPanel, c);

        return imageToolsPanel;
    }

    private JScrollPane createImageViewArea() {
        ImagePanel imageViewPanel = new ImagePanel();
        imageView = new ScaledImageLabel();
        ImageScrollView scrollPane = new ImageScrollView(imageView);


        scrollPane.add(imageView);
        //imageViewPanel.add(imageView);
       // scrollPane.setViewportView(imageViewPanel);
        
        return scrollPane;
    }

    public void loadImage(File file) {
        try {
            Image image = ImageIO.read(file);
            imageView.setIcon(new ImageIcon(image));
            this.getJMenuBar().getMenu(1).getItem(0).setEnabled(true);
            currentImageFile = file;
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public void loadOcrResult(String result) throws BadLocationException, IOException {
        System.out.print(result);
        HTMLDocument doc = (HTMLDocument) textArea.getDocument();
        HTMLEditorKit editorKit = (HTMLEditorKit) textArea.getEditorKit();
        result = result.replace("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" />", "");
        String text = "";
        // textArea.setC
        editorKit.insertHTML(doc, doc.getLength(), result, 0, 0, null);
        //textArea.setPage(result.);
    }

    public File getLoadImage() {
        return currentImageFile;
    }
}
