package com.relation.dao;

import com.relation.dao.utils.JdbcUtils;
import com.relation.pojo.School;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbSchool {
    private Connection conn=null;
    private PreparedStatement st=null;
    private ResultSet rs=null;

    public boolean addSchool(School school) throws SQLException {
        try{
            getConnected();
            String sql="insert into school(`id`,`name`,`level`)values(?,?,?)";
            st=conn.prepareStatement(sql);     //预编译
            st.setInt(1,school.getId());
            st.setString(2,school.getName());
            st.setInt(3,school.getLevel());
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

    public School searchSchool(String name) throws SQLException {
        try{
            getConnected();
            String sql="select * from school where `name`=?";
            st=conn.prepareStatement(sql);     //预编译
            st.setString(1, name);
            rs=st.executeQuery();
            conn.commit();
            School returnSchool=new School(Integer.parseInt(rs.getObject("id").toString()),
                    rs.getObject("name").toString(),
                    Integer.parseInt(rs.getObject("level").toString()));
            return returnSchool;
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
