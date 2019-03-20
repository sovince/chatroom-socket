package com.sovince.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/3/5
 * Time: 16:24
 */
public class Receiver extends Thread {
    private Socket socket;


    public Receiver() {
    }

    public void waitForClient(ServerSocket serverSocket){
        try {
            Socket socket = serverSocket.accept();
            this.socket = socket;
            Server.socketList.add(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try{
            SocketAddress socketAddress = socket.getRemoteSocketAddress();
            String addr = socketAddress.toString();
            //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String msg;

            Sender.offerToQueue("系统","新用户连接:"+addr);
//            Sender.offerToQueue("系统","当前用户数:"+Server.socketList.size());
            Sender.broadcast();

            while (true){
                msg = dataInputStream.readUTF();
                System.out.println(addr+":"+msg);

                if(msg.equals("quit")){
                    Sender.offerToQueue(addr,msg);
                    Sender.offerToQueue(addr,"用户退出");
                    Sender.broadcast();
                    dataInputStream.close();
                    break;
                }
                Sender.offerToQueue(addr,msg);
                Sender.broadcast();
            }
            dataInputStream.close();
            socket.close();
        }
        catch (IOException e){
            //e.printStackTrace();
        }
    }

}
