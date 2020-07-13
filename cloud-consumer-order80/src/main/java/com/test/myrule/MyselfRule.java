package com.test.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 自定义ribbon负载均衡策略
 *      该配置类不能放在@ComponentScan所能扫描到的地方,
 *      否则这个自定义配置类就会被所有ribbon客户端共享,达不到特殊定制化得目的
 */
@Configuration
public class MyselfRule {

    @Bean
    public IRule myRule(){
        return new RandomRule();
    }
}
