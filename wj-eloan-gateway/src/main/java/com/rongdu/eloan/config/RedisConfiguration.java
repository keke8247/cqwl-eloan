package com.rongdu.eloan.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/17 13:40
 * @Since version 1.0.0
 */
@Configuration
public class RedisConfiguration {

    /**
     * @Description:
     * 由于SpringBoot 自动配置的RedisTemplate生成key的时候会包含特殊字符,所以自己创建一个RedisTemplate来替换SpringBoot生成的
    */
    @Bean
    public RedisTemplate redisTemplate(@Value("${spring.redis.host}")String host,@Value("${spring.redis.port}") int port){
        RedisTemplate redisTemplate = new RedisTemplate();
        RedisSerializer stringSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);

        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.setConnectionFactory(standaloneConnectionFactory(host, port));
        return  redisTemplate;
    }

        protected JedisConnectionFactory standaloneConnectionFactory(String host,int port){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }
}
