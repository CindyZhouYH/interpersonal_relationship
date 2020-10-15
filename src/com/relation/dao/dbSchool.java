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
            School returnSchool=null;
            System.out.println(rs);
            System.out.println(returnSchool);
            while(rs.next()){
                System.out.println("1");
                returnSchool=new School(Integer.parseInt(rs.getObject("id").toString()),
                        rs.getObject("name").toString(),
                        Integer.parseInt(rs.getObject("level").toString()));
            }
            return returnSchool;
        }catch(SQLException e){
            conn.rollback();
            return null;
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }

    public School searchSchoolThrowId(int id) throws SQLException {
        try{
            System.out.println("seartching");
            getConnected();
            String sql="select * from school where `id`=?";
            st=conn.prepareStatement(sql);     //预编译
            st.setInt(1, id);
            rs=st.executeQuery();
            conn.commit();
            School returnSchool=null;
            System.out.println(rs);
            while(rs.next()) {
                returnSchool = new School(Integer.parseInt(rs.getObject("id").toString()),
                        rs.getObject("name").toString(),
                        Integer.parseInt(rs.getObject("level").toString()));
            }
            System.out.println("out");
            System.out.println(returnSchool.toString());
            return returnSchool;
        }catch(SQLException e){
            System.out.println("error");
            conn.rollback();
            return null;
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }

    public int getCount() throws SQLException {
        try {
            getConnected();
            String sql = "select count(*) from school";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            conn.commit();
            if (!rs.next()) {
                System.out.println("rs.next = false");
                return 0;
            }
            int count = rs.getInt(1);
            System.out.println("school sql count = " + count);
            return count;
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("school count sqlException");
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
            String sql = "select max(id) from school";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            conn.commit();
            rs.next();
            int maxId = rs.getInt(1);
            System.out.println("school maxid sql maxid = " + maxId);
            return maxId;
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("school maxid sqlException, set to 0");
            return 0;
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }

    private void getConnected() throws SQLException {
        conn= JdbcUtils.getConnection();
        conn.setAutoCommit(false);
    }
}
