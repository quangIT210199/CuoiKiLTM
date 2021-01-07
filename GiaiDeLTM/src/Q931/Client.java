/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q931;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author HoangHung
 */
public class Client {

    DatagramSocket client;

    public void openConnect() throws SocketException {
        client = new DatagramSocket();
    }

    public void send(String str) throws UnknownHostException, IOException {
        byte dataSent[] = new byte[1024];
        dataSent = str.getBytes();
        DatagramPacket packet = new DatagramPacket(dataSent, dataSent.length, InetAddress.getByName("localhost"), 1107);
        client.send(packet);
    }

    public String receive() throws IOException {
        byte dataRecive[] = new byte[1024];
        DatagramPacket packet = new DatagramPacket(dataRecive, dataRecive.length);
        client.receive(packet);
        return new String(packet.getData());
    }
    public static void main(String[] args) throws SocketException, IOException {
        Client client=new Client();
        client.openConnect();
        client.send(";B17DCCN284;931");
        String question=client.receive();
        String temp[]=question.split(";");
        String questionID=temp[0];
        String data=temp[1];
        String num[]=data.split(",");
        ArrayList<Integer> list=new ArrayList<>();
        for(String i:num) list.add(Integer.parseInt(i.trim()));
        int maxValue=Collections.max(list);
        int minValue=Collections.min(list);
        client.send(questionID+";"+maxValue+","+minValue);
    }
}
