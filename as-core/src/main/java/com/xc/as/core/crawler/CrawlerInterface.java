package com.xc.as.core.crawler;

import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by yxc on 2016/11/26.
 */
public interface CrawlerInterface {
    /*
	 * 只有实现此接口的爬虫类，才能被扫描器识别
	 * 爬虫执行入口请重写run()
	 */

    public void run()  throws Exception;

    public void setMongoTemplate(MongoTemplate mongoOps);

    public String getTableName();
}
