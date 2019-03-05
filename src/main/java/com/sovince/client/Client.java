package com.sovince.client;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/3/5
 * Time: 19:37
 */
public class Client {
    private Socket socket;

    public Client(String host,int port){
        try {
            socket = new Socket(host,port);

            new Receiver(socket).start();//开启消息接收线程

            Speaker speaker = new Speaker(socket);//发言
            speaker.speak();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1",7777);
    }
}
