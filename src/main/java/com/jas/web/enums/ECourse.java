package com.jas.web.enums;

import java.util.LinkedList;
import java.util.List;

public enum ECourse {
    FISRT_CLASS(1,"第一大节课"),
    SECOND_CLASS(2,"第二大节课"),
    THIRD_CLASS(3,"第三大节课"),
    FOURTH_CLASS(4,"第四大节课");

    ECourse(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private Integer value;
    private String desc;



    public static ECourse findByValue(Integer value){
        switch (value){
            case 1:
                return FISRT_CLASS;
            case 2:
                return SECOND_CLASS;
            case 3:
                return THIRD_CLASS;
            case 4:
                return FOURTH_CLASS;
            default:
                return null;
        }
    }

    public static String getDescByValue(Integer value){
        ECourse eSex = findByValue(value);
        if (eSex == null){
            return "";
        }
        return eSex.getDesc();
    }

    public static List<Integer> getValues(){
        List<Integer> descList = new LinkedList<>();
        for(ECourse eCourse : ECourse.values()){
            descList.add(eCourse.getValue());
        }
        return descList;
    }
    public static List<String> getDescs(){
        List<String> descList = new LinkedList<>();
        for(ECourse eCourse : ECourse.values()){
            descList.add(eCourse.getDesc());
        }
        return descList;
    }


    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
