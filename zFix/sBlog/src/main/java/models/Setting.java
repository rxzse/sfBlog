/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Date;

/**
 *
 * @author RxZ
 */
public class Setting {

    private int id;
    private String blogName;
    private String blogIntro;
    private int postPageSize;
    private Date createTime;
    private Date modifyTime;

    public Setting() {
    }

    public Setting(int id, String blogName, String blogIntro, int postPageSize, Date createTime, Date modifyTime) {
        this.id = id;
        this.blogName = blogName;
        this.blogIntro = blogIntro;
        this.postPageSize = postPageSize;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBlogIntro() {
        return blogIntro;
    }

    public void setBlogIntro(String blogIntro) {
        this.blogIntro = blogIntro;
    }

    public int getPostPageSize() {
        return postPageSize;
    }

    public void setPostPageSize(int postPageSize) {
        this.postPageSize = postPageSize;
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
    
    
}
