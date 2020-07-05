package com.lib.mangement.Common.entity;

import java.util.Date;

public class BookInfo {
    private int id;
    private int s_num;
    private String status;
    private Date booktime;
    private Date endtime;
    private int u_num;
    private String u_name;

    public BookInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getS_num() {
        return s_num;
    }

    public void setS_num(int s_num) {
        this.s_num = s_num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBooktime() {
        return booktime;
    }

    public void setBooktime(Date booktime) {
        this.booktime = booktime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
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
}
