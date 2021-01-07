/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PhanTuThieu;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author quang
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 1108);
        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        
        dos.writeUTF("B17DCAT148;802");
        
        String nhan = dis.readUTF();
        System.out.println("nhan " + nhan);
        //String nhanve = "reqID;10;4,5,3,2,7,2";
        
        String reqID = nhan.trim().split(";")[0];
        int n = Integer.parseInt(nhan.trim().split(";")[1]);
        
        String chuoi = nhan.trim().split(";")[2];
        String []str = chuoi.split(",");
        
        int[] arr = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i].trim());
        }
        
        String kq = reqID+";";
        
        for (int i = 1; i < n; i++) {
            if(check(i, arr) == false){
                kq = kq + i + ",";
            }
        }
        
        kq = kq.substring(0, kq.length() - 1);//để xóa dấu , ở cuối
        System.out.println(kq);
        dos.writeUTF(kq);
        
        dis.close();
        dos.close();
        client.close();
    }
    
    public static boolean check(int a, int b[]){
        for (int i = 0; i < b.length; i++) {
            if(a == b[i]){
                return true;
            }
        }
        
        return false;
    }
}
