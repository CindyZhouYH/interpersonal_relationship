package com.relation.service;

import com.relation.dao.*;
import com.relation.pojo.User;

import java.sql.SQLException;

public class UserService {
    private dbUser dbuser=new dbUser();

    public int addUser(User user) throws SQLException {
        return dbuser.addUser(user);
    }
}
