package com.foxconn.npbg.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public Integer setAdd(String key, Set<String> list) throws Exception{
        String[] records = new String[list.size()];
        try {
            boolean keyExist = redisTemplate.hasKey(key);
            int count = redisTemplate.opsForSet().add(key, list.toArray(records)).intValue();
            // 如果一开始该key不存在，则设置7天有效期
            if (!keyExist)
                redisTemplate.expire(key, 7, TimeUnit.DAYS);
            return count;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("redis存储或设置过期时间出现错误");
        }

    }

    public Set<String> setMembers(String key) throws Exception{
        try {
            return redisTemplate.opsForSet().members(key);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("redis获取集合所有元素失败");
        }
    }
}
