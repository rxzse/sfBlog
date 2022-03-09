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
public class Category {
    // _id, cateName, alias, description, shortUrl, image, createTime, modifyTime
    private String _id, cateName, alias, description, shortUrl, image;
    private Date createTime, modifyTime;
    private int sequence;

    public Category() {
    }

    public Category(String _id, String cateName, String alias, String description, String shortUrl, String image, Date createTime, Date modifyTime) {
        this._id = _id;
        this.cateName = cateName;
        this.alias = alias;
        this.description = description;
        this.shortUrl = shortUrl;
        this.image = image;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        return "Category{" + "_id=" + _id + ", cateName=" + cateName + ", alias=" + alias + ", description=" + description + ", shortUrl=" + shortUrl + ", image=" + image + ", createTime=" + createTime + ", modifyTime=" + modifyTime + '}';
    }
    
    
    
}
