/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object_933;

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
public class ClientObject {
    private static DatagramSocket client;
    
    public static void main(String[] args) throws SocketException, IOException, ClassNotFoundException {
        client = new DatagramSocket();
        Student933 student = new Student933("B17DCAT148");
        send(student);
        
        Student933 student1 = receive();
        System.out.println(student1.getId()+student1.getCode()+student1.getName());
        
        //Chuan hoa ten
        String name = student1.getName().toLowerCase();
        System.out.println(name);
        
        String tenChuanHoa = chuanHoa(name);
        System.out.println(tenChuanHoa);
        student1.setName(tenChuanHoa);
        
        String email = chuanHoaEmail(name);
        System.out.println(email);
        student1.setEmail(email);
        
        send(student1);
    }
    
    public static String chuanHoa(String str){
//        StringBuilder sb = new StringBuilder();
//        String []ten = str.split(" ");
//        for (int i = 0; i < ten.length; i++) {
//            sb.append(Character.toUpperCase(ten[i].charAt(0))).append(ten[i].substring(1)).append(" ");
//        }
//        return sb.toString().trim();
        String kq ="";
        for (int i = 0; i < str.length(); i++) {
            if(i == 0 || (str.charAt(i - 1) == ' ' && str.charAt(i) != ' ')){
                kq += Character.toUpperCase(str.charAt(i));
            }
            else {
                kq += str.charAt(i);
            }
        }
        
        return kq.trim();
    }
    
    public static String chuanHoaEmail(String name){
        String[] s = name.split(" ");
        String email = s[s.length - 1];
        
        for (int i = 0; i <= s.length - 2; i++) {
            email += s[i].substring(0, 1);
        }
        
        return email + "@ptit.edu.vn";
    }
    
    public static void send(Student933 st) throws IOException{
        if(client != null){
            ByteArrayOutputStream baos = 
                    new ByteArrayOutputStream();
            ObjectOutputStream oos = 
                    new ObjectOutputStream(baos);
            oos.writeObject(st);
            oos.flush();
            InetAddress add = InetAddress.getByName("localhost");
            
            byte[] sendData = baos.toByteArray();
            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, add, 1105);
            client.send(packet);
        }
    }
    
    public static Student933 receive() throws IOException, ClassNotFoundException{
        Student933 student = null;
        if(client != null){
            byte[] rece = new byte[1024];
            
            DatagramPacket recePK = 
                    new DatagramPacket(rece, rece.length);
            client.receive(recePK);
            
            ByteArrayInputStream bais = new ByteArrayInputStream(rece);
            ObjectInputStream ois = new ObjectInputStream(bais);
            
            student = (Student933) ois.readObject();
            
            return student;
        }
        
        return null;
    }
}
