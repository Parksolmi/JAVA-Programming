import javax.swing.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.border.EmptyBorder;
import java.awt.event.*;

@SuppressWarnings("serial")
public class ServerFrame extends JFrame{
	
	private JPanel contentPane;
	private JTextArea textArea;
	private JTextField portAddress;
	private JTextField input;
	private JButton btnNewButton_2;
	
	//메세지 주고 받을 버퍼
	BufferedReader reader;
	PrintWriter writer;
	
	//소켓
	ServerSocket serverSocket;
	Socket socket;
	
	//메세지 받기 종료
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
		
		//채팅창
		textArea = new JTextArea();
		textArea.setBounds(12, 61, 690, 393);
		textArea.setEditable(false); //쓰지 못함
		contentPane.add(textArea);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 61, 690, 393);
		contentPane.add(scrollPane);
		
		//포트 주소 입력
		JLabel lblNewLabel_1 = new JLabel("\uD3EC\uD2B8 \uC8FC\uC18C :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("나눔고딕", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 21, 84, 28);
		contentPane.add(lblNewLabel_1);
		
		portAddress = new JTextField();
		portAddress.setColumns(10);
		portAddress.setBounds(96, 21, 154, 28);
		contentPane.add(portAddress);

		// 접속 버튼
		JButton btnNewButton_1 = new JButton("\uC811\uC18D");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 스레드 생성 및 시작
				ServerThread st = new ServerThread();
				st.setDaemon(true); // 메인 종료시 스레드도 같이 종료
				st.start(); // 스레드 시작
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
		//엔터 키 눌렀을 때 - 전송
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
					if (serverSocket != null)
						serverSocket.close();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	//소켓 생성 & 받은 메세지 계속해서 읽어오기
	class ServerThread extends Thread {
		public void run() {
			try {
				// 포트 번호 형변환
				int portNum = Integer.parseInt(portAddress.getText());

				// 서버 소캣 생성
				serverSocket = new ServerSocket(portNum);
				textArea.append("서버 소켓이 준비되었습니다.\n");
				textArea.append("클라이언트의 접속을 기다리는 중입니다...\n");

				// 요청이 들어옴
				socket = serverSocket.accept();
				textArea.append(socket.getInetAddress().getHostAddress() + "님이 접속하셨습니다.\n");
				
				// 통신을 위한 reader & writer
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream());

				while (true) {
					//상대방이 보내온 메세지 읽기
					String str = reader.readLine();
					textArea.append("Client> " + str + "\n");
					textArea.setCaretPosition(textArea.getText().length()); //스크롤 따라가도록
					
					if (isExit == true) {
						textArea.append("채팅을 종료합니다.\n");
						break;
					}
				}
				
			} catch (IOException e) {
				textArea.append("클라이언트가 나갔습니다.\n");
			} finally {
				try {
					socket.close();
				} catch (Exception ignored) {
				}
			}

		}
	}
	
	//메세지 보내기
	void sendMessage() {
		
		//보낼 메세지 textField에서 읽어오기
		String msg = input.getText();
		//textField지우기
		input.setText("");
		
		//채팅창에 표시
		textArea.append("Server> " + msg + "\n");
		textArea.setCaretPosition(textArea.getText().length()); //스크롤 따라가도록
		
		Thread thread1 = new Thread() {
			
			public void run() {
				try {
					writer.write(msg);
					writer.flush(); //버퍼 출력 및 비우기
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		};
		thread1.start();
	}
}
