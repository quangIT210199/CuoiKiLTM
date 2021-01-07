/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object_933;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author quang
 */
public class Client {
    public static void main(String[] args) throws SocketException, IOException, ClassNotFoundException {
        DatagramSocket client = new DatagramSocket();
        
        Student933 st = new Student933("B17DCAT148");
        
        send(st, client);
        
        Student933 st1 = rece(client);
        
        System.out.println(st1.getId() + " " + st1.getName());
        String name = st1.getName().toLowerCase();
        
        String ten = chTen(name);
        
        st1.setName(ten);
        
        String email = chEmail(name);
        
        st1.setEmail(email);
        
        send(st1, client);
        
    }
    
    public static String chTen(String name){
        String kq="";
        for (int i = 0; i < name.length(); i++) {
            if(i == 0 || (name.charAt(i - 1) == ' ' && name.charAt(i) != ' ')){
                kq += Character.toUpperCase(name.charAt(i));
            }else{
                kq += name.charAt(i);
            }
        }
        return kq; 
    }
    
    public static String chEmail(String name){
        String[] arr = name.trim().split(" ");
        String email = arr[arr.length - 1];
        
        for (int i = 0; i <= arr.length - 2; i++) {
            email += arr[i].substring(0, 1);
        }
        
        return email + "@ptit.edu.vn";
    }
    
    public static void send(Student933 student, DatagramSocket client) throws IOException {
        if(client != null){
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            
            oos.writeObject(student);
            oos.flush();
            
            byte[] data = baos.toByteArray();
            InetAddress inet = InetAddress.getByName("localhost");
            
            DatagramPacket sendPK = new DatagramPacket(data, data.length, inet, 1105);
            client.send(sendPK);
        }
    }
    
    public static Student933 rece(DatagramSocket client) throws IOException, ClassNotFoundException{
        Student933 st = null;
        
        if(client != null){
            byte[] data = new byte[1024];
            DatagramPacket recePK = new DatagramPacket(data, data.length);
            client.receive(recePK);
            
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bais);
            
            st = (Student933) ois.readObject();
            
            return st;
        }
        
        return null;
    }
}
