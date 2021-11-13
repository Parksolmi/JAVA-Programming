package studyCafe;
import java.awt.*;
import javax.swing.*;

import javax.swing.JFrame;

public class GUI
{
	public static void main(String[] args) throws Exception {
		
		//모드 선택하는 윈도우
		
		
		//프레임 생성
		JFrame frame = new JFrame("Mode");
		//프레임 크기 바꾸기
		frame.setPreferredSize(new Dimension(600, 300));
		//윈도우 위치 설정
		frame.setLocation(500, 400);
		
		//content pane 생성
		Container contentPane = frame.getContentPane();
		
		//로고 텍스트
		contentPane.add(new JLabel("STUDY CAFE" ,SwingConstants.CENTER));
		
		//모드 버튼
		//패널 생성
		JPanel buttonPanel = new JPanel();
		//레이아웃
		FlowLayout buttonlayout = new FlowLayout();
		buttonPanel.setLayout(buttonlayout);
		//버튼 생성
		JButton managerButton = new JButton("Manager");
		JButton ownerButton = new JButton("Owner");
		managerButton.setPreferredSize(new Dimension(150, 70));
		ownerButton.setPreferredSize(new Dimension(150, 70));
		buttonPanel.add(managerButton);
		buttonPanel.add(ownerButton);
		
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		
		//x버튼 눌렀을 때 이벤트처리
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//프레임 디스플레이
		frame.pack();
		frame.setVisible(true);
	}
}
