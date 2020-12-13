package com.relation.dao;

import com.relation.dao.utils.JdbcUtils;
import com.relation.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class dbFriendship {

    private Connection conn = null;
    private PreparedStatement st = null;
    private ResultSet rs = null;

    private void getConnected() throws SQLException {
        conn = JdbcUtils.getConnection();
        conn.setAutoCommit(false);
    }

    public void addFriendship(int id1, int id2) throws SQLException {
        try {
            //System.out.println("adding friendship between " + id1 + " and " + id2);
            getConnected();
            String sql = "insert into friendship(`user1_id`,`user2_id`)values(?,?)";
            st = conn.prepareStatement(sql);     //预编译
            //System.out.println(st);
            st.setInt(1, id1);
            st.setInt(2, id2);
            st.executeUpdate();
            conn.commit();
            //System.out.println("successfully added friendship");
            return;
        } catch (SQLException e) {
            conn.rollback();
            //System.out.println("failed to add friendship");
            return;
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }

    public HashSet<Integer> getAllFriends(int id) throws SQLException {
        try {
            getConnected();
            String sql = "select * from friendship where `user1_id` = ? or `user2_id`=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, id);   //预编译
            //System.out.println(" - " + sql);
            rs = st.executeQuery();
            conn.commit();
            HashSet<Integer> friends=new HashSet<>();
            while(rs.next()){
                friends.add(Integer.parseInt(rs.getObject("user1_id").toString()));
                friends.add(Integer.parseInt(rs.getObject("user2_id").toString()));
            }
            friends.remove(id);
            //System.out.println("all friends of user " + id + "");
            return friends;
        } catch (SQLException e) {
            conn.rollback();
            return null;
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }

}
