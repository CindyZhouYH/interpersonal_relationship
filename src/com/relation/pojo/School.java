package com.relation.pojo;

public class School {
    private static int totalNum=0;
    private int id;
    private String name;
    private int level; //1:幼儿园；2:小学；3：初中；4：高中；5：大学

    public School(String name, int level) {
        totalNum++;
        this.id=totalNum;
        this.name = name;
        this.level = level;
    }

    public School(int id, String name, int level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public static int getTotalNum() {
        return totalNum;
    }

    public static void setTotalNum(int totalNum) {
        School.totalNum = totalNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
