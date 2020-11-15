package com.relation.pojo;

public class BirthInformation {
    private static int totalNum = 0;
    private int id;
    private int user_id;
    private int patriarchal_family_id;
    private int maternal_family_id;
    private int year;

    public BirthInformation() {
    }

    public BirthInformation(int user_id, int patriarchal_family_id, int maternal_family_id, int year) {
        totalNum++;
        this.id = totalNum;
        this.user_id = user_id;
        this.patriarchal_family_id = patriarchal_family_id;
        this.maternal_family_id = maternal_family_id;
        this.year = year;
    }

    public BirthInformation(int id, int user_id, int patriarchal_family_id, int maternal_family_id, int year) {
        this.id = id;
        this.user_id = user_id;
        this.patriarchal_family_id = patriarchal_family_id;
        this.maternal_family_id = maternal_family_id;
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

    public int getPatriarchal_family_id() {
        return patriarchal_family_id;
    }

    public void setPatriarchal_family_id(int patriarchal_family_id) {
        this.patriarchal_family_id = patriarchal_family_id;
    }

    public int getMaternal_family_id() {
        return maternal_family_id;
    }

    public void setMaternal_family_id(int maternal_family_id) {
        this.maternal_family_id = maternal_family_id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
