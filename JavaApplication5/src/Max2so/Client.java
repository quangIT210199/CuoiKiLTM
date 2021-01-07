/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Max2so;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quang
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 8500);
            DataInputStream in = 
                    new DataInputStream(client.getInputStream());
            DataOutputStream out = 
                    new DataOutputStream(client.getOutputStream());
            //String to array
            // ReqestID;3,2,4,1,4,5,6
            String chuoi = in.readUTF();
            String[] arr = chuoi.split(";");
            
            String req = arr[0].trim();
            String[] str = arr[1].trim().split(",");
            
            int[] num = new int[str.length];
            for (int i = 0; i < str.length; i++) {
                num[i] = Integer.parseInt(str[i].trim());
            }
            
            Arrays.sort(num);
            String kq = req +","+num[num.length - 2] +","+num[num.length-1];
            
            out.writeUTF(kq);
            
            client.close();
            in.close();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
