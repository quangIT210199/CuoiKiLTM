/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q933;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author HoangHung
 */
public class Server {

    DatagramSocket server;
    DatagramPacket packetReceive;

    public void openConnect() throws SocketException {
        server = new DatagramSocket(1109);
    }

    public Student receive() throws IOException, ClassNotFoundException {
        if (server != null) {
            byte dataReceive[] = new byte[1024];
            packetReceive = new DatagramPacket(dataReceive, dataReceive.length);
            server.receive(packetReceive);
            ByteArrayInputStream bais=new ByteArrayInputStream(dataReceive);
            ObjectInputStream in=new ObjectInputStream(bais);
            Student student=(Student) in.readObject();
            return student;
        }
        return null;
    }

    public void send(Student student) throws IOException {
        if (server != null) {
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            ObjectOutputStream out=new ObjectOutputStream(baos);
            out.writeObject(student);
            byte dataSent[]=baos.toByteArray();
            DatagramPacket packet=new DatagramPacket(dataSent,dataSent.length,packetReceive.getAddress(),packetReceive.getPort());
            server.send(packet);
        }
    }
    public static void main(String[] args) throws SocketException, IOException, ClassNotFoundException {
        Server server=new Server();
        server.openConnect();
        while (true) {            
            Student student=server.receive();
            System.out.println(student);
            student.setId(100);
            student.setName("nguyen hoang hung");
            server.send(student);
            Student student1=server.receive();
            System.out.println(student1);
        }
    }

}
