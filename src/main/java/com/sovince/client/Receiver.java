package com.sovince.client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/3/5
 * Time: 19:40
 */
public class Receiver extends Thread {
    private Socket socket;
    public Receiver(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            String msg;
            while (!(msg=dataInputStream.readUTF()).isEmpty()){
                System.out.println(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
