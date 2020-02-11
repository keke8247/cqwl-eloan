package com.rongdu.eloan.service;

import com.rongdu.eloan.domain.Order;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/10 16:30
 * @Since version 1.0.0
 */
public interface OrderService {
    Long createOrder(Order order);
}
