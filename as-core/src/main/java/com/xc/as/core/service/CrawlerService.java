package com.xc.as.core.service;

import com.mongodb.DBCursor;
import com.xc.as.core.crawler.CrawlerInterface;
import com.xc.as.core.model.CralwerSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<String> getTenItems(String crawler){
        try{
            Class<CrawlerInterface> c =(Class<CrawlerInterface>) Class.forName(crawler);
            CrawlerInterface ins = c.newInstance();
            String tableName = ins.getTableName();

            List<String> result = new ArrayList<String>();
            if(null!=tableName && tableName.length()>0){
                Query q = new Query().limit(10);
                DBCursor cur = mongoOps.getCollection(tableName).find(q.getQueryObject());
                int index = 0;
                while(cur.hasNext() && index++ < 10){
                    result.add((String)cur.next().get("title"));
                }
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
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
