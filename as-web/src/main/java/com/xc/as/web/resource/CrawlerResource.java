package com.xc.as.web.resource;

import com.xc.as.core.crawler.CrawlerInterface;
import com.xc.as.core.crawler.CrawlerScaner;
import com.xc.as.web.common.ResourceResponseSupport;
import com.xc.as.web.common.RestResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by yxc on 2016/11/26.
 */
@Controller
@RequestMapping(value = "/crawler")
public class CrawlerResource extends ResourceResponseSupport {

    @Autowired
    protected MongoTemplate mongoOps;

    @RequestMapping(value = "/getAllCrawlerClass", method = RequestMethod.GET )
    public ResponseEntity<RestResultResponse> getAllCrawlerClass() throws ClassNotFoundException {
        try{
            List<Class<?>> classList = CrawlerScaner.getAllAssignedClass();
            return new ResponseEntity<RestResultResponse>(
                    this.buildSuccessRestResultResponse(classList),
                    HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<RestResultResponse>(
                    this.buildErrorRestResultResponse(e),
                    HttpStatus.EXPECTATION_FAILED
            );
        }
    }

    @RequestMapping(value = "/testRun", method = RequestMethod.GET )
    public ResponseEntity<RestResultResponse> testRun() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, IOException {
        List<Class<?>> classList = CrawlerScaner.getAllAssignedClass();

        for(Class<?> c: classList){
            Class<CrawlerInterface> cClass = (Class<CrawlerInterface>) c;
            CrawlerInterface crawlerInterface = cClass.newInstance();
            crawlerInterface.setMongoTemplate(mongoOps);
            try {
                crawlerInterface.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new ResponseEntity<RestResultResponse>(
                this.buildSuccessRestResultResponse(null),
                HttpStatus.OK);
    }
}
