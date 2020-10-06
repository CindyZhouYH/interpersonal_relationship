package com.relation.pojo;

import com.relation.service.Service;

import java.sql.SQLException;

public class User {
    private static int totalNum = 0;
    private int id;
    private String username;
    private String name;
    private String email;
    private String key;
    private static boolean updated = false;

    private void update() {
        try {
            if(!updated) {
                totalNum = Service.UserService.getMaxId();
                updated = true;
                System.out.println("updated user totalnum to " + totalNum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            totalNum = 0;
            System.out.println("error updating");
        }
    }

    public User() {
        update();
    }

    public User(String username, String name, String email, String key) {
        update();
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
