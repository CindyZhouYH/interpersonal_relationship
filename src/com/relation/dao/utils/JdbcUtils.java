package com.relation.dao.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils{
    private static String driver=null;
    private static String url=null;
    private static String username=null;
    private static String password=null;

    static{
        try {
            driver = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://127.0.0.1:3306/interpersonal_relationship?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            username = "root";
            password = "ZYHmysql917";
            Class.forName(driver);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

    public static void release(Connection con, Statement st, ResultSet re) throws SQLException {
        if(re!=null){
            re.close();
        }
        if(st!=null){
            st.close();
        }
        if(con!=null){
            con.close();
        }
    }

}
