/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP_918;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author quang
 */
public class server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Customer918 customer=new Customer918(913, "", "nguyen van hai duong", "21-01-1999", "");
        
        ServerSocket ss= new ServerSocket(1110);
        System.out.println("Server da san sang");
            
        while(true){
            Socket server = ss.accept();
            System.out.println("Server da ket noi!");
            ObjectOutputStream oos= new ObjectOutputStream(server.getOutputStream());
            ObjectInputStream ois= new ObjectInputStream(server.getInputStream());
            
            System.out.println(ois.readUTF());
            
            oos.writeObject(customer);
            oos.flush();
                   
            customer= (Customer918) ois.readObject();
            System.out.println(customer.getName() +" "+ customer.getDayOfBirth());
        }
    }
}
