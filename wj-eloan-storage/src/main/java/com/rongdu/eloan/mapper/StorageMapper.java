package com.rongdu.eloan.mapper;

import com.rongdu.eloan.domain.Storage;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/10 16:57
 * @Since version 1.0.0
 */
@Mapper
public interface StorageMapper {
    int updateStorage(Storage storage);
}
