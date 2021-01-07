/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemKiTuXH;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author 84377
 */
public class Server {
    public static void main(String[] args) throws IOException {
        
        ServerSocket server = new ServerSocket(8501);
        
        Socket s = server.accept();
        System.out.println("runnning");
        BufferedWriter bw  = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        
        bw.write("quang; Qnacba12 adasd");
        bw.newLine();
        bw.flush();
        System.out.println("send the messs");
        String a = br.readLine();
        System.out.println(a);
        
        String kq = br.readLine();
        System.out.println(kq);
    }
}
