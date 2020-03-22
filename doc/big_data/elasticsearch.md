es架构
    
    https://juejin.im/post/5d351143f265da1bd04f1e19#heading-16
    
es write

    https://blog.csdn.net/R_P_J/article/details/82254494
    https://www.infoq.cn/article/HAdBrlW6FAO0FmshXKpZ
es 写优化
    
    加大translog flush间隔，降低iops，writeblock
    加大index refresh间隔，除了降低io，更重要的是降低segment merge频率。
    调整bulk请求
    优化磁盘间任务均衡情况，将shard尽量均匀分布在物理主机的各个磁盘。
    优化节点间的任务分布，将任务尽量均匀的发到各节点。
    优化lucene层建立索引的过程，目的是降低cpu占用率以及io，例如 禁用_all字段。
es read
    
    https://zhuanlan.zhihu.com/p/33671444
    https://zhuanlan.zhihu.com/p/35814539
    https://zhuanlan.zhihu.com/p/47951652
    https://zhuanlan.zhihu.com/p/49206974
    https://blog.csdn.net/zhanglh046/article/details/78536143
    https://www.infoq.cn/article/elasticsearch-performance-tuning-practice-at-ebay

es 深度分页问题
    
    http://arganzheng.life/deep-pagination-in-elasticsearch.html
    
es集群规划
    
    https://blog.csdn.net/laoyang360/article/details/85109769
    https://blog.csdn.net/laoyang360/article/details/103545432
    https://blog.csdn.net/laoyang360/article/details/78080602
    https://blog.csdn.net/laoyang360/article/details/102539888