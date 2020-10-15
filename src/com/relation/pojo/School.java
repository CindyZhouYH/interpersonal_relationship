package com.relation.pojo;

import com.relation.service.Service;

import java.sql.SQLException;
import java.util.Arrays;

public class School {
    private static int totalNum=0;
    private int id;
    private String name;
    private int level; //1:幼儿园；2:小学；3：初中；4：高中；5：大学
    private String type;
    private static boolean updated = false;
    private String[] types={"Kindergarten", "Primary School", "Junior High School", "Senior High School", "University"};
    private void update() {
        try {
            if(!updated) {
                totalNum = Service.SchoolService.getMaxId();
                updated = true;
                System.out.println("updated school totalnum to " + totalNum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            totalNum = 0;
            System.out.println("error updating");
        }
    }

    public School(String name, int level) {
        update();
        totalNum++;
        this.id=totalNum;
        this.name = name;
        this.level = level;
        this.type=types[level-1];
    }

    public School(int id, String name, int level) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.type=types[level-1];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", type='" + type + '\'' +
                ", types=" + Arrays.toString(types) +
                '}';
    }
}
