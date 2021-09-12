package com.hngy.zp.notepad.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil_NotePad {
    static String url = "jdbc:mysql://47.112.233.235:3306/Notepad_zp?useUnicode=true&characterEncoding=UTF-8";
    static String userName = "zp1";
    static String passWord = "123456zp";

    // TODO Auto-generated method stub
    // 1. 在项目的构建路径中加入第三方包
    // 2. 加驱动
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getConnection();
            }
        }).start();
    }

    public static Connection getConnection() {

        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // 3. 通过驱动程序管理器类获得连接对象

            conn = DriverManager.getConnection(url, userName, passWord);
            if (conn != null) {
                System.out.println("连接成功！");
            } else {
                System.out.println("连接失败！");
            }


        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException se) {
            System.out.println(se.toString());
        }
        return conn;
    }

    // 关闭连接、语句及结果集对象
    public static void ClossAll(Connection conn, Statement stmt, ResultSet rs) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
