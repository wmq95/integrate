# TestService服务搭建

## 流程

1)	banner 和logback 都放在了web-core模块resource 目录下，这个原理就相当于SpringBoot启动的时候会扫描所有jar包resource/spring.factories
文件，查找自动装配类一样。

2）纠结mysql 依赖和mybatisPlus的依赖是否存放在web-core中，最后还是没有放入其中
其实也很简单，因为有些web服务也许并不需要用到mysql等，就譬如还想搭建的zuul，gateway等等 他们可以选择使用mysql或者不用

3）mysql id自增还是使用UUID?
https://www.cnblogs.com/jpfss/p/11506816.html
从实际开发场景来说，需要提前知道记录id 所以采用UUID形式，使用雪花ID（计算变成大型分布式也能使用）

4）orm使用mybatisPlus; 使用codeGenerator生成model 还是很方便的

5）自定义全局异常和返回值格式

6）BO/DTO/PARAM/QUERY：集成dozer，作为实体转化工具

```word
6.1）dozer配置文件默认放在了common-util中，使用spring的服务中可以新建对应文件达到覆盖效果
因为读取配置文件的时候使用resourceLoader读取文件, 
loadResource(),读取文件，其中有一个findResource(name)方法，
大致就是会先从本服务的jar包路径下去读取文件 ，然后再去其他jar包路径下读取文件

6.2）dozer 使用map 基于反射，总归比get/set更消耗性能
https://mp.weixin.qq.com/s?__biz=MzIzMzgxOTQ5NA==&
mid=2247506810&idx=2&sn=4796b6febb59b0e96a76f57f515bbf47
&chksm=e8fd4773df8ace650ee22d3194482cb131cbd722b7b6e6795bf9edd56f317d20c5bb0711da41&scene=
126&sessionid=1601455719&key=91d26c6f1e2e4148d57956cfc14c338474fd0d6d9
f67ff027c0ee3c6a721f88830abb68d74467e95c7a540ad350aaa63d69897a0e984b9efee
ab25e46f45f35021eff0ee06a5cf6ff154dd189b57a1aa7942ded0de0f1d745efa0e399e5
8937925824d156f13274075c412a645d757365fc8adc78e26566dd1e219e8f30d7aae&ascene=1
&uin=MTk0OTY0Mjg2MQ%3D%3D&devicetype=Windows+10+x64&version=63000016&lang=zh_CN&exp
ortkey=AWOkbHNYJknFS53LT6S3eSY%3D&pass_ticket=aDfMJNh35pjQVzjJ%2F3xfeVBcRJekyz%2BrdRm2Tu6bjtxiwX%2BUMNtqyOY4yDmaYcEM&wx_header=0

6.3）使用DTO/BO/PARAM优缺点：
优点：清晰，改动方便，符合规范
缺点：在实际开发中总感觉dto/bo等定义过多，而且大多数字段重复，特别是用于查询的时候，各个业务场景不同，虽然查询的主体是一样的，
但是会出现返回不同数据结构的需求，这个时候一般会定义多个DTO，但是大部分字段都是相同的，，总感觉有冗余。。
个人想法：
    采用耦合的方式，假设我有三张表 用户表、订单表、付款表；首先提供三个表的基本信息DTO，
        业务1：查询订单表，返回用户姓名、金额等等===》可以定义一个返回的DTO 里面有上述三个表的dto字段
        缺点：返回数据多余，无用的数据也被返回
        优点：在仅涉及三表业务的场景，改动很小
        业务2：对于新增/更新操作，在实际中其他2个param差不多甚至完全相似，更新比新增的param多一个主键ID，
        对于这种场景，可以直接就是用一个表的DTO（单表新增/修改），这样节省了很多的SaveXXXParam和UpdateXXXParam
```

7）服务采用面向接口编程，，之前都采用param/dto等方式做接口参数，
综合第二点 ，采用接口作为业务参数

## 11.13 规划

1）集成swagger、缓存、队列

缓存采用redis， 使用lettuce客户端。

​	在集成中，key value 采用json格式序列化实体类方式，正好配合dozer 去映射实体类。

​	配到的一个坑点：idea 居然有缓存 之前没遇见过这个bug 重启服务居然还是以前的代码 不是最新的代码，需要自己点击packe 然后运行方法才行。。。。（郁闷）

​	------真相了：在run configuration中 before launch 里设置build

2）集成docker，maven-docker 插件、docker-compose

3）多配置文件，多环境、maven 多profile

4）webFlux reactor 编程

5）配置中心

6）feign 调用-负载均衡

7）链路追踪

8）网关-鉴权、授权

9）分布式事务

10）分布式任务调度

11）应用层 nginx lua语言？

12）分布分表 or myCat？

## 偶然

​	1	在编写controller中方法返回接口，结果也能返回，而且经过测试 会根据不同实现去返回不同的结果，应该是在返回写入body的时候根据了返回值具体的类型 ，然后通过反射获取了fileds和对应的get方法，就是说如果filed没有对应的get方法 结果不会返回。（亲测结果）

---------原因在于@ResponseBody注解和HttpMessageConverter上---------------------------------

​	SpringMVC  默认给我们添加了几个MessageConverter（在WebMvcConfigurationSupport类中），当我们加上了@ResponseBody注解 并且没有配置MessageConvert的时候，

会进入到MappingJackson2HttpMessageConverter这个MessageConverter进行返回值的转换，

里面最后就是使用了jackson ObjectMapper 的write方法 把具体的对象写出去。。。