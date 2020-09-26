package com.relation.pojo;

public class User {
    private static int totalNum=0;
    private int id;
    private String username;
    private String name;
    private String email;
    private String key;

    public User() {
    }

    public User( String username, String name, String email, String key) {
        totalNum++;
        this.id = totalNum;
        this.username = username;
        this.name = name;
        this.email = email;
        this.key = key;

    }
    public User(int id, String username, String name, String email, String key) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.key = key;
    }

    public static void setTotalNum(int totalNum) {
        User.totalNum = totalNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static int getTotalNum() {
        return totalNum;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
