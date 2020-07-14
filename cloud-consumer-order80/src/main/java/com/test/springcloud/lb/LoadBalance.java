package com.test.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * 负载均衡接口
 */
public interface LoadBalance {

    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
