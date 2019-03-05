package com.sovince.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/3/5
 * Time: 16:24
 */
public class Sender {
    public static void broadcast(){
        String addr;
        String msg;
            try {
                while (true){
                    if(Server.msgQueue.isEmpty()){
                        break;
                    }else{
                        Map<String,String> map = Server.msgQueue.poll();
                        addr = map.get("addr");
                        msg = map.get("msg");

                        for (Socket socket:Server.socketList){
                            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                            dataOutputStream.writeUTF(addr+":"+msg);
                            System.out.println("消息已广播:"+msg);
                        }
                    }
                }
            }catch (IOException e){
                e.printStackTrace();
            }
    }
}
