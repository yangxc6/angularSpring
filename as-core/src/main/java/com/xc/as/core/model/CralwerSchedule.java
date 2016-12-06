package com.xc.as.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by yxc on 2016/12/6.
 */
@Document(collection = "CralwerSchedule")
public class CralwerSchedule {
    @Id
    private String id;

    private String crawlerClass;
    private String time;

    public String getId() {
        return id;
    }

    public CralwerSchedule setId(String id) {
        this.id = id;
        return this;
    }

    public String getCrawlerClass() {
        return crawlerClass;
    }

    public CralwerSchedule setCrawlerClass(String crawlerClass) {
        this.crawlerClass = crawlerClass;
        return this;
    }

    public String getTime() {
        return time;
    }

    public CralwerSchedule setTime(String time) {
        this.time = time;
        return this;
    }


}
