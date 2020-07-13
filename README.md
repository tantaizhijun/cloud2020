

--基础服务
1. order服务 + payment服务
2. 抽取common公共包, 搭建eureka7001单机服务

--eureka
3. payment8001 注册进单机版eureka7001
4. order80注册进7001
5. 搭建eureka集群(7001+7002), 
6. order80 和 payment8001 注册进eureka集群
7. payment增加集群节点8002, order80调用集群服务(负载均衡),对RestTemplate增加负载均衡功能

--zookeeper
8. payment8004注册进`zookeeper单机版`注册中心,(没有进行外部调用测试)
9. orderZK80注册进zk单机版,并调用payment8004

--consul
分布式的服务发现和配置管理
10. payment8006注册进consul
11. consul order80注册进consul, 并调用payment8006





#服务调用
order80 --> payment8001/8002  | eureka7001/7002

