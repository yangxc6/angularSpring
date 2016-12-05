package com.xc.as.web.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by yxc on 2016/12/5.
 */

@Controller
public class AdminResource {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView homePage(){
        return new ModelAndView("main/home");
    }
}
