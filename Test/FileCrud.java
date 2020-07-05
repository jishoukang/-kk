package com.lib.mangement.Test;

import com.lib.mangement.Common.entity.User;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileCrud {
    @Test
    public void fileStudentAdd() throws Exception{
        FileOutputStream fileOutputStream=new FileOutputStream(new File("students.data"));
        List<User> userList=new ArrayList<>();
        User user1=new User();
        user1.setName("testUser");
        user1.setDepartment("testDepartment");
        user1.setPassword("testPwd");
        user1.setId(100101);
        User user2=new User();
        user2.setName("testUser1");
        user2.setDepartment("testDepartment1");
        user2.setPassword("testPwd1");
        user2.setId(100102);

        userList.add(user1);
        userList.add(user2);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(userList);
        objectOutputStream.close();
        fileOutputStream.close();
    }
    @Test
    public void fileStudentDelete() throws Exception{
        FileInputStream fileOutputStream=new FileInputStream(new File("students.data"));
        ObjectInputStream objectInputStream=new ObjectInputStream(fileOutputStream);
        List<User> userList= (List<User>) objectInputStream.readObject();

        for (User user : userList) {
            System.out.println(user);
        }

        int findUserid=100101;
        for (User user : userList) {
            if(user.getId()==findUserid){
                userList.remove(user);
            }
        }
        for (User user : userList) {
            System.out.println(user);
        }
        objectInputStream.close();
        fileOutputStream.close();
        fileWrite(userList);
    }

    @Test
    public void fileStudentUpdate() throws Exception{
        FileInputStream fileOutputStream=new FileInputStream(new File("students.data"));
        ObjectInputStream objectInputStream=new ObjectInputStream(fileOutputStream);
        List<User> userList= (List<User>) objectInputStream.readObject();

        for (User user : userList) {
            System.out.println(user);
        }

        User update=new User();
        update.setId(100101);
        update.setPassword("100101");
        update.setDepartment("新闻媒体部");
        update.setClass_name("传媒三班");
        for (User user : userList) {

            if(user.getId()==update.getId()){
                user.setClass_name(update.getClass_name());
                user.setDepartment(update.getDepartment());
                user.setPassword(update.getPassword());
                user.setId(update.getId());
            }
        }
        for (User user : userList) {
            System.out.println(user);
        }
        objectInputStream.close();
        fileOutputStream.close();
        fileWrite(userList);
    }
    @Test
    public void findUserById() throws Exception{
        FileInputStream fileOutputStream=new FileInputStream(new File("students.data"));
        ObjectInputStream objectInputStream=new ObjectInputStream(fileOutputStream);
        List<User> userList= (List<User>) objectInputStream.readObject();
        User getUser=new User();
        getUser.setName("testUser");

        for (User user : userList) {
            if(user.getName().equals(getUser.getName())){
                System.out.println(user);
            }

        }

        objectInputStream.close();
        fileOutputStream.close();

    }

    public void fileWrite(List<User> users) throws Exception{
        FileOutputStream fileOutputStream=new FileOutputStream(new File("students.data"));
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(users);
        objectOutputStream.close();
        fileOutputStream.close();
    }




}
