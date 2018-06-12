package com.renrun.supplychain.app.RedisSerializer;

import com.alibaba.fastjson.JSON;
import com.renrun.supplychain.ucenter.share.entity.UserPermPermTree;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by swk on 2017/5/20.
 * jd.wang@renrunkeji.com
 */
public class UserPermPermTreeSerializer implements RedisSerializer<List<UserPermPermTree>> {

    @Override
    public byte[] serialize(List<UserPermPermTree> t) throws SerializationException {
        return JSON.toJSONBytes(t);
    }

    @Override
    public List<UserPermPermTree> deserialize(byte[] bytes) throws SerializationException {
        if(bytes == null){
            return null;
        }

        String s = new String(bytes, UTF_8);
        return JSON.parseArray(s, UserPermPermTree.class);
    }
}
