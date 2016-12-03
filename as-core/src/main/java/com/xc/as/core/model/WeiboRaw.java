package com.xc.as.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by yxc on 2016/11/28.
 */
@Document(collection="WeiboRaw")
public class WeiboRaw {
    @Id
    private String id;

    private String uid;
    private String extend;
    private String geo;
    private String huati_tag;
    private String created_at;
    private String source_id;
    private String filter;
    private String content;

    @Field("prov_id")
    private String provcode;
    private String city_id;
    private String location_name;
    private String mentions;
    private String blog_url;

    @Field("parent_rt_id_db")
    private String parentId;
    private double emotionValue;
    private String postDate;
    private String keyword;

    private List<String> segmentWords;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getExtend() {
        return extend;
    }
    public void setExtend(String extend) {
        this.extend = extend;
    }
    public String getGeo() {
        return geo;
    }
    public void setGeo(String geo) {
        this.geo = geo;
    }
    public String getHuati_tag() {
        return huati_tag;
    }
    public void setHuati_tag(String huati_tag) {
        this.huati_tag = huati_tag;
    }
    public String getCreated_at() {
        return created_at;
    }
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    public String getSource_id() {
        return source_id;
    }
    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }
    public String getFilter() {
        return filter;
    }
    public void setFilter(String filter) {
        this.filter = filter;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getProv_id() {
        return provcode;
    }
    public void setProv_id(String prov_id) {
        this.provcode = prov_id;
    }
    public String getCity_id() {
        return city_id;
    }
    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }
    public String getLocation_name() {
        return location_name;
    }
    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }
    public String getMentions() {
        return mentions;
    }
    public void setMentions(String mentions) {
        this.mentions = mentions;
    }
    public String getBlog_url() {
        return blog_url;
    }
    public void setBlog_url(String blog_url) {
        this.blog_url= blog_url;
    }

    public String getParentId() {
        return parentId;
    }
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public double getEmotionValue() {
        return emotionValue;
    }
    public void setEmotionValue(double emotionValue) {
        this.emotionValue = emotionValue;
    }
    public String getPostDate() {
        return postDate;
    }
    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }
    public String getKeyword() {
        return keyword;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public String getProvcode() {
        return provcode;
    }
    public void setProvcode(String provcode) {
        this.provcode = provcode;
    }
    public List<String> getSegmentWords() {
        return segmentWords;
    }
    public void setSegmentWords(List<String> segmentWords) {
        this.segmentWords = segmentWords;
    }


}
