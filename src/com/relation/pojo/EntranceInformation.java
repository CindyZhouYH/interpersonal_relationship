package com.relation.pojo;

public class EntranceInformation {
    private static int totalNum=5;
    private int id;
    private int user_id;
    private int school_id;
    private int year;

    public EntranceInformation(int user_id, int school_id, int year) {
        totalNum++;
        this.id=totalNum;
        this.user_id = user_id;
        this.school_id = school_id;
        this.year = year;
    }

    public EntranceInformation(int id, int user_id, int school_id, int year) {
        this.id = id;
        this.user_id = user_id;
        this.school_id = school_id;
        this.year = year;
    }

    public static int getTotalNum() {
        return totalNum;
    }

    public static void setTotalNum(int totalNum) {
        EntranceInformation.totalNum = totalNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
