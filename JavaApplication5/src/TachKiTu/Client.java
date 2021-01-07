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
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    private static Socket socket;
    private static BufferedWriter bw;
    private static BufferedReader br;
    private static StringBuilder str1 = new StringBuilder();
    private static StringBuilder str2 = new StringBuilder();
    
    public static void main(String[] args) {
        try {
            StringBuilder rs = new StringBuilder();
            socket = new Socket("localhost", 1108);
            
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            String str = "B17DCAT148;701";
            bw.write(str);
            bw.newLine();
            bw.flush();
            
            str = br.readLine();
            System.out.println(str);
            xuly(str);
            System.out.println(str1.toString() +"\n" + str2.toString());
            
            bw.write(str1.toString());
            bw.newLine();
            bw.flush();
            
            bw.write(str2.toString());
            bw.newLine();
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                bw.close();
                br.close();
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static void xuly(String strDb) {

        for (int i = 0; i < strDb.length(); i++) {
            String strTemp = "";
            strTemp += strDb.charAt(i);
            if (strTemp.matches("[a-zA-z0-9]")) {
                str1.append(strTemp);
            } else
                str2.append(strTemp);
        }

    }
}
