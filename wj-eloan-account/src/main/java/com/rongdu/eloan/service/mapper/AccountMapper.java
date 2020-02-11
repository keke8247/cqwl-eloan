package com.rongdu.eloan.service.mapper;

import com.rongdu.eloan.domain.AccountInfo;
import com.rongdu.eloan.service.dto.AccountDTO;
import com.rongdu.eloan.util.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/9 14:13
 * @Since version 1.0.0
 */
@Mapper(componentModel = "spring", uses = {}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper extends EntityMapper<AccountDTO,AccountInfo>{

}
