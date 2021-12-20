import javax.swing.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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
	
	//메세지 주고 받을 버퍼
	BufferedReader reader;
	PrintWriter writer;
	
	//소켓
	Socket socket;
	
	//메세지 받기 종료
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
		
		//채팅창
		textArea = new JTextArea();
		textArea.setBounds(12, 61, 690, 393);
		textArea.setEditable(false); //쓰지 못함
		contentPane.add(textArea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 61, 690, 393);
		contentPane.add(scrollPane);
		
		//IP주소 입력
		JLabel lblNewLabel = new JLabel("IP \uC8FC\uC18C : ");
		lblNewLabel.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 23, 84, 28);
		contentPane.add(lblNewLabel);
		
		ipAddress = new JTextField();
		ipAddress.setBounds(89, 23, 154, 28);
		contentPane.add(ipAddress);
		ipAddress.setColumns(10);
		
		//포트 주소 입력
		JLabel lblNewLabel_1 = new JLabel("\uD3EC\uD2B8 \uC8FC\uC18C :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(278, 23, 84, 28);
		contentPane.add(lblNewLabel_1);

		portAddress = new JTextField();
		portAddress.setColumns(10);
		portAddress.setBounds(364, 23, 154, 28);
		contentPane.add(portAddress);

		// 접속 버튼
		JButton btnNewButton_1 = new JButton("\uC811\uC18D");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//스레드 생성 및 시작
				ClientThread ct = new ClientThread();
				ct.setDaemon(true);
				ct.start();
			}
		});
		btnNewButton_1.setBounds(591, 23, 111, 27);
		contentPane.add(btnNewButton_1);
		
		// 채팅 입력 창
		input = new JTextField();
		input.setBounds(12, 464, 480, 37);
		contentPane.add(input);
		input.setColumns(10);
		
		// 전송 버튼
		btnNewButton_2 = new JButton("\uC804\uC1A1");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		btnNewButton_2.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btnNewButton_2.setBounds(504, 464, 95, 37);
		contentPane.add(btnNewButton_2);
		// 엔터 키 눌렀을 때 - 전송
		input.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);

				int keyCode = e.getKeyCode();
				if (keyCode == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});

		// 종료 버튼
		JButton btnNewButton = new JButton("\uC885\uB8CC");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 채팅 종료
				isExit = true;
			}
		});
		btnNewButton.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		btnNewButton.setBounds(607, 464, 95, 37);
		contentPane.add(btnNewButton);
		
		
		setVisible(true);
		
		// 창 닫을 때 이벤트 처리
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				try {
					if (reader != null)
						reader.close();
					if (writer != null)
						writer.close();
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
				
				//포트 번호 형변환
				int portNum = Integer.parseInt(portAddress.getText());
				
				//소켓 생성
				socket = new Socket(ipAddress.getText(), portNum);
				textArea.append("서버에 접속하였습니다.\n");
				
				// 통신을 위한 reader & writer
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream());
				
				//메세지 받기
				while(true) {
					String msg = reader.readLine();
					textArea.append("Server> " + msg + "\n");
					textArea.setCaretPosition(textArea.getText().length());
					
					if (isExit == true) {
						textArea.append("채팅을 종료합니다.\n");
						break;
					}
				}
			} catch(UnknownHostException e) {
				textArea.append("서버 주소가 잘못되었습니다.\n");
			} catch(IOException e) {
				textArea.append("서버와 연결이 끊겼습니다.\n");
			}
		}
	}
	
	void sendMessage() {
		//입력창의 메세지 가져오기
		String msg = input.getText();
		input.setText("");
		
		textArea.append("Client> " + msg + "\n");
		textArea.setCaretPosition(textArea.getText().length());
		
		Thread thread2 = new Thread() {
			public void run() {
				try {
					writer.write(msg);
					writer.flush();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		};
		thread2.start();
	}
}
