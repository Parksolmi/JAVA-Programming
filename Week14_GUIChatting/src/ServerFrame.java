import javax.swing.*;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.border.EmptyBorder;
import java.awt.event.*;

@SuppressWarnings("serial")
public class ServerFrame extends JFrame{
	
	private JPanel contentPane;
	private JTextArea textArea;
	private JTextField portAddress;
	private JTextField input;
	private JButton btnNewButton_2;
	
	//�޼��� �ְ� ���� ����
	DataInputStream dis;
	DataOutputStream dos;
	
	//����
	ServerSocket serverSocket;
	Socket socket;
	
	//�޼��� �ޱ� ����
	boolean isExit = false;
	
	
	public ServerFrame()
	{
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 730, 550);
		setTitle("Server Chatting Window");
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//ä��â
		textArea = new JTextArea();
		textArea.setBounds(12, 61, 690, 393);
		textArea.setEditable(false); //���� ����
		contentPane.add(textArea);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(12, 61, 690, 393);
		contentPane.add(scrollPane);
		
		//��Ʈ �ּ� �Է�
		JLabel lblNewLabel_1 = new JLabel("\uD3EC\uD2B8 \uC8FC\uC18C :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("��������", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 21, 84, 28);
		contentPane.add(lblNewLabel_1);
		
		portAddress = new JTextField();
		portAddress.setColumns(10);
		portAddress.setBounds(96, 21, 154, 28);
		contentPane.add(portAddress);

		// ���� ��ư
		JButton btnNewButton_1 = new JButton("\uC811\uC18D");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(portAddress != null )
				{
					isExit = false;
					
					// ������ ���� �� ����
					ServerThread st = new ServerThread();
					st.setDaemon(true); // ���� ����� �����嵵 ���� ����
					st.start(); // ������ ����
				}
				else
				{
					textArea.append("��Ʈ ��ȣ�� �Է��ϼ���.\n");
				}
				
			}
		});
		btnNewButton_1.setBounds(591, 23, 111, 27);
		contentPane.add(btnNewButton_1);

		// ä�� �Է� â
		input = new JTextField();
		input.setBounds(12, 464, 480, 37);
		contentPane.add(input);
		input.setColumns(10);

		// ���� ��ư
		btnNewButton_2 = new JButton("\uC804\uC1A1");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		btnNewButton_2.setFont(new Font("��������", Font.PLAIN, 15));
		btnNewButton_2.setBounds(504, 464, 95, 37);
		contentPane.add(btnNewButton_2);
		//���� Ű ������ �� - ����
		input.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				
				int keyCode = e.getKeyCode();
				if(keyCode == KeyEvent.VK_ENTER)
				{
					sendMessage();
				}
			}
		});
		
		// ���� ��ư
		JButton btnNewButton = new JButton("\uC885\uB8CC");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ä�� ����
				isExit = true;
				
				try {
					if (dis != null)
						dis.close();
					if (dos != null)
						dos.close();
					if (socket != null)
						socket.close();
					if (serverSocket != null)
						serverSocket.close();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("��������", Font.PLAIN, 15));
		btnNewButton.setBounds(607, 464, 95, 37);
		contentPane.add(btnNewButton);
		
		setVisible(true);
		
		// â ���� �� �̺�Ʈ ó��
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				try {
					if (dis != null)
						dis.close();
					if (dos != null)
						dos.close();
					if (socket != null)
						socket.close();
					if (serverSocket != null)
						serverSocket.close();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	//���� ���� & ���� �޼��� ����ؼ� �о����
	class ServerThread extends Thread {
		public void run() {
			try {
				// ��Ʈ ��ȣ ����ȯ
				int portNum = Integer.parseInt(portAddress.getText());

				// ���� ��Ĺ ����
				serverSocket = new ServerSocket(portNum);
				textArea.append("���� ������ �غ�Ǿ����ϴ�.\n");
				textArea.append("Ŭ���̾�Ʈ�� ������ ��ٸ��� ���Դϴ�...\n");

				// ��û�� ����
				socket = serverSocket.accept();
				textArea.append(socket.getInetAddress().getHostAddress() + "���� �����ϼ̽��ϴ�.\n");
				
				// ����� ���� reader & writer
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());

				while (!isExit) {
					//������ ������ �޼��� �б�
					String str = dis.readUTF();
					textArea.append("Client> " + str + "\n");
					textArea.setCaretPosition(textArea.getText().length()); //��ũ�� ���󰡵���
				}
			} catch (IOException e) {
				textArea.append("Ŭ���̾�Ʈ�� ������ ����Ǿ����ϴ�.\n");
			} catch (NumberFormatException nfe) {
				textArea.append("��Ʈ ��ȣ�� ����� �Է����ּ���\n");
			} finally {
				try {
					if (dis != null)
						dis.close();
					if (dos != null)
						dos.close();
					if (socket != null)
						socket.close();
					if (serverSocket != null)
						serverSocket.close();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	
	//�޼��� ������
	void sendMessage() {
		
		//���� �޼��� textField���� �о����
		String msg = input.getText();
		//textField�����
		input.setText("");
		
		//ä��â�� ǥ��
		textArea.append("Server> " + msg + "\n");
		textArea.setCaretPosition(textArea.getText().length()); // ��ũ�� ���󰡵���

		Thread thread1 = new Thread() {

			public void run() {
				try {
					dos.writeUTF(msg);
					dos.flush(); // ���� ��� �� ����

				} catch(SocketException se) {
					textArea.append("ä���� ����Ǿ����ϴ�. �ٽ� ä���� ���Ѵٸ� ������ ���ּ���.\n");
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		};
		thread1.start();
	}
}