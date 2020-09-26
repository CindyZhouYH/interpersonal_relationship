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

    private void getConnected() throws SQLException {
        conn= JdbcUtils.getConnection();
    }

    public boolean addInfo(User user, School sch, EntranceInformation ei) throws SQLException {
        try{
            getConnected();
            String sql="insert into entranceinformation(`id`,`user_id`,`schoool_id`,`year`)values(?,?,?,?)";
            st=conn.prepareStatement(sql);     //预编译
            st.setInt(1,ei.getId());
            st.setInt(2,user.getId());
            st.setInt(3,sch.getId());
            st.setInt(4,ei.getYear());
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
            ArrayList allEntranceInfo=new ArrayList();
            while(rs.next()){
                EntranceInformation newEI=new EntranceInformation(Integer.parseInt(rs.getObject("id").toString()),
                        Integer.parseInt(rs.getObject("user_id").toString()),
                        Integer.parseInt(rs.getObject("school_id").toString()),
                        Integer.parseInt(rs.getObject("year").toString()));
                allEntranceInfo.add(newEI);
            }
            return allEntranceInfo;
        }catch(SQLException e){
            conn.rollback();
            return null;
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}
