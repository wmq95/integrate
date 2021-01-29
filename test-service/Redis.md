# Redis

## 一致性hash算法 consistent hashing

https://blog.csdn.net/cywosp/article/details/23397179/



​	按照常用的算法讲key 哈希到一个指定的2^32次方的桶中，形成闭环。

![image-20201123152801884](F:\mdNotes\images\image-20201123152801884.png)

对于对象object1，object2，Object3进行hash 分布到环上

![image-20201123152910110](F:\mdNotes\images\image-20201123152910110.png)

然后在用相同hash 对机器进行哈希，机器node1，node2，node3

![image-20201123152954106](F:\mdNotes\images\image-20201123152954106.png)

按照顺时针原则，存储对象。

### 机器节点的删除添加

当某个节点删除时，无需对多有的对象重新进行hash 只需要把对象的一小部分进行迁移，比如删除node2，只需要把node和node1之间的对象分布到node3上

新增节点，node4 进行hash ，把对应的对象分不到node4上面

![image-20201123153255014](F:\mdNotes\images\image-20201123153255014.png)

### 平衡性

上述情况，还不满足平衡性，如果只有node1，和node3 会发现对象存储很不平衡，大多分布在node3上面，解决方案，虚拟节点--虚拟节点就是把所以真实节点做一个简单的的假象复制，然后分布在环上。

![image-20201123153637695](F:\mdNotes\images\image-20201123153637695.png)

## Redis Cluster

不同于上述一致性hash算法，redis Cluster  采用的一种hash slot(槽位)概念。

在cluster中一共有16384个槽位，对集群个数进行取余，然后分配不同槽位上的hash数据

如果三个机器节点A,B,C;

A 的槽位0~~~~5500；

B 的槽位5501~~~~11000；

C的槽位11001~~~~16383；

对一个对象进行hash 然后分不到不同的节点上。

### 添加节点

加入一个节点D， 那么槽位的余数由3改到4 那么每个节点槽位范围相应缩小，然后槽位上的部分数据进行迁移，默认槽位是平均分配的，键入节点会把A，B，C的部分数据槽到新的节点上去。

### 删除节点

删除节点，把该节点的数据槽到对象节点上

### master/slave 主从模型

redis Cluster 保留了之前redis 的主从模型，因为 在分布式存储场景下，如果某个节点崩溃，会导致整个redis集群都不能正常工作，所有可以对节点在进行一个主从全量复制，增加可用性。

但是同时 redis Cluster 并不保证一致性。因为主从采用的是异步，就算采用同步备份也不能完全保证一致性

## Spring cache 集成

和springCache 集成简单 但是使用cahce自带的cacheAble，cachePut 注解有几点注意

1	只能加在public 方法上

2	同方法内部调用不会生效（类似于事务失效的原因），在service中 A方法调用缓存注解B方法 没有生效

3	注解可以加在接口上，但是需要在spring中定义key生成规则，不定义话 key会是空是null， 会报错