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
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author HoangHung
 */
public class Client {

    DatagramSocket client;

    public void openConnect() throws SocketException {
        client = new DatagramSocket();
    }

    public void send(Student student) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(student);
        byte datasent[] = baos.toByteArray();
        DatagramPacket packet = new DatagramPacket(datasent, datasent.length, InetAddress.getByName("localhost"), 1109);
        client.send(packet);
    }

    public Student receive() throws IOException, ClassNotFoundException {
        byte dataReceive[] = new byte[1024];
        DatagramPacket packet = new DatagramPacket(dataReceive, dataReceive.length);
        client.receive(packet);
        ByteArrayInputStream bais = new ByteArrayInputStream(dataReceive);
        ObjectInputStream in = new ObjectInputStream(bais);
        Student student = (Student) in.readObject();
        return student;
    }

    public static String ChuanHoaTen(String str) {
        System.out.println(str);
        str = str.toLowerCase().trim().replaceAll("\\s+", " ");
        String result = "";
        String word[] = str.split(" ");
        for (int i = 0; i < word.length; i++) {
            result += word[i].substring(0, 1).toUpperCase() + word[i].substring(1) + " ";
        }
        return result.substring(0, result.length() - 1);
    }

    public static String TaoEmail(String str) {
        str = str.toLowerCase().trim().replaceAll("\\s+", " ");
        String result = "";
        String word[] = str.split(" ");
        for (int i = 0; i < word.length - 1; i++) {
            result += word[i].substring(0, 1);
        }
        return word[word.length - 1] + result + "@ptit.edu.vn";
    }

    public static void main(String[] args) throws SocketException, IOException, ClassNotFoundException {
        Client client = new Client();
        client.openConnect();
        Student student = new Student();
        student.setCode("B17DCCN284");
        client.send(student);
        Student studentRe = client.receive();
        System.out.println(studentRe);
        student.setName(ChuanHoaTen(studentRe.getName()));
        student.setEmail(TaoEmail(studentRe.getName()));
        client.send(student);

    }
}
