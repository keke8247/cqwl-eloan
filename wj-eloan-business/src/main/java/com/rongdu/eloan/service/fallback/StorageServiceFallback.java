package com.rongdu.eloan.service.fallback;

import com.rongdu.eloan.service.StorageService;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/2/11 11:25
 * @Since version 1.0.0
 */
@Component
public class StorageServiceFallback implements StorageService{
    @Override
    public String echo(String commodityCode, int count) {
        return "storage 服务挂了,请稍后重试!";
    }
}
