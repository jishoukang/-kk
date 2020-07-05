package com.lib.mangement.Test;



import com.lib.mangement.Common.dao.CommonDao;
import com.lib.mangement.Common.entity.User;

import java.util.List;

public class TestDao {
    public static void main(String[] args) throws Exception {
        List<User> users = CommonDao.executeQuery("select * from user", User.class);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
