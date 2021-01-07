/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinKC;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nhuva
 */
public class Server {
    public static void main(String[] args) {
        String s="1,3,9,19,33,20";
        OutputStream out;
        InputStream inp;
        int kc,a,b;
        String str;
        
        try {
            ServerSocket server=new ServerSocket(1107);
            System.out.println("Server san sang...");
            while(true){
                Socket conn=server.accept();
                byte[] c = new byte[1024];
                inp=conn.getInputStream();
                
                inp.read(c);
                str = new String(c);
                System.out.println(str);
                
                out=conn.getOutputStream();
                out.write(s.getBytes());
                
                kc=inp.read();
                a=inp.read();
                b=inp.read();
                System.out.println(kc+","+a+","+b);
                out.close();
                inp.close();
                conn.close();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
