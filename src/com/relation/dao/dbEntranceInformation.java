package com.relation.dao;

import com.relation.dao.utils.JdbcUtils;
import com.relation.pojo.EntranceInformation;
import com.relation.pojo.School;
import com.relation.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// 进行有关entranceInformation-table的增删查改工作，并返回结果
public class dbEntranceInformation {
    private Connection conn=null;
    private PreparedStatement st=null;
    private ResultSet rs=null;

    public boolean addInfo(User user, School sch, EntranceInformation ei) throws SQLException {
        try{
            getConnected();
            String sql="insert into entranceinformation(`id`,`user_id`,`school_id`,`year`)values(?,?,?,?)";
            st=conn.prepareStatement(sql);     //预编译
            System.out.println(st);
            System.out.println(ei.getId());
            System.out.println(user.getId());
            System.out.println(sch.getId());
            System.out.println(ei.getYear());
            st.setInt(1,ei.getId());
            st.setInt(2,user.getId());
            st.setInt(3,sch.getId());
            st.setInt(4,ei.getYear());
            System.out.println(st);
            st.executeUpdate();
            System.out.println(st);
            conn.commit();
            return true;
        }catch(SQLException e){
            conn.rollback();
            return false;
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }

    public boolean deleteEntranceInfo(EntranceInformation ei) throws SQLException {
        try{
            getConnected();
            String sql="delete from entranceinformation where `id`=?";
            st=conn.prepareStatement(sql);     //预编译
            st.setInt(1,ei.getId());
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

    public ArrayList<EntranceInformation> searchEntranceInfo(User user) throws SQLException {
        try{
            getConnected();
            String sql="select * from entranceinformation where `user_id`=?";
            st=conn.prepareStatement(sql);     //预编译
            st.setInt(1,user.getId());
            rs=st.executeQuery();
            conn.commit();
            System.out.println("get all entrances");
            ArrayList allEntranceInfo=new ArrayList();
            while(rs.next()){
                EntranceInformation newEI=new EntranceInformation(Integer.parseInt(rs.getObject("id").toString()),
                        Integer.parseInt(rs.getObject("user_id").toString()),
                        Integer.parseInt(rs.getObject("school_id").toString()),
                        Integer.parseInt(rs.getObject("year").toString()));
                allEntranceInfo.add(newEI);
            }
            System.out.println(allEntranceInfo);
            return allEntranceInfo;
        }catch(SQLException e){
            conn.rollback();
            return null;
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }

    public int getCount() throws SQLException {
        try {
            getConnected();
            String sql = "select count(*) from entranceinformation";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            conn.commit();
            if (!rs.next()) {
                System.out.println("rs.next = false");
                return 0;
            }
            int count = rs.getInt(1);
            System.out.println("entranceinformation count = " + count);
            return count;
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("entranceinformation count sqlException");
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
            String sql = "select max(id) from entranceinformation";
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();
            conn.commit();
            rs.next();
            int maxId = rs.getInt(1);
            System.out.println("entranceinformation maxid = " + maxId);
            return maxId;
        } catch (SQLException e) {
            conn.rollback();
            System.out.println("entranceinformation maxid sqlException, set to 0");
            return 0;
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }

    private void getConnected() throws SQLException {
        conn= JdbcUtils.getConnection();
        conn.setAutoCommit(false);
    }

    public ArrayList<Integer> getClassmatesEntranceInfo(EntranceInformation e) throws SQLException {
        try{
            getConnected();
            String sql="select * from entranceinformation where `school_id`=? and `year`=?";
            st=conn.prepareStatement(sql);     //预编译
            st.setInt(1,e.getSchool_id());
            st.setInt(1,e.getYear());
            rs=st.executeQuery();
            conn.commit();
            ArrayList<Integer> allClassmatesId=new ArrayList<>();
            while(rs.next()){
                allClassmatesId.add(Integer.parseInt(rs.getObject("id").toString()));
            }
            return allClassmatesId;
        }catch(SQLException exc){
            conn.rollback();
            return null;
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}
