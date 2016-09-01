/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaba37.easyreader.imageView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author Krystian
 */
public class ImageScrollView extends JScrollPane{
    JLabel imageLabel;
    
    public ImageScrollView(JLabel label){
        this.imageLabel = label;
    }
    
    protected void paintComponent(Graphics g) {
        this.setBackground(Color.green);
        if(!ImageDrawer.getInstance().isZooming()){
            this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
            this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            
            imageLabel.setSize(this.getWidth(), this.getHeight());
        } else {
            this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            this.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        }
        
        this.setViewportView(imageLabel);
    }
}
