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
public class Post {
    // _id, name, category ref, createdTime, modifyTime, alias
    private String _id, name, catefory_ref, alias;
    private Date createdTime, modifyTime;

    public Post() {
    }

    public Post(String _id, String name, String catefory_ref, String alias, Date createdTime, Date modifyTime) {
        this._id = _id;
        this.name = name;
        this.catefory_ref = catefory_ref;
        this.alias = alias;
        this.createdTime = createdTime;
        this.modifyTime = modifyTime;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatefory_ref() {
        return catefory_ref;
    }

    public void setCatefory_ref(String catefory_ref) {
        this.catefory_ref = catefory_ref;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "Post{" + "_id=" + _id + ", name=" + name + ", catefory_ref=" + catefory_ref + ", alias=" + alias + ", createdTime=" + createdTime + ", modifyTime=" + modifyTime + '}';
    }
    
    
}
