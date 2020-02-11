package com.rongdu.eloan.mapper;

import com.rongdu.eloan.domain.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/10 16:32
 * @Since version 1.0.0
 */
@Mapper
public interface OrderMapper {
    Long insertOrder(Order order);
}
