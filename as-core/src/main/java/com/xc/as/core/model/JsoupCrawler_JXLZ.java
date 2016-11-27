package com.xc.as.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.util.Date;

/**
 * Created by yxc on 2016/11/26.
 */
@Document(collection = "JsoupCrawler_JXLZ")
public class JsoupCrawler_JXLZ {
    @Id
    private String id;

    private String title;
    private String content;
    private String url;
    private int hashCode;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date createdDate;

    private String sourceUrl;

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }

    public int getHashCode() {
        return hashCode;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }


}
