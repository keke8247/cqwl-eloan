package com.rongdu.eloan.service.impl;

import com.rongdu.eloan.domain.Order;
import com.rongdu.eloan.mapper.OrderMapper;
import com.rongdu.eloan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/10 16:33
 * @Since version 1.0.0
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createOrder(Order order) {
        return orderMapper.insertOrder(order);
    }
}
