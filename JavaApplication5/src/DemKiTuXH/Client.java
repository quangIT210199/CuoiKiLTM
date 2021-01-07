/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemKiTuXH;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost",8501);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        
        bw.write("B17DCAT148;803");
        bw.newLine();
        bw.flush();
        
        String chuoi = br.readLine();
        System.out.println(chuoi);
        
        String[] arrStr = chuoi.split(";");
        String req = arrStr[0];
        String num = arrStr[1];
        
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            if(c == 0){
                break;
            }
            if (hm.containsKey(c)) {
                hm.replace(c, hm.get(c)+1);               
            }else{
                hm.put(c, 1);
            }
        }
        int max = 0;       
        char c = 0;
        for (Map.Entry<Character, Integer> entry : hm.entrySet()) {
            Character key = entry.getKey();
            Integer val = entry.getValue();            
            System.out.println(key + ":" + val);
            if (max < val) {
                max = val;                
                c = key;
            }
        }                
        String kq = c +":";

        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == c) {
                kq += i + ",";
            }
        }
        System.out.println("Result: " + kq.trim());
        
        bw.write(kq);
        bw.newLine();
        bw.flush();
    }
    
    public static String xuLy(String str){
        HashMap<Character, Integer> hash = new HashMap<>();
        
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if(hash.containsKey(c)){ // nếu tồn tại thay thế và tăng đươn vị
                hash.replace(c, hash.get(c) + 1);
            }
            else{//nếu k có thì cho vô hash
                hash.put(c, 1);
            }
        }
        //tìm từ xuất hiện nhiều 
        int max = 0;
        char c = 0;
        for (Map.Entry<Character, Integer> entry : hash.entrySet()) {
            Character key = entry.getKey();
            Integer val = entry.getValue();
            System.out.println(key + ":" + val);
            if(val > max){
                max = val;
                c = key;
            }
        }
        
        String kq = c +":";
        
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == c){
                kq += i + ",";
            }
        }
        
        return kq;
    }
}