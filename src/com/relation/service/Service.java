package com.relation.service;

import com.relation.dao.dbEntranceInformation;
import com.relation.dao.dbSchool;
import com.relation.dao.dbUser;
import com.relation.pojo.EntranceInformation;
import com.relation.pojo.School;
import com.relation.pojo.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class Service {

    public static class EntranceInformationService {
        private static dbEntranceInformation dbe = new dbEntranceInformation();

        public static boolean addInfo(User user, School school, EntranceInformation ei) throws SQLException {
            return dbe.addInfo(user, school, ei);
        }

        public static ArrayList<EntranceInformation> getEntranceInformation(User user) throws SQLException {
            return dbe.searchEntranceInfo(user);
        }

        public static int getMaxId() throws SQLException {
            return dbe.getMaxId();
        }
    }

    public static class SchoolService {
        private static dbSchool dbs = new dbSchool();

        public static School searchSchool(String name) throws SQLException {
            return dbs.searchSchool(name);
        }

        public static boolean addSchool(School school) throws SQLException {
            return dbs.addSchool(school);
        }

        public static int getMaxId() throws SQLException {
            return dbs.getMaxId();
        }
    }

    public static class UserService {
        private static dbUser dbuser = new dbUser();

        public static int addUser(User user) throws SQLException {
            return dbuser.addUser(user);
        }

        public static boolean deleteUser(String username) throws SQLException {
            return dbuser.deleteUser(username);
        }

        public static User searchUser(String username) throws SQLException {
            return dbuser.searchUser(username);
        }

        public static boolean updateUserKey(String key, int id) throws SQLException {
            return dbuser.upDateUserKey(key,id);
        }
        public static boolean updateUserUsername(String name, int id) throws SQLException {
            return dbuser.upDateUserUsername(name,id);
        }

        public static boolean searchSameEmailUser(String email) throws SQLException {
            return dbuser.searchSameEmailUser(email);
        }

        public static int getMaxId() throws SQLException {
            return dbuser.getMaxId();
        }
    }
}
