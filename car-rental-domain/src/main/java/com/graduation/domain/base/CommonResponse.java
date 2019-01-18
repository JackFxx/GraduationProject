package com.graduation.domain.base;
import com.graduation.common.enums.ApiEnum;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Descrtiption 标准出参实体
 * @Author fuxiaoxiang2
 * @Create 2019/1/17 16:28
 */
public class CommonResponse implements Serializable {
    private static final long serialVersionUID = 2540189149301031288L;
    private Integer code;
    private String msg;
    private Object data;
    private CommonResponse(){

    }
    public static CommonResponse success(){
        CommonResponse response = new CommonResponse();
        response.setCode(ApiEnum.SUCCESS.getCode());
        response.setMsg(ApiEnum.SUCCESS.getMsg());
        return response;
    }
    public static CommonResponse success(Object object){
        CommonResponse response = new CommonResponse();
        response.setCode(ApiEnum.SUCCESS.getCode());
        response.setMsg(ApiEnum.SUCCESS.getMsg());
        response.setData(object);
        return response;
    }
    public static CommonResponse failed(ApiEnum apiEnum){
        CommonResponse response = new CommonResponse();
        response.setCode(apiEnum.getCode());
        response.setMsg(apiEnum.getMsg());
        return response;
    }
    private void setCode(Integer code) {
        this.code = code;
    }
    private void setMsg(String msg) {
        this.msg = msg;
    }
    private void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
