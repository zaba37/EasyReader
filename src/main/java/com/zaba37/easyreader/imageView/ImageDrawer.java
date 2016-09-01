/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaba37.easyreader.imageView;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class ImageDrawer {
    private static ImageDrawer instance = null;
    
    private boolean zooming;
    private static float zoomScale; 
    
    protected ImageDrawer(){
        zoomScale = 1;
        zooming = false;
    }
    
    public static ImageDrawer getInstance(){
        
        if(instance == null){
            instance = new ImageDrawer();
        }
        
        return instance;
    }
    
    public static void drawScaledImage(Image image, Component canvas, Graphics g) {
        int imgWidth = image.getWidth(null);
        int imgHeight = image.getHeight(null);
        
        imgWidth = (int)((float)imgWidth * 1);
        imgHeight = (int)((float)imgHeight * 1);
        
        double imgAspect = (double) imgHeight / imgWidth;
 
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
         
        canvasWidth = (int)((float)canvasWidth * 1);
        canvasHeight = (int)((float)canvasHeight * 1);
        
        double canvasAspect = (double) canvasHeight / canvasWidth;
 
        int x1 = 0; // top left X position
        int y1 = 0; // top left Y position
        int x2 = 0; // bottom right X position
        int y2 = 0; // bottom right Y position
         
        if (imgWidth < canvasWidth && imgHeight < canvasHeight) {
            // the image is smaller than the canvas
            x1 = (canvasWidth - imgWidth)  / 2;
            y1 = (canvasHeight - imgHeight) / 2;
            x2 = imgWidth + x1;
            y2 = imgHeight + y1;
             
        } else {
            if (canvasAspect > imgAspect) {
                y1 = canvasHeight;
                // keep image aspect ratio
                canvasHeight = (int) (canvasWidth * imgAspect);
                y1 = (y1 - canvasHeight) / 2;
            } else {
                x1 = canvasWidth;
                // keep image aspect ratio
                canvasWidth = (int) (canvasHeight / imgAspect);
                x1 = (x1 - canvasWidth) / 2;
            }
            x2 = canvasWidth + x1;
            y2 = canvasHeight + y1;
        }
        
        Graphics2D a = (Graphics2D)g;
        System.out.println(zoomScale);
        
        //x1 y1       x2 y1 
        
        //x1 y2       x2 y2
        
        double pointsHeight = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y1, 2));
        double pointsWidth = Math.sqrt(Math.pow(x2 - x2, 2) + Math.pow(y1 - y2, 2));
        
        if(zoomScale > 1){
            double percentToAdd = zoomScale - 1;
            int plusX =(int)(pointsWidth * percentToAdd);
            int plusY =(int)(pointsHeight * percentToAdd);
            
            //x1 = x1 - plusX/2;
            x2 = x2 + plusX;
            
            //y1 = y1 - plusY/2;
            y2 = y2 + plusY;
        }else if(zoomScale < 1){
            double percentToMinus = zoomScale - 1;
            int minusX =(int)(pointsWidth * percentToMinus);
            int minusY =(int)(pointsHeight * percentToMinus);
            
            x2 = x2 - minusX;
            y2 = y2 - minusY;
        }
                
//        a.translate(canvasWidth/2, canvasHeight/2);
//        a.scale(zoomScale, zoomScale);
//        a.translate(-canvasWidth/2, -canvasHeight/2);
//        
        g.drawImage(image, x1, y1, x2, y2, 0, 0, imgWidth, imgHeight, null);
        
        
//        a.translate(canvasWidth/2, canvasHeight/2);
//        a.scale(zoomScale, zoomScale);
//        a.translate(-canvasWidth/2, -canvasHeight/2);
//        a.translate(canvasWidth/2, canvasHeight/2);
//        a.scale(1, 1);
//        a.translate(-canvasWidth/2, -canvasHeight/2);
        

        //a.scale(zoomScale, zoomScale);
    }
    
    public boolean isZooming(){
        return zooming;
    }
    
    public void setZooming(boolean zooming){
        this.zooming = zooming;
    }
    
    public void resetZoom(){
        zoomScale = 1;
        zooming = false;
    }
    
    public void zoomIn(){
        if(zoomScale < 2){
            zoomScale += 0.2;
        }
        
        if(zoomScale == 1){
            zooming = false;
        } else {
            zooming = true;
        }
    }
    
    public void zoomOut(){
        if(zoomScale > 0.5){
            zoomScale -= 0.2;
        }
        
        if(zoomScale == 1){
            zooming = false;
        } else {
            zooming = true;
        }
    }
}