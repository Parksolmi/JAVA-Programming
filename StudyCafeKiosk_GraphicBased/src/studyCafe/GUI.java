package studyCafe;
import java.awt.*;
import javax.swing.*;

import javax.swing.JFrame;

public class GUI
{
	public static void main(String[] args) throws Exception {
		
		//��� �����ϴ� ������
		
		
		//������ ����
		JFrame frame = new JFrame("Mode");
		//������ ũ�� �ٲٱ�
		frame.setPreferredSize(new Dimension(600, 300));
		//������ ��ġ ����
		frame.setLocation(500, 400);
		
		//content pane ����
		Container contentPane = frame.getContentPane();
		
		//�ΰ� �ؽ�Ʈ
		contentPane.add(new JLabel("STUDY CAFE" ,SwingConstants.CENTER));
		
		//��� ��ư
		//�г� ����
		JPanel buttonPanel = new JPanel();
		//���̾ƿ�
		FlowLayout buttonlayout = new FlowLayout();
		buttonPanel.setLayout(buttonlayout);
		//��ư ����
		JButton managerButton = new JButton("Manager");
		JButton ownerButton = new JButton("Owner");
		managerButton.setPreferredSize(new Dimension(150, 70));
		ownerButton.setPreferredSize(new Dimension(150, 70));
		buttonPanel.add(managerButton);
		buttonPanel.add(ownerButton);
		
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		
		//x��ư ������ �� �̺�Ʈó��
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//������ ���÷���
		frame.pack();
		frame.setVisible(true);
	}
}
