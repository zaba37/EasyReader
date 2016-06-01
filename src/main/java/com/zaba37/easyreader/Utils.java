/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zaba37.easyreader;

import java.io.File;

/**
 *
 * @author zaba3
 */
public class Utils {

    public final static String jpeg = "jpeg";
    public final static String tiff = "tiff";
    public final static String png = "png";
    public final static String bmp = "bmp";
    public final static String pdf = "pdf";
    
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }

        return ext;
    }
}
