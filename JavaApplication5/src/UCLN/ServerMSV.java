/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UCLN;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nhuva
 */
public class ServerMSV {
    public static void main(String[] args) {
        DataOutputStream out;
        DataInputStream inp;
        String msv;
        int a=8,b=6,ucln,bcnn,tong, tich;
        try {
            ServerSocket server =new ServerSocket(1110);
            System.out.println("Server started...");
            while(true){
                Socket conn=server.accept();
                inp=new DataInputStream(conn.getInputStream());
                msv=inp.readUTF();
                System.out.println(msv);
                out=new DataOutputStream(conn.getOutputStream());
                out.writeInt(a);
                out.writeInt(b);
                
                ucln=inp.readInt();
                bcnn=inp.readInt();
                tong=inp.readInt();
                tich=inp.readInt();
                System.out.println("UCLN:" +ucln+"\nBCNN: "+bcnn+"\nTong: "+tong+"\nTich: "+tich);
                out.close();
                inp.close();
                conn.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerMSV.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
