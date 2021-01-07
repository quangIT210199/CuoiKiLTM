
package Object_933;

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
 * @author Admin
 */
public class server {
    public static DatagramPacket receivePacket =null;
    public static void main(String[] args) throws SocketException, IOException, ClassNotFoundException {
        DatagramSocket server= new DatagramSocket(1105);
        System.out.println("running");
        while(true){
            Student933 student = receive(server);
            System.out.println(student.getCode());
            
            Student933 student1= new Student933("281",student.getCode(),"vu qUANG hUy",null);
            send(student1, server);
            
            Student933 student2=receive(server);
            System.out.println(student2.getName()+" "+student2.getEmail());
        }
        
    }
    
    public static void send(Student933 st, DatagramSocket server) throws IOException{
        if(server!=null){
            ByteArrayOutputStream baos= new ByteArrayOutputStream();
            ObjectOutputStream oos= new ObjectOutputStream(baos);
            oos.writeObject(st);
            oos.flush();
            
            InetAddress add= InetAddress.getByName("localhost");
            
            byte []sendData =baos.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,receivePacket.getAddress(),receivePacket.getPort());
            server.send(sendPacket);
            
        }
    }
    
    public static Student933 receive(DatagramSocket server) throws IOException, ClassNotFoundException{
        Student933 student=null;
        if(server!=null){
            byte []receiveData = new byte[1024];
            
            receivePacket=new DatagramPacket(receiveData, receiveData.length);
            server.receive(receivePacket);
            
            ByteArrayInputStream bais= new ByteArrayInputStream(receiveData);
            ObjectInputStream ois=new ObjectInputStream(bais);
            student = (Student933) ois.readObject();
            return student;
            
        }
        return null;
    }
}
