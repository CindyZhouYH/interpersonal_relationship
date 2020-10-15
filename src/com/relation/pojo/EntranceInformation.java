package com.relation.pojo;

import com.relation.service.Service;

import java.sql.SQLException;

public class EntranceInformation {
    private static int totalNum=5;
    private int id;
    private int user_id;
    private int school_id;
    private int year;
    private String schoolName;
    private String schoolType;
    private static boolean updated = false;

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    private void update() {
        try {
            if(!updated) {
                totalNum = Service.EntranceInformationService.getMaxId();
                updated = true;
                System.out.println("updated EntranceInformation totalnum to " + totalNum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            totalNum = 0;
            System.out.println("error updating");
        }
    }

    public EntranceInformation(int user_id, int school_id, int year) {
        update();
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

    @Override
    public String toString() {
        return "EntranceInformation{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", school_id=" + school_id +
                ", year=" + year +
                ", schoolName='" + schoolName + '\'' +
                ", schoolType='" + schoolType + '\'' +
                '}';
    }
}
