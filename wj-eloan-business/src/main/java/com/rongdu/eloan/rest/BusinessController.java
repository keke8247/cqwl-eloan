package com.rongdu.eloan.rest;

import com.rongdu.eloan.service.OrderService;
import com.rongdu.eloan.service.StorageService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/10 17:06
 * @Since version 1.0.0
 */
@RestController
public class BusinessController {
    private static final Logger logger = LoggerFactory.getLogger(BusinessController.class);

    private static final String SUCCESS = "success";

    private static final String FAIL = "fail";

    private static final String USER_ID = "U100001";

    private static final String COMMODITY_CODE = "C00321";

    private static final int ORDER_COUNT = 2;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StorageService storageService;

    @Autowired
    private OrderService orderService;

    @GlobalTransactional(timeoutMills = 300000, name = "spring-cloud-demo-tx")
    @GetMapping(value = "/seata/rest", produces = "application/json")
    public String rest() {

        String result = restTemplate.getForObject(
                "http://127.0.0.1:10083/storage/" + COMMODITY_CODE + "/" + ORDER_COUNT,
                String.class);

        if (!SUCCESS.equals(result)) {
            throw new RuntimeException();
        }

        String url = "http://127.0.0.1:10082/order";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("userId", USER_ID);
        map.add("commodityConde", COMMODITY_CODE);
        map.add("orderCount", ORDER_COUNT + "");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(
                map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request,
                String.class);

        result = response.getBody();

        if (!SUCCESS.equals(result)) {
            throw new RuntimeException();
        }

        return SUCCESS;
    }

    @GlobalTransactional(timeoutMills = 300000, name = "spring-cloud-demo-tx")
    @GetMapping(value = "/seata/feign", produces = "application/json")
    public String feign() {

        logger.info("/seata/feign start........xid:{}", RootContext.getXID());
        String result = storageService.echo(COMMODITY_CODE, ORDER_COUNT);

        if (!SUCCESS.equals(result)) {
            throw new RuntimeException(result);
        }
        logger.info("storage 执行完成......xid:{}", RootContext.getXID());


        result = orderService.order(USER_ID, COMMODITY_CODE, ORDER_COUNT);

        logger.info("order 执行完成......xid:{}", RootContext.getXID());

        if (!SUCCESS.equals(result)) {
            throw new RuntimeException();
        }

        return SUCCESS;
    }
}
