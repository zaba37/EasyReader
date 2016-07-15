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
public class UnderlineAction extends StyledEditorKit.StyledTextAction {

    JTextPane editor;

    public UnderlineAction(JTextPane editor) {
        super("font-underline");
        this.editor = editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (editor != null) {
            StyledEditorKit kit = getStyledEditorKit(editor);
            MutableAttributeSet attr = kit.getInputAttributes();
            boolean underline = (StyleConstants.isUnderline(attr)) ? false : true;
            SimpleAttributeSet sas = new SimpleAttributeSet();
            StyleConstants.setUnderline(sas, underline);
            setCharacterAttributes(editor, sas, false);
        }
    }
}
