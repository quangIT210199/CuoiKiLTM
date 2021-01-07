/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q913;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author HoangHung
 */
public class Server {

    ServerSocket server;

    public Server() throws IOException, ClassNotFoundException {
        server = new ServerSocket(1109);
        System.out.println("run");
        while (true) {            
            Socket socket=server.accept();
            ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
            String msv=in.readUTF();
            System.out.println(msv);
            Student student=new Student(100, msv, (float) 3.2,null);
            out.writeObject(student);
            Student studentRe=(Student) in.readObject();
            System.out.println(studentRe);
        }
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server=new Server();
    }
}
