package com.rongdu.eloan.system.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/6 10:51
 * @Since version 1.0.0
 */
@RestController
public class SystemUserController {
    @GetMapping("/userInfo")
    public ResponseEntity userInfo(@RequestParam("userId") String userId){
        return new ResponseEntity("this is userID:"+userId+" message", HttpStatus.OK);
    }

    @PostMapping("/saveUser")
    public ResponseEntity saveUser(@RequestParam String userStr){
        return new ResponseEntity("save success!",HttpStatus.OK);
    }

    @GetMapping("/echo/{string}")
    public String echo(@PathVariable String string) {
        return "hello this is systemUserService " + string;
    }
}
