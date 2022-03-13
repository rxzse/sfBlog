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
public class Post {

    private int id;
    private int category;
    private String title;
    private String alias;
    private String html;
    private String markdown;
    private boolean isDraft;
    private boolean isActive;
    private Date createTime;
    private Date modifyTime;
    private Date publishTime;

    public Post() {
    }

    public Post(int id, int category, String title, String alias, String html, String markdown, boolean isDraft, boolean isActive, Date createTime, Date modifyTime, Date publishTime) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.alias = alias;
        this.html = html;
        this.markdown = markdown;
        this.isDraft = isDraft;
        this.isActive = isActive;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.publishTime = publishTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getMarkdown() {
        return markdown;
    }

    public void setMarkdown(String markdown) {
        this.markdown = markdown;
    }

    public boolean isIsDraft() {
        return isDraft;
    }

    public void setIsDraft(boolean isDraft) {
        this.isDraft = isDraft;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
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

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", category=" + category + ", title=" + title + ", alias=" + alias + ", html=" + html + ", markdown=" + markdown + ", isDraft=" + isDraft + ", isActive=" + isActive + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", publishTime=" + publishTime + '}';
    }
    
    


}
