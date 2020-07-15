package com.test.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String paymentInfo_ok(Integer id) {
        return "线程池:" + Thread.currentThread().getName() + "paymentInfo_ok, id=" + id;
    }


    /**
     * 服务降级超时时间设置为3秒, 超过3秒就进行服务降级处理
     *
     * 超时访问
     * HystrixCommand:一旦调用服务方法失败并抛出了错误信息后,
     *      会自动调用@HystrixCommand标注好的fallbckMethod调用类中的指定方法
     *
     * execution.isolation.thread.timeoutInMilliseconds:线程超时时间3秒钟
     */
    @HystrixCommand(fallbackMethod = "timeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    @Override
    public String paymentInfo_timeout(Integer id) {
        //报错测试
        int i = 10/0;

        //超时测试
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        return "线程池:" + Thread.currentThread().getName() + "paymentInfo_timeout, id=" + id;
    }


    //服务降级处理方法
    public String timeoutHandler(Integer id){
        return "线程池:" + Thread.currentThread().getName() +  " 超时或运行报错 handler id:" + id;
    }




}
