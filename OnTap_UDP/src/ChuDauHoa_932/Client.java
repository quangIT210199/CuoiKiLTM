/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChuDauHoa_932;

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
        
        String msv = ";B17DCAT148;932";
        send(msv, client);
        
        String[] chuoi = rece(client).split(";");
        String req = chuoi[0].trim();
        
        String str = chuoi[1].toLowerCase();
        String kq= "";
        for (int i = 0; i < str.length(); i++) {
            if(i == 0 || (str.charAt(i - 1) == ' ' && str.charAt(i) != ' ')){
                kq += Character.toUpperCase(str.charAt(i));
            }
            else{
                kq += str.charAt(i);
            }
        }
        
        String rs = req +";"+kq;
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
            
            return new String(recePK.getData()).trim();
        }
        return null;
    }
}
