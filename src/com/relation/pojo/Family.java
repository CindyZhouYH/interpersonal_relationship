package com.relation.pojo;

public class Family {
    private static int totalNum = 0;
    private int id;
    private String family_name;
    private String place;

    public Family() {
    }

    public Family(String family_name, String place) {
        totalNum++;
        this.id = totalNum;
        this.family_name = family_name;
        this.place = place;
    }

    public Family(int id, String family_name, String place) {
        this.id = id;
        this.family_name = family_name;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
