package com.graduation.common.enums;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 返回参数枚举
 * @Author fuxiaoxiang2
 * @Create 2019/1/17 16:32
 */
public enum  ApiEnum {
    SUCCESS(200,"成功"),
    NO_SOURCE(401,"请求资源不存在"),
    OPERATION_FAILED(407,"失败"),
    NO_LOGIN(407,"未登录"),
    SERVER_ERROR(501,"服务端错误");
    private Integer code;
    private String msg;
    ApiEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
