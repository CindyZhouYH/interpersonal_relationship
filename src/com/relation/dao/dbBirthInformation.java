package com.relation.dao;

import com.relation.dao.utils.JdbcUtils;
import com.relation.pojo.*;

import javax.naming.BinaryRefAddr;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbBirthInformation {
    private Connection conn=null;
    private PreparedStatement st=null;
    private ResultSet rs=null;

    private void getConnected() throws SQLException {
        conn= JdbcUtils.getConnection();
        conn.setAutoCommit(false);
    }

    public boolean addBirthInfo(BirthInformation birthInformation) throws SQLException {
        try{
            getConnected();
            String sql="insert into birth_information(`id`,`user_id`, `patriarchal_family_id`, `maternal_family_id`, `year`)values(?,?,?,?,?)";
            st=conn.prepareStatement(sql);     //预编译
            st.setInt(1,birthInformation.getId());
            st.setInt(2,birthInformation.getUser_id());
            st.setInt(3,birthInformation.getPatriarchal_family_id());
            st.setInt(4,birthInformation.getMaternal_family_id());
            st.setInt(5,birthInformation.getYear());
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
}