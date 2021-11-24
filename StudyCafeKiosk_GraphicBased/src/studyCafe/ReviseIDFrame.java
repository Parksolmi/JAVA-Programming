package studyCafe;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class ReviseIDFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	public ReviseIDFrame(Management mg) {
		//윈도우 타이틀 설정
		this.setTitle("Revise ID");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(600, 400, 600, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(112, 128, 144));
		//레이아웃 설정(위치와 크기 자유지정)
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		//로고 텍스트
		JLabel logo = new JLabel("STUDY CAFE");
		logo.setForeground(Color.WHITE);
		logo.setBounds(190, 40, 200, 50);
		logo.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(logo);
		//폰트 크기
		logo.setFont(new Font("Serif", Font.BOLD, 30));
		
		JLabel lblNewLabel = new JLabel("ID            :");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setBounds(101, 104, 129, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewId = new JLabel("New ID     :");
		lblNewId.setForeground(Color.WHITE);
		lblNewId.setFont(new Font("굴림", Font.BOLD, 20));
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
		btnNewButton.setBackground(new Color(25, 25, 112));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//텍스트 필드가 비었을 경우
				if(textField.getText().equals(""))
				{
					MessageFrame mf = new MessageFrame("Enter the existing ID");
				}
				else if(textField_1.getText().equals(""))
				{
					MessageFrame mf = new MessageFrame("Enter the new ID");
				}
				//아이디 수정
				else
				{
					if(mg.checkManagerID(textField.getText()))
					{
						mg.reviseManagerID(textField_1.getText());
						MessageFrame mf = new MessageFrame("Revise ID Success");
						
						//파일에 저장
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
					}
				}
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton.setBounds(143, 201, 136, 34);
		contentPane.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(25, 25, 112));
		btnCancel.setForeground(Color.WHITE);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("굴림", Font.BOLD, 20));
		btnCancel.setBounds(299, 201, 136, 34);
		contentPane.add(btnCancel);
		
		
		setVisible(true);
		
	}

}
