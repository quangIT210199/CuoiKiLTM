/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q721;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author HoangHung
 */
public class Client {

//    public static String solve(String str) {
//        Map<Character, Integer> map = new HashMap<>();
//        for (int i = 0; i < str.length(); i++) {
//            if (str.charAt(i) != ' ') {
//                if (map.containsKey(str.charAt(i))) {
//                    map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
//                } else {
//                    map.put(str.charAt(i), 1);
//                }
//            }
//        }
//        Set<Character> keySet = map.keySet();
//        String result = "";
//        for (Character i : keySet) {
//            if (map.get(i) >= 2) {
//                result += i + ":" + map.get(i) + ",";
//            }
//        }
//    return result ;
//}
    public static String solve(String str) {
        String s = "";
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' ' && s.indexOf(str.charAt(i)) == -1) {
                int sl=1;
                for (int j = i + 1; j < str.length(); j++) {
                    if(str.charAt(j)==str.charAt(i)) sl++;
                }
                if(sl>=2){
                result+=str.charAt(i)+":"+sl+",";
                }
                s=s+str.charAt(i);
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1108);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write("B17DCCN284" + "\n");
        out.flush();
        String question = in.readLine();
        System.out.println(question);
        out.write(solve(question));
        out.newLine();
        out.flush();
    }
}
