package com.relation.dao;

import com.relation.dao.utils.JdbcUtils;
import com.relation.pojo.User;

import java.sql.*;

public class connect {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        // y哦那个互信息
        String url = "jdbc:mysql://127.0.0.1:3306/interpersonal_relationship?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String username="root";
        String password="root";
        // 连接

        Connection conn= JdbcUtils.getConnection();
        String sql="insert into user(`id`,`username`,`name`,`email`,`key`)values(?,?,?,?,?)";
        PreparedStatement st=conn.prepareStatement(sql);
        //写sql语句
        User user=new User("cindy917","Yuanhang Zhou","18678902738@163.com","123456");
        st=conn.prepareStatement(sql);     //预编译
        st.setInt(1,user.getId());
        st.setString(2,user.getUsername());
        st.setString(3,user.getName());
        st.setString(4,user.getEmail());
        st.setString(5,user.getKey());
        st.executeUpdate();
        st.close();
        conn.close();
    }
}
