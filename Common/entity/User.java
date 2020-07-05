package com.lib.mangement.Common.entity;



import java.io.Serializable;


public class User implements Serializable {
    private static long serialVersionUID=1L;
    private int id;
    private String name;
    private String password;
    private String class_name;
    private String department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "User{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", password='" + password + '\'' +
              ", class_name='" + class_name + '\'' +
              ", department='" + department + '\'' +
              '}';
    }
}
