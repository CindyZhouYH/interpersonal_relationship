package com.relation.dao;

import java.sql.*;

public class connect {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        // y哦那个互信息
        String url = "jdbc:mysql://127.0.0.1:3306/ssmbuild?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        String username="root";
        String password="ZYHmysql917";
        // 连接
        Connection connection=DriverManager.getConnection(url,username,password);
        Statement statement=connection.createStatement();
        //写sql语句
        String sqlInsert="insert into ";
        String sql="SELECT * FROM books";
        //返回结果集
        ResultSet result=statement.executeQuery(sql);
        while(result.next()){
            System.out.println("id="+result.getObject("bookID"));
            System.out.println("name="+result.getObject("bookName"));
        }
        //释放
        result.close();
        statement.close();
        connection.close();
    }
}
