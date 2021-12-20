import javax.swing.*;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.border.EmptyBorder;
import java.awt.event.*;

@SuppressWarnings("serial")
public class ClientFrame extends JFrame{
	
	private JPanel contentPane;
	private JTextArea textArea;
	private JTextField ipAddress;
	private JTextField portAddress;
	private JTextField input;
	private JButton btnNewButton_2;
	
	// �޼��� �ְ� ���� ����
	DataInputStream dis;
	DataOutputStream dos;
	
	//����
	Socket socket;
	
	//�޼��� �ޱ� ����
	boolean isExit = false;
	
	public ClientFrame()
	{
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 730, 550);
		setTitle("Client Chatting Window");
		
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
		
		//IP�ּ� �Է�
		JLabel lblNewLabel = new JLabel("IP \uC8FC\uC18C : ");
		lblNewLabel.setFont(new Font("�������", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 23, 84, 28);
		contentPane.add(lblNewLabel);
		
		ipAddress = new JTextField();
		ipAddress.setBounds(89, 23, 154, 28);
		contentPane.add(ipAddress);
		ipAddress.setColumns(10);
		
		//��Ʈ �ּ� �Է�
		JLabel lblNewLabel_1 = new JLabel("\uD3EC\uD2B8 \uC8FC\uC18C :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("�������", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(278, 23, 84, 28);
		contentPane.add(lblNewLabel_1);

		portAddress = new JTextField();
		portAddress.setColumns(10);
		portAddress.setBounds(364, 23, 154, 28);
		contentPane.add(portAddress);

		// ���� ��ư
		JButton btnNewButton_1 = new JButton("\uC811\uC18D");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(ipAddress.getText() != null && portAddress.getText()!=null)
				{
					isExit = false;
					
					//������ ���� �� ����
					ClientThread ct = new ClientThread();
					ct.setDaemon(true);
					ct.start();
				}
				else
				{
					if(ipAddress.getText() == null)
					{
						textArea.append("������ �ּҸ� �Է��ϼ���.\n");
					}
					else
					{
						textArea.append("��Ʈ ��ȣ�� �Է��ϼ���.\n");
					}
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
		btnNewButton_2.setFont(new Font("�������", Font.PLAIN, 15));
		btnNewButton_2.setBounds(504, 464, 95, 37);
		contentPane.add(btnNewButton_2);
		// ���� Ű ������ �� - ����
		input.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);

				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_ENTER) {
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
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("�������", Font.PLAIN, 15));
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
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	class ClientThread extends Thread {
		public void run() {
			try {
				
				int portNum;
				
				// ��Ʈ ��ȣ ����ȯ
				portNum = Integer.parseInt(portAddress.getText());

				// ���� ����
				socket = new Socket(ipAddress.getText(), portNum);
				textArea.append("������ �����Ͽ����ϴ�.\n");

				// ����� ���� reader & writer
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());

				// �޼��� �ޱ�
				while (!isExit) {
					String msg = dis.readUTF();
					textArea.append("Server> " + msg + "\n");
					textArea.setCaretPosition(textArea.getText().length());
				}
				
			} catch(UnknownHostException e) {
				textArea.append("���� �ּҰ� �߸��Ǿ����ϴ�.\n");
			} catch(IOException e) {
				textArea.append("������ ������ ������ϴ�.\n");
			} catch(NumberFormatException nfe) {
				textArea.append("��Ʈ ��ȣ�� ����� �Է����ּ���\n");
			} finally {
				try {
					if (dis != null)
						dis.close();
					if (dos != null)
						dos.close();
					if (socket != null)
						socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		}
	}
	
	void sendMessage() {
		
		//�Է�â�� �޼��� ��������
		String msg = input.getText();
		input.setText("");
		
		//ä��â�� ǥ��
		textArea.append("Client> " + msg + "\n");
		textArea.setCaretPosition(textArea.getText().length()); // ��ũ�� ���󰡵���
		
		Thread thread2 = new Thread() {
			public void run() {
				try {
					dos.writeUTF(msg);
					dos.flush(); //���� ��� �� ����
					
				} catch(SocketException se) {
					textArea.append("ä���� ����Ǿ����ϴ�. �ٽ� ä���� ���Ѵٸ� ������ ���ּ���.\n");
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		};
		thread2.start();
	}
}
