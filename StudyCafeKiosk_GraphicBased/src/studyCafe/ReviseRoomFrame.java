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
		//������ Ÿ��Ʋ ����
		this.setTitle("Revise Room");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(600, 400, 600, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(112, 128, 144));
		//���̾ƿ� ����(��ġ�� ũ�� ��������)
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Name        :");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("����", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(104, 39, 137, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblCapacity = new JLabel("Capacity    :");
		lblCapacity.setForeground(Color.WHITE);
		lblCapacity.setHorizontalAlignment(SwingConstants.LEFT);
		lblCapacity.setFont(new Font("����", Font.BOLD, 20));
		lblCapacity.setBounds(104, 77, 137, 35);
		contentPane.add(lblCapacity);
		
		JLabel lblPrice = new JLabel("Price         :");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrice.setFont(new Font("����", Font.BOLD, 20));
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
		
		String orgRoomName = name.getText(); //���� �� �� �̸�
		
		btnNewButton = new JButton("Revise");
		btnNewButton.setBackground(new Color(25, 25, 112));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//textField�� ����ٸ�
				if(name.getText().equals("") || capacity.getText().equals("") || price.getText().equals(""))
				{
					MessageFrame mf = new MessageFrame("Please enter all the data.");
				}
				else
				{
					if(!mg.isRoomExist(name.getText())) //�� �̸��� ���ٸ�
					{
						//�� ���� ����
						try {
							//�ο� ����
							mg.changeCapacity(roomName, Integer.parseInt(capacity.getText()));
							//���� ����
							mg.changePricePerHour(roomName, Integer.parseInt(price.getText()));
							//�̸� ����
							mg.changeRoomName(roomName, name.getText());
							
							//���̺� ���� ����
							defaultMode.setValueAt(name.getText(), row, 0);
							defaultMode.setValueAt(capacity.getText(), row, 1);
							defaultMode.setValueAt(price.getText(), row, 2);
							
							setVisible(false);
							
							//saveFlag true�� ����
							if(OwnerFrame.getSaveFlag() == false)
							{
								OwnerFrame.setSaveFlagTrue();
							}
							
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					else //�ش� �� �̸��� �����Ѵٸ�
					{
						if (name.getText().equals(orgRoomName)) //���� �ϴ� �� �̸��� �ڱ� �ڽ��̸�
						{
							//�� ���� ����
							try {
								//�ο� ����
								mg.changeCapacity(roomName, Integer.parseInt(capacity.getText()));
								//���� ����
								mg.changePricePerHour(roomName, Integer.parseInt(price.getText()));
								//�̸� ����
								mg.changeRoomName(roomName, name.getText());
								
								//���̺� ���� ����
								defaultMode.setValueAt(name.getText(), row, 0);
								defaultMode.setValueAt(capacity.getText(), row, 1);
								defaultMode.setValueAt(price.getText(), row, 2);
								
								setVisible(false);
								
								//saveFlag true�� ����
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
		btnNewButton.setFont(new Font("����", Font.BOLD, 20));
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
		btnCancel.setFont(new Font("����", Font.BOLD, 20));
		btnCancel.setBounds(310, 182, 131, 35);
		contentPane.add(btnCancel);
		
		//������ ���÷���
		setVisible(true);
	}

}
