package com.cyzs.net;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description: nio server端的程序 ，最完美的一次
 * @Author xh
 * @create 2020-01-11 22:07
 */
public class NioSocketServer {

    public static void main(String[] args)throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().setReuseAddress(true);
        serverSocketChannel.socket().bind(new InetSocketAddress(8899));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("开始监听。。。");
        while (true){
            int select = selector.select();
            System.out.println("select 数量：" + select);
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                try{
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isAcceptable()){
                        System.out.println("有新的连接 ");
                        ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel channel = serverSocketChannel1.accept();
                        channel.configureBlocking(false);
                        channel.register(selector, SelectionKey.OP_READ);

                    }
                    if (selectionKey.isReadable()){
                        /**
                         * 如果byteBuffer不够大，会再次获得selectionKey，第二次读取剩下的，直到读完
                         * iterator.remove()不会影响
                         */
                        SocketChannel channel = null;
                        try{

                            channel = (SocketChannel) selectionKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            channel.read(byteBuffer);
                            byteBuffer.flip();
                            byte[] bytes = new byte[byteBuffer.limit()];
                            byteBuffer.get(bytes);
                            String s = new String(bytes, StandardCharsets.UTF_8);
                            System.out.println("数据的大小："+s.length()+"  "+s);
                            byteBuffer.clear();
                            //写数据
                            byteBuffer.put(LocalDateTime.now().toString().getBytes("UTF-8"));
                            byteBuffer.flip();
                            channel.write(byteBuffer);
                            byteBuffer.clear();
                            channel.close();
                        }catch (Exception e){
                            e.printStackTrace();
                            /**
                             * 当出现异常的时候必须关闭channel
                             */
                            channel.close();
                        }

                    }

                }catch (Exception e){
                    e.printStackTrace();

                }finally {
                    System.out.println("remove");
                    iterator.remove();
                }

            }
        }
    }
}
