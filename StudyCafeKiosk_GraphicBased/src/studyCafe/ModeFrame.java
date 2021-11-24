package studyCafe;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class ModeFrame extends JFrame {

	//1. 모드 선택하는 윈도우
	
	
	private JButton managerButton; // 매니저모드 버튼
	private JButton ownerButton; // 오너모드 버튼

	public ModeFrame(Management mg)
	{
		getContentPane().setBackground(new Color(119, 136, 153));
		// 프레임 크기
		this.setSize(700, 400);
		// 윈도우 위치 설정
		this.setLocation(600, 300);
		// 윈도우 타이틀 설정
		this.setTitle("Mode");
		// content pane 생성
		Container modeCP = this.getContentPane();

		// 레이아웃 설정(위치와 크기 자유지정)
		modeCP.setLayout(null);

		// Mode를 위한 JPanel 생성
		JPanel modePanel = new JPanel();
		modePanel.setBackground(new Color(112, 128, 144));
		modePanel.setBounds(0, 0, 684, 361);
		modePanel.setLayout(null);
		// Log out을 위한 JPanel생성
		JPanel logOutPanel = new JPanel();
		logOutPanel.setBackground(new Color(112, 128, 144));
		logOutPanel.setBounds(0, 0, 684, 361);
		logOutPanel.setLayout(null);
		
		// 로고 텍스트
		JLabel logo1 = new JLabel("STUDY CAFE");
		logo1.setForeground(Color.WHITE);
		logo1.setBounds(205, 81, 290, 50);
		logo1.setHorizontalAlignment(JLabel.CENTER);
		modePanel.add(logo1);
		// 폰트 크기
		logo1.setFont(new Font("Serif", Font.BOLD, 40));

		// 모드 버튼 생성
		// 매니저 버튼
		managerButton = new JButton("Manager");
		managerButton.setForeground(Color.WHITE);
		managerButton.setBackground(new Color(25, 25, 112));
		managerButton.setFont(new Font("굴림", Font.BOLD, 18));
		managerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ManagerFrame mf = new ManagerFrame(mg);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		managerButton.setBounds(147, 226, 150, 50);
		modePanel.add(managerButton);

		// 오너 버튼
		ownerButton = new JButton("Owner");
		ownerButton.setForeground(Color.WHITE);
		ownerButton.setBackground(new Color(25, 25, 112));
		ownerButton.setFont(new Font("굴림", Font.BOLD, 18));
		ownerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 패널 변경
				logOutPanel.setVisible(true);
				modePanel.setVisible(false);

			}
		});
		ownerButton.setBounds(397, 226, 150, 50);
		modePanel.add(ownerButton);

		// Log out Panel
		// 뒤로가기 버튼
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modePanel.setVisible(true);
				logOutPanel.setVisible(false);
			}
		});
		btnNewButton.setBackground(new Color(25, 25, 112));
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 17));
		btnNewButton.setBounds(24, 21, 103, 40);
		logOutPanel.add(btnNewButton);

		// Log out Panel
		// 로고 텍스트
		// 로고 텍스트
		JLabel logo2 = new JLabel("STUDY CAFE");
		logo2.setForeground(Color.WHITE);
		logo2.setBounds(205, 81, 290, 50);
		logo2.setHorizontalAlignment(JLabel.CENTER);
		logOutPanel.add(logo2);
		// 폰트 크기
		logo2.setFont(new Font("Serif", Font.BOLD, 40));

		// 아이디 입력
		JLabel lblNewLabel = new JLabel("Owner ID : ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setBounds(112, 213, 112, 33);
		logOutPanel.add(lblNewLabel);

		JTextField textField = new JTextField();
		textField.setBounds(225, 212, 247, 40);
		logOutPanel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("Log in");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(25, 25, 112));
		btnNewButton_1.setFont(new Font("굴림", Font.BOLD, 17));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 아이디 일치 여부 확인
				if (mg.checkManagerID(textField.getText())) // 일치할 때
				{
					OwnerFrame of = new OwnerFrame(mg);
					textField.setText(""); //텍스트 필드에 입력한 아이디 지우기
					//panel visible설정
					modePanel.setVisible(true);
					logOutPanel.setVisible(false);
				} else // 불일치 할 때
				{
					MessageFrame ef = new MessageFrame("Wrong ID");
					textField.setText(""); //텍스트 필드에 입력한 아이디 지우기
				}
			}
		});
		btnNewButton_1.setBounds(484, 211, 103, 40);
		logOutPanel.add(btnNewButton_1);
		
		//panel visible설정
		modePanel.setVisible(true);
		logOutPanel.setVisible(false);
		//contentPane에 생성한 panel 추가
		modeCP.add(logOutPanel);
		modeCP.add(modePanel);
		// x버튼 눌렀을 때 이벤트처리
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 프레임 디스플레이
		this.setVisible(true);
	}
}
