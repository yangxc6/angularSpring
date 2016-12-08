package com.xc.as.core.service;

import com.xc.as.core.crawler.CrawlerInterface;
import com.xc.as.core.model.CralwerSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by yxc on 2016/12/6.
 */

@Component
@Lazy(value=false)
public class ScheduledService {
    @Autowired
    protected MongoTemplate mongoOps;

    public ScheduledService(){
        super();
    }

    @Scheduled(cron = "0 0 * * * *")
    public void runTask(){
        System.out.println("Scheduled Crawler task begin run");

        List<CrawlerInterface> cls = new ArrayList<CrawlerInterface>();
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");

        try{
            List<CralwerSchedule> cralwerList = mongoOps.find(new Query(),CralwerSchedule.class);
            if(null!= cralwerList && cralwerList.size()>0){
                for(CralwerSchedule c: cralwerList){
                    Class<CrawlerInterface> ci = (Class<CrawlerInterface>) Class.forName(c.getCrawlerClass());
                    CrawlerInterface ins = ci.newInstance();
                    cls.add(ins);
                    int hourC = format.parse(c.getTime()).getHours();
                    if(hourC == hour ){
                        ins.run();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("Scheduled Crawler task end run");
        }
    }
}
