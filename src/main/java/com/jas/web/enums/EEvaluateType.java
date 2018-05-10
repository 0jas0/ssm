package com.jas.web.enums;

public enum EEvaluateType {
    UNASSESS(0,"未评价"),
    EXCELLENT(1, "优"),
    GOOD(2, "良"),
    AVERAGE(2, "中"),
    POOR(3, "差");

    private final int value;
    private final String desc;

    private EEvaluateType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static int getValueByDesc(String desc){
        switch (desc){
            case "优":
                return EEvaluateType.EXCELLENT.getValue();
            case "良":
                return EEvaluateType.GOOD.getValue();
            case "中":
                return EEvaluateType.AVERAGE.getValue();
            case "差":
                return EEvaluateType.POOR.getValue();
            default:
                return EEvaluateType.UNASSESS.getValue();
        }
    }
    public static EEvaluateType findByValue(int value) {
        switch (value) {
            case 1:
                return EXCELLENT;
            case 2:
                return GOOD;
            case 3:
                return AVERAGE;
            case 4:
                return POOR;
            default:
                return UNASSESS;
        }
    }

    public static String getDescByValue(int value) {
        EEvaluateType byValue = findByValue(value);
        if(byValue==null){
            return "";
        }
        return byValue.getDesc();
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
