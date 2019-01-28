package com.graduation.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * @param <T>
 * @author fuxiaoxiang2
 * @description 自定义基于fastJson的  序列化规则 fastjson序列化时将class信息写入，反解析的时候，
 * fastjson默认情况下会开启autoType的检查，相当于一个白名单检查吧，如果序列化信息中的类路径不在autoType中，
 * 反解析就会报com.alibaba.fastjson.JSONException: autoType is not
 * @create 2019/01/07
 */
public class FastJsonSerializer<T> implements RedisSerializer<T> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    static {//设置autoType白名单
        ParserConfig.getGlobalInstance().addAccept("com.graduation.domain.bo");
    }

    private Class<T> clazz;

    public FastJsonSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (null == t) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (null == bytes || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);
        return JSON.parseObject(str, clazz);
    }

}

