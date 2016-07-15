/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaba37.easyreader.actions.textEditor;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;

/**
 *
 * @author zaba3
 */
public class ChangeFontAction extends StyledEditorKit.StyledTextAction {

    JTextPane editor;
    JComboBox selector;

    public ChangeFontAction(JTextPane editor, JComboBox selector) {
        super("font-change");
        this.editor = editor;
        this.selector = selector;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (editor != null && selector != null) {
            String fontName = (String) selector.getSelectedItem();
            MutableAttributeSet attr = new SimpleAttributeSet();
            StyleConstants.setFontFamily(attr, fontName);
            setCharacterAttributes(editor, attr, false);
        }
    }
}
