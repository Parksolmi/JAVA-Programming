package studyCafe;

import java.awt.Font;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.Color;

@SuppressWarnings("serial")
public class ReviseRoomFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnCancel;
	
	public ReviseRoomFrame(Management mg, JTable roomTable) {
		//윈도우 타이틀 설정
		this.setTitle("Revise Room");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(600, 400, 600, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(112, 128, 144));
		//레이아웃 설정(위치와 크기 자유지정)
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Name        :");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(104, 39, 137, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblCapacity = new JLabel("Capacity    :");
		lblCapacity.setForeground(Color.WHITE);
		lblCapacity.setHorizontalAlignment(SwingConstants.LEFT);
		lblCapacity.setFont(new Font("굴림", Font.BOLD, 20));
		lblCapacity.setBounds(104, 77, 137, 35);
		contentPane.add(lblCapacity);
		
		JLabel lblPrice = new JLabel("Price         :");
		lblPrice.setForeground(Color.WHITE);
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
		
		String orgRoomName = name.getText(); //수정 전 방 이름
		
		btnNewButton = new JButton("Revise");
		btnNewButton.setBackground(new Color(25, 25, 112));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//textField가 비었다면
				if(name.getText().equals("") || capacity.getText().equals("") || price.getText().equals(""))
				{
					MessageFrame mf = new MessageFrame("Please enter all the data.");
				}
				else
				{
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
							
							//saveFlag true로 변경
							if(OwnerFrame.getSaveFlag() == false)
							{
								OwnerFrame.setSaveFlagTrue();
							}
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					else //해당 방 이름이 존재한다면
					{
						if (name.getText().equals(orgRoomName)) //존재 하는 방 이름이 자기 자신이면
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
								
								//saveFlag true로 변경
								if(OwnerFrame.getSaveFlag() == false)
								{
									OwnerFrame.setSaveFlagTrue();
								}
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						else
						{
							MessageFrame mf = new MessageFrame(name.getText() + " is already exist");
						}
					}
				}
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton.setBounds(147, 182, 131, 35);
		contentPane.add(btnNewButton);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(25, 25, 112));
		btnCancel.setForeground(Color.WHITE);
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
