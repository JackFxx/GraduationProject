package com.graduation.carrentalweb.controller;

import com.alibaba.fastjson.JSONObject;
import com.graduation.common.enums.UserEnum;
import com.graduation.domain.bo.UserBO;
import com.graduation.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption
 * @Author fuxiaoxiang2
 * @Create 2019/1/6 20:55
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource(name = "userService")
    UserService userService;

    @RequestMapping("/registry")
    @ResponseBody
    public Map<String, Object> registryUser(UserBO bo) {
        Map<String, Object> result = new HashMap<>();
        try {
            int registryCode = userService.registryUser(bo);
            result.put("code", registryCode * 100);
            result.put("msg", UserEnum.queryUserMessage(registryCode).getMessage());
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "服务端错误");
            logger.error("registry user:{}", JSONObject.toJSONString(bo), e);
        }
        return result;
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> loginUser(UserBO bo, ModelMap map) {
        Map<String, Object> result = new HashMap<>();
        if (null == bo) {
            return null;
        }
        try {
            int loginCode = userService.loginUser(bo.getUsername(), bo.getPassword(), bo.getMobile());
            map.addAttribute("user", bo);
            result.put("code", loginCode * 100);
            result.put("msg", UserEnum.queryUserMessage(loginCode).getMessage());
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "服务端错误");
            logger.error("login user:{}", JSONObject.toJSONString(bo), e);
        }
        return result;
    }

    @RequestMapping(value = "/checkout/{id}")
    @ResponseBody
    public Map<String, Object> checkoutUser(Long id) {
        Map<String, Object> result = new HashMap<>();
        if (null == id) {
            return null;
        }
        try {
            userService.checkoutLogin(id);
            result.put("code", 100);
            result.put("msg", UserEnum.queryUserMessage(1).getMessage());
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "服务端错误");
            logger.error("checkout user:{}", id, e);
        }
        return result;
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Map<String, Object> updateUser(UserBO bo) {
        Map<String, Object> result = new HashMap<>();
        if (null == bo) {
            return null;
        }
        try {
            int updateCount = userService.updateUser(bo);
            result.put("code", updateCount * 100);
            result.put("msg", UserEnum.queryUserMessage(updateCount).getMessage());
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "服务端错误");
            logger.error("update user:{}", JSONObject.toJSONString(bo), e);
        }
        return result;
    }
}
