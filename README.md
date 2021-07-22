# integrate
## 自身涉及到的spring的功能等整合
基于Spring boot，Spring cloud Netflix

##组件
1）数据库方面采用mysql ，orm 使用mybatisPlus

2）对于容器方面，选择了undertow 做为容器

3）对于服务注册：由于eureka 已停止服务，采用阿里的nacos 正好还nacos还能够当作配置中心

4）使用gateway作网关，所有服务基于spring security 保护采用jwt Token 形式，也实现了Oauth2 协议

5）分布式事务采用seata (配置中心方式)实现强一致性，对于高并发场景例如秒杀，采用rocketMq 事务消息机制实现

6）存储引擎使用es

7）日志模块elk

8）监控采用prometheus

##TODO
1 sharding JDBC

##思考

使用mysql 主从复制 数据同步得时候怎么解决数据不一致

分库分表怎么做另一个维度得查询？ 可以使用搜索引擎(ES)




