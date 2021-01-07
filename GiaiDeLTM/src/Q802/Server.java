/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q802;

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
        server = new DatagramSocket(1107);
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
        System.out.println("run");
        while (true) {
            String msv = server.receive();
            System.out.println(msv);
            String question = "hero;10;1,2,4,2,5,2,3,2,5";
            server.send(question);
            String result = server.receive();
            System.out.println(result);
        }

    }

}
