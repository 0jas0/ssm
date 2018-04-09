package com.jas.web.utils;

import java.sql.*;
import javax.swing.JOptionPane;
public class DbOp{
    private static String url="jdbc:mysql://123.206.53.60:3306/bookdb?characterEncoding=UTF-8";
    private static String user = "root";
    private static String password = "juan0911";
    private static Connection con=null;
    private DbOp(){
        try{
            if(con==null){
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection(url,user,password);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"数据库未能打开！");
            System.out.println(e.getMessage());
        }
    }
    public static ResultSet executeQuery(String sql){
        ResultSet rs=null;
        try{
            if(con==null){
                new DbOp();
            }
            rs=con.createStatement().executeQuery(sql);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"数据查询失败！");
            rs=null;
        }
        return rs;
    }
    public static int executeUpdate(String sql){
        int a=0;
        try{
            if(con==null){
                new DbOp();
            }
            a= con.createStatement().executeUpdate(sql);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"数据库更新失败！");
            a= -1;
        }
        return a;
    }
    public static void close(){
        try{
            if(con!=null){

                con.close();
                con=null;
                //JOptionPane.showMessageDialog(null,"数据库已关闭！");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"数据库未打开！");
        }
    }

    public static void main(String[] args) {
        executeQuery("select * from user");
    }
}