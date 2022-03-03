/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author RxZ
 */
public class Setting {
    // siteName, siteAuthor, baseUrl
    private String siteName, siteAuthor, baseUrl;

    public Setting() {
    }

    public Setting(String siteName, String siteAuthor, String baseUrl) {
        this.siteName = siteName;
        this.siteAuthor = siteAuthor;
        this.baseUrl = baseUrl;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteAuthor() {
        return siteAuthor;
    }

    public void setSiteAuthor(String siteAuthor) {
        this.siteAuthor = siteAuthor;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public String toString() {
        return "Setting{" + "siteName=" + siteName + ", siteAuthor=" + siteAuthor + ", baseUrl=" + baseUrl + '}';
    }
    
    
}
