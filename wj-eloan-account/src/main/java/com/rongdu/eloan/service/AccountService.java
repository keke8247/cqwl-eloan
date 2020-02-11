package com.rongdu.eloan.service;

import com.rongdu.eloan.domain.AccountInfo;
import com.rongdu.eloan.service.dto.AccountDTO;
import org.springframework.cache.annotation.CacheConfig;

import java.util.List;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/9 14:05
 * @Since version 1.0.0
 */
public interface AccountService {

    AccountDTO findById(Long id);

    AccountDTO create(AccountInfo resources);

    int update(AccountDTO resources);

    void delete(Long id);

    List<AccountDTO> queryAccounts();
}
