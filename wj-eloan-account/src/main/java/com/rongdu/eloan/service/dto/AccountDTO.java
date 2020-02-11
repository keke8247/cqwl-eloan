package com.rongdu.eloan.service.dto;

import lombok.Data;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/9 14:05
 * @Since version 1.0.0
 */
@Data
public class AccountDTO {
    private Long id;
    private String userId;
    private int money;
}
