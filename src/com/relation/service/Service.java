package com.relation.service;

import com.relation.dao.*;
import com.relation.pojo.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class Service {

    public static class EntranceInformationService {
        private static dbEntranceInformation dbe = new dbEntranceInformation();

        public static boolean addInfo(User user, School school, EntranceInformation ei) throws SQLException {
            return dbe.addInfo(user, school, ei);
        }

        public static boolean deleteEntranceInformation(EntranceInformation ei) throws SQLException {
            return dbe.deleteEntranceInfo(ei);
        }

        public static ArrayList<EntranceInformation> getEntranceInformation(User user) throws SQLException {
            return dbe.searchEntranceInfo(user);
        }

        public static int getMaxId() throws SQLException {
            return dbe.getMaxId();
        }

        public static ArrayList<Integer> getClassmatesEntranceInfo(EntranceInformation e) throws SQLException {
            return dbe.getClassmatesEntranceInfo(e);
        }
    }

    public static class SchoolService {
        private static dbSchool dbs = new dbSchool();

        public static School searchSchool(String name) throws SQLException {
            return dbs.searchSchool(name);
        }
        public static School searchSchoolThrowId(int id) throws SQLException {
            return dbs.searchSchoolThrowId(id);
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

        public static User getUserThroughId(Integer id) throws SQLException {
            return dbuser.getUserThroughId(id);
        }
    }

    public static class FamilyService {
        private static dbFamily dbfamily = new dbFamily();

        public static boolean addFamily(Family b) throws SQLException {
            return dbfamily.addFamily(b);
        }

        public static Family searchFamily(String name,String place) throws SQLException {
            return dbfamily.searchFamily(name, place);
        }
    }

    public static class BirthInfoService {
        private static dbBirthInformation dbbirth = new dbBirthInformation();

        public static boolean addBirthInfo(BirthInformation b) throws SQLException {
            return dbbirth.addBirthInfo(b);
        }

        public static BirthInformation getBirthInfo(Integer user_id) throws SQLException {
            return dbbirth.getBirthInfo(user_id);
        }

        public static ArrayList<BirthInformation> getBrotherThroughFamilyId(Integer id) throws SQLException {
            return dbbirth.getBrotherThroughFamilyId(id);
        }
    }
}