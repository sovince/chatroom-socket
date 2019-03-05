package com.sovince.server;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/3/5
 * Time: 16:24
 */
public class Receiver extends Thread {
    private Socket socket;

    public Receiver(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            InetAddress inetAddress = socket.getInetAddress();
            String addr = inetAddress.getHostAddress();
            //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String msg;
            while (!(msg=dataInputStream.readUTF()).isEmpty()){//
                System.out.println(addr+":"+msg);
                offerToQueue(addr,msg);
                Sender.broadcast();
            }
        }
        catch (IOException e){
            //e.printStackTrace();
        }
    }

    private void offerToQueue(String addr,String msg){
        Map<String, String> map= new HashMap<>();
        map.put("addr",addr);
        map.put("msg",msg);
        Server.msgQueue.offer(map);
    }
}
