package OutPutStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Client {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket client = new Socket(InetAddress.getByName("localhost"), 1107);
        
        // Gửi mã sinh viên lên server
        String maSV = "B17DCCN642;908";
        byte[] dataSent = maSV.getBytes();
        OutputStream os = client.getOutputStream();
        os.write(dataSent);
        os.flush();
        System.out.println("Gửi mã lên server thành công !");
        
        // Nhận chuỗi các số từ server gửi về
        byte[] dataReceive = new byte[1024];
        InputStream is = client.getInputStream();
        is.read(dataReceive);
        String data = new String(dataReceive).trim();
        System.out.println("Chuỗi số nhận được : " + data);
        
        // Xử lý chuỗi số
        String ss[] = data.split(" ");
        ArrayList<Integer> numbers = new ArrayList<>();
        for(int i = 0; i < ss.length; i++){
            numbers.add(Integer.parseInt(ss[i]));
        }
        
        
        int MAX  = Collections.max(numbers);
        int min = Collections.min(numbers);
        
        System.out.println(MAX);
        System.out.println(min);
        
        int MAX_2 = Integer.MIN_VALUE;
        for(int i = 0; i < numbers.size(); i++){
            if (numbers.get(i) >= MAX_2 && numbers.get(i) != MAX) {
                MAX_2 = numbers.get(i);
            }
        }
        
        // Xử lý kết quả gửi về server
        String result = String.valueOf(MAX_2);
        for(int i = 0; i < numbers.size(); i++){
            if (numbers.get(i) == MAX_2) {
                result += " " + String.valueOf(i + 1);
            }
        }
        result = result.trim();
        
        System.out.println( result);
        
        // Gửi về server
        dataSent = result.getBytes();
        os.write(dataSent);
        System.out.println("Đã gửi kết quả về server...");
        
        os.flush();
        os.close();
        is.close();
        client.close();
        
    }
}
