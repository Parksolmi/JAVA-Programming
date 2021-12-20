import java.net.*;

public class ClientExample3 {
	public static void main(String[] args)
	{
		Socket socket = null;
		
		try {
			socket = new Socket("172.18.5.51", 9002);
			Thread thread1 = new SenderThread(socket);
			Thread thread2 = new ReceiverThread(socket);
			thread1.start();
			thread2.start();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
