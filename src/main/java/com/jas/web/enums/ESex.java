package com.jas.web.enums;

public enum ESex {
    MAN(0,"男"),
    WOMAN(1,"女");

    ESex(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    private Integer value;
    private String desc;

    public static ESex findByValue(Integer value){
        switch (value){
            case 0:
                return MAN;
            case 1:
                return WOMAN;
            default:
                return null;
        }
    }

    public static String getDescByValue(Integer value){
        ESex eSex = findByValue(value);
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
