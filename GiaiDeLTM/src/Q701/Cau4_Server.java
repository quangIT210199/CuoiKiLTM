/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class Cau4_Server {
    public static void main(String[] args) {
        try {
            ServerSocket server=new ServerSocket(1106);
            while(true){
            Socket socket=server.accept();
            String s="1,3,9,19,33,20";
            byte b[]=new byte[1000];
            InputStream in=socket.getInputStream();
            in.read(b);
            System.out.println(new String (b));
            OutputStream out=socket.getOutputStream();
            out.write(s.getBytes());
            byte c[]=new byte[1000];
            in.read(c);
            System.out.println(new String(c));
            }
        } catch (IOException ex) {
            Logger.getLogger(Cau4_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
