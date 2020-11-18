package com.relation.dao;

import com.relation.dao.utils.JdbcUtils;
import com.relation.pojo.*;

import javax.naming.BinaryRefAddr;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public BirthInformation getBirthInfo(Integer user_id) throws SQLException {
        try {
            getConnected();
            String sql = "select * from birth_information where `user_id`=?";
            st = conn.prepareStatement(sql);     //预编译
            st.setInt(1, user_id);
            rs = st.executeQuery();
            conn.commit();
            BirthInformation returnInfo = null;
            while (rs.next()) {
                returnInfo = new BirthInformation(Integer.parseInt(rs.getObject("id").toString()),
                        Integer.parseInt(rs.getObject("user_id").toString()),
                        Integer.parseInt(rs.getObject("patriarchal_family_id").toString()),
                        Integer.parseInt(rs.getObject("maternal_family_id").toString()),
                        Integer.parseInt(rs.getObject("year").toString()));
            }
            return returnInfo;
        } catch (SQLException e) {
            conn.rollback();
            return null;
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }

    public ArrayList<BirthInformation> getBrotherThroughFamilyId(Integer id) throws SQLException {
        try {
            getConnected();
            String sql = "select * from birth_information where `patriarchal_family_id`=? or `maternal_family_id`=?";
            st = conn.prepareStatement(sql);     //预编译
            st.setInt(1, id);
            st.setInt(2, id);
            rs = st.executeQuery();
            conn.commit();
            ArrayList<BirthInformation> brotherInfos=new ArrayList<>();
            while (rs.next()) {
                brotherInfos.add(new BirthInformation(Integer.parseInt(rs.getObject("id").toString()),
                        Integer.parseInt(rs.getObject("user_id").toString()),
                        Integer.parseInt(rs.getObject("patriarchal_family_id").toString()),
                        Integer.parseInt(rs.getObject("maternal_family_id").toString()),
                        Integer.parseInt(rs.getObject("year").toString())));
            }
            return brotherInfos;
        } catch (SQLException e) {
            conn.rollback();
            return null;
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }
}
