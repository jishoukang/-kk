package com.lib.mangement.Common.dao;

import com.lib.mangement.Common.entity.Admin;
import com.lib.mangement.Common.entity.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommonDao {

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanage", "root", "12345");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return con;
    }

    public static void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static int executeUpdate(String sql, Object... params) throws SQLException {
        int rowAffect = 0;

        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
        }
        rowAffect = pstmt.executeUpdate();
        closeAll(null, pstmt, con);

        return rowAffect;

    }

    public static <T> List<T> executeQuery(String sql, Class<T> clazz, Object... params) throws Exception {

        List<T> list = new ArrayList<T>();
        Connection con = getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
        }
        ResultSet rs = pstmt.executeQuery();

        //获取结果集的元数据
        ResultSetMetaData rsmd = rs.getMetaData();
        //从结果的元数据中获取结果集的元数据，元数据就是结果集的列信息
        List<String> columnNames = new ArrayList<String>();//存储结果的列头的名字
        for (int i = 0; i < rsmd.getColumnCount(); i++) {
            columnNames.add(rsmd.getColumnLabel(i + 1));
        }

        while (rs.next()) {
            //实例化T类型的实体对象
            T t = clazz.newInstance();
            //用反射调用t中的set方法
            for (String columnName : columnNames) {
                //类似于setId setUserName setUserPassword
                String setterName = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                Method[] methods = clazz.getDeclaredMethods();
                for (Method m : methods) {

                    if (m.getName().equalsIgnoreCase(setterName))
                         try {
                             m.invoke(t, rs.getObject(columnName));
                           }catch(Exception e){
                             e.printStackTrace();
                           }

                }
            }
            list.add(t);
        }
        return list;
    }
     // 从文件获取登录用户信息，转为User对象
    public static User getUserFromFile(){
        try {
           FileInputStream infs=new FileInputStream(new File("user.txt"));
            ObjectInputStream obinfs=new ObjectInputStream(infs);
           return (User) obinfs.readObject();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Admin getAdminFromFile() {
        try {
            FileInputStream infs=new FileInputStream(new File("admin.txt"));
            ObjectInputStream obinfs=new ObjectInputStream(infs);
            return (Admin) obinfs.readObject();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
