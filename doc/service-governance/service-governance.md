服务治理设计的方面

https://www.iteye.com/blog/javatar-1345073

个人关注的点 服务发现以及注册， 服务路由，服务软负载均衡与容错，服务的容量（tps qps等），服务的限流降级（服务的重要程度），服务的依赖关系（调用链路等）， 服务的日志归集，
服务的上线以及下线审核，服务的自动化部署 等等。 

rpc框架核心代码
https://www.iteye.com/blog/javatar-1123915

服务治理涉及到组件

    涉及到rpc组件 如dubbo
    涉及到注册中心 zk（cp） 或者其他如nacos（ap）
    涉及到网络框架 netty
    涉及到的限流组件 如 sentinel 令牌桶 漏斗算法        
    涉及到全链路跟踪组件 sofatracer
    涉及到日志归集组件
    涉及到告警组件
    
    