package com.graduation.web.controller;

import com.graduation.web.config.annotation.LimitAccess;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @RequestMapping
    public String carRentalIndexView(ModelMap map){
        map.addAttribute("index",null);
        map.addAttribute("user",null);
        return REDIRECT_TO_BOOK_URL;
    }
}
