package com.relation.service;

import com.relation.dao.*;
import com.relation.pojo.*;

import java.sql.SQLException;

public class SchoolService {
    private dbSchool dbs=new dbSchool();
    private dbEntranceInformation dbe=new dbEntranceInformation();

    public School searchSchool(String name) throws SQLException {
        return dbs.searchSchool(name);
    }
    public boolean addSchool(School school) throws SQLException {
        return dbs.addSchool(school);
    }
    public boolean addInfo(User user, School school, EntranceInformation ei) throws SQLException {
        return dbe.addInfo(user, school, ei);
    }
}
