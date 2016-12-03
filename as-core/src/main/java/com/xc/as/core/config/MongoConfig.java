package com.xc.as.core.config;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;

/**
 * Created by yxc on 2016/11/26.
 */
//@Configuration
@Component
@EnableMongoRepositories
public class MongoConfig extends AbstractMongoConfiguration {
    private String dbName = "angularSpring";
    private String host = "localhost";
    private int port = 27017;

    protected String getDatabaseName() {
        return this.dbName;
    }

    @Bean
    public Mongo mongo() throws Exception {
        return new Mongo(this.host, this.port);
    }

    @Bean
    public MongoTemplate mongoTemplate(Mongo mongo) throws Exception {
        System.out.println("creating mongoTemplate");
        MongoTemplate mt = new MongoTemplate(mongo, this.dbName);
        System.out.println(" " + mt.toString());
        return mt;
    }
}
