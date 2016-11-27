package com.xc.as.web.resource;

import com.xc.as.core.Utils.UtilsTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.net.URL;

/**
 * Created by yxc on 2016/11/25.
 */
@Controller
public class HelloController {
    @RequestMapping(value = "/UtilsTest", method = RequestMethod.GET)
    public ModelAndView utilsTest(){
        UtilsTest.print();
        URL path = Thread.currentThread().getContextClassLoader().getResource("");
        System.out.print(path.toString());
        return new ModelAndView("UtilsTest");
    }
}