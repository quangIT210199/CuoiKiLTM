/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MaxMin_931;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author HoangHung
 */
public class Server {

    DatagramSocket server;
    DatagramPacket packetRe;

    public void openConnect() throws SocketException {
        server = new DatagramSocket(1110);
    }

    public void send(String str) throws IOException {
        if (server != null) {
            byte[] dataSent = new byte[1024];
            dataSent = str.getBytes();
            DatagramPacket packet = new DatagramPacket(dataSent, dataSent.length, packetRe.getAddress(), packetRe.getPort());
            server.send(packet);
        }
    }

    public String receive() throws IOException {
        if (server != null) {
            byte dataReceive[] = new byte[1024];
            packetRe = new DatagramPacket(dataReceive, dataReceive.length);
            server.receive(packetRe);
            return new String(packetRe.getData());
        }
        return null;
    }

    public static void main(String[] args) throws SocketException, IOException {
        Server server = new Server();
        server.openConnect();
        while (true) {            
            String msv=server.receive();
            System.out.println(msv);
            String question="Q931;1,-3,5,8,9,-10,15,20,-4,6";
            server.send(question);
            String result=server.receive();
            System.out.println(result);
        }
    }

}
