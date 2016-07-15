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
public class ChangeFontSizeAction extends StyledEditorKit.StyledTextAction {

    JTextPane editor;
    JComboBox selector;

    public ChangeFontSizeAction(JTextPane editor, JComboBox selector) {
        super("font-size");
        this.editor = editor;
        this.selector = selector;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (editor != null && selector != null) {
            int fontSize = Math.round((float)selector.getSelectedItem());
            MutableAttributeSet attr = new SimpleAttributeSet();
            StyleConstants.setFontSize(attr, fontSize);
            setCharacterAttributes(editor, attr, false);
        }
    }
}
