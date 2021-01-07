/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP_918;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author quang
 */
public class Client {
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket client = new Socket("localhost",1110);
        
        ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
        
        oos.writeUTF("B17DCAT148;918");
        oos.flush();
        
        Customer918 cus = (Customer918) ois.readObject();
        System.out.println(cus.getName() +" "+ cus.getDayOfBirth());
        String name = cus.getName();
        String date = cus.getDayOfBirth();
        
        cus.setName(chuanHoaTen(name));
        
        cus.setDayOfBirth(chDate(date));
        
        System.out.println(cus.getName() +" "+ cus.getDayOfBirth());
        System.out.println("quang");
        
        oos.writeObject(cus);
        oos.flush();
        
        oos.close();
        ois.close();
        client.close();

    }
    
    private static String chuanHoaTen(String str) {
        String res = "";
        int idx = str.lastIndexOf(" ") + 1;
        String hoDem = str.substring(0, idx);
        String ten = str.substring(idx);

        ten = ten.toUpperCase();
        String hd2 = "";
        for (int i = 0; i < hoDem.length(); i++) {
            if (i == 0 || hoDem.charAt(i - 1) == ' ') {
                hd2 += Character.toUpperCase(hoDem.charAt(i));
            } else {
                hd2 += hoDem.charAt(i);
            }
        }
        res += ten + ", " + hd2;
        return res;
    }
    
    private static String chDate(String date) {
        String[] strArr = date.split("-");

        return strArr[1] + "/" + strArr[0] + "/" + strArr[2];
    }
}
