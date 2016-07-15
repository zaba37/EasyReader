/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaba37.easyreader.actions.menuBar;

import com.zaba37.easyreader.ocr.OcrEngine;
import com.zaba37.easyreader.windows.MainWindow;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.text.BadLocationException;

/**
 *
 * @author zaba3
 */
public class StartOcrAction extends AbstractAction {
    
    private JFrame frame;
    private OcrEngine ocrEngine;
    private File file;
    
    public StartOcrAction(JFrame frame) {
        this.frame = frame;
        ocrEngine = OcrEngine.getInstance();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ((MainWindow) frame).loadOcrResult(ocrEngine.getOcrResult(((MainWindow)frame).getLoadImage()));
        } catch (BadLocationException ex) {
            Logger.getLogger(StartOcrAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StartOcrAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
