package com.lib.mangement.dao.impl;



import com.lib.mangement.Common.dao.CommonDao;

import com.lib.mangement.Common.entity.Admin;
import com.lib.mangement.Common.entity.Broken;
import com.lib.mangement.Common.entity.User;
import com.lib.mangement.dao.AdminDao;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl implements AdminDao {


	//增加学生信息
    public  void addStudent(User user) {
        String sql="insert into user(name,department,class_name,password) values(?,?,?,?)";
         try {
             CommonDao.executeUpdate(sql,new Object[]{user.getName(),user.getDepartment(),user.getClass_name(),
             user.getPassword()});

           }catch(Exception e){
             e.printStackTrace();
           }
    }

    //获取所有学生信息
    public  List<User> getAllStudent() {
        String sql="select * from user";
         try {
             return CommonDao.executeQuery(sql,User.class,null);
           }catch(Exception e){
             e.printStackTrace();
           }
         return null;
    }

    //获取某个学生的信息
    public  List<User> getStudentBySid(String text) {
        String sql="select * from user where id=?";
        try {
            return CommonDao.executeQuery(sql,User.class,new Object[]{text});
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //增加违规记录
    public  void addBroken(Broken broken) {
        String sql="insert into broken(u_num,u_name,v_date,dw_date,content) values(?,?,?,?,?)";
        try {
            CommonDao.executeUpdate(sql,new Object[]{broken.getU_num(),
                  broken.getU_name(),broken.getV_date(),
                  broken.getDw_date(),broken.getContent()});

        }catch(Exception e){
            ;
        }
    }

    //修改管理员密码
    public  void updatePassword(Admin admin) {
        String sql="update admin set password=? where a_id=?";
        try {
            CommonDao.executeUpdate(sql,new Object[]{admin.getPassword(),
                 admin.getA_id()});

        }catch(Exception e){
            e.printStackTrace();
        }
    }
 
    //获取所有违规信息
    public  List<Broken> getBrokens() {
        String sql="select * from broken";
        try {
            return CommonDao.executeQuery(sql,Broken.class,null);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //获取某个学生的违规信息
    public  List<Broken> getBrokensBySearch(String key) {
        String sql="select * from broken where u_num=?";
        try {
            return CommonDao.executeQuery(sql,Broken.class,new Object[]{key});
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //修改违规记录
    public  void updateBroken(String column, Object newValue, int id) {
        String sql="update broken set "+column+"=? where id=?";
         try {
             CommonDao.executeUpdate(sql,new Object[]{newValue,id});
           }catch(Exception e){
             e.printStackTrace();
           }
    }

    //修改学生信息
    public  void updateUser(String column, Object newValue, int id) {
        String sql="update user set "+column+"=? where id=?";
        try {
            CommonDao.executeUpdate(sql,new Object[]{newValue,id});
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //删除违规记录
    public void deleteBroken(Broken selectedItem) {
        String sql="delete from broken where id=?";
        Object[] params=new Object[]{selectedItem.getId()};
         try {
             CommonDao.executeUpdate(sql,params);
           }catch(Exception e){
             e.printStackTrace();
           }
    }

    public void deleteUser(User selectedItem) {
         String sql="delete from user where id=?";
          try {
              CommonDao.executeUpdate(sql,selectedItem.getId());
            }catch(Exception e){
              e.printStackTrace();
            }
    }

}
