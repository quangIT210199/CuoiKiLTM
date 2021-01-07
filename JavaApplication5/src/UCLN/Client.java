/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UCLN;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author quang
 */
public class Client {
    static int UCLN(int a, int b){
        int tmp;
        
        while(b != 0){
            tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
    
    public static void main(String[] args) {
        DataInputStream in;
        DataOutputStream out;
        
        String msv="B17DCAT148,911";
        int a, b, ucln, bcnn, tong, tich;
        
        try {
            Socket client = new Socket("localhost", 1110);
            out = new DataOutputStream(client.getOutputStream());
            out.writeUTF(msv);
            in = new DataInputStream(client.getInputStream());
            
            a = in.readInt();
            b = in.readInt();
            ucln = UCLN(a, b);
            
            if(ucln != 0){
                bcnn = (a*b)/ucln;
            }else{
                bcnn = 0;
            }
            
            tong = a + b;
            tich = a * b;
            
            out.writeInt(ucln);
            out.writeInt(bcnn);
            out.writeInt(tong);
            out.writeInt(tich);
            
            in.close();
            out.close();
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
