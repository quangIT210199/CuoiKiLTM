/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MinKC;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Math.abs;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    public static void main(String[] args) {
        InputStream in;
        OutputStream out;
        
        try {
            Socket client = new Socket("localhost", 1107);
            //Nhận DL thôi
            in = client.getInputStream();
            out = client.getOutputStream();
            byte[] b = new byte[1024];
            
            String msv = "B17DCAT148;701";
            b = msv.getBytes();
            out.write(b);
            
            in.read(b);
            
            String s = new String(b).trim();
            String[] str = s.split(",");
            
            int[] arr = new int[str.length];
            for (int i = 0; i < str.length; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }
            
            int min = 10000, temp1 = 0, temp2 = 0;
            for (int i = 0; i < arr.length; i++) {
                for (int j = i + 1; j < arr.length; j++) {
                    int temp = abs(arr[i] - arr[j]);
                    if(temp < min){
                        min = temp;
                        temp1 = arr[i];
                        temp2 = arr[j];
                    }
                }
            }
           
            out.write(min);
            out.write(temp1);
            out.write(temp2);
            
            out.close();
            in.close();
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
