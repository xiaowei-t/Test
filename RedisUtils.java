package com.iflytek.springredis.untils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据key读取数据
     */
    public  Object get( String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        try {
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 写入数据
     */
    public  boolean set(String key, Object value) {
        if (StringUtils.isBlank(key)) {
            return false;
        }
        try {
            redisTemplate.opsForValue().set(key, value);
            log.info("存入redis成功，key：{}，value：{}", key, value);
            return true;
        } catch (Exception e) {
            log.error("存入redis失败，key：{}，value：{}", key, value);
            e.printStackTrace();
        }
        return false;
    }
}