
payment 服务8004

 服务注册中心为: zookeeper
 
    zk的服务节点是临时的, 过会心跳断了就会删除节点, 心跳重新发送之后, 又会作为新的节点加入
 
 
 1. 排除zookeeper-discovery自带的版本 使用公司安装的版本
 2. 