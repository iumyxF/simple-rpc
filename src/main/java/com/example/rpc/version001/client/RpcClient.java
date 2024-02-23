package com.example.rpc.version001.client;

import com.example.rpc.version001.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author iumyx
 * @description:
 * @date 2024/2/23 10:23
 */
public class RpcClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8848);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            oos.writeInt(1001);
            oos.flush();

            User user = (User) ois.readObject();
            System.out.println("response user = " + user.toString());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
