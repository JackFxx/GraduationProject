package com.graduation.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 用户基本枚举
 * @Author fuxiaoxiang2
 * @Create 2019/1/7 10:15
 */
public enum UserEnum {
    USER_SUCCESS(1, "操作成功"),
    NO_THIS_USER(0, "当前用户不存在"),
    USER_PASS_ERROR(-1, "用户名或密码错误"),
    USER_GENDER_NULL(-2, "用户性别为空"),
    USER_REGISTRY_ERROR(-3, "用户注册出现错误"),
    NO_USER_PASSWORD(-4, "请输入用户名或密码"),
    USER_SERVER_ERROR(-5, "服务端错误"),
    USER_PARAM_ERROR(-6, "参数错误");
    private static final Map<Integer, UserEnum> userEnumMap = new HashMap<Integer, UserEnum>() {{
        put(1,USER_SUCCESS);
        put(0, NO_THIS_USER);
        put(-1, USER_PASS_ERROR);
        put(-2, USER_GENDER_NULL);
        put(-3, USER_REGISTRY_ERROR);
        put(-4, NO_USER_PASSWORD);
        put(-5,USER_SERVER_ERROR);
        put(-6,USER_PARAM_ERROR);
    }};
    int code;
    String message;

    UserEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static UserEnum queryUserMessage(int code) {
        return userEnumMap.get(code);
    }
}
