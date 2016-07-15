/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaba37.easyreader.actions.menuBar;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import org.jsoup.Jsoup;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;

/**
 *
 * @author zaba3
 */
public class SaveAsAction extends AbstractAction {

    private JTextPane editor;
    private int option;
    private final JFileChooser saveChooser;
    private File fileName;
    private JFrame frame;

    public SaveAsAction(JFrame frame, JTextPane editor, int option) {
        this.editor = editor;
        this.option = option;
        this.saveChooser = new JFileChooser();
        this.frame = frame;
        saveChooser.setApproveButtonText("Save");
    }

    //options
    //1-txt
    //2-pdf
    //3-docx
    //4-doc
    @Override
    public void actionPerformed(ActionEvent e) {

        int actionDialog = saveChooser.showOpenDialog(frame);
        if (actionDialog != JFileChooser.APPROVE_OPTION) {
            return;
        }

        if (option == 1) {
            fileName = new File(saveChooser.getSelectedFile() + ".txt");
        }

        BufferedWriter outFile = null;
        try {
            outFile = new BufferedWriter(new FileWriter(fileName));
            StringWriter writer = new StringWriter();
            HTMLDocument doc = (HTMLDocument) editor.getStyledDocument();

            //HTMLWriter htmlWriter = new OnlyBodyHTMLWriter(writer, doc);
            //htmlWriter.write();
           // outFile.write(Jsoup.parse(editor.getText()).text());
//InputStream stream = ContentHandlerExample.class.getResourceAsStream("test.doc");
//            ContentHandler contenthandler = new BodyContentHandler();
//            Metadata metadata = new Metadata();
//            Parser parser = new AutoDetectParser();
//            parser. .parse(is, contenthandler, metadata, new ParseContext());
//            System.out.println(contenthandler.toString());

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (outFile != null) {
                try {
                    outFile.close();
                } catch (IOException exx) {
                    // one of the few times that I think that it's OK
                    // to leave this blank
                }
            }
        }
    }

    private static class OnlyBodyHTMLWriter extends HTMLWriter {

        public OnlyBodyHTMLWriter(Writer w, HTMLDocument doc) {
            super(w, doc);
        }

        private boolean inBody = false;

        private boolean isBody(Element elem) {
            // copied from HTMLWriter.startTag()
            AttributeSet attr = elem.getAttributes();
            Object nameAttribute = attr
                    .getAttribute(StyleConstants.NameAttribute);
            HTML.Tag name = null;
            if (nameAttribute instanceof HTML.Tag) {
                name = (HTML.Tag) nameAttribute;
            }
            return name == HTML.Tag.BODY;
        }

        @Override
        protected void startTag(Element elem) throws IOException,
                BadLocationException {
            if (inBody) {
                super.startTag(elem);
            }
            if (isBody(elem)) {
                inBody = true;
            }
        }

        @Override
        protected void endTag(Element elem) throws IOException {
            if (isBody(elem)) {
                inBody = false;
            }
            if (inBody) {
                super.endTag(elem);
            }
        }
    }
}
