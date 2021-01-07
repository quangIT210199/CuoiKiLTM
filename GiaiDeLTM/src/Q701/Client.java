/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q701;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author HoangHung
 */
public class Client {

    public static String sovle(List<Integer> list) {
        int min=Integer.MAX_VALUE;
        String result="";
        for (int i = 1; i < list.size(); i++) {
            if(list.get(i)-list.get(i-1)<=min){
            min=list.get(i)-list.get(i-1);
            result=min+","+list.get(i-1)+","+list.get(i);
            }
        }
        return  result;
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1107);
        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();
        out.write("B17DCCN284".getBytes());
        byte question[] = new byte[1024];
        in.read(question);
        String num[] = new String(question).trim().split(",");
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < num.length; i++) {
            System.out.println(num[i]+"."+i);
            list.add(Integer.parseInt(num[i].trim()));
        }
       
        Collections.sort(list);
        String result=sovle(list);
        byte dataSent[]=new byte[1024];
        dataSent=result.getBytes();
        out.write(dataSent);
        out.flush();
    }
}
