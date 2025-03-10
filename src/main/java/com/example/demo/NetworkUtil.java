package com.example.demo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class NetworkUtil {
    private final Socket socket;
    private final ObjectOutputStream oos;
    private final ObjectInputStream ois;

    public NetworkUtil(String s, int port) throws IOException {
        this.socket = new Socket(s, port);
        //System.out.println("connected");
        oos = new ObjectOutputStream(socket.getOutputStream());
        //System.out.println("output");
        ois = new ObjectInputStream(socket.getInputStream());
        System.out.println("input");
    }

    public NetworkUtil(Socket s) throws IOException {
        this.socket = s;
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
    }

    public Object read() throws IOException, ClassNotFoundException {
        return ois.readUnshared();
    }

    public void write(Object o) throws IOException {
        oos.writeUnshared(o);
    }

    public void closeConnection() throws IOException {
        ois.close();
        oos.close();
    }
}

