package com.lib.mangement.dao.impl;


import com.lib.mangement.Common.dao.CommonDao;
import com.lib.mangement.Common.entity.*;

import com.lib.mangement.dao.UserDao;
import com.sun.org.apache.xml.internal.utils.ObjectPool;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

	
    public  void applySeat(BookInfo bookInfo) throws SQLException {
        String create="insert into bookinfo(s_num,status,booktime,u_num,u_name) values(?,?,?,?,?)";
        Object[]params=new Object[]{bookInfo.getS_num(),bookInfo.getStatus(),bookInfo.getBooktime(),bookInfo.getU_num(),bookInfo.getU_name()};

        CommonDao.executeUpdate(create,params);

        String setatatus="update seat set status=? where id=?";

        CommonDao.executeUpdate(setatatus,new Object[]{bookInfo.getStatus(),bookInfo.getS_num()});

    }

    //显示位置为"空"的座位信息
    public  List<Seat> getSeatInfo() {
        String sql="select * from seat where status='空'";
        try {
            return  CommonDao.executeQuery(sql,Seat.class,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //显示(根据学号/座位号)搜索的座位预约信息
    public  List<BookInfo> getBookInfo(String sid,String uid) {
        String sql="select * from bookinfo where s_num=? or u_num=?";
        try {
            return  CommonDao.executeQuery(sql,BookInfo.class,new Object[]{sid,sid});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //修改学生状态(暂时离座/完全离座)
    public  void tempStatus(int id, String  status) {
        String sql="update bookinfo set status=? where id=?";
         try {
             CommonDao.executeUpdate(sql,new Object[]{status,id});
           }catch(Exception e){
             e.printStackTrace();
           }
    }

    //修改密码
    public  void updateUserPass(User userFromFile) {
         String sql="update user set password=? where id=?";
         try {
             CommonDao.executeUpdate(sql,new Object[]{userFromFile.getPassword(),userFromFile.getId()});
           }catch(Exception e){
             e.printStackTrace();
           }
    }

    //显示学生的违规记录
    public  List<Broken> getBrokenByUserid(int id) {
         String sql="select * from broken where u_num=?";
         try {
             return  CommonDao.executeQuery(sql,Broken.class,new Object[]{id});
           }catch(Exception e){
             e.printStackTrace();
           }
         return null;
    }

    
    public  void chageSeatStatus(String status, int s_num) {
        String update="update seat set status=? where id=?";
             try {

             CommonDao.executeUpdate(update,new Object[]{status,s_num});

           }catch(Exception e){
             e.printStackTrace();
           }
    }

    public  List<BookInfo> getpersonalbook(int id) {
        String sql="select * from bookinfo where u_num=?";
         try {
             return CommonDao.executeQuery(sql,BookInfo.class,new Object[]{id});
           }catch(Exception e){
             e.printStackTrace();
           }
         return null;
    }

    public User getUserByNaPwdType(String name, String password) {
       String  sql="select * from user where id=? and password=?";

        Object[] objects = {name, password};
        List<User> users = null;
        try {
            users = CommonDao.executeQuery(sql, User.class, objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            return users.get(0);

        }catch (Exception e){
            e.getMessage();
            return null;
        }
    }


    public   int getUserBynameAndPhone(String usernamestr, String phonestr) {
        String sql="select * from user where name=? and phone=?";
        Object[] prasm=new Object[]{usernamestr,phonestr};
        try {
            List<User> users = CommonDao.executeQuery(sql, User.class, prasm);
            if(users.size()==0){
                String adminsql="select * from admin where name=? and phone=?";
                Object[] adminparam=new Object[]{usernamestr,phonestr};
                List<Admin> admins = CommonDao.executeQuery(adminsql, Admin.class, adminparam);
                if(admins.size()==0){
                    return -1;
                }else{
                    return 2;
                }
            }else{
                return 1;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public   void forgetAdminPwd(String usernamestr, String phonestr, String newpassstr) {
        String sql="update admin set password=? where name=? and phone=?";
        Object[] prams=new Object[]{newpassstr,usernamestr,phonestr};
        try {
            CommonDao.executeUpdate(sql,prams);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public   void forgetUserPwd(String usernamestr, String phonestr, String newpassstr) {
        String sql="update user set password=? where name=? and phone=?";
        Object[] prams=new Object[]{newpassstr,usernamestr,phonestr};
        try {
            CommonDao.executeUpdate(sql,prams);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public   void changeUserPwd(User user) {
        String sql="update user set password=? where id=?";
        Object[] params=new Object[]{user.getPassword(),user.getId()};
        try {
            CommonDao.executeUpdate(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Admin getAdmin(String text, String text1) {
        String sql="select * from admin where w_id=? and password=?";
        Object[] params=new Object[]{text,text1};
         try {
           ArrayList<Admin> results= (ArrayList<Admin>) CommonDao.executeQuery(sql,Admin.class,params);
           if(results.size()!=0){
               return results.get(0);
           }else{
               return null;
           }
           }catch(Exception e){
             e.printStackTrace();
           }
        return null;
    }

    public List<Seat> getSeatInfoBySeatId(String text) {
        String sql="select * from seat where id=?";
        try {
            return  CommonDao.executeQuery(sql,Seat.class,new Object[]{text});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	public boolean getSeatByUid(int id) {
		 String sql="select * from bookinfo where u_num=? and status!='空'";
		 try {
			List<BookInfo> resultBookInfos=CommonDao.executeQuery(sql, BookInfo.class, new Object[] {id});
			if(resultBookInfos.size()==0)
				return false;
			else {
				 return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
