package studyCafe;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class AddRoomFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnCancel;

	public AddRoomFrame(Management mg,JTable roomTable) {
		
		//윈도우 타이틀 설정
		this.setTitle("Add Room");
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
		
		JTextField name = new JTextField();
		name.setBounds(242, 39, 221, 28);
		contentPane.add(name);
		name.setColumns(10);
		
		JTextField capacity = new JTextField();
		capacity.setColumns(10);
		capacity.setBounds(242, 77, 221, 28);
		contentPane.add(capacity);
		
		JTextField price = new JTextField();
		price.setColumns(10);
		price.setBounds(242, 118, 221, 28);
		contentPane.add(price);
		
		btnNewButton = new JButton("Add");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(25, 25, 112));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//JTextField가 비어져있다면
				if(name.getText().equals("") || capacity.getText().equals("") || price.getText().equals(""))
				{
					MessageFrame mf = new MessageFrame("Please enter all the data.");
				}
				else
				{
					//JTextField 타입 별로 가져오기
					String roomName = name.getText();
					int roomCapacity = Integer.parseInt(capacity.getText());
					int roomPrice = Integer.parseInt(price.getText());
					
					if(!mg.isRoomExist(roomName)) //해당 방 이름이 존재하지 않는다면
					{
						//방 만들기
						mg.createRoom(roomName, roomCapacity, roomPrice);
						
						//테이블에 추가
						String newRowArr[] = new String[4];
						int index = 0;
						newRowArr[index] = name.getText(); index++;
						newRowArr[index] = capacity.getText(); index++;
						newRowArr[index] = price.getText(); index++;
						newRowArr[index] = "Empty";
						
						DefaultTableModel tableModel = (DefaultTableModel) roomTable.getModel();
						tableModel.addRow(newRowArr);
	
						setVisible(false);
						
						//saveFlag true로 변경
						if(OwnerFrame.getSaveFlag() == false)
						{
							OwnerFrame.setSaveFlagTrue();
						}
					}
					else //해당 방 이름이 존재하면
					{
						MessageFrame mf = new MessageFrame(roomName + " is already exist");
					}
				}
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 20));
		btnNewButton.setBounds(147, 182, 131, 35);
		contentPane.add(btnNewButton);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setBackground(new Color(25, 25, 112));
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
