package studyCafe;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class ModeFrame extends JFrame {

	//1. ��� �����ϴ� ������
	
	
	private JButton managerButton; // �Ŵ������ ��ư
	private JButton ownerButton; // ���ʸ�� ��ư

	public ModeFrame(Management mg)
	{
		getContentPane().setBackground(new Color(119, 136, 153));
		// ������ ũ��
		this.setSize(700, 400);
		// ������ ��ġ ����
		this.setLocation(600, 300);
		// ������ Ÿ��Ʋ ����
		this.setTitle("Mode");
		// content pane ����
		Container modeCP = this.getContentPane();

		// ���̾ƿ� ����(��ġ�� ũ�� ��������)
		modeCP.setLayout(null);

		// Mode�� ���� JPanel ����
		JPanel modePanel = new JPanel();
		modePanel.setBackground(new Color(112, 128, 144));
		modePanel.setBounds(0, 0, 684, 361);
		modePanel.setLayout(null);
		// Log out�� ���� JPanel����
		JPanel logOutPanel = new JPanel();
		logOutPanel.setBackground(new Color(112, 128, 144));
		logOutPanel.setBounds(0, 0, 684, 361);
		logOutPanel.setLayout(null);
		
		// �ΰ� �ؽ�Ʈ
		JLabel logo1 = new JLabel("STUDY CAFE");
		logo1.setForeground(Color.WHITE);
		logo1.setBounds(205, 81, 290, 50);
		logo1.setHorizontalAlignment(JLabel.CENTER);
		modePanel.add(logo1);
		// ��Ʈ ũ��
		logo1.setFont(new Font("Serif", Font.BOLD, 40));

		// ��� ��ư ����
		// �Ŵ��� ��ư
		managerButton = new JButton("Manager");
		managerButton.setForeground(Color.WHITE);
		managerButton.setBackground(new Color(25, 25, 112));
		managerButton.setFont(new Font("����", Font.BOLD, 18));
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

		// ���� ��ư
		ownerButton = new JButton("Owner");
		ownerButton.setForeground(Color.WHITE);
		ownerButton.setBackground(new Color(25, 25, 112));
		ownerButton.setFont(new Font("����", Font.BOLD, 18));
		ownerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �г� ����
				logOutPanel.setVisible(true);
				modePanel.setVisible(false);

			}
		});
		ownerButton.setBounds(397, 226, 150, 50);
		modePanel.add(ownerButton);

		// Log out Panel
		// �ڷΰ��� ��ư
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modePanel.setVisible(true);
				logOutPanel.setVisible(false);
			}
		});
		btnNewButton.setBackground(new Color(25, 25, 112));
		btnNewButton.setFont(new Font("����", Font.BOLD, 17));
		btnNewButton.setBounds(24, 21, 103, 40);
		logOutPanel.add(btnNewButton);

		// Log out Panel
		// �ΰ� �ؽ�Ʈ
		// �ΰ� �ؽ�Ʈ
		JLabel logo2 = new JLabel("STUDY CAFE");
		logo2.setForeground(Color.WHITE);
		logo2.setBounds(205, 81, 290, 50);
		logo2.setHorizontalAlignment(JLabel.CENTER);
		logOutPanel.add(logo2);
		// ��Ʈ ũ��
		logo2.setFont(new Font("Serif", Font.BOLD, 40));

		// ���̵� �Է�
		JLabel lblNewLabel = new JLabel("Owner ID : ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel.setBounds(112, 213, 112, 33);
		logOutPanel.add(lblNewLabel);

		JTextField textField = new JTextField();
		textField.setBounds(225, 212, 247, 40);
		logOutPanel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton_1 = new JButton("Log in");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(new Color(25, 25, 112));
		btnNewButton_1.setFont(new Font("����", Font.BOLD, 17));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���̵� ��ġ ���� Ȯ��
				if (mg.checkManagerID(textField.getText())) // ��ġ�� ��
				{
					OwnerFrame of = new OwnerFrame(mg);
					textField.setText(""); //�ؽ�Ʈ �ʵ忡 �Է��� ���̵� �����
					//panel visible����
					modePanel.setVisible(true);
					logOutPanel.setVisible(false);
				} else // ����ġ �� ��
				{
					MessageFrame ef = new MessageFrame("Wrong ID");
					textField.setText(""); //�ؽ�Ʈ �ʵ忡 �Է��� ���̵� �����
				}
			}
		});
		btnNewButton_1.setBounds(484, 211, 103, 40);
		logOutPanel.add(btnNewButton_1);
		
		//panel visible����
		modePanel.setVisible(true);
		logOutPanel.setVisible(false);
		//contentPane�� ������ panel �߰�
		modeCP.add(logOutPanel);
		modeCP.add(modePanel);
		// x��ư ������ �� �̺�Ʈó��
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ������ ���÷���
		this.setVisible(true);
	}
}
