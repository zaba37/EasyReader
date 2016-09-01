/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaba37.easyreader.imageView;

import java.awt.Graphics;
 
import javax.swing.ImageIcon;
import javax.swing.JLabel;
 
public class ScaledImageLabel extends JLabel {
    
    protected void paintComponent(Graphics g) {
        ImageIcon icon = (ImageIcon) getIcon();
        
        if (icon != null) {
            ImageDrawer.drawScaledImage(icon.getImage(), this, g);
        }
    }
    
}