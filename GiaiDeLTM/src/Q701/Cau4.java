/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author DELL
 */
public class Cau4 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1106);
        byte[] data = new byte[1024];
        OutputStream out = socket.getOutputStream();
        out.write("B17DCCN530".getBytes());
        InputStream in = socket.getInputStream();
        in.read(data);
        ArrayList<Integer> arr = new ArrayList<>();
        String[] s = (new String(data)).trim().split(",");
        for(int i=0; i<s.length; i++){
            System.out.println(s[i]+".");
            arr.add(Integer.parseInt(s[i]));
        }
        Collections.sort(arr);
        int min = Integer.MAX_VALUE;
        int a=0, b=0;
        for(int i=1; i< arr.size(); i++){
            int c = arr.get(i) - arr.get(i-1);
            if(c < min){
                min = c;
                a = arr.get(i);
                b = arr.get(i-1);
            }
        }
        String result = min + "," + a + "," + b;
        out.write(result.getBytes());
        out.close();
        in.close();
    }
}
