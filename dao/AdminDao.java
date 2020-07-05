package com.lib.mangement.dao;


import com.lib.mangement.Common.dao.CommonDao;
import com.lib.mangement.Common.entity.Admin;
import com.lib.mangement.Common.entity.Broken;
import com.lib.mangement.Common.entity.User;

import java.util.List;

/**
 * 这是管理员接口类
 * @author zz
 * @version 1.0
 * */

public interface AdminDao {

	/**
	 * 这是增加学生信息的方法
	 * @param user 学生用户
	 * */
    public  void addStudent(User user);

    /**
     * 这是获取所有学生信息的方法
     * @return 无返回值
     * */
    public  List<User> getAllStudent();

    /**
     * 这是搜索某一个学生信息的方法
     * @param text 学生学号
     * @return 无返回值
     * */
    public  List<User> getStudentBySid(String text);

    /**
     * 这是增加违规信息的方法
     * @param broken 违规信息
     * */
    public  void addBroken(Broken broken);

    /**
     * 这是修改管理员密码的方法
     * @param admin 管理员用户
     * */
    public  void updatePassword(Admin admin) ;

    /**
     * 这是获取所有违规信息的方法
     * @return 无返回值
     * */
    public  List<Broken> getBrokens() ;

    /**
     * 这是获取某个学生的违规信息的方法
     * @param key 学生学号
     * @return 无返回值
     * */
    public  List<Broken> getBrokensBySearch(String key);

    /**
     * 这是修改违规信息的方法 
     * @param column 需要修改的违规信息
     * @param newValue 修改后的值
     * @param id 要被修改记录的学生的学号
     * */
    public  void updateBroken(String column, Object newValue, int id);

    /**
     * 这是修改学生信息的方法 
     * @param column  需要修改的信息
     * @param newValue 修改后的值
     * @param id 要被修改记录的学生的学号
     * */
    public  void updateUser(String column, Object newValue, int id);
   }