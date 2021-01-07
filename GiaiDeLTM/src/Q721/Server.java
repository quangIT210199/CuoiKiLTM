/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q721;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author HoangHung
 */
public class Server {
       public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(1108);
           while (true) {               
               Socket socket=server.accept();
               BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
               BufferedWriter out=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
               String msv=in.readLine();
               System.out.println(msv);
               String question="hgisdhhdkjfhshfbk$%@$bcss sdbsaijfb   abdsihb";
               out.write(question);
               out.newLine();
               out.flush();
               String result=in.readLine();
               System.out.println(result);
               
           }
    }
}
