package com.leo.util.lang;

/**
 * 周一到周日的数字代码为1-7
 * 如果数字为0-7的请+1
 * @author 刘绍林
 * @create 2017-10-16 0:58
 **/
public enum  WeekEnum {
    //周一
    MONDAY ("周一",1),
    //周二
    TUESDAY ("周二",2),
    //周三
    WEDNESDAY ("周三",3),
    //周四
    THURSDAY ("周四",4),
    //周五
    FRIDAY  ("周五",5),
    //周六
    SATURDAY( "周六",6),
    //周日
    SUNDAY  ("周日",7);

    private String name;
    private int number;

    WeekEnum(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public static String getNameByNumber(int number){
        for (WeekEnum week:WeekEnum.values()){
            if(number==week.getNumber()){
                return week.name;
            }
        }
        return "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
