cpu飙高
    
    https://mp.weixin.qq.com/s/Q_0gz7H7TZ9QgtI7LaAIfg
    https://www.javatang.com/archives/2017/10/25/36441958.html#_Native_Thread_Status
系统线程巨多问题。

       线程池创建使用完成没有shutdown导致，虽然线程池引用指向新创建的线程池，由于线程池与线程双向引用，随着线程池的创建， 线程越来越多而得不到回收。

tcp 三次握手与四次握手
    
    https://blog.csdn.net/smileiam/article/details/78226816
    https://blog.csdn.net/lengxiao1993/article/details/82771768
    https://blog.csdn.net/qq_28000789/article/details/87780126

threadlocal
    
    线程上下文传递，使用之后一定要remove 防止内存泄露
    http://ifeve.com/%E4%BD%BF%E7%94%A8threadlocal%E4%B8%8D%E5%BD%93%E5%8F%AF%E8%83%BD%E4%BC%9A%E5%AF%BC%E8%87%B4%E5%86%85%E5%AD%98%E6%B3%84%E9%9C%B2/
    https://xxxblank.github.io/2018/02/11/ThreadLocal%E2%80%94%E2%80%94%E7%BA%BF%E7%A8%8B%E7%9A%84%E5%B1%80%E9%83%A8%E5%8F%98%E9%87%8F/
    
零拷贝
    
    https://www.jianshu.com/p/fad3339e3448
    https://www.cnblogs.com/huxiao-tee/p/4657851.html#_label2
    https://www.cnblogs.com/huxiao-tee/p/4660352.html
    
多线程

    源码解析
    http://yukai.space/2017/04/26/%E7%BA%BF%E7%A8%8B%E6%B1%A0%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86%E6%96%B9%E6%A1%88/
    ![线程池的生命周期](_v_images/_线程池的生命周期_1521798094_329775457.png)
    
    ![线程池的流程图](_v_images/_线程池的流程图_1521798165_2028296539.png)
    
    step1.调用ThreadPoolExecutor的execute提交线程，首先检查CorePool，如果CorePool内的线程小于CorePoolSize，新创建线程执行任务。
    step2.如果当前CorePool内的线程大于等于CorePoolSize，那么将线程加入到BlockingQueue。
    step3.如果不能加入BlockingQueue，在小于MaxPoolSize的情况下创建线程执行任务。
    step4.如果线程数大于等于MaxPoolSize，那么执行拒绝策略。
    
    ![什么时候采取拒绝策略 参考下图](_v_images/_什么时候采取拒绝策略_1521798281_424518495.png)
    线程池异常处理方案
    http://yukai.space/2017/04/26/%E7%BA%BF%E7%A8%8B%E6%B1%A0%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86%E6%96%B9%E6%A1%88/

java内存模型
    
    https://my.oschina.net/u/1778239/blog/1610185
    https://my.oschina.net/u/1778239/blog/1613240

多态的实现原理

    https://blog.csdn.net/seu_calvin/article/details/52191321 
    https://www.jianshu.com/p/56a7c4b26b14  
并发问题：
    
    https://www.zhihu.com/question/20255499
    https://www.cnblogs.com/duanxz/p/4967042.html
    并发集合相关

mysql死锁等
    
    https://www.cnblogs.com/duanxz/p/4394641.html
    https://my.oschina.net/wuweixiang/blog/3013479