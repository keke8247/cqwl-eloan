package com.rongdu.eloan.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * 熔断降级处理Controller
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/17 14:16
 * @Since version 1.0.0
 */
@RestController
public class FallbackController {

    @RequestMapping("/defaultFallback")
    public ResponseEntity defaultFallback(){
        Map map = new HashMap();
        map.put("code","400");
        map.put("msg","服务异常");
        return new ResponseEntity(map, HttpStatus.FORBIDDEN);
    }
}
