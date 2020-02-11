package com.rongdu.eloan.rest;

import com.rongdu.eloan.domain.Order;
import com.rongdu.eloan.service.OrderService;
import io.seata.core.context.RootContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/10 16:22
 * @Since version 1.0.0
 */
@RestController
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    private Random random;

    @PostMapping(value = "/order",produces = "application/json")
    @Transactional(rollbackFor = Exception.class)
    public String order(String userId,String commodityConde,int orderCount){
        logger.info("Order Service Begin ... xid: {}", RootContext.getXID());

        int orderMoney = calculate(commodityConde,orderCount);

        invokeAccountService(orderMoney);

        Order order = new Order();
        order.setUserId(userId);
        order.setCommodityCode(commodityConde);
        order.setCount(orderCount);
        order.setMoney(orderMoney);

        Long orderId = orderService.createOrder(order);

        //随机报错
//        if(random.nextBoolean()){
//            throw new RuntimeException("this is a mock Exception");
//        }

        order.setId(orderId);

        logger.info("Order Service End ... Created:{}" ,order);

        if(null != orderId){
            return "success";
        }

        return "fail";
    }

    private int calculate(String commodityId, int orderCount) {
        return 2 * orderCount;
    }

    //调用 账户服务
    private void invokeAccountService(int orderMoney){
        String url = "http://localhost:10081/api/account";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String,String> map = new LinkedMultiValueMap<String,String>();
        map.add("userId", "U100001");
        map.add("money",orderMoney+"");

        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(map,headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url,request,String.class);
    }
}
