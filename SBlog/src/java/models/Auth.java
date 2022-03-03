/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import java.util.Date;
/**
 *
 * @author RxZ
 */
public class Auth {
    // password, createTime, modifyTime
    private String password;
    private Date createTime, modifyTime;

    public Auth() {
    }

    public Auth(String password, Date createTime, Date modifyTime) {
        this.password = password;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "Auth{" + "password=" + password + ", createTime=" + createTime + ", modifyTime=" + modifyTime + '}';
    }
    
    
    
}
