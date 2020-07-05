package com.lib.mangement.Common.entity;

import java.io.Serializable;

/**
 * 
 *   
 *
 */

public class Admin  implements Serializable {
    private int a_id;
    private String a_name;
    private int w_id;
    private String password;

    public Admin() {
    }

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getA_name() {
        return a_name;
    }

    public void setA_name(String a_name) {
        this.a_name = a_name;
    }

    public int getW_id() {
        return w_id;
    }

    public void setW_id(int w_id) {
        this.w_id = w_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
