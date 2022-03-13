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
public class Category {
    private int id;
    private String name;
    private String alias;
    private int sequence;
    private Date createTime;
    private Date modifyTime;
    
    // base for stats
    private int postCount;

    public Category() {
    }

    public Category(int id, String name, String alias, int sequence, Date createTime, Date modifyTime) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.sequence = sequence;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public Category(int id, String name, String alias, int sequence, Date createTime, Date modifyTime, int postCount) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.sequence = sequence;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.postCount = postCount;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
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

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name=" + name + ", alias=" + alias + ", sequence=" + sequence + ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", postCount=" + postCount + '}';
    }
    
    


    
}
