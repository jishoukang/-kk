package com.lib.mangement.Common.entity;

import java.util.Date;

public class Broken {
    private int id;
    private int u_num;
    private String u_name;

    private Date v_date;
    private Date dw_date;
    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getU_num() {
        return u_num;
    }

    public void setU_num(int u_num) {
        this.u_num = u_num;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }


    public Date getV_date() {
        return v_date;
    }

    public void setV_date(Date v_date) {
        this.v_date = v_date;
    }

    public Date getDw_date() {
        return dw_date;
    }

    public void setDw_date(Date dw_date) {
        this.dw_date = dw_date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
