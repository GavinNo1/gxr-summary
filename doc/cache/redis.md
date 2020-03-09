redis哨兵机制：
    
    https://juejin.im/post/5b7d226a6fb9a01a1e01ff64
    哨兵机制解决了redis主节点的高可用，但是Sentinel本身出现故障 只能通过报警人工解决。
    使用哨兵无法解决写操作无法负载均衡、存储能力受到单机的限制的问题。

redis 集群机制
    
    slot槽 一致性哈希 扩容 缩容 写能力扩展，高可用（主备）

redis 落盘机制
    
    https://juejin.im/post/5d776dcef265da03b574744d
   
redis 缓存穿透问题
    
    https://www.iteye.com/blog/carlosfu-2248185

redis 缓存击穿
    
    https://www.iteye.com/blog/carlosfu-2249316 
    第一，做好 cache的高可用 第二 后端做好限流降级方案。
    
redis hot key
    
    https://www.iteye.com/blog/carlosfu-2269687

redis 大key问题
    
    https://www.javazhiyin.com/41124.html
    https://my.oschina.net/grace233/blog/2993840
    https://cloud.tencent.com/developer/article/1517399

redis db 一致性
    
    先写db 在删除 cache 删除不成功重试 放入不成功的集合  后台开一个线程，定时检查 清除cache
    同时开一个线程定时从数据库更新cache中失效的数据（先检查 再加锁 hold 防止业务线程并发更新，更数据造成压力）
    
本地缓存
    
    用于hot key 对redis的流量冲击，检测到hot key 访问本地缓存（guava encache  lru-2（缓存污染）），（访问计数，后台开线程检查是否hot key 更新本地缓存 再开线程1s清除一下计数）
    https://toutiao.io/posts/56yqwd/preview
    https://www.taowong.com/blog/2018/07/07/distributed-system-cache-3.html

本地缓存一致性

    zk+netty 数据更新清除

注意：保证 redis db一致性与  保证多机本地缓存一致性 （分开保证） 刷新（1s）本地缓存时可以使用redis暂时服务热点数据（1s 10w tps）

`缓存使用 一般都是主动清除+定时过期。`

该段话 很受启发 
    
    一般来说，在我们的通信模型里存在某种不对称性。这种不对称性包括，访问不同介质的速度差异，访问本地和网络资源的速度差异，以及读和写的频率差异。
    我们利用此种不对称结构提出的设计原则非常简单粗暴 但是很实用  所以在设计系统的时候 也要有所取舍 面面俱到也意味着隐藏了弊端，埋下一颗雷。
    
    
cache觉得比较好的链接

    https://database.51cto.com/art/201808/580720.htm
    https://tech.meituan.com/2017/03/17/cache-about.html
    http://blog.yangliu.online/2016/10/18/distributed-consistency-and-caching-techniques/
    https://tech.youzan.com/tmc/


