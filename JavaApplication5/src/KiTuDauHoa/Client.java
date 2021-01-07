/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KiTuDauHoa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author quang
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("localhost",8501);
        BufferedWriter bw  = 
                new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        BufferedReader br = 
                new BufferedReader(new InputStreamReader(s.getInputStream()));
        
        String chuoi = br.readLine().trim();
        System.out.println(chuoi);
        
        chuoi = chuoi.toLowerCase();//cho thành chữ thường hết
        String kq="";
        
        for (int i = 0; i < chuoi.length(); i++) {
//            if(i == 0 || (Character.isAlphabetic(chuoi.charAt(i)) && chuoi.charAt(i-1) == ' ' )){
//                kq += Character.toUpperCase(chuoi.charAt(i));
//            }else{
//                kq += chuoi.charAt(i);
//            }
            if(i == 0 || chuoi.charAt(i - 1) == ' '){
                kq += Character.toUpperCase(chuoi.charAt(i));
            }
            else{
                kq += chuoi.charAt(i);
            }
        }
        
        bw.write(kq);
        bw.newLine();
        bw.flush();
        
        br.close();
        bw.close();
        s.close();
    }
}
