# jvm常见OOM错误
**Java heap space** 
原因：不管机器上还没有空闲的物理内存, 只要堆内存使用量达到最大内存限制,就会抛出 java.lang.OutOfMemoryError: Java heap space 错误。  
详细原因：
超出预期的访问量/数据量。 应用系统设计时,一般是有 “容量” 定义的, 部署这么多机器, 用来处理一定量的数据/业务。 如果访问量突然飙升, 超过预期的阈值, 类似于时间坐标系中针尖形状的图谱, 那么在峰值所在的时间段, 程序很可能就会卡死、并触发 java.lang.OutOfMemoryError: Java heap space 错误。

内存泄露(Memory leak). 这也是一种经常出现的情形。由于代码中的某些错误, 导致系统占用的内存越来越多. 如果某个方法/某段代码存在内存泄漏的, 每执行一次, 就会（有更多的垃圾对象）占用更多的内存. 随着运行时间的推移, 泄漏的对象耗光了堆中的所有内存, 那么 java.lang.OutOfMemoryError: Java heap space 错误就爆发了。

**解决方案**
1.内存设计比较小  调整堆内存
2.代码问题导致内存泄漏 定位 查找 解决。（1，哪类对象占用了最多内存？2，这些对象是在哪部分代码中分配的。）
步骤：
1，获取生产服务器上的执行堆转储（heap dump）的权限。
2. 在适当的时间执行堆转储 一般来说,内存分析需要比对多个堆转储文件 每次执行堆转储, 都会对JVM进行“冻结”, 所以生产环境中,也不能执行太多的Dump操作,否则系统缓慢或者卡死
3. 用另一台机器来加载Dump文件。一般来说, 如果出问题的JVM内存是8GB, 那么分析 Heap Dump 的机器内存需要大于 8GB. 打开转储分析软件(我们推荐Eclipse MAT , 当然你也可以使用其他工具)。
4. 检测快照中占用内存最大的 GC roots
5. 接下来, 找出可能会分配大量对象的代码. 如果对整个系统非常熟悉, 可能很快就能定位了

**GC overhead limit exceeded**
原因：程序基本上耗尽了所有的可用内存, GC也清理不了。
原因分析：JVM抛出 java.lang.OutOfMemoryError: GC overhead limit exceeded 错误就是发出了这样的信号: 执行垃圾收集的时间比例太大, 有效的运算量太小. 默认情况下, 如果GC花费的时间超过 98%, 并且GC回收的内存少于 2%, JVM就会抛出这个错误。假如不抛出 GC overhead limit 错误会发生什么情况呢? 那就是GC清理的这么点内存很快会再次填满, 迫使GC再次执行. 这样就形成恶性循环, CPU使用率一直是100%, 而GC却没有任何成果. 系统用户就会看到系统卡死 - 以前只需要几毫秒的操作, 现在需要好几分钟才能完成。

**PermGen space**
主要原因：加载到内存中的 class 数量太多或体积太大
情景：
1.程序启动时产生的 OutOfMemoryError  ---. 增加 PermGen 的大小
2.运行时产生的 OutOfMemoryError
使用启动参数:-XX:+CMSClassUnloadingEnabled 默认情况下 CMSClassUnloadingEnabled 的值为false, 所以需要明确指定。 启用以后, GC 将会清理 PermGen, 卸载无用的 class. 当然, 这个选项只有在设置 UseConcMarkSweepGC 时生效
如果确定class可以被卸载，还存在 OutOfMemoryError, 那就需要进行堆转储分析了。然后通过堆转储分析器(如 Eclipse MAT) 加载 heap dump。找出最重的 classloader, 也就是加载 class 数量最多的那个. 通过加载的 class 及对应的实例数量, 比对类加载器, 找出最靠前的部分, 挨个进行分析。
对于每个有嫌疑的类, 都需要手动跟踪到生成这些类的代码中, 以定位问题。
堆转储执行命令 jmap -dump:file=dump.hprof,format=b <process-id> 

**Metaspace**
原因： Metaspace 错误的主要原因, 是加载到内存中的 class 数量太多或者体积太大。
方案： 1增加 Metaspace 的大小 2 直接去掉 Metaspace 的大小限制（需要注意, 不限制Metaspace内存的大小, 假若物理内存不足, 有可能会引起内存交换(swapping), 严重拖累系统性能。 此外,还可能造成native内存分配失败等问题。）

**Unable to create new native thread** （程序创建的线程数量已达到上限值 出现这个问题 一般都是程序的bug）
原因：  本地内存不足 
解决方法：执行线程转储(thread dump) 来分析具体情况

**Out of swap space**(交换空间(swap space,虚拟内存) 不足,是由于物理内存和交换空间都不足所以导致内存分配失败)
原因：操作系统的交换空间太小。
机器上的某个进程耗光了所有的内存资源。
内存泄漏
解决方案：升级服务器配置, 增加物理机的内存。当然也可以进行程序优化, 降低内存空间的使用量, 通过堆转储分析器可以检测到哪些方法/代码分配了大量的内存。














