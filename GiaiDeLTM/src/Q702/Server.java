
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Đề bài : 
 * + Sử dụng InputStream và OutputStream
 * + Client gửi mã sv , mã câu hỏi lên server
 * + Server gửi trả 1 dãy các số cách nhau bới khoảng trắng dưới dạng mảng byte
 * + Client nhân được, tìm số lớn thứ 2 và các vị trí của nó rồi gửi trả cũng dưới dạng chuỗi cách nhau bởi dấu ' '
 */
public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Start server...");
        ServerSocket server = new ServerSocket(1107);
        while(true){
        Socket client = server.accept();
        
        // Nhận thông tin sinh viên gửi lên
        InputStream is = client.getInputStream();
        byte[] dataReceive = new byte[1024];
        is.read(dataReceive);
        String maSV = new String(dataReceive).trim();
        System.out.println("Thông tin sinh viên gửi lên : " + maSV);
        
        // Gửi trả một mảng số
        OutputStream os = client.getOutputStream();
        String data = "3 -12 -13 -14 -12 -12 -16 -18";
        byte[] dataSent = data.getBytes();
        os.write(dataSent);
        System.out.println("Server gửi chuỗi số cho client thành công...");
//        nó đau r
        // Nhận kết quả trả về
        dataReceive = new byte[1024];
        is.read(dataReceive);
        String result = new String(dataReceive).trim();
        System.out.println("Kết quả nhận được : " + result);
        }
//        is.close();
//        os.close();
//        client.close();
//        server.close();
    }
}
