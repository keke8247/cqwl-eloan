package com.rongdu.eloan.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/10 16:20
 * @Since version 1.0.0
 */
@Data
public class Order implements Serializable {

    /**
     * id.
     */
    public long id;

    /**
     * user id.
     */
    public String userId;

    /**
     * commodity code.
     */
    public String commodityCode;

    /**
     * count.
     */
    public int count;

    /**
     * money.
     */
    public int money;

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", userId='" + userId + '\'' + ", commodityCode='"
                + commodityCode + '\'' + ", count=" + count + ", money=" + money + '}';
    }

}

