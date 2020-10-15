package com.relation.dao;

import com.relation.dao.utils.JdbcUtils;
import com.relation.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.SynchronousQueue;

// 进行有关User-table的增删查改工作，并返回结果
public class dbUser {
    private Connection conn = null;
    private PreparedStatement st = null;
    private ResultSet rs = null;

    public int addUser(User user) throws SQLException {
        try {
            if (searchSameEmailUser(user.getEmail())) {
                return 0;
            }
            getConnected();
            System.out.println("connect over");
            String sql = "insert into user(`id`,`username`,`name`,`email`,`key`)values(?,?,?,?,?)";
            st = conn.prepareStatement(sql);     //预编译
            System.out.println(st);
            st.setInt(1, user.getId());
            st.setString(2, user.getUsername());
            st.setString(3, user.getName());
            st.setString(4, user.getEmail());
            st.setString(5, user.getKey());
            System.out.println(st);
            st.executeUpdate();
            conn.commit();
            return 1;
        } catch (SQLException e) {
            conn.rollback();
            return 0;
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }

    public boolean deleteUser(String username) throws SQLException {
        try {
            System.out.println("start delete");
            getConnected();
            int user_id = 0;
            // get user id
            String sql;
            sql = "select * from user where `username`=?";
            st = conn.prepareStatement(sql);
            st.setString(1, username);
            rs = st.executeQuery();
            conn.commit();
            while (rs.next()) {
                user_id = Integer.parseInt(rs.getObject("id").toString());
            }
            // delete user
            System.out.println("user");
            sql = "delete from user where `username`=?";
            st = conn.prepareStatement(sql);     //预编译
            st.setString(1, username);
            st.executeUpdate();
            // delete ei
            System.out.println("entrance");
            sql = "delete from entranceinformation where `user_id`=?";
            st = conn.prepareStatement(sql);     //预编译
            st.setInt(1, user_id);
            st.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
            return false;
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }

    public boolean upDateUserKey(String key, int id) throws SQLException {
        try {
            getConnected();
            String sql = "update user set `key`=? where `id`=?";
            st = conn.prepareStatement(sql);     //预编译
            st.setString(1, key);
            st.setInt(2,id);
            st.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            conn.rollback();
            return false;
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
    public boolean upDateUserUsername(String name, int id) throws SQLException {
        try {
            getConnected();
            String sql = "update user set `username`=? where `id`=?";
            st = conn.prepareStatement(sql);     //预编译
            st.setString(1, name);
            st.setInt(2, id);
            st.executeUpdate();
            conn.commit();
            return true;
        } catch (SQLException e) {
            conn.rollback();
            return false;
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }


    public User searchUser(String username) throws SQLException {
        try {
            getConnected();
            String sql = "select * from user where `username`=?";
            st = conn.prepareStatement(sql);     //预编译
            st.setString(1, username);
            //System.out.println(st);
            rs = st.executeQuery();
            conn.commit();
            System.out.println(rs);
            User returnUser = null;
            while (rs.next()) {
                returnUser = new User(Integer.parseInt(rs.getObject("id").toString()),
                        rs.getObject("username").toString(),
                        rs.getObject("name").toString(),
                        rs.getObject("email").toString(),
                        rs.getObject("key").toString());
            }
            return returnUser;
        } catch (SQLException e) {
            conn.rollback();
            return null;
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }

    public boolean searchSameEmailUser(String email) throws SQLException {
        // have same email user: true
        // else: false
        try {
            getConnected();
            String sql = "select * from user where `email`=?";
            st = conn.prepareStatement(sql);     //预编译
            st.setString(1, email);
            rs = st.executeQuery();
            conn.commit();
            boolean result = rs.next();
            System.out.println(result);
            return result;
        } catch (SQLException e) {
            conn.rollback();
            return true;
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }

    public int getCount() throws SQLException {
        try {
            getConnected();
            String sql = "select count(*) from user";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            conn.commit();
            if (!rs.next()) {
                System.out.println("rs.next = false");
                return 0;
            }
            int count = rs.getInt(1);
            System.out.println("user count = " + count);
            return count;
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("user count sqlException");
            return 0;
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }

    public int getMaxId() throws SQLException {
        try {
            if (getCount() == 0) {
                return 0;
            }
            getConnected();
            System.out.println("1");
            String sql = "select max(id) from user";
            st = conn.prepareStatement(sql);
            System.out.println("2");
            rs = st.executeQuery();
            System.out.println("3");
            conn.commit();
            rs.next();
            int maxId = rs.getInt(1);
            System.out.println("user maxid = " + maxId);
            return maxId;
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("user maxid sqlException, set to 0");
            return 0;
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }

    private void getConnected() throws SQLException {
        conn = JdbcUtils.getConnection();
        conn.setAutoCommit(false);
    }
}
