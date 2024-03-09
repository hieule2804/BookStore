/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class InforOrder {

    private int id;
    private String name, phone, city, location;
    private int orderid,totalMoney;
    private String shipper;
    private String status;
    private Date date;
    public InforOrder() {
    }

    public InforOrder(int id, String name, String phone, String city, String location, int orderid, int totalMoney, String shipper, String status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.city = city;
        this.location = location;
        this.orderid = orderid;
        this.totalMoney = totalMoney;
        this.shipper = shipper;
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getShipper() {
        return shipper;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "InforOrder{" + "id=" + id + ", name=" + name + ", phone=" + phone + ", city=" + city + ", location=" + location + ", orderid=" + orderid + ", totalMoney=" + totalMoney + ", shipper=" + shipper + ", status=" + status + '}';
    }

   
    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

}
