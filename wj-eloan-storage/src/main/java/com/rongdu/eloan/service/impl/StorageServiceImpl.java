package com.rongdu.eloan.service.impl;

import com.rongdu.eloan.domain.Storage;
import com.rongdu.eloan.mapper.StorageMapper;
import com.rongdu.eloan.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/10 16:57
 * @Since version 1.0.0
 */
@Service
public class StorageServiceImpl implements StorageService{

    @Autowired
    private StorageMapper storageMapper;

    @Override
    public int updateStorage(Storage storage) {
        return storageMapper.updateStorage(storage);
    }
}
