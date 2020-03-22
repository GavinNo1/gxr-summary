# hashmap
https://juejin.im/post/5ab99afff265da23a2291dee

https://juejin.im/post/5a23f82ff265da432003109b#heading-13

https://juejin.im/post/5a255bbd6fb9a0450c493f4d#heading-3

总结：
![hashmap的put方法流程图](_v_images/_hashmap的pu_1522651479_1786269868.png)


多线程情况下 操作hashMap会造成死循环。
原因：在jdk7中 hashmap在扩容时，在同一个桶中的链表数据依旧在新的桶中， 链表中数据会倒置， 在多线程操作下 会造成死循环  所以 如果多线程的话使用ConcurrentHashMap来代替(使用的分段式锁)。
如何解决死锁：
    使用jps查询端口号  jps  列出当前机器所有java进程
    使用 jstack [ option ] pid   打印出当前堆栈信息
hashmap初始容量为16 每次扩容为原来的两倍。基于此种特性 为了提高效率 使用位运算来代替取&操作 ，这又引入了哈希分布不均匀的问题，所以HashMap为解决这问题，又对hash算法做了一些改进，进行了扰动计算 。（常规的设计是把桶的大小设计为素数。相对来说素数导致冲突的概率要小于合数）

作为key值要使用不变类这样定位的时候才正确。定位分两步，第一步 取key 的hashcode 然后做一些优化， 然后得到int值对容量取模（X % 2^n = X & (2^n - 1)）

jdk8对链表长度大于8 存储结构转为红黑树  可以将最坏情况下的性能从O(n)提高到O(logn)。

jdk8 对rehash也有优化  我们使用的是2次幂的扩展(指长度扩为原来2倍)，所以，元素的位置要么是在原位置，要么是在原位置再移动2次幂的位置。即原来数据索引要么不变 要么是原索引+oldCap。 原因：见 https://tech.meituan.com/java-hashmap.html。




