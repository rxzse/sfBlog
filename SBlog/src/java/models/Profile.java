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
public class Profile {
    // firstName, lastName, bio, desc, createTime, modifyTime
    private String firstName, lastName, bio, desc;
    private Date createTime, modifyTime;

    public Profile() {
    }

    public Profile(String firstName, String lastName, String bio, String desc, Date createTime, Date modifyTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.desc = desc;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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
        return "Profile{" + "firstName=" + firstName + ", lastName=" + lastName + ", bio=" + bio + ", desc=" + desc + ", createTime=" + createTime + ", modifyTime=" + modifyTime + '}';
    }
    
    
}
