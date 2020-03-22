分布式一致性原理
    
    http://cmsblogs.com/?p=4111
    http://cmsblogs.com/?p=4113
    http://cmsblogs.com/?p=4115
    http://cmsblogs.com/?p=4178
    
分布式锁原理
    
    http://cmsblogs.com/?p=4117
    https://blog.csdn.net/zhousenshan/article/details/79935714

选举原理：
    
    http://cmsblogs.com/?p=4117


watch说明:
    
    触发一次，服务端即移除，使用需要重复注册（设计成这个样子的原因是 zk觉得触发watch的事件是不频繁的）。watch与服务端是长连接。

zk脑裂

    1.ZooKeeper默认采用了Quorums这种方式，假设某个leader假死，其余的followers选举出了一个新的leader。这时，旧的leader复活并且仍然认为自己是leader，这个时候它向其他followers发出写请求也是会被拒绝的。因为每当新leader产生时，会生成一个epoch，这个epoch是递增的，followers如果确认了新的leader存在，知道其epoch，就会拒绝epoch小于现任leader epoch的所有请求。那有没有follower不知道新的leader存在呢，有可能，但肯定不是大多数，否则新leader无法产生。Zookeeper的写也遵循quorum机制，因此，得不到大多数支持的写是无效的，旧leader即使各种认为自己是leader，依然没有什么作用。
    2.机器数量 2n+1，部署 两地三中心或者三地五中心，增加机器 需要修改zoo.cfg配置，然后逐台重启。

zk读一致性
    
    ZooKeeper并不保证在每个实例中，两个不同的客户端将具有相同的ZooKeeper数据的视图。由于诸如网络延迟的因素，一个客户端可以在另一客户端被通知该改变之前执行更新，考虑两个客户端A和B的场景。如果客户端A将znode / a的值从0设置为1，则告诉客户端B读取/ a，则客户端B可以读取旧值0，这取决于它连接到的服务器。如果客户端A和客户端B读取相同的值很重要，则客户端B应该在执行读取之前从ZooKeeper API方法调用sync()方法。
    因此，ZooKeeper本身不保证所有服务器上同步发生变化，但ZooKeeper原语可用于构建更高级的函数，提供有用的客户端同步。
    zk的sync方法的解释：异步的实现当前进程与leader之间的指定path的数据同步；
session
    
    指zk客户端与zk服务器之间的会话，在zk中，会话是通过客户端和服务器之间的一个TCP长连接来实现的。通过这个长连接，客户端能够使用心跳检测与服务器保持有效的会话，也能向服务器发送请求并接收响应，还可接收服务器的Watcher事件通知。Session的sessionTimeout，是会话超时时间，如果这段时间内，客户端未与服务器发生任何沟通（心跳或请求），服务器端会清除该session数据，客户端的TCP长连接将不可用，这种情况下，客户端需要重新实例化一个Zookeeper对象。

_`Curator客户端必备。`_

zk cp 不完全适合做为注册中心的原因
    
    http://jm.taobao.org/2018/06/13/%E5%81%9A%E6%9C%8D%E5%8A%A1%E5%8F%91%E7%8E%B0%EF%BC%9F/

应用场景：
    
    数据发布/订阅，配置中心，分布式锁，选主与服务发现
    