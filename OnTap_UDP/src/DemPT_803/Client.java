/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemPT_803;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author quang
 */
public class Client {
    
    public static void main(String[] args) throws SocketException, IOException {
        DatagramSocket client = new DatagramSocket();
        String msv = ";B17DCAT148;803";
        
        send(msv, client);
        
        String[] chuoi = rece(client).split(";");
        String req = chuoi[0];
        String data = chuoi[1];
        
        HashMap<Character,Integer> hm = new HashMap<Character, Integer>();
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if(c == 0){
                break;
            }
            if(hm.containsKey(c)){
                hm.replace(c, hm.get(c) + 1);
            }
            else{
                hm.put(c, 1);
            }
        }
        //tìm phần tử xh nhiều nhất
        int max = 0;
        char c = 0;
        for(Map.Entry<Character, Integer> entry : hm.entrySet()){
            Character key = entry.getKey();
            Integer val = entry.getValue();
            System.out.println(key +" "+ val);
            if( max < val){
                max = val;
                c = key;
            }
        }
        String kq = c +":";
        //tìm vị trị
        for (int i = 0; i < data.length(); i++) {
            if(data.charAt(i) == c){
                kq += i + ",";
            }
        }
        String rs = req +"; "+ kq;
        System.out.println("kq" + kq.trim());
        
        send(rs, client);
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
            
            return new String(recePK.getData());
        }
        
        return null;
    }
}
