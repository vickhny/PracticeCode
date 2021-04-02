package clientserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.*;

public class Server {
    public static void main(String[] args) throws IOException {
        int Port = Integer.parseInt(JOptionPane.showInputDialog("Input Your Port : "));
        String IP = JOptionPane.showInputDialog("Input Your IP Server : ");
        ServerSocket serverSock = new ServerSocket(6066);
        Socket Sock = serverSock.accept();
        DataOutputStream out = new DataOutputStream(Sock.getOutputStream());
        out.writeUTF("i am fine, thank you");
        DataInputStream in = new DataInputStream(Sock.getInputStream());
        System.out.println(in.readUTF());
        Sock.close();
    }
}
