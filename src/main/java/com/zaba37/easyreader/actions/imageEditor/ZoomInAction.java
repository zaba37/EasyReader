/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaba37.easyreader.actions.imageEditor;

import com.zaba37.easyreader.imageView.ImageDrawer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;

/**
 *
 * @author Krystian
 */
public class ZoomInAction extends AbstractAction{

    @Override
    public void actionPerformed(ActionEvent e) {
        ImageDrawer.getInstance().zoomIn();
    }
    
}
