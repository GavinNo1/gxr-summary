hbase与传统数据库关系
    
    HBase擅长于存储结构简单的海量数据但索引能力有限，而Oracle等传统关系型数据库(RDBMS)能够提供丰富的查询能力，但却疲于应对TB级别的海量数据存储，HBase对传统的RDBMS并不是取代关系，而是一种补充。
HBase中的数据为何不直接存放于HDFS之上
    
    HBase中存储的海量数据记录，通常在几百Bytes到KB级别，如果将这些数据直接存储于HDFS之上，会导致大量的小文件产生，为HDFS的元数据管理节点(NameNode)带来沉重的压力

文件能否直接存储于HBase里面
    
    缺陷一:无法回避Split和Compaction,写性能较差。之前己经提到过HBase由于大对象的影响,在写入时容易频繁的触发Split和Compaction,由于Split对 于写操作的阻塞和Compaction对于集群I/O的占用,将直接对写性能造成直接的不良影响。缓慢的Compaction操作也会导致Flush的延时,从而阻塞住客户端的更新。
    缺陷二:不稳定的延时。由于Split和Compaction带来的影响,导致其Flush过程被延时从而引发MemStore的不断增长导致客户端的插入被锁住,这种延时一方面难以满足实时系统的低延时要求,另一方面是其不稳定的延时有可能会导致超时异常而引发不必要的重试。
解决之道 MOB

Lsm tree
    
    https://www.cnblogs.com/bangerlee/p/4307055.html
lsm tree 与 b+树 对比
    
    https://juejin.im/entry/5b2b4cb6e51d4558a30528d6    
    
hfile原理解析
    
    http://www.nosqlnotes.com/technotes/hbase/hfile/
    http://hbasefly.com/2016/03/25/hbase-hfile/
    http://hbasefly.com/2016/04/03/hbase_hfile_index/
 
Compaction的本质
    
    减少HFile文件数量，减少文件句柄数量，降低读取时延
    Major Compaction可以帮助清理集群中不再需要的数据（过期数据，被标记删除的数据，版本数溢出的数据）
    影响： 
        Compaction对于查询毛刺的影响（查询时延总是会出现偶发性的陡增）
        Compaction会影响Block Cache，因为HFile文件发生合并以后，旧HFile文件所关联的被Cache的Block将会失效。这也会影响到读取时延

选择合适的Compaction依据
    
    写入数据类型／单条记录大小   
    业务读写比例
    随着时间的不断推移，RowKey的数据分布呈现什么特点？
    数据在读写上是否有冷热特点?
    ​是否只读取/改写最近产生的数据？
    是否有频繁的更新与删除?
    数据是否有TTL限制?
    是否有较长时间段的业务高峰期和业务低谷期？
    写入吞吐量能否满足要求。随着时间的推移，写入吞吐量是否会不断降低？
    读取时延能否满足要求。随着时间的推移，读取时延是否出现明显的增大？
    不断的统计分析Compaction产生的IO总量，以及随着时间的变化趋势
    http://hbasefly.com/2016/07/13/hbase-compaction-1/
    http://hbasefly.com/2016/07/25/hbase-compaction-2/

region split
 
    http://hbasefly.com/2017/08/27/hbase-split/

rit (其实感觉和balance差不多)
    
    http://hbasefly.com/2016/09/08/hbase-rit/

MemStore
    
    实现MemStore模型的数据结构是SkipList（跳表），跳表可以实现高效的查询\插入\删除操作，这些操作的期望复杂度都是O(logN)。另外，因为跳表本质上是由链表构成，所以理解和实现都更加简单。这是很多KV数据库（Redis、LevelDB等）使用跳表实现有序数据集合的两个主要原因。
    优化内存使用：MSLAB、ChunkPool以及Chunk Offheap
    提升内存利用率可以使MemStore中存储下更多的KV数据，进而减少Flush和Compaction发生的频率，提升整个HBase集群的性能
    http://hbasefly.com/2019/10/18/hbase-memstore-evolution/
    http://hbasefly.com/2016/03/23/hbase-memstore-flush/

BlockCache
    
    JVM内存配置量 < 20G，BlockCache策略选择LRUBlockCache
    http://hbasefly.com/2016/04/08/hbase-blockcache-1/
    http://hbasefly.com/2016/04/26/hbase-blockcache-2/
    http://hbasefly.com/2016/05/06/hbase-blockcache-3/
    http://hbasefly.com/2016/06/18/hbase-practise-ram/
GC
    
    http://hbasefly.com/2016/05/21/hbase-gc-1/
    http://hbasefly.com/2016/05/29/hbase-gc-2/
    http://hbasefly.com/2016/08/09/hbase-cms-gc/
    
snapshot（lsm类系统）
    
    1. 加一把全局锁，此时不允许任何的数据写入更新以及删除
    2. 将Memstore中的缓存数据flush到文件中（可选）
    3. 为所有HFile文件分别新建引用指针，这些指针元数据就是snapshot
    http://hbasefly.com/2017/09/17/hbase-snapshot/
       
Region数量问题：
    
    更多的Region意味着会加大HBase集群的负担，尤其是加重Region Assignment流程的负担，
    另外，Region增多，MemStore占用的总体内存变大，而在实际内存无法变大的情况下，只会使得Flush更早被触发，Flush的质量变差

预设合理的数据分片 – Region依据 也是rowkey的设计准则
    
    Key的组成结构
    Key的数据分布预估:如果不能基于Key的组成结构来预估数据分布的话，可能会导致数据在Region间的分布不均匀
    读写并发度需求:依据读写并发度需求，设置合理的Region数量
    http://hbasefly.com/2016/08/22/hbase-practise-cluster-planning/


    
Master主要职责以及hbase架构
    
    负责管理所有的RegionServer
    建表/修改表/删除表等DDL操作请求的服务端执行主体  
    管理所有的数据分片(Region)到RegionServer的分配
    如果一个RegionServer宕机或进程故障，由Master负责将它原来所负责的Regions转移到其它的RegionServer上继续提供服务。
    Master自身也可以作为一个RegionServer提供服务，该能力是可配置的
    http://www.blogjava.net/DLevin/archive/2015/08/22/426877.html
write流程
    
    http://www.nosqlnotes.com/technotes/hbase/hbase-overview-writeflow/
    http://www.nosqlnotes.com/technotes/hbase/flush-compaction/
    http://hbasefly.com/2016/03/23/hbase_writer/
    http://hbasefly.com/2016/12/10/hbase-parctice-write/
read流程

    http://www.nosqlnotes.com/technotes/hbase/hbase-read/
    http://hbasefly.com/2017/06/11/hbase-scan-2/
    http://hbasefly.com/2016/12/21/hbase-getorscan/
    http://hbasefly.com/2016/11/11/hbase%e6%9c%80%e4%bd%b3%e5%ae%9e%e8%b7%b5%ef%bc%8d%e8%af%bb%e6%80%a7%e8%83%bd%e4%bc%98%e5%8c%96%e7%ad%96%e7%95%a5/

Procedure解读
    
    http://www.nosqlnotes.com/technotes/hbase/procedure-v2/