package com.sovince.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
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

                        //System.out.println(Server.socketList.size());
                        Iterator<Socket> iterator = Server.socketList.iterator();
                        while (iterator.hasNext()){
                            Socket socket = iterator.next();
                            try {
                                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                                dataOutputStream.writeUTF(addr+":"+msg);
                            }catch (SocketException s){
                                synchronized (Server.socketList){
                                    if (Server.socketList.contains(socket)){
                                        System.out.println("SocketException!");
//                                dataOutputStream.close();
                                        socket.close();
                                        iterator.remove();//移除出现异常的socket
                                        System.out.println("移除了出现异常的socket");
                                    }
                                }

                            }
                        }
                        System.out.println("消息已广播:"+msg+"用户数:"+Server.socketList.size());

                    }
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
    }

    public static void offerToQueue(String addr,String msg){
        Map<String, String> map= new HashMap<>();
        map.put("addr",addr);
        map.put("msg",msg);
        Server.msgQueue.offer(map);
    }
}
