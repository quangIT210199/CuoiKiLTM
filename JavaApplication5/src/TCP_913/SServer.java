/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP_913;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell 5548
 */
public class SServer {

    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ServerSocket serverSocket;
    //Student student = new Student("Nguyễn Văn Quang", 3.0, "");

    public SServer() {
        Student student = new Student(913, "B17DCAT148", 4.0f, "");
        try {
            serverSocket = new ServerSocket(1109);
            System.out.println("...");
            while (true) {
                Socket socket = serverSocket.accept();
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream(socket.getInputStream());

                String str = ois.readUTF();
                System.out.println(str);
                
                oos.writeObject(student);
                oos.flush();
                
                student = (Student) ois.readObject();
                System.out.println(student);
            }
        } catch (IOException ex) {
            Logger.getLogger(SServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SServer.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                serverSocket.close();
                oos.close();
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        new SServer();
    }
}

