package com.jas.web.enums;

public enum  ECourseType {
    ELECTIVE(0,"选修"),
    REQUIRED(1,"必修");

    ECourseType(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private Integer value;
    private String desc;



    public static ECourseType findByValue(Integer value){
        switch (value){
            case 0:
                return ELECTIVE;
            case 1:
                return REQUIRED;
            default:
                return null;
        }
    }

    public static String getDescByValue(Integer value){
        ECourseType eSex = findByValue(value);
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
