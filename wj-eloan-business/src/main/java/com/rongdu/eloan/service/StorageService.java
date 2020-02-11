package com.rongdu.eloan.service;

import com.rongdu.eloan.service.fallback.StorageServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/10 17:08
 * @Since version 1.0.0
 */
@FeignClient(name = "wj-eloan-storage-service",fallback = StorageServiceFallback.class)
public interface StorageService{
    @GetMapping(value = "/storage/{commodityCode}/{count}", produces = "application/json")
    String echo(@PathVariable(value = "commodityCode") String commodityCode, @PathVariable(value = "count") int count);
}