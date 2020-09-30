##TestService服务搭建

1)banner 和logback 都放在了web-core模块resource 目录下，这个原理就相当于SpringBoot启动的时候会扫描所有jar包resource/spring.factories
文件，查找自动装配类一样。

2）纠结mysql 依赖和mybatisPlus的依赖是否存放在web-core中，最后还是没有放入其中
其实也很简单，因为有些web服务也许并不需要用到mysql等，就譬如还想搭建的zuul，gateway等等 他们可以选择使用mysql或者不用

3）mysql id自增还是使用UUID?
https://www.cnblogs.com/jpfss/p/11506816.html
从实际开发场景来说，需要提前知道记录id 所以采用UUID形式，使用雪花ID（计算变成大型分布式也能使用）

4）orm使用mybatisPlus; 使用codeGenerator生成model 还是很方便的