package com.xc.as.web.resource;

import com.xc.as.core.model.WeiboRaw;
import com.xc.as.core.service.WeiboService;
import com.xc.as.web.common.ResourceResponseSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by yxc on 2016/11/28.
 */
@Controller
@RequestMapping(value = "/weibo")
public class WeiboResource extends ResourceResponseSupport {

    @Autowired
    WeiboService weiboService;

    @RequestMapping(value = "/getFirstTenItems" , method = RequestMethod.GET)
    public ResponseEntity<List<WeiboRaw>> getFirstTenItems(){
        try{
            List<WeiboRaw> result =  weiboService.getFirstTenItems();
            return new ResponseEntity<List<WeiboRaw>>(
                    result,
                    HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<WeiboRaw>>( HttpStatus.EXPECTATION_FAILED);
        }
    }

}
