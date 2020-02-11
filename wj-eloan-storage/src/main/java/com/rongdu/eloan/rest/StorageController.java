package com.rongdu.eloan.rest;

import com.rongdu.eloan.domain.Storage;
import com.rongdu.eloan.service.StorageService;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/10 16:50
 * @Since version 1.0.0
 */
@RestController
public class StorageController {
    private static final Logger logger = LoggerFactory.getLogger(StorageController.class);

    @Autowired
    private StorageService storageService;

    @GetMapping(value = "/storage/{commodityCode}/{count}", produces = "application/json")
    @Transactional(rollbackFor = Exception.class)
    public String echo(@PathVariable String commodityCode,@PathVariable int count){
        logger.info("Storage Service Begin ... xid: {}", RootContext.getXID());

        Storage storage = new Storage();
        storage.setCommodityCode(commodityCode);
        storage.setCount(count);

        int result = storageService.updateStorage(storage);

        logger.info("Storage Servcie End ... ");

        if(result == 1){
            return "success";
        }
        return  "fail";
    }
}
