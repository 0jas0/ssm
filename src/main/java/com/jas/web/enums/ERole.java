package com.jas.web.enums;

public enum ERole {
    STUDENT(0,"学生"),
    TEACHER(1,"老师");

    ERole(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private Integer value;
    private String desc;

    public static ERole findByValue(Integer value){
        switch (value){
            case 0:
                return STUDENT;
            case 1:
                return TEACHER;
            default:
                return null;
        }
    }

    public static String getDescByValue(Integer value){
        ERole eSex = findByValue(value);
        if (eSex == null){
            return "";
        }
        return eSex.getDesc();
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
