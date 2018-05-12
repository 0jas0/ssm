package com.jas.web.utils;

public class FileUtil {
    public static String getExt(String fileName){
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        return ext;
    }

    public static void main(String[] args) {
        String ext = FileUtil.getExt("aa.txt");
        System.out.println(ext);
    }
}
