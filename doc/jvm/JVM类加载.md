# JVM类加载
http://wiki.xiaohansong.com/java/class_lifecycle.html  类的生命周期

http://blog.xiaohansong.com/2016/08/09/ThreadLocal-leak-analyze/     ThreadLocal 内存泄露的实例分析

http://blog.xiaohansong.com/2016/08/06/ThreadLocal-memory-leak/     深入分析 ThreadLocal 内存泄漏问题


当一个类被主动使用时，Java虚拟就会对其初始化，如下六种情况为主动使用：

当创建某个类的新实例时（如通过new或者反射，克隆，反序列化等）

当调用某个类的静态方法时

当使用某个类或接口的静态字段时

当调用Java API中的某些反射方法时，比如类Class中的方法，或者java.lang.reflect中的类的方法时

当初始化某个子类时

当虚拟机启动某个被标明为启动类的类（即包含main方法的那个类）
    Java编译器会收集所有的类变量初始化语句和类型的静态初始化器，将这些放到一个特殊的方法中：clinit。 

实际上，static块的执行发生在“初始化”的阶段。初始化阶段，jvm主要完成对静态变量的初始化，静态块执行等工作。