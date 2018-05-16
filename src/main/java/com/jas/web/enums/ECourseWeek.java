package com.jas.web.enums;

import java.util.LinkedList;
import java.util.List;

public enum ECourseWeek {
    MONDAY(1,"周一"),
    TUESDAY(2,"周二"),
    WEDNESDAY(3,"周三"),
    THURSDAY(4,"周四"),
    FRIDAY(5,"周五"),
    SATURDAY(6,"周六"),
    SUNDAY(7,"周日");

    ECourseWeek(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private Integer value;
    private String desc;



    public static ECourseWeek findByValue(Integer value){
        switch (value){
            case 1:
                return MONDAY;
            case 2:
                return TUESDAY;
            case 3:
                return WEDNESDAY;
            case 4:
                return THURSDAY;
            case 5:
                return FRIDAY;
            case 6:
                return SATURDAY;
            case 7:
                return SUNDAY;
            default:
                return null;
        }
    }

    public static String getDescByValue(Integer value){
        ECourseWeek eSex = findByValue(value);
        if (eSex == null){
            return "";
        }
        return eSex.getDesc();
    }

    public static List<Integer> getValues(){
        List<Integer> courseWeek = new LinkedList<>();
        for(ECourseWeek eCourseWeek : ECourseWeek.values()){
            courseWeek.add(eCourseWeek.getValue());
        }
        return courseWeek;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
