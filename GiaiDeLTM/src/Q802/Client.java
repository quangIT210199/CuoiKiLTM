/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q802;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HoangHung
 */
public class Client {

    DatagramSocket client;

    public void openConnect() throws SocketException {
        client = new DatagramSocket();
    }

    public void send(String str) throws IOException {
        if (client != null) {
            byte[] dataSent = new byte[1024];
            dataSent = str.getBytes();
            DatagramPacket packet = new DatagramPacket(dataSent, dataSent.length, InetAddress.getByName("localhost"), 1107);
            client.send(packet);
        }
    }

    public String receive() throws IOException {
        if (client != null) {
            byte dataReceive[] = new byte[1024];
            DatagramPacket packet = new DatagramPacket(dataReceive, dataReceive.length);
            client.receive(packet);
            return new String(packet.getData());
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.openConnect();
        client.send(";B17DCCN284;802");
        String question = client.receive();
        System.out.println(question);
        
        
        String temp[] = question.split(";");
        String questionID = temp[0];
        int n = Integer.parseInt(temp[1].trim());
        String num[] = temp[2].split(",");
        
        String result = "";
        
        List<Integer> listnum = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            listnum.add(i);
        }
        for (int i = 0; i < num.length; i++) {
            if (listnum.contains(Integer.parseInt(num[i].trim()))) {
                listnum.remove(Integer.parseInt(num[i].trim()));
            }
        }
        for (int i = 0; i < listnum.size(); i++) {
            result += listnum.get(i) + ",";
        }
        result = result.substring(0, result.length() - 1);
        result = questionID + ";" + result;
        client.send(result);
    }
}
