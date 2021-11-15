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
				//������ ũ��
				this.setSize(600, 300);
				//������ ��ġ ����
				this.setLocation(600, 400);
				//������ Ÿ��Ʋ ����
				this.setTitle("Mode");
				//content pane ����
				Container modeCP = this.getContentPane();
				
				//���̾ƿ� ����(��ġ�� ũ�� ��������)
				modeCP.setLayout(null);
				
				//�ΰ� �ؽ�Ʈ
				JLabel logo = new JLabel("STUDY CAFE");
				logo.setBounds(190, 50, 200, 50);
				logo.setHorizontalAlignment(JLabel.CENTER);
				modeCP.add(logo);
				//��Ʈ ũ��
				logo.setFont(new Font("Serif", Font.BOLD, 30));
				
				//��� ��ư ����
				//�Ŵ��� ��ư
				managerButton = new JButton("Manager");
				managerButton.setFont(new Font("����", Font.BOLD, 15));
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
				
				//���� ��ư
				ownerButton = new JButton("Owner");
				ownerButton.setFont(new Font("����", Font.BOLD, 15));
				ownerButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						LogInFrame lf = new LogInFrame(mg);
					}
				});
				
				ownerButton.setBounds(350, 160, 150, 50);
				modeCP.add(ownerButton);
				
				//x��ư ������ �� �̺�Ʈó��
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//������ ���÷���
				this.setVisible(true);
			}

}
