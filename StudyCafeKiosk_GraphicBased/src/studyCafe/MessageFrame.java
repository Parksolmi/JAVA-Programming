package studyCafe;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

@SuppressWarnings("serial")
public class MessageFrame extends JFrame {

	private JPanel contentPane;

	public MessageFrame(String message) {
		
		//������ Ÿ��Ʋ ����
		this.setTitle("Message");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(750, 450, 500, 200);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(112, 128, 144));
		//���̾ƿ� ����(��ġ�� ũ�� ��������)
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//������ ���÷���
		setVisible(true);
		
		JLabel lblNewLabel = new JLabel(message);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(46, 31, 386, 58);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(25, 25, 112));
		btnNewButton.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton.setBounds(179, 98, 125, 43);
		
		//��ư �̺�Ʈ
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		contentPane.add(btnNewButton);
	}
}
