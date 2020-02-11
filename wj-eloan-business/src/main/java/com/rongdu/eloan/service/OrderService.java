package com.rongdu.eloan.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/10 17:08
 * @Since version 1.0.0
 */
@FeignClient(name = "wj-eloan-order-service")
public interface OrderService {
    @PostMapping(value = "/order",produces = "application/json")
    String order(@RequestParam(value = "userId") String userId,
                 @RequestParam(value = "commodityConde") String commodityConde,
                 @RequestParam(value = "orderCount") int orderCount);
}