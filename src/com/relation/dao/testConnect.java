package com.relation.dao;

import com.relation.dao.utils.JdbcUtils;

import java.sql.*;

public class testConnect {
    public static void main(String[] args) throws SQLException {
        Connection conn=null;
        PreparedStatement st=null;
        ResultSet rs=null;
        try{
            conn= JdbcUtils.getConnection();
            //st=conn.createStatement();
            conn.setAutoCommit(false);

            String sql="insert into books(`bookID`,`bookName`,`bookCounts`,`detail`)values(?,?,?,?)";
            st=conn.prepareStatement(sql);     //预编译
            st.setInt(1,123);
            st.setString(2,"我的儿子");
            st.setInt(3,0);
            st.setString(4,"王思琪是我的儿子");
            int i=st.executeUpdate();
            if(i>0){
                System.out.println("插入成功");
            }
            sql="delete from books where bookId=?";
            st=conn.prepareStatement(sql);     //预编译
            st.setInt(1,123);
            i=st.executeUpdate();
            if(i>0){
                System.out.println("删除成功");
            }
            sql="select * from books";
            st=conn.prepareStatement(sql);     //预编译
            st.executeQuery();
            ResultSet result=st.executeQuery(sql);
            while(result.next()){
                System.out.println("id="+result.getObject("bookID"));
                System.out.println("name="+result.getObject("bookName"));
            }
            conn.commit();
        }catch(SQLException e){
            conn.rollback();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }
    }
}
