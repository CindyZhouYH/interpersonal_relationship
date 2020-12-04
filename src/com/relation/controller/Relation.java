package com.relation.controller;

import com.relation.pojo.User;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class Relation {
    public static final int FRIEND = 1;
    public static final int FAMILY = 2;
    public static final int CLASSMATE = 3;

    private int user1;
    private int user2;
    private int type = 0;

    private User user_1;
    private User user_2;

    /*public Relation(int user1, int user2, int type) {
        this.user1 = user1;
        this.user2 = user2;
        this.type = type;
    }*/

    public Relation(User user1, User user2, int type) {
        this.user_1 = user1;
        this.user_2 = user2;
        this.user1 = user1.getId();
        this.user2 = user2.getId();
        this.type = type;
    }

    public User getUser_1() {
        return user_1;
    }

    public User getUser_2() {
        return user_2;
    }

    public int getUser1() {
        return user1;
    }

    public int getUser2() {
        return user2;
    }

    public int getType() {
        return type;
    }

    @Override
    public int hashCode() {
        return (min(user1, user2) * 299213 + max(user1, user2)) * 299213 + type;
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof Relation) {
            return  (
                    (((Relation) o).getUser1() == user1 && ((Relation) o).getUser2() == user2)
                            ||
                    (((Relation) o).getUser1() == user2 && ((Relation) o).getUser2() == user1)
                    )
                    &&
                    ((Relation) o).getType() == type;
        }
        return false;
    }

}
