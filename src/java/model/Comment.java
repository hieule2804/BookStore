/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Comment {
    private int cmtid;
    private int bid;
    private String cmtuser,cmtdetail;

    public Comment() {
    }

    public Comment(int cmtid, int bid, String cmtuser, String cmtdetail) {
        this.cmtid = cmtid;
        this.bid = bid;
        this.cmtuser = cmtuser;
        this.cmtdetail = cmtdetail;
    }

    public int getCmtid() {
        return cmtid;
    }

    public void setCmtid(int cmtid) {
        this.cmtid = cmtid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getCmtuser() {
        return cmtuser;
    }

    public void setCmtuser(String cmtuser) {
        this.cmtuser = cmtuser;
    }

    public String getCmtdetail() {
        return cmtdetail;
    }

    public void setCmtdetail(String cmtdetail) {
        this.cmtdetail = cmtdetail;
    }
    
    
}
