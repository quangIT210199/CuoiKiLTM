/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q701;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author HoangHung
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(1107);
        while (true) {            
            Socket socket=server.accept();
            OutputStream out=socket.getOutputStream();
            InputStream in=socket.getInputStream();
            byte msv[]=new byte[1024];
            in.read(msv);
            System.out.println(new String(msv));
            out.write("1,3,9,19,33,20".getBytes());
            out.flush();
            byte result[]=new byte[1024];
            in.read(result);
            System.out.println(new String(result));
            
        }
        
        
    }
}
