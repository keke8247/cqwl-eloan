package com.rongdu.eloan.service.mapper;

import com.rongdu.eloan.domain.AccountInfo;
import com.rongdu.eloan.service.dto.AccountDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/9 16:39
 * @Since version 1.0.0
 */
@Mapper
public interface AccountMybatisMapper {
    List<AccountDTO> queryAccounts();
    
    int updateByUserId(AccountDTO accountInfo);
}
