import java.io.*;
import java.net.*;
class ClientExample1 {
public static void main(String[] args) {
Socket socket = null;
try {
socket = new Socket("172.30.1.54", 9004);
InputStream in = socket.getInputStream();
OutputStream out = socket.getOutputStream();
String str = "Hello, Server";
out.write(str.getBytes());
byte arr[] = new byte[100];
in.read(arr);
System.out.println(new String(arr));
}
catch (Exception e) {
System.out.println(e.getMessage());
}
finally {
try {
socket.close();
}
catch (Exception e) {
}
}
}
}

