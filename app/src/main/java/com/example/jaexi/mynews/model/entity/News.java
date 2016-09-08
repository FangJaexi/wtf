package com.example.jaexi.mynews.model.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
public class News implements Serializable {
    /**
     * 新闻 id
     */
    private int nid;
    /**标题*/
    private String title="";
    /**摘要*/
    private String summary="";
    /**图标*/
    private String icon="";
    /**网页链接*/
    private String link="";
    /**新闻类型*/
    private int type;
    public News(int nid, String title, String summary, String list_image,
                String url,int type) {
        this.nid = nid;
        this.title = title;
        this.summary = summary;
        this.icon = list_image;
        this.link = url;
        this.type=type;
    }
    public int getNid() {
        return nid;
    }
    public String getTitle() {
        return title;
    }
    public String getSummary() {
        return summary;
    }
    public String getIcon() {
        return icon;
    }
    public String getLink() {
        return link;
    }
    public int getType() {
        return type;
    }
    @Override
    public String toString() {
        return "News [nid=" + nid + ", title=" + title + ", summary=" + summary
                + ", icon=" + icon + ", url=" + link +  ", type=" + type+"]";
    }
}
