package com.rongdu.eloan.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/17 13:52
 * @Since version 1.0.0
 */
@Configuration
public class RateLimiterConfiguration {

    /**
     * 自定义根据访问ip限流.
     * */
    @Bean(value = "ipKeyResolver")
    public KeyResolver ipKeyResolver(){
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }

}
