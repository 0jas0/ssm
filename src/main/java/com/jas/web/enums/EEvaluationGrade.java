package com.jas.web.enums;

public enum  EEvaluationGrade {
    DIFFERENCE(0,"差"),
    GENERAL(1,"一般"),
    GOOD(2,"好"),
    VERY_GOOD(3,"很好"),
    NICE(4,"非常好");

    EEvaluationGrade(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private Integer value;
    private String desc;



    public static EEvaluationGrade findByValue(Integer value){
        switch (value){
            case 0:
                return DIFFERENCE;
            case 1:
                return GENERAL;
            case 2:
                return GOOD;
            case 3:
                return VERY_GOOD;
            case 4:
                return NICE;
            default:
                return null;
        }
    }

    public static String getDescByValue(Integer value){
        EEvaluationGrade eSex = findByValue(value);
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
