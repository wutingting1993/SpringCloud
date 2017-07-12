package com.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by NCP-605 on 2017/7/11.
 */
@FeignClient("SERVICE-SUPPORT")
public interface RefactorHelloService {

}
