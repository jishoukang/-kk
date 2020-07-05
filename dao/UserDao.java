package com.lib.mangement.dao;

import com.lib.mangement.Common.dao.CommonDao;
import com.lib.mangement.Common.entity.*;

import java.sql.SQLException;

import java.util.List;

/**
 * 这是用户接口类
 * @author zz
 * @version 1.0
 * 
 * */

public interface UserDao {
	
    public   void applySeat(BookInfo bookInfo) throws SQLException ;

    public   List<Seat> getSeatInfo();

    public   List<BookInfo> getBookInfo(String sid,String uid);

    public   void tempStatus(int id, String  status) ;

    public   void updateUserPass(User userFromFile);

    public   List<Broken> getBrokenByUserid(int id);

    public   void chageSeatStatus(String status, int s_num);

    public   List<BookInfo> getpersonalbook(int id);

    public User getUserByNaPwdType(String name, String password);

    public   int getUserBynameAndPhone(String usernamestr, String phonestr);

    public   void forgetAdminPwd(String usernamestr, String phonestr, String newpassstr);

    public   void forgetUserPwd(String usernamestr, String phonestr, String newpassstr);
    
    public   void changeUserPwd(User user);

    public Admin getAdmin(String text, String text1) ;

}

