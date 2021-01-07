/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaxMin_931;

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
        String msv =";B17DCAT148;931";
        
        send(msv, client);
        String[] chuoi = rece(client).trim().split(";");
        
        String req = chuoi[0].trim();
        String[] arrNum = chuoi[1].trim().split(",");
        
        int[] num = new int[arrNum.length];
        for (int i = 0; i < arrNum.length; i++) {
            num[i] = Integer.parseInt(arrNum[i].trim());
        }
        
        int max = 0, min = 99999;
        for (int i = 0; i < arrNum.length; i++) {
            if(max < num[i]){
                max = num[i];
            }
        }
        
        for (int i = 0; i < arrNum.length; i++) {
            if(min > num[i]){
                min = num[i];
            }
        }
        
        String kq = req +";"+max +","+min;
        
        send(kq, client);
    }
    
    public static void send(String str, DatagramSocket client) throws UnknownHostException, IOException{
        if(client != null){
            byte[] data = new byte[1024];
            
            data = str.getBytes();
            InetAddress inet = InetAddress.getByName("localhost");
            DatagramPacket sendPK = new DatagramPacket(data, data.length, inet, 1110);
            
            client.send(sendPK);
        }
    }
    
    public static String rece(DatagramSocket client) throws IOException{
        if(client != null){
            byte[] data = new byte[1024];
            DatagramPacket recePK = new DatagramPacket(data, data.length);
            
            client.receive(recePK);
            
            return new String(recePK.getData()).trim();
        }
        
        return null;
    }
}
