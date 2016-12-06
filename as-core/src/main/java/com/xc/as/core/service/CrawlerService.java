package com.xc.as.core.service;

import com.xc.as.core.model.CralwerSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yxc on 2016/12/6.
 */
@Service("CrawlerService")
public class CrawlerService {
    @Autowired
    MongoTemplate mongoOps;

    public boolean deleteCrawler(String crawler, String time){
        try{
            mongoOps.remove(new Query(Criteria.where("crawlerClass").is(crawler).and("time").is(time)), CralwerSchedule.class);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveCrawler(String crawler, String hour){
        try{
            mongoOps.upsert(new Query(Criteria.where("crawlerClass").is(crawler).and("time").is(hour)),
                    new Update().set("crawlerClass", crawler).set("time", hour), CralwerSchedule.class);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public List<CralwerSchedule> getCrawlerTable(){
        try{
            return (List<CralwerSchedule>) mongoOps.find(new Query(),CralwerSchedule.class);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
