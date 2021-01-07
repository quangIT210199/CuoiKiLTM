/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UDP_936_Loaibokytu;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author quang
 */
public class Client {
    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket client = new DatagramSocket();
        String msv = ";B17DCAT148;936";
        
        sendPK(msv, client);
        // Test xu ly
        String data = recePK(client).trim();
        String[] chuoi = data.split(";");
        String req = chuoi[0];
        String str1 = chuoi[1];
        String str2 = chuoi[2];
        
        String strOut ="";
        for (int i = 0; i < str1.length(); i++) {
            String c = str1.charAt(i) +"";
            if(!str2.contains(c)){
                strOut += str1.charAt(i);
            }
        }
        String kq = req +";"+strOut;
        
        sendPK(kq, client);
    }
    
    public static void sendPK(String str, DatagramSocket client) throws UnknownHostException, IOException{
        if(client != null){
            byte[] sendData = new byte[1024];
            
            sendData = str.getBytes();
            
            InetAddress inet = InetAddress.getByName("localhost");
            
            DatagramPacket sendPK = new DatagramPacket(sendData, sendData.length, inet, 1108);
            
            client.send(sendPK);
        }
    }
    
    public static String recePK(DatagramSocket client) throws IOException{
        if(client != null){
            byte[] rece = new byte[1024];
            
            DatagramPacket recePK = new DatagramPacket(rece, rece.length);
            
            client.receive(recePK);
            
            return new String(recePK.getData()).trim();
        }
        
        return null;
    }
}
