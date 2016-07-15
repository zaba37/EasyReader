/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaba37.easyreader.actions.textEditor;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;

/**
 *
 * @author zaba3
 */
public class ItalicAction extends StyledEditorKit.StyledTextAction {

    private JTextPane editor;

//    public ItalicAction(){
//        super("font-italic");
//    }
    public ItalicAction(JTextPane editor) {
        super("font-italic");
        this.editor = editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (editor != null) {
            StyledEditorKit kit = getStyledEditorKit(editor);
            MutableAttributeSet attr = kit.getInputAttributes();
            boolean italic = (StyleConstants.isItalic(attr)) ? false : true;
            SimpleAttributeSet sas = new SimpleAttributeSet();
            StyleConstants.setItalic(sas, italic);
            setCharacterAttributes(editor, sas, false);
        }
    }
}
