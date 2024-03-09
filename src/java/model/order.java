/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class order {
    private int id ;
    private String cusname;
    private Date date;
    private int totaloney;

    public order() {
    }

    public order(int id, String cusname, Date date, int totaloney) {
        this.id = id;
        this.cusname = cusname;
        this.date = date;
        this.totaloney = totaloney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTotaloney() {
        return totaloney;
    }

    public void setTotaloney(int totaloney) {
        this.totaloney = totaloney;
    }
    
    
}
