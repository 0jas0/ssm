package com.jas.web.utils;

public class FileUtil {
    public static String getExt(String fileName){
        String ext = fileName.substring(fileName.lastIndexOf(".")+1);
        return ext;
    }
}
