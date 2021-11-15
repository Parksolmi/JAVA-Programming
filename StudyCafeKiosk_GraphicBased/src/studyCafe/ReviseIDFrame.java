package studyCafe;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class ReviseIDFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	public ReviseIDFrame(Management mg) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(600, 400, 600, 300);
		contentPane = new JPanel();
		//���̾ƿ� ����(��ġ�� ũ�� ��������)
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//�ΰ� �ؽ�Ʈ
		JLabel logo = new JLabel("STUDY CAFE");
		logo.setBounds(190, 40, 200, 50);
		logo.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(logo);
		//��Ʈ ũ��
		logo.setFont(new Font("Serif", Font.BOLD, 30));
		
		JLabel lblNewLabel = new JLabel("ID            :");
		lblNewLabel.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel.setBounds(101, 104, 129, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewId = new JLabel("New ID     :");
		lblNewId.setFont(new Font("����", Font.BOLD, 20));
		lblNewId.setBounds(101, 148, 129, 34);
		contentPane.add(lblNewId);
		
		textField = new JTextField();
		textField.setBounds(235, 104, 247, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(235, 148, 247, 34);
		contentPane.add(textField_1);
		
		JButton btnNewButton = new JButton("Revise");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//���̵� ����
				if(mg.checkManagerID(textField.getText()))
				{
					mg.reviseManagerID(textField_1.getText());
					MessageFrame mf = new MessageFrame("Revise ID Success");
					
					//���Ͽ� ����
					File ownerIDFile = new File("studyCafeOwnerID.dat");
					DataOutputStream idOut = null;
					
					try {
						idOut = new DataOutputStream(new FileOutputStream(ownerIDFile));
						mg.writeID(idOut);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					setVisible(false);
				}
				else
				{
					MessageFrame mf = new MessageFrame("Wrong ID");
					setVisible(false);
				}
			}
		});
		btnNewButton.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton.setBounds(143, 201, 136, 34);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("����", Font.BOLD, 20));
		btnCancel.setBounds(299, 201, 136, 34);
		contentPane.add(btnCancel);
		
		
		setVisible(true);
		
	}

}
