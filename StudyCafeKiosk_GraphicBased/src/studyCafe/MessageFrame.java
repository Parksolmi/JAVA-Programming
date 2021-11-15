package studyCafe;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MessageFrame extends JFrame {

	private JPanel contentPane;

	public MessageFrame(String message) {
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(600, 400, 600, 300);
		contentPane = new JPanel();
		//레이아웃 설정(위치와 크기 자유지정)
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//프레임 디스플레이
		setVisible(true);
		
		JLabel lblNewLabel = new JLabel(message);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(46, 34, 488, 111);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton.setBounds(216, 155, 150, 48);
		
		//버튼 이벤트
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		contentPane.add(btnNewButton);
	}
}
