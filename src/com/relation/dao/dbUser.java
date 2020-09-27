package com.relation.dao;

import com.relation.dao.utils.JdbcUtils;
import com.relation.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 进行有关User-table的增删查改工作，并返回结果
public class dbUser {
    private Connection conn=null;
    private PreparedStatement st=null;
    private ResultSet rs=null;

    public int addUser(User user) throws SQLException {
        try{
            getConnected();
            System.out.println("connect over");
            String sql="insert into user(`id`,`username`,`name`,`email`,`key`)values(?,?,?,?,?)";
            st=conn.prepareStatement(sql);     //预编译
            System.out.println(st);
            st.setInt(1,user.getId());
            st.setString(2,user.getUsername());
            st.setString(3,user.getName());
            st.setString(4,user.getEmail());
            st.setString(5,user.getKey());
            System.out.println(st);
            st.executeUpdate();
            conn.commit();
            return 1;
        }catch(SQLException e){
            conn.rollback();
            return 0;
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }

    public boolean deleteUser(User user) throws SQLException {
        try{
            getConnected();
            String sql="delete from user where `username`=?";
            st=conn.prepareStatement(sql);     //预编译
            st.setString(1,user.getUsername());
            st.executeUpdate();
            conn.commit();
            return true;
        }catch(SQLException e){
            conn.rollback();
            return false;
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }

    public boolean upDateUserKey(User user) throws SQLException {
        try{
            getConnected();
            String sql="update user set `key`=? where `id`=?";
            st=conn.prepareStatement(sql);     //预编译
            st.setString(1,user.getKey());
            st.setInt(2,user.getId());
            st.executeUpdate();
            conn.commit();
            return true;
        }catch(SQLException e){
            conn.rollback();
            return false;
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }

    public User searchUser(String username) throws SQLException {
        try{
            getConnected();
            String sql="select * from user where `username`=?";
            st=conn.prepareStatement(sql);     //预编译
            st.setString(1,username);
            rs=st.executeQuery();
            conn.commit();
            User returnUser=new User(Integer.parseInt(rs.getObject("id").toString()),
                    rs.getObject("username").toString(),
                    rs.getObject("name").toString(),
                    rs.getObject("email").toString(),
                    rs.getObject("key").toString());
            return returnUser;
        }catch(SQLException e){
            conn.rollback();
            return null;
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }

    private void getConnected() throws SQLException {
        conn= JdbcUtils.getConnection();
        conn.setAutoCommit(false);
    }
}
