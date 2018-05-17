package com.jas.web.enums;

import java.util.LinkedList;
import java.util.List;

public enum ECourseSemester {
    FRESHMAN_UPPER(1,"大一上学期"),
    FRESHMAN_LOWER(2,"大一下学期"),
    SOPHOMORE_UPPER(3,"大二上学期"),
    SOPHOMORE_LOWER(4,"大二下学期"),
    JUNIOR_UPPER(5,"大三上学期"),
    JUNIOR_LOWER(6,"大三下学期"),
    SENIOR_UPPER(7,"大四上学期"),
    SENIOR_LOWER(8,"大四下学期");

    ECourseSemester(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private Integer value;
    private String desc;



    public static ECourseSemester findByValue(Integer value){
        switch (value){
            case 1:
                return FRESHMAN_UPPER;
            case 2:
                return FRESHMAN_LOWER;
            case 3:
                return SOPHOMORE_UPPER;
            case 4:
                return SOPHOMORE_LOWER;
            case 5:
                return JUNIOR_UPPER;
            case 6:
                return JUNIOR_LOWER;
            case 7:
                return SENIOR_UPPER;
            case 8:
                return SENIOR_LOWER;
            default:
                return null;
        }
    }

    public static String getDescByValue(Integer value){
        ECourseSemester eSex = findByValue(value);
        if (eSex == null){
            return "";
        }
        return eSex.getDesc();
    }

    public static List<Integer> getValues(){
        List<Integer> descList = new LinkedList<>();
        for(ECourseSemester eCourse : ECourseSemester.values()){
            descList.add(eCourse.getValue());
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
