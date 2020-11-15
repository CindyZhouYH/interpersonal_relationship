package com.relation.dao;

import com.relation.dao.utils.JdbcUtils;
import com.relation.pojo.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbFamily {
    private Connection conn=null;
    private PreparedStatement st=null;
    private ResultSet rs=null;

    private void getConnected() throws SQLException {
        conn= JdbcUtils.getConnection();
        conn.setAutoCommit(false);
    }

    public boolean addFamily(Family f) throws SQLException {
        try{
            getConnected();
            String sql="insert into family(`id`,`family_name`, `place`)values(?,?,?)";
            st=conn.prepareStatement(sql);     //预编译
            st.setInt(1,f.getId());
            st.setString(2,f.getFamily_name());
            st.setString(3,f.getPlace());
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

    public Family searchFamily(String name, String place) throws SQLException{
        try{
            getConnected();
            String sql="select * from family where `family_name`=? and `place`=?";
            st=conn.prepareStatement(sql);     //预编译
            st.setString(1, name);
            st.setString(2, place);
            rs=st.executeQuery();
            conn.commit();
            Family returnFamily=null;
            while(rs.next()){
                returnFamily=new Family(Integer.parseInt(rs.getObject("id").toString()), rs.getObject("family_name").toString(), rs.getObject("place").toString());
            }
            return returnFamily;
        }catch(SQLException e){
            conn.rollback();
            return null;
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}
