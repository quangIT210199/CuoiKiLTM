/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TachKiTu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell 5548
 */
public class SServer {

    private ServerSocket serverSocket;
    private BufferedWriter bw;
    private BufferedReader br;

    public SServer() {
        try {
            serverSocket = new ServerSocket(1108);
            listening();
        } catch (IOException ex) {
            Logger.getLogger(SServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listening() throws IOException {
        System.out.println("SServer open ...");
        while (true) {
            Socket socket = serverSocket.accept();
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String str = br.readLine();
            System.out.println("Server nhan : " + str);
            str = "sdsajS7@%ahs5463A*&$#";
            bw.write(str);
            bw.newLine();
            bw.flush();

            str = br.readLine();
            System.out.println(str);
            str = br.readLine();
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        new SServer();
    }
}
