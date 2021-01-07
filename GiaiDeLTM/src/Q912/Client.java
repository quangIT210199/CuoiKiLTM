/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q912;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author HoangHung
 */
public class Client {

    StringBuilder str1 = new StringBuilder();
    StringBuilder str2 = new StringBuilder();

    public void solve(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetterOrDigit(str.charAt(i))) {
                str1.append(str.charAt(i));
            } else {
                str2.append(str.charAt(i));
            }
        }
    }

    public Client() throws IOException {
        Socket socket = new Socket("localhost", 1108);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out.write("B17DCCN284;912");
        out.newLine();
        out.flush();
        String question = in.readLine();
        System.out.println(question);
        solve(question);
        out.write(str1.toString());
        out.newLine();
        out.flush();
        out.write(str2.toString());
        out.newLine();
        out.flush();
        socket.close();

    }
    public static void main(String[] args) throws IOException {
        Client client=new Client();
    }
}
