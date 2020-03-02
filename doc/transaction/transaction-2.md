事务的四大特性 acid以及原理
 
先介绍一条数据的大概流转。

    假设有A、B两个数据，值分别为1,2.
    1. 事务开始
    2. 记录A=1到undo log
    3. 修改A=3
    4. 记录A=3到 redo log
    5. 记录B=2到 undo log
    6. 修改B=4
    7. 记录B=4到redo log
    8. 将redo log写入磁盘
    9. 事务提交
通过redo日志保证持久性（d），通过undo日志保证院子性（a），通过mvcc+锁保证隔离性（i），通过以上三个属性保证一致性（c）。
前三个是手段 后一个是目的。

参考链接：
    
    https://www.jianshu.com/p/20e10ed721d0
    https://www.cnblogs.com/rjzheng/p/10841031.html