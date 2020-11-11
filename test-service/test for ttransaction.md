## test for @Transactional
spring transactional 注解失效情况

首先注解只能加载public 方法之上

### 1) 内部方法调用
service 主方法没有事务注解 方法内部调用 同类下的事务方法，，注解失效

### 2) 没有指定异常
默认如果加指定rollbackFor的话 抛出RuntimeException 会回滚,但是如果出现其他异常不会回滚

### 3) 自定做了try catch 
自己做了try catch 异常操作 ,然后不抛出异常,不会回滚