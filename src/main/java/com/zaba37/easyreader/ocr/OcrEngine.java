/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaba37.easyreader.ocr;

import java.io.File;
import net.sourceforge.tess4j.*;

public class OcrEngine {

    private static OcrEngine instance = null;
    private Tesseract tesseract;

    private OcrEngine() {
        tesseract = new Tesseract();
        tesseract.setHocr(true);
        tesseract.setTessVariable("hocr_font_info","1");
        tesseract.setTessVariable("pitsync_linear_version", "6");
        tesseract.setTessVariable("tessedit_pageseg_mode", "1");
        //only for testing change language option
      // tesseract.setLanguage("pol");
        
    }
    
    public static OcrEngine getInstance(){
        if(instance == null){
            instance = new OcrEngine();
        }
        
        return instance;
    }
    
   public String getOcrResult(File file){
       String result = "";
       
       try{
           result = tesseract.doOCR(file);
       }catch(TesseractException e){
           e.printStackTrace();
       }
       
       return result;
    }
    
    

}
