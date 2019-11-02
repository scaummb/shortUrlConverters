package com.core.utils.redis;

import com.core.constants.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisTemplateFactory {
    final static Logger LOGGER = LoggerFactory.getLogger(RedisTemplateFactory.class);

    @Autowired
    private SystemConfig systemConfig;

    private static RedisTemplate redisTemplate;

    /**
     * 创建redisTemplate
     */
    public RedisTemplate createRedisTemplate() {
        LOGGER.info("[create redis template] systemConfig = {}", systemConfig.getRedisUrl());
        try {
            long startTime = System.currentTimeMillis();
            JedisConnectionFactory factory = new JedisConnectionFactory();
            factory.setUsePool(true);
            factory.setHostName(systemConfig.getRedisUrl().split(":")[0]);
            factory.setPort(Integer.valueOf(systemConfig.getRedisUrl().split(":")[1]));
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setBlockWhenExhausted(true);
            poolConfig.setTestOnBorrow(true);
            poolConfig.setMaxTotal(-1);
            factory.setPoolConfig(poolConfig);
            factory.afterPropertiesSet();

            RedisTemplate template = new RedisTemplate();
            RedisSerializer<String> stringSerializer = new StringRedisSerializer();
            template.setKeySerializer(stringSerializer);
            template.setHashKeySerializer(stringSerializer);
            template.setValueSerializer(stringSerializer);
            template.setHashValueSerializer(stringSerializer);
            template.setEnableDefaultSerializer(true);
            template.setConnectionFactory(factory);
            template.afterPropertiesSet();

            return template;
        } catch (Exception e){
            LOGGER.error("RedisTemplateFactory create redisTemplate error, redisUri={}", new SystemConfig().getRedisUrl(), e);
        }
        return null;
    }

    /**
     * 工厂方法获取redisTemplate
     */
    public RedisTemplate getRedisTemplate() {
        if (redisTemplate == null){
            redisTemplate = this.createRedisTemplate();
            return redisTemplate;
        } else {
            return redisTemplate;
        }
    }

}
