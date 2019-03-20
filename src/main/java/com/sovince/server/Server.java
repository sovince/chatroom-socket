package com.sovince.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/3/5
 * Time: 16:24
 */
public class Server {
    private ServerSocket serverSocket;//服务端
    public static List<Socket> socketList;//客户端集合
    public static Queue<Map<String,String>> msgQueue;//模拟消息队列 但没有阻塞方法 只能依托inputstream.read()来阻塞

    public Server(int port){
        try {
            serverSocket = new ServerSocket(port);//指定端口
            socketList = new ArrayList<>();
            msgQueue = new LinkedList<>();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void start(){
        System.out.println("服务端已启动，等待连接……");
        while (true){
            Receiver receiver = new Receiver();
            receiver.waitForClient(serverSocket);
            receiver.start();
        }
    }

    public static void main(String[] args) {
        new Server(7777).start();
    }


}
