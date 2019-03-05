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
    public static Queue<Map<String,String>> msgQueue;//消息队列

    public Server(int port){
        try {
            serverSocket = new ServerSocket(port);//指定端口
            socketList = new ArrayList<>();
            msgQueue = new LinkedList<>();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void start() throws IOException{
        while (true){
            Socket client = serverSocket.accept();
            socketList.add(client);//
            Receiver receiver = new Receiver(client);
            receiver.start();
        }
    }

    public static void main(String[] args) throws Exception{
        new Server(7777).start();
    }


}
