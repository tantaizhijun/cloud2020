


1. order服务 + payment服务
2. 抽取common公共包, 搭建eureka7001单机服务
3. payment8001 注册进单机版eureka7001
4. order80注册进7001
5. 搭建eureka集群(7001+7002), 
6. order80 和 payment8001 注册进eureka集群
7. payment增加集群节点8002, order80调用集群服务(负载均衡),对RestTemplate增加负载均衡功能
8. payment8004注册进zookeeper注册中心,(没有进行外部调用测试)



