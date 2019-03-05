package com.sovince.client;

import java.io.DataOutputStream;
import java.net.Socket;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/3/5
 * Time: 18:14
 */
public class Test {
    public static void main(String[] args)throws Exception {
        Socket socket = new Socket("127.0.0.1",7777);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF("hello");
        dataOutputStream.flush();
        dataOutputStream.close();
        socket.close();
    }
}
