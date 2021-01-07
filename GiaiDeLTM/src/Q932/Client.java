/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q932;

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

//    public static String ChuanHoaData(String str){
//        str=str.toLowerCase().trim().replaceAll("\\s+"," ");
//        System.out.println(str);
//        String word[]=str.split(" ");
//    
//        String result="";
//        System.out.println(word.length);
//        for(int i=0;i<word.length;i++){
//            result=result+word[i].substring(0,1).toUpperCase()+word[i].substring(1)+" ";
//        }
//        return  result.substring(0,result.length()-1);
//    }
    public static String ChuanHoaData(String str) {
        str = str.toLowerCase().trim();
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            if (i == 0 || (str.charAt(i) != ' ' && str.charAt(i - 1) == ' ')) {
                result += Character.toUpperCase(str.charAt(i));
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }

    public static void main(String[] args) throws SocketException, IOException {
        Client client = new Client();
        client.OpenConnect();
        client.send("B17DCCN284");
        String question = client.receive();
        String temp[] = question.split(";");
        String questionID = temp[0];
        String data = temp[1];
        data = ChuanHoaData(data);
        client.send(questionID + ";" + data);
    }
}
