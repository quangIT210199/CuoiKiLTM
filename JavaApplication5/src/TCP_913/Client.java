/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP_913;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;
    private static Socket client;
    private static Student student;
    
    public static void main(String[] args){
        try {
            client = new Socket("2.tcp.ngrok.io", 18913);
            oos = new ObjectOutputStream(client.getOutputStream());
            ois = new ObjectInputStream(client.getInputStream());
            
            oos.writeUTF("B17DCAT148, 913");
            oos.flush();
            
            student = (Student) ois.readObject();
            System.out.println(student);
            
            setGPA();
            
            oos.writeObject(student);
            oos.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                client.close();
                oos.close();
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    static void setGPA(){
        if(3.7 <= student.getGpa() && student.getGpa() <= 4.0){
            student.setGpaLetter("A");
        }
        if(3.0 <= student.getGpa() && student.getGpa() <= 3.7){
            student.setGpaLetter("B");
        }
        if(2.0 <= student.getGpa() && student.getGpa() <= 3.0){
            student.setGpaLetter("C");
        }
        if(1.0 <= student.getGpa() && student.getGpa() <= 2.0){
            student.setGpaLetter("E");
        }
        if(0 <= student.getGpa() && student.getGpa() <= 1.0){
            student.setGpaLetter("F");
        }
    }
}
