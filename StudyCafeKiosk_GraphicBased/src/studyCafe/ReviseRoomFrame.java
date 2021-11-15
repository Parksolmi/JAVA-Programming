package studyCafe;

import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;

@SuppressWarnings("serial")
public class ReviseRoomFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnCancel;
	
	public ReviseRoomFrame(Management mg, JTable roomTable) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(600, 400, 600, 300);
		contentPane = new JPanel();
		//레이아웃 설정(위치와 크기 자유지정)
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Name        :");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(104, 39, 137, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblCapacity = new JLabel("Capacity    :");
		lblCapacity.setHorizontalAlignment(SwingConstants.LEFT);
		lblCapacity.setFont(new Font("굴림", Font.BOLD, 20));
		lblCapacity.setBounds(104, 77, 137, 35);
		contentPane.add(lblCapacity);
		
		JLabel lblPrice = new JLabel("Price         :");
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setFont(new Font("굴림", Font.BOLD, 20));
		lblPrice.setBounds(104, 112, 137, 35);
		contentPane.add(lblPrice);
		
		DefaultTableModel defaultMode = (DefaultTableModel) roomTable.getModel();
		int row = roomTable.getSelectedRow();
		
		//textField
		JTextField name = new JTextField();
		name.setBounds(242, 39, 221, 28);
		String roomName = String.valueOf(defaultMode.getValueAt(row, 0));
		name.setText(roomName);
		contentPane.add(name);
		name.setColumns(10);
		
		JTextField capacity = new JTextField();
		capacity.setColumns(10);
		capacity.setBounds(242, 77, 221, 28);
		String roomCapacity = String.valueOf(defaultMode.getValueAt(row, 1));
		capacity.setText(roomCapacity);
		contentPane.add(capacity);
		
		JTextField price = new JTextField();
		price.setColumns(10);
		price.setBounds(242, 118, 221, 28);
		String roomPrice = String.valueOf(defaultMode.getValueAt(row, 2));
		price.setText(roomPrice);
		contentPane.add(price);
		
		btnNewButton = new JButton("Revise");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!mg.isRoomExist(name.getText())) //방 이름이 없다면
				{
					//방 정보 수정
					try {
						//인원 수정
						mg.changeCapacity(roomName, Integer.parseInt(capacity.getText()));
						//가격 수정
						mg.changePricePerHour(roomName, Integer.parseInt(price.getText()));
						//이름 수정
						mg.changeRoomName(roomName, name.getText());
						
						//테이블 내용 변경
						defaultMode.setValueAt(name.getText(), row, 0);
						defaultMode.setValueAt(capacity.getText(), row, 1);
						defaultMode.setValueAt(price.getText(), row, 2);
						
						setVisible(false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else //해당 방 이름이 존재한다면
				{
					MessageFrame mf = new MessageFrame(name.getText() + " is already exist");
				}
				
				
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton.setBounds(147, 182, 131, 35);
		contentPane.add(btnNewButton);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setFont(new Font("굴림", Font.BOLD, 20));
		btnCancel.setBounds(310, 182, 131, 35);
		contentPane.add(btnCancel);
		
		//프레임 디스플레이
		setVisible(true);
	}

}
