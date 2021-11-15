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
				//프레임 크기
				this.setSize(600, 300);
				//윈도우 위치 설정
				this.setLocation(600, 400);
				//윈도우 타이틀 설정
				this.setTitle("Mode");
				//content pane 생성
				Container modeCP = this.getContentPane();
				
				//레이아웃 설정(위치와 크기 자유지정)
				modeCP.setLayout(null);
				
				//로고 텍스트
				JLabel logo = new JLabel("STUDY CAFE");
				logo.setBounds(190, 50, 200, 50);
				logo.setHorizontalAlignment(JLabel.CENTER);
				modeCP.add(logo);
				//폰트 크기
				logo.setFont(new Font("Serif", Font.BOLD, 30));
				
				//모드 버튼 생성
				//매니저 버튼
				managerButton = new JButton("Manager");
				managerButton.setFont(new Font("굴림", Font.BOLD, 15));
				managerButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							ManagerFrame mf = new ManagerFrame(mg);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
				
				managerButton.setBounds(100, 160, 150, 50);
				modeCP.add(managerButton);
				
				//오너 버튼
				ownerButton = new JButton("Owner");
				ownerButton.setFont(new Font("굴림", Font.BOLD, 15));
				ownerButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						LogInFrame lf = new LogInFrame(mg);
					}
				});
				
				ownerButton.setBounds(350, 160, 150, 50);
				modeCP.add(ownerButton);
				
				//x버튼 눌렀을 때 이벤트처리
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//프레임 디스플레이
				this.setVisible(true);
			}

}
