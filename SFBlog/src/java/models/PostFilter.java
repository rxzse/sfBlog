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
public class PostFilter {
    private int category;
    private String title;
    private boolean isDraft;
    private boolean isActive;
    private int start;
    private int total;
    private Date createTimeStart, createTimeEnd;

    public PostFilter() {
    }

    public PostFilter(int category, String title, boolean isDraft, boolean isActive, int start, int total, Date createTimeStart, Date createTimeEnd) {
        this.category = category;
        this.title = title;
        this.isDraft = isDraft;
        this.isActive = isActive;
        this.start = start;
        this.total = total;
        this.createTimeStart = createTimeStart;
        this.createTimeEnd = createTimeEnd;
    }
    
    

    public PostFilter(int category, String title, boolean isDraft, boolean isActive, Date createTimeStart, Date createTimeEnd) {
        this.category = category;
        this.title = title;
        this.isDraft = isDraft;
        this.isActive = isActive;
        this.createTimeStart = createTimeStart;
        this.createTimeEnd = createTimeEnd;
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

    public Date getCreateTimeStart() {
        return createTimeStart;
    }

    public void setCreateTimeStart(Date createTimeStart) {
        this.createTimeStart = createTimeStart;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }

    @Override
    public String toString() {
        return "PostFilter{" + "category=" + category + ", title=" + title + ", isDraft=" + isDraft + ", isActive=" + isActive + ", createTimeStart=" + createTimeStart + ", createTimeEnd=" + createTimeEnd + '}';
    }

   
    
}
