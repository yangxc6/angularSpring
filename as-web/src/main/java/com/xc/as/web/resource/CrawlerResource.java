package com.xc.as.web.resource;

import com.xc.as.core.crawler.CrawlerScaner;
import com.xc.as.web.common.ResourceResponseSupport;
import com.xc.as.web.common.RestResultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by yxc on 2016/11/26.
 */
@Controller
public class CrawlerResource extends ResourceResponseSupport {

    @RequestMapping(value = "/crawler/getAllCrawlerClass", method = RequestMethod.GET )
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

    @RequestMapping(value = "/crawler/testRun", method = RequestMethod.GET )
    public ResponseEntity<RestResultResponse> testRun() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        Class<?> c =  Class.forName("com.xc.as.core.crawler.JXLZCallbackImpl");
        Method mth = c.getMethod("run",null);
        mth.invoke(c.newInstance());
        return new ResponseEntity<RestResultResponse>(
                this.buildSuccessRestResultResponse(null),
                HttpStatus.OK);
    }
}
