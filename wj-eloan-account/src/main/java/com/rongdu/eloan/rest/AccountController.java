package com.rongdu.eloan.rest;

import com.rongdu.eloan.domain.AccountInfo;
import com.rongdu.eloan.service.AccountService;
import com.rongdu.eloan.service.dto.AccountDTO;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/9 14:26
 * @Since version 1.0.0
 */
@RestController
@RequestMapping("api")
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private static final String SUCCESS = "success";

    private static final String FAIL = "fail";

    private Random random = new Random();

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/create")
    public ResponseEntity create(@RequestBody AccountInfo resources){
        Assert.isNull(resources.getId(),"新增用户,cannot have an ID");

        return new ResponseEntity(accountService.create(resources), HttpStatus.CREATED);
    }

    @GetMapping("/getAccount")
    public ResponseEntity getAccount(@RequestParam(value = "id") Long id){
        return new ResponseEntity(accountService.findById(id),HttpStatus.OK);
    }

    @PostMapping(value = "/account",produces = "application/json")
    public String account(String userId,int money){
        logger.info("Account Service ... xid: {}", RootContext.getXID());

        //随机报错
        if(random.nextBoolean()){
            throw new RuntimeException("this is a mock Exception");
        }

        //扣减金额
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserId(userId);
        accountDTO.setMoney(money);

        int result = accountService.update(accountDTO);

        if(result == 1){
            return SUCCESS;
        }

        return FAIL;
    }

    //使用路径参数方式入参 {message} 接收参数的时候必须使用@PathVariable注解接收参数. 否则获取不到参数
    @GetMapping(value="/echo/{message}")
    public String echo(@PathVariable(value = "message") String message){
        return "Hello message:"+message;
    }
}
