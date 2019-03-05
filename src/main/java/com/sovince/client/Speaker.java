package com.sovince.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by vince
 * Email: so_vince@outlook.com
 * Data: 2019/3/5
 * Time: 19:41
 */
public class Speaker{
    private Socket socket;
    public Speaker(Socket socket){
        this.socket = socket;
    }

    public void speak(){

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String msg;
            while (true){
                msg = bufferedReader.readLine();
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(msg);
                if(msg.equals("quit")) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
