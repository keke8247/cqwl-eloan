package com.rongdu.eloan.filter;

import io.netty.buffer.UnpooledByteBufAllocator;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Description
 * 记录日志时通常关注请求URI、Method、QueryString、POST请求的Body、响应信息和来源IP等。
 * 对于Spring Cloud Gateway这其中的POST请求的Body获取比较复杂，这里添加一个全局过滤器预先获取并存入请求的Attributes中。
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2020/1/17 14:50
 * @Since version 1.0.0
 */
//@Component
public class CachePostBodyFilter implements GlobalFilter,Ordered{
    private static final String POST = "POST";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();

        String method = serverHttpRequest.getMethodValue();
        if(POST.equalsIgnoreCase(method)){ //对Post请求做处理
            ServerRequest serverRequest = ServerRequest.create(exchange, HandlerStrategies.withDefaults().messageReaders());
            Mono<String> bodyToMono = serverRequest.bodyToMono(String.class);
            return bodyToMono.flatMap(body ->{
                exchange.getAttributes().put("cacheRequestBody",body);
                ServerHttpRequest newRequest = new ServerHttpRequestDecorator(serverHttpRequest){
                    @Override
                    public HttpHeaders getHeaders() {
                        HttpHeaders httpHeaders = new HttpHeaders();
                        httpHeaders.putAll(super.getHeaders());
                        httpHeaders.set(HttpHeaders.TRANSFER_ENCODING, "chunked");
                        return httpHeaders;
                    }

                    @Override
                    public Flux<DataBuffer> getBody() {
                        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));
                        DataBuffer bodyDataBuffer = nettyDataBufferFactory.wrap(body.getBytes());
                        return Flux.just(bodyDataBuffer);
                    }
                };
                return chain.filter(exchange.mutate().request(newRequest).build());
            });
        }
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
