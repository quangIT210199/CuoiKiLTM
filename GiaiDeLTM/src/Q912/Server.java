/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q912;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author HoangHung
 */
public class Server {

    ServerSocket server;

    public Server() throws IOException {
        server = new ServerSocket(1108);
        while (true) {
            System.out.println("Started");
            Socket socket=server.accept();
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println("msv: "+in.readLine());
            String question="hung%$a@h^";
            out.write(question);
            out.newLine();
            out.flush();
            System.out.println("str1:"+in.readLine());
            System.out.println("str2:"+in.readLine());
        }
    }
    public static void main(String[] args) throws IOException {
        Server server=new Server();
    }
}
