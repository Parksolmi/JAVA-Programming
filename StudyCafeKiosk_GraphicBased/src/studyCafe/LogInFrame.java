package studyCafe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogInFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	public LogInFrame(Management mg) {

		setTitle("Log In");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(600, 400, 600, 300);
		contentPane = new JPanel();
		//���̾ƿ� ����(��ġ�� ũ�� ��������)
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//�ڷΰ��� ��ư
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setFont(new Font("����", Font.BOLD, 17));
		btnNewButton.setBounds(12, 10, 87, 40);
		contentPane.add(btnNewButton);

		//�ΰ� �ؽ�Ʈ
		JLabel logo = new JLabel("STUDY CAFE");
		logo.setBounds(190, 50, 200, 50);
		logo.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(logo);
		//��Ʈ ũ��
		logo.setFont(new Font("Serif", Font.BOLD, 30));
		
		//���̵� �Է�
		JLabel lblNewLabel = new JLabel("Owner ID : ");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel.setBounds(77, 141, 112, 33);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(190, 140, 247, 40);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Log in");
		btnNewButton_1.setFont(new Font("����", Font.BOLD, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//���̵� ��ġ ���� Ȯ��
				if(mg.checkManagerID(textField.getText())) //��ġ�� ��
				{
					setVisible(false);
					OwnerFrame of = new OwnerFrame(mg);
				}
				else //����ġ �� ��
				{
					MessageFrame ef = new MessageFrame("Wrong ID");
				}
			}
		});
		btnNewButton_1.setBounds(449, 139, 87, 40);
		contentPane.add(btnNewButton_1);
		
		//������ ���÷���
		setVisible(true);
		
		
	}
}
