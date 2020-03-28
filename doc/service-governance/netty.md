netty网络模型
   
    https://www.jianshu.com/p/6ae30cf5ae9e
    https://www.jianshu.com/p/03bb8a945b37
    https://blog.csdn.net/baixiaoshi/article/details/48708347
netty客户端 服务端 解读

    https://blog.csdn.net/u010013573/article/details/84726991
    https://blog.csdn.net/u010013573/article/details/85113716
    说明：NioEventLoop可以被多个channels绑定，每个channel只能绑定到一个eventLoop

netty数据流转
    https://blog.csdn.net/u010013573/article/details/85147388
    https://blog.csdn.net/u010013573/article/details/85166721
    https://blog.csdn.net/u010013573/article/details/85158858
    
    说明:在数据传输方面，SocketChannel中封装了socket，在读取数据时，将channel中的数据读取到ByteBufer缓冲区，
    在写数据时，将数据写到ByteBuffer缓冲区，然后通过channel发送出去，可以通过方法configureBlocking，设置是否启用非阻塞模式，如果设置了，则SocketChannel可以直接返回，而不需要阻塞，
    通常SocketChannel设置为非阻塞模式，而ServerSocketChannel为服务端监听客户端连接请求，可以设置为阻塞模式，即在accept中阻塞等待客户端的连接请求
    
基于netty的心跳检测应用
    
    https://blog.csdn.net/u010013573/article/details/85222823

基于netty的拆包黏包

其他


