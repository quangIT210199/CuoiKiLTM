/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PhanTuThieu_802;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author quang
 */
public class Server {
        public static DatagramPacket receivePacket=null;
    public static void main(String[] args) throws SocketException, IOException {
         DatagramSocket server = new DatagramSocket(1110);
         System.out.println("running");
         String msv;
         String kqua="";
         while (true) {
            msv=receive(server);
            System.out.println(msv);
             
         String question="reqID; 10; 4,5,3,2,7,2";
         send(question, server);
             System.out.println("gui rồi");
         kqua=receive(server);
         System.out.println(kqua);  
    }
}
    
    public static void send(String str, DatagramSocket server) throws IOException{
        if(server!=null){
            byte[] sendData= new byte[1024];
            
            sendData =str.getBytes();
            DatagramPacket packet= new DatagramPacket(sendData, sendData.length,receivePacket.getAddress(),receivePacket.getPort());
            
            server.send(packet);
        }
    }
    
    public static String receive(DatagramSocket server) throws IOException{
        if(server != null){
            byte[] receiveData = new byte[1024];
            
            receivePacket = new DatagramPacket(receiveData, receiveData.length);
            server.receive(receivePacket);
            return new String(receivePacket.getData()).trim();
        }
        return null;
    }
}
