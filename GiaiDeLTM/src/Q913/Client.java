/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q913;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author HoangHung
 */
public class Client {
    public Client() throws IOException, ClassNotFoundException{
        Socket socket=new Socket("localhost",1109);
        ObjectOutputStream out=new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
        out.writeUTF("B17DCCN284;913");
        out.flush();
        Student student=(Student) in.readObject();
        setGpaByMe(student);
        out.writeObject(student);
        
    }
    public void setGpaByMe(Student student){
    float gpa=student.getGpa();
    if(gpa>3.7) student.setGpaLetter("A");
    else if(gpa>3.0) student.setGpaLetter("B");
    else if(gpa>2.0) student.setGpaLetter("C");
    else student.setGpaLetter("D");
    }
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client=new Client();
    }
}
