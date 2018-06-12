package com.renrun.supplychain.app.RedisSerializer;

import com.alibaba.fastjson.JSON;
import com.renrun.supplychain.ucenter.share.entity.UserDicCode;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by swk on 2017/5/21.
 * jd.wang@renrunkeji.com
 */
public class DicCodeSerializer implements RedisSerializer<List<UserDicCode>> {

    @Override
    public byte[] serialize(List<UserDicCode> t) throws SerializationException {
        return JSON.toJSONBytes(t);
    }

    @Override
    public List<UserDicCode> deserialize(byte[] bytes) throws SerializationException {
        if(bytes == null){
            return null;
        }

        String s = new String(bytes, UTF_8);
        return JSON.parseArray(s, UserDicCode.class);
    }
}
