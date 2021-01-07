/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PhanTuThieu_802;

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
        send(msv, client);
        //Test xử lí
        String data = rece(client);

        String[] chuoi = data.split(";");
        String req = chuoi[0].trim();
        int n = Integer.parseInt(chuoi[1].trim());
        
        String[] num = chuoi[2].trim().split(",");
        
        int[] arrNum = new int[num.length];
        for (int i = 0; i < num.length; i++) {
            arrNum[i] = Integer.parseInt(num[i].trim());
        }
        
        String kq = req +";";
        
        for (int i = 1; i < n; i++) {
            if(xuLy(i, arrNum) == false){
                kq += i +",";
            }
        }
        
        kq = kq.substring(0 , kq.length() - 1);
        
        send(kq, client);
    }
    
    public static boolean xuLy(int a, int[] b){
        for (int i = 0; i < b.length; i++) {
            if(b[i] == a){
                return true;
            }
        }
        
        return false;
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
