package com.xc.as.core.service;

import com.xc.as.core.config.MongoConfig;
import com.xc.as.core.model.WeiboRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yxc on 2016/11/28.
 */
@Service("WeiboService")
public class WeiboService {

    @Autowired
    protected MongoTemplate mongoOps;

    public WeiboService() throws Exception {
        ApplicationContext ac = new AnnotationConfigApplicationContext(MongoConfig.class);
        this.mongoOps = (MongoTemplate) ac.getBean("mongoTemplate");

        if(null == this.mongoOps){
            throw new Exception();
        }
    }

    public List<WeiboRaw> getFirstTenItems(){
        return mongoOps.find(new Query().limit(10),WeiboRaw.class);
    }
}
