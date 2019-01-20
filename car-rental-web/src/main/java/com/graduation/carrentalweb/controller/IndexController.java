package com.graduation.carrentalweb.controller;

import com.graduation.carrentalweb.config.annotation.LimitAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 首页
 * @Author fuxiaoxiang2
 * @Create 2019/1/7 15:33
 */
@Controller
@RequestMapping("/main")
public class IndexController {
    private static final String REDIRECT_TO_BOOK_URL = "index";

    @LimitAccess(count = 5,time = 60000)
    @RequestMapping(method = RequestMethod.GET)
    public String carRentalIndexView(ModelMap map){
        map.addAttribute("index",null);
        map.addAttribute("user",null);
        return REDIRECT_TO_BOOK_URL;
    }
}
