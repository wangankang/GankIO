package com.cornor.gank.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Desc:
 * User:cornor
 * Date:2018/3/7
 */

public class GankCategory implements Serializable {
    @SerializedName("_id")
    private String Id;
    @SerializedName("createdAt")
    private Date createdat;
    private String desc;
    private List<String> images;
    @SerializedName("publishedAt")
    private Date publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    public void setId(String Id) {
        this.Id = Id;
    }
    public String getId() {
        return Id;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }
    public Date getCreatedat() {
        return createdat;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
    public List<String> getImages() {
        return images;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }
    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public String getSource() {
        return source;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
    public boolean getUsed() {
        return used;
    }

    public void setWho(String who) {
        this.who = who;
    }
    public String getWho() {
        return who;
    }

}
