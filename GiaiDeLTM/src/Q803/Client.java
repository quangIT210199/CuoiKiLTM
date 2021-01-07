/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q803;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author HoangHung
 */
public class Client {
    
    DatagramSocket client;
    
    public void OpenConnect() throws SocketException {
        client = new DatagramSocket();
    }
    
    public String receive() throws IOException {
        byte dataReceive[] = new byte[1024];
        DatagramPacket packet = new DatagramPacket(dataReceive, dataReceive.length);
        client.receive(packet);
        return new String(packet.getData());
    }
    
    public void send(String str) throws UnknownHostException, IOException {
        byte dataSent[] = new byte[1024];
        dataSent = str.getBytes();
        DatagramPacket packet = new DatagramPacket(dataSent, dataSent.length, InetAddress.getByName("localhost"), 1108);
        client.send(packet);
    }
    
    public static String solve(String str) {
        String s = "";
        String result = "";
        int maxRepeat = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ' && s.indexOf(str.charAt(i)) == -1) {
                int sl = 1;
                String vt = (i+1)+",";
                for (int j = i + 1; j < str.length(); j++) {
                    if (str.charAt(i) == str.charAt(j)) {
                        sl++;
                        vt =vt+(j + 1)+",";
                    }
                }
                if (sl > maxRepeat) {
                  
                    result = str.charAt(i) +":"+ vt;
                    maxRepeat=sl;
                }
                s+=str.charAt(i);
            }
        }
        return result;
    }
    
    public static void main(String[] args) throws SocketException, IOException {
        Client client = new Client();
        client.OpenConnect();
        client.send(";B17DCCN284;803");
        String question = client.receive();
        String temp[] = question.split(";");
        String questionID = temp[0];
        String data = temp[1].trim();
        String result = solve(data);
        client.send(questionID + ";" + result);
    }
}
