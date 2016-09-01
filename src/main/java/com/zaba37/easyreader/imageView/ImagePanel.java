/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaba37.easyreader.imageView;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Krystian
 */
public class ImagePanel extends JPanel{
    
    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("Image Panel Paint");
    }
}
